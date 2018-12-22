<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<script type="text/javascript">
    try{ace.settings.loadState('main-container')}catch(e){}
</script>
<div id="sidebar" class="sidebar responsive sidebar-fixed ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>

<%--    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <!--左侧顶端按钮-->
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <a class="btn btn-success" href="#" role="button" title="文章列表"><i class="ace-icon fa fa-signal"></i></a>
            <a class="btn btn-info" href="#" role="button" title="添加文章"><i class="ace-icon fa fa-pencil"></i></a>
            <a class="btn btn-warning" href="#" role="button" title="会员列表"><i class="ace-icon fa fa-users"></i></a>
            <a class="btn btn-danger" href="#" role="button" title="站点设置"><i class="ace-icon fa fa-cogs"></i></a>
        </div>
        <!--左侧顶端按钮（手机）-->
        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <a class="btn btn-success" href="#" role="button" title="文章列表"></a>
            <a class="btn btn-info" href="#" role="button" title="添加文章"></a>
            <a class="btn btn-warning" href="#" role="button" title="会员列表"></a>
            <a class="btn btn-danger" href="#" role="button" title="站点设置"></a>
        </div>
    </div>--%>
    <div class="sidebar-shortcuts">
        <h5>
            <i class="menu-icon glyphicon glyphicon-th-large"></i>
            <span class="sidebar-shortcuts-large">导航菜单栏</span>
        </h5>
    </div>

    <jsp:include page="/module/nav?path=${requestURI}"/>

    <!--菜单栏缩进开始-->
    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>