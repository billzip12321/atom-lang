/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.lang.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;

/**
 * 用户模型DAO实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserDAO.java, V1.0.1 2014年1月28日 上午9:57:57 $
 */
public class UserDAO extends AbstractDAO {
    public static final String NAME                    = "UserDAO";

    /** 编号 */
    private String             noFieldName             = "no";

    /** 位状态 */
    private String             flagFieldName           = "flag";

    /** 昵称/显示名称 */
    private String             nickNameFieldName       = "nick_name";

    /** 登录密码 */
    private String             passwdFieldName         = "passwd";

    /** 登录密码错误输入次数 */
    private String             passwdErrCountFieldName = "passwd_err_count";

    /** 注册日期（格式：yyyy-MM-dd） */
    private String             registDateFieldName     = "regist_date";

    /** 激活日期（格式：yyyy-MM-dd） */
    private String             activeDateFieldName     = "active_date";

    /** 认证日期（格式：yyyy-MM-dd） */
    private String             authDateFieldName       = "auth_date";

    /** 手机号码 */
    private String             mobileFieldName         = "mobile";

    /** 电子邮箱 */
    private String             emailFieldName          = "email";

    /** 真实姓名 */
    private String             realNameFieldName       = "real_name";

    /** 出生日期 */
    private String             birthDateFieldName      = "birth_date";

    /** 头像地址 */
    private String             avatarPathFieldName     = "avatar_path";

    /** 现居地邮政编码 */
    private String             postCodeFieldName       = "post_code";

    /** 现居地省份代码 */
    private String             provinceCodeFieldName   = "province_code";

    /** 现居地市代码 */
    private String             cityCodeFieldName       = "city_code";

    /** 现居地县代码 */
    private String             countyCodeFieldName     = "county_code";

    /** 现居地街道信息 */
    private String             streetInfoFieldName     = "street_info";

    /** 插入SQL */
    private String             insertSQL;

    /** 更新SQL */
    private String             updateSQL;

