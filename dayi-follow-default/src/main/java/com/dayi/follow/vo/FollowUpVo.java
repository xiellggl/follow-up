package com.dayi.follow.vo;

import com.dayi.follow.model.follow.Department;

public class FollowUpVo {
    private String name;  // 姓名
    private String userName;    // 账号
    private String inviteCode;  // 邀请码
    private Integer isManager;  // 是否负责人：0--否；1--是
    private Integer isAdmin;    // 是否管理员：0--否；1--是
    private String deptName;
    private Department department;
    private String createTimeFm;
    private String updateTimeFm;

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