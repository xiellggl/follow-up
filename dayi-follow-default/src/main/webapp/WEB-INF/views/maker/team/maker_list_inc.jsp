<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<div class="row">
    <form class="form-horizontal" style="max-width: 800px;">
        <div class="clearfix maintop">
            <c:if test="${pageType eq 'my'}">
                <div class="col-xs-4 col-sm-3 btn-sespan">
                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="ace-icon glyphicon glyphicon-phone"></i>
                                        </span>
                        <input type="text" name="mobile" class="form-control admin_sea"
                               value="${param.mobile}" placeholder="客户手机号码"/>
                    </div>
                </div>
            </c:if>
                <div class="col-xs-4 col-sm-3 btn-sespan">
                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="ace-icon fa fa-barcode"></i>
                                        </span>
                        <input type="text" name="inviteCode" class="form-control admin_sea"
                               value="${param.inviteCode}" placeholder="邀请码"/>
                    </div>
                </div>
            <c:if test="${pageType eq 'team'}">
                <div class="col-xs-4 col-sm-3 btn-sespan">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="ace-icon fa fa-check"></i>
                        </span>
                        <input type="text" name="followUp" class="form-control admin_sea"
                               value="${param.followUp}" placeholder="跟进人"/>
                    </div>
                </div>
            </c:if>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="ace-icon fa fa-flag"></i>
                    </span>
                    <select name="orgType" class="form-control admin_sea">
                        <option value="">机构类型</option>
                        <c:forEach items="${orgTypes}" var="item">
                            <option value="${item.value}" <c:if test="${param.orgType eq item.value}">selected</c:if>>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group-btn">
                    <div class="btn-group dropup">
                        <button type="submit" class="btn btn-xs btn-purple">
                            <span class="ace-icon fa fa-search"></span>
                            搜索
                        </button>
                        <button data-toggle="dropdown" class="btn btn-xs btn-info dropdown-toggle hidden visible-xs" aria-expanded="false">
                            <span class="ace-icon fa fa-caret-down icon-only"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="./list">显示全部</a>
                            </li>
                        </ul>
                    </div>
                    <a href="./list" class="btn btn-xs btn-info hidden-xs">
                        <span class="ace-icon fa fa-globe"></span>
                        显示全部
                    </a>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="row">
    <div class="col-xs-12">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>姓名</th>
                <th>会员类型</th>
                <c:if test="${pageType eq 'my'}"><th>手机号</th></c:if>
                <th>年龄</th>
                <th class="hidden-sm hidden-xs">注册时间</th>
                <th>会员期限</th>
                <th>管理代理商数量</th>
                <th>有效代理商数量</th>
                <th>管理资产规模</th>
                <th class="hidden-sm hidden-xs">邀请码</th>
                <c:if test="${pageType eq 'team'}"><th>跟进人</th></c:if>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty page.results}">
                <tr>
                    <td colspan="11" class="no_data">暂无数据</td>
                </tr>
            </c:if>

            <c:if test="${not empty page.results}">
                <c:forEach items="${page.results}" var="item" >
                    <tr>
                            <%-- 名称 --%>
                        <td><c:if test="${not empty item.linkPersonFm}">${item.linkPersonFm}</c:if></td>
                            <%-- 会员类型 --%>
                        <td>${item.orgTypeStr}</td>
                            <%-- 手机号 --%>
                        <c:if test="${pageType eq 'my'}"><td>${item.mobile}</td></c:if>
                            <%-- 年龄 --%>
                        <td>${item.age}</td>
                            <%-- 注册时间 --%>
                        <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <%-- 会员期限 --%>
                        <td>${item.deadLineStr}</td>
                            <%-- 管理代理商 --%>
                        <td>${item.agentNum}</td>
                            <%-- 有效代理商 --%>
                        <td>${item.validAgentNum}</td>
                            <%-- 管理资产规模 --%>
                        <td>${item.manageFundFm}</td>
                            <%-- 邀请码 --%>
                        <td class="hidden-sm hidden-xs">${item.inviteCode}</td>

                        <c:if test="${pageType eq 'team'}"><td>${item.followUp}</td></c:if>
                         <%-- 操作 --%>
                          <td>
                              <a href="./contact?orgId=${item.id}&returnUrl=${returnUrl}" data-toggle="tooltip" title="联系记录"><i class="ace-icon fa fa-comments-o bigger-130"></i></a>
                          </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <c:if test="${not empty page.results}">
            <div class="pagerBar" id="pagerBar">
                <common:page url="${pageUrl}" type="3" />
            </div>
        </c:if>
    </div>
</div>