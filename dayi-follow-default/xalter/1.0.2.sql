USE dayi_follow;

#日志表
ALTER TABLE `follow_up_log`
CHANGE COLUMN `manage_fund` `maker_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '创客管理资产规模' AFTER `out_cash`,
CHANGE COLUMN `manage_growth_fund` `maker_growth_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '创客管理资产规模净增' AFTER `maker_fund`,
ADD COLUMN `manage_fund` decimal(15, 2) NULL COMMENT '管理资产规模' AFTER `maker_growth_fund`,
ADD COLUMN `manage_growth_fund` decimal(15, 2) NULL COMMENT '管理资产规模净增' AFTER `fund`;

#跟进人表
ALTER TABLE `follow_up`
ADD COLUMN `his_max_fund` decimal(15, 2) NULL COMMENT '历史最高资产规模' AFTER `identity`;

#来源日报统计
CREATE TABLE `source_report`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` smallint(6) NULL DEFAULT NULL COMMENT '类型：1-资管中心，2-创客，3-其他',
  `in_cash` decimal(15, 2) NULL DEFAULT NULL COMMENT '网银转入总额',
  `out_cash` decimal(15, 2) NULL DEFAULT NULL COMMENT '转出到卡总额',
  `growth_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '净增总额',
  `manage_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '管理资产总额',
  `manage_growth_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '管理资产净增总额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

#日志表
ALTER TABLE `follow_up_log`
ADD COLUMN `sign_org_num` int(11) NULL COMMENT '新签创客人数' AFTER `out_cash_num`;