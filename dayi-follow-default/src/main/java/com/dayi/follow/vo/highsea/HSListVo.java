package com.dayi.follow.vo.highsea;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.util.CommonUtils;

/**
 * @author xiell
 * @date 2019/4/10
 */
public class HSListVo {
    private String linkPerson;//姓名
    private String idCard;//身份证

    private String inviteCode;//邀请码

    private Integer customerType;//客户类型

    private String createDate;//注册时间

    private String warehouseDate;//入库时间

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(String warehouseDate) {
        this.warehouseDate = warehouseDate;
    }
}