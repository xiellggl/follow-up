<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>

<div class="page-content">
<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <i class="ace-icon fa fa-book"></i>
            联系记录
        </h4>
        <table class="table table-striped table-bordered table-hover" id="list">
            <thead>
            <tr>
                <th>联系时间</th>
                <th>联系人</th>
                <th>联系方式</th>
                <th>客户意向度</th>
                <th>下次联系时间</th>
                <th>客户类型</th>
                <th>沟通内容</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty page.results}">
                <tr>
                    <td colspan="7" class="no_data">暂无数据</td>
                </tr>
            </c:if>

            <c:if test="${not empty page.results}">
                <c:forEach items="${page.results}" var="item" >
                    <tr>
                        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${item.followUpName}</td>
                        <td>${item.contactTypeStr}</td>
                        <td>${item.customerIntentionTypeStr}</td>
                        <td><fmt:formatDate value="${item.nextContactTime}" pattern="yyyy-MM-dd"/></td>
                        <td>${item.customerTypeStr}</td>
                        <td>${item.content}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <c:if test="${not empty page.results}">
            <div class="pagerBar" id="pagerBar">
                <common:page url="${pageUrl}" type="3" />
            </div>
        </c:if>
    </div>
</div>
</div>
