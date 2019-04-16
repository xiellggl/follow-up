USE dayi_follow;

ALTER TABLE `follow_agent`
ADD COLUMN `high_sea_flag` tinyint(2) NOT NULL COMMENT '是否公海用户(0：不是,1：是)' AFTER `his_max_fund`,
ADD COLUMN `warehouse_date` timestamp(0) NULL DEFAULT NULL COMMENT '入库时间' AFTER `high_sea_flag`;

#将羊毛党的客户作为公海基础(这条看情况执行)
#UPDATE follow_agent set high_sea_flag = 1,follow_id = null  where follow_id =X;

#建立配置表
CREATE TABLE `config`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mark` varchar(50) NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

#初始化配置表
INSERT INTO `config`(`id`, `mark`, `value`, `create_time`, `update_time`) VALUES ('244218ca000001a1', 'HS_RANGE', null, '2019-04-11 15:24:58', '2019-04-11 15:24:58');
INSERT INTO `config`(`id`, `mark`, `value`, `create_time`, `update_time`) VALUES ('244218ca000002a1', 'PS_NUM', null, '2019-04-11 15:24:58', '2019-04-11 15:24:58');

#添加公海权限

