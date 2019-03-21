package com.dayi.follow.vo.report;


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

    public void setItems(List items) {
        this.items = items;
    }

}