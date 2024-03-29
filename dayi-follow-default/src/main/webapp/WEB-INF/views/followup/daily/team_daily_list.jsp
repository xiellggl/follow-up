<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>团队报表-团队日报</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css" />
</head>
<body class="no-skin">
<%@include file="/inc/followup/topbar.jsp"%>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/">首页</a>
                    </li>
                    <li>团队报表</li>
                    <li class="active">团队日报</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="row">
                    <form class="form-horizontal">
                        <div class="col-xs-12 col-sm-4 maintop">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                </span>
                                <input type="text" name="date" id="date" class="form-control admin_sea dates" value="${date}" placeholder="点击选择日期范围"/>
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-xs btm-input btn-purple ajax-search-form">
                                        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                        搜索
                                    </button>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>日期</th>
                                <th class="hidden-xs">团队名称</th>
                                <th class="hidden-xs">今日新开户</th>
                                <th class="hidden-xs">入金总额</th>
                                <th class="hidden-xs">
                                    出金总额
                                    <a href="#" data-toggle="tooltip" title="由实际出金计算所得">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th class="hidden-xs">
                                    管理资产规模
                                    <a href="#" data-toggle="tooltip" title="名下所有代理商的总货款之和（从分配时算起）">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th class="hidden-xs">
                                    资产规模净值
                                    <a href="#" data-toggle="tooltip" title="环比历史最高，当前管理资产规模 - 历史最高资产规模">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th class="hidden-xs">
                                    创客管理资产规模
                                    <a href="#" data-toggle="tooltip" title="创客名下所有代理商的协议资金（代理中）之和">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.results}">
                                <tr>
                                    <td colspan="9" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item">
                                    <tr>
                                        <td>${item.date}</td>
                                        <td>${item.deptName}</td>
                                        <td>${item.openAccountNum}</td>
                                        <td>${item.inCashFm}</td>
                                        <td>${item.outCashFm}</td>
                                        <td>${item.manageFundFm}</td>
                                        <td>${item.manageGrowthFundFm}</td>
                                        <td>${item.makerFundFm}</td>
                                        <td>
                                            <a href="./daily/detail?date=${item.date}">
                                                <i class="ace-icon fa fa-external-link"></i> 查看
                                            </a>
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
<%@include file="/inc/followup/script.jsp"%>
<script charset="UTF-8" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", 'daterangepicker'],function(common){
        common.head();
        var date_o = {
            autoUpdateInput: false,
            locale: locale_cn,
        };
        date_o.locale.cancelLabel = "清空";
        $('.dates').daterangepicker(date_o);

        $('.dates').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
        });

        $('.dates').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
    });
</script>
</body>
</html>