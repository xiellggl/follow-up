USE dayi_follow;

ALTER TABLE `follow_agent`
ADD COLUMN `high_sea_flag` tinyint(2) NOT NULL COMMENT '是否公海用户(0：不是,1：是)' AFTER `his_max_fund`;
ADD COLUMN `ware_house_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间' AFTER `high_sea_flag`;

#将羊毛党的客户作为公海基础
UPDATE follow_agent set high_sea_flag = 1,follow_id = null  where follow_id =X

#建立配置表
CREATE TABLE `config`  (
  `id` varbinary(18) NOT NULL,
  `mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;