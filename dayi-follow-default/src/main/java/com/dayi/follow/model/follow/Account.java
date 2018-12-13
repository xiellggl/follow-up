package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class Account {
    private BigDecimal useable;//余额
    private BigDecimal frozen;//代理冻结资金
    private BigDecimal outFrozen;//出金资金冻结
    private BigDecimal totalInCash;//总入金
    private String bankRealName;//实际的开户行名称
    private BigDecimal cargoInterestPuchas;//代采货物价值累加
    private BigDecimal cargoInterest;//货物价值累加
    private BigDecimal interest;//利息

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getUseable() {
        return useable;
    }

    public void setUseable(BigDecimal useable) {
        this.useable = useable;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public BigDecimal getOutFrozen() {
        return outFrozen;
    }

    public void setOutFrozen(BigDecimal outFrozen) {
        this.outFrozen = outFrozen;
    }

    public BigDecimal getTotalInCash() {
        return totalInCash;
    }

    public void setTotalInCash(BigDecimal totalInCash) {
        this.totalInCash = totalInCash;
    }

    public String getBankRealName() {
        return bankRealName;
    }

    public void setBankRealName(String bankRealName) {
        this.bankRealName = bankRealName;
    }

    public BigDecimal getCargoInterestPuchas() {
        return cargoInterestPuchas;
    }

    public void setCargoInterestPuchas(BigDecimal cargoInterestPuchas) {
        this.cargoInterestPuchas = cargoInterestPuchas;
    }

    public BigDecimal getCargoInterest() {
        return cargoInterest;
    }

    public void setCargoInterest(BigDecimal cargoInterest) {
        this.cargoInterest = cargoInterest;
    }
}