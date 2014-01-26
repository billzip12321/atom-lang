/**
 * 模块分类数据表
 */
-- DROP TABLE IF EXISTS atom_catg;
CREATE TABLE atom_catg (
  code 		VARCHAR(64) COMMENT '分类代码',
  catg		VARCHAR(64)	COMMENT '上级分类代码',
  sort		VARCHAR(10)	COMMENT '分类排序',
  title 	VARCHAR(256) COMMENT '分类说明',
  ext_map 	VARCHAR(1024) COMMENT '分类扩展参数',
  summary 	VARCHAR(1024) COMMENT '分类摘要说明',
  gmt_create DATETIME 	COMMENT '记录创建时间',
  gmt_modify DATETIME 	COMMENT '记录修改时间',
  PRIMARY KEY (code)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '模块分类信息数据表';
