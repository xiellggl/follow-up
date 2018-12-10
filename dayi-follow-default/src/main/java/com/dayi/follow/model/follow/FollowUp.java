package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
import com.dayi.user.authorization.authc.Principal;
import com.dayi.user.model.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


/**
 * @author xiell
 * @date 2018/11/12
 */
public class FollowUp extends BaseModel implements Principal {
    @NotBlank(message = "名字不能为空！")
    private String name;  // 姓名
    @NotBlank(message = "账号不能为空！")
    private String userName;    // 账号
    @NotBlank(message = "密码不能为空！")
    private String password;    // 密码
    @NotBlank(message = "手机号不能为空！")
    private String mobile;      // 手机号
    @NotBlank(message = "所属部门不能为空！")
    private String deptId;     // 所属部门ID
    @NotBlank(message = "邀请码不能为空！")
    private String inviteCode;  // 邀请码
    private Integer disable=1;    // 是否启用：1--启用；0--禁用
    @NotNull(message = "请选择二级资产开关状态！")
    private Integer switchStatus; //二级资管状态
    private String channelRemark;//渠道码备注
    private String createBy;    // 创建人
    private String updateBy;    // 修改人
    @NotBlank(message = "角色不能为空！")
    private String roleids;//角色ids
    @NotNull(message = "请选择身份！")
    private Integer identity;//1-跟进人，2-运维




    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getRoleids() {
        return roleids;
    }

    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

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

    public String getDeptId() {
        return deptId;
    }

    public String getInviteCode() {
        return inviteCode;
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


    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
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

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
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


    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
