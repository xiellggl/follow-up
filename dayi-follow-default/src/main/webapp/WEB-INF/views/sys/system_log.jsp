<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>日志管理</title>
    <%@include file="/inc/followup/csslink.jsp"%><link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>

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
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">日志管理</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            日志管理
                        </small>
                    </h1>
                </div>

                <div class="row">
                    <form class="form-horizontal">
                        <div class="clearfix maintop">
                            <div class="col-xs-12 col-sm-3 maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="beginDate" value="${param.beginDate}"
                                           placeholder="开始时间"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 maintop">
                            <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                <input type="text" class="form-control admin_sea dates" name="endDate" value="${param.endDate}"
                                       placeholder="结束时间"/>
                            </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <input type="text" name="operatorName" class="form-control search-query admin_sea" value="${param.operatorName}" placeholder="操作人"/>
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-xs btn-purple">
                                            <span class="ace-icon fa fa-search"></span>
                                            搜索
                                        </button>
                                        <a href="?" class="btn btn-xs btn-info">
                                            <span class="ace-icon fa fa-globe"></span>
                                            显示全部
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" >
                            <thead>
                            <tr>
                                <th>操作人</th>
                                <th>操作模块</th>
                                <th>操作内容</th>
                                <th>操作时间</th>
                                <th>操作IP</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:if test="${empty page}">
                            <tr>
                            <td colspan="8" class="no_data">暂无数据记录</td>
                            </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                            <c:forEach items="${page.results}" var="item" >
                                <tr>
                                    <td>${item.authorName}</td>
                                    <td>${item.what}</td>
                                    <td>${item.note}</td>
                                    <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${item.ip}</td>
                                </tr>
                            </c:forEach>
                            </c:if>

                            </tbody>
                        </table>

                        <%--<c:if test="${not empty page}">--%>
                        <%--<div class="pagerBar" id="pagerBar">--%>
                        <%--<common:page2 url="${pageUrl}" type="3"/>--%>
                        <%--</div>--%>
                        <%--</c:if>--%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<style>
    .calendar-time{display: none;}
</style>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "daterangepicker"], function (common) {
        common.head("system",3);
        var date_o = {
            singleDatePicker: true,
            autoUpdateInput: false,
            timePicker24Hour : true,
            timePicker : true,
            locale: locale_cn,
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