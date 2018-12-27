<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<div class="row">
    <div class="col-xs-12">
        <div class=" clearfix">
            <h2 class="pull-left blue">
                客户：${detailVo.linkPersonFm}
                &nbsp;&nbsp;&nbsp;
            </h2>
            <a href="/followup/uc/customer/agent/list?${returnUrl}" style="float: right;margin: 20px 10px 0 0;" class="btn btn-sm btn-info" type="reset">返回</a>
        </div>
        <h4 class="header smaller lighter blue">
            <i class="ace-icon fa fa-user"></i>
            客户信息
        </h4>
        <table class="table table-striped table-bordered customer-info">
            <tr>
                <th>姓名</th>
                <td>${detailVo.linkPersonFm}</td>
                <th>手机号</th>
                <td>
                    <c:choose>
                        <c:when test="${pageType eq 'team'}">
                            ${fn:substring(detailVo.mobile, 0, 3)}****${fn:substring(detailVo.mobile, 7, 11)}
                        </c:when>
                        <c:otherwise>
                            ${detailVo.mobile}
                        </c:otherwise>
                    </c:choose>
                </td>
                <th>所在地</th>
                <td>${detailVo.idCardAddr}</td>
            </tr>
            <tr>
                <th>邀请码</th>
                <td>${detailVo.inviteCode}</td>
                <th>出生月日</th>
                <td>${detailVo.dateStr}</td>
                <th>年龄</th>
                <td>${detailVo.age}</td>
            </tr>
            <tr>
                <th>实名认证</th>
                <td>
                    <c:if test="${not empty detailVo.idCard}">
                        <fmt:formatDate value="${detailVo.cardValidDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if>
                </td>
                <th>是否绑卡</th>
                <td>
                    <c:if test="${detailVo.bankSign}">
                        <fmt:formatDate value="${detailVo.bankSignDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if>
                </td>
                <th>是否入金</th>
                <td>
                    ${detailVo.inCash >0 ? "是":"否"}
                </td>
            </tr>
            <tr>
                <th>注册时间</th>

                <td><fmt:formatDate value="${detailVo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <th>分配时间</th>
                <td><fmt:formatDate value="${detailVo.assignDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <th>账号状态</th>
                <td>${detailVo.statusStr}</td>
            </tr>
        </table>

        <h4 class="header smaller lighter blue">
            <i class="ace-icon fa  fa-credit-card"></i>
            资产信息
        </h4>

        <table class="table table-striped table-bordered customer-info">
            <tr>
                <th>总货款</th>
                <td>${detailVo.totalFundFm}</td>
                <th>协议资金</th>
                <td>${detailVo.agentFundFm}</td>
                <th>冻结资金</th>
                <td>${detailVo.frozenFundFm}</td>
            </tr>
            <tr>
                <th>余额</th>
                <td>${detailVo.useableFundFm}</td>
                <th>最近代理</th>
                <td>
                    ${detailVo.recentAgentFundFm}&nbsp;&nbsp;${detailVo.recentAgentDate}
                </td>
                <th>已开通结算银行</th>
                <td>${detailVo.bankOpen}</td>
            </tr>
            <tr>
                <th>当日累计入金</th>
                <td>${detailVo.dayInCashFm}&nbsp;&nbsp;${detailVo.dayLastInCashTime}</td>
                <th>当日申请出金</th>
                <td>${detailVo.dayApplyOutCashFm}&nbsp;&nbsp;${detailVo.dayLastApplyOutCashTime}</td>
                <th>实际出金</th>
                <td>${detailVo.dayOutCashFm}</td>
            </tr>
        </table>
    </div>
</div>
