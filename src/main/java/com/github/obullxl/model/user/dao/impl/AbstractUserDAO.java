/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.model.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.obullxl.lang.das.AbstractDAO;
import com.github.obullxl.lang.das.JdbcInsert;
import com.github.obullxl.lang.das.JdbcSelect;
import com.github.obullxl.lang.das.JdbcStmtValue;
import com.github.obullxl.lang.das.JdbcUpdate;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.model.user.UserModel;
import com.github.obullxl.model.user.dao.UserDAO;
import com.github.obullxl.model.user.enums.ActiveStateEnum;
import com.github.obullxl.model.user.enums.UserSexEnum;

/**
 * 用户模型DAO实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserDAO.java, V1.0.1 2014年1月28日 上午9:57:57 $
 */
public abstract class AbstractUserDAO extends AbstractDAO implements UserDAO {

    /** 编号 */
    protected String noFieldName             = "no";

    /** 昵称/显示名称 */
    protected String nickNameFieldName       = "nick_name";

    /** 状态 */
    protected String stateFieldName          = "state";

    /** 管理员后台 */
    protected String mngtFieldName           = "mngt";

    /** 性别 */
    protected String sexFieldName            = "sex";

    /** 允许登录状态 */
    protected String loginStateFieldName     = "login_state";

    /** 邮箱激活状态 */
    protected String emailStateFieldName     = "email_state";

    /** 手机激活状态 */
    protected String mobileStateFieldName    = "mobile_state";

    /** 登录密码 */
    protected String passwdFieldName         = "passwd";

    /** 登录密码错误输入次数 */
    protected String passwdErrCountFieldName = "passwd_err_count";

    /** 注册日期（格式：yyyy-MM-dd） */
    protected String registDateFieldName     = "regist_date";

    /** 激活日期（格式：yyyy-MM-dd） */
    protected String activeDateFieldName     = "active_date";

    /** 认证日期（格式：yyyy-MM-dd） */
    protected String authDateFieldName       = "auth_date";

    /** 手机号码 */
    protected String mobileFieldName         = "mobile";

    /** 电子邮箱 */
    protected String emailFieldName          = "email";

    /** 真实姓名 */
    protected String realNameFieldName       = "real_name";

    /** 出生日期 */
    protected String birthDateFieldName      = "birth_date";

    /** 头像地址 */
    protected String avatarPathFieldName     = "avatar_path";

    /** 现居地邮政编码 */
    protected String postCodeFieldName       = "post_code";

    /** 现居地省份代码 */
    protected String provinceCodeFieldName   = "province_code";

    /** 现居地市代码 */
    protected String cityCodeFieldName       = "city_code";

    /** 现居地县代码 */
    protected String countyCodeFieldName     = "county_code";

    /** 现居地街道信息 */
    protected String streetInfoFieldName     = "street_info";

    /** 插入SQL */
    private String   insertSQL;

    /** 更新SQL */
    private String   updateSQL;

