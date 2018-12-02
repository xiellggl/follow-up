<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="item" items="${topDeptList}" varStatus="vs">
    <%--每一次循环，index+1--%>
    <c:set var="index" value="${index + 1}" scope="request" />
    <tr data-id="${item.id}" data-pid="${item.pid}">
        <td>
            <div style="margin-left:${level*2}em;">${item.name}</div>
        </td>
        <td class="hidden-sm hidden-xs">${item.remark}</td>
        <td>${item.manager.name}</td>
        <td>${item.personNum}</td>
        <td>
            <a href="#"  data-toggle="modal" data-target="#myModalEditFollowuper" data-act="edit" data-toggle="tooltip" title="修改">
                <i class="ace-icon fa fa-pencil bigger-130"></i></a>
            <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                <i class="ace-icon fa fa-trash-o bigger-130 red"></i></a>
        </td>
    </tr>
    <%--如果有下级部门--%>
    <c:if test="${fn:length(item.subDeptList) > 0}">
        <%--循环一次子列表，level+1--%>
        <c:set var="level" value="${level + 1}" scope="request" />
        <%--注意此处，子列表覆盖treeList，在request作用域--%>
        <c:set var="topDeptList" value="${item.subDeptList}" scope="request" />
        <%--这里递归--%>
        <c:import url="dept_item.jsp" />
    </c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->