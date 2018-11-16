/**
 * Created by Pg on 2017/2/9.
 * 视频播放扩展 v2.0
 */
(function($box){

    // 视频切换
    function updateCurr(arr,currIndex,type) {

        // 关闭播放视频
        var video = $box.find('li.curr video')[0];
        if(video){
            video.pause();
        }

        var currLeft = currIndex -1;
        if(currLeft < 0){
            currLeft = arr.length -1;
        }
        var currRight = currIndex +1;
        if(currRight >= arr.length){
            currRight = 0;
        }

        arr.removeAttr('class');
        if(type == -1){
            // 左
            arr.eq(currIndex).addClass('curr canplay');
            arr.eq(currLeft).addClass('right canplay');
            arr.eq(currRight).addClass('left canplay');
        }else if(type == 1){
            // 右
            arr.eq(currIndex).addClass('curr canplay');
            arr.eq(currLeft).addClass('right canplay');
            arr.eq(currRight).addClass('left canplay');
        }
        $box.find('dl a').eq(currIndex).addClass('curr').siblings().removeAttr('class')
    }

    /** .video 相关 */
    $box

        // 容器触发视频播放
        .on('click','li.curr.canplay',function(){
            $(this).removeClass('canplay').find('video')[0].play();
        })

        // 标题关联视频切换
        .on('click','dl a',function(){
            var mi = this, arr = $box.find('li'), oldCurr = $box.find('li.curr'),
                currIndex = $(mi).index()/2, oldCurrIndex = oldCurr.index();
            if(currIndex != oldCurrIndex)
            updateCurr(arr,currIndex,currIndex > oldCurrIndex?1:-1);
        })

        // 视频切换
        .on('click','.arrow',function () {
            var mi = this, curr = $box.find('li.curr'), arr = $box.find('li'),
                index = curr.index(), currIndex = 0 , type = 0;
            if($(mi).hasClass('prev')){
                type = -1;
                currIndex = index -1;
                if(currIndex < 0){
                    currIndex = arr.length -1;
                }
            }else if($(mi).hasClass('next')){
                type = 1;
                currIndex = index +1;
                if(currIndex >= arr.length){
                    currIndex = 0;
                }
            }
            updateCurr(arr,currIndex,type);
        })

        /** 视频控件相关 */
        .find('video')

        // 视频单击触发播放
        .on('click',function(){
            if(this.paused){
                this.play();
            }else{
                this.pause();
            }
        })
        // 进度条变更事件
        .on('timeupdate',function(){
            if(!window.take_timer) {
                var mi = this, tool = $(mi).siblings('.tool'), timer = tool.find('.timer'),
                    curr = mi.currentTime.toFixed(1), dura = mi.duration.toFixed(1);

                timer.find('i').css({width: (curr / dura).toFixed(2) * 100 + '%'});
                timer.find('.l').html((curr - curr % 60) / 60 + ":" + (curr % 60).toFixed(0));
                timer.find('.r').html((dura - dura % 60) / 60 + ":" + (dura % 60).toFixed(0));
                timer.find('.next').css({width: 100 - (curr / dura).toFixed(2) * 100 + '%'});
            }
        })

        // 播放完毕 清空进度
        .on('ended',function(){
            var mi = this, tool = $(mi).siblings('.tool');
            tool.closest('li').addClass('canplay');
            tool.find('.timer i').css({width:0+'%'});
            tool.find('.next').css({width:'100%'});
            tool.find('.l').html('0:00');
        })
        // 播放 关联状态
        .on('play',function(){
            $(this).siblings('.tool').find('.play').addClass('curr');
        })
        // 关闭 关联状态
        .on('pause',function(){
            $(this).siblings('.tool').find('.play').removeClass('curr');
        })


        /** 工具栏相关 */
        .siblings('.tool')

        // 播放控制
        .on('click','.play',function(){

            var mi = this, video = $(mi).closest('.tool').siblings('video')[0];

            if($(mi).hasClass('curr')){
                video.pause(); // 暂停
            }else{
                video.play(); // 播放
            }
        })
        // 视频最大化控制
        .on('click','.max',function(){
            var video = $(this).closest('.tool').siblings('video')[0];
            layer.open({
                type:2,
                title: false,
                area: ['90%', '90%'],
                shade: 0.5,
                shadeClose: true,
                content:[video.currentSrc,'no'],
                success:function($box){
                    $box.css({'background-color':'#000'});
                    video.pause();
                }
            });
        })
        // 触发 音量控制 拖拽
        .on('mousedown','.sound b',function(e) {
            window.take_sound = $(this).closest('i');
            window.py = e.pageY - window.take_sound.height();
            window.take_sound.closest('.tool');
        })
        // 自选音量控制
        .on('click','.sound label a',function(e){
            var mi = this, tool = $(mi).closest('.tool'), video = tool.siblings('video')[0],
                sound = tool.find('.sound'),
                my = sound.find('label').height(), curr = 1;

            // 左右计算不一样
            switch (mi.className){
                case 'prev':
                    curr = e.offsetY / my;
                    break;
                case 'next':
                    curr = (my - ($(mi).height() - e.offsetY)) / my;
                    break;
            }

            // 设置音量
            video.volume = 1 - curr.toFixed(1);

            // 同步控件
            sound.find('i').css({height: curr.toFixed(2) * 100 + '%'});
            sound.find('.next').css({height: (1 - curr.toFixed(2)) * 100 + '%'});

        })
        // 触发 进度条 拖拽
        .on('mousedown','.timer b',function(e){
            window.take_timer = $(this).closest('i');
            window.px = e.pageX - window.take_timer.width();
            window.take_timer.closest('.tool').siblings('video')[0].pause();
        })
        // 自选进度播放
        .on('click','.timer a',function(e){
            var mi = this, tool = $(mi).closest('.tool'), video = tool.siblings('video')[0],
                mx = $(mi).closest('label').width(), dura = video.duration, curr = 0;

            // 左右计算不一样
            switch (mi.className){
                case 'prev':
                    curr = e.offsetX/ mx * dura;
                    break;
                case 'next':
                    curr = (mx - $(mi).width() + e.offsetX) / mx * dura;
                    break;
            }
            // 进度变更
            video.currentTime = curr;
            // 如果视频在停止状态
            if(video.paused) video.play();
        });


        /** 小圆点拖拽 */
        $(window)

            // 拖拽结束
            .on('mouseup',function(e){
                if(window.take_timer){
                    // 进度条
                    var tool = window.take_timer.closest('.tool'),
                        video = tool.siblings('video')[0],
                        timer = tool.find('.timer'),
                        mp = timer.find('label').width(), np = e.pageX-window.px,
                        dura = video.duration.toFixed(1), curr = dura * np / mp;
                    // 同步控件
                    timer.find('i').css({width: (curr / dura).toFixed(2) * 100 + '%'});
                    timer.find('.l').html((curr - curr % 60) / 60 + ":" + (curr % 60).toFixed(0));
                    timer.find('.r').html((dura - dura % 60) / 60 + ":" + (dura % 60).toFixed(0));
                    // 播放时间变更
                    video.currentTime = curr;
                    // 如果视频在停止状态
                    if(video.paused) video.play();
                    // 删除操作标识
                    window.take_timer = !1;
                }else if(window.take_sound){
                    // 删除操作标识
                    window.take_sound = !1;
                }
            })
            // 拖拽
            .on('mousemove',function(e){
                if(window.take_timer){
                    // 进度条
                    var timer = window.take_timer.closest('.timer'),
                        mx = timer.find('label').width(), nx = e.pageX-window.px;
                    // 小圆移动
                    if(nx > 0&&nx < mx){
                        // 同步控件
                        window.take_timer.css({width:nx});
                        timer.find('.next').css({width: 100 - (nx / mx).toFixed(2) * 100 + '%'});
                    }
                }else if(window.take_sound){
                    // 音量条
                    var sound = window.take_sound.closest('.sound'), video = window.take_sound.closest('.tool').siblings('video')[0],
                        my = sound.find('label').height(), ny = e.pageY-window.py, curr = 1 - (ny / my).toFixed(1);
                    // 小圆移动
                    if(ny > 0&&ny < my){
                        // 同步控件
                        window.take_sound.css({height:ny});
                        sound.find('.next').css({height: 100 - (ny / my).toFixed(2) * 100 + '%'});
                        // 设置音量
                        video.volume = curr;
                    }
                }

            });


    // 初始化 视频位置
    var lis = $box.find('li');
    lis.first().addClass('curr');
    lis.eq(1).addClass('left');
    lis.last().addClass('right');

}($('.video')));
