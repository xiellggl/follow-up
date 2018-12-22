define(function(require, exports, module) {
    require("layer");
    layer.config({
        path: '/static/public/js/layer/'
    });

    /**
     * 判断是否为数组
     * @param obj 对象
     * @returns {boolean}
     */
    exports.isArray = function(obj) {
        return Object.prototype.toString.call(obj) === '[object Array]';
    };
    /**
     * 判断是否为JSON
     * @param obj 对象
     * @returns {boolean}
     */
    exports.isJson = function(obj){
        return Object.prototype.toString.call(obj).toLowerCase() === "[object object]" && !obj.length;
    }
    /**
     * 字符串处理
     *
     * */
    exports.str={
        /**
         * 去头尾空格
         * @param {string} s 需要处理的字符串
         * */
        dTrim : function(s){
            if(s!=null && s!="") {
                return s.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');
            }else{
                return "";
            }
        },
        /**
         * url参数转json
         * @param {string} string 需要处理的字符串
         * @param {Boolean} [overwrite=false] 是否重写
         * @return {json} 返回json对象
         */
        par2Json:function(string, overwrite){
            var obj = {};
            var pairs = string.split('&');
            var d = decodeURIComponent;
            var name,value;
            $.each(pairs, function(i,pair) {
                pair = pair.split('=');
                name = d(pair[0]);
                value = d(pair[1]);
                obj[name] = overwrite || !obj[name] ? value : [].concat(obj[name]).concat(value);
            });
            return obj;
        },
        /**
         * 计算字符串字数
         * @param {string} str 需要处理的字符串
         * @param {string} [type=1] 计算方式 默认为：中文为1个字符，type=2中文为2个字符计算
         * @return {Int} 返回字符串个数
         */
        len : function(str,type){
            var Length=0;
            if(arguments.length>0){
                if(type==2){
                    for(var i=0;i<str.length;i++){
                        Length = Length + (str.charCodeAt(i)>128?2:1);//一个中文字算二,一个英文字算一
                    }
                }else{
                    for(var i=0;i<str.length;i++){
                        Length = Length + (str.charCodeAt(i)>128?1:0.5);//一个中文字算一,两个英文字算一
                    }
                }
                return Math.ceil(Length);//有半个字要+1
            }else{
                return null;
            }
        },
        //隐藏金额部分数字
        hideMoney:function (money) {
            var r = money.toFixed(2).toString();
            //两位数以下的不处处理
            if(money<100){
                return r;
            }
            var arr = r.split(".");
            var len = arr[0].length;
            var x = "*";
            for(var i=1;i<len-2;i++){
                x += "*";
            }
            return arr[0].substring(0,2) + x + "."+ arr[1];
        }
    };

    /**
     * 数组处理
     *
     * */
    exports.arr={
        /**
         * 数组去重,并移除空值
         * @param arr
         * @returns {Array}
         */
        unique:function(arr){
            var res=[],hash={};
            var len=arr.length;
            for(var i=0;i<len;i++) {
                if(!hash[arr[i]]){
                    if(arr[i]!=""){
                        res.push(arr[i]);
                    }
                    hash[arr[i]] = true;
                }
            }
            return res;
        },

        /**
         * sort数组排序
         * @param array
         * @param type 排序方式，默认1，为升序，-1为降序
         * @param [field] 排序字段 默认数组，选填；josn对象名称
         * @returns {*}
         */
        systemSort:function(array,type,field){
            type=type || 1;
            return array.sort(function(a, b){
                if(field){
                    return type * (a[field] - b[field]);
                }else {
                    return type * (a - b);
                }
            });
        }
    };

    /**
     * 秒钟倒计时
     * @param {$} jqObj 显示秒数容器
     * @param {$} secs 为设定秒数
     * @param {function}  callback 为0秒时回调接入口
     * */
    exports.loadSecond = function(jqObj,secs,callback){
        var i=secs;
        var t= setInterval(function(){
            if(i == 0) {
                clearInterval(t);
                if(typeof(callback) == "function") callback();
            }else{
                //jqObj.text('请等待 ' + i + ' s');
                jqObj.text(i);
            }
            i--;
        },1000);
    };

    /**
     * 刷新验证码
     * @param $captcha
     */
    exports.getCaptcha = function($captcha){
        var src = $captcha.attr('src').split('?')[0],
            time = +new Date(),
            newSrc = src + '?t=' + time;
        $captcha.attr('src', newSrc);
    };

    /**
     * 功能：全选/不全选/点选
     * @param {$} $chkAll 操作全选/不全选Checkbox
     * @param {$} $chkItem Checkbox项
     * @param {$} $selValue 隐藏表单项，用于存放选中值，于逗号隔开
     * @param {function} checkCallBack 选中回调
     */
    exports.checkBox = function($chkAll,$chkItem,$selValue,checkCallBack){
        $chkAll.off("change");
        $chkItem.off("change");
        $chkAll.change(function(){
            $chkAll.prop('checked',this.checked);
            $chkItem.prop('checked',this.checked);
            __checkValue();
        });
        $chkItem.change(function(e){
            e.stopPropagation();
            $chkAll.prop('checked', $chkItem.size() == $chkItem.filter(':checked').size());
            __checkValue();
        });
        var __checkValue=function(){
            if(typeof checkCallBack =="function"){
                checkCallBack();
            }
            if($selValue==null){
                return;
            }
            var str=[];
            $chkItem.filter(':checked').each(function(){
                str.push($(this).val());
            });
            $selValue.val(str+"");
        };
    };

    /**
     * 判断是否为IE
     * @return {Number} 返回IE版本（6-9），其他返回-1
     * */
    exports.isIE=function(){
        if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/6./i)=="6."){
            return 6
        }else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/7./i)=="7."){
            return 7
        }else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/8./i)=="8."){
            return 8
        }else if(navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i)=="9."){
            return 9
        }
        return -1;
    };

    /**
     * 对日期进行格式化，
     * @param {date} date 要格式化的日期
     * @param {string} format 进行格式化的模式字符串
     *     支持的模式字母有：
     *     y:年,
     *     M:年中的月份(1-12),
     *     d:月份中的天(1-31),
     *     h:小时(0-23),
     *     m:分(0-59),
     *     s:秒(0-59),
     *     S:毫秒(0-999),
     *     q:季度(1-4)
     * @return String
     */
    exports.dateFormat=function(date, format){
        if(date==null){
            return;
        }
        date = new Date(date);
        var map = {
            "M": date.getMonth() + 1, //月份
            "d": date.getDate(), //日
            "h": date.getHours(), //小时
            "m": date.getMinutes(), //分
            "s": date.getSeconds(), //秒
            "q": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        format = format.replace(/([yMdhmsqS])+/g, function(all, t){
            var v = map[t];
            if (v !== undefined) {
                if (all.length > 1) {
                    v = '0' + v;
                    v = v.substr(v.length - 2);
                }
                return v;
            }
            else if (t === 'y') {
                return (date.getFullYear() + '').substr(4 - all.length);
            }
            return all;
        });
        return format;
    };

    //数字格式化
    exports.formatNumber=function(s,n){
        var n_ = n;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for(i = 0; i < l.length; i ++ )
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        if(n_ == 0){
            return t.split("").reverse().join("")
        }
        return t.split("").reverse().join("") + "." + r;
    };

    /*Cookie*/
    exports.Cookie={
        _defaults:{
            hours:24*30,	//几个钟后终止(默认1个月)
            path:null,		//可访问cookie的目录
            domain:null,	//window.location.host.indexOf("dayi35.com")!=-1 ? "dayi35.com" : null, 	//可访问主机名
            secure:false 	//数据传输加密
        },
        set:function(name,value,options){
            var opts=$.extend(this._defaults ,options);
            var expires="";
            if(opts.hours != null){
                var date = new Date();
                var ms = opts.hours*3600*1000;
                date.setTime(date.getTime() + ms);
                expires= "; expires=" + date.toGMTString();
            }
            document.cookie = name + "=" + escape (value) + expires +
                ((opts.path == null) ? "" : ("; path=" + opts.path)) +
                ((opts.domain == null) ? "" : ("; domain=" + opts.domain)) +
                ((opts.secure == true) ? "; secure" : "");
        },
        get:function(name){
            var cookie = document.cookie,
                arr = cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
            if(arr != null){
                return unescape(arr[2]);
            }
            return null;
        },
        remove:function(name,options){
            var opts=$.extend(this._defaults ,options);
            opts.hours=-1;
            this.set(name,'',opts);
        }
    };

    /**
     * 标识当前页状态
     * 作用：主要用于网站的导航当前页标识，通用索引项
     * @param {$} $obj 父对象
     * @param {String} subTag 子标签名
     * @param {Number} index 当前所在的索引位置
     * @param {String} [className="current"]
     * 选中样式名｜默认为“current”。
     * */
    exports.markCurrentStatus = function($obj,subTag,index,className){
        var _className=className || "current";
        if($obj.attr("data-type")=="unFixed"){
            $obj.find('[data-id="'+index+'"]').addClass(_className);
        }else{
            $obj.find(subTag).eq(index).addClass(_className);
        }
    };

    /**
     * 输入后回车触发
     * @param {$} $inputs 输入框对象
     * @param {function} callback 回调
     * */
    exports.inputEnterCallback=function($inputs,callback){
        $inputs.on("keyup",function (e) {
            var event = window.event || e;
            if ((event.keyCode || event.which) == 13) {
                callback();
            }
        });
    };

    /**
     * 成功提示
     * @param msgStr 提示语
     * @param [endFn] 结束后回调 可选 默认为刷新
     */
    exports.successMsg=function (msgStr,endFn) {
        layer.msg('<div class="msg_success">' + msgStr + '</div>', {
            shade: 0.1,skin: "layui-layer-hui"
        }, function () {
            if(typeof endFn =="function"){
                endFn();
            }else if(endFn=="reload"){
                window.location.reload(true);
            }
        });
    };

    /**
     * ajax相关封装
     *
     */
    exports.ajax={
        //记录loading回去层索引
        loadingIndex:null,
        /**
         * 异步操作登录超时处理(用于拦截器的页面)
         * @param callback
         */
        sessionTimeout:function(callback){
            $(document).ajaxError(function(event, xhr, opt, error){
                var loginMarkReg=/<title>.*?登录.*?<\/title>/;
                if(loginMarkReg.test(xhr.responseText)){
                    layer.alert('<p class="tc cRed">登录超时</p>',{title:"温馨提示",end:function(){
                        if(typeof callback == "function"){
                            callback(opt);
                        }else{
                            //刷新页面
                            window.location.reload(true);
                        }
                    }});

                }
            });
        },

        /**
         * ajax处理事件封装
         * @param {json} options 参数配置入口
         * @return request
         */
        handle:function(options) {
            var This=this;
            var defaults = {
                method: "post",
                url: '',
                data: null,
                processData : true,
                contentType : "application/x-www-form-urlencoded",
                dataType: "json",
                jsonp:false,
                async: true,
                $btn: null,
                isLoadding: false,
                timeout:0,
                offlineback: null,  //登录下线回调入口
                succback: null,     //成功返回回调入口
                failback: null       //错误返回回调入口
            };
            var opts = $.extend(defaults, options);
            var url = opts.url;
            url += url.indexOf("?") == -1 ? "?" : "&";
            url += "t=" + new Date().getTime();
            var request;
            if(request!= null){
                request.abort();
            }
            request = $.ajax({
                method: opts.method,
                url: url,
                data: opts.data,
                processData : opts.processData,
                contentType : opts.contentType,
                async: opts.async,
                dataType: opts.dataType,
                jsonp:opts.jsonp,
                timeout:opts.timeout,
                beforeSend: function () {
                    if (opts.isLoadding) {
                        // area 设定宽高（解决框架偏移）
                        This.loadingIndex = layer.load(16, {shade: [0.1, '#fff'],area:["60px","24px"]});
                    }
                    if (opts.$btn) {
                        opts.$btn.prop("disabled", true);
                    }
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                if (This.loadingIndex) {
                    layer.close(This.loadingIndex);
                }
                layer.alert('<p class="tc cRed">请求出错，请稍后重试！</p>', {title: "温馨提示"})

                if (opts.$btn) {
                    opts.$btn.prop("disabled", false);
                }
            }).done(function (data) {
                //html类型
                if(opts.dataType == "html" || opts.dataType == null){
                    //ajax重定向没办法捕捉，所以只能通过页面特定字符来判断跳到登录页面了
                    var loginMarkReg=/<title>.*?登录.*?<\/title>/;
                    if(loginMarkReg.test(data)){
                        layer.alert('<p class="tc cRed">登录超时</p>',{title:"温馨提示",end:function(){
                            //刷新页面
                            window.location.reload(true);
                        }});
                        return false;
                    }
                    if(typeof opts.succback == "function") {
                        opts.succback(data);
                    }
                    return;
                }
                if(data.succ===true || data.succ==="true" || data.retCode ==="success" || data.retCode ==="SUCCESS"){
                    if (typeof opts.succback == "function") {
                        opts.succback(data);
                    } else {
                        exports.successMsg(data.msg,"reload");
                    }
                }else if(data.status=="300" || data.status=="310" || data.isLogin==false){
                    layer.alert('<p class="tc cRed">登录超时</p>',{title:"温馨提示",end:function(){
                        if(typeof opts.offlineback == "function"){
                            opts.offlineback(data);
                        }else if(opts.offlineback == "reload"){
                            //刷新页面
                            window.location.reload(true);
                        }else{
                            //跳转到登录页面
                            var url=window.location.pathname;
                            window.location.href = "/followup/login?goTo=" + encodeURIComponent(url);
                        }
                    }});
                }else {
                    if (opts.$btn) {
                        opts.$btn.prop("disabled", false);
                    }
                    if (typeof opts.failback == "function") {
                        opts.failback(data);
                    } else {
                        layer.alert('<p class="tc cRed">' + data.msg + '</p>', {title: "温馨提示"});
                    }
                }
            }).always(function (XMLHttpRequest,status) {
                if (This.loadingIndex) {
                    layer.close(This.loadingIndex);
                }
                if(status=='timeout'){
                    request.abort();
                    layer.alert('<p class="tc cRed">请求超时，请稍后重试！</p>', {title: "温馨提示"})
                }
            });
            return request;
        }
    };

    /**
     * 功能：页面HTML加载
     * @url 页面url
     * @data 搜索参数序列化对象
     * @$pageListPanel 页面HTML容器，默认为$("#htmlContainer") 也用作一个回调
     */
    exports.loadPageHTML = function (url, data, $pageListPanel, isLoading) {
        var $panel = $pageListPanel || $("#htmlContainer");
        var _isLoading = isLoading == null ? true : isLoading;
        exports.ajax.handle({
            type: "get",
            url: url,
            data: data,
            isLoadding: _isLoading,
            dataType: "html",
            succback: function (html) {
                if (exports.ajax.loadingIndex) {
                    layer.close(exports.ajax.loadingIndex);
                }
                if (typeof $pageListPanel == "function") {
                    $pageListPanel(html);
                } else {
                    $panel.html(html);
                }
            }
        });
    };
    exports.clickPageFn = function (url, data, $pageListPanel, isLoading) {
        $pageListPanel.on('click', '.pages a', function () {
            var pn = $(this).data("id");
            var p = url.indexOf("?") == -1 ? "?" : "&";
            var _url = url + p + 'pageNo=' + pn;
            exports.loadPageHTML(_url, data, $pageListPanel, isLoading);
            return false;
        });
    };

    //导出功能
    exports.exportFn=function(url,data){
        data=decodeURIComponent(data,true);
        // 获得url和data
        if( url && data ){
            // data 是 string 或者 array/object
            data = typeof data == 'string' ? data : $.param(data);
            // 把参数组装成 form的  input
            var inputs = '';
            $.each(data.split('&'), function(){
                var pair = this.split('=');
                inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
            });
            // request发送请求
            $('<form action="'+ url +'" method="post" target="_blank">'+inputs+'</form>').appendTo('body').submit().remove();
        }
    };

    /**
     * 按钮状态修改操作
     */
    exports.btnStateOperate = function() {
        $('body').on('click','.state-btn',function () {
            var url = this.href,
                val = $(this).data('id'),
                $btn=$(this);
            exports.ajax.handle({
                url:url,
                data:{x:val},
                succback:function (data) {
                    var className = data.data.status == 1 ? "btn-yellow" : "btn-danger";
                    var btn = '<button class="btn btn-minier '+className+'">'+data.data.txt+'</button>';
                    $btn.html(btn).attr('title',data.msg);
                    return false;
                }
            });
            return false;
        });
    };

    /**
     * 按钮询问操作
     */
    exports.btnConfirmOperate = function() {
        $('body').on('click','.confirm-rst-url-btn',function () {
            var url = this.href,
                info = $(this).data('info');
            layer.confirm(info, {icon: 3}, function (index) {
                layer.close(index);
                exports.ajax.handle({
                    url: url
                });
            });
            return false;
        });
    };

    /**
     * 更新排序
     * @param $form
     */
    exports.listOrder = function ($form) {
        $form.on('click','#btnorder',function () {
            var url = $(this).attr("href");
            if (!url) {
                url = $form.data('action');
            }
            exports.ajax.handle({
                url: url,
                data: $("input.list_order").serialize()
            });
            return false;
        });
    };

    /**
     * 选择多条操作
     * @param $form 列表表单
     */
    exports.checksOption = function ($form) {
        $form.on('click','[data-rel="multiTermBtn"]',function () {
            var url = $(this).attr("href");
            if (!url) {
                url = $form.attr('action');
            }
            var chk_value = [];
            $form.find('input[data-rel="id"]:checked').each(function () {
                chk_value.push($(this).val());
            });
            if (!chk_value.length) {
                layer.alert('至少选择一个项', {icon: 5});
                return false;
            }
            exports.ajax.handle({
                url:url,
                data:$('input[data-rel="id"]').serialize()
            });
            return false;
        });
    };

    exports.head = function(n) {
        //导航标识
        var $navSide = $("#navSide");

        //非菜单显示页面标识
        if(n){
            $navSide.find('[data-path="'+n+'"]').not("active").addClass("active").closest("ul").closest("li").addClass("open");
        }

        var $userMenu = $("#userMenu");
        //查看个人信息
        var $modal = $("#myModalInfo");
        $userMenu.find('[data-act="info"]').on("click",function () {
            exports.loadPageHTML("/followup/uc/myinfo",null,function (html) {
                $modal.html(html).show(300);
                require.async("clipboard",function () {
                    var clipboard = new Clipboard("#copyLink");
                    clipboard.on("success",function (e) {
                        exports.successMsg('<div class="msg_success">复制成功</div>');
                    });
                    clipboard.on("error",function (e) {
                        var html='<p class="tc red">复制失败，请手动复制下面网址</p>' +
                            '<p>'+e.text+'</p>';
                        layer.alert(html,{title:"温馨提示"});
                    });
                });
            });
        });



        //修改密码
        $userMenu.find('[data-act="pwd"]').on("click",function () {
            exports.loadPageHTML("/followup/uc/pwd",null,function (html) {
                $modal.html(html).show(300);
                var $form = $modal.find("form");
                require.async(["validate","addMethod"],function () {
                    $form.validate({
                        rules:{
                            oldPwd: "required",
                            newPwd:{
                                required: true,
                                isPassword: true,
                                minlength: 8,
                                maxlength: 20
                            },
                            confirmNewPwd: {
                                required: true,
                                equalTo: "#newPwd"
                            }
                        },
                        messages:{
                            oldPwd:"请输入旧密码",
                            newPwd:{
                                require: "请输入新密码",
                                isPassword: "密码须包含数字、字母或下划线",
                                minlength: "请输入8至20位密码",
                                maxlength: "请输入8至20位密码"
                            },
                            confirmNewPwd: {
                                required: "请再次确认密码",
                                equalTo: "两次密码输入不一致"
                            }
                        },
                        errorPlacement: function (error, element) {
                            var $tipsBox = element.closest(".form-group").find(".tips_box");
                            if ($tipsBox.length) {
                                $tipsBox.html(error);
                            } else {
                                element.after(error);
                            }
                        },
                        errorClass: "field-error",
                        success: function (label, element) {
                            label.remove();
                            return true;
                        },
                        submitHandler: function (form) {
                            exports.ajax.handle({
                                url: "/followup/uc/update/pwd.json",
                                data: $form.serialize(),
                            });
                            return false;
                        }
                    });
                });
            });
        });


        //退出登录
        $("#btnLogout").on("click",function () {
            var url = this.href,
                info = $(this).data('info');
            layer.confirm(info, {icon: 3}, function (index) {
                layer.close(index);
                window.location.href = url;
            });
            return false;
        });

        $(function () { $('[title]').tooltip(); });

    }




});