    /** 查询SQL */
    private String             tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.noFieldName);
            sql.append(",").append(this.flagFieldName);
            sql.append(",").append(this.nickNameFieldName);
            sql.append(",").append(this.passwdFieldName);
            sql.append(",").append(this.passwdErrCountFieldName);
            sql.append(",").append(this.registDateFieldName);
            sql.append(",").append(this.activeDateFieldName);
            sql.append(",").append(this.authDateFieldName);
            sql.append(",").append(this.mobileFieldName);
            sql.append(",").append(this.emailFieldName);
            sql.append(",").append(this.realNameFieldName);
            sql.append(",").append(this.birthDateFieldName);
            sql.append(",").append(this.avatarPathFieldName);
            sql.append(",").append(this.postCodeFieldName);
            sql.append(",").append(this.provinceCodeFieldName);
            sql.append(",").append(this.cityCodeFieldName);
            sql.append(",").append(this.countyCodeFieldName);
            sql.append(",").append(this.streetInfoFieldName);
            sql.append(",").append(this.gmtCreateFieldName);
            sql.append(",").append(this.gmtModifyFieldName);

            this.tableFields = sql.toString();
        }

        return this.tableFields;
    }

    /**
     * 插入用户模型
     */
    public void insert(final UserDTO user) {
        // SQL
        if (this.insertSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(this.tableName).append("(");
            sql.append(this.findTableFields());
            sql.append(") VALUES(");
            sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[用户模型]-InsertSQL: {}", this.insertSQL);
        }

        // 执行插入
        JdbcInsert.execute(this.dataSource, this.insertSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, user.getNo());
                stmt.setString(2, user.findValve().getValve());
                stmt.setString(3, user.getNickName());
                stmt.setString(4, user.getPasswd());
                stmt.setInt(5, user.getPasswdErrCount());
                stmt.setString(6, user.getRegistDate());
                stmt.setString(7, user.getActiveDate());
                stmt.setString(8, user.getAuthDate());
                stmt.setString(9, user.getMobile());
                stmt.setString(10, user.getEmail());
                stmt.setString(11, user.getRealName());
                stmt.setString(12, user.getBirthDate());
                stmt.setString(13, user.getAvatarPath());
                stmt.setString(14, user.getPostCode());
                stmt.setString(15, user.getProvinceCode());
                stmt.setString(16, user.getCityCode());
                stmt.setString(17, user.getCountyCode());
                stmt.setString(18, user.getStreetInfo());

                if (user.getGmtCreate() == null) {
                    stmt.setTimestamp(19, null);
                } else {
                    stmt.setTimestamp(19, new Timestamp(user.getGmtCreate().getTime()));
                }

                if (user.getGmtModify() == null) {
                    stmt.setTimestamp(20, null);
                } else {
                    stmt.setTimestamp(20, new Timestamp(user.getGmtModify().getTime()));
                }
            }
        });
    }

    /**
     * 更新用户模型
     */
    public int update(final UserDTO user) {
        // SQL
        if (this.updateSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(this.tableName);
            sql.append(" SET ");

            sql.append(this.flagFieldName).append("=?");
            sql.append(",").append(this.nickNameFieldName).append("=?");
            sql.append(",").append(this.passwdFieldName).append("=?");
            sql.append(",").append(this.passwdErrCountFieldName).append("=?");
            sql.append(",").append(this.registDateFieldName).append("=?");
            sql.append(",").append(this.activeDateFieldName).append("=?");
            sql.append(",").append(this.authDateFieldName).append("=?");
            sql.append(",").append(this.mobileFieldName).append("=?");
            sql.append(",").append(this.emailFieldName).append("=?");
            sql.append(",").append(this.realNameFieldName).append("=?");
            sql.append(",").append(this.birthDateFieldName).append("=?");
            sql.append(",").append(this.avatarPathFieldName).append("=?");
            sql.append(",").append(this.postCodeFieldName).append("=?");
            sql.append(",").append(this.provinceCodeFieldName).append("=?");
            sql.append(",").append(this.cityCodeFieldName).append("=?");
            sql.append(",").append(this.countyCodeFieldName).append("=?");
            sql.append(",").append(this.streetInfoFieldName).append("=?");
            sql.append(",").append(this.gmtModifyFieldName).append("=?");

            sql.append(" WHERE ");
            sql.append(this.noFieldName).append("=?");

            this.updateSQL = sql.toString();
            logger.warn("[用户模型]-UpdateSQL: {}", this.updateSQL);
        }

        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, this.updateSQL, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, user.findValve().getValve());
                stmt.setString(2, user.getNickName());
                stmt.setString(3, user.getPasswd());
                stmt.setInt(4, user.getPasswdErrCount());
                stmt.setString(5, user.getRegistDate());
                stmt.setString(6, user.getActiveDate());
                stmt.setString(7, user.getAuthDate());
                stmt.setString(8, user.getMobile());
                stmt.setString(9, user.getEmail());
                stmt.setString(10, user.getRealName());
                stmt.setString(11, user.getBirthDate());
                stmt.setString(12, user.getAvatarPath());
                stmt.setString(13, user.getPostCode());
                stmt.setString(14, user.getProvinceCode());
                stmt.setString(15, user.getCityCode());
                stmt.setString(16, user.getCountyCode());
                stmt.setString(17, user.getStreetInfo());

                if (user.getGmtModify() == null) {
                    stmt.setTimestamp(18, null);
                } else {
                    stmt.setTimestamp(18, new Timestamp(user.getGmtModify().getTime()));
                }

                // 条件
                stmt.setString(19, user.getNo());
            }
        });
    }

    /**
     * 更新登录密码
     */
    public int updatePasswd(final String no, final String passwd, final Date gmtModify) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName).append(" SET ");
        sql.append(this.passwdFieldName).append("=?");
        sql.append(",").append(this.gmtModifyFieldName).append("=?");
        sql.append(" WHERE ").append(this.noFieldName).append("=?");

        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, passwd);

                if (gmtModify == null) {
                    stmt.setTimestamp(2, null);
                } else {
                    stmt.setTimestamp(2, new Timestamp(gmtModify.getTime()));
                }

                stmt.setString(3, no);
            }
        });
    }

    /**
     * 更新登录密码错误次数
     */
    public int updatePasswdErrCount(final String no, final int passwdErrCount, final Date gmtModify) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName).append(" SET ");
        sql.append(this.passwdErrCountFieldName).append("=?");
        sql.append(",").append(this.gmtModifyFieldName).append("=?");
        sql.append(" WHERE ").append(this.noFieldName).append("=?");

        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setInt(1, passwdErrCount);

                if (gmtModify == null) {
                    stmt.setTimestamp(2, null);
                } else {
                    stmt.setTimestamp(2, new Timestamp(gmtModify.getTime()));
                }

                stmt.setString(3, no);
            }
        });
    }

    /**
     * 根据编号查询单个用户模型
     */
    public UserDTO findUnique(final String unique) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.noFieldName).append("=?");
        sql.append(" OR ").append(this.nickNameFieldName).append("=?");
        sql.append(" OR ").append(this.mobileFieldName).append("=?");
        sql.append(" OR ").append(this.emailFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, unique);
                stmt.setString(2, unique);
                stmt.setString(3, unique);
                stmt.setString(4, unique);
            }
        });
    }

    /**
     * 根据编号查询单个用户模型
     */
    public UserDTO findByNo(final String no) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.noFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, no);
            }
        });
    }

    /**
     * 根据昵称查询单个用户模型
     */
    public UserDTO findByNickName(final String nickName) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.nickNameFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, nickName);
            }
        });
    }

    /**
     * 根据手机查询单个用户模型
     */
    public UserDTO findByMobile(final String mobile) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.mobileFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, mobile);
            }
        });
    }

    /**
     * 根据电子邮箱查询单个用户模型
     */
    public UserDTO findByEmail(final String email) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(this.findTableFields());
        sql.append(" FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.emailFieldName).append("=?");

        // 执行查询
        return JdbcSelect.selectOne(this.dataSource, sql.toString(), this, new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, email);
            }
        });
    }

    /**
     * 删除单个用户模型
     */
    public int delete(final String no) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.tableName);
        sql.append(" WHERE ");
        sql.append(this.noFieldName).append("=?");

        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, no);
            }
        });
    }

    /** 
     * @see com.github.obullxl.lang.das.JdbcSelect.JdbcRowMap#map(java.sql.ResultSet)
     */
    @SuppressWarnings("unchecked")
    public List<UserDTO> map(ResultSet rs) throws SQLException {
        List<UserDTO> users = new ArrayList<UserDTO>();

        while (rs.next()) {
            UserDTO user = new UserDTO();

            user.setNo(rs.getString(this.noFieldName));
            user.setFlag(rs.getString(this.flagFieldName));
            user.setNickName(rs.getString(this.nickNameFieldName));
            user.setPasswd(rs.getString(this.passwdFieldName));
            user.setPasswdErrCount(rs.getInt(this.passwdErrCountFieldName));
            user.setRegistDate(rs.getString(this.registDateFieldName));
            user.setActiveDate(rs.getString(this.activeDateFieldName));
            user.setAuthDate(rs.getString(this.authDateFieldName));
            user.setMobile(rs.getString(this.mobileFieldName));
            user.setEmail(rs.getString(this.emailFieldName));
            user.setRealName(rs.getString(this.realNameFieldName));
            user.setBirthDate(rs.getString(this.birthDateFieldName));
            user.setAvatarPath(rs.getString(this.avatarPathFieldName));
            user.setPostCode(rs.getString(this.postCodeFieldName));
            user.setProvinceCode(rs.getString(this.provinceCodeFieldName));
            user.setCityCode(rs.getString(this.cityCodeFieldName));
            user.setCountyCode(rs.getString(this.countyCodeFieldName));
            user.setStreetInfo(rs.getString(this.streetInfoFieldName));
            user.setGmtCreate(rs.getTimestamp(this.gmtCreateFieldName));
            user.setGmtModify(rs.getTimestamp(this.gmtModifyFieldName));

            users.add(user);
        }

        return users;
    }

    // ~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~ //

    public String getTableName() {
        return tableName;
    }

    public String getNoFieldName() {
        return noFieldName;
    }

    public String getFlagFieldName() {
        return flagFieldName;
    }

    public String getNickNameFieldName() {
        return nickNameFieldName;
    }

    public String getPasswdFieldName() {
        return passwdFieldName;
    }

    public String getPasswdErrCountFieldName() {
        return passwdErrCountFieldName;
    }

    public String getRegistDateFieldName() {
        return registDateFieldName;
    }

    public String getActiveDateFieldName() {
        return activeDateFieldName;
    }

    public String getAuthDateFieldName() {
        return authDateFieldName;
    }

    public String getMobileFieldName() {
        return mobileFieldName;
    }

    public String getEmailFieldName() {
        return emailFieldName;
    }

    public String getRealNameFieldName() {
        return realNameFieldName;
    }

    public String getBirthDateFieldName() {
        return birthDateFieldName;
    }

    public String getAvatarPathFieldName() {
        return avatarPathFieldName;
    }

    public String getPostCodeFieldName() {
        return postCodeFieldName;
    }

    public String getProvinceCodeFieldName() {
        return provinceCodeFieldName;
    }

    public String getCityCodeFieldName() {
        return cityCodeFieldName;
    }

    public String getCountyCodeFieldName() {
        return countyCodeFieldName;
    }

    public String getStreetInfoFieldName() {
        return streetInfoFieldName;
    }

    public String getGmtCreateFieldName() {
        return gmtCreateFieldName;
    }

    public String getGmtModifyFieldName() {
        return gmtModifyFieldName;
    }

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setNoFieldName(String noFieldName) {
        this.noFieldName = noFieldName;
    }

    public void setFlagFieldName(String flagFieldName) {
        this.flagFieldName = flagFieldName;
    }

    public void setNickNameFieldName(String nickNameFieldName) {
        this.nickNameFieldName = nickNameFieldName;
    }

    public void setPasswdFieldName(String passwdFieldName) {
        this.passwdFieldName = passwdFieldName;
    }

    public void setPasswdErrCountFieldName(String passwdErrCountFieldName) {
        this.passwdErrCountFieldName = passwdErrCountFieldName;
    }

    public void setRegistDateFieldName(String registDateFieldName) {
        this.registDateFieldName = registDateFieldName;
    }

    public void setActiveDateFieldName(String activeDateFieldName) {
        this.activeDateFieldName = activeDateFieldName;
    }

    public void setAuthDateFieldName(String authDateFieldName) {
        this.authDateFieldName = authDateFieldName;
    }

    public void setMobileFieldName(String mobileFieldName) {
        this.mobileFieldName = mobileFieldName;
    }

    public void setEmailFieldName(String emailFieldName) {
        this.emailFieldName = emailFieldName;
    }

    public void setRealNameFieldName(String realNameFieldName) {
        this.realNameFieldName = realNameFieldName;
    }

    public void setBirthDateFieldName(String birthDateFieldName) {
        this.birthDateFieldName = birthDateFieldName;
    }

    public void setAvatarPathFieldName(String avatarPathFieldName) {
        this.avatarPathFieldName = avatarPathFieldName;
    }

    public void setPostCodeFieldName(String postCodeFieldName) {
        this.postCodeFieldName = postCodeFieldName;
    }

    public void setProvinceCodeFieldName(String provinceCodeFieldName) {
        this.provinceCodeFieldName = provinceCodeFieldName;
    }

    public void setCityCodeFieldName(String cityCodeFieldName) {
        this.cityCodeFieldName = cityCodeFieldName;
    }

    public void setCountyCodeFieldName(String countyCodeFieldName) {
        this.countyCodeFieldName = countyCodeFieldName;
    }

    public void setStreetInfoFieldName(String streetInfoFieldName) {
        this.streetInfoFieldName = streetInfoFieldName;
    }

}