    /** 查询SQL */
    private String   tableFields;

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findTableFields()
     */
    public String findTableFields() {
        if (this.tableFields == null) {
            StringBuilder sql = new StringBuilder();

            sql.append(this.noFieldName);
            sql.append(",").append(this.nickNameFieldName);
            sql.append(",").append(this.stateFieldName);
            sql.append(",").append(this.mngtFieldName);
            sql.append(",").append(this.sexFieldName);
            sql.append(",").append(this.loginStateFieldName);
            sql.append(",").append(this.emailStateFieldName);
            sql.append(",").append(this.mobileStateFieldName);
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
     * @see com.github.obullxl.lang.das.AbstractDAO#findInsertSQL()
     */
    public String findInsertSQL() {
        if (this.insertSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(this.tableName).append("(");
            sql.append(this.findTableFields());
            sql.append(") VALUES(");
            sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            sql.append(")");

            this.insertSQL = sql.toString();
            logger.warn("[用户模型]-InsertSQL: {}", this.insertSQL);
        }

        return this.insertSQL;
    }

    /** 
     * @see com.github.obullxl.lang.das.AbstractDAO#findUpdateSQL()
     */
    public String findUpdateSQL() {
        if (this.updateSQL == null) {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(this.tableName);
            sql.append(" SET ");
            sql.append(this.noFieldName).append("=?");
            sql.append(",").append(this.nickNameFieldName).append("=?");
            sql.append(",").append(this.stateFieldName).append("=?");
            sql.append(",").append(this.mngtFieldName).append("=?");
            sql.append(",").append(this.sexFieldName).append("=?");
            sql.append(",").append(this.loginStateFieldName).append("=?");
            sql.append(",").append(this.emailStateFieldName).append("=?");
            sql.append(",").append(this.mobileStateFieldName).append("=?");
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

        return this.updateSQL;
    }

    /**
     * 填充字段值
     */
    private int fillStatmentValue(PreparedStatement stmt, UserModel user) throws SQLException {
        int idx = 0;

        stmt.setString(++idx, user.getNo());
        stmt.setString(++idx, user.getNickName());
        super.fillStmtEnumBaseValue(++idx, stmt, user.getStateEnum());
        super.fillStmtEnumBaseValue(++idx, stmt, user.getMngtEnum());
        super.fillStmtEnumBaseValue(++idx, stmt, user.getSexEnum());
        super.fillStmtEnumBaseValue(++idx, stmt, user.getLoginEnum());
        super.fillStmtEnumBaseValue(++idx, stmt, user.getEmailEnum());
        super.fillStmtEnumBaseValue(++idx, stmt, user.getMobileEnum());
        stmt.setString(++idx, user.getPasswd());
        stmt.setInt(++idx, user.getPasswdErrCount());
        stmt.setString(++idx, user.getRegistDate());
        stmt.setString(++idx, user.getActiveDate());
        stmt.setString(++idx, user.getAuthDate());
        stmt.setString(++idx, user.getMobile());
        stmt.setString(++idx, user.getEmail());
        stmt.setString(++idx, user.getRealName());
        stmt.setString(++idx, user.getBirthDate());
        stmt.setString(++idx, user.getAvatarPath());
        stmt.setString(++idx, user.getPostCode());
        stmt.setString(++idx, user.getProvinceCode());
        stmt.setString(++idx, user.getCityCode());
        stmt.setString(++idx, user.getCountyCode());
        stmt.setString(++idx, user.getStreetInfo());

        return idx;
    }

    /** 
     * @see com.github.obullxl.model.user.dao.UserDAO#insert(com.github.obullxl.model.user.UserModel)
     */
    public void insert(final UserModel user) {
        JdbcInsert.execute(this.dataSource, this.findInsertSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = fillStatmentValue(stmt, user);
                fillStmtTimestampValue(++idx, stmt, user.getGmtCreate());
                fillStmtTimestampValue(++idx, stmt, user.getGmtModify());
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.user.dao.UserDAO#update(com.github.obullxl.model.user.UserModel)
     */
    public int update(final UserModel user) {
        return JdbcUpdate.executeUpdate(this.dataSource, this.findUpdateSQL(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = fillStatmentValue(stmt, user);
                fillStmtTimestampValue(++idx, stmt, user.getGmtModify());

                // 条件
                stmt.setString(++idx, user.getNo());
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.user.dao.UserDAO#updatePasswd(java.lang.String, java.lang.String)
     */
    public int updatePasswd(final String no, final String passwd) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName).append(" SET ");
        sql.append(this.passwdFieldName).append("=?");
        sql.append(",").append(this.gmtModifyFieldName).append("=?");
        sql.append(" WHERE ").append(this.noFieldName).append("=?");

        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setString(++idx, passwd);
                fillStmtTimestampValue(++idx, stmt, new Date());
                stmt.setString(++idx, no);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.user.dao.UserDAO#updatePasswdErrCount(java.lang.String, int)
     */
    public int updatePasswdErrCount(final String no, final int passwdErrCount) {
        // SQL
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.tableName).append(" SET ");
        sql.append(this.passwdErrCountFieldName).append("=?");
        sql.append(",").append(this.gmtModifyFieldName).append("=?");
        sql.append(" WHERE ").append(this.noFieldName).append("=?");

        // 执行
        return JdbcUpdate.executeUpdate(this.dataSource, sql.toString(), new JdbcStmtValue() {
            public void set(PreparedStatement stmt) throws SQLException {
                int idx = 0;
                stmt.setInt(++idx, passwdErrCount);
                fillStmtTimestampValue(++idx, stmt, new Date());
                stmt.setString(++idx, no);
            }
        });
    }

    /** 
     * @see com.github.obullxl.model.user.dao.UserDAO#findByNo(java.lang.String)
     */
    public UserModel findByNo(final String no) {
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
     * @see com.github.obullxl.model.user.dao.UserDAO#findByUnique(java.lang.String)
     */
    public UserModel findByUnique(final String unique) {
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
     * @see com.github.obullxl.model.user.dao.UserDAO#findByNickName(java.lang.String)
     */
    public UserModel findByNickName(final String nickName) {
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
     * @see com.github.obullxl.model.user.dao.UserDAO#findByEmail(java.lang.String)
     */
    public UserModel findByEmail(final String email) {
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
     * @see com.github.obullxl.model.user.dao.UserDAO#findByMobile(java.lang.String)
     */
    public UserModel findByMobile(final String mobile) {
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
     * @see com.github.obullxl.model.user.dao.UserDAO#deleteByNo(java.lang.String)
     */
    public int deleteByNo(final String no) {
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
    public List<UserModel> map(ResultSet rs) throws SQLException {
        List<UserModel> users = new ArrayList<UserModel>();

        while (rs.next()) {
            UserModel user = new UserModel();

            user.setNo(rs.getString(this.noFieldName));
            user.setNickName(rs.getString(this.nickNameFieldName));
            user.setStateEnum(ActiveStateEnum.findDefault(rs.getString(this.stateFieldName)));
            user.setMngtEnum(ValveBoolEnum.findDefault(rs.getString(this.mngtFieldName)));
            user.setSexEnum(UserSexEnum.findDefault(rs.getString(this.sexFieldName)));
            user.setLoginEnum(ValveBoolEnum.findDefault(rs.getString(this.loginStateFieldName)));
            user.setEmailEnum(ValveBoolEnum.findDefault(rs.getString(this.emailStateFieldName)));
            user.setMobileEnum(ValveBoolEnum.findDefault(rs.getString(this.mobileStateFieldName)));
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

    // ~~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public void setNoFieldName(String noFieldName) {
        this.noFieldName = noFieldName;
    }

    public void setNickNameFieldName(String nickNameFieldName) {
        this.nickNameFieldName = nickNameFieldName;
    }

    public void setStateFieldName(String stateFieldName) {
        this.stateFieldName = stateFieldName;
    }

    public void setMngtFieldName(String mngtFieldName) {
        this.mngtFieldName = mngtFieldName;
    }

    public void setSexFieldName(String sexFieldName) {
        this.sexFieldName = sexFieldName;
    }

    public void setLoginStateFieldName(String loginStateFieldName) {
        this.loginStateFieldName = loginStateFieldName;
    }

    public void setEmailStateFieldName(String emailStateFieldName) {
        this.emailStateFieldName = emailStateFieldName;
    }

    public void setMobileStateFieldName(String mobileStateFieldName) {
        this.mobileStateFieldName = mobileStateFieldName;
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
