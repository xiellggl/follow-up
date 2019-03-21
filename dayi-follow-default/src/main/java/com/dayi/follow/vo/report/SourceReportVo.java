package com.dayi.follow.vo.report;


import com.dayi.follow.model.follow.SourceReport;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SourceReportVo {
    private String typeStr;
    private Integer type;//来源类型
    private BigDecimal inCash;//网银转入总额
    private BigDecimal outCash;//转出到卡总额

    private BigDecimal growthFund;//净增总额

    private BigDecimal manageFund;//管理资产规模
    private BigDecimal manageGrowthFund;//管理资产规模净增


    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

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

    public String getTypeStr() {
        if (StringUtils.isBlank(typeStr)) {
            return SourceReport.getTypeName(this.type);
        } else return typeStr;
    }

    public static final List sum(List<SourceReportVo> list) {
        List newList = new ArrayList();
        newList.addAll(list);

        if (!list.isEmpty()) {

            BigDecimal inCash = BigDecimal.ZERO;
            BigDecimal outCash = BigDecimal.ZERO;
            BigDecimal growthFund = BigDecimal.ZERO;
            BigDecimal manageFund = BigDecimal.ZERO;
            BigDecimal manageGrowthFund = BigDecimal.ZERO;

            for (SourceReportVo item : list) {
                inCash.add(item.getInCash());
                outCash.add(item.getOutCash());
                growthFund.add(item.getGrowthFund());
                manageFund.add(item.getManageFund());
                manageGrowthFund.add(item.getManageGrowthFund());
            }

            SourceReportVo sr = new SourceReportVo();
            sr.setTypeStr("总计");
            sr.setInCash(inCash);
            sr.setOutCash(outCash);
            sr.setGrowthFund(growthFund);
            sr.setManageFund(manageFund);
            sr.setManageGrowthFund(manageGrowthFund);
            newList.add(sr);
        }
        return newList;
    }
}