<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
<%--权限判断--%>
<c:set var="agentLinkPermission" value="false" />

<c:set var="teamOrgPermission" value="false" />
<c:set var="personalOrgPermission" value="false" />

<c:set var="saleTeamDailyPermission" value="false" />
<c:set var="salePersonalDailyPermission" value="false" />
<c:set var="kaTeamDailyPermission" value="false" />
<c:set var="kaPersonalDailyPermission" value="false" />

<c:set var="cusStatusPermission" value="false" />
<c:set var="adminCusStatusPermission" value="false" />


<c:set var="sevenIncashPermission" value="false" />
<c:set var="sevenOpenPermission" value="false" />
<c:set var="cusProportionPermission" value="false" />
<c:set var="cusFundFankPermission" value="false" />


<c:forEach items="${permissions}" var="item">
    <c:if test="${item.url eq '/index/agent/link'}">
        <c:set var="agentLinkPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/ka/personal/orgdata'}">
        <c:set var="teamOrgPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/ka/team/orgdata'}">
        <c:set var="personalOrgPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/sale/team/daily'}">
        <c:set var="saleTeamDailyPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/sale/personal/daily'}">
        <c:set var="salePersonalDailyPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/ka/team/daily'}">
        <c:set var="kaTeamDailyPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/ka/personal/daily'}">
        <c:set var="kaPersonalDailyPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/cus/status'}">
        <c:set var="cusStatusPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/admin/cus/status'}">
        <c:set var="adminCusStatusPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/seven/incash'}">
        <c:set var="sevenIncashPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/seven/open'}">
        <c:set var="sevenOpenPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/cus/proportion'}">
        <c:set var="cusProportionPermission" value="true" />
    </c:if>

    <c:if test="${item.url eq '/index/cus/fund/rank'}">
        <c:set var="cusFundFankPermission" value="true" />
    </c:if>
