/**
 * Created by liuyi
 *
 */
define(function (require, exports, module) {
    var common = require('common');
    exports.init = function (form) {
        // 授权事件初始化
        $(form).submit(function(){
            $(form['send']).prop("disabled","disabled").css("background-color","#88ceea").html("正在授权...");
            $.getJSON("/finance/sso/platformAuthorize.json",$(form).serialize(), function(data) {
                if(data.succ){
                    var goto = form['goto'] && form['goto'].value || '';
                    layer.msg && layer.msg(data.msg,{icon:1,time:1500},function(){
                        callback( goto.length ? goto : "/finance/uc/per/valid/step2")
                    }) || common.msg('<p align="center">'+data.msg+'</p>',function(){
                        callback( goto.length ? goto : "/finance/m/per/set/name/step-2")
                    });
                }else{
                    layer.msg && layer.msg(data.msg,{icon:2}) || common.msg('<p align="center">'+data.msg+'</p>');
                    $(form['send']).prop("disabled",false).css("background-color","#00AAEE").html("授权登陆");
                }
            });
        });
    };

    function callback(path) {
        $.getJSON("/finance/sso/clean.json",function () {
            location.href= path ;
        });
    }


});