<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>联合检查表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsWorkUnioncheckController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsWorkUnioncheck.id}"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建人名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="createName" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.createName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建人登录名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="createBy" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.createBy}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建日期：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="createDate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkUnioncheck.createDate}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					更新人名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="updateName" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.updateName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					更新人登录名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="updateBy" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.updateBy}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					更新日期：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="updateDate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkUnioncheck.updateDate}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					所属部门：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="sysOrgCode" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.sysOrgCode}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					所属公司：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="sysCompanyCode" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.sysCompanyCode}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					关联的业务主表id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="bid" type="text" class="form-control" maxlength="36" value = "${famsWorkUnioncheck.bid}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					左上编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="number" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.number}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="numberContent" type="text" class="form-control" maxlength="350" value = "${famsWorkUnioncheck.numberContent}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkUnioncheck.checkDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkTime" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkUnioncheck.checkTime}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					建设单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="buildDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkUnioncheck.buildDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkUnioncheck.workDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业项目：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workName" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.workName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业地点：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workPlace" type="text" class="form-control" maxlength="100" value = "${famsWorkUnioncheck.workPlace}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业队进场时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workStartTime" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkUnioncheck.workStartTime}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业队撤场时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workEndTime" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkUnioncheck.workEndTime}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查结果：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkResult" type="text" class="form-control" maxlength="32" value = "${famsWorkUnioncheck.checkResult}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					整改结果：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="repairResult" type="text" class="form-control" maxlength="32" value = "${famsWorkUnioncheck.repairResult}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查小结：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkResultNote" type="text" class="form-control" maxlength="100" value = "${famsWorkUnioncheck.checkResultNote}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					整改意见：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkRepairNote" type="text" class="form-control" maxlength="100" value = "${famsWorkUnioncheck.checkRepairNote}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业单位签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="sgSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.sgSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					场道管理部签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="cdSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.cdSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					机坪监管部签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="jpSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkUnioncheck.jpSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					流程实例id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="procinstId" type="text" class="form-control" maxlength="36" value = "${famsWorkUnioncheck.procinstId}"  ignore="ignore"  />
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
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: _this,
		  format: 'yyyy-MM-dd HH:mm',
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