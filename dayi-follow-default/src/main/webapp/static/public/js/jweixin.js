var weiXin = function(config){

    wx.config({
        debug: config.debug,
        appId: config.appId,
        timestamp: config.timestamp,
        nonceStr: config.nonceStr,
        signature: config.signature,
        jsApiList: [
            'onMenuShareTimeline',
            'onMenuShareAppMessage'
        ]
    });
    wx.ready(function(){
        /**
         * 分享到朋友圈
         */
        wx.onMenuShareTimeline({
            title: config.title,
            link: config.link,
            imgUrl: config.imgUrl
        });

        /**
         * 分享给朋友
         */
        wx.onMenuShareAppMessage({
            title: config.title,
            desc: config.desc,
            link: config.link,
            imgUrl: config.imgUrl
        });

    });
};