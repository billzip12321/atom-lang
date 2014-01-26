/**
 * 系统参数数据表
 */
-- DROP TABLE IF EXIST atom_cfg;
CREATE TABLE atom_cfg (
  catg 	VARCHAR(64)			COMMENT '分类',
  name	VARCHAR(64)			COMMENT '名称',
  title VARCHAR(128)		COMMENT '说明',
  value VARCHAR(512)		COMMENT '值',
  value_ext VARCHAR(1024)	COMMENT '扩展值',
  gmt_create DATETIME		COMMENT '创建时间',
  gmt_modify DATETIME		COMMENT '修改时间',
  PRIMARY KEY(catg, name)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '系统参数数据表';
