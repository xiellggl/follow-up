<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>管理员报表-管理员日报</title>
    <%@include file="/inc/followup/csslink.jsp" %>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
</head>
<body class="no-skin">
<%@include file="/inc/followup/topbar.jsp" %>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/">首页</a>
                    </li>
                    <li>管理员报表</li>
                    <li class="active">管理员日报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            管理员日报
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal">
                        <div class="clearfix maintop">
                            <div class="col-xs-12 col-sm-3 maintop" style="width: 180px">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value="${date}"
                                           placeholder="日志日期"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 maintop" style="width: 100px;">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-xs btn-purple">
                                            <span class="ace-icon fa fa-search"></span>
                                            搜索
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <%--<th>日期</th>--%>
                                <th>客户来源</th>
                                <th>今日入金</th>
                                <th>今日出金</th>
                                <th>
                                    今日净增
                                    <a href="#" data-toggle="tooltip" title="今日入金 - 今日出金">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>
                                    管理资产规模
                                    <a href="#" data-toggle="tooltip" title="名下所有代理商的总货款之和（从分配时算起）">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>
                                    资产管理规模净值
                                    <a href="#" data-toggle="tooltip" title="环比上一天，当前管理资产规模 - 上一天的资产规模">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty list}">
                                <tr>
                                    <td colspan="7" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty list}">
                                <c:forEach items="${list}" var="item">
                                    <tr>
                                        <%--<td>${item.date}</td>--%>
                                        <td>${item.typeStr}</td>
                                        <td>${item.inCash}</td>
                                        <td>${item.outCash}</td>
                                        <td>${item.growthFund}</td>
                                        <td>${item.manageFund}</td>
                                        <td>${item.manageGrowthFund}</td>
                                        <td>
                                            <c:if test="${item.type eq 1}">
                                            <a href="./daily/detail?date=${item.date}">
                                                    <i class="ace-icon fa fa-external-link"></i>查看详情
                                            </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty page}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page url="${pageUrl}" type="3"/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%@include file="/inc/followup/script.jsp" %>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "daterangepicker"], function (common) {
        common.head();
        var date_o = {
            autoUpdateInput: false,
            locale: locale_cn,
            singleDatePicker: true      //设置为单个的datepicker，而不是有区间的datepicker 默认false
        };
        date_o.locale.cancelLabel = "清空";
        $('.dates').daterangepicker(date_o);

        $('.dates').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD'));
        });

        $('.dates').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
    });
</script>
</body>
</html>