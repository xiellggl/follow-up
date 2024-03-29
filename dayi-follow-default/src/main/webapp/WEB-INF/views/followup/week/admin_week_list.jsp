<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>管理员报表-管理员周报</title>
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
                    <li class="active">管理员周报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            管理员周报(${weekVo.startDate} - ${weekVo.endDate})
                        </small>
                    </h1>
                    <div class="pull-right">
                        <a href="./week/export?date=${weekVo.startDate} - ${weekVo.endDate}" class="btn btn-xs btn-danger">
                            <span class="ace-icon glyphicon glyphicon-export"></span>
                            一键导出
                        </a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
                            <li ${weekVo.startDate eq weekVo.thisWeekStart ? 'class="active"':''}><a href="?date=${weekVo.thisWeekStart} - ${weekVo.thisWeekEnd}">本周</a></li>
                            <li ${weekVo.startDate eq weekVo.lastWeekStart ? 'class="active"':''}><a href="?date=${weekVo.lastWeekStart} - ${weekVo.lastWeekEnd}">上一周</a></li>
                            <c:if test="${weekVo.startDate ne weekVo.thisWeekStart and weekVo.startDate ne weekVo.lastWeekStart}">
                                <li class="active"><a>${weekVo.startDate} - ${weekVo.endDate}</a></li>
                            </c:if>
                            <li>
                                <a class="dates" data-toggle="popover" id="showWeeklyPicker">
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
                                <th>本周入金</th>
                                <th>本周出金</th>
                                <th>
                                    本周净增
                                    <a href="#" data-toggle="tooltip" title="本周入金 - 本周出金">
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
                            <c:if test="${empty weekVo.SRSumList}">
                                <tr>
                                    <td colspan="7" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty weekVo.SRSumList}">
                                <c:forEach items="${weekVo.SRSumList}" var="item">
                                    <tr>
                                        <td>${item.typeStr}</td>
                                        <td>${item.inCash}</td>
                                        <td>${item.outCash}</td>
                                        <td>${item.growthFund}</td>
                                        <td>${item.manageFund}</td>
                                        <td>${item.manageGrowthFund}</td>
                                        <td>
                                            <c:if test="${item.type eq 1}">
                                            <a href="./week/detail?date=${weekVo.startDate} - ${weekVo.endDate}">
                                                查看详情
                                            </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty weekVo.SRSumList}">
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
        $("#showWeeklyPicker").popover({
            container:"body",
            placement:"bottom",
            html:true,
            content:'<div id="weeklyContent" style="width: 230px;"></div>'
        }).on("shown.bs.popover",function () {
            $("#weeklyContent").flexoCalendar({
                type:'weekly',
                //allowDate:['2018-01','2018-08'],
                onselect:function (date) {
                    var dateArr = date.split(",");
                    var endDate = new Date(dateArr[0]).getTime() + 1000*60*60*24*4;
                    window.location.href = "?date=" + dateArr[0] + " - " + common.dateFormat(endDate,"yyyy-MM-dd");
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