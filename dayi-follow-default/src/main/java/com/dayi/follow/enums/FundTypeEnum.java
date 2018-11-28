package com.dayi.follow.enums;

/**
 * @author xiell
 * @date 2018/11/14
 */

/**
 * 资金操作枚举
 */
public enum FundTypeEnum {
    INTO(1, "转入"), // 入金
    OUTCASH(-9, "转出"),//出金成功 0, //出金
    AGENT_IN_HAND(-107, "申请代理"),//手动申请代理
    AGENED(-100, "已代理"),
    RECEIVED_COST(-101, "回款"),
    REFUND(-102, "退款"),
    AGENCY_COST(-103, "服务费"),//代理商
    DEFICIT(-108, "贸易订金不足"),//deficit //短缺资金
    DAMAGES(-8, "转让费"),
    WITHDRAWALS(-112, "转出到卡"),//withdrawals
    WITHDRAWALS_FAILED(-119, "转出到卡失败"),
    INVEST_CANCLE(-109, "取消预约代理"),
    PAYPUCHAS(-200, "收价格平抑金"),
    HONGBAO(103, "红包"),
    COUPON(104, "活动返现"),
    FIXTOSTABLE(105, "转换稳健代理成功"),
    ENDAGENTAPPLY(106, "转让申请"),
    ENDAGENTAPPLYCANCEL(107, "取消转让"),
    FIXTODOUBLE(109, "转换双季代理成功"),

    INVITATION_COUPON(108, "邀请好友活动返现"),
    WITHDRAWALSAPPLY(-121, "转出到卡申请"),
    AUTOAGENED(-120, "自动续期代理"),


    //2016-3-23 新增
    INTO_FAILED(-113, "转入失败"),    //入金失败
    OUTCASH_FROZEN(-114, "转出预约"), //出金预约
    OUTCASH_ROLL_BACK(-115, "转出失败"), //出金失败
    AGENT_AUTO_APPLY_INVEST(-116, "自动申请代理"),
    AGENT_AUTO_INVEST(-117, "自动代理"),
    AGENT_PRIOR_INVEST(-118, "优先代理"),
    //现货的资金类型,主要是平台流水使用
    SPOT_TOSPOT(34, "代理可出货款"),
    SPOT_TOSPOTINTEREST(35, "货款价格变化"),
    SPOT_PAYPUCHAS(40, "退价格平抑金"),
    SERVICE_FEE(-41, "提现手续费"),
    DISCOUNT_AMOUNT(-42, "转让优惠额度"),
    //易收富新增
    INEXPRESSFEE(-45, "收物流费"),
    //机构使用
    COMMISSION_UNBALANCE(-106, "服务费"),
    COMMISSION_BALANCE(-105, "收代理费用"),
    CLEARACCOUNTIN(-999, "系统补登"),
    CLEARACCOUNTOUT(999, "系统补登"),
    //优化之后,暂时使用的类型(已不再使用,但是为了之前的正常显示，故保留)
    DEFICIT_GENERATE(-110, "生成货物价格变化"),
    FULL_GENERATE(-111, "货物价值变化生成"),
    TEST_(11111111, "");

    private Integer key;
    private String cname;

    FundTypeEnum(Integer key, String cname) {
        this.key = key;
        this.cname = cname;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public static String getCnameByKey(int key) {
        for (FundTypeEnum type : FundTypeEnum.values()) {
            if (type.getKey() == key)
                return type.getCname();
        }
        return "";
    }

    public static FundTypeEnum getEnumByKey(int key) {
        for (FundTypeEnum type : FundTypeEnum.values()) {
            if (type.getKey() == key)
                return type;
        }
        return null;
    }
}

