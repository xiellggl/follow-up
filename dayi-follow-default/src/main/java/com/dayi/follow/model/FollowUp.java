package com.dayi.follow.model;

import com.dayi.mybatis.support.BaseModel;
import com.dayi.user.authorization.authc.Principal;


/**
 * @author xiell
 * @date 2018/11/12
 */
public class FollowUp extends BaseModel implements Principal {
    private String name;  // 姓名
    private String userName;    // 账号
    private String password;    // 密码
    private String mobile;      // 手机号
    private Integer deptId;     // 所属部门ID
    private String inviteCode;  // 邀请码
    private Integer isManager;  // 是否负责人：0--否；1--是
    private Integer isAdmin;    // 是否管理员：0--否；1--是
    private Integer disable;    // 是否启用：1--启用；0--禁用
    private Integer switchStatus; //二级资管状态
    private String channelRemark;//渠道码备注
    private String chargeDeptId;//负责部门id
    private String createBy;    // 创建人
    private String modifyBy;    // 修改人


    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public Integer getDisable() {
        return disable;
    }

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public String getChannelRemark() {
        return channelRemark;
    }

    public String getChargeDeptId() {
        return chargeDeptId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    //---------------------setter

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public void setChannelRemark(String channelRemark) {
        this.channelRemark = channelRemark;
    }

    public void setChargeDeptId(String chargeDeptId) {
        this.chargeDeptId = chargeDeptId;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

}
