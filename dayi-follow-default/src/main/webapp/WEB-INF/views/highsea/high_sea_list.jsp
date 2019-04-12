<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公海客户列表</title>
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
                    <li>公海客户</li>
                    <li class="active">公海客户列表</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            公海客户列表
                        </small>
                    </h1>
                </div>
                <div class="row">
                    <form class="form-horizontal" >
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan">
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
                                           value="${param.mobile}" placeholder="手机号码"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-check-square-o"></i>
                                    </span>
                                    <%--<select name="customerType" class="form-control admin_sea">--%>
                                        <%--<option value="">客户类型</option>--%>
                                        <%--<option value="0"  ${param.customerType=='0'?"selected":''}>已开户</option>--%>
                                        <%--<option value="1"  ${param.customerType=='1'?"selected":''}>未联系</option>--%>
                                        <%--<option value="2"  ${param.customerType=='2'?"selected":''}>无意向</option>--%>
                                        <%--<option value="3"  ${param.customerType=='3'?"selected":''}>流失</option>--%>
                                        <%--<option value="4"  ${param.customerType=='4'?"selected":''}>无效</option>--%>
                                        <%--<option value="5"  ${param.customerType=='5'?"selected":''}>可被跟进</option>--%>
                                    <%--</select>--%>
                                    <select name="customerType" class="form-control admin_sea">
                                        <option value="">客户类型</option>
                                        <c:forEach items="${cusType}" var="item">
                                            <option value="${item.value}" <c:if test="${param.customerType eq item.value}">selected</c:if>>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                            </div>
                        </div>
                        <div class="clearfix maintop">
                            <div class="col-sm-3 hidden-xs btn-sespan assignDateDiv">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="assignDate" value="${param.warehouseDate}"
                                           placeholder="入库时间"/>
                                </div>
                            </div>
                            <div class="col-xs-4 col-sm-2 btn-sespan">
                                <div class="btn-group dropup">
                                    <button type="submit" class="btn btn-xs btn-purple">
                                        <span class="ace-icon fa fa-search"></span>
                                        搜索
                                    </button>
                                    <a href="/highsea/getconfig" class="btn btn-xs btn-primary" style="margin-left: 20px">公海设置</a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" id="dynamic-table">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>邀请码</th>
                                <th>客户类型</th>
                                <th class="hidden-xs">注册时间</th>
                                <th class="hidden-sm hidden-xs">入库时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty list}">
                                <tr>
                                    <td colspan="6" class="no_data">暂无数据记录</td>
                                </tr>
                            </c:if>

                            <c:if test="${not empty list}">
                                <c:forEach items="${list}" var="item" >
                                    <tr data-id="${item.id}">
                                        <!-- 姓名 -->
                                        <td>${item.LinkPersonFm}</td>
                                        <!-- 邀请码 -->
                                        <td>${item.inviteCode}</td>
                                        <%--客户类型--%>
                                        <td>${item.CustomerTypeStr}</td>
                                        <!-- 注册时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 入库时间 -->
                                        <td class="hidden-xs"><fmt:formatDate value="${item.warehouseDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <!-- 操作 -->
                                        <td>
                                            <button type="button" class="btn btn-primary dragCustomer" data-id="${item.id}">认领客户</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                        <c:if test="${not empty list}">
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


        var $myModal = $("#myModal");
        var $pageListPanel = $myModal.find(".selectlistbox");
        var ids = null;
        var select_data = null;
        var url_selectlist = "/agent/assign/select";

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
                url: "/agent/assign/save/batch.json",
                data: {followId: id, agentIds: ids},
                succback: function (data) {
                    $myModal.modal("hide");
                    ids = null;
                    select_data = null;
                    common.successMsg(data.msg, "reload");
                }
            });
            return false;
        });


        //认领客户
        $(".dragCustomer").on("click",function (e) {
            e.preventDefault();
            var agentId = $(this).attr("data-id");

            common.ajax.handle({
                method: 'get',
                url: "/highsea/drags",
                data: {
                    agentId: agentId
                },
                succback: function (data) {
                    common.successMsg(data.msg, "reload");
                }
            });
        })
    });
</script>
</body>
</html>