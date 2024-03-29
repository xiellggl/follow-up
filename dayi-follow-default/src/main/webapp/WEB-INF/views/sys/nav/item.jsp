<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="cur" items="${navMenus}">
    <c:choose>
        <c:when test='${not empty cur.childMenus and fn:length(cur.childMenus) > 0}'>
            <c:set var="hasActive" value="false" />
            <c:forEach var="item" items="${cur.childMenus}">
                <c:if test="${item.url eq path}">
                    <c:set var="hasActive" value="true" />
                </c:if>
            </c:forEach>
            <li ${hasActive?'class="open"':''}>
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa ${cur.cssName}"></i>
                    <span class="menu-text">${cur.name}</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <c:set var="navMenus" value="${cur.childMenus}" scope="request" />
                    <c:import url="/WEB-INF/views/sys/nav/item.jsp" />
                </ul>
            </li>
        </c:when>
        <c:otherwise>
            <li data-path="${fn:replace(cur.url,"/","_")}" ${cur.url eq path ? 'class="active"' : ''}>
                <a href="${cur.url}">
                    <c:choose>
                        <c:when test="${empty cur.parentId}">
                            <i class="menu-icon fa fa-home"></i>
                            <span class="menu-text">${cur.name}</span>
                        </c:when>
                        <c:otherwise>
                            <i class="menu-icon fa fa-caret-right"></i>
                            ${cur.name}
                        </c:otherwise>
                    </c:choose>
                </a>
            </li>
        </c:otherwise>
    </c:choose>
</c:forEach>
