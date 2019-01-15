package com.dayi.follow.vo.export;

import com.dayi.common.web.excel.AbstractExcel;
import com.dayi.common.web.excel.Column;
import com.dayi.follow.vo.agent.AssignListVo;

import java.util.List;

/**
 * 分配跟进人，创客列表导出
 */
public class OrgAssignExport extends AbstractExcel<AssignListVo> {

    // 按照文档列头顺序添加
    private Column column0 = addColumn("会员ID", 4000);
    private Column column1 = addColumn("名称", 4000);
    private Column column2 = addColumn("注册时间", 5000);
    private Column column3 = addColumn("手机号", 4000);
    private Column column4 = addColumn("身份证号", 4000);
    private Column column5 = addColumn("机构类型", 4000);
    private Column column6 = addColumn("跟进人", 4000);
    private Column column7 = addColumn("分配时间", 4000);
    private Column column8 = addColumn("邀请码", 4000);

    public OrgAssignExport(String fileName, String fileTitle, List<AssignListVo> datas) {
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
        // 身份证号
        column4.setValue(vo.getIdCard());
        // 机构类型
        column5.setValue(vo.getOrgTypeStr());
        // 跟进人
        column6.setValue(vo.getFollowUp());
        //分配时间
        if (vo.getAssignDate() == null) {
            column7.setValue("");
        } else {
            column7.setFullTimeValue(vo.getAssignDate());
        }
        //邀请码
        column8.setValue(vo.getInviteCode());
    }
}

