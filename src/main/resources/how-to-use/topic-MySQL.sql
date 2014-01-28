/**
 * 主题模型数据表
 */
-- DROP TABLE IF EXISTS atom_topic;
CREATE TABLE atom_topic (
  id		VARCHAR(32) 	COMMENT 'ID',
  flag		VARCHAR(64) 	COMMENT '位状态',
  catg		VARCHAR(64) 	COMMENT '分类代码',
  type		VARCHAR(10) 	COMMENT '类型',
  topic		VARCHAR(64) 	COMMENT '主题ID',
  post_user_no		VARCHAR(64) COMMENT '发布者编号',
  post_nick_name	VARCHAR(32) COMMENT '发布者昵称',
  link_url		VARCHAR(255) 	COMMENT '引用URL',
  media_url		VARCHAR(255) 	COMMENT '多媒体URL',
  gmt_post		DATETIME 		COMMENT '发布时间',
  visit_count 	INT DEFAULT '0'	COMMENT '浏览次数',
  reply_count 	INT DEFAULT '0' COMMENT '跟帖次数',
  reply_user_no		VARCHAR(64) COMMENT '跟帖者编号',
  reply_nick_name	VARCHAR(32) COMMENT '跟帖者昵称',
  gmt_reply		DATETIME 		COMMENT '跟帖时间',
  ext_map		VARCHAR(1024) 	COMMENT '扩展参数',
  title_style	VARCHAR(255) 	COMMENT '标题样式',
  title			VARCHAR(255) 	COMMENT '标题',
  summary		VARCHAR(255) 	COMMENT '摘要信息',
  content		TEXT 			COMMENT '内容',
  gmt_create 	DATETIME   COMMENT '创建时间',
  gmt_modify 	DATETIME   COMMENT '修改时间',
  PRIMARY KEY (id),
  KEY atom_topic_catg_idx(catg),
  KEY atom_topic_type_idx(type),
  KEY atom_topic_topic_idx(topic),
  KEY atom_topic_puno_idx(post_user_no),
  KEY atom_topic_runo_idx(reply_user_no)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '主题模型数据表';
