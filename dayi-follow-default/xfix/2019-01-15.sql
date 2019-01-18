

use dayi_follow;

#修复资产管理中心部门人数

UPDATE department set person_num =52 where id =2;

#变更首页路径
UPDATE permission set url ='/index' where id ='23b10044000001a1';