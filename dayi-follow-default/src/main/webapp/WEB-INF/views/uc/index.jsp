<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/inc/followup/taglib.jsp"%>
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
                        <div class="row">
                            <c:choose>
                                <c:when test="${isKA}">
                                    <div class="col-sm-4">
                                        <h3 class="header smaller lighter red">
                                            <i class="ace-icon fa fa-bullhorn"></i>
                                            创客数据
                                        </h3>

                                        <p>创客人数：${orgCount.kaOrgNum}</p>
                                        <p>创客有效代理商总数：${orgCount.kaOrgValidAgentNum}</p>
                                        <p>创客管理资金规模：${orgCount.kaOrgManageMoneyFormat} </p>



                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-sm-4">
                                        <h3 class="header smaller lighter red">
                                            <i class="ace-icon fa fa-bullhorn"></i>
                                            今日待联系代理商
                                            <p style="font-size: small;display: inline-block;margin-bottom: 0;height: 25px">（下次联系时间为今天的代理商）</p>
                                        </h3>
                                        <div class="infobox-container">
                                            <div style="text-align: center; font-size:80px; font-weight: bold; line-height: 100px; width: 200px;">
                                                <a href="/uc/customer/agent/list?waitToLinkToday=1" class="red">${wLCount.waitLinkNum}</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>



                            <div class="col-sm-8">
                                <h3 class="header smaller lighter">
                                    <i class="ace-icon fa fa-calendar"></i>
                                    ${pDDaily.date} <c:if test="${isManager}">（团队）</c:if>日报
                                </h3>
                                <div class="row">
                                    <c:choose>
                                        <c:when test="${not empty pDDaily}">
                                            <div class="col-sm-4">
                                                <c:if test="${not isKA}">
                                                    <p>新开户数：${pDDaily.newOpenNum}</p>
                                                </c:if>
                                                <p>入金人数：${pDDaily.inCashNum}</p>
                                                <p>实际出金人数：${pDDaily.outCashNum}</p>
                                            </div>
                                            <div class="col-sm-4">
                                                <p>入金总额：${pDDaily.cyberBankTotalInCashFormat}</p>
                                                <p>实际出金总额：${pDDaily.totalApplyOutCashFormat}</p>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div style="color: #ddd; text-align: center;">暂无数据统计</div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="col-xs-12 space-6"></div>
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
                                            <span class="infobox-data-number">${fUCountVo.agentCount}</span>
                                            <div class="infobox-content">客户总数</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-blue">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-comments"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.linkTotal}</span>
                                            <div class="infobox-content">已联系客户</div>
                                        </div>
                                    </div>

                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon glyphicon glyphicon-star"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.inCashTotal}</span>
                                            <div class="infobox-content">已入金客户</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-check"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.idCardTotal}</span>
                                            <div class="infobox-content">已实名客户</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-green2">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-credit-card"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.signTotal}</span>
                                            <div class="infobox-content">已绑卡客户</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-heart"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.agentTotal}</span>
                                            <div class="infobox-content">已开户代理</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-red">
                                        <div class="infobox-icon">
                                            <i class="ace-icon glyphicon glyphicon-random"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.lostTotal}</span>
                                            <div class="infobox-content">流失客户</div>
                                        </div>
                                    </div>
                                    <div class="infobox infobox-red">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-bookmark-o"></i>
                                        </div>
                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${fUCountVo.noFundTotal}</span>
                                            <div class="infobox-content">总资产为零</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-8 charts-box">
                                <h3 class="header smaller lighter green">
                                    <i class="ace-icon fa fa-bar-chart-o"></i>
                                    数据统计
                                </h3>
                                <c:if test="${fUCountVo.agentCount > 0}">
                                    <div class="col-sm-6 charts-item" id="charts1" style="height:260px;"></div>
                                    <div class="col-sm-6 charts-item" id="charts2" style="height:260px;"></div>
                                    <c:if test="${not isKA}">
                                        <div class="col-sm-6 charts-item" id="charts3" style="height:240px;"></div>
                                    </c:if>
                                    <div class="col-sm-6 charts-item" id="charts4" style="height:240px;"></div>
                                </c:if>
                                <c:if test="${fUCountVo.agentCount eq 0}">
                                    <div style="color: #ddd; text-align: center;">暂无数据统计</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/inc/followup/script.jsp"%>
<script>
    seajs.use(["common","echarts_common"],function (common) {
        common.head();

        // 基于准备好的dom，初始化echarts实例
        var charts1 = echarts.init(document.getElementById('charts1'));

        // 指定图表的配置项和数据
        var charts1_option = {
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
        };

        // 基于准备好的dom，初始化echarts实例
        var charts2 = echarts.init(document.getElementById('charts2'));

        // 指定图表的配置项和数据
        var charts2_option = {
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
        };

        <c:if test="${not isKA}">
        var charts3 = echarts.init(document.getElementById('charts3'));
        var charts3_option = {
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
        };
        </c:if>


        var charts4 = echarts.init(document.getElementById('charts4'));
        var charts4_option = {
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
        };

        common.ajax.handle({
            url:"/followup/uc/charts.json",
            succback:function (data) {
                //

                //近七日入金总金额
                var cusTypeList = data.item.cusTypeList;
                var len1 = cusTypeList.length;
                for(var i=0;i<len1;i++){
                    charts1_option.legend.data.push(cusTypeList[i].customerType);
                    var obj = {};
                    obj.value = cusTypeList[i].num;
                    obj.name = cusTypeList[i].customerType;
                    charts1_option.series[0].data.push(obj);
                }
                charts1.setOption(charts1_option);


                var totalMoneysList = data.item.totalMoneysList;
                var len2 = totalMoneysList.length;
                for(var i=0;i<len2;i++){
                    charts2_option.legend.data.push(totalMoneysList[i].moneyType);
                    var obj = {};
                    obj.value = totalMoneysList[i].num;
                    obj.name = totalMoneysList[i].moneyType;
                    charts2_option.series[0].data.push(obj);
                }
                charts2.setOption(charts2_option);


                if(data.item.sDOpenList){
                    //近七日开户数
                    var sDOpenList = data.item.sDOpenList;
                    var len3 = sDOpenList.length;
                    for(var i=0;i<len3;i++){
                        charts3_option.xAxis[0].data.push(common.dateFormat(sDOpenList[i].date,"MM-dd"));
                        charts3_option.series[0].data.push(sDOpenList[i].num);
                    }
                    charts3.setOption(charts3_option);
                }

                //近七日入金总金额
                var sDInCashList = data.item.sDInCashList;
                var len4 = sDInCashList.length;
                for(var i=0;i<len4;i++){
                    charts4_option.xAxis[0].data.push(common.dateFormat(sDInCashList[i].date,"MM-dd"));
                    charts4_option.series[0].data.push(sDInCashList[i].totalInCash);
                }
                charts4.setOption(charts4_option);
            }
        });
    });
</script>
</body>
</html>