var addFlag = true;
var getGroupId = $('#updateGroupId').attr('value');
var getRealName = $('#updateRealName').attr('value');
var $peopleNumber = $('#peopleNumber');
//接收人
//录入页面
function addUserAjax() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "famsAnounceGroupController.do?datagrid&field=id,name,count,user&user&state=1",
        contentType: '',
        data:{},
        success:function (res) {
            var data = res.rows;
            if(data !== ''){
                addSelectChange(data);

            }else {
                alert('请检查网络')
            }
        }
    });
}
//录入页面下拉框交互
function addSelectChange(data){
    $('#send_pattern').on('change',function () {
        //当前选中的option与接收人相同
        var $value = $("#receiver option"+"[value="+this.value+"]");
        //接收人的当前选中
        $value.attr('selected',true).siblings().attr('selected',false);
        if(this.value === 'custom'){  //选择分组
            $('#receiver').show().attr('disabled',false);
            $('#dropBox').hide();
            $value.removeAttr('selected'); //当前不选中
            $('#receiver option').hide(); //先隐藏全部
            if(data != '' && addFlag){  //第一次进来发送请求,拼接标签
                addFlag = false;
                $.each(data,function (i,item) {
                    var option_html = '<option value="'+item.id+'" count="'+item.count+'" name="addOption">'+item.name+'</option>';
                    $('#receiver').append(option_html);
                });
                //当前不选中,数据第一个选中
                $value.nextAll('option[name="addOption"]:first').attr('selected',true);
            }else if(data == ''){
                $('#receiver').append('<option value="" selected>无数据</option>')
                $('#peopleNumber').text('0'); //清空总人数
            }else {
                $('#receiver option[name="addOption"]').show(); //再次进来时将隐藏的显示出来
                //当前不选中,数据第一个选中
                $value.nextAll('option[name="addOption"]:first').attr('selected',true);
            }
            //接收人的总人数
            peopleNumber();
        }else if(this.value === 'current'){ //选择账号
            $('#receiver option').show()
                .nextAll('option[name="addOption"]').hide();
            $('#receiver').hide();
            $('#dropBox').show();
            //接收人的总人数
            currentNumber();
        }else if(this.value === 'all'){  //全员发送
            //下拉选择的交互
            addOperation();
            //接收人的总人数
            allPeopleNum($peopleNumber,$('#allNumber'))
        }else if(this.value === 'internal'){ //飞管内部
            //下拉选择的交互
            addOperation();
            //接收人的总人数
            allPeopleNum($peopleNumber,$('#internalNumber'))
        }
        if(this.value === ''){
            //下拉选择的交互
            addOperation();
            $peopleNumber.text('0');
        }
    })
}
//人数获取
function peopleNumber() {
    var count = $('#receiver option:selected').attr('count');
    $('#peopleNumber').text(count);
}
//人数获取
function currentNumber() {
    if($('#names').val() !== ''){
        $peopleNumber.text($peopleNumber.attr('count'))
    }else {
        $peopleNumber.text('0')
    }
}
function addOperation() {
    $('#receiver option').show()
        .nextAll('option[name="addOption"]').hide();
    $('#receiver').show().attr('disabled',true);
    $('#dropBox').hide();
}
//编辑页面
function updateUserAjax() {
    if(getGroupId == 'all' || getGroupId == 'internal'){
       return
    }else {
        if(getRealName === ''){ //为空,说明是选择分组模式
            $.ajax({
                type : "get",
                dataType : "json",
                url : "famsAnounceGroupController.do?datagrid&field=id,name,count,user&user",
                contentType : '',
                data : {},
                success : function(res) {
                    var data = res.rows;
                    if (data !== '') {
                        $('#receiver option').hide(); //先隐藏全部
                        $.each(data, function(i, item) {
                            if (item.id === getGroupId) {//匹配选中
                                var option_html = '<option value="'+item.id+'" count="'+item.count+
                                    '" name="addOption" selected>'+item.name+'</option>';
                                $('#receiver').append(option_html);
                                // $("#receiver option" + "[value=" + item.id + "]").attr('selected', true)
                            }
                        });
                        //初始化接收人的总人数
                        peopleNumber();
                        //发送模式下拉选择
                        // updateSelectChange()
                    } else {
                        alert('请检查网络')
                    }
                }
            });
        }
    }

}
//总人数获取
function allPeopleNum (obj1,obj2){
    obj1.text(obj2.attr('value'));
}

