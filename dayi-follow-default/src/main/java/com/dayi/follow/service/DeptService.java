package com.dayi.follow.service;


/**
 * @author xiell
 * @date 2018/11/12
 */

import com.dayi.common.util.BizResult;
import com.dayi.follow.model.follow.Department;

import java.util.List;

/**
 * 部门 业务接口类
 */
public interface DeptService {

    /**
     * 递归查询 -- 指定部门
     */
    List<String> getSubDeptIds(String deptId);

    /**
     * 递归查询 -- 指定部门
     */
    List<Department> getSubDept(String deptId);

    /**
     * 获取部门
     */
    Department get(String deptId);

    /**
     * 更新部门
     *
     * @param department
     * @return
     */
    BizResult updateDept(Department department);

    //获取一级部门
    List<Department> getTopList();


    //添加部门
    BizResult add(Department department);


    BizResult delete(Department department);

    //检查城市服务商邀请码
    boolean checkInviteCode(String inviteCode);

    //处理减少部门人数
    void doReducePerson(Department department);

    //处理更新部门人数
    void doUpdatePerson(Department department,String newPId);

    List<Department> getDeptTree(Department department);
}
