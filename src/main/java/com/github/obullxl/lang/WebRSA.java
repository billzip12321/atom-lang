/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.utils.ObjectUtils;

/**
 * RSA加解密(Java&JS)
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebRSA.java, V1.0.1 2014年6月4日 下午9:19:44 $
 */
public class WebRSA {
    private static final Logger   logger           = LogUtils.get();

    /** 算法名称 */
    private static final String   ALGORITHOM       = "RSA";

    /** 密钥大小 */
    private static final int      KEY_SIZE         = 1024;

    /** 默认的安全服务提供者 */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    /** 缓存的密钥对 */
    private KeyPair               oneKeyPair;

    /** KeyPair对象文本内容对象 */
    private KeyPairObjectText     keyPairObjectText;
    
    /**
     * DI-KeyPair对象文本
     */
    public void setKeyPairObjectText(KeyPairObjectText keyPairObjectText) {
        this.keyPairObjectText = keyPairObjectText;
    }

    /**
     * KeyPair对象文本内容对象
     */
    public static interface KeyPairObjectText {
        /** 获取文本内容 */
        public String getObjectText();

        /** 存储文本内容 */
        public void saveObjectText(String text);
    }

    /**
     * 生成并存储RSA密钥对。
     */
    public KeyPair newKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            keyPairGen.initialize(KEY_SIZE, new SecureRandom(Long.toString(System.currentTimeMillis()).getBytes()));

            this.oneKeyPair = keyPairGen.generateKeyPair();
            this.keyPairObjectText.saveObjectText(ObjectUtils.toBase64(this.oneKeyPair));

            return this.oneKeyPair;
        } catch (Exception e) {
            logger.error("[RSA]-新建RSA密钥对异常！", e);
        }

        return null;
    }

    /**
     * 返回已初始化的默认的公钥。
     */
    public RSAPublicKey findPublicKey() {
        return (RSAPublicKey) this.findKeyPair().getPublic();
    }

    /**
     * 返回已初始化的默认的私钥。
     */
    public RSAPrivateKey findPrivateKey() {
        return (RSAPrivateKey) this.findKeyPair().getPrivate();
    }

    /**
     * 返回RSA密钥对
     */
    public KeyPair findKeyPair() {
        if (this.oneKeyPair != null) {
            return this.oneKeyPair;
        }

        String text = this.keyPairObjectText.getObjectText();
        if (StringUtils.isBlank(text)) {
            this.oneKeyPair = (KeyPair) ObjectUtils.fromBase64(text);
            if (this.oneKeyPair != null) {
                return this.oneKeyPair;
            }
        }

        this.oneKeyPair = newKeyPair();
        if (this.oneKeyPair == null) {
            throw new RuntimeException("[RSA]-密钥对无法初始化！");
        }

        return this.oneKeyPair;
    }

    /**
     * 使用指定的私钥解密数据。
     *
     * @param privateKey 给定的私钥。
     * @param data 要解密的数据。
     * @return 原数据。
     */
    public byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);

        return ci.doFinal(data);
    }

    /**
     * 使用给定的私钥解密给定的字符串。
     * <p />
     * 若私钥为 {@code null}，或者 {@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
     * 私钥不匹配时，返回 {@code null}。
     *
     * @param privateKey 给定的私钥。
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    public String decryptString(PrivateKey privateKey, String encrypttext) {
        if (privateKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }

        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt(privateKey, en_data);
            return new String(data);
        } catch (Exception e) {
            logger.error("[RSA]-根据密钥解密异常！", e);
        }

        return null;
    }

    /**
     * 使用默认的私钥解密给定的字符串。
     * <p />
     * 若{@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
     * 私钥不匹配时，返回 {@code null}。
     *
     * @param encrypt 密文。
     * @return 原文字符串。
     */
    private String decryptString(String encrypt) {
        if (StringUtils.isBlank(encrypt)) {
            return null;
        }

        try {
            byte[] data = Hex.decodeHex(encrypt.toCharArray());
            byte[] cache = this.decrypt(this.findPrivateKey(), data);
            return new String(cache);
        } catch (Exception e) {
            logger.error("[RSA]-字符串[" + encrypt + "]解密异常！", e);
        }

        return null;
    }

    /**
     * 私钥解密由JS加密的字符串。
     *
     * @param encrypt 密文。
     * @return {@code encrypt} 的原文字符串。
     */
    public String decryptJS(String encrypt) {
        String text = this.decryptString(encrypt);

        if (text == null) {
            return null;
        }

        return StringUtils.reverse(text);
    }

}
