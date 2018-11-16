/**
 * Created by wusan on 2018/1/26.
 * [塑如意|现货|门户] 用户中心 [登陆|注册] 模块 pc
 */
(function( $ , fn ){

    !!$ ? fn($) : console.error('缺少jQuery')

}( $ || window.jQuery || window.Zepto, function($){

    var
        // 加密 inputs
        rsaInputs = [];

    /// 处理 form 获取 btn 时的类型转换
    function give(obj){
        return Object.prototype.toString.apply(obj) === '[object RadioNodeList]' && obj[0] || obj;
    }

    /// 加密 input
    function rsaSET( crypt , key ){
        crypt.setKey(key);
        $.each(rsaInputs,function(i,ip,v){
            v = crypt.encrypt(ip.value);
            if(v){
                ip.past = ip.value;
                ip.value = v;
            }
        });
        return rsaInputs;
    }

    /// 去用户中心获取 key
    function getKEY( url , callback ){
        $.getJSON(url + "?jsonpCallback=?", function(rs){
            callback(rs.result);
        });
    }

    /// 加密处理
    function rsaForm( form , url , callback ){

        if(!(rsaInputs = $(form).find('.rsa')).length){
            callback()
        }else if(window.JSEncrypt){
            getKEY(url, function(key){
                callback(rsaSET(new JSEncrypt(), key));
            });
        }else{
            ///-- 获取 加密脚本
            $.getScript('/static/public/js/RSA/jsencrypt.min.js',function(){
                getKEY(url, function(key) {
                    callback(rsaSET(new JSEncrypt(), key));
                });
            });
        }
    }

    // 还原加密
    function clearRsa(){
        if(!!rsaInputs){
            $.each(rsaInputs,function(i,ip){
                ip.value = ip.past;
            });
        }
    }

    /// 初始化
    function init( op ){

        if(!op.form) return console.error('form is null');

        rsaForm( op.form , give(op.form['send']).value , function(){
            send( op.form , op.succback , op.errback , op.form['goto'].value )
        });

    }

    /// 发送校验
    function send( form , cussback , errback , goto ){

        var param = $(form).serialize();

        // 还原
        clearRsa();

        function callback(url, fn){
            if (typeof fn === "function") {
                return fn(url || goto)
            } else {
                ///---- 跳转新页面
                location.href = url || goto;
            }
        }

        // 禁用提交按钮
        give(form.send).disabled = true;

        $.getJSON( form.action+"?jsonpCallback=?" , param , function( rs ){

            if(rs.succ){
                var SSOURLS = rs.result && rs.result.ssoUrls
                if(!SSOURLS){
                    ///-- 成功回调
                    cussback(callback);
                }else{
                    (function sayHello(len) {
                        $.jsonp({
                            url: SSOURLS[len] + "&jsonpCallback=?",
                            complete: function () {
                                if (len <= 0) {
                                    ///-- 成功回调
                                    cussback(callback);
                                } else {
                                    sayHello(len-1)
                                }
                            }
                        })
                    }(SSOURLS.length-1))
                }
            }else{

                ///-- 错误回调
                if( Object.prototype.toString.apply(errback) === '[object Function]' ){
                    errback( rs );
                }else{
                    window.layer && (layer.msg && layer.msg('<p align="center">'+rs.msg+'</p>') || layer.open({content : rs.msg,className : 'msgStyle'})) || alert(rs.msg);
                }

            }
        }).fail(function(){
            // 发生错误时去掉按钮现在
            give(form.send).disabled = false;
            // 提示错误信息
            window.layer && (layer.msg && layer.msg("系统出了小差，请稍后再试..") || layer.open({content : "系统出了小差，请稍后再试..",className : 'msgStyle'})) || alert("系统出了小差，请稍后再试..");
        });
    }

    /// 外部调用

    // 表单加密
    window.rsaForm = $.rsaForm = rsaForm;
    // 表单加密
    window.clearRsa = $.clearRsa = clearRsa;
    // 登陆加密
    window.ucp = $.ucp = function (op) {
        init($.extend({
            form : document.forms['register']||document.forms['login'],
            succback : function(goto){ goto() }
        },op));
    }

}));