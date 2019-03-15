USE dayi_follow;

ALTER TABLE `follow_up_log`
CHANGE COLUMN `manage_fund` `maker_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '创客管理资产规模' AFTER `out_cash`,
CHANGE COLUMN `manage_growth_fund` `maker_growth_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '创客管理资产规模净增' AFTER `maker_fund`,
ADD COLUMN `fund` decimal(15, 2) NULL COMMENT '管理资产规模' AFTER `maker_growth_fund`,
ADD COLUMN `growth_fund` decimal(15, 2) NULL COMMENT '管理资产规模净增' AFTER `fund`;