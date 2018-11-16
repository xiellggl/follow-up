/*
 * author: Tracy
 * email: cuixigame@gmail.com
 * date: 2014.7.13
 * slideX(水平切换)、slideY(纵向切换)
 * last edit: jianhai 2015/11/9
 * edit note: 给rotate_item 也加上width跟height 以免children比parent的宽度要小，出现内容溢出的现象;专门改造过，为适应专场而用
 */
;(function(factory){

	if(typeof define == 'function'){
		define('rotate', ['jquery'], function(require, exports, module){
			factory('jquery');
			//return 'jquery';
		})
	}

})(function(){

	$.fn.rotate = function(options){

		// 默认参数
		var defaults = {
			effects				: "slideX",			// String 滚动效果
			delay				: 500,				// Number 滚动时间
			interval			: 5000,				// Number 滚动时间间隔
			multiple			: 1,				// Number 滚动视窗中显示元素个数
			slideNum			: 1,				// Number 每次滚动的元素个数
			easing				: "swing",			// String 缓动效果
			enterStop			: false				// Boolean 鼠标划上是否悬停
		};

		if(this.length > 1){
			this.each(function(){
				$(this).rotate(options);
			});
		}else if(this.length == 0){
			return this;
		}

		var rotate = this;
		rotate.config = {};
		rotate.count = 0;
		rotate.patterns = {};
		rotate.interval = null;
		rotate.slideList = rotate.find(".rotate");
		if(!rotate.slideList.length){ return false; }
		rotate.slideLi = rotate.slideList.find(".rotate_item");
		if(!rotate.slideLi.length){ return false; }
		rotate.len = rotate.slideLi.length;

		if(rotate.slideList.is(':hidden')){
			//父元素是否隐藏
			var rotatePar = rotate.slideList.parent(),
				roateParHidden = rotatePar.is(':hidden'),
				slideList = rotate.slideList;

			if(roateParHidden){
				//原父元素样式
				var oldParDisplay = rotatePar.css('display'),
					oldParPosition = rotatePar.css('position'),
					oldParLeft = rotatePar.css('left');

				rotatePar.css({
					display: 'block',
					position: 'absolute',
					left: '-99999px'
				});
			}
			//将隐藏列表显示
			var oldListDisplay = slideList.css('display'),
				oldListPosition = slideList.css('position'),
				oldListLeft = slideList.css('left');

			slideList.css({
				display: 'block',
				position: 'absolute',
				left: '-99999px'
			});

			//获取列表长宽
			rotate.width = rotate.slideLi.innerWidth();
			rotate.height = rotate.slideLi.innerHeight();

			oldParLeft = oldParLeft ? oldParLeft : 0;
			oldListLeft = oldListLeft ? oldListLeft : 0;

			//将列表父级隐藏
			rotatePar.css({
				display: oldParDisplay,
				position: oldParPosition,
				left: oldParLeft
			});

			//将列表隐藏
			slideList.css({
				display: oldListDisplay,
				position: oldListPosition,
				left: oldListLeft
			});

		}else{
			rotate.width = rotate.slideLi.innerWidth();
			rotate.height = rotate.slideLi.innerHeight();
		}

		rotate.thresholdElem = [];

		// 初始化函数
		var init = function(options){
			// 合并参数
			rotate.config = $.extend({}, defaults, options);
			// 如果元素总数小于等于视窗中显示元素个数
			if(rotate.len <= rotate.config.multiple){ return false; }

			// 如果元素总数小于视窗中显示元素个数和滚动元素个数总和
			//if(rotate.len < rotate.config.multiple + rotate.config.slideNum){ return false; }

			//克隆滚动列表
			rotate.slideLi.clone().appendTo(rotate.slideList);

			// 初始化样式
			initLayout();

			// 自动轮播
			autoSlide();

			// 鼠标悬停
			if(rotate.config.enterStop){
				rotate.slideList.on("mouseenter", function(){
					clearInterval(rotate.interval);
				});
				rotate.slideList.on("mouseleave", function(){
					autoSlide();
				});
			}
		};

		// 样式初始化
		var initLayout = function(){
			var size, sizeType, floatType,
				effects = rotate.config.effects,
				multiple = rotate.config.multiple,
				elemWidth = rotate.width,
				elemHeight = rotate.height,
				slideList = rotate.slideList.find(".rotate_item"),
				slideLen = slideList.length,
				mulWidth = elemWidth,
				mulHeight = elemHeight;

			if( effects == "slideX"){
				sizeType = "width";
				floatType = "left";
				mulWidth = multiple * elemWidth;
				size = elemWidth * slideLen;
			}else{
				sizeType = "height";
				floatType = "none";
				mulHeight = multiple * elemHeight;
				size = elemHeight * slideLen;
			}

			rotate.css({
				position: "relative",
				overflow: "hidden",
				width: mulWidth,
				height: mulHeight
			});

			rotate.slideList.css({
				position: 'absolute',
				left: 0
			}).css(sizeType, size);

			slideList.css("float", floatType);

		};

		// 自动轮播
		var autoSlide = function(){
			var effects = rotate.config.effects;
			clearInterval(rotate.interval);
			rotate.interval = setInterval(function(){
				//判断滚动列表是否显示，不显示则禁止滚动
				var isHidden = rotate.slideList.is(':hidden');
				if(isHidden){return false;}
				rotate.patterns[effects]();
			}, rotate.config.interval);
		};

		// 轮播效果
		$.each({
			slideX: ["left", "width"],
			slideY: ["top", "height"]
		}, function(name, val){
			var posName = val[0],
				sizeType = val[1];

			rotate.patterns[name] = function(){
				var mulData = {},
					slideNum = rotate.config.slideNum,
					slidePos = parseInt(rotate.slideList.css(posName)),
					elemSize = parseInt(rotate.slideLi.css(sizeType)),
					thresholdVal = rotate.len * elemSize,
					delay = rotate.config.delay,
					easing = rotate.config.easing,
					distance;

				//判断轮播列表是否滚动完一圈
				if(slidePos + thresholdVal <= 0){
					rotate.slideList.css(posName, slidePos + thresholdVal);
				}else{
					rotate.count += slideNum;
				}

				distance = elemSize * slideNum;
				mulData[posName] = "-=" + distance;

				rotate.slideList
					.stop(true, true)
					.animate(mulData, delay, easing);
			};

		});

		// 初始化
		init(options);

		return this;
	};

});


