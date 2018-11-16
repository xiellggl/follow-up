package com.dayi35.core.followup.model;

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiidee.eagle.framework.base.model.BaseEntity;
import com.fiidee.eagle.framework.util.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;

/** 跟进人部门 实体类 */
@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class FollowDept extends BaseEntity {

    private Integer pid;    // 上级部门ID
    private Integer sortNo; // 排序号（同级）
    private String name;    // 部门名称
    private String remark;  // 部门描述
    private Integer cityServer; //是否城市服务商
    private String  cityInviteCode; //城市服务商邀请码
    private Integer personNum;//部门人数
    @Transient
    private String treeName;  // 部门名称--树型显示

    @Transient
    private String managers;  // 负责人

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "followDept", cascade = {CascadeType.REMOVE})
    private List<FlowUp> flowUpList = Lists.newArrayList();  // 跟进人

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private FollowDept parentDept;  // 上级部门

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentDept", cascade = {CascadeType.REMOVE})
    @OrderBy("sortNo")
    private List<FollowDept> subDeptList = Lists.newArrayList();  // 下级部门

    public FollowDept() {}

    public FollowDept(Integer sortNo, String name, String treeName) {
        this.sortNo = sortNo;
        this.name = name;
        this.treeName = treeName;
    }

    /** 新增 -- 设置属性 */
    public void setAddProperty() {
        Date nowDate = new Date();
        this.setCreateDate(nowDate);
        this.setModifyDate(nowDate);
    }

    /** 修改 -- 设置属性 */
    public void setModifyProperty(FollowDept deptUI) {
        if(deptUI != null){ // 更新页面修改属性值
            BeanUtils.copyProperties(deptUI, this);
            if(deptUI.getPid() == null){ // 当选择上级部门为顶级时
                this.setPid(null);
            }
        }
        this.setModifyDate(new Date());
    }

    /* 自定义属性方法 */
    /** 负责人 */
    @Transient
    public String getManagers() {
        if(CollectionUtils.isNotEmpty(flowUpList)){
            managers = "";
            Integer isManager = null;
            for(FlowUp flowUp : flowUpList){
                isManager = flowUp.getIsManager(); // 是否负责人：0--否；1--是
                if(isManager != null && isManager == 1){
                    managers = managers + flowUp.getLinkPerson() + "，";
                }
            }
            if(StringUtils.isNotBlank(managers)){
                managers = managers.substring(0, managers.length() - 1);
            }
        }
        return managers;
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

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    /** Getter Setter Field */
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

    public List<FlowUp> getFlowUpList() {
        return flowUpList;
    }

    public void setFlowUpList(List<FlowUp> flowUpList) {
        this.flowUpList = flowUpList;
    }

    public FollowDept getParentDept() {
        return parentDept;
    }

    public void setParentDept(FollowDept parentDept) {
        this.parentDept = parentDept;
    }

    public List<FollowDept> getSubDeptList() {
        return subDeptList;
    }

    public void setSubDeptList(List<FollowDept> subDeptList) {
        this.subDeptList = subDeptList;
    }
}
