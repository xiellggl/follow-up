package com.dayi.follow.vo.report;


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
}