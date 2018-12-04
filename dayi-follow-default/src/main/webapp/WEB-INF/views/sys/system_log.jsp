<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>日志管理</title>
    <c:set var="pageName" value="financeUcOrgPage" />
    <%@include file="/inc/followup/csslink.jsp"%>
</head>
<body>
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
                                    <input type="text" class="form-control admin_sea dates" name="date" value="${param.date}"
                                           placeholder="日志日期"/>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 maintop">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <input type="text" name="deptName" class="form-control search-query admin_sea" value="${param.deptName}" placeholder="操作人"/>
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

                            <c:if test="${empty page.items}">
                            <tr>
                                <td colspan="8" class="no_data">暂无数据记录</td>
                            </tr>
                            </c:if>

                            <c:forEach var="i" begin="1" end="5">
                            <tr>
                                <td>老陈</td>
                                <td>首页信息</td>
                                <td>登录账号</td>
                                <td>2016-02-20 14:44:43</td>
                                <td>172.28.13.41</td>
                            </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <c:if test="${not empty page}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page2 url="${pageUrl}" type="3"/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){

    });
</script>
</body>
</html>