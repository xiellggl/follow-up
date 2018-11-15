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
            <div class="col-xs-12 maintop">账号：${followUp.userName}</div>
            <div class="col-xs-12 maintop">姓名：${followUp.linkPerson}</div>

            <c:if test="${followUp.userName ne 'admin' && followUp.isAdmin ne 1}"> <%-- 当不为 超级管理员 或 部门管理员 时 --%>
                <div class="col-xs-12 maintop">所属部门：${followUp.deptName}</div>
                <div class="col-xs-12 maintop">
                    邀请码：<span style="margin-right: 10px;">${followUp.inviteCode}</span>
                </div>
                <c:if test="${not empty followUp.followDept}">

                    <div class="col-xs-12 maintop">
                        邀请链接：
                        <c:choose>
                            <%--上级机构是城市服务商的--%>
                            <c:when test="${followUp.followDept.cityServer==1}">
                                <input type="text" id="inviteCode"
                                       value="${financeHost}/finance/reg?inviteCode=${followUp.followDept.cityInviteCode}&followCode=${followUp.inviteCode}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" id="inviteCode"
                                       value="${financeHost}/finance/reg?inviteCode=${followUp.inviteCode}">
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:;" id="copyLink" data-clipboard-target="#inviteCode">复制</a>
                    </div>
                </c:if>
            </c:if>
            <div class="col-xs-12 maintop">
                权限：
                <c:choose>
                    <c:when test="${followUp.userName eq 'admin'}">
                        超级管理员
                    </c:when>
                    <c:when test="${followUp.isAdmin eq 1}">
                        管理员
                    </c:when>
                    <c:when test="${followUp.isManager eq 1}">
                        团队负责人
                    </c:when>
                    <c:otherwise>
                        销售员
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-xs-12 maintop">创建时间：${createDateStrSub}</div>
            <div class="col-xs-12 maintop">最后修改时间：${modifyDateStrSub}</div>
        </div>
    </div>
</div>