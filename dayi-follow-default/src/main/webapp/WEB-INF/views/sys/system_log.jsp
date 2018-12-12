<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>日志管理</title>
    <%@include file="/inc/followup/csslink.jsp"%>
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
                        <a href="/followup/manage/index">首页</a>
                    </li>
                    <li class="active">跟进人查询</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="page-header clearfix">
                    <h1 class="pull-left">
                        您当前操作
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            跟进人查询
                        </small>
                    </h1>
                </div>

                <div class="row">
                    <form class="form-horizontal">
                        <div class="clearfix maintop">

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="当前跟进人"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="更变跟进人"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="名称"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="手机号"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date" value=""
                                           placeholder="邀请码"/>
                                </div>
                            </div>

                            <div class="col-xs-2 col-sm-2 btn-sespan">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="ace-icon fa fa-calendar"></i>
                                    </span>
                                    <input type="text" class="form-control admin_sea dates" name="date"
                                           value="${param.date}" placeholder="变更日期"/>
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-xs btn-purple">
                                            <span class="ace-icon fa fa-search"></span>
                                            搜索
                                        </button>
                                        <a href="?" class="btn btn-xs btn-info">
                                            <span class="ace-icon fa fa-globe"></span>
                                            导出
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>

                <div class="space-10"></div>
                <div class="row" id="listPan">
                    <div class="col-xs-12">
                        <table class="table table-striped table-bordered table-hover" >
                            <thead>
                            <tr>
                                <th>会员ID</th>
                                <th>名称</th>
                                <th>手机号</th>
                                <th>注册时间</th>
                                <th>邀请码</th>
                                <th>当前跟进人</th>
                                <th>变更前跟进人</th>
                                <th>变更日期</th>
                                <th>变更前总资产</th>
                                <th>当前总资产</th>
                                <th>新增代理商服务费</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%--<c:if test="${empty page.items}">--%>
                            <%--<tr>--%>
                                <%--<td colspan="11" class="no_data">暂无数据记录</td>--%>
                            <%--</tr>--%>
                            <%--</c:if>--%>

                            <c:forEach var="i" begin="1" end="5">
                            <tr>
                                <td>520</td>
                                <td>马冬梅</td>
                                <td>13711802518</td>
                                <td>2016-02-20 14:44:43</td>
                                <td>1314</td>
                                <td>夏洛</td>
                                <td>郝建</td>
                                <td>2016-02-20 14:44:43</td>
                                <td>100.000.62</td>
                                <td>100.000.62</td>
                                <td>998</td>
                            </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                        <%--<c:if test="${not empty page}">--%>
                            <%--<div class="pagerBar" id="pagerBar">--%>
                                <%--<common:page2 url="${pageUrl}" type="3"/>--%>
                            <%--</div>--%>
                        <%--</c:if>--%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","validate","template"],function(common,validate,template){
        common.head("system",3);
    });
</script>
</body>
</html>