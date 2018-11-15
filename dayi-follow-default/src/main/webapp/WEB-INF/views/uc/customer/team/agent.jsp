<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>团队客户-代理商</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="/static/public/bootstrap/plug/multiselect/bootstrap-multiselect.css" />
    <style>
        .multiselect-native-select .btn-group>.btn>.caret{ border-top-color: #666; margin-top:0;}
        .bank-item label{margin: 0 5px;}
    </style>
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
                        <a href="/followup/uc/index">首页</a>
                    </li>
                    <li class="active">团队客户-代理商</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <c:set var="pageType" value="team" />
                <%@include file="../agent_list_inc.jsp"%>
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
<script>
    seajs.use(["common","multiselect"],function (common) {
        common.head("teamCustomer",1);
        //多选下拉
        var $bankType = $("#bankType");
        $bankType.multiselect({
            nonSelectedText: '已开通结算银行',
            allSelectedText:"全部选中",
            nSelectedText: '个选中',
            buttonClass: 'btn btn-white',
        });

        var $myModal = $("#myModal");
        var $pageListPanel = $myModal.find(".selectlistbox");
        var ids = null;
        var select_data = null;
        var url_selectlist = "/followup/manage/followuper/assign/select";

        //分配跟进人
        $('[data-act="assign"]').on("click", function () {
            var $this = $(this);
            var flowId = $(this).data("flowid");
            var url_from = "/followup/manage/followuper/assign/select"+"?flowId="+flowId;;

            if ($this.hasClass("all")) {
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
            select_data = {filter_LIKEANYWHERES_linkPerson: linkPerson};
            select_data.flowId=flowId;
            common.loadPageHTML(url_selectlist, select_data, $pageListPanel, true);
            return false;
        });
        //分配
        $myModal.on("click", '[data-act="check"]', function () {
            var id = $(this).data("id");
            common.ajax.handle({
                url: "/followup/manage/followuper/assign/agent/save.json",
                data: {flowId:id,ids: ids},
                succback: function (data) {
                    $myModal.modal("hide");
                    ids = null;
                    select_data = null;
                    common.successMsg(data.msg, "reload");
                }
            });
            return false;
        });
        var datacount = $('[data-id="pageRecordTotal"]').html();
        if (datacount<20){
            $("#page-import").css('display','none');
            $("#page-button").css('display','none');
        }else {

        }
        var pagego = $("#page-button");
        var pageurl = pagego.attr("data-url");
        $(pagego).click(function(){
            var temp = $('input[name="page-import"]').val();
            if (temp==""){
                alert("请输入页码")
            }else {
                window.location.href=pageurl+"&pageNo="+temp;
            }
        });
    });
</script>
</body>
</html>