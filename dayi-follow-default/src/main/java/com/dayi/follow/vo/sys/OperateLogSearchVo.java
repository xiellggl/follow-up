package com.dayi.follow.vo.sys;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统日志查询条件封装Vo
 *
 * @author zhoujiakai
 * @date 2018/12/03
 */
public class OperateLogSearchVo {

    /** 页码 */
    private int pageNo;

    /** 每页条数 */
    private int pageSize;

    /** 操作人 */
    private String operatorName;

    /** 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public int getPageNo() {
        return pageNo > 0 ? pageNo : 1;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize > 0 ? pageSize : 10;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "OperateLogSearchVo{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", operatorName='" + operatorName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
