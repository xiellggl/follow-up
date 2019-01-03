<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>跟进人分配</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/daterangepicker3/daterangepicker.css"/>
</head>
<body class="no-skin">
<%@include file="/inc/followup/topbar.jsp"%>
<div class="main-container" id="main-container">
    <%@include file="/inc/followup/sidebar.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/">首页</a>
                    </li>
                    <li>跟进人分配</li>
                    <li class="active">创客</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            分配创客
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal" >
                        <div class="clearfix maintop">
                            <div class="col-sm-2 hidden-xs btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="createDate" value="${param.createDate}"
                                           placeholder="注册时间"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon glyphicon glyphicon-phone"></i>
                                    </span>
                                    <input type="text" name="mobile" class="form-control admin_sea"
                                           value="${param.mobile}" placeholder="客户手机号码"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-user"></i>
                                    </span>
                                    <input type="text" name="cusName" class="form-control admin_sea"
                                           value="${param.cusName}" placeholder="客户姓名"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check-square-o"></i>
                                    </span>
                                    <select name="assignStatus" class="form-control admin_sea">
                                        <option value="0"  ${param.assignStatus=='0'?"selected":''}>未分配</option>
                                        <option value="1"  ${param.assignStatus=='1'?"selected":''}>已分配</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                            </div>
                        </div>
                        <div class="clearfix maintop">
                            <div class="col-sm-2 hidden-xs btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="assignDate" value="${param.assignDate}"
                                           placeholder="分配时间"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check"></i>
                                    </span>
                                    <input type="text" name="followUp" class="form-control admin_sea"
                                           value="${param.followUp}" placeholder="跟进人"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-barcode"></i>
                                    </span>
                                    <input type="text" name="inviteCode" class="form-control admin_sea"
                                           value="${param.inviteCode}" placeholder="邀请码"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
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
                            <div class="col-xs-4 col-sm-2 btn-sespan">
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
                                <a href="./list"
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
                                    <input type="hidden" id="checkIds" name="ids" value="">
                                </th>
                                <th>姓名</th>
                                <th class="hidden-xs">注册时间</th>
                                <th>手机号</th>
                                <th class="hidden-sm hidden-xs">身份证号</th>
                                <th>机构类型</th>
                                <th>跟进人</th>
                                <th class="hidden-xs">分配时间</th>
                                <th>邀请码</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty page.results}">
                                <tr>
                                    <td colspan="10" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty page.results}">
                                <c:forEach items="${page.results}" var="item" >
                                    <tr data-id="${item.id}">
                                        <td class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace check" name="id" value="${item.id}">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <!-- 名称 -->
                                        <td>${item.linkPersonFm}</td>
                                        <!-- 注册时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.createDate}"
                                                                              pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 手机号 -->
                                        <td>${item.mobile}</td>
                                        <!-- 身份证号 -->
                                        <td class="hidden-sm hidden-xs"><c:if
                                                test="${not empty item.idCard}">${fn:substring(item.idCard, 0, fn:length(item.idCard) - 4)}****</c:if></td>
                                        <!-- 机构类型 -->
                                        <td>${item.orgTypeStr}</td>
                                        <!-- 跟进人 -->
                                        <td>${item.followUp}</td>
                                        <!-- 分配时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.assignDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 邀请码 -->
                                        <td>${item.inviteCode}</td>
                                        <!-- 操作 -->
                                        <td>
                                            <a style="margin: 0 5px;" href="javascript:;" class="green"
                                               data-id="${item.id}" data-from="from=assign_list" data-flowid="${item.followId}"
                                               data-name='<c:if test="${not empty item.linkPersonFm}">${fn:substring(item.linkPersonFm,0,1)}**</c:if>'
                                               data-act="assign" data-toggle="tooltip" title="分配跟进人">
                                                <i class="ace-icon glyphicon glyphicon-log-in"></i>
                                            </a>
                                            <c:if test="${not empty item.followId}">
                                                <a style="margin: 0 5px;" href="javascript:;" class="light-grey"
                                                   data-id="${item.id}"
                                                   data-name='<c:if test="${not empty item.linkPersonFm}">${fn:substring(item.linkPersonFm,0,1)}**</c:if>'
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
                        <c:if test="${not empty page.results}">
                            <div class="pagerBar" id="pagerBar">
                                <common:page url="${pageUrl}" type="3" />
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
<%@include file="/inc/followup/script.jsp"%>
<script charset="UTF-8" async="" src="/static/public/daterangepicker3/moment.min.js"></script>
<script>
    seajs.use(["common", "daterangepicker"], function (common) {
        //菜单高亮
        common.head();
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
                    url: "/org/assign/clear/batch.json",
                    data: {ids: ids}
                });
            });
        });

        var $myModal = $("#myModal");
        var $pageListPanel = $myModal.find(".selectlistbox");
        var ids = null;
        var select_data = null;
        var url_selectlist = "/agent/assign/select";

        //分配跟进人
        $('[data-act="assign"]').on("click", function () {
            var $this = $(this);
            var from = $(this).data("from");
            var flowId = $(this).data("flowid");
            var url_from = "/agent/assign/select?" + from + "&followId=" + flowId;

            if ($this.hasClass("all")) {
                url_from = "/agent/assign/select?" + from;
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
            var flowId = $(this).data("followid");
            var linkPerson = $myModal.find('[name="followUp"]').val();
            select_data = {followUp: linkPerson};
            select_data.followId=flowId;
            common.loadPageHTML(url_selectlist, select_data, $pageListPanel, true);
            return false;
        });
        //分配
        $myModal.on("click", '[data-act="check"]', function () {
            var id = $(this).data("id");
            common.ajax.handle({
                url: "/org/assign/save/batch.json",
                data: {followId: id, orgIds: ids},
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