package com.dayi.follow.vo.report;


import java.util.ArrayList;
import java.util.List;

//周报
public class MonthVo extends MonthBaseVo {
    private List items;

    public MonthVo(String month) {
        super(month);
    }

    public List getItems() {
        return items;
    }

    public List getRSumList() {
        return ReportVo.sum(items);
    }

    public List getSRSumList() {
        return SourceReportVo.sum(items);
    }

    public void setItems(List items) {
        this.items = items;
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