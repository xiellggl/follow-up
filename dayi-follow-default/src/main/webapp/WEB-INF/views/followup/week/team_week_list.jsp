<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>团队报表-团队周报</title>
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
                        <a href="/index">首页</a>
                    </li>
                    <li class="active">团队周报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            团队周报(${teamWeekVo.startDate} - ${teamWeekVo.endDate})
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
                            <li ${teamWeekVo.startDate eq teamWeekVo.thisWeekStart ? 'class="active"':''}><a href="?date=${teamWeekVo.thisWeekStart} - ${teamWeekVo.thisWeekEnd}">本周</a></li>
                            <li ${teamWeekVo.startDate eq teamWeekVo.lastWeekStart ? 'class="active"':''}><a href="?date=${teamWeekVo.lastWeekStart} - ${teamWeekVo.lastWeekEnd}">上一周</a></li>
                            <c:if test="${teamWeekVo.startDate ne teamWeekVo.thisWeekStart and teamWeekVo.startDate ne teamWeekVo.lastWeekStart}">
                                <li class="active"><a>${teamWeekVo.startDate} - ${teamWeekVo.endDate}</a></li>
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
                                <th>姓名</th>
                                <th>本周新开户</th>
                                <th>创客管理资金净增</th>
                                <th>本周入金总额</th>
                                <th>本周出金总额</th>
                                <th>本周资金净增</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty teamWeekVo.reportVos}">
                                <tr>
                                    <td colspan="8" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty teamWeekVo.reportVos}">
                                <c:forEach items="${teamWeekVo.reportVos}" var="item">
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