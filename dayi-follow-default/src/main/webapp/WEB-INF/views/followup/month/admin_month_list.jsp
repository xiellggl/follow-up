<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>管理员报表-管理员月报</title>
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
                    <li>管理员报表</li>
                    <li class="active">管理员月报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            管理员月报(${monthVo.month})
                        </small>
                    </h1>
                    <div class="pull-right">
                        <a href="./month/export?date=${monthVo.month}" class="btn btn-xs btn-danger">
                            <span class="ace-icon glyphicon glyphicon-export"></span>
                            一键导出
                        </a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
                            <li ${monthVo.month eq monthVo.thisMonth ? 'class="active"':''}><a href="?date=${monthVo.thisMonth}">本月</a></li>
                            <li ${monthVo.month eq monthVo.lastMonth ? 'class="active"':''}><a href="?date=${monthVo.lastMonth}">上一月</a></li>
                            <c:if test="${monthVo.month ne monthVo.thisMonth and monthVo.month ne monthVo.lastMonth}">
                                <li class="active"><a>${monthVo.month}</a></li>
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
                                <th>客户来源</th>
                                <th>本月入金</th>
                                <th>本月出金</th>
                                <th>
                                    本月净增
                                    <a href="#" data-toggle="tooltip" title="本月入金 - 本月出金">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>
                                    管理资产规模
                                    <a href="#" data-toggle="tooltip" title="名下所有代理商的总货款之和 （从分配时算起）">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>
                                    资产管理规模净值
                                    <a href="#" data-toggle="tooltip" title="环比上一个月份，当前管理资产规模 - 上个月的资产规模">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty monthVo.SRSumList}">
                                <tr>
                                    <td colspan="7" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty monthVo.SRSumList}">
                                <c:forEach items="${monthVo.SRSumList}" var="item">
                                    <tr>
                                        <td>${item.typeStr}</td>
                                        <td>${item.inCash}</td>
                                        <td>${item.outCash}</td>
                                        <td>${item.growthFund}</td>
                                        <td>${item.manageFund}</td>
                                        <td>${item.manageGrowthFund}</td>
                                        <td>
                                            <c:if test="${item.type eq 1}">
                                            <a href="./month/detail?date=${monthVo.month}">
                                                查看详情
                                            </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty monthVo.SRSumList}">
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


        //给Body加一个Click监听事件
        /*$('body').on('click', function(event) {
            var target = $(event.target);
            if (!target.hasClass('popover') //弹窗内部点击不关闭
                && target.parent('.popover-content').length === 0
                && target.parent('.popover-title').length === 0
                && target.parent('.popover').length === 0
                && target.parent('.flexoCalendar').length === 0
                && target.data("toggle") !== "popover") {
                //弹窗触发列不关闭，否则显示后隐藏
                $('[data-toggle="popover"]').popover('hide');
            }
            return false;
        });*/


    });
</script>
</body>
</html>