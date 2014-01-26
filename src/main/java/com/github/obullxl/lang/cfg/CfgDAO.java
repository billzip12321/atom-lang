/**
 * obullxl@gmail.com
 */
package com.github.obullxl.lang.cfg;

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
 * 系统参数DAO实现
 */
public class CfgDAO {
    /** The name of the DAO */
    public static final String  NAME               = "CfgDAO";

    /** Logger */
    private static final Logger logger             = LogUtils.get();

    /** 数据源 */
    private DataSource          dataSource;

    /** 参数数据表 */
    private String              tableName          = "atom_cfg";

    /** 参数分类 */
    private String              catgFieldName      = "catg";

    /** 参数KEY */
    private String              nameFieldName      = "name";

    /** 参数说明 */
    private String              titleFieldName     = "title";

    /** 参数值 */
    private String              valueFieldName     = "value";

    /** 参数扩展值 */
    private String              valueExtFieldName  = "value_ext";

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
        Validate.notNull(this.dataSource, "[系统参数]-数据源注入失败!");

        logger.warn("[系统参数]-数据表信息:{}({},{},{},{},{},{},{}).", //
            this.tableName, this.catgFieldName, this.nameFieldName, this.titleFieldName, //
            this.valueFieldName, this.valueExtFieldName, this.gmtCreateFieldName, this.gmtModifyFieldName);

        this.findInsertSQL();
        logger.warn("[系统参数]-InsertSQL: {}", this.insertSQL);

        this.findUpdateSQL();
        logger.warn("[系统参数]-UpdateSQL: {}", this.updateSQL);

        this.findSelectSQL();
        logger.warn("[系统参数]-SelectSQL: {}", this.selectSQL);
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
                    sql.append(",").append(this.nameFieldName);
                    sql.append(",").append(this.titleFieldName);
                    sql.append(",").append(this.valueFieldName);
                    sql.append(",").append(this.valueExtFieldName);
                    sql.append(",").append(this.gmtCreateFieldName);
                    sql.append(",").append(this.gmtModifyFieldName);
                    sql.append(") VALUES(");
                    sql.append("?,?,?,?,?,?,?");
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
                    sql.append(this.titleFieldName).append("=?");
                    sql.append(",").append(this.valueFieldName).append("=?");
                    sql.append(",").append(this.valueExtFieldName).append("=?");
                    sql.append(",").append(this.gmtModifyFieldName).append("=?");
                    sql.append(" WHERE ");
                    sql.append(this.catgFieldName).append("=?");
                    sql.append(" AND ").append(this.nameFieldName).append("=?");

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
                    sql.append(",").append(this.nameFieldName);
                    sql.append(",").append(this.titleFieldName);
                    sql.append(",").append(this.valueFieldName);
                    sql.append(",").append(this.valueExtFieldName);
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
     * 插入系统参数
     */
    public void insert(CfgDTO cfg) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(this.findInsertSQL());

            // 设置值
            stmt.setString(1, cfg.getCatg());
            stmt.setString(2, cfg.getName());
            stmt.setString(3, cfg.getTitle());
            stmt.setString(4, cfg.getValue());
            stmt.setString(5, cfg.getValueExt());

            if (cfg.getGmtCreate() == null) {
                stmt.setTimestamp(6, null);
            } else {
                stmt.setTimestamp(6, new Timestamp(cfg.getGmtCreate().getTime()));
            }

            if (cfg.getGmtModify() == null) {
                stmt.setTimestamp(7, null);
            } else {
                stmt.setTimestamp(7, new Timestamp(cfg.getGmtModify().getTime()));
            }

            // 执行插入
            stmt.execute();
        } catch (Exception e) {
            String txt = "[系统参数]-插入系统参数[" + cfg + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 更新系统参数
     */
    public int update(CfgDTO cfg) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(this.findUpdateSQL());

            // 设置值
            stmt.setString(1, cfg.getTitle());
            stmt.setString(2, cfg.getValue());
            stmt.setString(3, cfg.getValueExt());

            if (cfg.getGmtModify() == null) {
                stmt.setTimestamp(4, null);
            } else {
                stmt.setTimestamp(4, new Timestamp(cfg.getGmtModify().getTime()));
            }

            stmt.setString(5, cfg.getCatg());
            stmt.setString(6, cfg.getName());

            // 执行更新
            return stmt.executeUpdate();
        } catch (Exception e) {
            String txt = "[系统参数]-更新系统参数[" + cfg + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 查询系统参数
     */
    public List<CfgDTO> find() {
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
            String txt = "[系统参数]-查询系统参数异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 删除系统参数
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
            String txt = "[系统参数]-删除系统参数[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 根据分类删除系统参数
     */
    public int delete(String catg) {
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
            String txt = "[系统参数]-删除系统参数[" + sql + "]异常!";
            logger.error(txt, e);
            throw new RuntimeException(txt, e);
        } finally {
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }

    /**
     * 根据分类+名称删除系统参数
     */
    public int delete(String catg, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM " + this.tableName + " WHERE " + //
                     this.catgFieldName + "=? AND " + this.nameFieldName + "=?";
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, catg);
            stmt.setString(2, name);

            // 执行删除
            return stmt.executeUpdate();
        } catch (Exception e) {
            String txt = "[系统参数]-删除系统参数[" + sql + "]异常!";
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
    private List<CfgDTO> map(ResultSet rs) throws SQLException {
        List<CfgDTO> cfgs = new ArrayList<CfgDTO>();

        while (rs.next()) {
            CfgDTO cfg = new CfgDTO();

            cfg.setCatg(rs.getString(this.catgFieldName));
            cfg.setName(rs.getString(this.nameFieldName));
            cfg.setTitle(rs.getString(this.titleFieldName));
            cfg.setValue(rs.getString(this.valueFieldName));
            cfg.setValueExt(rs.getString(this.valueExtFieldName));
            cfg.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            cfg.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            cfgs.add(cfg);
        }

        return cfgs;
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

    public void setNameFieldName(String nameFieldName) {
        this.nameFieldName = nameFieldName;
    }

    public void setTitleFieldName(String titleFieldName) {
        this.titleFieldName = titleFieldName;
    }

    public void setValueFieldName(String valueFieldName) {
        this.valueFieldName = valueFieldName;
    }

    public void setValueExtFieldName(String valueExtFieldName) {
        this.valueExtFieldName = valueExtFieldName;
    }

    public void setGmtCreateFieldName(String gmtCreateFieldName) {
        this.gmtCreateFieldName = gmtCreateFieldName;
    }

    public void setGmtModifyFieldName(String gmtModifyFieldName) {
        this.gmtModifyFieldName = gmtModifyFieldName;
    }

}
