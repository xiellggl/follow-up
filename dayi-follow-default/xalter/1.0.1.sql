USE dayi_follow;
ALTER TABLE `follow_agent`
ADD COLUMN `his_max_fund` decimal(15, 2) NULL COMMENT '历史最高货款' AFTER `total_fund_before`;