
USE dayi_spot;

#删除代理商联系表
DROP TABLE IF EXISTS agent_contact;


#删除部门表
DROP TABLE IF EXISTS follow_dept;

#修改代理商表

alter table agent drop  flow_id ,drop  follow_up , drop  flow_date ,drop  flow_date_before ,
                  drop  follow_up_before,drop  asserts_before,drop  balance_before,
                  drop  customer_type,drop  customer_intention_type;


#修改机构商表
alter table organization drop  flow_id ,drop  follow_up , drop  flow_date ,drop  flow_date_before ,
                  drop  follow_up_before,drop  asserts_before;

#删除跟进人表

DROP TABLE IF EXISTS flow_up;

#删除统计每日业绩表

DROP TABLE IF EXISTS follow_up_log;

#删除创客联系表
DROP TABLE IF EXISTS organization_contact;

