package com.dayi.follow.vo.export;

import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.followup.FMDetailListVo;
import org.joda.time.DateTime;

import java.util.List;

/**
 * 跟进人管理，代理商/创客明细导出
 */
public class AgentDetailExport extends AbstractExcel<FMDetailListVo> {

    // 按照文档列头顺序添加
    private Column column0 = addColumn("会员ID", 4000);
    private Column column1 = addColumn("名称", 4000);
    private Column column2 = addColumn("手机号", 5000);
    private Column column3 = addColumn("注册时间", 4000);
    private Column column4 = addColumn("邀请码", 4000);
    private Column column5 = addColumn("当前跟进人", 4000);
    private Column column6 = addColumn("变更前跟进人", 4000);
    private Column column7 = addColumn("变更日期", 4000);
    private Column column8 = addColumn("变更前总资产", 4000);
    private Column column9 = addColumn("当前总资产", 6000);
    private Column column10 = addColumn("管理资产规模", 6000);
    private Column column11 = addColumn("代理商服务费", 6000);


    public AgentDetailExport(String fileName, String fileTitle, List<FMDetailListVo> datas) {
        super(fileName, fileTitle, datas);
    }

    protected void fillData(FMDetailListVo vo) {
        // 会员id
        column0.setValue(vo.getId());
        // 名称
        column1.setValue(vo.getLinkPerson());
        // 手机号
        column2.setValue(vo.getMobile());
        // 注册时间
        column3.setFullTimeValue(vo.getCreateDate());
        // 邀请码
        column4.setValue(vo.getInviteCode());
        // 跟进人
        column5.setValue(vo.getFollowUp());

        column6.setValue(vo.getFollowUpBefore());
        column7.setValue(new DateTime(vo.getChangeDate()).toString("yyyy-MM-dd HH:mm:ss"));
        column8.setValue(vo.getTotalFundBefore());
        column9.setValue(vo.getTotalFund());
        column10.setValue(vo.getManageFund());
        column11.setValue(vo.getInterest());
    }
}

