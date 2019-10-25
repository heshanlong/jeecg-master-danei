<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>航天器漏油事件报告</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
	<style>
		.panel-body{
			border-style: none;
		}
		.tablebox{
			width: 100%;
			border-left: 1px solid black;
			display: flex
		}
		.boxleft{
			width: 50px;
			height: 100%;
			font-size: 30px;
			text-align: center;
		}
		.boxright{
			flex: 1;
			box-sizing: border-box
		}
		.ji{
			display: table
		}
		.zi{
			display: table-cell;
			vertical-align: middle;
			border-bottom: 1px solid black;
			border-top: 1px solid black;
		}
		table{
			width: 100%;
			height: 100%;
			border-top: none;
			border-right: none;
			border-bottom: none
		}
		h1 {
			width: 100%;
			margin: auto;
			text-align: center
		}

		h4 {
			width: 100%;
			margin: auto;
		}

		h5 {
			width: 100%;
			margin: auto;
			text-align: right
		}

		.fontSize {
			font-size: 15px;
		}


		.bottom {
			width: 100%;
			margin: auto;
			display: flex;
			border: 1px solid black;
		}


		.other {
			border-top: none !important;
			border-bottom: none !important;
		}
		.foot{
			width: 100%;
			margin: auto;
			display: flex;
			border: 1px solid black;
			border-top: none
		}
		.footb{
			width: 100%;
			/*height: 50px;*/
			margin: auto;
			display: flex;
			border: 1px solid black;
			border-bottom: none
		}
		.foota{
			flex: 1;
			/*display: flex;*/
			/*line-height: 50px;*/
			/*padding-top: 10px;*/
			/*padding-bottom: 10px;*/
		}
		.boxright th{
			text-align: center;
			padding: 5px 15px;
			border: 1px solid black;

		}
		.form-group{
			margin-top: 15px;
		}
		.easyui-linkbutton{
			line-height: 0 !important;
		}
		.ignored{
			display: none;
		}
		.layui-laydate-content>.layui-laydate-list {
			padding-bottom: 0px;
			overflow: hidden;
		}
		.layui-laydate-content>.layui-laydate-list>li{
			width:50%
		}
		.merge-box .scrollbox .merge-list {
			padding-bottom: 5px;
		}
	</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	 <div class="panel-heading"></div>
	 <div class="panel-body">
		 <form class="form-horizontal" role="form" id="formobj" action="famsAircontrolSafecheckController.do?exampleReport" method="POST">
			 <input type="hidden" id="btn_sub" class="btn_sub"/>
			 <input type="hidden" id="id" name="id" value="${famsUnsafeeventAircraftleakage.id}"/>
			 <div style="width: 80%;margin: auto">
				 <%--<h4>编号：BYG-QW-B-02-507</h4>--%>
				 <h1>航天器漏油事件报告</h1>
				 <h5>&nbsp;</h5>
				 <div class="footb">
				 
				 	<div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label  class="col-sm-3 control-label">接报时间：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <input name="settime" type="text" class="form-control laydate-date"  type='date' value='${famsUnsafeeventAircraftleakage.settime}' />
								 </div>
							 </div>
						 </div>
					 </div>
					 
					 
					 <div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label class="col-sm-3 control-label">信息来源：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									<input name="sourceformation" type="text" class="form-control laydate-time" style="width:130px;"  value='${famsUnsafeeventAircraftleakage.sourceformation}' />
								 </div>
							 </div>
						 </div>
					 </div>
				 
					 <div class="foota">
						 <div class="form-group">
							 <label class="col-sm-3 control-label">航班号：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <div class="input-group-btn">
									 	<input name="flightno" type="text" class="form-control laydate-time" style="width:130px;"  value='${famsUnsafeeventAircraftleakage.flightno}' />
									 </div>
								 </div>
							 </div>
						 </div>
					 </div>
					 

				 </div>
				 <!-- 第二行 -->
				 <div class="footb">
				 
				 	<div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label class="col-sm-3 control-label">航线：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <input name="routes" type="text" class="form-control input-sm" value='${famsUnsafeeventAircraftleakage.routes}' maxlength="32"  />
								 </div>
							 </div>
						 </div>
					 </div>
					 
				 	<div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label class="col-sm-3 control-label">机型：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <input name="models" type="text" class="form-control input-sm"  value='${famsUnsafeeventAircraftleakage.models}' maxlength="32" />
								 </div>
							 </div>
						 </div>
					 </div>
					 
					 <div class="foota" style="border-right:1px solid black">

						 <div class="form-group">
							 <label class="col-sm-3 control-label">机号：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <input name="immediately" type="text" class="form-control input-sm" value='${famsUnsafeeventAircraftleakage.immediately}' maxlength="32"  />
								 </div>
							 </div>
						 </div>
					 </div>
				</div>
				
				<div class="footb">
					<div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label class="col-sm-3 control-label">漏油面积：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <input  name="spillarea" type="text" class="form-control laydate-datetime" value='${famsUnsafeeventAircraftleakage.spillarea}'/>
								 </div>
							 </div>
						 </div>
					 </div>
						
					 <div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label class="col-sm-3 control-label">漏油种类：</label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
									 <input name="oiltypes" type="text" class="form-control laydate-datetime" value='${famsUnsafeeventAircraftleakage.oiltypes}'/>
								 </div>
							 </div>
						 </div>
					  </div>
					
					  <div class="foota" style="border-right:1px solid black">
						 <div class="form-group">
							 <label class="col-sm-3 control-label"> </label>
							 <div class="col-sm-7">
								 <div class="input-group" style="width:100%">
								 
								 </div>
							 </div>
						 </div>
					   </div>
				</div>


				 <div class="bottom">
					 <label for="remark" class="col-sm-2 control-label">处理经过：</label>
					 <div class="col-sm-10 control-label">
						 <textarea name="remark" class="form-control input-sm" rows="6" maxlength="1000"></textarea>
					 </div>
				 </div>


			 </div>

		 </form>
	 </div>
 </div>

 <script src="plug-in/hplus/plugins/suggest/bootstrap-suggest.min.js"></script>

 <script type="text/javascript">
 
 
	$('.radioInput').on('ifChecked', function(event) {
		var value = event.target.defaultValue;
		var name = $(event.target).attr("name");
		if (name + ":2" == value) {
			$("#" + name).parent().attr("class", "rd1");
		} else {
			$("#" + name).parent().attr("class", "rd1 ignored");
		}
	});
 
 
