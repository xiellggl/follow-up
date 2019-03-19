package com.dayi.follow.vo.export;

import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.report.MonthVo;
import com.dayi.follow.vo.report.ReportVo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

/**
 * 团队月报 导出
 *
 * @author xiell
 */
public class AdminDetailExport extends AbstractExcel<ReportVo> {
    // 按照文档列头顺序添加
    private Column column1 = addColumn("日期", 3500);
    private Column column2 = addColumn("团队名称", 3500);
    private Column column3 = addColumn("今日新开户", 3500);
    private Column column4 = addColumn("新签创客", 3500);
    private Column column5 = addColumn("入金总额", 4000);
    private Column column6 = addColumn("出金总额", 4000);
    private Column column7 = addColumn("管理资产规模", 4000);
    private Column column8 = addColumn("资产规模净值", 6000);
    private Column column9 = addColumn("创客管理资产规模", 6000);

    public AdminDetailExport(String fileName, String fileTitle, List<ReportVo> datas) {
        super(fileName, fileTitle, datas);
    }


    protected void fillData(ReportVo vo) {
        // 日期
        column1.setValue(vo.getDate());
        // 团队名称
        if (!StringUtils.isBlank(vo.getName())) {
            column2.setValue(vo.getName());
        } else {
            column2.setValue(vo.getDeptName());
        }
        // 今日新开户
        column3.setValue(vo.getOpenAccountNum());
        // 新签创客
        column4.setValue(vo.getSignOrgNum());
        // 入金总额
        column5.setValue(vo.getInCash());
        // 出金总额
        column6.setValue(vo.getOutCash());
        // 管理资产规模
        column7.setValue(vo.getManageFund());
        //资产规模净值
        column8.setValue(vo.getManageGrowthFund());
        //创客管理资产规模
        column9.setValue(vo.getMakerFund());
    }


}

