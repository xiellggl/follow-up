

#建库
CREATE DATABASE `dayi_follow_all` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

USE dayi_follow_all;


#创建代理商联系表
DROP TABLE IF EXISTS agent_contact;
CREATE TABLE `dayi_follow_all`.`agent_contact`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `agent_id` int(12) NOT NULL COMMENT '代理商ID',
  `contact_type` tinyint(2) NULL DEFAULT NULL COMMENT '联系方式(1：电话,2：微信,3：QQ,4：邮件,5：短信)',
  `customer_type` tinyint(2) NULL DEFAULT NULL COMMENT '客户类型(1：未联系,2：已开户,3：可被跟进,4：无意向,5：流失,6：无效)',
  `cus_intention_type` tinyint(2) NULL DEFAULT NULL COMMENT '客户意向度(1：强,2：中,3：弱,4：无)',
  `next_contact_time` datetime(0) NULL DEFAULT NULL COMMENT '下次联系时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系内容',
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IX_create_time`(`create_time`) USING BTREE,
  INDEX `IX_agent_id`(`agent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代理商联系表' ROW_FORMAT = Compact;


#创建部门表
DROP TABLE IF EXISTS department;
CREATE TABLE `dayi_follow_all`.`department`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `pid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级部门ID',
  `sort_no` int(5) NULL DEFAULT NULL COMMENT '同级排序号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `city_server` int(11) NULL DEFAULT 0 COMMENT '是否城市服务商0：否，1:是',
  `city_invite_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市服务商邀请码',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `person_num` int(11) NULL DEFAULT 0 COMMENT '部门人数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '跟进人部门表' ROW_FORMAT = Compact;


