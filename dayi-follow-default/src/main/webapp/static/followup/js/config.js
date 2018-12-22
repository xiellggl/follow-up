(function () {
    /*IE8下未开启调试，console出现错误提示兼容*/
    window.console = window.console || {};
    window.console.log = window.console.log || function(){};
    var getCookie = function(name){
        var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
        if(arr != null){
            return unescape(arr[2]);
        }
        return null;
    };
    /* Local variable */
    var host    = "//" + location.host;
    //var debug  = getCookie("debugFlag") ? true : false;
    var debug = true;
    var config={
        vars : {
            "public":"/static/public/js/",
            "followup":"/static/followup/js/",
            "page":"/static/followup/js/page/",
        },
        //别名
        alias: {
            //公共js及组件
            "layer":"{public}layer/layer.js",
            "template":"{public}artTemplate.js",
            "clipboard":"{public}clipboard/clipboard.js",

            //百度统计插件 （根据需求选择，因为这个文件是比较大的）
            //完全版 -- 所有功能 但非常大 2.76 Mb--> 717 Kb
            "echarts":"{public}echarts/echarts.js",
            //常用版 -- 包含常用的图表组件 452 Kb（折 柱 饼 散点 图例 工具栏 标注/线/域 数据区域缩放）
            "echarts_common":"{public}echarts/echarts.common.min.js",
            //精简版 -- 只包含基础图表 291 Kb（折 柱 饼）
            "echarts_simple":"{public}echarts/echarts.simple.min.js",


            "moment":"/static/public/daterangepicker3/moment.min.js",
            "daterangepicker":"/static/public/daterangepicker3/daterangepicker.js",
            "multiselect":"/static/public/bootstrap/plug/multiselect/bootstrap-multiselect.js",
            "flexoCalendar":"/static/public/flexoCalendar/flexoCalendar.js",


            //验证类
            "validate":"{public}validate/validate.js",
            "addMethod":"{public}validate/addMethod.js",

            "common":"{followup}common.js",
        },
        preload:[
            "validate"
        ],

        //错误信息查看
        debug: 1,

        //文件映射
        map: [
            //可配置版本号
            //['.css', '.css?v=' + version],
            //['.js', '.js?v=' + version]
        ],

        // 文件编码
        charset:function(url){
            if(url.indexOf(".gbk.")>-1){
                return "GBK";
            }
            return "UTF-8";
        }
    };
    var alias = config.alias;
    var v = typeof(rev) == "undefined" ? '' : '?v='+rev;
    var suffix = (debug ? ".js" : ".min.js");
    for (var key in alias){
        if(alias[key].indexOf(".min")==-1 && alias[key].indexOf("WdatePicker")==-1 && alias[key].indexOf(".js")!=-1){
            alias[key] = alias[key].replace(/\.js/, suffix) + v;
        }
    }
    seajs.config(config);
})();