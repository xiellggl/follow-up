
$.fn.createSelect = function(options,template){
    //默认参数
    var defaults = {
        classify: false,                                            //是否有品名分类
        classifyName: '',                                           //存在品名分类，需提供类名
        productUrl: dayihost+'/uc/brand/all/prodname',                      //品名列表接口
        productTypeUrl: dayihost+'/uc/brand/name/list',                    //品名分类列表接口
        factoryUrl: dayihost+'/uc/brand/all/factory',                       //厂家列表接口
        brandUrl: dayihost+'/uc/brand/brand/list',                           //牌号搜索接口
    selectList: [],                                                  //分类品名厂家牌号选择器列表
        hasHotList: true
    };

    //当前对象
    var selectModule = this;

    //初始化函数
    var init = function(options){
        selectModule.config = $.extend({}, defaults, options);
        faintSearch();
    };

    //获取品名牌号数据
    var faintSearch = function(){
        var productUrl = selectModule.config.productUrl,
            productTypeUrl = selectModule.config.productTypeUrl,
            selectList = selectModule.config.selectList,
            hasClassify = selectModule.config.classify;


        //获取品名列表
        $.getJSON(productUrl+"?callback=?", function(proData){
            //转换品名列表数据格式
            var productList = getDataList(proData.items, 'productName');
            //获取所有品名
            var hasHot = selectModule.config.hasHotList,
                brandList = chinMatch(productList),
                brandObj = getDataObj(brandList),
                brandDataObj = {
                    'typeName' : '品名',
                    'dataObjList' : brandObj
                };

            //获取厂家列表
            $.getJSON(selectModule.config.factoryUrl+"?callback=?", function(facData){
                //转换厂家列表数据格式
                var factoryList = getDataList(facData.items, 'name');
                //获取所有厂家
                var facList = chinMatch(factoryList),
                    facObj = getDataObj(facList),
                    factoryDataObj = {
                        'typeName' : '厂家',
                        'dataObjList' : facObj
                    };

                var bindSearchElement = selectList.slice(1,3);

                for(var i=0; i<bindSearchElement.length; i++){
                    var elementSearch = bindSearchElement[i];
                    //移除列表
                    $(elementSearch).on('keyup', function(){
                        var sortVal = $(this).val(),
                            hasSortLayer = $('.sort_layer').length;
                        if(sortVal && !!hasSortLayer){
                            $('.sort_layer .sort_link').off();
                            $('.sort_layer').remove();
                        }
                    });

                    //显示列表
                    if(hasHot){
                        $(elementSearch).on('click', function(){
                        var dataList, tempList,
                            element = $(this),
                            elementVal = element.val(),
                            fieldType = $(this).attr('data-type'),
                            zIndex = $('.layui-layer').css('zIndex'),
                            scrollTop = parseInt($(document).scrollTop()),
                            innerHeight = parseInt(element.innerHeight()),
                            innerWidth = parseInt(element.innerWidth()),
                            offsetLeft = parseInt(element.offset().left),
                            offsetTop = parseInt(element.offset().top);

                        innerWidth = innerWidth > 268 ? innerWidth : 268;

                        if(elementVal != ''){ return false; }

                        if(fieldType == 'productName'){
                            dataList = brandDataObj;
                        }else if(fieldType == 'factoryName'){
                            dataList = factoryDataObj;
                        }

                        tempList = template('temp_list', dataList);

                        $(tempList).appendTo($('body'));

                        $('.sort_layer').css({
                            position: 'absolute',
                            left: offsetLeft,
                            top: offsetTop + innerHeight,
                            width: innerWidth,
                            'z-index' : zIndex + 1
                        });

                        $('.sort_layer').on('click', '.sort_link', function(){
                            var sortVal = $(this).html();
                            element.val(sortVal);
                            $('.sort_layer .sort_link').off();
                            $('.sort_layer').remove();
                            return false;
                        });
                    });
                    }
                }

                if(hasHot){
                    $(document).on('click', function(event){
                        var target = event.target,
                            isLayer = $(target).is('.sort_layer'),
                            isField = $(target).is('input'),
                            hasSortLayer = $('.sort_layer').length;
                        if(!isLayer && !isField && !!hasSortLayer){
                            $('.sort_layer .sort_link').off();
                            $('.sort_layer').remove();
                        }
                    });
                }

                if(hasClassify){
                    var classifyObj = selectModule.config.classifyName;
                    var createProductSelect = function(dataList){
                        //查询品名
                        $(selectModule).chSearch({
                            relative: false,
                            searchBox: [selectList[1]],
                            dataList: [dataList]
                        });
                    };

                    var getTypeData = function(params){

                        $.getJSON(selectModule.config.productTypeUrl+"/" + params+"?callback=?", function(data){
                            var productDataList = getDataList(data.items, 'productName');
                            createProductSelect(productDataList);
                        });
                    };

                    //选择默认品名分类
                    var defaultProType = $(classifyObj).val();
                    getTypeData(defaultProType);

                    //选择品名分类
                    $(classifyObj).on('change', function(){
                        var typeElement = selectList[0],
                            typeVal = $(this).val();

                        for(var i=0; i<selectList.length; i++){
                            $(selectList[i]).val('');
                        }

                        getTypeData(typeVal);
                        $(typeElement).val(typeVal);
                    });
                }else{
                    //查询品名
                    $(selectModule).chSearch({
                        relative: false,
                        searchBox: [selectList[1]],
                        dataList: [productList]
                    });
                }

                //查询厂家
                $(selectModule).chSearch({
                    relative: false,
                    searchBox: [selectList[2]],
                    dataList: [factoryList]
                });

                //查询牌号
                $(selectModule).chSearch({
                    relative: true,
                    mustRelative: true,
                    searchBox: [selectList[3]],
                    dataType: ['brandNumber'],
                    relativeSearchBox: selectList,
                    searchLink: [selectModule.config.brandUrl]
                });


            });
        });
    };

    //将拼音与中文的对应关系添加到数组中
    var chinMatch = function(data){
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

    //将首字母与列表对应
    var getDataObj = function(data){
        var dataObj = [],
            letters = 'abcdefghijklmnopqrstuvwxyz',
            lettersLen = letters.length;
        for(var i=0; i<lettersLen; i++){
            var dataList = [],
                letter = letters[i];
            $.each(data, function(index, item){
                var element = item[0],
                    firstLetter = element.charAt(0).toLowerCase();
                if(firstLetter == letter){
                    dataList.push(item[1]);
                }
            });

            var dataLen = dataList.length;
            if(dataLen > 0){
                var dataItemObj = {
                    'firstLetter': letter.toUpperCase(),
                    'dataList': dataList
                };
                dataObj.push(dataItemObj);
            }
        }
        //返回对象
        return dataObj;
    }

    //对返回的数据进行处理
    var getDataList = function(data, dataType){
        var dataList = [];

        $.each(data, function(index, item){
            dataList.push(item[dataType]);

        });
        return dataList;
    };

    //初始化
    init(options);
}