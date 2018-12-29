<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:set var="space" value="　　" />
<c:forEach var="curItem" items="${selectList}" varStatus="vs">
    <option value="${curItem.id}"  ${selectedId eq curItem.id ? 'selected' : ''} >${curItem.name}</option>
</c:forEach>

