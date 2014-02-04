/**
 * 关系模型数据表
 */
-- DROP TABLE IF EXISTS atom_relate;
CREATE TABLE atom_relate (
  catg		VARCHAR(64) 	COMMENT '分类',
  src_no 	VARCHAR(64) 	COMMENT '源编号',
  src_name 	VARCHAR(128) 	COMMENT '源名称',
  dst_no 	VARCHAR(64) 	COMMENT '目标编号',
  dst_name	VARCHAR(128)	COMMENT '目标名称',
  ext_map	VARCHAR(1024)	COMMENT '扩展参数',
  gmt_create DATETIME 	COMMENT '创建时间',
  gmt_modify DATETIME 	COMMENT '修改时间',
  PRIMARY KEY (catg, src_no, dst_no)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '关系模型数据表';
