/**
 * 消息信箱模型数据表
 */
-- DROP TABLE IF EXISTS atom_msg_box;
CREATE TABLE atom_msg_box (
  id		VARCHAR(32)	COMMENT 'ID',
  catg		VARCHAR(10) COMMENT '分类',
  view_state 	VARCHAR(10)	COMMENT '阅读状态',
  gmt_view		DATETIME 	COMMENT '阅读时间',
  post_user_no 	 VARCHAR(64) COMMENT '发送方编号',
  post_nick_name VARCHAR(32) COMMENT '发送方昵称',
  take_user_no 	 VARCHAR(64) COMMENT '接收方编号',
  take_nick_name VARCHAR(32) COMMENT '接收方昵称',
  task_users	VARCHAR(1024)	COMMENT '接收方列表',
  title			VARCHAR(255)	COMMENT '标题',
  content	TEXT		COMMENT '内容',
  gmt_create DATETIME 	COMMENT '创建时间',
  gmt_modify DATETIME 	COMMENT '修改时间',
  PRIMARY KEY (id),
  KEY atom_msg_box_pun_catg_idx(catg, post_user_no),
  KEY atom_msg_box_tun_catg_idx(catg, take_user_no)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT '消息信箱模型数据表';
