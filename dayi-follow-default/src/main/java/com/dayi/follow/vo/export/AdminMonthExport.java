package com.dayi.follow.vo.export;

import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.report.AdminMonthVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 团队月报 导出
 *
 * @author xiell
 */
public class AdminMonthExport extends AbstractExcel<AdminMonthVo> {
    // 按照文档列头顺序添加
    private Column column1 = addColumn("团队", 3500);
    private Column column2 = addColumn("姓名", 3500);
    private Column column3 = addColumn("邀请码", 3500);
    private Column column4 = addColumn("新签创客", 3500);
    private Column column5 = addColumn("完成有效开户量", 4000);
    private Column column6 = addColumn("月完成入金总额", 4000);
    private Column column7 = addColumn("当前管理总资产规模", 4000);
    private Column column8 = addColumn("管理资产规模净值（环比上个月）", 6000);

    public AdminMonthExport(String fileName, String fileTitle, List<AdminMonthVo> datas) {
        super(fileName, fileTitle, datas);
    }


    protected void fillData(AdminMonthVo vo) {
        // 团队
        column1.setValue(vo.getDeptName());
        // 姓名
        column2.setValue(vo.getName());
        // 邀请码
        column3.setValue(vo.getInviteCode());
        // 新签创客
        column4.setValue(vo.getOrgNum());
        // 完成有效开户量
        column5.setValue(vo.getOpenNum());
        // 月完成新入金代理金额
        column6.setValue(vo.getInCash());
        // 当前管理总资产规模
        BigDecimal manageFund = vo.getManageFund();
        if (manageFund != null) {
            column7.setValue(manageFund.toString());
        } else {
            column7.setValue("暂无数据");
        }
        // 管理资产规模净值（环比上个月）
        column8.setValue(vo.getRingGrowthRatio());
    }


}

