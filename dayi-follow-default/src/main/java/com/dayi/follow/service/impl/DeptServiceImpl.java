package com.dayi.follow.service.impl;

import com.dayi.common.util.BizResult;
import com.dayi.follow.dao.follow.DeptMapper;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
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
    public BizResult updateDept(Department department) {
        department.setUpdateTime(new Date());
        if (deptMapper.update(department) == 1) {
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
    public BizResult add(Department department) {
        return 1 == deptMapper.add(department) ? BizResult.SUCCESS : BizResult.FAIL;
    }


    @Override
    public List<Department> getEditCanSelectDepts(List<Department> departments, Department department) {

        for (Department item : departments) {
            if (item.getId().equals(department.getId())) {
                departments.remove(item);
                return departments;
            }
            getEditCanSelectDepts(item.getSubDeptList(), department);
        }
        return departments;
    }

    @Override
    public BizResult delete(Department department) {
        return 1 == deptMapper.delete(department) ? BizResult.SUCCESS : BizResult.FAIL;
    }

    @Override
    public List<Department> doDeptTreeName(List<Department> departments, int depth) {
        if (departments.isEmpty()) return null;
        String prefix = "";

        for (int i = 0; i < depth; i++) {
            prefix = prefix + "　";
        }

        ++depth;
        for (Department department : departments) {
            department.setTreeName(prefix + department.getName());
            doDeptTreeName(department.getSubDeptList(), depth);
        }
        return departments;
    }

}