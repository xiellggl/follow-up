package com.dayi.follow.vo.report;


import java.util.ArrayList;
import java.util.List;

//团队周报
public class WeekVo extends WeekBaseVo {

    private List items;

    public WeekVo(String betweenDate) {
        super(betweenDate);
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public List getRSumList() {
        return ReportVo.sum(items);
    }

    public List getSRSumList() {
        return SourceReportVo.sum(items);
    }

    public void addSRItem(SourceReportVo o) {
        if (null != o) {
            if (items == null) {
                items = new ArrayList();
            }
            items.add(o);
        }
    }

    public void addRItem(ReportVo o) {
        if (null != o) {
            if (items == null) {
                items = new ArrayList();
            }
            items.add(o);
        }
    }
}