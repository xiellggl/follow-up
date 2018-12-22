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
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li>团队报表</li>
                    <li class="active">团队周报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            团队周报(${startDate} - ${endDate})
                        </small>
                    </h1>
                    <div class="pull-right">
                        <a href="./export?date=${startDate} - ${endDate}" class="btn btn-xs btn-danger">
                            <span class="ace-icon glyphicon glyphicon-export"></span>
                            一键导出
                        </a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
                            <li ${startDate eq thisWeekStart ? 'class="active"':''}><a href="?date=${thisWeekStart} - ${thisWeekEnd}">本周</a></li>
                            <li ${startDate eq lastWeekStart ? 'class="active"':''}><a href="?date=${lastWeekStart} - ${lastWeekEnd}">上一周</a></li>
                            <c:if test="${startDate ne thisWeekStart and startDate ne lastWeekStart}">
                                <li class="active"><a>${startDate} - ${endDate}</a></li>
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
                                <th>团队名称</th>
                                <th>姓名</th>
                                <th>类别</th>
                                <th>周一</th>
                                <th>周二</th>
                                <th>周三</th>
                                <th>周四</th>
                                <th>周五</th>
                                <th>本周完成新开户</th>
                                <th>本周完成入金总额</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.items}">
                                <tr>
                                    <td colspan="10" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.items}">
                                <c:forEach items="${page.items}" var="item">
                                    <tr>
                                        <td rowspan="2">${item.deptName}</td>
                                        <td rowspan="2">${item.followUpName}</td>
                                        <td style="text-align: right;">有效新开户</td>
                                        <td>${item.monOpenAccountNum}</td>
                                        <td>${item.tueOpenAccountNum}</td>
                                        <td>${item.wedOpenAccountNum}</td>
                                        <td>${item.thuOpenAccountNum}</td>
                                        <td>${item.friOpenAccountNum}</td>
                                        <td rowspan="2">${item.weekOpenAccountNum}</td>
                                        <td rowspan="2">${item.weekInCash}</td>
                                    </tr>
                                    <tr style="text-align: right; color: orangered;">
                                        <td>入金总额</td>
                                        <td>${item.monInCash}</td>
                                        <td>${item.tueInCash}</td>
                                        <td>${item.wedInCash}</td>
                                        <td>${item.thuInCash}</td>
                                        <td>${item.friInCash}</td>
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