</c:forEach>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>系统管理</title>
    <%@include file="/inc/followup/csslink.jsp"%>
    <style>
        .charts-box{}
        .charts-box .charts-item{margin-bottom:30px; padding-bottom:20px; border-bottom:dotted 1px #eee;}
        a:hover{text-decoration: none;}
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
                    <li class="active">
                        <i class="ace-icon fa fa-home home-icon"></i>
                        首页控制台
                    </li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row" id="indexDataBoxs"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<%--
直销日报
直销团队日报
KA日报
KA团队日报

客户资产阶级统计
客户类型占比
近七日入金
近七天开户数

待联系代理商

团队创客
个人创客


管理员客户状态
客户状态

--%>


<%--今日待联系代理商--%>
<script type="text/html" id="tplAgentLink">
    <div class="col-sm-4">
        <h3 class="header smaller lighter red">
            <i class="ace-icon fa fa-bullhorn"></i>
            今日待联系代理商
            <p style="font-size: small;display: inline-block;margin-bottom: 0;height: 25px">（下次联系时间为今天的代理商）</p>
        </h3>
        <div class="infobox-container">
            <div style="text-align: center; font-size:80px; font-weight: bold; line-height: 100px; width: 200px;">
                <a href="/customer/agent/list?waitToLinkToday=1" class="red">{{result}}</a>
            </div>
        </div>
    </div>
</script>

<%--创客数据--%>
<script type="text/html" id="tplOrgData">
    <div class="col-sm-4">
        <h3 class="header smaller lighter red">
            <i class="ace-icon fa fa-bullhorn"></i>
            创客数据
        </h3>
        <p>创客人数：{{result.orgNum}}</p>
        <p>创客有效代理商总数：{{result.agentNum}}</p>
        <p>创客管理资金规模：{{result.manageFundFm}}</p>
    </div>
</script>

<%--日报--%>
<script type="text/html" id="tplDaily">
    <div class="col-sm-8" id="dailyBoxs">
        <h3 class="header smaller lighter">
            <i class="ace-icon fa fa-calendar"></i>
            日报
        </h3>
    </div>
</script>
<script type="text/html" id="tplDailyItem">
    <h4 class="header smaller lighter clearfix">
        <span class="pull-left">{{deptName}}（{{date}}）</span>
        <%--<a href="/followup/manage/log/daily/detail?did={{deptId}}&date={{date}}" class="pull-right" style="font-size: 14px;">
            <i class="ace-icon fa fa-external-link"></i> 查看详情
        </a>--%>
    </h4>
    <div class="row">
        <div class="col-sm-4">
            <p>新开户数：{{openNum}}</p>
            <p>入金人数：{{inCashNum}}</p>
            <p>实际出金人数：{{outCashNum}}</p>
        </div>
        <div class="col-sm-4">
            <p>入金总额：{{inCashFm}}</p>
            <p>实际出金总额：{{outCashFm}}</p>
        </div>
    </div>
</script>


<%--客户状态--%>
<script type="text/html" id="tplCusStatus">
    <div class="col-sm-4">
        <h3 class="header smaller lighter blue">
            <i class="ace-icon fa fa-users"></i>
            代理商状态
        </h3>
        <div class="infobox-container">
            <div class="infobox infobox-blue">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-user"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.cusNum}}</span>
                    <div class="infobox-content">客户总数</div>
                </div>
            </div>
            <div class="infobox infobox-blue">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-comments"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.hadLinkNum}}</span>
                    <div class="infobox-content">已联系客户</div>
                </div>
            </div>

            <div class="infobox infobox-green">
                <div class="infobox-icon">
                    <i class="ace-icon glyphicon glyphicon-star"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.hadInCashNum}}</span>
                    <div class="infobox-content">已入金客户</div>
                </div>
            </div>
            <div class="infobox infobox-green">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-check"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.hadRealNameNum}}</span>
                    <div class="infobox-content">已实名客户</div>
                </div>
            </div>
            <div class="infobox infobox-green2">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-credit-card"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.hadSignNum}}</span>
                    <div class="infobox-content">已绑卡客户</div>
                </div>
            </div>
            <div class="infobox infobox-green">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-heart"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.hadAgentNum}}</span>
                    <div class="infobox-content">已开户代理</div>
                </div>
            </div>
            <div class="infobox infobox-red">
                <div class="infobox-icon">
                    <i class="ace-icon glyphicon glyphicon-random"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.hadLostNum}}</span>
                    <div class="infobox-content">流失客户</div>
                </div>
            </div>
            <div class="infobox infobox-red">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-bookmark-o"></i>
                </div>
                <div class="infobox-data">
                    <span class="infobox-data-number">{{result.noFundNum}}</span>
                    <div class="infobox-content">总资产为零</div>
                </div>
            </div>
        </div>
    </div>
</script>

<%--管理员客户状态--%>
<script type="text/html" id="tplAdminCusStatus">
    <div class="col-sm-4">
        <h3 class="header smaller lighter green">
            <i class="ace-icon fa fa-bullhorn"></i>
            客户信息
        </h3>
        <div class="infobox-container">
            <div class="infobox infobox-blue2">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-comments"></i>
                </div>
                <div class="infobox-data">
                    {{result.followUpNum}}
                    <div class="infobox-content">跟进人数量</div>
                </div>
            </div>
            <div class="infobox infobox-red">
                <div class="infobox-icon">
                    <i class="ace-icon glyphicon glyphicon-transfer"></i>
                </div>
                <div class="infobox-data">
                    {{result.waitAssignNum}}
                    <div class="infobox-content">待分配用户</div>
                </div>
            </div>
            <div class="infobox infobox-pink">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-users"></i>
                </div>
                <div class="infobox-data">
                    {{result.followCusNum}}
                    <div class="infobox-content">跟进用户总数</div>
                </div>
            </div>
            <div class="infobox infobox-green">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-check"></i>
                </div>
                <div class="infobox-data">
                    {{result.nameNum}}
                    <div class="infobox-content">已认证</div>
                </div>
            </div>
            <div class="infobox infobox-green">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-credit-card"></i>
                </div>
                <div class="infobox-data">
                    {{result.cardNum}}
                    <div class="infobox-content">已绑卡</div>
                </div>
            </div>
            <div class="infobox infobox-green">
                <div class="infobox-icon">
                    <i class="ace-icon fa fa-heart"></i>
                </div>
                <div class="infobox-data">
                    {{result.agentNum}}
                    <div class="infobox-content">已代理</div>
                </div>
            </div>
            <div class="infobox infobox-purple">
                <div class="infobox-icon">
                    <i class="ace-icon glyphicon glyphicon-yen"></i>
                </div>
                <div class="infobox-data">
                    {{result.totalFundFm}}
                    <div class="infobox-content">资产总规模</div>
                </div>
            </div>
        </div>
    </div>
