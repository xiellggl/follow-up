<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>

<div class="row clearfix">
    <c:if test="${not empty flowUp.linkPerson}">
        <div class="col-xs-8 col-sm-5 fl">
            <div style="line-height: 50px;font-size: 16px;margin: 0 0 10px 0;">当前跟进人：${flowUp.linkPerson}</div>
        </div>
    </c:if>
<div class="col-xs-8 col-sm-4 fr">
    <form class="form-horizontal" style="text-align: center;padding:10px 0;">
        <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="ace-icon fa fa-check"></i>
                                </span>
            <input type="text" name="linkPerson" placeholder="跟进人"/>
            <span class="input-group-btn">
                                    <button type="button" class="btn btn-sm btn-info" data-flowid="${flowUp.id}" data-from="from=assign_list" data-act="search">
                                        <span class="ace-icon fa fa-search"></span>
                                        查询
                                    </button>
                                </span>
        </div>
    </form>
</div>
</div>
<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>跟进人</th>
        <th>邀请码</th>
        <th>手机号</th>
        <th>跟进用户数</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty page.items}">
        <tr>
            <td colspan="5" class="no_data">查无跟进人列表</td>
        </tr>
    </c:if>

    <c:if test="${not empty page.items}">
        <c:forEach items="${page.items}" var="item">
            <tr>
                <td>${item.linkPerson}</td>
                <td>${item.inviteCode}</td>
                <td>${item.mobile}</td>
                <td>${item.subAgentList.size() + item.subOrgList.size() }</td>
                <td><a href="javascript:;" data-id="${item.id}" data-act="check" data-toggle="tooltip" title="选择跟进人">
                    <i class="ace-icon fa fa-check-square-o"></i> 选择跟进人
                </a></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<c:if test="${not empty page.items}">
    <div class="pagerBar" id="pagerBar">
        <common:page2 url="${pageUrl}" type="3"/>
    </div>
</c:if>
