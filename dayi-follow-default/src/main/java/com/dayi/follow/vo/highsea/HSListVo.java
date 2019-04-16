package com.dayi.follow.vo.highsea;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.util.CommonUtils;

import java.util.Date;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class HSListVo {
    private Integer id;

    private String linkPerson;//姓名
    private String idCard;//身份证

    private String inviteCode;//邀请码

    private Integer customerType;//客户类型

    private Date createDate;//注册时间

    private Date warehouseDate;//入库时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkPersonFm() {
        return CommonUtils.getHideName(linkPerson, idCard);
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCustomerTypeStr() {
        return AgentCusTypeEnum.getNameByValue(customerType);
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(Date warehouseDate) {
        this.warehouseDate = warehouseDate;
    }
}