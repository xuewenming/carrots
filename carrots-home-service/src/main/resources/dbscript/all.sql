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




-- ----------------------------
-- Table structure for `company_industry`
-- ----------------------------
-- DROP TABLE IF EXISTS `company_industry`;
CREATE TABLE `company_industry` (
    `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT '自增长ID',  
    `company_id` bigint(20) NOT NULL   COMMENT '公司ID',  
    `industry` bigint(20) NOT NULL   COMMENT '公司行业',  
    `create_by` bigint(20) NOT NULL   COMMENT '创建人ID',  
    `update_by` bigint(20) NOT NULL   COMMENT '更新人ID',  
    `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',  
    `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',  
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Table structure for `company_tags`
-- ----------------------------
-- DROP TABLE IF EXISTS `company_tags`;
CREATE TABLE `company_tags` (
    `id` bigint(20) NOT NULL   AUTO_INCREMENT  COMMENT '自增长ID',  
    `company_id` bigint(20) NOT NULL   COMMENT '公司ID',  
    `tag` varchar(20) NOT NULL   COMMENT '公司标签',  
    `create_by` bigint(20) NOT NULL   COMMENT '创建人ID',  
    `update_by` bigint(20) NOT NULL   COMMENT '更新人ID',  
    `update_at` bigint(20) NOT NULL   COMMENT '数据更新时间',  
    `create_at` bigint(20) NOT NULL   COMMENT '数据创建时间',  
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;




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





