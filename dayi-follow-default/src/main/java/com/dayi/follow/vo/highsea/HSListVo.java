package com.dayi.follow.vo.highsea;

import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.util.CommonUtils;

import java.math.BigDecimal;
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

    private boolean bankSign;//是否绑卡,同时作为是否开通结算银行的条件
    private Date bankSignDate; //绑卡时间

    private BigDecimal inCash;//入金，是否为空-用于是否入金判断
    private Date fristInCashDate; //首次入金时间

    private Date cardValidDate;//实名认证时间

    public boolean isBankSign() {
        return bankSign;
    }

    public void setBankSign(boolean bankSign) {
        this.bankSign = bankSign;
    }

    public Date getBankSignDate() {
        return bankSignDate;
    }

    public void setBankSignDate(Date bankSignDate) {
        this.bankSignDate = bankSignDate;
    }

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public Date getFristInCashDate() {
        return fristInCashDate;
    }

    public void setFristInCashDate(Date fristInCashDate) {
        this.fristInCashDate = fristInCashDate;
    }

    public Date getCardValidDate() {
        return cardValidDate;
    }

    public void setCardValidDate(Date cardValidDate) {
        this.cardValidDate = cardValidDate;
    }

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