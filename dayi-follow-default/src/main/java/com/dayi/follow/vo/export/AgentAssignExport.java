package com.dayi.follow.vo.export;

import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.agent.AssignListVo;
import com.dayi.follow.vo.followup.FMDetailListVo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

/**
 * 跟进人管理，代理商/创客明细导出
 */
public class AgentAssignExport extends AbstractExcel<AssignListVo> {

    // 按照文档列头顺序添加
    private Column column0 = addColumn("会员ID", 4000);
    private Column column1 = addColumn("名称", 4000);
    private Column column2 = addColumn("注册时间", 5000);
    private Column column3 = addColumn("手机号", 4000);
    private Column column4 = addColumn("开户银行", 4000);
    private Column column5 = addColumn("实际开户银行", 4000);
    private Column column6 = addColumn("身份证号", 4000);
    private Column column7 = addColumn("银行卡号", 4000);
    private Column column8 = addColumn("跟进人", 4000);
    private Column column9 = addColumn("分配时间", 4000);
    private Column column10 = addColumn("邀请码", 6000);

    public AgentAssignExport(String fileName, String fileTitle, List<AssignListVo> datas) {
        super(fileName, fileTitle, datas);
    }

    protected void fillData(AssignListVo vo) {
        // 会员id
        column0.setValue(vo.getId());
        // 名称
        column1.setValue(vo.getLinkPerson());
        // 注册时间
        column2.setFullTimeValue(vo.getCreateDate());
        // 手机号
        column3.setValue(vo.getMobile());
        // 开户银行
        column4.setValue(vo.getBank());
        // 实际开户银行
        column5.setValue(vo.getRealBank());
        //身份证号
        column6.setValue(vo.getIdCard());
        //银行卡号
        column7.setValue(vo.getBankAccount());
        //跟进人
        column8.setValue(vo.getFollowUp());
        // 分配时间
        if (vo.getAssignDate() == null) {
            column9.setValue("");
        } else {
            column9.setFullTimeValue(vo.getAssignDate());
        }
        //邀请码
        column10.setValue(vo.getInviteCode());
    }
}

