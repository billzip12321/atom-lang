/**
 * 用户模型数据表
 */
-- DROP TABLE IF EXISTS atom_user;
CREATE TABLE atom_user (
  no 		VARCHAR(64) 	COMMENT '编号',
  flag 		VARCHAR(64) 	COMMENT '位状态',
  nick_name VARCHAR(32) 	COMMENT '昵称',
  passwd 	VARCHAR(64) 	COMMENT '密码',
  passwd_err_count	INT DEFAULT '0'	COMMENT '密码错误次数',
  regist_date	VARCHAR(10)		COMMENT '注册日期',
  active_date 	VARCHAR(10) 	COMMENT '激活日期',
  auth_date 	VARCHAR(10) 	COMMENT '认证日期',
  mobile		VARCHAR(16)		COMMENT '移动电话',
  email			VARCHAR(64)		COMMENT '电子邮箱',
  real_name 	VARCHAR(64) 	COMMENT '真实姓名',
  birth_date 	VARCHAR(10) 	COMMENT '出生日期',
  avatar_path	VARCHAR(128)	COMMENT '头像地址',
  post_code 	VARCHAR(10) 	COMMENT '现居地邮政编码',
  province_code VARCHAR(10) 	COMMENT '现居地省代码',
  city_code 	VARCHAR(10) 	COMMENT '现居地市代码',
  county_code	VARCHAR(10)		COMMENT '现居地县代码',
  street_info	VARCHAR(128)	COMMENT '现居地街道信息',
  gmt_create DATETIME 	COMMENT '创建时间',
  gmt_modify DATETIME 	COMMENT '修改时间',
  PRIMARY KEY (no),
  UNIQUE KEY atom_user_nick_u (nick_name),
  UNIQUE KEY atom_user_mobile_u (mobile),
  UNIQUE KEY atom_user_email_u (email)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '用户模型数据表';

INSERT INTO atom_user(no, nick_name, passwd, mobile, email, gmt_create, gmt_modify) VALUES('0000000001', "admin", "21218cca77804d2ba1922c33e0151105", "13816241202", "obullxl@163.com", NOW(), NOW());
INSERT INTO atom_user(no, nick_name, passwd, mobile, email, gmt_create, gmt_modify) VALUES('0000000002', "老牛啊",  "21218cca77804d2ba1922c33e0151105", "15601656663", "obullxl@gmail.com", NOW(), NOW());
