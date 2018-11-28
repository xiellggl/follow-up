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
     * 拼写 SQL 条件 -- 通过跟进人所属部门 ID -- 拼写本级不包括其他负责人的跟进人 ID 的 NOT IN 条件语句
     */
    public String spellMyDeptManagerFlowIdsNotInsql(Integer deptId, String fieldName, String alias) {
        StringBuffer strBuf = new StringBuffer();
        if (deptId != null && StringUtils.isNotBlank(fieldName)) {
            Department dept = deptMapper.get(deptId);
            if (dept != null) {
                String fieldStr = StringUtils.isNotBlank(alias) ? (alias + "." + fieldName) : fieldName;
                String flowUpIds = "";
                List<FollowUp> flowUpList = dept.getFollowUpList();
                if (CollectionUtils.isNotEmpty(flowUpList)) {
                    Integer isManager = null;
                    for (FollowUp flowUp : flowUpList) {
                        isManager = flowUp.getIsManager();  // 是否负责人：0--否；1--是
                        if (isManager != null && isManager == 1) {
                            flowUpIds = flowUpIds + flowUp.getId() + ",";
                        }
                    }
                }
                if (StringUtils.isNotBlank(flowUpIds)) {
                    flowUpIds = flowUpIds.substring(0, flowUpIds.length() - 1);
                    strBuf.append(" AND ").append(fieldStr).append(" NOT IN (").append(flowUpIds).append(") ");
                }
            }
        }
        return strBuf.toString();
    }


    /**
     * 拼写 SQL 条件 -- 通过跟进人所属部门 ID -- 拼写所有下级跟进人 ID 的 IN 条件语句
     */
    public String spellSubFlowIdsInsql(Integer deptId, List<Integer> chargeDeptIds, String fieldName, String alias, boolean isHaveOwn) {
        StringBuffer strBuf = new StringBuffer();
        List<Department> mysubList = new ArrayList<Department>();
//        if (StringUtils.isNotBlank(fieldName)) {
//            if (chargeDeptIds != null && chargeDeptIds.size() != 0) {
//                for (Integer chargeDeptId : chargeDeptIds) {
//                    mysubList = this.getSubDepts(chargeDeptId, isHaveOwn, mysubList);
//                }
//            }
//            if (deptId != null) {
//                mysubList = this.getSubDepts(deptId, isHaveOwn, null);
//            }
//            if (CollectionUtils.isNotEmpty(mysubList)) {
//                String fieldStr = StringUtils.isNotBlank(alias) ? (alias + "." + fieldName) : fieldName;
//                List<FollowUp> flowUpList = null;
//                String flowUpIds = "";
//                for (Department dept : mysubList) {
//                    flowUpList = dept.getFollowUpList();
//                    if (CollectionUtils.isNotEmpty(flowUpList)) {
//                        for (FollowUp flowUp : flowUpList) {
//                            if (!flowUp.getIsAdmin().equals(1)) { // 排除 管理员 跟进人
//                                flowUpIds = flowUpIds + flowUp.getId() + ",";
//                            }
//                        }
//                    }
//                }
//                if (StringUtils.isNotBlank(flowUpIds)) {
//                    flowUpIds = flowUpIds.substring(0, flowUpIds.length() - 1);
//                    strBuf.append(" AND ").append(fieldStr).append(" IN (").append(flowUpIds).append(") ");
//                }
//            }
//        }
        return strBuf.toString();
    }

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

    /* 私有方法：递归查询所有下级部门 */
    private List<Department> queryAllSubDeptList(List<Department> deptList, Integer ridDeptId, int depth) {
        List<Department> retList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(deptList)) {
            List<Department> subList = null;
            Department dept = null;
            /* 根据级次补充前面缩进图标 */
            String depthStr = "";
            for (int i = 0; i < depth; i++) {
                depthStr = depthStr + "　";
            }
            for (int i = 0; i < deptList.size(); i++) {
                dept = deptList.get(i);
                /* 修改部门时，需要排除自身及下属所有部门 */
                if (ridDeptId != null && ridDeptId.equals(dept.getId())) continue;
                dept.setTreeName(depthStr + dept.getName());
                retList.add(dept);
                subList = dept.getSubDeptList();
                if (CollectionUtils.isNotEmpty(subList)) {
                    retList.addAll(queryAllSubDeptList(subList, ridDeptId, (depth + 1)));
                }
            }
        }
        return retList;
    }


}