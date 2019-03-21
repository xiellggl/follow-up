package com.dayi.follow.model.follow;

import com.dayi.common.util.NameItem;
import com.dayi.common.util.NameItems;
import com.dayi.mybatis.support.BaseModel;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @author xiell
 * @date 2019/3/18
 */
public class SourceReport extends BaseModel {
    private Integer type;//来源类型
    private BigDecimal inCash;//网银转入总额
    private BigDecimal outCash;//转出到卡总额

    private BigDecimal growthFund;//净增总额

    private BigDecimal manageFund;//管理资产规模
    private BigDecimal manageGrowthFund;//管理资产规模净增

    public static final NameItem TYPE_ZG = NameItem.valueOf("资管中心", 1);
    public static final NameItem TYPE_MAKER = NameItem.valueOf("创客", 2);
    public static final NameItem TYPE_OTHER = NameItem.valueOf("其它", 3);

    public final static NameItems TYPE_ALL = NameItems.valueOf(TYPE_ZG, TYPE_MAKER, TYPE_OTHER);


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getInCash() {
        return inCash;
    }

    public void setInCash(BigDecimal inCash) {
        this.inCash = inCash;
    }

    public BigDecimal getOutCash() {
        return outCash;
    }

    public void setOutCash(BigDecimal outCash) {
        this.outCash = outCash;
    }

    public BigDecimal getGrowthFund() {
        return growthFund;
    }

    public void setGrowthFund(BigDecimal growthFund) {
        this.growthFund = growthFund;
    }

    public BigDecimal getManageFund() {
        return manageFund;
    }

    public void setManageFund(BigDecimal manageFund) {
        this.manageFund = manageFund;
    }

    public BigDecimal getManageGrowthFund() {
        return manageGrowthFund;
    }

    public void setManageGrowthFund(BigDecimal manageGrowthFund) {
        this.manageGrowthFund = manageGrowthFund;
    }

    public final static String getTypeName(Integer id) {
        if (id == null) return null;
        Iterator<NameItem> iterator = TYPE_ALL.iterator();
        while (iterator.hasNext()) {
            NameItem next = iterator.next();
            if (id == next.getId()) {
                return next.getName();
            }
        }
        return null;
    }
}