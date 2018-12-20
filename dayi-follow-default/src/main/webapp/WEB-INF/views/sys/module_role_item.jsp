<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<c:forEach var="cur" items="${moduleList}">
     <c:choose>
         <c:when test='${not empty cur.childMenus and fn:length(cur.childMenus) > 0}'>
             <li>
                 <div class="tit">
                     <label><input data-rel="menu" type="checkbox" value="" /> ${cur.name}</label>
                 </div>
                 <ul class="${cur.childMenus[0].type eq 1 ? 'last clearfix' : ''}">
                     <c:set var="moduleList" value="${cur.childMenus}" scope="request" />
                     <c:import url="module_role_item.jsp" />
                 </ul>
             </li>
         </c:when>
         <c:otherwise>
             <c:if test="${cur.type eq 0}">
                 <li>
                     <div class="tit">
                         <label><input type="checkbox" value="" disabled /> ${cur.name}</label>
                     </div>
                 </li>
             </c:if>
             <c:if test="${cur.type eq 1}">
                 <li class="option-item">
                     <label>
                         <c:set var="checked" value="false" scope="request" />
                         <c:forEach var="permission" items="${role.permissionList}">
                             <c:if test="${permission.id eq cur.id}">
                                 <c:set var="checked" value="true" />
                             </c:if>
                         </c:forEach>
                         <input type="checkbox" data-rel="permission" name="permissionId" value="${cur.id}" ${checked?'checked':''} /> ${cur.name}
                     </label>
                 </li>
             </c:if>
         </c:otherwise>
     </c:choose>
</c:forEach>
