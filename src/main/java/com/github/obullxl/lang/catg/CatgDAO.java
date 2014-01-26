/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.catg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.DBUtils;
import com.github.obullxl.lang.utils.LogUtils;

/**
 * 模块分类DAO实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgDAO.java, V1.0.1 2014年1月26日 上午11:59:19 $
 */
public class CatgDAO {
    /** The name of the DAO */
    public static final String  NAME               = "CatgDAO";

    /** Logger */
    private static final Logger logger             = LogUtils.get();

    /** 数据源 */
    private DataSource          dataSource;

    /** 参数数据表 */
    private String              tableName          = "atom_catg";

    /** 上级分类 */
    private String              catgFieldName      = "catg";

    /** 分类代码 */
    private String              codeFieldName      = "code";

    /** 分类排序值 */
    private String              sortFieldName      = "sort";

    /** 分类说明 */
    private String              titleFieldName     = "title";

    /** 分类扩展值 */
    private String              extMapFieldName    = "ext_map";

    /** 分类摘要描述 */
    private String              summaryFieldName   = "summary";

    /** CreateTime */
    private String              gmtCreateFieldName = "gmt_create";

    /** ModifyTime */
    private String              gmtModifyFieldName = "gmt_modify";

    /** 插入SQL */
    private final Lock          insertLock         = new ReentrantLock();
    private String              insertSQL;

    /** 更新SQL */
    private final Lock          updateLock         = new ReentrantLock();
    private String              updateSQL;

    /** 查询SQL */
    private final Lock          selectLock         = new ReentrantLock();
    private String              selectSQL;

    /**
     * 初始化
     */
    public void init() {
        Validate.notNull(this.dataSource, "[模块分类]-数据源注入失败!");

        logger.warn("[模块分类]-数据表信息:{}({},{},{},{},{},{},{},{}).", //
            this.tableName, this.catgFieldName, this.codeFieldName, this.sortFieldName, this.titleFieldName, //
            this.extMapFieldName, this.summaryFieldName, this.gmtCreateFieldName, this.gmtModifyFieldName);

        this.findInsertSQL();
        logger.warn("[模块分类]-InsertSQL: {}", this.insertSQL);

        this.findUpdateSQL();
        logger.warn("[模块分类]-UpdateSQL: {}", this.updateSQL);

        this.findSelectSQL();
        logger.warn("[模块分类]-SelectSQL: {}", this.selectSQL);
    }

    /**
     * 获取INSERT SQL
     */
    private String findInsertSQL() {
        if (this.insertSQL == null) {
            this.insertLock.lock();
            try {
                if (this.insertSQL == null) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO ").append(this.tableName).append("(");
                    sql.append(this.catgFieldName);
                    sql.append(",").append(this.codeFieldName);
                    sql.append(",").append(this.sortFieldName);
                    sql.append(",").append(this.titleFieldName);
                    sql.append(",").append(this.extMapFieldName);
                    sql.append(",").append(this.summaryFieldName);
                    sql.append(",").append(this.gmtCreateFieldName);
                    sql.append(",").append(this.gmtModifyFieldName);
                    sql.append(") VALUES(");
                    sql.append("?,?,?,?,?,?,?,?");
                    sql.append(")");

                    this.insertSQL = sql.toString();
                }
            } finally {
                this.insertLock.unlock();
            }
        }

        return this.insertSQL;
    }

    /**
     * 获取UPDATE SQL
     */
    private String findUpdateSQL() {
        if (this.updateSQL == null) {
            this.updateLock.lock();
            try {
                if (this.updateSQL == null) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("UPDATE ").append(this.tableName);
                    sql.append(" SET ");
                    sql.append(this.catgFieldName).append("=?");
                    sql.append(",").append(this.sortFieldName).append("=?");
                    sql.append(",").append(this.titleFieldName).append("=?");
                    sql.append(",").append(this.extMapFieldName).append("=?");
                    sql.append(",").append(this.summaryFieldName).append("=?");
                    sql.append(",").append(this.gmtModifyFieldName).append("=?");
                    sql.append(" WHERE ");
                    sql.append(this.codeFieldName).append("=?");

                    this.updateSQL = sql.toString();
                }
            } finally {
                this.updateLock.unlock();
            }
        }

