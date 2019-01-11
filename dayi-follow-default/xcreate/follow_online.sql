

#建库
CREATE DATABASE `dayi_follow` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

USE dayi_follow;


#创建代理商联系表
DROP TABLE IF EXISTS agent_contact;
CREATE TABLE `dayi_follow`.`agent_contact`  (
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
CREATE TABLE `dayi_follow`.`department`  (
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
CREATE TABLE `dayi_follow`.`follow_agent`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人id',
  `agent_id` int(12) NOT NULL COMMENT '代理商ID',
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
CREATE TABLE `dayi_follow`.`follow_org`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人id',
  `org_id` int(12) NOT NULL COMMENT '创客ID',
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
CREATE TABLE `dayi_follow`.`follow_up`  (
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
CREATE TABLE `dayi_follow`.`follow_up_log`  (
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
CREATE TABLE `dayi_follow`.`org_contact`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_id` int(12) NOT NULL COMMENT '创客ID',
  `contact_type` tinyint(2) NULL DEFAULT NULL COMMENT '联系方式(1：电话,2：微信,3：QQ,4：邮件,5：短信)',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系内容',
  `follow_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跟进人ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '创客联系表' ROW_FORMAT = Compact;

#创建模块表
DROP TABLE IF EXISTS `module`;
CREATE TABLE `dayi_follow`.`module`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `parentid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父id',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块名称',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '跳转地址',
  `status` smallint(6) NOT NULL DEFAULT 1 COMMENT '模块状态[1-激活 0-禁用]',
  `del_status` smallint(6) NOT NULL DEFAULT 0 COMMENT '删除状态（0=正常,1=删除）',
  `sort` int(6) NULL DEFAULT 0 COMMENT '排序',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `create_by` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `update_by` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `css_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模块表' ROW_FORMAT = Compact;

#创建操作日志表
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `dayi_follow`.`operate_log`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `author_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `author_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人ip',
  `action` int(2) NOT NULL COMMENT '动作 操作类型（1=查询，2=新增，3=修改，4=删除）',
  `what` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '做了什么 ',
  `note` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细描述 ',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员操作日志' ROW_FORMAT = Compact;

#创建权限表
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `dayi_follow`.`permission`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限名称',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识/路径',
  `moduleid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块id',
  `display_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单显示名称',
  `display_status` smallint(6) NOT NULL DEFAULT 1 COMMENT '显示状态（0=隐藏，1=显示）',
  `del_status` smallint(6) NOT NULL DEFAULT 0 COMMENT '删除状态（0=正常,1=删除）',
  `status` smallint(6) NOT NULL DEFAULT 1 COMMENT '状态（0=禁用，1=启用）',
  `sort` int(6) NULL DEFAULT 0 COMMENT '排序',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `update_by` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `parentid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

#创建角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `dayi_follow`.`role`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `descript` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色描述',
  `status` smallint(6) NOT NULL DEFAULT 1 COMMENT '状态（0=禁用，1=启用）',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;


#创建角色-权限关系表
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `dayi_follow`.`role_permission`  (
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `roleId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `permissionId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限关系表' ROW_FORMAT = Compact;


#复制agent_contact数据到新库agent_contact中
INSERT into  dayi_follow.agent_contact
(id,agent_id,contact_type,customer_type,cus_intention_type,next_contact_time,follow_id,content,create_time,update_time)
SELECT id,agent_id,contact_type,customer_type,customer_intention_type,next_contact_time,flow_id,content,create_date,modify_date
from dayi_spot.agent_contact;

#复制follow_dept表到department表中
insert into dayi_follow.department(id,pid,sort_no,name,city_server,city_invite_code,remark,person_num,create_time,update_time)
SELECT id,pid,sort_no,name,city_server,city_invite_code,remark,person_num,create_date,modify_date from dayi_spot.follow_dept;


#将agent表的跟进人数据，迁移到follow_agent表
INSERT into  dayi_follow.follow_agent (id,follow_id,agent_id,follow_up_before,assign_date_before,
customer_type,cus_intention_type,assign_date,agent_fund_before,total_fund_before,create_time,update_time)
SELECT CONCAT(id,'a',IFNULL(flow_id,0)),flow_id,id,follow_up_before,flow_date_before,
customer_type,customer_intention_type,flow_date,asserts_before,balance_before,create_date,
modify_date from dayi_spot.agent;

#将organization表的跟进人数据，迁移到follow_org表
INSERT into  dayi_follow.follow_org (id,follow_id,org_id,follow_up_before,assign_date,
assign_date_before,manage_fund_before,create_time,update_time)
SELECT CONCAT(id,'a',IFNULL(flow_id,0)),flow_id,id,follow_up_before,flow_date,
flow_date_before,asserts_before,create_date,
modify_date from dayi_spot.organization;

#复制flow_up到follow_up表
INSERT into  dayi_follow.follow_up (id,dept_id,name,mobile,invite_code,
user_name,disable,`password`,create_by,update_by,create_time,update_time,switch_status,channel_remark,identity)
SELECT id,dept_id,link_person,mobile,invite_code,
user_name,`disable`,`password`,create_by,modify_by,create_date,modify_date,switch_status,channel_remark,1
from dayi_spot.flow_up;

#修补超管和管理员的部门id
update dayi_follow.follow_up set dept_id = 2 WHERE user_name='admin' or user_name='chenyh1' or dept_id is null or dept_id='' ;

#修补超管和管理员的身份
update dayi_follow.follow_up set identity = 2 WHERE user_name='admin' or user_name='chenyh1';

#复制follow_up_log数据到新库中
INSERT into dayi_follow.follow_up_log (id,follow_id,dept_id,open_account_num,in_cash,in_cash_num,out_cash,manage_growth_fund,manage_fund,out_cash_num,create_time,update_time )
SELECT id,flow_id,dept_id,open_account_num,in_cash,in_cash_num,out_cash,manage_asset_growth,manage_asset,out_cash_num,create_date,modify_date from dayi_spot.follow_up_log;

#复制organization_contact数据到org_contact中
INSERT into dayi_follow.org_contact (id,org_id,contact_type,content,follow_id,create_time,update_time )
SELECT id,org_id,contact_type,content,flow_id,create_date,modify_date from dayi_spot.organization_contact;


#插入模块数据
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b0fedc000001a1', '', '系统管理', '', 1, 0, 9, '', 'admin', 'admin', '2018-12-22 13:52:42', '2018-12-24 16:19:05', 'fa-list-alt');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b110e4000001a1', '', '我的客户', '', 1, 0, 2, '', 'admin', 'admin', '2018-12-22 15:09:38', '2018-12-24 17:05:31', 'fa-calendar');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b11189000001a1', '', '个人报表', '', 1, 0, 4, '', 'admin', 'admin', '2018-12-22 15:12:23', '2018-12-24 16:18:10', 'fa-list');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b1157e000001a1', '', '团队客户', '', 1, 0, 3, '', 'admin', 'admin', '2018-12-22 15:29:16', '2018-12-24 16:18:02', 'fa-calendar');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b1157e000002a1', '', '团队报表', '', 1, 0, 5, '', 'admin', 'admin', '2018-12-22 15:29:33', '2018-12-24 16:18:21', 'fa-list ');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b1157e000003a1', '', '管理员报表', '', 1, 0, 6, '', 'admin', 'admin', '2018-12-22 15:30:05', '2018-12-24 16:18:38', 'fa-list');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b115ef000001a1', '', '跟进人管理', '/', 1, 1, 4, '', 'admin', NULL, '2018-12-22 15:31:09', '2018-12-22 15:38:21', 'fa-folder');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b11a38000001a1', '', '跟进人管理', '', 1, 0, 7, '', 'admin', 'admin', '2018-12-22 15:49:26', '2018-12-24 16:18:50', 'fa-folder ');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b11bec000001a1', '', '跟进人分配', '', 1, 0, 8, '', 'admin', 'admin', '2018-12-22 15:56:42', '2018-12-24 16:18:59', 'fa-tachometer');
INSERT INTO `dayi_follow`.`module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('23b1278e000001a1', '', '首页数据权限', '', 1, 0, 1, '', 'admin', 'admin', '2018-12-22 16:46:20', '2018-12-28 15:16:01', '');

#插入权限数据
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10044000001a1', '首页', '/', '', NULL, 1, 0, 1, 0, '', 'admin', 'admin', '2018-12-22 13:58:42', '2018-12-22 14:33:28', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10090000001a1', '模块管理', '/module/list', '23b0fedc000001a1', NULL, 1, 0, 1, 0, '', 'admin', NULL, '2018-12-22 13:59:58', '2018-12-22 13:59:58', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b101f4000001a1', '添加模块', '/module/add/save', '23b0fedc000001a1', NULL, 0, 0, 1, 1, '', 'admin', 'admin', '2018-12-22 14:05:54', '2018-12-22 15:09:16', '23b10090000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10249000001a1', '编辑模块', '/module/edit/save', '23b0fedc000001a1', NULL, 0, 0, 1, 2, '', 'admin', 'admin', '2018-12-22 14:07:19', '2018-12-22 17:11:13', '23b10090000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10249000002a1', '删除模块', '/module/delete', '23b0fedc000001a1', NULL, 0, 0, 1, 3, '', 'admin', 'admin', '2018-12-22 14:07:44', '2018-12-22 14:11:26', '23b10090000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10649000001a1', '解除绑定', '/module/untying', '23b0fedc000001a1', NULL, 0, 0, 1, 5, '', 'admin', 'admin', '2018-12-22 14:24:23', '2018-12-22 15:06:38', '23b10090000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10649000002a1', '启用禁用模块', '/module/enableModule', '23b0fedc000001a1', NULL, 0, 0, 1, 6, '', 'admin', NULL, '2018-12-22 14:25:02', '2018-12-22 14:25:02', '23b10090000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10719000001a1', '绑定功能', '/permission/bind/save', '23b0fedc000001a1', NULL, 0, 0, 1, 7, '', 'admin', 'humm', '2018-12-22 14:27:51', '2018-12-25 17:33:39', '23b1076c000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10719000002a1', '添加功能', '/permission/add/save', '23b0fedc000001a1', NULL, 0, 0, 1, 8, '', 'admin', 'humm', '2018-12-22 14:28:20', '2018-12-25 17:33:29', '23b1076c000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10719000003a1', '编辑功能', '/permission/edit/save', '23b0fedc000001a1', NULL, 0, 0, 1, 9, '', 'admin', 'humm', '2018-12-22 14:28:46', '2018-12-25 17:33:14', '23b1076c000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1076c000001a1', '功能列表', '/permission/list', '23b0fedc000001a1', NULL, 1, 0, 1, 10, '', 'admin', 'humm', '2018-12-22 14:29:14', '2018-12-25 17:32:26', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1076c000002a1', '删除功能', '/permission/delete', '23b0fedc000001a1', NULL, 0, 0, 1, 11, '', 'admin', 'humm', '2018-12-22 14:29:32', '2018-12-25 17:34:07', '23b1076c000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10815000001a1', '角色管理', '/role/list', '23b0fedc000001a1', NULL, 1, 0, 1, 12, '', 'admin', NULL, '2018-12-22 14:32:03', '2018-12-22 14:32:03', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10815000002a1', '添加角色', '/role/add/save', '23b0fedc000001a1', NULL, 0, 0, 1, 13, '', 'admin', NULL, '2018-12-22 14:32:24', '2018-12-22 14:32:24', '23b10815000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10815000003a1', '修改角色', '/role/edit/save', '23b0fedc000001a1', NULL, 0, 0, 1, 14, '', 'admin', 'humm', '2018-12-22 14:32:43', '2018-12-25 17:51:48', '23b10815000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10985000001a1', '启用禁用角色', '/role/enableRole', '23b0fedc000001a1', NULL, 0, 0, 1, 15, '', 'admin', NULL, '2018-12-22 14:38:11', '2018-12-22 14:38:11', '23b10815000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10985000002a1', '删除角色', '/role/delete', '23b0fedc000001a1', NULL, 0, 0, 1, 16, '', 'admin', NULL, '2018-12-22 14:38:30', '2018-12-22 14:38:30', '23b10815000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b109cd000001a1', '用户管理', '/user/list', '23b0fedc000001a1', NULL, 1, 0, 1, 17, '', 'admin', NULL, '2018-12-22 14:39:23', '2018-12-22 14:39:23', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b109cd000002a1', '添加用户', '/user/add/save', '23b0fedc000001a1', NULL, 0, 0, 1, 18, '', 'admin', NULL, '2018-12-22 14:39:45', '2018-12-22 14:39:45', '23b109cd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b109cd000003a1', '编辑用户', '/user/update/save', '23b0fedc000001a1', NULL, 0, 0, 1, 19, '', 'admin', NULL, '2018-12-22 14:40:03', '2018-12-22 14:40:03', '23b109cd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10a2c000001a1', '重置密码', '/user/resetpwd', '23b0fedc000001a1', NULL, 0, 0, 1, 20, '', 'admin', 'admin', '2018-12-22 14:40:58', '2018-12-22 14:56:52', '23b109cd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10a2c000002a1', '启用用户', '/user/enable', '23b0fedc000001a1', NULL, 0, 0, 1, 21, '', 'admin', 'admin', '2018-12-22 14:41:42', '2018-12-22 15:02:58', '23b109cd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10a6d000001a1', '禁用用户', '/user/disable', '23b0fedc000001a1', NULL, 0, 0, 1, 22, '', 'admin', 'admin', '2018-12-22 14:42:03', '2018-12-22 15:10:48', '23b109cd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10a6d000002a1', '删除用户', '/user/delete', '23b0fedc000001a1', NULL, 0, 0, 1, 23, '', 'admin', 'admin', '2018-12-22 14:42:23', '2018-12-22 15:03:12', '23b109cd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b10dfd000001a1', '部门管理', '/dept/list', '23b0fedc000001a1', NULL, 1, 0, 1, 24, '', 'admin', 'admin', '2018-12-22 14:57:15', '2018-12-24 17:03:00', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b111be000001a1', '个人日报', '/report/daily', '23b11189000001a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:13:16', '2018-12-22 16:41:33', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b111be000002a1', '个人周报', '/report/week', '23b11189000001a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:13:57', '2018-12-22 16:41:42', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b111ff000001a1', '个人月报', '/report/month', '23b11189000001a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:14:21', '2018-12-22 16:41:51', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1149a000001a1', '显示隐藏功权限', '/permission/displace', '23b0fedc000001a1', NULL, 0, 0, 1, 12, '', 'admin', 'humm', '2018-12-22 15:25:28', '2018-12-25 17:34:26', '23b1076c000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1149a000002a1', '我的代理商', '/agent/list', '23b110e4000001a1', NULL, 1, 0, 1, 0, '', 'admin', 'admin', '2018-12-22 15:26:21', '2018-12-24 16:16:33', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b114e7000001a1', '我的创客', '/org/list', '23b110e4000001a1', NULL, 1, 0, 1, 5, '', 'admin', 'admin', '2018-12-22 15:26:45', '2018-12-24 16:16:54', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11617000001a1', '团队代理商', '/team/agent/list', '23b1157e000001a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:31:49', '2018-12-24 10:14:27', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11617000002a1', '团队创客', '/team/org/list', '23b1157e000001a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:32:17', '2018-12-24 10:14:38', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11617000003a1', '团队日报', '/report/team/daily', '23b1157e000002a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:32:37', '2018-12-22 16:47:05', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11654000001a1', '团队周报', '/report/team/week', '23b1157e000002a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:32:50', '2018-12-22 16:47:13', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11654000002a1', '团队月报', '/report/team/month', '23b1157e000002a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:33:05', '2018-12-22 16:47:21', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11693000001a1', '管理员日报', '/report/admin/daily', '23b1157e000003a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:33:53', '2018-12-24 09:28:59', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11693000002a1', '管理员周报', '/report/admin/week', '23b1157e000003a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:34:06', '2018-12-24 09:29:10', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11693000003a1', '管理员月报', '/report/admin/month', '23b1157e000003a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:34:24', '2018-12-24 09:28:49', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11701000001a1', '跟进人列表', '/followup/list', '23b11a38000001a1', NULL, 1, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 15:35:43', '2018-12-22 15:52:53', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11eab000001a1', '代理商分配列表', '/agent/assign/list', '23b11bec000001a1', NULL, 1, 0, 1, 0, '', 'admin', 'admin', '2018-12-22 16:08:25', '2018-12-24 16:20:34', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11eab000002a1', '创客分配列表', '/org/assign/list', '23b11bec000001a1', NULL, 1, 0, 1, 3, '', 'admin', 'admin', '2018-12-22 16:08:58', '2018-12-24 16:20:29', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11f30000001a1', '代理商分配跟进人', '/agent/assign/save/batch', '23b11bec000001a1', NULL, 0, 0, 1, 1, '', 'admin', 'admin', '2018-12-22 16:10:38', '2018-12-24 16:20:15', '23b11eab000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11f30000002a1', '代理商清除跟进人', '/agent/assign/clear/batch', '23b11bec000001a1', NULL, 0, 0, 1, 2, '', 'admin', 'admin', '2018-12-22 16:11:27', '2018-12-24 16:20:23', '23b11eab000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11f93000001a1', '创客分配跟进人', '/org/assign/save/batch', '23b11bec000001a1', NULL, 0, 0, 1, 5, '', 'admin', 'admin', '2018-12-22 16:12:17', '2018-12-24 16:20:41', '23b11eab000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b11f93000002a1', '创客清除跟进人', '/org/assign/clear/batch', '23b11bec000001a1', NULL, 0, 0, 1, 6, '', 'admin', 'admin', '2018-12-22 16:12:41', '2018-12-24 16:20:48', '23b11eab000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23cb2c51000001a1', '代理商分配列表导出', '/agent/assign/list/export', '23b11bec000001a1', NULL, 0, 0, 1, 8, '', 'admin', NULL, '2018-12-22 16:21:09', '2018-12-22 16:21:09', '23b11eab000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23cb2c51000002a1', '创客分配列表导出', '/org/assign/list/export', '23b11bec000001a1', NULL, 0, 0, 1, 8, '', 'admin', NULL, '2018-12-22 16:21:12', '2018-12-22 16:21:12', '23b11eab000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b121ed000001a1', '代理商明细', '/followup/agent/list', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', NULL, '2018-12-22 16:22:19', '2018-12-22 16:22:19', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b121ed000002a1', '全部代理商明细', '/followup/all/agent/list', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', NULL, '2018-12-22 16:22:47', '2018-12-22 16:22:47', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b121ed000003a1', '创客明细', '/followup/org/list', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 16:23:03', '2018-12-22 16:26:15', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12248000001a1', '全部创客明细', '/followup/all/org/list', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', NULL, '2018-12-22 16:23:50', '2018-12-22 16:23:50', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b122d4000001a1', '创客明细导出', '/followup/org/list/export', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 16:26:10', '2018-12-27 22:13:12', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1231d000001a1', '代理商明细导出', '/followup/agent/list/export', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 16:27:23', '2018-12-27 22:13:27', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1231d000002a1', '全部代理商明细导出', '/followup/all/agent/list/export', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 16:27:44', '2018-12-27 22:12:45', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1231d000003a1', '全部创客明细导出', '/followup/all/org/list/export', '23b11a38000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 16:28:09', '2018-12-27 22:13:00', '23b11701000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12736000001a1', '团队日报详情', '/report/team/daily/detail', '23b1157e000002a1', NULL, 0, 0, 1, NULL, '', 'admin', NULL, '2018-12-22 16:44:52', '2018-12-22 16:44:52', '23b11617000003a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12736000002a1', '团队日报详情导出', '/report/team/daily/detail/export', '23b1157e000002a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-22 16:45:13', '2018-12-25 14:43:19', '23b11617000003a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b127c5000001a1', '直销日报', '/index/sale/personal/daily', '23b1278e000001a1', NULL, 0, 0, 1, 0, '', 'admin', 'admin', '2018-12-22 16:47:15', '2018-12-24 16:12:32', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b127c5000002a1', 'KA日报', '/index/ka/personal/daily', '23b1278e000001a1', NULL, 0, 0, 1, 2, '', 'admin', 'admin', '2018-12-22 16:47:40', '2018-12-24 16:13:03', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b127c5000003a1', '直销团队日报', '/index/sale/team/daily', '23b1278e000001a1', NULL, 0, 0, 1, 1, '', 'admin', 'admin', '2018-12-22 16:48:12', '2018-12-24 16:12:45', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12810000001a1', '团队创客', '/index/ka/team/orgdata', '23b1278e000001a1', NULL, 0, 0, 1, 10, '', 'admin', 'admin', '2018-12-22 16:48:30', '2018-12-24 16:14:47', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12810000002a1', '客户资产阶级统计', '/index/cus/fund/rank', '23b1278e000001a1', NULL, 0, 0, 1, 5, '', 'admin', 'admin', '2018-12-22 16:48:44', '2018-12-24 16:13:30', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12810000003a1', '管理员客户状态', '/index/admin/cus/status', '23b1278e000001a1', NULL, 0, 0, 1, 12, '', 'admin', 'admin', '2018-12-22 16:49:01', '2018-12-24 16:15:01', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12810000004a1', '个人创客', '/index/ka/personal/orgdata', '23b1278e000001a1', NULL, 0, 0, 1, 11, '', 'admin', 'admin', '2018-12-22 16:49:17', '2018-12-24 16:14:54', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12850000001a1', '近七天开户数', '/index/seven/open', '23b1278e000001a1', NULL, 0, 0, 1, 8, '', 'admin', 'admin', '2018-12-22 16:49:34', '2018-12-24 16:14:31', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12850000002a1', '待联系代理商', '/index/agent/link', '23b1278e000001a1', NULL, 0, 0, 1, 9, '', 'admin', 'admin', '2018-12-22 16:49:50', '2018-12-24 16:14:39', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12850000003a1', 'KA团队日报', '/index/ka/team/daily', '23b1278e000001a1', NULL, 0, 0, 1, 3, '', 'admin', 'admin', '2018-12-22 16:50:05', '2018-12-24 16:13:20', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12850000004a1', '客户类型占比', '/index/cus/proportion', '23b1278e000001a1', NULL, 0, 0, 1, 6, '', 'admin', 'admin', '2018-12-22 16:50:25', '2018-12-24 16:14:12', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12897000001a1', '近七日入金', '/index/seven/incash', '23b1278e000001a1', NULL, 0, 0, 1, 7, '', 'admin', 'admin', '2018-12-22 16:50:45', '2018-12-24 16:14:21', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b12897000002a1', '客户状态', '/index/cus/status', '23b1278e000001a1', NULL, 0, 0, 1, 13, '', 'admin', 'admin', '2018-12-22 16:50:59', '2018-12-24 16:15:11', '23b10044000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b1314c000001a1', '编辑部门', '/dept/update**', '23b0fedc000001a1', NULL, 0, 0, 1, 26, '', 'admin', NULL, '2018-12-22 17:27:54', '2018-12-22 17:27:54', '23b10dfd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b362cb000001a1', '添加部门', '/dept/add**', '23b0fedc000001a1', NULL, 0, 0, 1, 25, '', 'admin', 'admin', '2018-12-24 09:23:38', '2018-12-24 09:27:23', '23b10dfd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b362cb000002a1', '删除部门', '/dept/delete', '23b0fedc000001a1', NULL, 0, 0, 1, 27, '', 'admin', NULL, '2018-12-24 09:23:58', '2018-12-24 09:23:58', '23b10dfd000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b3637c000001a1', '管理员日报详情', '/report/admin/daily/detail', '23b1157e000003a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-24 09:26:35', '2018-12-24 09:30:41', '23b11693000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b3637c000002a1', '管理员周报导出', '/report/admin/week/export', '23b1157e000003a1', NULL, 0, 0, 1, NULL, '', 'admin', NULL, '2018-12-24 09:27:08', '2018-12-24 09:27:08', '23b11693000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b363df000001a1', '管理员月报导出', '/report/admin/month/export', '23b1157e000003a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-24 09:28:14', '2018-12-24 09:31:21', '23b11693000003a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b364c7000001a1', '添加代理商联系记录', '/agent/contact/add**', '23b110e4000001a1', NULL, 0, 0, 1, 2, '', 'admin', 'admin', '2018-12-24 09:32:06', '2018-12-25 14:16:12', '23b1149a000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b364c7000002a1', '查看代理商联系记录', '/agent/contact', '23b110e4000001a1', NULL, 0, 0, 1, 1, '', 'admin', 'admin', '2018-12-24 09:32:52', '2018-12-25 14:16:17', '23b1149a000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b36515000001a1', '查看代理商详情', '/agent/detail', '23b110e4000001a1', NULL, 0, 0, 1, 3, '', 'admin', 'admin', '2018-12-24 09:33:24', '2018-12-25 14:16:21', '23b1149a000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b36515000002a1', '查看代理商登录日志', '/agent/loginlog', '23b110e4000001a1', NULL, 0, 0, 1, 4, '', 'admin', 'admin', '2018-12-24 09:33:49', '2018-12-25 14:16:24', '23b1149a000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b3655f000001a1', '添加创客联系记录', '/org/contact/add/save', '23b110e4000001a1', NULL, 0, 0, 1, 7, '', 'admin', 'admin', '2018-12-24 09:34:38', '2018-12-25 14:16:45', '23b114e7000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b3655f000002a1', '查看创客联系记录', '/org/contact', '23b110e4000001a1', NULL, 0, 0, 1, 6, '', 'admin', 'admin', '2018-12-24 09:35:01', '2018-12-25 14:16:49', '23b114e7000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b36e31000001a1', '查看团队代理商详情', '/team/agent/detail', '23b1157e000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-24 10:12:15', '2018-12-24 15:27:42', '23b11617000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b36e31000002a1', '查看团队代理商联系记录', '/team/agent/contact', '23b1157e000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-24 10:13:16', '2018-12-24 15:26:53', '23b11617000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b36e9d000001a1', '查看团队代理商登录日志', '/team/agent/loginlog', '23b1157e000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-24 10:14:04', '2018-12-24 15:27:03', '23b11617000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b36eda000001a1', '查看团队创客联系记录', '/team/org/contact', '23b1157e000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-24 10:15:05', '2018-12-24 15:27:15', '23b11617000002a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b3790d000001a1', '系统日志', '/operatelog/list', '23b0fedc000001a1', NULL, 1, 0, 1, 28, '', 'quokka', 'quokka', '2018-12-24 10:58:36', '2018-12-24 11:01:43', '');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b4ff4c000001a1', '管理员日报详情导出', '/report/admin/daily/detail/export', '23b1157e000003a1', NULL, 0, 0, 1, 6, '', 'admin', 'admin', '2018-12-25 14:43:43', '2018-12-25 14:47:09', '23b11693000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b52a6f000001a1', '编辑角色页面', '/role/edit', '23b0fedc000001a1', NULL, 0, 0, 1, NULL, '', 'humm', 'humm', '2018-12-25 17:47:45', '2018-12-25 17:52:30', '23b10815000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b53005000001a1', '查看团队跟进人列表', '/team/agent/followup/select', '23b1157e000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-25 18:11:35', '2018-12-25 18:31:26', '23b11617000001a1');
INSERT INTO `dayi_follow`.`permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('23b534bc000001a1', '查看跟进人列表', '/agent/assign/select', '23b11bec000001a1', NULL, 0, 0, 1, NULL, '', 'admin', 'admin', '2018-12-25 18:31:43', '2018-12-25 18:36:35', '23b11eab000001a1');

#插入角色
INSERT INTO `dayi_follow`.`role`(`id`, `name`, `descript`, `status`, `create_time`, `update_time`) VALUES ('238b3efb000001a1', '超级管理员', '', 1, '2018-11-23 22:39:52', '2018-12-26 17:55:54');

#插入角色-权限
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000004a1', '238b3efb000001a1', '23b10044000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000005a1', '238b3efb000001a1', '23b127c5000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000006a1', '238b3efb000001a1', '23b127c5000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000007a1', '238b3efb000001a1', '23b127c5000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000008a1', '238b3efb000001a1', '23b12850000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000009a1', '238b3efb000001a1', '23b12810000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900000aa1', '238b3efb000001a1', '23b12850000004a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900000ba1', '238b3efb000001a1', '23b12897000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900000ca1', '238b3efb000001a1', '23b12850000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900000da1', '238b3efb000001a1', '23b12850000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900000ea1', '238b3efb000001a1', '23b12810000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900000fa1', '238b3efb000001a1', '23b12810000004a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000010a1', '238b3efb000001a1', '23b12810000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000011a1', '238b3efb000001a1', '23b12897000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000012a1', '238b3efb000001a1', '23b1149a000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000013a1', '238b3efb000001a1', '23b364c7000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000014a1', '238b3efb000001a1', '23b364c7000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000015a1', '238b3efb000001a1', '23b36515000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000016a1', '238b3efb000001a1', '23b36515000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000017a1', '238b3efb000001a1', '23b114e7000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000018a1', '238b3efb000001a1', '23b3655f000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000019a1', '238b3efb000001a1', '23b3655f000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900001aa1', '238b3efb000001a1', '23b11617000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900001ba1', '238b3efb000001a1', '23b11617000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900001ca1', '238b3efb000001a1', '23b36e31000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900001da1', '238b3efb000001a1', '23b36e31000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900001ea1', '238b3efb000001a1', '23b36e9d000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900001fa1', '238b3efb000001a1', '23b36eda000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000020a1', '238b3efb000001a1', '23b53005000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000021a1', '238b3efb000001a1', '23b111be000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000022a1', '238b3efb000001a1', '23b111be000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000023a1', '238b3efb000001a1', '23b111ff000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000024a1', '238b3efb000001a1', '23b11617000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000025a1', '238b3efb000001a1', '23b11654000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000026a1', '238b3efb000001a1', '23b11654000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000027a1', '238b3efb000001a1', '23b12736000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000028a1', '238b3efb000001a1', '23b12736000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000029a1', '238b3efb000001a1', '23b11693000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900002aa1', '238b3efb000001a1', '23b11693000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900002ba1', '238b3efb000001a1', '23b11693000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900002ca1', '238b3efb000001a1', '23b3637c000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900002da1', '238b3efb000001a1', '23b3637c000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900002ea1', '238b3efb000001a1', '23b363df000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900002fa1', '238b3efb000001a1', '23b4ff4c000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000030a1', '238b3efb000001a1', '23b11701000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000031a1', '238b3efb000001a1', '23b121ed000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000032a1', '238b3efb000001a1', '23b121ed000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000033a1', '238b3efb000001a1', '23b121ed000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000034a1', '238b3efb000001a1', '23b12248000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000035a1', '238b3efb000001a1', '23b122d4000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000036a1', '238b3efb000001a1', '23b1231d000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000037a1', '238b3efb000001a1', '23b1231d000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000038a1', '238b3efb000001a1', '23b1231d000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000039a1', '238b3efb000001a1', '23b11eab000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900003aa1', '238b3efb000001a1', '23b534bc000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900003ba1', '238b3efb000001a1', '23b11f30000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900003ca1', '238b3efb000001a1', '23b11f30000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900003da1', '238b3efb000001a1', '23b11eab000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900003ea1', '238b3efb000001a1', '23b11f93000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900003fa1', '238b3efb000001a1', '23b11f93000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000040a1', '238b3efb000001a1', '23b10090000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000041a1', '238b3efb000001a1', '23b52a6f000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000042a1', '238b3efb000001a1', '23b101f4000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000043a1', '238b3efb000001a1', '23b10249000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000044a1', '238b3efb000001a1', '23b10249000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000045a1', '238b3efb000001a1', '23b10649000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000046a1', '238b3efb000001a1', '23b10649000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000047a1', '238b3efb000001a1', '23b10719000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000048a1', '238b3efb000001a1', '23b10719000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000049a1', '238b3efb000001a1', '23b10719000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900004aa1', '238b3efb000001a1', '23b1076c000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900004ba1', '238b3efb000001a1', '23b1076c000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900004ca1', '238b3efb000001a1', '23b10815000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900004da1', '238b3efb000001a1', '23b1149a000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900004ea1', '238b3efb000001a1', '23b10815000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900004fa1', '238b3efb000001a1', '23b10815000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000050a1', '238b3efb000001a1', '23b10985000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000051a1', '238b3efb000001a1', '23b10985000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000052a1', '238b3efb000001a1', '23b109cd000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000053a1', '238b3efb000001a1', '23b109cd000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000054a1', '238b3efb000001a1', '23b109cd000003a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000055a1', '238b3efb000001a1', '23b10a2c000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000056a1', '238b3efb000001a1', '23b10a2c000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000057a1', '238b3efb000001a1', '23b10a6d000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000058a1', '238b3efb000001a1', '23b10a6d000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d29000059a1', '238b3efb000001a1', '23b10dfd000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900005aa1', '238b3efb000001a1', '23b362cb000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900005ba1', '238b3efb000001a1', '23b1314c000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900005ca1', '238b3efb000001a1', '23b362cb000002a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');
INSERT INTO `dayi_follow`.`role_permission`(`id`, `roleId`, `permissionId`, `create_time`, `update_time`) VALUES ('23b67d2900005da1', '238b3efb000001a1', '23b3790d000001a1', '2018-12-26 17:55:54', '2018-12-26 17:55:54');

#修补超管用户的角色
update dayi_follow.follow_up set roleids = '238b3efb000001a1' WHERE user_name='admin';
update dayi_follow.follow_up set roleids = '238b3efb000001a1' WHERE user_name='chenyh1';

