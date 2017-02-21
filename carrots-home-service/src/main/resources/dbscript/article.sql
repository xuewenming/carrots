-- ----------------------------
-- Table structure for `article`
-- ----------------------------
-- DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT 'id',  
  `type` int(11) DEFAULT NULL   COMMENT '类型',   
  `img` varchar(255) NOT NULL   COMMENT '封面',    
  `title` text NOT NULL   COMMENT '标题',
  `orderBy` int(11) DEFAULT NULL   COMMENT '顺序',
  `author` varchar(50) DEFAULT NULL   COMMENT '作者',
  `source` varchar(100) DEFAULT NULL   COMMENT '来源', 
  `url` varchar(1000) DEFAULT NULL   COMMENT '链接',
  `content` text   COMMENT '内容', 
  `summary` text   COMMENT '摘要',
  `create_by` bigint(20) DEFAULT NULL   COMMENT '创建人ID', 
  `update_by` bigint(20) DEFAULT NULL   COMMENT '更新人ID',
  `publish_at` bigint(20) DEFAULT NULL   COMMENT '发布时间',
  `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',
  `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',
  `status` bigint(20) NOT NULL   COMMENT '是否上线',
  `industry` bigint(20) NOT NULL   COMMENT '行业编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;





