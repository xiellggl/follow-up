<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="item" items="${deptList}" varStatus="vs">
    <%--每一次循环，index+1--%>
    <c:set var="index" value="${index + 1}" scope="request" />
        <option value="${item.id}" ${deptVo.pid eq item.id ? 'selected' : ''}> ${item.treeName} </option>
    <%--如果有下级部门--%>
    <c:if test="${fn:length(item.subDeptList) > 0}">
        <%--循环一次子列表，level+1--%>
        <c:set var="level" value="${level + 1}" scope="request" />
        <%--注意此处，子列表覆盖treeList，在request作用域--%>
        <c:set var="deptList" value="${item.subDeptList}" scope="request" />
        <%--这里递归--%>
        <c:import url="dept_select_item.jsp" />
    </c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->