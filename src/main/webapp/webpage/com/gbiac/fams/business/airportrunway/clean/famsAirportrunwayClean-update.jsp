<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_airportrunway_clean</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<script type="text/javascript" src="webpage/common/js/common.js"></script>
<style>
	.panel-body{
		border-style: none;
	}
	input[type=radio]{
		height: 12px;

	}
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAirportrunwayCleanController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsAirportrunwayClean.id}"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="no" name="no"  value = "${famsAirportrunwayClean.no}" type="text" class="form-control input-sm" readonly="readonly"/>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="createByCn" name="createByCn"  value = "${famsAirportrunwayClean.createByCn}" type="text" class="form-control input-sm" readonly="readonly" ignore="checked" datatype="*1-100"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="createByCn" name="createDate"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='date'  value='${famsAirportrunwayClean.createDate}'/>" type="text" class="form-control input-sm" readonly="readonly"/>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="people" type="text" class="form-control" placeholder="请填写作业人,多位作业人需用逗号分隔" maxlength="100" value = "${famsAirportrunwayClean.people}"   ignore="checked" datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业开始时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="startTime" name="startTime" type="text"  readonly="readonly" class="form-control Wdate laydate-datetime"   value="${famsAirportrunwayClean.startTime}"  ignore="checked"  datatype="*"  />
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业结束时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="endTime"  name="endTime" type="text"   readonly="readonly" class="form-control Wdate laydate-datetime"  value="${famsAirportrunwayClean.endTime}"  ignore="checked"  datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>除胶跑道：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content" style="font-size: 15px;margin-top: 5px">
				<%-- <t:dictSelect field="cleanRunway" typeGroupCode="cleanway" hasLabel="false" defaultVal="${famsAirportrunwayClean.cleanRunway}" type="radio" extendJson="{class:'isUseFire i-checks'}"></t:dictSelect> --%>
					<%-- <input id="cleanRunway" type="hidden" value = "${famsAirportrunwayClean.cleanRunway}" /> --%>
					<input name="cleanRunway" type="radio"  value="01/19"  ignore="ignore"  readonly="readonly"   class="form-control i-checks" value="01/19">01/19</input>
								<input name="cleanRunway" type="radio"  value="02R/20L"    ignore="ignore"  readonly="readonly"  class="form-control i-checks" value="02R/20L">02R/20L</input>
								<input name="cleanRunway" type="radio"  value="02L/20R"    ignore="ignore"  readonly="readonly"  class="form-control i-checks" value="02L/20R">02L/20R</input>
								<input id="runway"   type="hidden" class="form-control input-sm  i-checks" maxlength="255" value="${famsAirportrunwayClean.cleanRunway}" ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>除胶位置：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content" >
				
					<t:dictSelect field="direction" typeGroupCode="direction" hasLabel="false" defaultVal="${famsAirportrunwayClean.direction}" type="radio" extendJson="{class:'isUseFire i-checks'}"></t:dictSelect>
					&nbsp;&nbsp;&nbsp;&nbsp;
					第
					<input   id="cleanLocation" name="cleanLocation" type="text" value = "${famsAirportrunwayClean.cleanLocation}" maxlength="100"  datatype="*" style="width:50x " ignore="checked"  onblur='checkFloatTwo("cleanLocation")'  oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"  placeholder="只能输入数字" />
					块
					<%-- <input id="direction" type="hidden" value = "${famsAirportrunwayClean.direction}" /> --%>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>除胶面积：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input  id="cleanArea"  name="cleanArea" type="text" ignore="checked"  datatype="*" value = "${famsAirportrunwayClean.cleanArea}" maxlength="100"  onBlur='checkFloatTwo("cleanArea")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"  placeholder="请输入除胶面积" />平方米
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
			    <label for="isEnable" class="col-md-3 col-sm-3 col-xs-3 bt-label  control-label">图片：</label>
			    <div class="col-sm-7">
			        <jsp:include page= "/webpage/common/jsp/upload.jsp">
			        	<jsp:param name= "name" value= "files"/>
						<jsp:param name= "ids" value= "${files.ids}"/>
						<jsp:param name= "value" value= "${files.paths}"/>
						<jsp:param name= "fileNames" value= "${files.fileNames}"/>
						<jsp:param name= "sizes" value= "${files.sizes}"/>
						<jsp:param name= "types" value="${files.types}" />
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
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row" style="margin-left: -25px;">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					备注：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<textarea id="note"  name="note" cols="55" rows="5" maxlength="2000" placeholder="请填写小于1000字的信息" value = "${famsAirportrunwayClean.note}"  ignore="ignore" style="width:100%"></textarea>
					<input id="added" type="hidden"  readonly="readonly"  value = "${famsAirportrunwayClean.note}"  ignore="ignore"  />
					<!-- <input name="note" type="text" class="form-control input-sm" maxlength="2000"  ignore="ignore" style="width:111px;height:111px" /> -->
				</div>
			</div>
		</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
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
$(document).ready(function() {
	$("#note").val($("#added").val());
	var runway = $("#runway").val();
	//alert(runway);
	$("[name='cleanRunway'][value='" + runway + "']").prop("checked", "checked");
	//var direction = $("#direction").val();
	//$("[name='direction'][value='" + direction + "']").prop("checked", "checked");
	
	//var cleanRunway = $("#cleanRunway").val();
	//$("[name='cleanRunway'][value='" + cleanRunway + "']").prop("checked", "checked");
	
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
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
		  elem: _this,
		  format: 'yyyy-MM-dd',
		  ready: function(date){
		  	 $(_this).val(DateJsonFormat(date,this.format));
		  }
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