</script>

<%--数据统计--%>
<script type="text/html" id="tplChartsBoxs">
    <div class="col-sm-8 charts-box" id="chartsBoxs">
        <h3 class="header smaller lighter green">
            <i class="ace-icon fa fa-bar-chart-o"></i>
            数据统计
        </h3>
    </div>
</script>


<script>
    seajs.use(["common","template","echarts_common"],function (common,template) {
        common.head();

        var indexDataList = [
            {
                tpl:"tplAgentLink",
                title:"待联系代理商",
                url:"/index/agent/link",
                permission:${agentLinkPermission}
            },{
                tpl:"tplOrgData",
                title:"团队创客数据",
                url:"/index/ka/team/orgdata",
                permission:${teamOrgPermission}
            },
            {
                tpl:"tplOrgData",
                title:"个创客数据",
                url:"/index/ka/personal/orgdata",
                permission:${personalOrgPermission}
            },
            {
                //日报
                tpl:"tplDaily",
                permission:${salePersonalDailyPermission or saleTeamDailyPermission or kaPersonalDailyPermission or kaTeamDailyPermission}
            },
            {
                tpl:"tplCusStatus",
                title:"客户状态",
                url:"/index/cus/status",
                permission:${cusStatusPermission}
            },{
                tpl:"tplAdminCusStatus",
                title:"管理员客户状态",
                url:"/index/admin/cus/status",
                permission:${adminCusStatusPermission}
            },{
                //统计数据
                tpl:"tplChartsBoxs",
                permission:${cusProportionPermission or cusFundFankPermission or sevenOpenPermission or sevenIncashPermission}
            }

        ];

        //首页数据统计方块
        var $indexDataBoxs = $("#indexDataBoxs");
        $.each(indexDataList,function (index,item) {
            var permission = item.permission;
            if(permission){
                var json_data = {};
                if(item.url){
                    common.ajax.handle({
                        url: item.url,
                        async:false,
                        succback: function (data) {
                            json_data = data;
                        }
                    });
                }
                $indexDataBoxs.append(template(item.tpl,json_data));
            }

        });


        //日报数据
        var dailyList = [{
            title:"直销日报",
            url:"/index/sale/personal/daily",
            permission:${salePersonalDailyPermission}
        },{
            title:"直销团队日报",
            url:"/index/sale/team/daily",
            permission:${saleTeamDailyPermission}
        },{
            title:"KA日报",
            url:"/index/ka/personal/daily",
            permission:${kaPersonalDailyPermission}
        },{
            title:"KA团队日报",
            url:"/index/ka/team/daily",
            permission:${kaTeamDailyPermission}
        }];

        var $dailyBoxs = $("#dailyBoxs");
        $.each(dailyList,function (index,item) {
            var permission = item.permission;
            if(permission){
                var json_data = {};
                common.ajax.handle({
                    url: item.url,
                    async:false,
                    succback: function (data) {
                        json_data = data.result;
                    }
                });

                $dailyBoxs.append(template("tplDailyItem",json_data));
            }
        });


        //数据统计图表
        var charts = [
            {
                type:"cusProportion",
                url:"/index/cus/proportion",
                permission:${cusProportionPermission},
                option:{
                    title : {
                        text: '客户类型统计',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        bottom : 'bottom',
                        data: []
                    },
                    series : [
                        {
                            name: '客户数',
                            type: 'pie',
                            radius : '50%',
                            center: ['50%', '45%'],
                            data:[],
                            label: {
                                normal: {
                                    show:false
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                },

            },
            {
                type:"cusFundFank",
                url:"/index/cus/fund/rank",
                permission:${cusFundFankPermission},
                option:{
                    title : {
                        text: '总资产各阶级统计',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        bottom : 'bottom',
                        data: []
                    },
                    series : [
                        {
                            name: '总资产',
                            type: 'pie',
                            radius : '50%',
                            center: ['50%', '45%'],
                            data:[],
                            label: {
                                normal: {
                                    show:false
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                }
            },
            {
                type:"sevenOpen",
                url:"/index/seven/open",
                permission:${sevenOpenPermission},
                option:{
                    title : {
                        text: '近七日开户数',
                        x:'center'
                    },
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : [],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'开户数',
                            type:'bar',
                            barWidth: '60%',
                            data:[]
                        }
                    ]
                }
            },
            {
                type:"sevenIncash",
                url:"/index/seven/incash",
                permission:${sevenIncashPermission},
                option:{
                    title : {
                        text: '近七日入金总金额',
                        x:'center'
                    },
                    color: ['#3398DB'],
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        },
                        formatter: function (params) {
                            var tar = params[0];
                            var valueFarm = common.str.hideMoney(tar.value);
                            return tar.name + '<br/>' + tar.seriesName + ' : ' + valueFarm;
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : [],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'入金总金额',
                            type:'bar',
                            barWidth: '60%',
                            data:[],
                        }
                    ]
                }
            }
        ];

        var $chartsBoxs =  $("#chartsBoxs");
        $.each(charts,function (index,value) {
            var permission = value.permission;
            if(permission){
                $chartsBoxs.append('<div class="col-sm-6 charts-item" id="charts'+index+'" style="height:260px;"></div>');
                value.mycharts = echarts.init(document.getElementById('charts' + index));
                common.ajax.handle({
                    url: value.url,
                    succback: function (data) {
                        var list = data.result;
                        var len = list.length;
                        switch(value.type)
                        {
                            case "cusProportion":
                                for(var i=0;i<len;i++){
                                    value.option.legend.data.push(list[i].cusTypeStr);
                                    var obj = {};
                                    obj.value = list[i].num;
                                    obj.name = list[i].cusTypeStr;
                                    value.option.series[0].data.push(obj);
                                }
                                break;
                            case "cusFundFank":
                                for(var i=0;i<len;i++){
                                    value.option.legend.data.push(list[i].fundRankStr);
                                    var obj = {};
                                    obj.value = list[i].num;
                                    obj.name = list[i].fundRankStr;
                                    value.option.series[0].data.push(obj);
                                }
                                break;
                            case "sevenOpen":
                                for(var i=0;i<len;i++){
                                    value.option.xAxis[0].data.push(common.dateFormat(list[i].dateStr,"MM-dd"));
                                    value.option.series[0].data.push(list[i].num);
                                }
                                break;
                            case "sevenIncash":
                                for(var i=0;i<len;i++){
                                    value.option.xAxis[0].data.push(common.dateFormat(list[i].dateStr,"MM-dd"));
                                    value.option.series[0].data.push(list[i].inCash);
                                }
                                break;
                        }

                        value.mycharts.setOption(value.option);
                    }
                });
            }
        });

    });
</script>
</body>
</html>