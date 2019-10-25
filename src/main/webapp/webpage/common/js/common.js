/**
 * 右下角展示消息
 * @param msg 提示内容
 */
function showMeg(msg) {
	layer.open({
		title:'提示信息',
		offset:'rb',
		content:msg,
		time:3000,
		btn:false,
		shade:false,
		icon:1,
		shift:2
	});
}
function showMe(msg){
    layer.open({
        title:'提示信息',
        offset:'rb',
        content:msg,
        time:3000,
        btn:false,
        shade:false,
        icon:1,
        shift:2
    });
}

/**
 * 文件上传进度回调方法
 */
function onprogress(){
}
var imgsize=6;
var is=0;
var hasIs=0;
var type;

/**
 * 多文件上传
 * @param obj 操作对象
 * @param single 是否单文件上传
 */
function myUploadFile(fileNames,distinct,module,acceptNum,acceptFileType,value1,typeSwf,obj,single){
    if(acceptNum!=""&& acceptNum!=null){
        imgsize=Number(acceptNum);
    }
	var _this=$(obj).parent();
    //保存目录位置
	var directory=$(_this).attr("directory");
	var files = $(obj)[0].files;
	if(value1!=''){
        hasIs=value1.split(";").length;
    }
    if(files.length>imgsize){
        if(acceptFileType=="image/*"){
            layer.msg('上传图片不能超过'+imgsize+'个');
        }else{
            layer.msg('上传文件不能超过'+imgsize+'个');
        }
        return ;
    }
    is=is+files.length;
	if(is+hasIs>imgsize){
	    // is--;
        if(acceptFileType=="image/*"){
            layer.msg('上传图片不能超过'+imgsize+'个');
        }else{
            layer.msg('上传文件不能超过'+imgsize+'个');
        }
        is=is-files.length;
        return ;
    }
    type=files[0].type;
	if(acceptFileType=="image/*"){
	    if(files[0].name.split(".")[1].toUpperCase() != 'BMP' && files[0].name.split(".")[1].toUpperCase() != 'PCT'
            && files[0].name.split(".")[1].toUpperCase() != 'PNG' && files[0].name.split(".")[1].toUpperCase() != 'JPEG'
            && files[0].name.split(".")[1].toUpperCase() != 'GIF' && files[0].name.split(".")[1].toUpperCase() != 'TIFF'
            && files[0].name.split(".")[1].toUpperCase() != 'JPG' && files[0].name.split(".")[1].toUpperCase() != 'ICO'){
            is=is-files.length;
            layer.msg('图片格式不正确，请重新上传');
            return;
        }
        var filesize = (files[0].size / 1024).toFixed(2);
        if(filesize > 5120){
            layer.msg('图片大于5M，请重新上传');
            is=is-files.length;
            return;
        }

    }else if(acceptFileType.indexOf(',')!=-1){
	    var s=acceptFileType.split(',');
	    var flag=false;
	    for(var i=0;i<acceptFileType.split(',').length;i++){ 
	        if(((acceptFileType.split(',')[i]).toUpperCase()).indexOf(files[0].name.split(".")[files[0].name.split(".").length-1].toUpperCase())!=-1){
                flag=true;
                break;
            }
        }
        if(!flag){
            is=is-files.length;
            layer.msg('文件格式不正确，请重新上传');
            return;
        }
        var filesize = (files[0].size / 1024).toFixed(2);
	    if(files[0].type.indexOf('image')!=-1){
            if(filesize > 5120){
                layer.msg('图片大于5M，请重新上传');
                is=is-files.length;
                return;
            }
        }else{
            if(filesize > 5120*10){
                is=is-files.length;
                layer.msg('文件大于50M，请重新上传');
                return;
            }
        }

    }else {
        var filesize = (files[0].size / 1024).toFixed(2);
        if(files[0].type.indexOf('image')!=-1){
            if(filesize > 5120){
                layer.msg('图片大于5M，请重新上传');
                is=is-files.length;
                return;
            }
        }else{
            if(filesize > 5120*10){
                is=is-files.length;
                layer.msg('文件大于50M，请重新上传');
                return;
            }
        }
    }
    
    if(module!=null){
     //是否可以上传文件
     var canUp=true;
     var upFileNames=$('.filename');
	     for(var i=0;i<upFileNames.length;i++){
	     
	     	if(upFileNames[i].innerHTML.indexOf(files[0].name)!=-1){
	     	    canUp=false;
	    		break;
	    	}
	     }
	if(!canUp){
		showMeg(files[0].name+'文件已存在，请重新上传');
		is=is-files.length;
		return false;
	}     
    
    	
    }
	for(var i=0; i< files.length; i++){
		(function (file) {
            var btnDom = '';
            if(file.type.indexOf('image')>-1){
                btnDom = "<a class=\"mybutton\" onclick=\"open1(this)\">"+
                    "<i class=\"fa fa-search-plus\" aria-hidden=\"true\" ></i>"+
                    "</a>";
            }else {
                btnDom = "<a class=\"mybutton\" style=\"visibility: hidden;\">"+
                "<i class=\"fa fa-search-plus\" aria-hidden=\"true\" ></i>"+
                "</a>";
            }
            var dom=$(
                "<div class=\"picturebox\">"+
                	"<div class=\"top\">"+
                		"<img class=\"image\" onerror=\"this.style.opacity=0;$(this).parent().addClass('defaultImage');changeImage(this)\" onclick=\"open3(this)\">"+
                	"</div>"+
					"<div class=\"center\">"+
						"<div class=\"bar\">"+
							"<div class=\"\">"+
								"<div class=\"progress\" style=\"height: 7px\">"+
									"<div class=\"progress-bar progress-bar-info progress-bar-striped active\" style=\"width: 0%;\">"+
									"</div>"+
								"</div>"+
							"</div>"+
						"</div>"+
						"<div class=\"filename\"></div>"+
						"<div class=\"detail fileSize\"></div>"+
					"</div>"+
					"<div class=\"bottom\">"+ btnDom +
						"<a class=\"mybutton fileId\" onclick=\"myRemoveFile(this,"+single+")\">"+
							"<i class=\"fa fa-trash\" aria-hidden=\"true\" ></i>"+
						"</a>"+
					"</div>"+
                "</div>");
			if(module!=null){
				if(!checkFileName(module,file.name)){
				  is=is-files.length;
				  return ;
				};
			}
			$(_this).prev().append(dom);
			//文件form对象
			var formData = new FormData();
			//文件对象    
			formData.append("files",file);
			formData.append("directory", directory);
			formData.append("typeSwf", typeSwf);
            formData.append("distinct", distinct);
            formData.append("module", module);
			
            window.sessionStorage.setItem("uploadSuccess","false");
			$.ajax({
				url: "famsCommonController.do?fileUpload",
				type: "POST",
				contentType: 'multipart/form-data',
				data: formData,
				async: true,
				cache: false,
				dataType:"json",
				processData: false, //告诉jQuery不要去设置Content-Type请求头
				contentType: false, //告诉jQuery不要去处理发送的数据
				//这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
				xhr: function(){
                    $(dom).find(".fileSize").html("("+(file.size/1024).toFixed(2)+"K)");
                    $(dom).find(".filename").html(file.name);
					var xhr = $.ajaxSettings.xhr();
					if(onprogress && xhr.upload) {
						xhr.upload.addEventListener("progress" , function(evt){
							var loaded = evt.loaded;     //已经上传大小情况
							var tot = evt.total;      //附件总大小 
							var per = Math.floor(100*loaded/tot);  //已经上传的百分比 
							if(typeSwf==1){
								if(per=100){
								  per=80;
								}
							}
							$(dom).find(".progress-bar").css("width" , per +"%");
							if(loaded==tot){//文件完成上传
                                $(dom).find(".progress-bar").removeClass("active");
							}
						}, false);
						return xhr;
					};
				},
				success: function(data) {
					if(data.success){
                        window.sessionStorage.setItem("uploadSuccess","true");
					$(dom).find(".progress-bar").css("width" ,  "100%");
						$(dom).find(".image").attr("src",data.obj.paths[0]);
                        $(dom).find(".fileId").attr("fileId",data.obj.ids[0]);
						var path=$(_this).prev().prev().prev().prev().val();
						if(path!=""){
							$(_this).prev().prev().prev().prev().val(path+";"+data.obj.ids[0]);
						}else{
							$(_this).prev().prev().prev().prev().val(data.obj.ids[0]);
						}
						if(single==true){//如果为单文件上传，则上传成功后隐藏上传控件
							$(obj).next().linkbutton("disable");
						}
					}else{
						showMeg(data.msg);
					}
				}  
			});
			if(i==files.length-1){
                $("#fileId").val("");
            }
		})(files[i]);
    }
}

