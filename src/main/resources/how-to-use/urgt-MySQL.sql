/**
 * 用户权限数据表
 */
-- DROP TABLE IF EXISTS atom_user_right;
CREATE TABLE atom_user_right (
  user_no 	VARCHAR(64) 	COMMENT '用户编号',
  nick_name VARCHAR(32) 	COMMENT '用户昵称',
  rgt_code 	VARCHAR(128) 	COMMENT '权限代码',
  rgt_name	VARCHAR(128)	COMMENT '权限名称',
  ext_map	VARCHAR(512)	COMMENT '扩展参数',
  gmt_create DATETIME 	COMMENT '创建时间',
  gmt_modify DATETIME 	COMMENT '修改时间',
  PRIMARY KEY (user_no, rgt_code)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '用户权限数据表';
