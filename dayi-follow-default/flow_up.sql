

#建库
CREATE DATABASE `dayi_follow_all` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

USE dayi_follow_all;

#跟进人表
DROP TABLE IF EXISTS follow_up;
CREATE TABLE `follow_up`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(10) NULL DEFAULT NULL COMMENT '所属部门ID',
  `is_manager` tinyint(2) NULL DEFAULT 0 COMMENT '是否负责人：0--否；1--是',
  `is_admin` tinyint(2) NULL DEFAULT 0 COMMENT '是否管理员：0--否；1--是',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `invite_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `disable` int(11) NULL DEFAULT 1 COMMENT '1:启用0禁用',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `switch_status` tinyint(2) NULL DEFAULT 1 COMMENT '二级资管状态(1:开启，-1:关闭)',
  `channel_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道码备注',
  `charge_dept_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责部门id',
  `roleids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '角色ids',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;




#复制agent_contact数据到新库中
INSERT into  agent_contact
(id,agent_id,contact_type,customer_type,cus_intention_type,next_contact_time,follow_id,content,create_time,update_time)
SELECT id,agent_id,contact_type,customer_type,customer_intention_type,next_contact_time,flow_id,content,create_date,modify_date
from .agent_contact


#将agent表的跟进人数据，迁移到follow_agent表
INSERT into  follow_agent (id,follow_id,agent_id,follow_up_before,assign_date_before,
customer_type,cus_intention_type,assign_date,agent_fund_before,total_fund_before,create_time,update_time)
SELECT CONCAT(id,'a',IFNULL(flow_id,0)),flow_id,id,follow_up_before,flow_date_before,
customer_type,customer_intention_type,flow_date,asserts_before,balance_before,create_date,
modify_date from agent

#复制follow_up表数据
INSERT into  follow_up (id,dept_id,name,mobile,invite_code,
user_name,disable,`password`,create_by,update_by,create_time,update_time,switch_status,channel_remark,identity)
SELECT id,dept_id,link_person,mobile,invite_code,
user_name,`disable`,`password`,create_by,modify_by,create_date,modify_date,switch_status,channel_remark,1
from flow_up

#复制部门表数据
insert into department(id,pid,sort_no,name,city_server,city_invite_code,remark,person_num,create_time,update_time)
SELECT id,pid,sort_no,name,city_server,city_invite_code,remark,person_num,create_date,modify_date from follow_dept

#将organization表的跟进人数据，迁移到follow_org表
INSERT into  follow_org (id,follow_id,org_id,follow_up_before,assign_date,
assign_date_before,manage_fund_before,create_time,update_time)
SELECT CONCAT(id,'a',IFNULL(flow_id,0)),flow_id,id,follow_up_before,flow_date,
flow_date_before,asserts_before,create_date,
modify_date from organization

#复制organization_contact数据到新库中
INSERT into org_contact (id,org_id,contact_type,content,follow_id,create_time,update_time )
SELECT id,org_id,contact_type,content,flow_id,create_date,modify_date from dev_dayi_spot.organization_contact

#复制follow_up_log数据到新库中
INSERT into follow_up_log (id,follow_id,dept_id,open_account_num,in_cash,in_cash_num,out_cash,manage_growth_fund,manage_fund,out_cash_num,create_time,update_time )
SELECT id,flow_id,dept_id,open_account_num,in_cash,in_cash_num,out_cash,manage_asset_growth,manage_asset,out_cash_num,create_date,modify_date from follow_up_log