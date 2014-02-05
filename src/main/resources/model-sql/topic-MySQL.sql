/**
 * 主题模型数据表
 */
-- DROP TABLE IF EXISTS atom_topic;
CREATE TABLE atom_topic (
  id		VARCHAR(32) 	COMMENT 'ID',
  model		VARCHAR(10) 	COMMENT '类型',
  state		VARCHAR(10) 	COMMENT '状态',
  top		VARCHAR(10) 	COMMENT '置顶标志',
  elite		VARCHAR(10) 	COMMENT '加精标志',
  original	VARCHAR(10) 	COMMENT '原创标志',
  media		VARCHAR(10) 	COMMENT '多媒体标志',
  reply		VARCHAR(10) 	COMMENT '允许评论标志',
  catg		VARCHAR(64) 	COMMENT '分类代码',
  sort		VARCHAR(10) 	COMMENT '排序',
  topic		VARCHAR(64) 	COMMENT '主题ID',
  link_url		VARCHAR(255) 	COMMENT '引用URL',
  media_url		VARCHAR(255) 	COMMENT '多媒体URL',
  post_user_no		VARCHAR(64) COMMENT '发布者编号',
  post_nick_name	VARCHAR(32) COMMENT '发布者昵称',
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
  KEY atom_topic_model_idx(model),
  KEY atom_topic_topic_idx(topic),
  KEY atom_topic_puno_idx(post_user_no),
  KEY atom_topic_runo_idx(reply_user_no)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '主题模型数据表';