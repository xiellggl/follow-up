<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>团队报表-团队月报</title>
    <%@include file="/inc/followup/csslink.jsp" %>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/flexoCalendar/flexoCalendar.css"/>
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
                    <li>团队报表</li>
                    <li class="active">团队月报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            团队月报(${teamMonthVo.month})
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
                            <li ${teamMonthVo.month eq teamMonthVo.thisMonth ? 'class="active"':''}><a href="?date=${teamMonthVo.thisMonth}">本月</a></li>
                            <li ${teamMonthVo.month eq teamMonthVo.lastMonth ? 'class="active"':''}><a href="?date=${teamMonthVo.lastMonth}">上一月</a></li>
                            <c:if test="${teamMonthVo.month ne teamMonthVo.thisMonth and teamMonthVo.month ne teamMonthVo.lastMonth}">
                                <li class="active"><a>${teamMonthVo.month}</a></li>
                            </c:if>
                            <li>
                                <a class="dates" data-toggle="popover" id="showmonthlyPicker">
                                    更多 <i class="ace-icon fa fa-angle-double-right"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>本月新开户</th>
                                <th>创客管理资金净增</th>
                                <th>本月入金总额</th>
                                <th>本月出金总额</th>
                                <th>本月资金净增</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty teamMonthVo.reportVos}">
                                <tr>
                                    <td colspan="8" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty teamMonthVo.reportVos}">
                                <c:forEach items="${teamMonthVo.reportVos}" var="item">
                                <tr>
                                    <td>${item.name}</td>
                                    <td>${item.openAccountNum}</td>
                                    <td>${item.manageGrowthFundFm}</td>
                                    <td>${item.inCashFm}</td>
                                    <td>${item.outCashFm}</td>
                                    <td>${item.growthFundFm}</td>
                                </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/inc/followup/script.jsp" %>
<script>
    seajs.use(["common", "flexoCalendar"], function (common) {
        common.head();
        $("#showmonthlyPicker").popover({
            container:"body",
            placement:"bottom",
            html:true,
            content:'<div id="monthlyContent" style="width: 230px;"></div>'
        }).on("shown.bs.popover",function () {
            $("#monthlyContent").flexoCalendar({
                type:'monthly',
                onselect:function (date) {
                    window.location.href = "?date=" + date;
                }
            });
        });
    });
</script>
</body>
</html>