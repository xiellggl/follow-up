<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>跟进人分配</title>
    <%@include file="/inc/followup/csslink.jsp" %>
    <c:set var="typeStr" value="${flowTypeTab eq 2?'创客':'代理商'}"/>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
</head>
<body class="no-skin">
<%@include file="/inc/followup/topbar.jsp" %>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li>跟进人分配</li>
                    <li class="active">${typeStr}</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            分配${typeStr}
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal" style="max-width: 800px;">
                        <c:if test="${flowTypeTab eq 2}">
                            <input type="hidden" name="flowTypeTab" value="2">
                        </c:if>
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="regdates" value="${param.regdates}"
                                           placeholder="注册时间"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                                    <input type="text" name="filter_LIKEANYWHERES_mobile" class="form-control admin_sea"
                                           value="${param.filter_LIKEANYWHERES_mobile}" placeholder="客户手机号码"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-user"></i>
                                    </span>
                                    <input type="text" name="filter_LIKEANYWHERES_linkPerson" class="form-control admin_sea"
                                           value="${param.filter_LIKEANYWHERES_linkPerson}" placeholder="客户姓名"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check-square-o"></i>
                                    </span>
                                    <select name="assignStat" class="form-control admin_sea">
                                        <option value="">分配状态</option>
                                        <option value="1"  ${assignStat=='1'?"selected":''}>未分配</option>
                                        <option value="2"  ${assignStat=='2'?"selected":''}>已分配</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="assigndates" value="${param.assigndates}"
                                           placeholder="分配时间"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <input type="text" name="filter_LIKEANYWHERES_followUp" class="form-control admin_sea"
                                           value="${param.filter_LIKEANYWHERES_followUp}" placeholder="跟进人"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-barcode"></i>
                                    </span>
                                    <c:choose>
                                        <c:when test="${flowTypeTab eq 2}">
                                            <input type="text" name="filter_LIKEANYWHERES_makerNum" class="form-control admin_sea"
                                                   value="${param.filter_LIKEANYWHERES_makerNum}" placeholder="邀请码"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" name="filter_LIKEANYWHERES_recordInviteCode" class="form-control admin_sea"
                                                   value="${param.filter_LIKEANYWHERES_recordInviteCode}" placeholder="邀请码"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-3 btn-sespan">
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
                                <a href="./list${flowTypeTab eq 2 ? '?flowTypeTab=2' : ''}"
                                   class="btn btn-xs btn-info hidden-xs">
                                    <span class="ace-icon fa fa-globe"></span>
                                    显示全部
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="clearfix maintop">
                        <div class="col-xs-4 col-sm-3 btn-sespan">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-xs btn-success all" data-act="assign" data-from="from=assign_list">
                                    <span class="ace-icon glyphicon glyphicon-log-in"></span>
                                    批量分配
                                </button>
                                <button type="button" class="btn btn-xs all" data-act="clearAssign" data-from="from=assign_list">
                                    <span class="ace-icon glyphicon glyphicon-log-out"></span>
                                    批量清除
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                                    <label class="pos-rel">
                                        <input type="checkbox" id="chkAll" class="ace">

                                        <span class="lbl"></span>
                                    </label>
                                    <input type="hidden" id="checkIds" name="ids">
                                </th>
                                <th>姓名</th>
                                <th class="hidden-xs">注册时间</th>
                                <th>手机号</th>
                                <c:if test="${flowTypeTab eq 1}">
                                    <th class="hidden-sm hidden-xs">开户银行</th>
                                </c:if>
                                <th class="hidden-sm hidden-xs">身份证号</th>
                                <c:if test="${flowTypeTab eq 1}">
                                    <th class="hidden-sm hidden-xs">银行卡号</th>
                                </c:if>
                                <th>跟进人</th>
                                <th class="hidden-xs">分配时间</th>
                                <th>邀请码</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.items}">
                                <tr>
                                    <td colspan="12" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.items}">
                                <c:forEach items="${page.items}" var="item">
                                    <tr>
                                        <td class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace check" name="id" value="${item.id}">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <!-- 名称 -->
                                        <td>${item.linkPersonStr}</td>
                                        <!-- 注册时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.createDate}"
                                                                              pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 手机号 -->
                                        <td>${item.mobile}</td>
                                        <!-- 开户银行 -->
                                        <c:if test="${flowTypeTab eq 1}">
                                            <td class="hidden-sm hidden-xs">${item.bankName}<c:if
                                                test="${not empty item.bankRealName}"><br>(${item.bankRealName})</c:if>
                                            </td>
                                        </c:if>
                                        <!-- 身份证号 -->
                                        <td class="hidden-sm hidden-xs"><c:if
                                            test="${not empty item.idCard}">${fn:substring(item.idCard, 0, fn:length(item.idCard) - 4)}****</c:if></td>
                                        <!-- 银行卡号 -->
                                        <c:if test="${flowTypeTab eq 1}">
                                            <td class="hidden-sm hidden-xs"><c:if
                                                test="${not empty item.bankAccount}">${fn:substring(item.bankAccount,0,3)}**********${fn:substring(item.bankAccount, fn:length(item.bankAccount)-3, -1)}</c:if></td>
                                        </c:if>
                                        <!-- 跟进人 -->
                                        <td>${item.followUp}</td>
                                        <!-- 分配时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.flowDate}"
                                                                              pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 邀请码 -->
                                        <td>
                                            <c:if test="${flowTypeTab eq 1}">${item.recordInviteCode}</c:if>
                                            <c:if test="${flowTypeTab eq 2}">${item.inviteCodeStr}</c:if>
                                        </td>
                                        <!-- 操作 -->
                                        <td>
                                            <a style="margin: 0 5px;" href="javascript:;" class="green"
                                               data-id="${item.id}" data-from="from=assign_list" data-flowid="${item.flowId}"
                                               data-name='<c:if test="${not empty item.linkPerson}">${fn:substring(item.linkPerson,0,1)}**</c:if>'
                                               data-act="assign" data-toggle="tooltip" title="分配跟进人">
                                                <i class="ace-icon glyphicon glyphicon-log-in"></i>
                                            </a>
                                            <c:if test="${not empty item.flowId}">
                                                <a style="margin: 0 5px;" href="javascript:;" class="light-grey"
                                                   data-id="${item.id}"
                                                   data-name='<c:if test="${not empty item.linkPerson}">${fn:substring(item.linkPerson,0,1)}**</c:if>'
                                                   data-act="clearAssign" data-toggle="tooltip" title="清除分配">
                                                    <i class="ace-icon glyphicon glyphicon-log-out"></i>
                                                </a>
                                            </c:if>
                                        </td>
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
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%--编辑跟进人模态框（Modal）--%>
<div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title">分配跟进人</h4>
            </div>
            <div class="modal-body" style="min-height:300px;">
                <div class="row">
                    <div class="col-xs-12 selectlistbox"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/inc/followup/script.jsp" %>