function checkFileName(module,name){
		var flag=true;

		$.ajax({
				url: "famsRulesregulationsController.do?ifNameExist",
				type: "POST",
				data:{
					pid:module,
					rulesName:name,
					rulesType:1
				},
				async: false,
				cache: false,
				dataType:"json",
				success:function(data){
					if(!data.success){
						showMeg(data.msg);
						flag=false;
					}
				}
		})
		return flag;

}
function open1(e){
    if(type.indexOf('image')!=-1){
        var s=$(e).parent().parent().find('img').attr('src');
        if(s!=""){
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                area: [500 + 'px', 500 + 'px'], //宽高
                content: "<img width='500px' height='500px'  src=" + s + " />"
            });
        }
    }else{
       // var fileId=$(e).parent().parent().find(".fileId").attr("fileId");
       // var src=$(e).parent().parent().find(".image").attr("src");
	    //createwindow("预览","famsRulesregulationsController.do?openViewFile&fileid="+fileId+"&rulesPathUrl="+src,600,600);
    }
}
function open3(e){
    if(type.indexOf('image')!=-1){
        var s=$(e).attr('src');
        if(s!=""){
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                area: [500 + 'px', 500 + 'px'], //宽高
                content: "<img width='500px' height='500px'  src=" + s + " />"
            });
        }
    }else{
       // var fileId=$(e).parent().parent().find(".fileId").attr("fileId");
       // var src=$(e).parent().parent().find(".image").attr("src");
        // createwindow("预览","famsRulesregulationsController.do?openViewFile&fileid="+fileId+"&rulesPathUrl="+src,600,600);
    }
}
/**
 * 多文件删除
 * @param obj 操作对象
 * @param single 是否单文件
 */
