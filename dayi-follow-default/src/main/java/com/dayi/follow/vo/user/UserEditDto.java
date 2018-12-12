package com.dayi.follow.vo.user;

import com.dayi.follow.model.follow.FollowUp;
import com.dayi.mybatis.support.BaseModel;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

public class UserEditDto extends BaseModel {

    /**
     * 姓名
     */
    @NotBlank(message = "名字不能为空！")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空！")
    private String mobile;

    /**
     * 所属部门ID
     */
    @NotBlank(message = "所属部门不能为空！")
    private String deptId;

    /**
     * 是否启用：1--启用；0--禁用
     */
    @NotNull(message = "请选择二级资产开关状态！")
    private Integer switchStatus;

    /**
     * 角色ids
     */
    @NotBlank(message = "角色不能为空！")
    private String roleids;

    /**
     * 1-跟进人，2-非跟进人
     */
    @NotNull(message = "请选择身份！")
    private Integer identity;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * dto 转 entity
     * @param dto
     * @param followUp
     */
    public static void dtoToEntity(UserEditDto dto, FollowUp followUp) {
        BeanUtils.copyProperties(dto, followUp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRoleids() {
        return roleids;
    }

    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
