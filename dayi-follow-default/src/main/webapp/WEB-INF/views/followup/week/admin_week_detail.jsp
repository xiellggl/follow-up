<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>管理员报表-管理员周报详情</title>
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
                    <li class="active">管理员周报详情</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            管理员周报详情(${date})
                        </small>
                    </h1>
                    <div class="pull-right">
                        <a href="./export?date=${date}" class="btn btn-xs btn-danger">
                            <span class="ace-icon glyphicon glyphicon-export"></span>
                            一键导出
                        </a>
                    </div>
                </div>
                <%--<div class="row">--%>
                    <%--<div class="col-xs-12">--%>
                        <%--<ul class="nav nav-tabs">--%>
                            <%--<li ${adminWeekVo.startDate eq adminWeekVo.thisWeekStart ? 'class="active"':''}><a href="?date=${adminWeekVo.thisWeekStart} - ${adminWeekVo.thisWeekEnd}">本周</a></li>--%>
                            <%--<li ${adminWeekVo.startDate eq adminWeekVo.lastWeekStart ? 'class="active"':''}><a href="?date=${adminWeekVo.lastWeekStart} - ${adminWeekVo.lastWeekEnd}">上一周</a></li>--%>
                            <%--<c:if test="${adminWeekVo.startDate ne adminWeekVo.thisWeekStart and adminWeekVo.startDate ne adminWeekVo.lastWeekStart}">--%>
                                <%--<li class="active"><a>${adminWeekVo.startDate} - ${adminWeekVo.endDate}</a></li>--%>
                            <%--</c:if>--%>
                            <%--<li>--%>
                                <%--<a class="dates" data-toggle="popover" id="showWeeklyPicker">--%>
                                    <%--更多 <i class="ace-icon fa fa-angle-double-right"></i>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th></th>
                                <th>日期</th>
                                <th>团队名称</th>
                                <th>本周新开户</th>
                                <th>新签创客</th>
                                <th>入金总额</th>
                                <th>出金总额</th>
                                <th>管理资产规模</th>
                                <th>
                                    资产规模净值
                                    <a href="#" data-toggle="tooltip" title="环比历史最高，当前管理资产规模 - 历史最高资产规模">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                                <th>
                                    创客管理资产规模
                                    <a href="#" data-toggle="tooltip" title="创客名下所有代理商的协议资金之和">
                                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty list}">
                                <tr>
                                    <td colspan="10" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty list}">
                                <c:forEach items="${list}" var="item">
                                    <tr>
                                        <td class="collapse_row" data-tag="${item.date}-${item.deptId}">
                                            <span class="detail-icon">
                                                <i class="fa fa-plus blue"></i>
                                            </span>
                                        </td>
                                        <td>${item.date}</td>
                                        <td>${item.deptName}</td>
                                        <td>${item.openAccountNum}</td>
                                        <td>${item.signOrgNum}</td>
                                        <td>${item.inCash}</td>
                                        <td>${item.outCash}</td>
                                        <td>${item.manageFund}</td>
                                        <td>${item.manageGrowthFund}</td>
                                        <td>${item.makerFund}</td>
                                    </tr>
                                    <c:forEach items="${item.pList}" var="row">
                                        <tr class="${row.date}-${row.deptId}" style="display: none;">
                                            <td></td>
                                            <td>${row.date}</td>
                                            <td>${row.name}</td>
                                            <td>${row.openAccountNum}</td>
                                            <td>${row.signOrgNum}</td>
                                            <td>${row.inCash}</td>
                                            <td>${row.outCash}</td>
                                            <td>${row.manageFund}</td>
                                            <td>${row.manageGrowthFund}</td>
                                            <td>${row.makerFund}</td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty list}">
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

        $(".collapse_row").on("click",function (e) {
            var parentTag = $(this).attr("data-tag");

            if($(this).find('i.fa').hasClass('fa-plus')){
                //收起状态 +
                $(this).find('i.fa').removeClass('fa-plus').addClass(' fa-minus');
                $(this).parents('tr').siblings('.' + parentTag).show();  //点击 + 展开数据
            } else {
                //展开状态
                $(this).find('i.fa').addClass(' fa-plus').removeClass(' fa-minus');
                $(this).parents('tr').siblings('.' + parentTag).hide();
            }
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