        return this.updateSQL;
    }

    /**
     * 获取SELECT SQL
     */
    private String findSelectSQL() {
        if (this.selectSQL == null) {
            this.selectLock.lock();
            try {
                if (this.selectSQL == null) {
                    StringBuilder sql = new StringBuilder();
                    sql.append("SELECT ");
                    sql.append(this.catgFieldName);
                    sql.append(",").append(this.codeFieldName);
                    sql.append(",").append(this.sortFieldName);
                    sql.append(",").append(this.titleFieldName);
                    sql.append(",").append(this.extMapFieldName);
                    sql.append(",").append(this.summaryFieldName);
                    sql.append(",").append(this.gmtCreateFieldName);
                    sql.append(",").append(this.gmtModifyFieldName);
                    sql.append(" FROM ").append(this.tableName);

                    this.selectSQL = sql.toString();
                }
            } finally {
                this.selectLock.unlock();
            }
        }

        return this.selectSQL;
    }

    /**
     * 插入模块分类
     */
    public void insert(CatgDTO catg) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(this.findInsertSQL());

            // 设置值
            stmt.setString(1, catg.getCatg());
            stmt.setString(2, catg.getCode());
            stmt.setString(3, catg.getSort());
            stmt.setString(4, catg.getTitle());
            stmt.setString(5, catg.getExtMap());
            stmt.setString(6, catg.getSummary());

            if (catg.getGmtCreate() == null) {
                stmt.setTimestamp(7, null);
            } else {
                stmt.setTimestamp(7, new Timestamp(catg.getGmtCreate().getTime()));
            }

            if (catg.getGmtModify() == null) {
                stmt.setTimestamp(8, null);
            } else {
                stmt.setTimestamp(8, new Timestamp(catg.getGmtModify().getTime()));
            }

            // 执行插入
            stmt.execute();
        } catch (Exception e) {
            String txt = "[模块分类]-插入模块分类[" + catg + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 更新模块分类
     */
    public int update(CatgDTO catg) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(this.findUpdateSQL());

            // 设置值
            stmt.setString(1, catg.getCatg());
            stmt.setString(2, catg.getSort());
            stmt.setString(3, catg.getTitle());
            stmt.setString(4, catg.getExtMap());
            stmt.setString(5, catg.getSummary());

            if (catg.getGmtModify() == null) {
                stmt.setTimestamp(6, null);
            } else {
                stmt.setTimestamp(6, new Timestamp(catg.getGmtModify().getTime()));
            }

            stmt.setString(7, catg.getCode());

            // 执行更新
            return stmt.executeUpdate();
        } catch (Exception e) {
            String txt = "[模块分类]-更新模块分类[" + catg + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 查询模块分类
     */
    public List<CatgDTO> find() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.createStatement();

            // 执行查询
            rs = stmt.executeQuery(this.findSelectSQL());

            // 对象映射
            return this.map(rs);
        } catch (Exception e) {
            String txt = "[模块分类]-查询模块分类异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 删除模块分类
     */
    public int delete() {
        Connection conn = null;
        Statement stmt = null;
        String sql = "DELETE FROM " + this.tableName;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.createStatement();

            // 执行删除
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            String txt = "[模块分类]-删除模块分类[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 根据代码删除模块分类
     */
    public int delete(String code) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.codeFieldName + "=?";
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);

            // 执行删除
            return stmt.executeUpdate();
        } catch (Exception e) {
            String txt = "[模块分类]-删除模块分类[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 根据分类删除模块分类
     */
    public int deleteByCatg(String catg) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM " + this.tableName + " WHERE " + this.catgFieldName + "=?";
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, catg);

            // 执行删除
            return stmt.executeUpdate();
        } catch (Exception e) {
            String txt = "[模块分类]-删除模块分类[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 对象映射
     */
    private List<CatgDTO> map(ResultSet rs) throws SQLException {
        List<CatgDTO> catgs = new ArrayList<CatgDTO>();

        while (rs.next()) {
            CatgDTO catg = new CatgDTO();

            catg.setCatg(rs.getString(this.catgFieldName));
            catg.setCode(rs.getString(this.codeFieldName));
            catg.setSort(rs.getString(this.sortFieldName));
            catg.setTitle(rs.getString(this.titleFieldName));
            catg.setExtMap(rs.getString(this.extMapFieldName));
            catg.setSummary(rs.getString(this.summaryFieldName));
            catg.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            catg.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            catgs.add(catg);
        }

        return catgs;
    }

    // ~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~ //

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setCatgFieldName(String catgFieldName) {
        this.catgFieldName = catgFieldName;
    }

    public void setCodeFieldName(String codeFieldName) {
        this.codeFieldName = codeFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public void setTitleFieldName(String titleFieldName) {
        this.titleFieldName = titleFieldName;
    }

    public void setExtMapFieldName(String extMapFieldName) {
        this.extMapFieldName = extMapFieldName;
    }

    public void setSummaryFieldName(String summaryFieldName) {
        this.summaryFieldName = summaryFieldName;
    }

    public void setGmtCreateFieldName(String gmtCreateFieldName) {
        this.gmtCreateFieldName = gmtCreateFieldName;
    }

    public void setGmtModifyFieldName(String gmtModifyFieldName) {
        this.gmtModifyFieldName = gmtModifyFieldName;
    }

}
