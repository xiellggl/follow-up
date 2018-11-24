package com.dayi.follow.model.follow;

import com.dayi.mybatis.support.BaseModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiell
 * @date 2018/11/16
 */

public class Department extends BaseModel {

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
    @Transient
    private List<FollowUp> followUpList = new ArrayList();  // 跟进人
    @Transient
    private Department parentDept;  // 上级部门
    @Transient
    private List<Department> subDeptList = new ArrayList();  // 下级部门

    public Department() {}

    public Department(Integer sortNo, String name, String treeName) {
        this.sortNo = sortNo;
        this.name = name;
        this.treeName = treeName;
    }



    /** 修改 -- 设置属性 */
    public void setModifyProperty(Department department) {
        if(department != null){ // 更新页面修改属性值
            BeanUtils.copyProperties(department, this);
            if(department.getPid() == null){ // 当选择上级部门为顶级时
                this.setPid(null);
            }
        }
        this.setUpdateTime(new Date());
    }

    /* 自定义属性方法 */
    /** 负责人 */
    @Transient
    public String getManagers() {
        if(CollectionUtils.isNotEmpty(followUpList)){
            managers = "";
            Integer isManager = null;
            for(FollowUp followUp : followUpList){
                isManager = followUp.getIsManager(); // 是否负责人：0--否；1--是
                if(isManager != null && isManager == 1){
                    managers = managers + followUp.getName() + "，";
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

    public List<FollowUp> getFollowUpList() {
        return followUpList;
    }

    public void setFollowUpList(List<FollowUp> followUpList) {
        this.followUpList = followUpList;
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
}
