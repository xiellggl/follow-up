package com.dayi.follow.vo;

import com.dayi.common.util.DateUtil;
import com.dayi.follow.model.follow.Department;
import com.dayi.follow.model.follow.FollowUp;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class FollowUpVo extends FollowUp {
    private String name;  // 姓名
    private String userName;    // 账号
    @NotBlank(message = "密码不能为空！")
    private String password;    // 密码
    @NotBlank(message = "手机号不能为空！")
    private String mobile;      // 手机号
    @NotBlank(message = "所属部门不能为空！")
    private String deptId;     // 所属部门ID
    @NotBlank(message = "邀请码不能为空！")
    private String inviteCode;  // 邀请码
    private Integer isManager;  // 是否负责人：0--否；1--是
    private Integer isAdmin;    // 是否管理员：0--否；1--是
    private Integer disable;    // 是否启用：1--启用；0--禁用
    @NotNull(message = "请选择二级资产开关状态！")
    private Integer switchStatus; //二级资管状态
    private String channelRemark;//渠道码备注
    private String chargeDeptId;//负责部门id
    private String createBy;    // 创建人
    private String modifyBy;    // 修改人
    @NotBlank(message = "角色不能为空！")
    private String roleids;//角色ids
    @NotNull(message = "请选择身份！")
    private Integer identity;//1-跟进人，2-运维
    private String deptName;

    private Department department;

    private String createTimeFm;
    private String updateTimeFm;

    public String getCreateTimeFm() {
        if (getCreateTime() != null) {
            createTimeFm = DateUtil.formatDate(getCreateTime(), "yyyy-MM-dd HH:mm:ss");
        }
        return createTimeFm;
    }

    public void setCreateTimeFm(String createTimeFm) {
        this.createTimeFm = createTimeFm;
    }

    public String getUpdateTimeFm() {
        if (getCreateTime() != null) {
            updateTimeFm = DateUtil.formatDate(getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
        }
        return updateTimeFm;
    }

    public void setUpdateTimeFm(String updateTimeFm) {
        this.updateTimeFm = updateTimeFm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


}