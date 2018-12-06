package com.dayi.follow.vo.user;

import com.dayi.follow.enums.MemberStatusEnum;
import com.dayi.follow.model.follow.Department;

import java.util.Date;
import java.util.List;

public class UserVo {
    private String id;
    private String name;  // 姓名
    private String userName;    // 账号
    private String inviteCode;  // 邀请码
    private Integer isManager;  // 是否负责人：0--否；1--是
    private Integer isAdmin;    // 是否管理员：0--否；1--是
    private String deptName;
    private Department department;
    private Date createTime;
    private String createTimeFm;
    private String updateTimeFm;

    //添加于用户列表
    private String roleids;
    private String rolesName;
    private Integer disable;
    private String status;

    public String getStatus() {
        if (this.getDisable()!=null){
            if(this.getDisable().intValue()== MemberStatusEnum.ENABLE.getValue()){
                return MemberStatusEnum.ENABLE.getName();
            }else {
                return MemberStatusEnum.DISABLE.getName();
            }
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleids() {
        return roleids;
    }

    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCreateTimeFm() {
        return createTimeFm;
    }

    public void setCreateTimeFm(String createTimeFm) {
        this.createTimeFm = createTimeFm;
    }

    public String getUpdateTimeFm() {
        return updateTimeFm;
    }

    public void setUpdateTimeFm(String updateTimeFm) {
        this.updateTimeFm = updateTimeFm;
    }
}