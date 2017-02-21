-- ----------------------------
-- Table structure for `product`
-- ----------------------------
-- DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT '自增长ID',  
    `company_id` bigint(20) NOT NULL   COMMENT '公司ID',  
    `name` varchar(20)    COMMENT '产品名称',  
    `slogan` varchar(100)    COMMENT '产品标语',  
    `logo` varchar(100)    COMMENT '产品LOGO',  
    `summary` varchar(255)    COMMENT '产品介绍',  
    `create_by` bigint(20) NOT NULL   COMMENT '创建人ID',  
    `update_by` bigint(20) NOT NULL   COMMENT '更新人ID',  
    `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',  
    `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',  
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;





