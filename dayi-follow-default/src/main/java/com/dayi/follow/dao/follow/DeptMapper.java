package com.dayi.follow.dao.follow;

import com.dayi.follow.model.follow.Department;
import com.dayi.mybatis.support.BaseMapper;

import java.util.List;

/**
 * @author xiell
 * @date 2018/11/13
 */
public interface DeptMapper extends BaseMapper<Department> {

    List<Department> getTopList();

    Department get(String deptId);

    List<DeptMapper> getSubDepts(String deptId);

    Department getByInviteCode(String inviteCode);
}