<script charset="UTF-8" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    var flowTypeTab =${flowTypeTab}; // 页签状态：1--代理商；2--创客
    seajs.use(["common", "daterangepicker"], function (common) {
        common.head("followuperAssign",flowTypeTab);
        var date_o = {
            autoUpdateInput: false,
            locale: locale_cn,
        };
        date_o.locale.cancelLabel = "清空";
        $('.dates').daterangepicker(date_o);

        $('.dates').on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('YYYY-MM-DD') + ' - ' + picker.endDate.format('YYYY-MM-DD'));
        });

        $('.dates').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
        var $list = $("#listPan");
        //全选、反选
        common.checkBox($("#chkAll"), $list.find('[name="id"]'), $("#checkIds"));

        var type = flowTypeTab == 2 ? "org" : "agent";

        //清除分配
        $('[data-act="clearAssign"]').on("click", function () {
            var $this = $(this), ids, name;
            if ($this.hasClass("all")) {
                //批量操作
                if ($(".check:checked").length <= 0) {
                    layer.alert('<p class="tc">请最少选择一名用户！！！</p>', {title: "温馨提示"});
                    return false;
                }
                name = "选中";
                ids = $("#checkIds").val();
            } else {
                ids = $this.data("id");
                name = $this.data("name");
            }
            layer.confirm('<p class="tc red">是否清除【' + name + '】的跟进人</p>', {title: "温馨提示"}, function () {
                common.ajax.handle({
                    url: "/followup/manage/followuper/assign/" + type + "/clear.json",
                    data: {ids: ids}
                });
            });
        });

        var $myModal = $("#myModal");
        var $pageListPanel = $myModal.find(".selectlistbox");
        var ids = null;
        var select_data = null;
        var url_selectlist = "/followup/manage/followuper/assign/select";

        //分配跟进人
        $('[data-act="assign"]').on("click", function () {
            var $this = $(this);
            var from = $(this).data("from");
            var flowId = $(this).data("flowid");
            var url_from = "/followup/manage/followuper/assign/select?" + from + "&flowId=" + flowId;

            if ($this.hasClass("all")) {
                url_from = "/followup/manage/followuper/assign/select?" + from;
                //批量操作
                if ($(".check:checked").length <= 0) {
                    layer.alert('<p class="tc cRed">请最少选择一名用户！！！</p>', {title: "温馨提示"});
                    return false;
                }
                ids = $("#checkIds").val();
            } else {
                ids = $this.data("id");
            }
            $myModal.modal("show");
            common.loadPageHTML(url_from, null, $pageListPanel, true);
            common.clickPageFn(url_from, select_data, $pageListPanel, true);
            $myModal.find('[name="linkPerson"]').val("");
            $pageListPanel.html("");
        });

        //搜索
        $myModal.on("click", '[data-act="search"]', function () {
            var flowId = $(this).data("flowid");
            var linkPerson = $myModal.find('[name="linkPerson"]').val();
            var from = $(this).data("from");
            select_data = {filter_LIKEANYWHERES_linkPerson: linkPerson};
            select_data.from=from;
            select_data.flowId=flowId;
            common.loadPageHTML(url_selectlist, select_data, $pageListPanel, true);
            return false;
        });
        //分配
        $myModal.on("click", '[data-act="check"]', function () {
            var id = $(this).data("id");
            common.ajax.handle({
                url: "/followup/manage/followuper/assign/" + type + "/save.json",
                data: {flowId: id, ids: ids},
                succback: function (data) {
                    $myModal.modal("hide");
                    ids = null;
                    select_data = null;
                    common.successMsg(data.msg, "reload");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>