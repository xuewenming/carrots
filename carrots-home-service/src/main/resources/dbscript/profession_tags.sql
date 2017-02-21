-- ----------------------------
-- Table structure for `profession_tags`
-- ----------------------------
-- DROP TABLE IF EXISTS `profession_tags`;
CREATE TABLE `profession_tags` (
    `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT '自增长ID',
    `company_id` bigint(20) NOT NULL   COMMENT '公司ID', 
    `profession_id` bigint(20) NOT NULL   COMMENT '职位ID',  
    `tag` varchar(20) NOT NULL   COMMENT '公司标签',  
    `create_by` bigint(20) NOT NULL   COMMENT '创建人ID',  
    `update_by` bigint(20) NOT NULL   COMMENT '更新人ID',  
    `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',  
    `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',  
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;





