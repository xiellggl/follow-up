package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */

import com.dayi.follow.model.follow.Department;

import java.util.List;

/**
 *  部门 业务接口类
 */
public interface DeptService {

    /**
     * 拼写 SQL 条件 -- 通过跟进人所属部门 ID -- 拼写本级不包括其他负责人的跟进人 ID 的 NOT IN 条件语句
     */
     String spellMyDeptManagerFlowIdsNotInsql(Integer deptId, String fieldName, String alias);
    /**
     * 拼写 SQL 条件 -- 通过跟进人所属部门 ID -- 拼写所有下级跟进人 ID 的 IN 条件语句
     */
     String spellSubFlowIdsInsql(Integer deptId, List<Integer> chargeDeptIds, String fieldName, String alias, boolean isHaveOwn);

    /**
     * 递归查询 -- 指定部门
     */
     List<Department> getMySubDeptList(Integer deptId, boolean isHaveOwn, List<Department> loopInvokingList);

    /**
     * 获取跟进人信息
     */
    Department get(String deptId);

    /**
     * 更新部门
     * @param department
     * @return
     */
    boolean updateDept(Department department);

}
