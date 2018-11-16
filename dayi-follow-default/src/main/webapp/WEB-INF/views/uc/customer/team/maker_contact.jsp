<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>客户-创客</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <style>
        .customer-info tr th{text-align: right;}
    </style>
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
                        <a href="/followup/uc/index">首页</a>
                    </li>
                    <li>
                        <a href="./list">团队客户-创客</a>
                    </li>
                    <li class="active">联系记录</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                    <%--<div class=" clearfix">--%>
                        <h4 class="header smaller lighter blue">
                            <i class="ace-icon fa fa-book"></i>
                            联系记录（客户：${orgVo.linkPersonStr}）
                            <a href="/followup/uc/customer/team/maker/list?${returnUrl}" style="float: right;margin: -10px 10px 0 0;" class="btn btn-sm btn-info" type="reset">返回</a>
                        </h4>

                    <%--</div>--%>
                        <table class="table table-striped table-bordered table-hover" id="list">
                            <thead>
                            <tr>
                                <th>联系时间</th>
                                <th>联系人</th>
                                <th>联系方式</th>
                                <th>沟通内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.items}">
                                <tr>
                                    <td colspan="7" class="no_data">暂无数据</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty page.items}">
                                <c:forEach items="${page.items}" var="item" >
                                    <tr>
                                        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${item.followUpName}</td>
                                        <td>${item.contactTypeStr}</td>
                                        <td>${item.content}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty page.items}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page2 url="${pageUrl}" type="3" />
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script type="text/html" id="tplContactItem">
    <tr>
        <td>{{createDate | dateFormat:'yyyy-MM-dd hh:mm:ss'}}</td>
        <td>{{followUpName}}</td>
        <td>{{contactTypeStr}}</td>
        <td>{{content}}</td>
    </tr>
</script>
<script>
    seajs.use(["common", "template", "validate", "addMethod"], function (common, template) {
        common.head("teamCustomer",2);
        template.helper("dateFormat",common.dateFormat);
    });
</script>
</body>
</html>