/**
 * 表单验证规则扩展
 * @class addMethod
 * */
(function( factory ) {
    if ( typeof define === "function" && define.amd ) {
        define( ["jquery", "validate"], factory );
    } else if (typeof module === "object" && module.exports) {
        alert(0);
        module.exports = factory(require("jquery"));
    } else {
        factory( jQuery );
    }
}(function( $ ) {

    function stripHtml(value) {
        // Remove html tags and space chars
        return value.replace(/<.[^<>]*?>/g, " ").replace(/&nbsp;|&#160;/gi, " ")
        // Remove punctuation
            .replace(/[.(),;:!?%#$'\"_+=\/\-“”’]*/g, "");
    }

    $.validator.addMethod("maxWords", function (value, element, params) {
        return this.optional(element) || stripHtml(value).match(/\b\w+\b/g).length <= params;
    }, $.validator.format("不能超过{0}字"));
    $.validator.addMethod("minWords", function (value, element, params) {
        return this.optional(element) || stripHtml(value).match(/\b\w+\b/g).length >= params;
    }, $.validator.format("不能少于{0}字"));
    $.validator.addMethod("rangeWords", function (value, element, params) {
        var valueStripped = stripHtml(value),
            regex = /\b\w+\b/g;
        return this.optional(element) || valueStripped.match(regex).length >= params[0] && valueStripped.match(regex).length <= params[1];
    }, $.validator.format("请输入{0}~{1}字"));


    /**
     * 只支持字母格式
     * */
    $.validator.addMethod("letter", function (value, element) {
        var reg = /^[a-zA-Z]+$/;
        return this.optional(element) || (reg.test(value));
    }, "只支持字母格式，如abc");
    /**
     * 只支持字母、数字格式
     * */
    $.validator.addMethod("letterNum", function (value, element) {
        var reg = /^[a-zA-Z0-9]+$/;
        return this.optional(element) || (reg.test(value));
    }, "只支持字母、数字格式，如HT0120");
    /**
     * 只支持字母、数字格式、中、下横字符
     * */
    $.validator.addMethod("letterNum_g", function (value, element) {
        var reg = /^[A-Za-z0-9_-]+$/;
        return this.optional(element) || (reg.test(value));
    }, "只支持字母、数字格式，如HT0120");
    /**
     * 邮编格式
     * */
    $.validator.addMethod("isZipCode", function (value, element) {
        var reg = /^[0-9]{6}$/;
        return this.optional(element) || (reg.test(value));
    }, "邮编格式不正确");

    /*手机正则*/
    var mobileReg = /^((1[3-9][0-9])+\d{8})$/;
    var telReg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
    /**
     * 手机格式
     * */
    $.validator.addMethod("isMobile", function (value, element) {
        return this.optional(element) || (mobileReg.test(value));
    }, "手机格式不正确");

    //固话、传真,传真格式与固话是一样的
    $.validator.addMethod("isTel", function(v, e) {
        return this.optional(e) || telReg.test(v);
    },"请输入正确的电话号码");

    //号码，固话与手机都可以
    $.validator.addMethod("allPhone", function(v, e) {
        return this.optional(e) || mobileReg.test(v)||(telReg.test(v));
    },"请输入正确的号码:区号-电话号码/手机号");

    /**
     * 是某个数的正数倍
     * */
    $.validator.addMethod("sizeTimes", function (value, element, param) {
        var size = $(param).val();
        return this.optional(element) || (Math.round(value*1000)%size==0);
    }, "数量必须是包装规格的整数倍");

    /**
     * 正整数
     * */
    $.validator.addMethod("isInt", function (value, element) {
        var reg = /^[0-9]\d*$/;
        return this.optional(element) || (reg.test(value));
    }, "只能为正整数");

    /**
     * 日期格式
     * */
    $.validator.addMethod("isdate", function (value, element) {
        var reg = /(((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9]))|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9]))|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9]))|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29))|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29))|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29))|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29))|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29))|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29))|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29))|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)))((\s+(0?[1-9]|1[012])(:[0-5]\d){0,2}(\s[AP]M))?$|(\s+([01]\d|2[0-3])(:[0-5]\d){0,2})?$))/;
        return this.optional(element) || (reg.test(value));
    }, "日期格式不正确");

    $.validator.addMethod("noSpace", function (value, element) {
        var reg = /(^\s+)|(\s+$)/g;
        return this.optional(element) || (!reg.test(value));
    }, "不能含有空格");
    $.validator.addMethod("zh_cn", function (value, element) {
        var reg = /^[\u4e00-\u9fa5]+·?[\u4e00-\u9fa5]+$/i;
        return this.optional(element) || (reg.test(value));
    }, "只支持汉字");
    $.validator.addMethod("usernameformat", function (value, element) {
        var reg = /^\w+$/;
        return this.optional(element) || (reg.test(value));
    }, "只能由数字,英文字母或者下划线组成");
    $.validator.addMethod("isPassword", function (value, element) {
        var reg = /^([a-zA-Z]+\d+)|(\d+[a-zA-Z]+)|(\d+[~!@#$%_^&*.?]+)|([~!@#$%_^&*.?]+\d+)|([~!@#$%_^&*.?]+[a-zA-Z]+)|([a-zA-Z]+[~!@#$%_^&*.?]+)$/;
        var reg1 = /^([0-9a-zA-Z]|[~!@#$%_^&*.?])+$/;
        return this.optional(element) || (reg.test(value) && reg1.test(value));
    }, "英文字母、数字或符号，不能是纯数字，格式如a888888");

    $.validator.addMethod("notEqualTo", function (value, element, param) {
        var name = $(param).val();
        return this.optional(element) || value != name;
    }, $.validator.format("密码不能和用户名重复"));

    //验证值不允许与特定值等于
    jQuery.validator.addMethod("notEqual", function(value, element,param) {
        return this.optional(element) || value != param;
    }, $.validator.format("输入值不允许为{0}!"));

    // 验证值必须大于特定值
    jQuery.validator.addMethod("gt", function(value, element, param){
        return this.optional(element) || parseFloat(value) > parseFloat(param);
    }, $.validator.format("输入值必须大于{0}!"));

    // 验证值必须小于特定值
    jQuery.validator.addMethod("lt", function(value, element, param){
        return this.optional(element) || parseFloat(value) < parseFloat(param);
    }, $.validator.format("输入值必须小于{0}!"));

    // 不大于特定值
    jQuery.validator.addMethod("notgt", function(value, element, param){
        return this.optional(element) || parseFloat(value) <= parseFloat(param);
    }, $.validator.format("输入值不能大于{0}!"));

    // 不小于特定值
    jQuery.validator.addMethod("notlt", function(value, element, param){
        return this.optional(element) || parseFloat(value) >= parseFloat(param);
    }, $.validator.format("输入值不能小于{0}!"));

    // 不大于特定值
    jQuery.validator.addMethod("notgtTo", function(value, element, param){
        var size = $(param).val();
        return this.optional(element) || parseFloat(value) <= parseFloat(size);
    }, $.validator.format("输入值不能大于数量"));

    // 不小于特定值
    jQuery.validator.addMethod("notltTo", function(value, element, param){
        var size = $(param).val();
        return this.optional(element) || parseFloat(value) >= parseFloat(size);
    }, $.validator.format("输入值不能小于这个数!"));

    //小数位数
    jQuery.validator.addMethod("decimals", function(value, element,param) {
        var reg=/^(([0-9])|([1-9]\d+))(\.\d{1,2})?$/;
        if(param==1){
            reg=/^(([0-9])|([1-9]\d+))(\.\d{1})?$/;
        }else if(param==3){
            reg=/^(([0-9])|([1-9]\d+))(\.\d{1,3})?$/;
        }
        return this.optional(element) || reg.test(value);
    }, "小数位不能超过{0}位");

    //银行卡号验证（简单）
    $.validator.addMethod("isBankNo", function (value, element) {
        var reg = /^[0-9]{16,19}$/;
        return this.optional(element) || (reg.test(value.replace(/[^\d]/g, '')));
    }, $.validator.format("银行卡号格式不正确"));

    //车牌号码验证（简单）
    $.validator.addMethod("isCarNo", function(value, element){
        var reg = /^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$|^[a-zA-Z]{2}\d{7}$ /;
        return this.optional(element) || (reg.test(value));
    },"请输入正确的车牌号码，大小写不区分");

    //车牌号码验证（简单）不带汉字验证的
    $.validator.addMethod("isCarNo2", function(value, element){
        var reg = /^[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$|^[a-zA-Z]{2}\d{7}$ /;
        return this.optional(element) || (reg.test(value));
    },"请输入正确的车牌号码，大小写不区分,如：SD0g11");

    $.extend($.validator.messages, {
        required: "不能为空",
        remote: "请修正该字段",
        email: "请输入正确的email",
        url: "请输入正确的网址",
        date: "请输入正确的日期",
        dateISO: "请输入正确的日期 (ISO).",
        number: "请输入数字",
        digits: "只能输入整数",
        creditcard: "请输入正确的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入正确后缀名的字符串",
        maxlength: $.validator.format("请输入一个长度不多于{0}的字符串"),
        minlength: $.validator.format("请输入一个长度不少于{0}的字符串"),
        rangelength: $.validator.format("请输入一个长度介于{0}和{1} 之间的字符串"),
        range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: $.validator.format("请输入一个最大为{0} 的值"),
        min: $.validator.format("请输入一个最小为{0} 的值"),
        mobile: "请输入正确的手机号码"
    });

}));







