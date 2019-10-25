<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>例行检查表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsWorkCheckController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsWorkCheck.id}"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					关联的业务主表id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="bid" type="text" class="form-control" maxlength="36" value = "${famsWorkCheck.bid}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					左上编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="number" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.number}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="numberContent" type="text" class="form-control" maxlength="350" value = "${famsWorkCheck.numberContent}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkCheck.checkDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkTime" type="text" class="form-control" maxlength="32" value = "${famsWorkCheck.checkTime}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					建设单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="buildDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkCheck.buildDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					施工项目：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workName" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.workName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					施工地点：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workPlace" type="text" class="form-control" maxlength="100" value = "${famsWorkCheck.workPlace}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					施工单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkCheck.workDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					施工队进场时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workStartTime" type="text" class="form-control" maxlength="32" value = "${famsWorkCheck.workStartTime}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					施工预计结束时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workEndTime" type="text" class="form-control" maxlength="32" value = "${famsWorkCheck.workEndTime}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					车牌号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="carNumber" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.carNumber}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					驾驶员：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="carDriver" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.carDriver}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					驾驶证号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="driverLicense" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.driverLicense}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="carDepart" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.carDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查结果：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkResult" type="text" class="form-control" maxlength="32" value = "${famsWorkCheck.checkResult}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					整改结果：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="repairResult" type="text" class="form-control" maxlength="32" value = "${famsWorkCheck.repairResult}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查小结：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkResultNote" type="text" class="form-control" maxlength="100" value = "${famsWorkCheck.checkResultNote}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					整改意见：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkRepairNote" type="text" class="form-control" maxlength="100" value = "${famsWorkCheck.checkRepairNote}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					安全员签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="aqSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.aqSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					飞行区管理部机坪监管部签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="fxSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkCheck.fxSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					流程实例id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="procinstId" type="text" class="form-control" maxlength="36" value = "${famsWorkCheck.procinstId}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
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