<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>

<div class="page-content">
<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <i class="ace-icon fa fa-book"></i>
            登录日志
        </h4>
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>登录时间</th>
                <th>登录方式</th>
                <th>登录地点</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty page.results}">
                <tr>
                    <td colspan="3" class="no_data">暂无数据</td>
                </tr>
            </c:if>

            <c:if test="${not empty page.results}">
                <c:forEach items="${page.results}" var="item">
                    <tr>
                        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${item.loginTypeStr}</td>
                        <td>${item.address}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <c:if test="${not empty page.results}">
            <div class="pagerBar" id="pagerBar">
                <common:page url="${pageUrl}" type="3"/>
            </div>
        </c:if>
    </div>
</div>
</div>
