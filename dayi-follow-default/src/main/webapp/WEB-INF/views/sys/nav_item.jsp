<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="cur" items="${navMenus}">
    <c:choose>
        <c:when test='${not empty cur.childMenus and fn:length(cur.childMenus) > 0}'>
            <li>
                <a href="javascript:void(0);" class="dropdown-toggle">
                    <i class="menu-icon fa fa-users"></i>
                    <span class="menu-text">${cur.name}</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <c:set var="navMenus" value="${cur.childMenus}" scope="request" />
                    <c:import url="/WEB-INF/views/sys/nav_item.jsp" />
                </ul>
            </li>
        </c:when>
        <c:otherwise>
            <li>
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
