package com.dayi.follow.vo.export;


import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.report.ReportDailyVo;

import java.util.List;

/**
 * 销售团队日报 导出
 *
 * @author xiell
 */
public class TeamDailyDetailExport extends AbstractExcel<ReportDailyVo> {
    // 按照文档列头顺序添加
    private Column column1 = addColumn("日期", 4000);
    private Column column2 = addColumn("姓名", 4000);
    private Column column3 = addColumn("今日新开户数", 4000);
    private Column column4 = addColumn("创客净增资金规模", 4000);
    private Column column5 = addColumn("入金总额", 3000);
    private Column column6 = addColumn("入金人数", 3000);
    private Column column7 = addColumn("实际出金总额", 3000);
    private Column column8 = addColumn("实际出金人数", 3000);

    public TeamDailyDetailExport(String fileName, String fileTitle, List<ReportDailyVo> datas) {
        super(fileName, fileTitle, datas);
    }

    protected void fillData(ReportDailyVo vo) {
        // 日期
        column1.setValue(vo.getDate());
        // 姓名
        column2.setValue(vo.getName());
        // 今日新开户数
        column3.setValue(vo.getOpenNum());
        // 今日创客净增资金规模
        column4.setValue(vo.getManageFundGrowthFm());
        // 网银转入总额
        column5.setValue(vo.getInCashFm());
        // 转出到卡总额
        column6.setValue(vo.getOutCashFm());
        // 入金人数
        column7.setValue(vo.getInCashNum());
        // 出金人数
        column8.setValue(vo.getOutCashNum());
    }
}