var subDlgIndex = '';
$(document).ready(function() {
	
	loadResult();
	function loadResult(){
		var rs = $('input[type="radio"]:checked');
		for(var i=0;i<rs.length;i++){
			console.log(rs[i]);
			var value = rs[i].defaultValue;
			var name = $(rs[i]).attr("name");
			if (name + ":2" == value) {
				$("#" + name).parent().attr("class", "rd1");
			} else {
				$("#" + name).parent().attr("class", "rd1 ignored");
			}
		}
	}
	
    $("#airNumber").val($("#flightn").val());

    $(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd HH:mm',
		  type: 'datetime',
			max: crtTimeFtt2(new Date((new Date()).getTime() + 24*60*60*1000)),
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
		});
	});
	$(".laydate-time").each(function(){
		var _this = this;
		laydate.render({
			format: 'HH:mm',
			type: 'time',
			elem: this,
		  	ready: formatminutes
		});
		function  formatminutes(date) {
            $($(".laydate-time-list li ol")[2]).find("li").remove();
		}
	});
	//单选框/多选框初始化
	$('.i-checks').iCheck({
		labelHover : false,
		cursor : true,
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%'
	});
	
	//表单提交
	$("#formobj").Validform({
		tiptype:function(msg,o,cssctl){
			if(o.type==3){
				validationMessage(o.obj,msg);
			}else{
				removeMessage(o.obj);
			}
		},
		btnSubmit : "#btn_sub",
		btnReset : "#btn_reset",
		ajaxPost : true,
		beforeSubmit : function(curform) {
            setradioResult();
		},
		usePlugin : {
			passwordstrength : {
				minLen : 6,
				maxLen : 18,
				trigger : function(obj, error) {
					if (error) {
						obj.parent().next().find(".Validform_checktip").show();
						obj.find(".passwordStrength").hide();
					} else {
						$(".passwordStrength").show();
						obj.parent().next().find(".Validform_checktip").hide();
					}
				}
			}
		},
		callback : function(data) {
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
});

function setradioResult() {
	var str = '';
    // console.log(aa)

    var aa = $(".rd1 input[type='radio']:checked");
    for(var j = 0;j<aa.length;j++){
        var result = '';
        //获取name,判断是否为xxx:2
        var ss = aa[j].value.split(":");
        if(ss[1]=="2"){
        	//获取对应id的value值,作为不符合的结果
        	dealResult = $("#"+ss[0]+"_action").val();
        	console.log("result:"+ result);
        }else{
        	dealResult = "";
        }
        //获取到达时间和完成时间
        var arrayTime = $("#"+ss[0]+"_arrayTime").val();
        var completeTime = $("#"+ss[0]+"_completeTime").val();
        console.log("arrayTime:"+ arrayTime+"------completeTime: "+completeTime);
        str += ss[0]+"/"+ss[1]+"/"+dealResult+"/"+arrayTime+"/"+completeTime+";";
        console.log("单次的log:"+ss[0]+"/"+ss[1]+"/"+dealResult+"/"+arrayTime+"/"+completeTime+";");
    }
	
    console.log(str)
    $("#radioResult").val(str);
}

var resultTemp;
var oldTemp=$("#airPlanId").val();
var testBsSuggest = $("#airNumber").bsSuggest({
    url: "famsCommonController/loadFlightData.do?keyword="+$("#airNumber").val(),
    // url: "famsCommonController.do?loadFlightData?"+$("#airNumber").val(),

    idField: "rownum",
    showBtn: false,
    showHeader: false,
    getDataMethod: 'data',
    allowNoKeyword: false,
    effectiveFields: ['flightno','crafttype','airlinefullcn'],            //有效显示于列表中的字段，非有效字段都会过滤，默认全部。
    effectiveFieldsAlias: {'flightno':'航班号','crafttype':'机型','airlinefullcn':'航线'},       //有效字段的别名对象，用于 header 的显示
    delay: 0.1,
    keyField:"flightno",  //每组数据的哪个字段作为输入框内容，优先级高于 indexKey 设置（推荐）
    indexId: 0,                     //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
    indexKey: 1                    //每组数据的第几个数据，作为input输入框的内容

}).on('onDataRequestSuccess', function (e, result) {
    resultTemp=result;
    //console.log('onDataRequestSuccess: ', result);
}).on('onSetSelectValue', function (e, keyword) {
    //console.log('onSetSelectValue: ', keyword);

    //console.log('onSetSelectValue: ', resultTemp.value[keyword.id-1].depart);
    $("#airTail").val(resultTemp.value[keyword.id-1].craftno);
    $("#airModel").val(resultTemp.value[keyword.id-1].crafttype);
    $("#airSlots").val(resultTemp.value[keyword.id-1].craftsite);

    $("#airPlanId").val(resultTemp.value[keyword.id-1].id);
    $("#airLine").val(resultTemp.value[keyword.id-1].airlinefullcn);
    if(resultTemp.value[keyword.id-1].AFSS_FLIO=='D'){//是否出港
        $("#actualDate").val(resultTemp.value[keyword.id-1].sat);//实际出港
        //计划出港时间
        if(resultTemp.value[keyword.id-1].est==''){
            $("#preDate").val(resultTemp.value[keyword.id-1].sst);

        }else{
            $("#preDate").val(crtTimeFtt(resultTemp.value[keyword.id-1].sst));

        }

    }else{
        $("#actualDate").val(resultTemp.value[keyword.id-1].eat);//进港，实际进港时间

        //计划进港时间
        if(resultTemp.value[keyword.id-1].est==''){
            $("#preDate").val(resultTemp.value[keyword.id-1].est);

        }else{
            $("#preDate").val(crtTimeFtt(resultTemp.value[keyword.id-1].est));

        }

    }


}).on('onUnsetSelectValue', function (e) {

    $("#airTail").val(null);
    $("#airModel").val(null);
    $("#airSlots").val(null);

    $("#airPlanId").val(null);
    $("#airLine").val(null);
    $("#preDate").val(null);

    $("#actualDate").val(null);
    //console.log("onUnsetSelectValue");
});


$('input[type="radio"]').css('color','blue')
$('input[type="radio"]').css('border','10px solid #f00')
$('input[type="radio"]').css('opacity',1)//{color:#fff;opacity:1}
</script>
</body>
</html>