/**
 * obullxl@gmail.com
 */
package com.github.obullxl.model.cfg.dao;

import com.github.obullxl.model.cfg.CfgModel;

/**
 * 参数模型DAO实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgDAO.java, V1.0.1 2014年2月5日 下午3:01:58 $
 */
public interface CfgDAO {
    public static final String NAME = "CfgDAO";

    /**
     * 插入参数模型
     */
    public void insert(CfgModel cfg);

    /**
     * 更新参数模型
     */
    public int update(CfgModel cfg);

    /**
     * 根据分类删除参数模型
     */
    public int delete(String catg);

    /**
     * 根据分类+名称删除参数模型
     */
    public int delete(String catg, String name);

}
