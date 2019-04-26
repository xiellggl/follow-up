<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<style>
    .relation-info-div{ position: relative;}
    .relation-info{
        display: none;
        width: 400px;
        background-color: #000; color: #fff; position: absolute;bottom: 0; left: -400px;z-index: 1;
    }
    .info-lin{
        line-height: 35px;
        font-size: 14px;
    }
    .relation-info-div:hover .relation-info{display: block;}
</style>
<c:if test="${empty param.waitToLinkToday}">
<div class="row">
    <form class="form-horizontal search-form" style="max-width: 1100px;">
        <div class="clearfix maintop">
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                    <select name="idCardValidate">
                        <option value="">实名状态</option>
                        <option value="1"  ${param.idCardValidate=='1'?"selected":''}>已认证</option>
                        <option value="0"  ${param.idCardValidate=='0'?"selected":''}>未认证</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-credit-card"></i>
                                    </span>
                    <select name="bankSign">
                        <option value="">绑卡状态</option>
                        <option value="1"  ${param.bankSign=='1'?"selected":''}>已绑定</option>
                        <option value="0"  ${param.bankSign=='0'?"selected":''}>未绑定</option>
                    </select>

                </div>
            </div>
            <div class="col-xs-4 col-sm-6 hidden-xs bank-item">
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="ace-icon glyphicon glyphicon-credit-card"></i>
                    </span>
                    <select name="bankType" id="bankType" multiple="multiple" style="height: 30px;">
                        <c:forEach items="${bankTypes}" var="item">
                            <c:set var="selected" value="" />
                            <c:forEach items="${bankTypesArr}" var="type">
                                <c:if test="${type eq item.key and empty selected}">
                                    <c:set var="selected" value="selected" />
                                </c:if>
                            </c:forEach>
                            <option value="${item.key}" ${selected}>${item.cname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="clearfix maintop">
            <div class="col-sm-3 hidden-xs btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-yen"></i>
                                    </span>
                    <select name="totalFound">
                        <option value="">客户总货款</option>
                        <option value="1" ${param.totalFound eq 1 ? 'selected' : ''}>货款为零</option>
                        <option value="2" ${param.totalFound eq 2 ? 'selected' : ''}>2W以下</option>
                        <option value="3" ${param.totalFound eq 3 ? 'selected' : ''}>2W~10W</option>
                        <option value="4" ${param.totalFound eq 4 ? 'selected' : ''}>10W~50W</option>
                        <option value="5" ${param.totalFound eq 5 ? 'selected' : ''}>50W~100W</option>
                        <option value="6" ${param.totalFound eq 6 ? 'selected' : ''}>100W~300W</option>
                        <option value="7" ${param.totalFound eq 7 ? 'selected' : ''}>300W以上</option>
                    </select>
                </div>
            </div>

            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check-square-o"></i>
                                    </span>
                    <select name="inCash">
                        <option value="">入金状态</option>
                        <option value="1" ${param.inCash=='1'?"selected":''}>已入金</option>
                        <option value="0" ${param.inCash=='0'?"selected":''}>未入金</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-log-in"></i>
                                    </span>
                    <select name="todayInCash">
                        <option value="">当日入金</option>
                        <option value="1" ${param.todayInCash=='1'?"selected":''}>当日有入金</option>
                        <option value="0" ${param.todayInCash=='0'?"selected":''}>当日无入金</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-log-out"></i>
                                    </span>
                    <select name="todayOutCash">
                        <option value="">当日出金</option>
                        <option value="1" ${param.todayOutCash=='1'?"selected":''}>当日有出金</option>
                        <option value="0" ${param.todayOutCash=='0'?"selected":''}>当日无出金</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="clearfix maintop">
            <div class="col-sm-3 hidden-xs btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-user"></i>
                                    </span>
                    <select name="customerType">
                        <option value="">客户类型</option>
                        <c:forEach items="${customerTypes}" var="item">
                            <option value="${item.value}" <c:if test="${param.customerType eq item.value}">selected</c:if>>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                    <input type="text" name="mobile" class="form-control admin_sea"
                           value="${param.mobile}" placeholder="客户手机号码"/>
                </div>
            </div>
            <div class="col-xs-4 col-sm-3 btn-sespan">
                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-barcode"></i>
                                    </span>
                    <input type="text" name="inviteCode" class="form-control admin_sea"
                           value="${param.inviteCode}" placeholder="邀请码"/>
                </div>
            </div>
            <c:if test="${pageType eq 'my'}">
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
            </c:if>
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
        </div>
        <c:if test="${pageType eq 'team'}">
            <div class="clearfix maintop" style="text-align: center;">
                <div class="input-group-btn">
                    <button type="submit" class="btn btn-xs btn-purple">
                        <span class="ace-icon fa fa-search"></span>
                        搜索
                    </button>
                    <a href="./list" class="btn btn-xs btn-info">
                        <span class="ace-icon fa fa-globe"></span>
                        显示全部
                    </a>
                </div>
            </div>
        </c:if>
    </form>
</div>
</c:if>
<div class="row">
    <div class="col-xs-12">
        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th>姓名</th>
                <th class="hidden-sm hidden-xs">注册时间</th>
                <th>手机号</th>
                <th>实名认证</th>
                <th>绑卡</th>
                <th>入金</th>
                <th>当日累计入金</th>
                <th>当日申请出金</th>
                <th class="hidden-sm hidden-xs">总货款</th>
                <th class="hidden-sm hidden-xs">最近代理<i class="glyphicon glyphicon-question-sign" data-toggle="tooltip"
                                                       title="最近一天累计代理金额" style="color:#aaa;"></i></th>
                <th class="hidden-sm hidden-xs">客户类型</th>
                <th class="hidden-sm hidden-xs">最后登录</th>
                <th class="hidden-sm hidden-xs">当前结算银行</th>
                <th class="hidden-sm hidden-xs">最近联系时间</th>
                <c:if test="${pageType eq 'team'}"><th>跟进人</th></c:if>
                <th class="hidden-sm hidden-xs">邀请码</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty page.results}">
                <tr>
                    <td colspan="20" class="no_data">暂无数据</td>
                </tr>
            </c:if>

            <c:if test="${not empty page.results}">
                <c:forEach items="${page.results}" var="item" >
                    <tr>
                            <%-- 名称 --%>
                        <td><c:if test="${not empty item.linkPersonFm}">${item.linkPersonFm}</c:if></td>
                            <%-- 注册时间 --%>
                        <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <%-- 手机号 --%>
                        <td>
                            <c:choose>
                                <c:when test="${pageType eq 'team'}">
                                    ${fn:substring(item.mobile, 0, 3)}****${fn:substring(item.mobile, 7, 11)}
                                </c:when>
                                <c:otherwise>
                                    ${item.mobile}
                                </c:otherwise>
                            </c:choose>
                        </td>
                            <%-- 实名认证 --%>
                        <td>
                            <c:choose>
                                <c:when test="${!empty item.idCard }">
                                <span class="green" data-toggle="tooltip"
                                      title="认证时间：<fmt:formatDate value='${item.cardValidDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <i class="ace-icon fa fa-check"></i>
                                </span>
                                </c:when>
                                <c:otherwise>
                                    <i class="ace-icon fa fa-times red"></i>
                                </c:otherwise>
                            </c:choose>


                        </td>
                            <%-- 是否绑卡 --%>
                        <td>
                            <c:choose>
                                <c:when test="${item.bankSign eq true}">
                                <span class="green" data-toggle="tooltip"
                                      title="绑卡时间：<fmt:formatDate value='${item.bankSignDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <i class="ace-icon fa fa-check"></i>
                                </span>
                                </c:when>
                                <c:otherwise>
                                    <i class="ace-icon fa fa-times red"></i>
                                </c:otherwise>
                            </c:choose>

                        </td>
                            <%-- 是否入金 --%>
                        <td>
                            <c:choose>
                                <c:when test="${not empty item.fristInCashDate and item.inCash > 0}">
                                <span class="green" data-toggle="tooltip"
                                      title="首次入金时间：<fmt:formatDate value='${item.fristInCashDate}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                                    <i class="ace-icon fa fa-check"></i>
                                </span>
                                </c:when>
                                <c:otherwise>
                                    <i class="ace-icon fa fa-times red"></i>
                                </c:otherwise>
                            </c:choose>

                        </td>
                            <%-- 当日累计入金 --%>
                        <td>${item.dayInCashFm}
                            <c:if test="${item.dayLastInCashTimeFm ne null}">/${item.dayLastInCashTimeFm}</c:if>
                        </td>
                    <%-- 当日申请出金 --%>
                        <td>${item.dayOutCashFm}
                            <c:if test="${item.dayLastOutCashTimeFm ne null}">/${item.dayLastOutCashTimeFm}</c:if>
                        </td>
                            <%-- 总货款 --%>
                        <td class="hidden-sm hidden-xs">${item.totalFundFm}</td>
                            <%-- 最近代理 --%>
                        <td class="hidden-sm hidden-xs">${item.recentAgentFundFm}<br><fmt:formatDate value="${item.recentAgentDate}" pattern="yyyy-MM-dd"/></td>
                            <%-- 客户类型 --%>
                         <td class="hidden-sm hidden-xs">${item.customerTypeStr}
                               <i  class="glyphicon glyphicon-exclamation-sign"  data-toggle="tooltip" title="意向度：${item.cusIntentionTypeStr}" style="color:#aaa;"></i>
                          </td>
                            <%-- 最后登录 --%>
                        <td class="hidden-sm hidden-xs"><fmt:formatDate value="${item.lastLoginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <%-- 当前结算银行 --%>
                        <td class="hidden-sm hidden-xs">
                            <c:if test="${item.bankSign}">
                                ${item.bankName}
                            </c:if>
                        </td>
                             <%-- 最近联系时间 --%>
                        <td class="hidden-sm hidden-xs relation-info-div">
                                <span class="relation-info" id="relation-info">
                                    <div class="triangle_border_right"></div>
                                    <span class="info-lin">${item.contactContent}</span>
                                </span>
                                <fmt:formatDate value="${item.contactDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <c:if test="${pageType eq 'team'}">
                            <%-- 跟进人 --%>
                        <td>${item.followUp}</td>
                        </c:if>
                            <%-- 邀请码 --%>
                        <td class="hidden-sm hidden-xs">${item.recordInviteCode}</td>
                            <%-- 操作 --%>
                        <td>
                            <a href="./detail?agentId=${item.id}&returnUrl=${returnUrl}" data-toggle="tooltip" title="明细"><i class="ace-icon fa fa-pencil-square-o bigger-110"></i></a>
                            <c:if test="${assignSelect and item.inCash <=0 and (item.recordInviteCode ne item.flowUpInviteCode) }">
                                <a style="margin: 0 5px;" href="javascript:;" class="green green-assign"
                                   data-id="${item.id}" data-flowid="${item.followId}"
                                   data-name='<c:if test="${not empty item.linkPerson}">${fn:substring(item.linkPerson,0,1)}**</c:if>'
                                   data-act="assign" data-toggle="tooltip" title="分配跟进人">
                                    <i class="ace-icon glyphicon glyphicon-log-in"></i>
                                </a>
                            </c:if>
                            <c:if test="${kick}">
                            <a href="javascript:;" data-toggle="tooltip" title="踢入公海" data-id="${item.id}" class="kick"><i class="ace-icon fa fa-users smaller-90"></i></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <c:if test="${not empty page.results}">
            <div class="pagerBar" id="pagerBar">
                <common:page url="${pageUrl}" type="3" />
                <input id="page-import" name="page-import" style="margin-left: 5px" class="input-sm" type="text" name="fname" placeholder="输入要跳转页数"/>
                <a id="page-button" href="javascript:;" data-url="${pageUrl}">Go</a>
            </div>
        </c:if>
    </div>
</div>