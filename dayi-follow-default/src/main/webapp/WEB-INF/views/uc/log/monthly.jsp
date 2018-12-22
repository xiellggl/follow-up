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
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">团队月报</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            团队月报(${date})
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
                            <li ${date eq thisMonth ? 'class="active"':''}><a href="?date=${thisMonth}">本月</a></li>
                            <li ${date eq lastMonth ? 'class="active"':''}><a href="?date=${lastMonth}">上一月</a></li>
                            <c:if test="${date ne thisMonth and date ne lastMonth}">
                                <li class="active"><a>${date}</a></li>
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
                                <th>${flowUpVo.deptName eq 'KA及渠道部' ? '创客管理资金净增' : '本月新开户'}</th>
                                <th>本月入金总额</th>
                                <th>本月出金总额</th>
                                <th>本月资金净增</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.items}">
                                <tr>
                                    <td colspan="8" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty page.items}">
                                <c:forEach items="${page.items}" var="item">
                                    <tr>
                                        <td>${item.followUpName}</td>
                                        <td>${flowUpVo.deptName eq 'KA及渠道部' ? item.manageAssetGrowthFormat : item.openAccountNum }</td>
                                        <td>${item.inCashFormat}</td>
                                        <td>${item.outCashFormat}</td>
                                        <td>${item.moneyGrowthFm}</td>
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