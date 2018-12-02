package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/16
 */

public class Department extends BaseModel {

    private Integer pid;    // 上级部门ID
    @NotNull(message = "排序号不能为空！")
    private Integer sortNo; // 排序号（同级）
    @NotBlank(message = "部门名称不能为空！")
    private String name;    // 部门名称
    private String remark;  // 部门描述
    @NotNull(message = "请选择是否城市服务商！")
    private Integer cityServer = 0; //是否城市服务商-默认不是
    private String cityInviteCode; //城市服务商邀请码
    private Integer personNum;//部门人数
    @NotBlank(message = "负责人不能为空！")
    private String managerId;//负责人id


    @Transient
    private FollowUp manager;  // 负责人
    @Transient
    private Department parentDept;  // 上级部门
    @Transient
    private List<Department> subDeptList = new ArrayList();  // 下级部门
    @Transient
    private String treeName;//树形显示

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Integer getCityServer() {
        return cityServer;
    }

    public void setCityServer(Integer cityServer) {
        this.cityServer = cityServer;
    }

    public String getCityInviteCode() {
        return cityInviteCode;
    }

    public void setCityInviteCode(String cityInviteCode) {
        this.cityInviteCode = cityInviteCode;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Department getParentDept() {
        return parentDept;
    }

    public void setParentDept(Department parentDept) {
        this.parentDept = parentDept;
    }

    public List<Department> getSubDeptList() {
        return subDeptList;
    }

    public void setSubDeptList(List<Department> subDeptList) {
        this.subDeptList = subDeptList;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public FollowUp getManager() {
        return manager;
    }

    public void setManager(FollowUp manager) {
        this.manager = manager;
    }
}
