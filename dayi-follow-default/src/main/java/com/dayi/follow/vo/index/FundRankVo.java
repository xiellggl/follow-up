package com.dayi.follow.vo.index;


import com.dayi.follow.enums.AgentCusTypeEnum;
import com.dayi.follow.enums.FundRankEnum;

//首页资产阶级图
public class FundRankVo {
    private Integer num;
    private Integer fundRank;
    private String fundRankStr;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getFundRank() {
        return fundRank;
    }

    public void setFundRank(Integer fundRank) {
        this.fundRank = fundRank;
    }

    public String getFundRankStr() {
        return FundRankEnum.getNameByValue(fundRank);
    }

    public void setFundRankStr(String fundRankStr) {
        this.fundRankStr = fundRankStr;
    }
}