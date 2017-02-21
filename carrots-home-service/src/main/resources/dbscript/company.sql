-- ----------------------------
-- Table structure for `company`
-- ----------------------------
-- DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
    `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT '自增长ID',  
    `name` varchar(100) NOT NULL   COMMENT '公司名称',  
    `province` varchar(100) NOT NULL   COMMENT '省编码',  
    `city` varchar(100) NOT NULL   COMMENT '市编码',  
    `county` varchar(100) NOT NULL   COMMENT '县编码',  
    `financing` bigint(20) NOT NULL   COMMENT '融资规模',  
    `approved` bigint(20) NOT NULL   COMMENT '认证状态',  
    `freezed` bigint(20) NOT NULL   COMMENT '冻结状态',  
    `logo` varchar(100) NOT NULL   COMMENT '公司logo',  
    `slogan` varchar(100) NOT NULL   COMMENT '公司标语',  
    `total_num` bigint(20) NOT NULL   COMMENT '公司规模',  
    `summary` varchar(255) NOT NULL   COMMENT '公司介绍',  
    `phone` varchar(100) NULL   COMMENT '手机',  
    `address` varchar(100) NULL   COMMENT '详细地址',  
    `map` varchar(100) NULL   COMMENT '地图',  
    `mail` varchar(100) NULL   COMMENT '邮箱',
    `product` varchar(100) NULL   COMMENT '产品名称',
    `profession_update_at` bigint(20) NULL   COMMENT '职位更新时间',
    `profession_num` bigint(20) NULL   COMMENT '职位名称总数',  
    `create_by` bigint(20) NOT NULL   COMMENT '创建人ID',  
    `update_by` bigint(20) NOT NULL   COMMENT '更新人ID',  
    `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',  
    `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',  
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;





