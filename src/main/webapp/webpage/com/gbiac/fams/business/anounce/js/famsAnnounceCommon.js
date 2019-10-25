//时间戳转日期
function formatDateTime(time,dateTime) {
    var t=new Date(time);
    var year=t.getFullYear();
    var month=t.getMonth()+1;
    var day=t.getDate();
    // var hours=t.getHours();
    // var minutes=t.getMinutes();
    // var seconds=t.getSeconds();
    if(dateTime){
        return year+"-"+(month<=9?'0'+month:month)+"-"+(day<=9?'0'+day:day)+" "+dateTime.hours+":"+dateTime.minutes
            +":"+dateTime.seconds
    }else {
        return year+"-"+(month<=9?'0'+month:month)+"-"+(day<=9?'0'+day:day)
    }
}

function clickEnvent(event,obj1,obj2) {
    $(event).click(function () {
        var state = $(event).attr('state');
        if(state == 0){
            var timestamp =Date.parse(new Date()); //获取当前时间戳
            if(obj2){
                $(obj1).val(formatDateTime(timestamp,{hours:'00',minutes:'00',seconds:'00'}));
                $(obj2).val(formatDateTime(timestamp,{hours:'23',minutes:'59',seconds:'59'}))
            }else {
                $(obj1).val(formatDateTime(timestamp))
            }
            $(event).attr('state',1)

        }else if(state == 1){
            if(obj2){
                $(obj1).val('');
                $(obj2).val('');
            }else {
                $(obj1).val('');
            }
            $(event).attr('state',0)
        }
    })
}