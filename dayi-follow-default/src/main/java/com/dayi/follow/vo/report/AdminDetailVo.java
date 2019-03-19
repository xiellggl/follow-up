package com.dayi.follow.vo.report;


import java.util.ArrayList;
import java.util.List;

public class AdminDetailVo extends ReportVo{

    List<ReportVo> pList = new ArrayList<>();

    public List<ReportVo> getpList() {
        return pList;
    }

    public void setpList(List<ReportVo> pList) {
        this.pList = pList;
    }

}