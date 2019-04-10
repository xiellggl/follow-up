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
                <div class="col-xs-12 maintop">所属部门：${user.deptName}</div>
                <div class="col-xs-12 maintop">
                    邀请码：<span style="margin-right: 10px;">${user.inviteCode}</span>
                </div>
                <div class="col-xs-12 maintop">
                    PC邀请链接：
                    <c:choose>
                        <%--上级机构是城市服务商的--%>
                        <c:when test="${user.department.cityServer==1}">
                            <input type="text" id="inviteCode" style="width: 380px" readonly
                                   value="${financeHost}/reg?inviteCode=${user.department.cityInviteCode}&followCode=${user.inviteCode}">
                            <a href="javascript:;" class="copyLink" data-clipboard-target="#inviteCode">复制</a>
                            <br>
                            H5邀请链接：
                            <input type="text" id="inviteCode2" style="width: 380px;margin: 12px 10px 0 0" readonly
                                   value="${financeHost}/m/?inviteCode=${user.department.cityInviteCode}&followCode=${user.inviteCode}">
                            <a href="javascript:;" class="copyLink" data-clipboard-target="#inviteCode2">复制</a>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="inviteCode3" style="width: 380px;margin-right: 10px" readonly
                                   value="${financeHost}/reg?inviteCode=${user.inviteCode}">
                            <a href="javascript:;" class="copyLink" data-clipboard-target="#inviteCode3">复制</a>
                            <br>
                            H5邀请链接：
                            <input type="text" id="inviteCode4" style="width: 380px;margin: 12px 10px 0 0" readonly
                                   value="${financeHost}/m/?inviteCode=${user.inviteCode}">
                            <a href="javascript:;" class="copyLink" data-clipboard-target="#inviteCode4">复制</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            <div class="col-xs-12 maintop">
                角色：${user.rolesName}
            </div>
            <div class="col-xs-12 maintop">创建时间：${user.createTimeFm}</div>
            <div class="col-xs-12 maintop">最后修改时间：${user.updateTimeFm}</div>
        </div>
    </div>
</div>