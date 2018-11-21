package com.dayi.follow.vo;


import com.dayi.follow.util.Misc;

import java.math.BigDecimal;

public class IndexVo {
    //ka创客数据
    private Integer kaOrgNum;//KA创客人数
    private Integer kaOrgValidAgentNum;//KA创客有效代理商总数
    private BigDecimal kaOrgManageFund;//KA创客管理资金规模

    //今日待联系代理商
    private Integer waitLinkNum;//今日待联系代理商

    //日报数据
    private String date;//日报日期
    private Integer openNum;//新开户数
    private BigDecimal inCash;//入金总额
    private String inCashFm;//入金总额（格式化：显示前两位和小数点位，其余用*标识）
    private Integer inCashNum;//入金人数
    private BigDecimal applyOutCash;//提现转出总额
    private String applyOutCashFm;//提现转出总额（格式化：显示前两位和小数点位，其余用*标识）
    private Integer outCashNum;//出金人数

    //----------------客户状态开始
    private Integer cusNum = 0;  // 客户总数
    private Integer hadLinkNum = 0;  // 已联系--用户人数
    private Integer hadInCashNum = 0;  // 已入金--用户人数
    private Integer hadRealNameNum = 0; // 已实名认证--用户人数
    private Integer hadSignNum = 0;  // 已绑卡--用户人数
    private Integer hadAgentNum = 0;  // 已代理--用户人数
    private Integer hadLostNum = 0;  //流失客户
    private Integer noFundNum = 0;  // 总资产为零--用户人数
    //管理员特有
    private Integer followUpNum = 0; // 跟进人数量
    private Integer waitAssignCusNum;//待分配用户
    private Double totalFund = 0d;  // 代理商资产总规模
    private String totalFundFm;  // 代理商资产总规模
    //------------------客户状态结束


    private Integer deptId;//部门（团队）ID
    private String deptName;//部门名称


}