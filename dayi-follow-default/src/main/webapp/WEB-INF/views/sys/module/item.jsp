<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="cur" items="${moduleList}" varStatus="vs">
    <%-- 每一次循环，index+1--%>
    <c:set var="index" value="${index + 1}" scope="request" />
    <tr id="${cur.id}" data-pid="${cur.parentId}" data-id="${cur.id}" ${not empty cur.parentId and cur.type eq 1 ? 'style="display:none;"' : ''}>
        <td>
            <c:if test="${fn:length(cur.childMenus) > 0}">
                <a data-act="togglePermissions" data-id="${cur.id}" href="javascript:;" style="cursor:pointer;" class="rule-list">
                    <span class="fa fa-plus blue"></span>
                    <span class="fa fa-minus blue" style="display: none;"></span>
                </a>
            </c:if>
        </td>
        <td><span style="padding-left:${level>0?2*level:0}em;">${cur.name}</span></td>
        <td class="center">
            <c:if test="${cur.type eq 0}">
                <a class="state-btn" data-state="${cur.status}" href="javascript:;" data-id="${cur.id}" title="已${cur.status eq 1 ? '启用':'禁用'}">
                        <span class="btn btn-minier ${cur.status eq 1 ? 'btn-yellow':'btn-danger'}">
                                ${cur.status  eq 1 ? '启用':'禁用'}
                        </span>
                </a>
            </c:if>
        </td>
        <td>${cur.url}</td>
        <td class="hidden-xs">${cur.order}</td>
        <c:if test="${addModulePermission or updateModulePermission or deleteModulePermission or bindModulePermission or untyingModulePermission}">
            <td>
                <c:if test="${cur.type eq 0 and (addModulePermission or updateModulePermission or deleteModulePermission or bindModulePermission)}">
                    <div class="btn-group dropup">
                        <div class="hidden-xs">
                            <c:if test="${addModulePermission}">
                                <a href="#" data-pid="${cur.id}" data-toggle="modal" data-target="#myModalEditModule" data-toggle="tooltip" title="添加子类">
                                    <i class="ace-icon fa fa-plus-circle bigger-130"></i>
                                </a>
                            </c:if>
                            <c:if test="${updateModulePermission}">
                                <a href="#" data-id="${cur.id}" data-toggle="modal" data-target="#myModalEditModule" data-toggle="tooltip" title="修改">
                                    <i class="ace-icon fa fa-pencil bigger-130"></i>
                                </a>
                            </c:if>
                            <c:if test="${deleteModulePermission}">
                                <a href="#" data-act="del" data-toggle="tooltip" title="删除">
                                    <i class="ace-icon fa fa-trash-o bigger-130 red"></i>
                                </a>
                            </c:if>
                            <c:if test="${bindModulePermission}">
                                <a href="/permission/list?bindModuleId=${cur.id}" data-act="bind" data-toggle="tooltip" title="绑定功能">
                                    <i class="ace-icon fa fa-link bigger-130"></i>
                                </a>
                            </c:if>
                        </div>
                        <button data-toggle="dropdown" class="btn btn-xs btn-info dropdown-toggle hidden visible-xs" aria-expanded="false">
                            <span class="ace-icon fa fa-caret-down icon-only"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <c:if test="${addModulePermission}">
                                <li>
                                    <a href="#" data-pid="${cur.id}" data-toggle="modal" data-target="#myModalEditModule">添加子类</a>
                                </li>
                            </c:if>
                            <c:if test="${updateModulePermission}">
                                <li>
                                    <a href="#" data-id="${cur.id}" data-toggle="modal" data-target="#myModalEditModule">修改</a>
                                </li>
                            </c:if>
                            <c:if test="${deleteModulePermission}">
                                <li>
                                    <a href="#" data-act="del">删除</a>
                                </li>
                            </c:if>
                            <c:if test="${bindModulePermission}">
                                <li>
                                    <a href="/permission/list?bindModuleId=${cur.id}" data-act="bind">绑定功能</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${cur.type eq 1 and untyingModulePermission}">
                    <a href="#" data-act="unbind" data-toggle="tooltip" title="解绑功能">
                        <i class="ace-icon fa fa-unlink bigger-130 red"></i>
                    </a>
                </c:if>
            </td>
        </c:if>
    </tr>

    <%--如果有childen--%>
    <c:if test="${fn:length(cur.childMenus) > 0}">
        <%--循环一次子列表，level+1--%>
        <c:set var="level" value="${level + 1}" scope="request" />
        <%--注意此处，子列表覆盖treeList，在request作用域--%>
        <c:set var="moduleList" value="${cur.childMenus}" scope="request" />
        <%--递归--%>
        <c:import url="item.jsp" />
    </c:if>
</c:forEach>
<%--退出时，level-1--%>
<c:set var="level" value="${level - 1}" scope="request" />