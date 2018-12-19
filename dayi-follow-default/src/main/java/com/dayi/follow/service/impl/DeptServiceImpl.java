package com.dayi.follow.service.impl;

import com.dayi.common.util.BizResult;
import com.dayi.component.annotation.Log;
import com.dayi.component.model.BaseLog;
import com.dayi.follow.dao.follow.DeptMapper;
import com.dayi.follow.dao.follow.FollowUpMapper;
import com.dayi.follow.dao.follow.UserMapper;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import com.dayi.follow.model.follow.OperateLog;
import com.dayi.follow.service.DeptService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/16
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    DeptMapper deptMapper;
    @Resource
    UserMapper userMapper;

    /**
     * 递归查询 -- 指定部门
     */
    public List<String> getSubDeptIds(String deptId) {

        List<String> deptIdList = new ArrayList<String>();
        if (StringUtils.isBlank(deptId)) return deptIdList;

        Department me = deptMapper.get(deptId);
        List<Department> subDeptList = me.getSubDeptList();
        if (CollectionUtils.isEmpty(subDeptList)) return deptIdList;

        for (Department dept : subDeptList) {
            deptIdList.add(dept.getId());
            deptIdList.addAll(this.getSubDeptIds(dept.getId()));
        }

        return deptIdList;
    }

    @Override
    public List<Department> getSubDept(String deptId) {
        List<Department> deptList = new ArrayList<Department>();
        if (StringUtils.isBlank(deptId)) return deptList;

        Department me = deptMapper.get(deptId);
        List<Department> subDeptList = me.getSubDeptList();
        if (CollectionUtils.isEmpty(subDeptList)) return deptList;

        for (Department dept : subDeptList) {
            deptList.add(dept);
            deptList.addAll(this.getSubDept(dept.getId()));
        }
        return deptList;
    }

    @Override
    public Department get(String deptId) {
        return deptMapper.get(deptId);
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.UPDATE, what = "部门管理", note = "修改部门")
    public BizResult updateDept(Department departmentVo) {
        //处理上级部门人数
        Department department = deptMapper.get(departmentVo.getId());

        doUpdatePerson(department, departmentVo.getPid());

        //复制属性
        department.setName(departmentVo.getName());
        department.setPid(departmentVo.getPid());
        department.setSortNo(departmentVo.getSortNo());
        if (departmentVo.getCityServer() == null) {
            department.setCityServer(0);
        } else {
            department.setCityServer(departmentVo.getCityServer());
        }
        department.setCityInviteCode(departmentVo.getCityInviteCode());
        department.setRemark(departmentVo.getRemark());
        department.setUpdateTime(new Date());

        if (deptMapper.updateAll(department) == 1) {
            return BizResult.SUCCESS;
        } else {
            return BizResult.FAIL;
        }
    }

    @Override
    public List<Department> getTopList() {
        return deptMapper.getTopList();

    }


    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.ADD, what = "部门管理", note = "添加部门")
    public BizResult add(Department department) {
        department.setId(deptMapper.getNewId());
        if (department.getCityServer() == null) {
            department.setCityServer(0);
        }
        department.setPersonNum(0);

        return 1 == deptMapper.add(department) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.DELETE, what = "部门管理", note = "删除部门")
    public BizResult delete(Department department) {
        List<String> userIds = new ArrayList<String>();
        userIds = userMapper.findIdsByDeptId(department.getId());
        if (!userIds.isEmpty()) return BizResult.fail("本部门存在用户，无法删除！");

        List<Department> subDeptList = this.getSubDept(department.getId());
        for (Department department1 : subDeptList) {
            userIds = userMapper.findIdsByDeptId(department1.getId());
        }
        if (userIds.isEmpty()) {
            for (Department department1 : subDeptList) {
                deptMapper.delete(department1);
            }

            deptMapper.delete(department);

            //处理上级部门人数
            doReducePerson(department);

            return BizResult.SUCCESS;
        } else {
            return BizResult.fail("本部门存在用户，无法删除！");
        }

    }

    @Override
    public boolean checkInviteCode(String inviteCode) {
        Department department = deptMapper.getByInviteCode(inviteCode);
        if (department == null) return false;
        return true;
    }

    @Override
    public void doReducePerson(Department department) {
        if (null != department) {
            Department parentDept = department.getParentDept();
            //处理上级部门
            while (parentDept != null) {
                parentDept.setPersonNum(parentDept.getPersonNum() - department.getPersonNum());
                deptMapper.updateAll(parentDept);
                parentDept = parentDept.getParentDept();
            }
        }
    }

    /**
     * @Description:
     * @Params: [department-修改的部门, newPId-新的上级部门Id]
     * @return: void
     * @Author: xiell
     * @Date: 2018/12/10
     */
    @Override
    public void doUpdatePerson(Department department, String newPId) {
        Department oldDept = department.getParentDept();
        while (oldDept != null) {
            oldDept.setPersonNum(oldDept.getPersonNum() - department.getPersonNum());
            deptMapper.updateAll(oldDept);
            oldDept = oldDept.getParentDept();
        }

        Department newDept = deptMapper.get(newPId);
        while (newDept != null) {
            newDept.setPersonNum(newDept.getPersonNum() + department.getPersonNum());
            deptMapper.updateAll(newDept);
            newDept = newDept.getParentDept();
        }
    }

    @Override
    @Log(target = OperateLog.class, action = BaseLog.LogAction.SEARCH, what = "部门管理", note = "查询部门列表")
    public List<Department> getDeptTree(Department department) {
        List<Department> departments = new ArrayList<Department>();

        List<Department> topList = getTopList();//顶级部门

        for (Department department1 : topList) {
            this.doRecurs(department1, department, departments, 0);
        }
        return departments;
    }

    private List<Department> doRecurs(Department loopDept, Department originDept, List<Department> departments, int depth) {
        if (originDept != null && loopDept.getId().equals(originDept.getId())) return departments;

        String prefix = "";
        for (int i = 0; i < depth; i++) {
            prefix = prefix + "　";
        }
        loopDept.setTreeName(prefix + loopDept.getName());

        departments.add(loopDept);

        depth++;
        List<Department> subDeptList = loopDept.getSubDeptList();
        for (Department department1 : subDeptList) {
            if (originDept != null && department1.getId().equals(originDept.getId())) continue;//排除原来部门
            doRecurs(department1, originDept, departments, depth);
        }

        return departments;
    }

}