#创建跟进人代理商联系表
DROP TABLE IF EXISTS follow_agent;
CREATE TABLE `dayi_follow_all`.`follow_agent`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人id',
  `agent_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代理商ID',
  `follow_up_before` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '之前跟进人',
  `assign_date_before` timestamp(0) NULL DEFAULT NULL COMMENT '上次分配跟进人时间',
  `customer_type` tinyint(2) NULL DEFAULT 1 COMMENT '客户类型(1：未联系,2：已开户,3：可被跟进,4：无意向,5：流失,6：无效)',
  `cus_intention_type` tinyint(2) NULL DEFAULT NULL COMMENT '客户意向度(1：强,2：中,3：弱,4：无)',
  `assign_date` timestamp(0) NULL DEFAULT NULL COMMENT '分配跟进人时间',
  `agent_fund_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '变更跟进人前代理资金',
  `total_fund_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '变更跟进人前总资产',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IX_follow_id`(`follow_id`) USING BTREE,
  INDEX `IX_agent_id`(`agent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

#创建跟进人机构商联系表
DROP TABLE IF EXISTS follow_org;
CREATE TABLE `dayi_follow_all`.`follow_org`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人id',
  `org_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构商id',
  `follow_up_before` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '之前跟进人',
  `assign_date` datetime(0) NULL DEFAULT NULL COMMENT '分配跟进人日期',
  `assign_date_before` datetime(0) NULL DEFAULT NULL COMMENT '跟进人变更时间',
  `manage_fund_before` decimal(15, 2) NULL DEFAULT NULL COMMENT '变更跟进人前管理资产',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

#创建跟进人表
DROP TABLE IF EXISTS follow_up;
CREATE TABLE `dayi_follow_all`.`follow_up`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `invite_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `disable` int(11) NULL DEFAULT 1 COMMENT '1:启用0禁用',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `switch_status` tinyint(2) NULL DEFAULT 1 COMMENT '二级资管状态(1:开启，-1:关闭)',
  `channel_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道码备注',
  `roleids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '角色ids',
  `identity` smallint(6) NULL DEFAULT NULL COMMENT '身份标识：1-跟进人，2-非跟进人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

#创建统计每日业绩表
DROP TABLE IF EXISTS follow_up_log;
CREATE TABLE `dayi_follow_all`.`follow_up_log`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人id',
  `dept_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在团队',
  `open_account_num` int(11) NULL DEFAULT NULL COMMENT '新开户数',
  `in_cash` decimal(15, 2) NULL DEFAULT NULL COMMENT '网银转入总额',
  `in_cash_num` int(11) NULL DEFAULT NULL COMMENT '入金人数',
  `out_cash` decimal(15, 2) NULL DEFAULT NULL COMMENT '转出到卡总额',
  `manage_growth_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '管理资产规模净增',
  `manage_fund` decimal(15, 2) NULL DEFAULT NULL COMMENT '管理资产规模',
  `out_cash_num` int(11) NULL DEFAULT NULL COMMENT '出金人数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IX_follow_id`(`follow_id`) USING BTREE,
  INDEX `IX_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

#创建创客联系表
DROP TABLE IF EXISTS org_contact;
CREATE TABLE `dayi_follow_all`.`org_contact`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_id` int(12) NOT NULL COMMENT '创客ID',
  `contact_type` tinyint(2) NULL DEFAULT NULL COMMENT '联系方式(1：电话,2：微信,3：QQ,4：邮件,5：短信)',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系内容',
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '创客联系表' ROW_FORMAT = Compact;

#复制agent_contact数据到新库agent_contact中
INSERT into  dayi_follow_all.agent_contact
(id,agent_id,contact_type,customer_type,cus_intention_type,next_contact_time,follow_id,content,create_time,update_time)
SELECT id,agent_id,contact_type,customer_type,customer_intention_type,next_contact_time,flow_id,content,create_date,modify_date
from dev_dayi_spot.agent_contact;

#复制follow_dept表到department表中
insert into dayi_follow_all.department(id,pid,sort_no,name,city_server,city_invite_code,remark,person_num,create_time,update_time)
SELECT id,pid,sort_no,name,city_server,city_invite_code,remark,person_num,create_date,modify_date from dev_dayi_spot.follow_dept;


#将agent表的跟进人数据，迁移到follow_agent表
INSERT into  dayi_follow_all.follow_agent (id,follow_id,agent_id,follow_up_before,assign_date_before,
customer_type,cus_intention_type,assign_date,agent_fund_before,total_fund_before,create_time,update_time)
SELECT CONCAT(id,'a',IFNULL(flow_id,0)),flow_id,id,follow_up_before,flow_date_before,
customer_type,customer_intention_type,flow_date,asserts_before,balance_before,create_date,
modify_date from dev_dayi_spot.agent;

#将organization表的跟进人数据，迁移到follow_org表
INSERT into  dayi_follow_all.follow_org (id,follow_id,org_id,follow_up_before,assign_date,
assign_date_before,manage_fund_before,create_time,update_time)
SELECT CONCAT(id,'a',IFNULL(flow_id,0)),flow_id,id,follow_up_before,flow_date,
flow_date_before,asserts_before,create_date,
modify_date from dev_dayi_spot.organization;

#复制flow_up到follow_up表
INSERT into  dayi_follow_all.follow_up (id,dept_id,name,mobile,invite_code,
user_name,disable,`password`,create_by,update_by,create_time,update_time,switch_status,channel_remark,identity)
SELECT id,dept_id,link_person,mobile,invite_code,
user_name,`disable`,`password`,create_by,modify_by,create_date,modify_date,switch_status,channel_remark,1
from dev_dayi_spot.flow_up;

#修补超管和管理员的部门id
update dayi_follow_all.follow_up set dept_id = 2 WHERE user_name='admin' or user_name='chenyh1';

#修补超管和管理员的身份
update dayi_follow_all.follow_up set identity = 2 WHERE user_name='admin' or user_name='chenyh1';

#复制follow_up_log数据到新库中
INSERT into dayi_follow_all.follow_up_log (id,follow_id,dept_id,open_account_num,in_cash,in_cash_num,out_cash,manage_growth_fund,manage_fund,out_cash_num,create_time,update_time )
SELECT id,flow_id,dept_id,open_account_num,in_cash,in_cash_num,out_cash,manage_asset_growth,manage_asset,out_cash_num,create_date,modify_date from dev_dayi_spot.follow_up_log;

#复制organization_contact数据到org_contact中
INSERT into dayi_follow_all.org_contact (id,org_id,contact_type,content,follow_id,create_time,update_time )
SELECT id,org_id,contact_type,content,flow_id,create_date,modify_date from dev_dayi_spot.organization_contact;

