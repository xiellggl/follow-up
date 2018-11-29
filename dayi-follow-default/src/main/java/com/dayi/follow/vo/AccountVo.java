package com.dayi.follow.vo;

import com.dayi.common.util.NameItem;

import java.util.Date;

/**
 * @author xiell
 * @date 2018/11/14
 */
public class AccountVo {
    private double useable;
    private double frozen;
    private double outFrozen;
    private double totalInCash;

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
}