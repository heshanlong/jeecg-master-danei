<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>违章告知单</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
<style>
	.panel-body{
		border-style: none;
	}
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolViolationController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsAircontrolViolation.id}"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="number" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.number}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					单位id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dutyId" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.dutyId}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					单位名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dutyCompany" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.dutyCompany}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					当事人姓名：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dutyPersonName" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.dutyPersonName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					当事人联系方式：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dutyPersonMobile" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.dutyPersonMobile}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					违章地点：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="violationAddr" type="text" class="form-control" maxlength="64" value = "${famsAircontrolViolation.violationAddr}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					控制区通行证号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="apronCard" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.apronCard}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					车牌号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="carNumber" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.carNumber}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					机坪处理：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
	            	<div style="padding-top:5px">
	            	<t:dictSelect field="apronPanishResult" defaultVal = "${famsAircontrolViolation.apronPanishResult}" extendJson="{class:'i-checks'}" type="checkbox" hasLabel="false"  title="机坪处理"  typeGroupCode="voi_d_str" ></t:dictSelect>
	            	</div>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					派发日期：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="decideDate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsAircontrolViolation.decideDate}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					派发人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="decideName" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.decideName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					处理人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dealName" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.dealName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					状态：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="status" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.status}"  ignore="ignore"  />
				</div>
			</div>
		</div>
					<div class="bt-item col-md-6 col-sm-6">
			        <div class="row">
						<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
							违章行为：
						</div>
						<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
						  	 	<textarea name="violationName" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.violationName}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">违章行为</label>
			          </div>
						</div>
					<div class="bt-item col-md-6 col-sm-6">
			        <div class="row">
						<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
							处罚依据：
						</div>
						<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
						  	 	<textarea name="panishRule" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.panishRule}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">处罚依据</label>
			          </div>
						</div>
					<div class="bt-item col-md-6 col-sm-6">
			        <div class="row">
						<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
							处罚结果：
						</div>
						<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
						  	 	<textarea name="accessPanishResult" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.accessPanishResult}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">准入处理</label>
			          </div>
						</div>
					<div class="bt-item col-md-6 col-sm-6">
			        <div class="row">
						<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
							备注：
						</div>
						<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
						  	 	<textarea name="remark" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.remark}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">备注</label>
			          </div>
						</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  type: 'datetime',
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