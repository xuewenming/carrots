-- ----------------------------
-- Table structure for `profession`
-- ----------------------------
-- DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
    `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT '自增长ID',  
    `company_id` bigint(20) NOT NULL   COMMENT '公司ID',
    `company_name` varchar(100) NOT NULL   COMMENT '公司名称',
    `name` varchar(100) NOT NULL   COMMENT '职位名称',  
    `province` varchar(100) NOT NULL   COMMENT '省编码',  
    `city` varchar(100) NOT NULL   COMMENT '市编码',  
    `county` varchar(100) NOT NULL   COMMENT '县编码',  
    `category` bigint(20) NOT NULL   COMMENT '职业类别',  
    `sub_category` bigint(20) NOT NULL   COMMENT '职业子类',  
    `education` bigint(20) NOT NULL   COMMENT '学历要求',  
    `experience` bigint(20) NOT NULL   COMMENT '工作经验',  
    `recommend` bigint(20) NOT NULL   COMMENT '推荐类型',  
    `compensation` bigint(20) NOT NULL   COMMENT '薪资',  
    `responsibility` varchar(100) NOT NULL   COMMENT '岗位职责',  
    `requisite` varchar(100) NOT NULL   COMMENT '必要条件',  
    `boon` varchar(100) NOT NULL   COMMENT '公司福利',  
    `status` bigint(20) NOT NULL   COMMENT '上架状态',
    `create_by` bigint(20) NOT NULL   COMMENT '创建人ID',  
    `update_by` bigint(20) NOT NULL   COMMENT '更新人ID',  
    `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',  
    `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',  
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;





