/**/

$.fn.chSearch = function(options){
    // 默认参数
    var defaults = {
        relative : true,                     // 是否联动搜索
        mustRelative : false,                // 是否必须联动搜索
        searchBox : [],                      // 传入模糊搜索
        relativeSearchBox : [],              // 关联输入框
        searchLink : [],
        dataList : [],
        dataType : [],
        size : ['auto', 'auto']                // 下拉框的大小，size[0]为宽度，size[1]为高度，auto会设置为输入框宽度
    };

    var chSearch = this;

    String.prototype.trim = function() {
        return this.replace(/(^\s*)|(\s*$)/g,"");
    }

    //初始化
    var init = function(options){
        chSearch.config = $.extend({}, defaults, options);
        var searchBox = chSearch.config.searchBox;

        // 联动搜索
        searchFn(searchBox);
    };

    //绑定搜索
    var searchFn = function(element){

        /*
        * 遍历所有输入框，绑定事件
        * 获取焦点时，查询出所有符合条件的数据
        * 键盘输入时，从获取的数据中进行模糊查询
        * */
        $.each(element, function(index, item){

            var dataSearch,
                relativeSearchBox = chSearch.config.relativeSearchBox,
                searchLink = chSearch.config.searchLink;

            //输入框获取焦点时，查询出所有符合条件的数据
            $(item).on('focus', function(){
                var that = this,
                    serStr = '', serData = {},
                    isRelative = chSearch.config.relative,
                    mustRelative = chSearch.config.mustRelative,
                    dataList = chSearch.config.dataList,
                    dataType = chSearch.config.dataType;

                $.each(relativeSearchBox, function(index2, item2){
                    var dataType = $(item2).attr('data-type'),
                        itemData = $(item2).val();

                    serData[dataType] = itemData;
                    serStr += itemData;
                });

                //判断关联查询的字段是否为空
                if((serStr == '' || !isRelative) && !mustRelative){
                    dataSearch = dataMatch(dataList[index]);
                }else{
                    if(serStr == ''){ return false; }
                    $.getJSON(searchLink[index]+"?callback=?", serData, function(data){
                        var resultList = [],
                            dataItems = data.items,
                            dataList = getDataList(dataItems, dataType[index]);
                        dataSearch = dataMatch(dataList);

                        //拼音首字母查询
                        //遍历索引，执行查找
                        for (var i = 0; i < dataSearch.length; i++){
                            //截取等长字符与筛选字母组合进行比较
                            resultList.push(dataSearch[i][1]);
                        }

                        // 显示下拉选择框
                        if(resultList.length == 0){
                            //移除下拉列表
                            removeSelectList();
                            return false;
                        }
                        createSelectList(that, resultList);
                    });
                }
            });

            //输入框失去焦点时，移除下拉框
            $(item).on('blur', function(){
                //移除下拉列表
                removeSelectList();
            });

            //键盘输入时，从获取的数据中进行模糊查询
            $(item).on('keyup', function(){
                //通过“event.keyCode”得到按下的实际字母
                var that = this,
                    resultList = [],
                    hasChin = false;
                    keyCache = $(this).val().trim(),                    //去除空格
                    keyLength = keyCache.length;                        //获取筛选内容长度

                if(keyCache == ''){
                    //移除下拉列表
                    removeSelectList();
                    return false;
                }

                //判断输入框中是否包含中文字符
                for(var i=0; i<keyLength; i++){
                    var unicode = keyCache.charCodeAt(i);
                    //如果不在汉字处理范围之内,返回原字符,也可以调用自己的处理函数
                    if(unicode <= 40869 && unicode >= 19968){
                        hasChin = true;
                    }
                }

                if(!dataSearch.length){return false;}

                //中文查询
                if(hasChin){
                    for(var i=0; i<dataSearch.length; i++){
                        var pos = dataSearch[i][1].indexOf(keyCache);
                        if(pos > -1){
                            resultList.push(dataSearch[i][1]);
                        }
                    }
                }else{
                    keyCache = keyCache.toLowerCase();
                    //拼音首字母查询
                    //遍历索引，执行查找
                    for (var i = 0; i < dataSearch.length; i++){
                        //截取等长字符与筛选字母组合进行比较
                        var dataItem = dataSearch[i][0].substr(0, keyLength);
                        dataItem = dataItem.toLowerCase();

                        if ( dataItem == keyCache){
                            resultList.push(dataSearch[i][1]);
                        }
                    }
                }

                // 显示下拉选择框
                if(resultList.length == 0){
                    //移除下拉列表
                    removeSelectList();
                    return false;
                }
                createSelectList(that, resultList);

            });

        });
    };

    //将拼音与中文的对应关系添加到数组中
    var dataMatch = function(data){
        var nameList = [];
        for (var i = 0; i < data.length; i++){
            if (data[i] != ""){
                //获取拼音首字母缩写
                var arrRslt = makePy(data[i]);
                //将拼音与中文的对应关系添加到数组中
                nameList.push(new Array(arrRslt.toString(), data[i]));
            }
        }
        return nameList;
    };

    //使用查询到的数据创建下拉框
    var createSelectList = function(element, optionList){
        var createLen = $('body').find('.createSelect').length;
        if(createLen > 0){
            $('.createSelect').remove();
        }
        var selectUl = $('<ul class="createSelect">');
            eleLeft = parseInt($(element).offset().left),
            eleTop = parseInt($(element).offset().top),
            eleWidth = parseInt($(element).innerWidth()),
            eleHeight = parseInt($(element).outerHeight()),
            optWidth = chSearch.config.size[0],
            optHeight = chSearch.config.size[1];

        optWidth = optWidth == 'auto' ? eleWidth : parseInt(optWidth);
        optHeight = optHeight == 'auto' ? parseInt(eleHeight) : parseInt(optHeight);

        var selectList = '';
        for(var i=0; i<optionList.length; i++){
            var selectItem = '<li title=" ' + optionList[i] + ' ">' + optionList[i] + '</li>';
            selectList += selectItem;
            selectUl.append($(selectItem));
        }

        var posTop = parseInt(eleTop) + parseInt(optHeight) - 1;

        selectUl.css({
            position: 'absolute',
            left: eleLeft,
            top: posTop,
            width: optWidth,
            maxHeight: optHeight*5,
            overflow: 'scroll',
            'z-index': 999999999
        });

        $('body').append(selectUl);

        selectUl.on('click', 'li', function(){
            var selVal = $(this).html();
            $(element).val(selVal);
            selectUl.css('display', 'none');
        });

    };

    //移除下拉列表
    var removeSelectList = function(){
        var createLen = $('body').find('.createSelect').length;
        if(createLen > 0){
            setTimeout(function(){
                $('.createSelect').off();
                $('.createSelect').remove();
            }, 200);
        }
    };

    //数据格式转换
    var getDataList = function(data, dataType){
        var dataList = [];
        $.each(data, function(index, item){
            dataList.push(item[dataType]);
        });
        return dataList;
    };



    //初始化模糊查询
    init(options);

    return this;

};