/*暂时不使用*/
/*//留言
function addreplyFun(obj){
    var $addReplyContent = $('#addReplyContent');
    $addReplyContent.attr('disabled',false);
    var businessId = $('#businessId').attr('value');
    var $text = $(obj).text();
    if($text === '添加回复'){
        $addReplyContent.val('');  //每次进来文本框置空
        $('#addReplyText').slideDown();
        $(obj).text('提交回复');
    }else {
        layer.load(2); //loading
        var contents = $addReplyContent.val();
        var params = {
            'id':businessId,
            'content':contents,
            'module':'notify'
        };
        if(contents !== ''){
            commonAjax('post','famsAnnounceNotifyController.do?replyToBusiness',params,
                function (res) {
                    if(res.ok){
                        // window.location.href = location.href  //刷新页面
                        layer.closeAll('loading'); //loading关闭
                        var data = res.data;
                        var dateTime = data.date.substring(0,16);
                        var content_html = '<li class="line-height30">' +
                            '<div class="clearfix receiveBox pdr10">' +
                            '<input type="hidden" class="btn_sub toUerId"' +
                            'value="'+data.id+'" />' +
                            '<div class="receiveName float-left">' +
                            '<span class="color-green">'+data.name+'</span>-' +
                            '<span class="font-bold">'+data.department+':</span>' +
                            '<span class="receiveMsg">'+data.content+'</span>' +
                            '</div>' +
                            '<span class="receiveTime float-right color-999">'+dateTime+'</span>' +
                            '</div></li>';
                        $('#itemList .leverUl').prepend(content_html);
                        //留言的数量
                        var relyCountNum = parseInt($('#relyCount').text()) + 1;
                        $('#relyCount').text(relyCountNum);
                    }else {
                        layer.closeAll('loading'); //loading关闭
                        layer.alert('回复添加失败,请检查网络', {icon: 2});
                    }
                })
        }else {
            layer.closeAll('loading'); //loading关闭
            layer.msg('没有提交内容哦!');
        }
        $(obj).text('添加回复');
        $('#addReplyText').slideUp();

    }
}
//回复点击事件
function clickReceive(obj) {
    //评论回复
    var valText = $(obj).text();
    var $receiveContent = $(obj)
        .next('.receiveContent');
    var $leverItemUl = $(obj).closest('li').find('.leverItemUl:first');
    if (valText === '回复') {
        $(obj).text('提交').next('.receiveContent').removeClass('hide');
        $receiveContent.children().val(''); //先清空
    } else if (valText === '提交') {
        layer.load(2); //loading
        //请求后台
        var toUserId = $(obj).parents('.receiveBox').find('.toUerId').val();
        var content = $receiveContent.children().val();
        var businessId = $("#businessId").val();
        if (content !== '') {
            var params = {
                'id':toUserId,
                'content':content,
                'module':'notify'
            };
            commonAjax('get','famsAnnounceNotifyController.do?replyToReply',params,function (res) {
                if (res.ok) {
                    //	window.location.href = location.href;
                    layer.closeAll('loading'); //loading关闭
                    var name = res.data.name;
                    var department = res.data.department;
                    var replyName = res.data.replyName;
                    var replyDepartment = res.data.replyDepartment;
                    var date = res.data.date.substring(0,16);
                    var replyContent = res.data.content;
                    var id = res.data.id;

                    var text_html = '<li>' +
                        '<div class="clearfix replyBox">'
                        + '<input type="hidden" class="btn_sub toUerId"'
                        + 'value="'+id+'" />'
                        + '<div class="receiveName float-left">'
                        + '<span class="color-green">'+name+'</span>-'
                        + '<span class="font-bold">'+department+'</span>'
                        + ' 回复 '
                        + '<span class="color-green">'+replyName+'</span>-'
                        + '<span class="font-bold">'+replyDepartment+':</span>'
                        + '<span class="receiveMsg ">'+replyContent+'</span>'
                        + '</div>'
                        + '<span class="receiveTime float-right color-999">'+date+'</span>'
                        + '</div></li>';

                    $leverItemUl.append(text_html)
                } else {
                    layer.closeAll('loading'); //loading关闭
                    layer.alert('回复添加失败,请检查网络', {icon: 2});
                }
            })
        } else {
            layer.closeAll('loading'); //loading关闭
            layer.msg('没有提交内容哦!');
        }
        $(obj).text('回复').next('.receiveContent').addClass('hide');
    }
}
//评论折叠
function foldList(obj) {
    $(obj).each(function (i,item) {
        if(i>1){
            $(item).hide();
            $(this).closest('ul').next('.moreBtn').show();
        }
    })
}
//点击更多展开折叠
function moreList(self){
    $(self).hide().prev('ul').children('li:hidden').show();
}*/

