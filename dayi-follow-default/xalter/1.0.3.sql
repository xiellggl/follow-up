USE dayi_follow;

ALTER TABLE `follow_agent`
ADD COLUMN `high_sea_flag` tinyint(2) NOT NULL COMMENT '是否公海用户(0：不是,1：是)' AFTER `his_max_fund`,
ADD COLUMN `warehouse_date` timestamp(0) NULL DEFAULT NULL COMMENT '入库时间' AFTER `high_sea_flag`;

#将羊毛党的客户作为公海基础(这条看情况执行)
#UPDATE follow_agent set high_sea_flag = 1,follow_id = null,warehouse_date=now(),update_time=now()  where follow_id =44;

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
INSERT INTO `permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('244cbf59000001a1', '客户公海', '/highsea/list', '244cbf1a000001a1', NULL, 1, 0, 1, 0, '', 'admin', NULL, '2019-04-19 17:18:42', '2019-04-19 17:25:17', '');
INSERT INTO `permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('244cc529000001a1', '公海设置', '/highsea/getconfig', '244cbf1a000001a1', NULL, 0, 0, 1, 1, '', 'admin', 'admin', '2019-04-19 17:43:30', '2019-04-19 17:48:25', '244cbf59000001a1');
INSERT INTO `permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('245596dc000001a1', '踢入公海', '/highsea/kick', '244cbf1a000001a1', NULL, 0, 0, 1, 2, '', 'admin', NULL, '2037-08-19 03:55:33', '2037-08-19 03:55:33', '23b1149a000002a1');
INSERT INTO `permission`(`id`, `name`, `url`, `moduleid`, `display_name`, `display_status`, `del_status`, `status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `parentid`) VALUES ('24559700000001a1', '认领客户', '/highsea/drag', '244cbf1a000001a1', NULL, 0, 0, 1, 3, '', 'admin', NULL, '2037-08-19 03:56:10', '2037-08-19 03:56:10', '244cbf59000001a1');
INSERT INTO `module`(`id`, `parentid`, `name`, `url`, `status`, `del_status`, `sort`, `description`, `create_by`, `update_by`, `create_time`, `update_time`, `css_name`) VALUES ('244cbf1a000001a1', '', '公海', '', 1, 0, 10, '', 'admin', NULL, '2019-04-19 17:17:39', '2019-04-19 17:17:14', 'fa-users');


