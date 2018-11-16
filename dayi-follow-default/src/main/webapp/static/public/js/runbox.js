(function(fn){

    window.runbox = fn;

}(function(param){

    var childs = param.box.children,
        curr = 0,
        time = 500,
        autoTimer,
        fastTimer,
        barArr;

    function run(fn){

        fn && fn();

        each(childs,function(i,cell){
            switch (i){
                case curr : setCss(cell,3,200,-100); break;
                case prev() : setCss(cell,2,-244,-200); break;
                case next() : setCss(cell,2,677,-200); break;
                default : setCss(cell,0,200,-300);
            }
            if (barArr) switch (i){
                case curr : barArr[i].className = 'curr'; break;
                default : barArr[i].removeAttribute('class');
            }
        });

        !fastTimer && timer();
    }

    function prev(){
        return (curr - 1 < 0) ? childs.length - 1 : curr - 1;
    }

    function next(){
        return (curr + 1 >= childs.length) ? 0 : curr + 1;
    }

    function setCss(cell,i,x,z){
        cell.style.webkitTransform =
             cell.style.oTransform =
              cell.style.transform = 'translate3d('+x+'px,0,'+z+'px)';
        cell.style.webkitTransition =
             cell.style.oTransition =
              cell.style.transition = time+'ms ease-in-out';
        cell.style.zIndex = i;
    }

    function each(arr,callback){
        for(var i = 0;i < arr.length;i++){
            callback(i,arr[i])
        }
    }

    function fastTo(end){

        if (end === curr) return false;

        var mid = Math.ceil((childs.length - 1)/2),
            dis = Math.abs(end - curr),
            fastType,
            fastItv = function (){
                curr = fastType ? prev() : next();
                time = 200;
                run();
                if(end === curr){
                    clearInterval(fastTimer);
                    fastTimer = !1;
                    time = 500;
                }
            };

        fastType = (end < curr && dis <= mid) || (end > curr && dis > mid);
        fastTimer = setInterval(fastItv,200);
        longTimer();
    }

    function barInit(){
        each(childs,function(i){
            var bar = document.createElement('i');
            param.bar.appendChild(bar);
            bar.onmouseup = function(){
                fastTo(i);
            }
        });
        barArr = param.bar.children;
        barArr[0].className = 'curr';
    }

    function prevInit(){

        param.prev.onmouseup = function(){
            curr = prev();
            fastTimer = !1;
            run();
            longTimer();
        }

    }

    function nextInit(){

        param.next.onmouseup = function(){
            curr = next();
            fastTimer = !1;
            run();
            longTimer();
        }

    }

    function timer(){
        autoTimer && clearTimeout(autoTimer);
        autoTimer = setTimeout(function(){
            curr = next();
            run();
        },param.timer||5000);
    }

    function longTimer(){
        autoTimer && clearTimeout(autoTimer);
        autoTimer = setTimeout(timer,5000)
    }

    childs.length && run(function(){

        param.bar && barInit();

        param.prev && prevInit();

        param.next && nextInit();

        param.box.onmousemove = longTimer;

    })

}));