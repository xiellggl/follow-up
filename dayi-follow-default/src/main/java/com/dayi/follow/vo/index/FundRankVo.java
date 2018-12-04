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
        if (fundRank != null) {
            switch (fundRank) {
                case (1):
                    fundRankStr = FundRankEnum.ZERO.getName();
                    break;
                case (2):
                    fundRankStr = FundRankEnum.DOWN_2W.getName();
                    break;
                case (3):
                    fundRankStr = FundRankEnum.BET_2WTO10W.getName();
                    break;
                case (4):
                    fundRankStr = FundRankEnum.BET_10WTO50W.getName();
                    break;
                case (5):
                    fundRankStr = FundRankEnum.BET_50WTO100W.getName();
                    break;
                case (6):
                    fundRankStr = FundRankEnum.BET_100WTO300W.getName();
                    break;
                case (7):
                    fundRankStr = FundRankEnum.UP_300W.getName();
                    break;
            }
        }
        return fundRankStr;
    }

    public void setFundRankStr(String fundRankStr) {
        this.fundRankStr = fundRankStr;
    }
}