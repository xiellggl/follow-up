package com.dayi.follow.vo.export;

import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.report.AdminWeekVo;

import java.util.List;

/**
 * 团队周报 导出
 *
 * @author xiell
 */
public class AdminWeekReportExport extends AbstractExcel<AdminWeekVo> {
    // 按照文档列头顺序添加
    private Column column1 = addColumn("团队", 3500);
    private Column column2 = addColumn("姓名", 3500);
    private Column column3 = addColumn("周一有效新开户", 3500);
    private Column column4 = addColumn("周一入金总额", 3500);
    private Column column5 = addColumn("周二有效新开户", 3500);
    private Column column6 = addColumn("周二入金总额", 3500);
    private Column column7 = addColumn("周三有效新开户", 3500);
    private Column column8 = addColumn("周三入金总额", 3500);
    private Column column9 = addColumn("周四有效新开户", 3500);
    private Column column10 = addColumn("周四入金总额", 3500);
    private Column column11 = addColumn("周五有效新开户", 3500);
    private Column column12 = addColumn("周五入金总额", 3500);
    private Column column13 = addColumn("本周完成新开户", 3500);
    private Column column14 = addColumn("本周完成入金总额", 3500);

    public AdminWeekReportExport(String fileName, String fileTitle, List<AdminWeekVo> datas) {
        super(fileName, fileTitle, datas);
    }



    protected void fillData(AdminWeekVo vo) {
        // 团队
        column1.setValue(vo.getDeptName());
        // 姓名
        column2.setValue(vo.getName());
        // 周一有效新开户
        column3.setValue(vo.getMonOpen());
        // 周一入金代理
        column4.setValue(vo.getMonInCash().toString());
        // 周二有效新开户
        column5.setValue(vo.getTueOpen());
        // 周二入金代理
        column6.setValue(vo.getTueInCash().toString());
        // 周三有效新开户
        column7.setValue(vo.getWedOpen());
        // 周三入金代理
        column8.setValue(vo.getWedInCash().toString());
        // 周四有效新开户
        column9.setValue(vo.getThuOpen());
        // 周四入金代理
        column10.setValue(vo.getThuInCash().toString());
        // 周五有效新开户
        column11.setValue(vo.getFriOpen());
        // 周五入金代理
        column12.setValue(vo.getFriInCash().toString());
        // 周有效新开户
        column13.setValue(vo.getOpenNum());
        // 周入金代理
        column14.setValue(vo.getInCash().toString());


    }


}

