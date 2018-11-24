

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

INSERT into  dayi_follow_all.agent_contact
(id,agent_id,contact_type,customer_type,customer_intention_type,next_contact_time,flow_id,content,create_date,modify_date)
SELECT * from dev_dayi_spot.agent_contact