/**
 * url      请求接口
 * params   传参
 * callback 回调函数
 * errback  失败回调
 * */
function commonAjax(type,url,params,callback,errback) {
    $.ajax({
        type: type,
        async: true,
        url: url,
        dataType: 'json',
        data: params,
        success:callback,
        error:errback
    })
}

//时间戳转日期
function formatDateTime(time,string) {
    var t=new Date(time);
    var year=t.getFullYear();
    var month=t.getMonth()+1;
    var day=t.getDate();
    var hours=t.getHours();
    var minutes=t.getMinutes();
    var seconds=t.getSeconds();
    if(string){
        if(string === 'yyyy-MM-dd HH:mm'){
            return year+"-"+(month<=9?'0'+month:month)+"-"+(day<=9?'0'+day:day)+" "+(hours<=9?'0'+hours:hours)+":"+(minutes<=9?'0'+minutes:minutes);
        }else {
            layer.msg('日期格式错误')
        }
    }else {
        return year+(month<=9?'0'+month:month)+(day<=9?'0'+day:day)+(hours<=9?'0'+hours:hours);
    }

}

/**
 * 对输出文字进行溢出省略处理
 * @param value为文字输出参数
 * @param length为处理文字输出的具体个数
 * @returns
 */
function renderSubstring(value, length) {
    if (value === '' || value === null) return "";
    if (value.length > length) return "<span title='" + value + "'>" + value.substring(0, length) + " ...</span>";
    return value;
}

//接收人的总人数监听
$('#receiver').change(function () {
    var count = $(this).find('option:selected').attr('count');
    $('#peopleNumber').text(count);
});

/*暂时不需要*/
/*$(document).ready(function () {
    //评论折叠
    foldList('.leverUl>li');
    $('ul.leverItemUl').each(function (i,item) {
        var $liItem = $(item).children('li');
        foldList($liItem);
    })

})*/

/*编辑页面交互*/
/*function updateSelectChange() {
    $('#send_pattern').on('change', function() {
        //当前选中的option与接收人相同
        var $value = $("#receiver option" + "[value=" + this.value + "]");
        //接收人的当前选中
        $value.attr('selected',true).siblings().attr('selected', false);
        if (this.value === 'custom') {  //选择分组
            $('#receiver').attr('disabled',false);

            if(getRealName !== ''){  //不为空,是选择账号模式
                //当前不选中,默认数据第一个选中
                $value.nextAll('option[name="addOption"]:first').attr('selected',true);
            }else {
                var groupOption = $("#receiver option" + "[value=" + getGroupId + "]");
                //是否为添加的option
                if (groupOption.attr('name') !== 'addOption') {
                    //当前不选中,默认数据第一个选中
                    $value.nextAll('option[name="addOption"]:first').attr('selected',true);
                } else {
                    //当前不选中,返回id的那条选中
                    groupOption.attr('selected',true)
                }
            }

            $('#receiver option').hide()
            .nextAll('option[name="addOption"]').show();//先隐藏全部宰显示添加的
            $('#receiverSelect').removeClass('hide');
            $('#dropBox').addClass('hide');
            //初始化接收人的总人数
            peopleNumber();
        }else if(this.value === 'current'){  //选择账号
            $('#receiverSelect').addClass('hide');
            $('#dropBox').removeClass('hide');
            //接收人的总人数
            currentNumber()
        }if(this.value === 'all'){
            //下拉选择的交互
            updateOperation();
            //接收人的总人数
            allPeopleNum($peopleNumber,$('#allNumber'))
        }else if(this.value === 'internal'){  //飞管内部
            //下拉选择的交互
            updateOperation();
            //接收人的总人数
            allPeopleNum($peopleNumber,$('#internalNumber'))
        }
        if(this.value === ''){
            //下拉选择的交互
            updateOperation();
            $peopleNumber.text('0');
        }
    })
}
function updateOperation() {
    $('#receiver option').show()
        .nextAll('option[name="addOption"]').hide();
    $('#receiverSelect').removeClass('hide');
    $('#dropBox').addClass('hide');
    $('#receiver').attr('disabled',true);
}*/



