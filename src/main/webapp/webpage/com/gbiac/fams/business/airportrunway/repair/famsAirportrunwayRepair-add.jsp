<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_airportrunway_repair</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<style>
	.panel-body{
		border-style: none;
	}
	input[type=radio]{
		height: 12px;
		/*margin: auto;*/
	}
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAirportrunwayRepairController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="row">
		
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="people" type="text" class="form-control input-sm"  placeholder="请填写作业人,多位作业人需用逗号分隔" maxlength="100" value = "${realName}"  ignore="checked" datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业开始时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="startTime" name="startTime" type="text"   value="${sdf }" readonly="readonly" class="Wdate form-control laydate-datetime"  ignore="checked"  datatype="*"  />
					</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业结束时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="endTime" name="endTime" type="text"    value="${edf }" readonly="readonly" class="Wdate form-control laydate-datetime"  ignore="checked"  datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>破损位置：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="damageLocation" type="text" class="form-control input-sm" maxlength="100" placeholder="请填写破损位置信息"  ignore="checked"  datatype="*" />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>破损类型：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content pslx" style="font-size: 14px">
					<%--extendJson="{onclick:'dType()'}"--%>
						<%--class:'isUseFire i-checks'}--%>
						<t:dictSelect  field="damageType" typeGroupCode="damageType" hasLabel="false" defaultVal="板块裂缝" type="radio" extendJson="{class:'isUseFire i-checks ischeckAta'}"></t:dictSelect>
						<%--readonly="readonly"--%>
						<input  id="damageTypeOther" name="damageTypeOther" readonly="readonly"  type="text"  maxlength="2000"  style="width:30x " ignore="checked"   placeholder="点击其他破损类型可修改" />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>维修方案：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content wxfa" style="font-size: 14px">
					<%--extendJson="{onclick:'maintenancePlanType()'}"--%>
					<t:dictSelect field="maintenancePlan" typeGroupCode="mainPlan" hasLabel="false" defaultVal="以防渗漏为目的对板块裂缝实施灌缝" type="radio"  extendJson="{class:'isUseFire i-checks ischeckAtz'}"></t:dictSelect>
						<%--readonly="readonly"--%>
						<input  id="maintenancePlanOther" name="maintenancePlanOther"   readonly="readonly"  type="text"  maxlength="2000"  style="width:30x " ignore="checked"  placeholder="点击其他维修方案可修改" />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>工作量：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<%--datatype="n1-100"--%>
					修补面积<input id="repairArea"  name="repairArea" style="margin-left: 5px" type="text" placeholder="必填"  ignore="checked"  maxlength="100"  datatype="*"   onblur='checkFloatTwo("repairArea")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}" />平方米
					&nbsp;&nbsp;&nbsp;&nbsp;
					<%--datatype="n1-100"--%>
					灌缝长度<input id="irrigationSeamLength"  name="irrigationSeamLength" style="margin-left: 5px" type="text" placeholder="必填"  ignore="checked"  maxlength="100"  datatype="*"   onblur='checkFloatTwo("irrigationSeamLength")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}" />米
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="form-group">
			    <label for="isEnable" class="col-md-3 col-sm-3 col-xs-3 bt-label  control-label">图片：</label>
			    <div class="col-sm-7">
			        <jsp:include page= "/webpage/common/jsp/upload.jsp">
			        	<jsp:param name= "name" value= "files"/>
						<jsp:param name= "ids" value= ""/>
						<jsp:param name= "value" value= ""/>
						<jsp:param name= "fileNames" value= ""/>
						<jsp:param name= "sizes" value= ""/>
						<jsp:param name= "directory" value= "airportrunwayClean"/>
						<jsp:param name= "multiple" value= "multiple"/>
						<jsp:param name= "required" value= ""/>
						<jsp:param name= "disable" value= ""/>
						<jsp:param name= "typeSwf" value= "0"/>
						<jsp:param name= "acceptFileType" value= "image/*"/>
			        </jsp:include>
			    </div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					备注：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<textarea name="note" cols="55" rows="5" maxlength="2000" placeholder="请填写小于1000字的信息" style="width:100%"></textarea>
					<!-- <input name="note" type="text" class="form-control input-sm" maxlength="255"  ignore="ignore" style="width:111px;height:111px" /> -->
				</div>
			</div>
		</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
function checkFloatTwo(obj){
	var a = $('#'+obj).val();
	var aa = Number(a).toFixed(2)<=0?'':Number(a).toFixed(2);
	if(aa.indexOf(".00")!=-1){
	  $('#'+obj).val( Number(aa.replace('.00','')));
	}else{
		if(aa.indexOf(".")!=-1 && aa.substr(aa.length-1,1)==0 ){
		$('#'+obj).val(aa.substr(0,aa.length-1));
		}else{
		$('#'+obj).val(aa);
		}	
	}
}
var subDlgIndex = '';
/**
 * 检查时间大小顺序，id1<id2
 * @param obj 操作对象
 * @param id1 小的id
 * @param id2 大的id
 */
function checkTimeSequence(obj,id1,id2){
	var time1=$("#"+id1).val();
	var time2=$("#"+id2).val();
	if(time1!=""&&time2!=""&&new Date(time1)>=new Date(time2)){
        showMeg("开始时间必须小于结束时间！");
        $(obj).val('');
	}
}

function dType(){
	var damageType =$("input[name='damageType']:checked").val();
	if(damageType == '其他'){
		$("#damageTypeOther").removeAttr("readonly");
		$("#damageTypeOther").attr("datatype","*");
		$("#damageTypeOther").attr("ignore","checked");
	}else{
		$("#damageTypeOther").attr("readonly","readonly");
		$("#damageTypeOther").val('');
		$("#damageTypeOther").removeAttr("datatype");
		$("#damageTypeOther").attr("ignore","ignore");
	}
}

function maintenancePlanType(){
	var maintenancePlan =$("input[name='maintenancePlan']:checked").val();
	if(maintenancePlan == '其它维修方案'){
		$("#maintenancePlanOther").removeAttr("readonly");
		$("#maintenancePlanOther").attr("datatype","*");
		$("#maintenancePlanOther").attr("ignore","checked");
	}else{
		$("#maintenancePlanOther").attr("readonly","readonly");
		$("#maintenancePlanOther").val('');
		$("#maintenancePlanOther").removeAttr("datatype");
		$("#maintenancePlanOther").attr("ignore","ignore");
	}
}

$(document).ready(function() {
	 $('.ischeckAta').on('ifChecked', function(event){
		 dType();
     });
	 $('.ischeckAtz').on('ifChecked', function(event){
		 maintenancePlanType();
     });
	$(".laydate-datetime").each(function(){
		var _this = this;
		var myDate = new Date();
		laydate.render({
		  elem: this,
		 // min: myDate.toLocaleTimeString()-1,
		  format: 'yyyy-MM-dd HH:mm',
		  type: 'datetime',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  },
		  done: function(value, date, endDate){
              if(_this.id=="startTime" || _this.id=="endTime"){
            	  checkTimeSequence(_this,'startTime','endTime');
              }
             
          }
		});
	});
	$(".laydate-date").each(function(){
		var _this = this;
		laydate.render({
		  elem: this
		});
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
</script>
</body>
</html>