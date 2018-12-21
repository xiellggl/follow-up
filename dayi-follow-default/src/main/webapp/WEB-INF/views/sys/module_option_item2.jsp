<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="curItem" items="${selectList}" varStatus="vs">
    <%-- 每一次循环，index+1--%>
    <c:set var="index" value="${index + 1}" scope="request" />
    <%--记录路径--%>
    <c:set var="paths" value="${paths}${empty paths ? '' : '>'}${curItem.name}" scope="request" />
    <option value="${curItem.id}" data-paths="${paths}" ${selectedId eq curItem.id ? 'selected' : ''} ${fn:length(curItem.childMenus) > 0 ? "disabled" : ""}>
        <c:if test="${not empty level2 and level2 > 0}">
            <c:forEach var="x" begin="1" end="${level2}" step="1">
                ${space}|
            </c:forEach>
            --
        </c:if>
            ${curItem.name}
    </option>

    <%--如果有childen--%>
    <c:if test="${fn:length(curItem.childMenus) > 0}">
        <%--循环一次子列表，level+1--%>
        <c:set var="level2" value="${level2 + 1}" scope="request" />
        <%--注意此处，子列表覆盖selectList，在request作用域--%>
        <c:set var="selectList" value="${curItem.childMenus}" scope="request" />
        <%--递归--%>
        <c:import url="module_option_item.jsp" />
    </c:if>
    <c:set var="paths" value="" scope="request" />
</c:forEach>
<%--退出时，level-1--%>
<c:set var="level2" value="${level2 - 1}" scope="request" />

<%--
<c:forEach var="curItem" items="${selectList}" varStatus="vs">
    <c:choose>
        <c:when test='${not empty curItem.childMenus and fn:length(curItem.childMenus) > 0}'>

            <optgroup label="${curItem.name}">
                <c:set var="selectList" value="${curItem.childMenus}" scope="request" />
                <c:import url="module_option_item2.jsp" />
            </optgroup>
        </c:when>
        <c:otherwise>
            <option value="${curItem.id}" ${selectedId eq curItem.id ? 'selected' : ''} >${curItem.name}</option>
        </c:otherwise>
    </c:choose>
</c:forEach>--%>
