<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">个人信息</h4>
        </div>
        <div class="modal-body clearfix">
            <div class="col-xs-12 maintop">账号：${user.userName}</div>
            <div class="col-xs-12 maintop">姓名：${user.name}</div>

            <c:if test="${user.userName ne 'admin' && user.isAdmin ne 1}"> <%-- 当不为 超级管理员 或 部门管理员 时 --%>
                <div class="col-xs-12 maintop">所属部门：${user.deptName}</div>
                <div class="col-xs-12 maintop">
                    邀请码：<span style="margin-right: 10px;">${user.inviteCode}</span>
                </div>
                <c:if test="${not empty user.department}">

                    <div class="col-xs-12 maintop">
                        邀请链接：
                        <c:choose>
                            <%--上级机构是城市服务商的--%>
                            <c:when test="${user.department.cityServer==1}">
                                <input type="text" id="inviteCode"
                                       value="${financeHost}/finance/reg?inviteCode=${user.department.cityInviteCode}&followCode=${user.inviteCode}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="inviteCode"
                                       value="${financeHost}/finance/reg?inviteCode=${user.inviteCode}">
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:;" id="copyLink" data-clipboard-target="#inviteCode">复制</a>
                    </div>
                </c:if>
            </c:if>
            <div class="col-xs-12 maintop">
                权限：
                <c:choose>
                    <c:when test="${user.userName eq 'admin'}">
                        超级管理员
                    </c:when>
                    <c:when test="${user.isAdmin eq 1}">
                        管理员
                    </c:when>
                    <c:when test="${user.isManager eq 1}">
                        团队负责人
                    </c:when>
                    <c:otherwise>
                        销售员
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-xs-12 maintop">创建时间：${user.createTimeFm}</div>
            <div class="col-xs-12 maintop">最后修改时间：${user.updateTimeFm}</div>
        </div>
    </div>
</div>