function myRemoveFile(obj,single){
    layer.confirm('确定删除该文件吗？',{
        btn:['确认','取消']
    },function() {
        var newValue="";
        var _this=$(obj);
        var picBox=$(_this).parent().parent();
        var box=$(picBox).parent();
        var fileId=$(_this).attr("fileId");
        var oldvalue=$(box).prev().prev().prev().val();
        var arrayValue=oldvalue.split(";");
        for(var i=0;i<arrayValue.length;i++){
            if(arrayValue[i].indexOf(fileId)==-1){
                newValue+=arrayValue[i]+";";
            }
        }
        newValue=newValue.substring(0, newValue.length-1);
        $.ajax({
            url:"famsCommonController.do?removeFile",
            data:{
                fileId:fileId
            },
            dataType:"json",
            success:function(result){
                if(result.success){
                    showMeg(result.msg);
                    if(single==true){//如果为单文件上传，则删除成功后启用上传控件
                        $(box).next().find(".easyui-linkbutton").linkbutton("enable");
                    }
                    //删除图片
                    $(box).prev().prev().prev().val(newValue);
                    $(picBox).remove();
                    is--;
                }else{
                    showMeg(result.msg);
                }
            }
        });
    },function(){
        return;
    });

}

/**
 * 啥事也不干
 * @param a
 * @param b
 * @param c
 * @constructor
 */
function WdatePicker_bak(a,b,c) {

}

/**
 * 时间格式化处理
 * @param fmt
 * @param date
 * @returns {*}
 */
