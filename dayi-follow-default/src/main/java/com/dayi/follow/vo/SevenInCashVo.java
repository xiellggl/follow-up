package com.dayi.follow.vo;


import com.dayi.follow.util.Misc;

import java.math.BigDecimal;

public class SevenInCashVo {
    private BigDecimal inCash;//入金总额
    private String inCashFm;//入金总额（格式化：显示前两位和小数点位，其余用*标识）
    private String dateStr;

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public String getInCashFm() {
        if (this.inCash != null) {
            inCashFm = Misc.parseMoney(inCash.doubleValue(), 2);
        }

        return inCashFm;
    }

    public void setInCashFm(String inCashFm) {
        this.inCashFm = inCashFm;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}