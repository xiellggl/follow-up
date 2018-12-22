<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!-- 菜单列表开始 -->
<c:set var="path" value="${param.path}" scope="request" />
<ul class="nav nav-list" id="navSide">
    <%@ include file="item.jsp" %>
</ul>