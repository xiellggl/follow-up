package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;

import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class Account {
    private double useable;//余额
    private double frozen;//代理冻结资金
    private double outFrozen;//出金资金冻结
    private double totalInCash;//总入金
    private String bankRealName;//实际的开户行名称


    public double getUseable() {
        return useable;
    }

    public void setUseable(double useable) {
        this.useable = useable;
    }

    public double getFrozen() {
        return frozen;
    }

    public void setFrozen(double frozen) {
        this.frozen = frozen;
    }

    public double getOutFrozen() {
        return outFrozen;
    }

    public void setOutFrozen(double outFrozen) {
        this.outFrozen = outFrozen;
    }

    public double getTotalInCash() {
        return totalInCash;
    }

    public void setTotalInCash(double totalInCash) {
        this.totalInCash = totalInCash;
    }

    public String getBankRealName() {
        return bankRealName;
    }

    public void setBankRealName(String bankRealName) {
        this.bankRealName = bankRealName;
    }
}