function dateFtt(fmt,date)
{ //author: meizz
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

/**
 * 检查时间大小顺序，id1<id2
 * @param obj 操作对象
 * @param id1 小的id
 * @param id2 大的id
 */
function checkTimeSequence(obj,id1,id2){
	var time1=$("#"+id1).val();
	var time2=$("#"+id2).val();
	if(time1!=""&&time2!=""&&time2<=time1){
        $(obj).val('');
        showMeg("结束时间必须大于开始时间！");
	}
}

/**
 * id1<id2并且时间在今天和明天范围内
 * @param obj
 * @param id1
 * @param id2
 */
function checkWorkTime(obj,id1,id2){
    var crtTime = dateFtt("yyyy-MM-dd",new Date());
    var tomorrow= dateFtt("yyyy-MM-dd",new Date(new Date().getTime() + 24*60*60*1000*2));
    var time1=$("#"+id1).val();
    var time2=$("#"+id2).val();
    if(time1!=""&&time2!=""){
        if(time1<crtTime||time2>tomorrow){
            $(obj).val('');
            showMeg("时间只能在今天和明天！");
        }else if(time1!=""&&time2!=""&&time2<=time1){
            $(obj).val('');
            showMeg("结束时间必须大于开始时间！");
        }
    }
}

/**
 * id1<id2 && id1>id3 && id2<id4
 * @param obj 操作对象
 * @param id1 小的id
 * @param id2 大的id
 */
function checkWorkFireTime(obj,id1,id2,id3,id4){
    var crtTime = dateFtt("yyyy-MM-dd",new Date());
    var tomorrow= dateFtt("yyyy-MM-dd",new Date(new Date().getTime() + 24*60*60*1000*2));
    var time1=$("#"+id1).val();
    var time2=$("#"+id2).val();
    var time3=$("#"+id3).val();
    var time4=$("#"+id4).val();
    var isUseFire = $('input[name="isUseFire"]:checked').val();
    if(time3!=""&&time4!=""){
        if(time3<crtTime||time4>tomorrow){
            $(obj).val('');
            showMeg("时间只能在今天和明天！");
        }else if(time4<=time3){
            $(obj).val('');
            showMeg("结束时间必须大于开始时间！");
        }
        if(isUseFire=='Y'){//需要动火
            if(time1!=""&&time1<time3){
                $("#"+id1).val(time3);
                showMeg("动火开始时间必须在施工时间内！");
            }else if(time2!=""&&time2>time4){
                $("#"+id2).val(time4);
                showMeg("动火结束时间必须在施工时间内！");
            }else if(time2!=""&&time1!=""&&time1>=time2){
                $(obj).val('');
                showMeg("动火结束时间必须大于动火开始时间！");
            }
        }else{
            $("#"+id1).val(time3);
            $("#"+id2).val(time4);
        }
    }
}
/**
 * 检查开始时间是否大于等于当天时间
 * @param obj
 * @param id1 开始日期
 * @returns
 */
function checkStartDate(obj,id1){
	var curDate = dateFtt("yyyy-MM-dd",new Date());
    var time1 = $("#"+id1).val();
    if(time1<curDate){
		$(obj).val("");
		showMeg('请确认是否日期大于或等于当前日期！');
	}
}
/**
 * 检查作业结束日期是否大于或等于作业开始日期
 * @param obj
 * @param id1 开始日期
 * @param id2 结束日期
 * @returns
 */
function checkEndDate(obj,id1,id2){
	var time1 = $("#"+id1).val();
	var time2 = $("#"+id2).val();
	if(time1>time2){
		$(obj).val("");
		showMeg('作业结束日期不能小于作业开始日期！');
	}
}
/**
 * 作业开始日期是否为同一天
 * @param obj
 * @param id1
 * @param id2
 * @returns
 */
function checkEndAndStartTimeIfOneDay(obj,id1,id2){
	var time1 = $("#"+id1).val().split(" ")[0];
	var time2 = $("#"+id2).val().split(" ")[0];
	if(time1!=time2){
		$(obj).val("");
		showMeg('请确认作业结束时间和作业开始时间为同一天！');
	}
}

/**
 * 共用的流程调用方法
 * @param formobj form表单对象
 */
function ValidformForBMP(formobj){
    //表单提交
    $("#"+formobj).Validform({
        tiptype: function (msg, o, cssctl) {
            if (o.type == 3) {
                validationMessage(o.obj, msg);
            } else {
                removeMessage(o.obj);
            }
        },
        ajaxPost: true,
        beforeSubmit: function (curform) {
        },
        beforeCheck:function(curform){
        },
        callback: function (data) {
            var win = frameElement.api.opener;
            if (data.success == true) {
                frameElement.api.close();
                win.reloadTable();
                win.tip(data.msg);
            } else {
                if (data.responseText == '' || data.responseText == undefined) {
                    $.messager.alert('错误', data.msg);
                    $.Hidemsg();
                } else {
                    try {
                        var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
                        $.messager.alert('错误', emsg);
                        $.Hidemsg();
                    } catch (ex) {
                        $.messager.alert('错误', data.responseText + "");
                        $.Hidemsg();
                    }
                }
                return false;
            }
        }
    });
}

/*******************以下是部门组织机构选择代码start*******************/
var globalDepartName,globalDepartId;
function openDepartmentSelect(obj) {
    globalDepartId=$(obj).prev();
    globalDepartName=$(obj).prev().prev();
    $.dialog.setting.zIndex = getzIndex();
    var orgIds = $(globalDepartId).val();
    $.dialog({
        content: 'url:departController.do?departSelect&orgIds=' + orgIds,
        zIndex: getzIndex(),
        title: '组织机构列表',
        lock: true,
        width: '400px',
        height: '350px',
        opacity: 0.4,
        button: [{
            name: '确定',
            callback: callbackDepartmentSelect,
            focus: true
        },
            {
                name: '取消',
                callback: function() {}
            }]
    }).zindex();
}
function callbackDepartmentSelect() {
    var iframe = this.iframe.contentWindow;
    var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
    var nodes = treeObj.getCheckedNodes(true);
    if(nodes.length>1){
        alert("仅可选择一个单位");
        return false;
    }
    if(nodes.length>0){
        var ids='',names='',orgcode='';
        for(i=0;i<nodes.length;i++){
            var node = nodes[i];
            ids += node.id;
            names += node.name;
            orgcode=node.code;

        }
        $(globalDepartName).val(names);
        $(globalDepartName).blur();
        $(globalDepartId).val(ids);
        //$('#orgIds').val(ids);
        //$('#dutyOrgCode').val(orgcode);
    }
}
function callbackClean(obj){
    $(obj).prev().prev().val('');
    $(obj).prev().prev().prev().val('');
}
/**
 * 重写的系统创建方法
 *
 * @param title
 * @param addurl
 * @param saveurl
 */
function mycreatedetailwindow(title, addurl,width,height) {
    width = width?width:700;
    height = height?height:400;
    if(width=="100%" || height=="100%"){
        width = window.top.document.body.offsetWidth;
        height =window.top.document.body.offsetHeight-100;
    }
    if(typeof(windowapi) == 'undefined'){
        $.dialog({
            content: 'url:'+addurl,
            zIndex: getzIndex(),
            lock : true,
            width:width,
            height: height,
            title:title,
            opacity : 0.3,
            cache:false,
            //cancelVal: '关闭',
            //cancel: true /*为true等价于function(){}*/
        });
    }else{
        W.$.dialog({
            content: 'url:'+addurl,
            zIndex: getzIndex(),
            lock : true,
            width:width,
            height: height,
            parent:windowapi,
            title:title,
            opacity : 0.3,
            cache:false,
            cancelVal: '关闭',
            cancel: function(){
                windowapi.zindex();
            }
            //cancel:true /*为true等价于function(){}*/
        });
    }
}
/*******************以下是部门组织机构选择代码end*******************/
/**************************************时间格式化处理************************************/
function dateFtt(fmt,date)
{ //author: dzh

    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
//创建时间格式化显示
function crtTimeFtt(value){
    var crtTime = new Date(value);
    return dateFtt("yyyy-MM-dd hh:mm",crtTime);//直接调用公共JS里面的时间类处理的办法
}

function crtTimeFtt2(value){
    var crtTime = new Date(value);
    return dateFtt("yyyy-MM-dd",crtTime)+" 23:59:59";//直接调用公共JS里面的时间类处理的办法
}


function changeImage(obj) {
    var src=$(obj).attr("src");
    var fileType=src.substring(src.lastIndexOf(".")+1,src.length);
    if(fileType=="xlsx"||fileType=="xls"){
        fileType="excel";
    }else if(fileType=="pdf"){
        fileType="pdf";
    }
    else if(fileType=="ppt"){
        fileType="ppt";
    }
    else if(fileType=="doc"||fileType=="docx"){
        fileType="word";
    }
    else {
        fileType="word";
    }
    $(obj).parent().css("background-image","url('images/"+fileType+".png')");
}