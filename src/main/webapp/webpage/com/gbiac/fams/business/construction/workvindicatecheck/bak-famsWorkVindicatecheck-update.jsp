<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>维护作业检查表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsWorkVindicatecheckController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsWorkVindicatecheck.id}"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建人名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="createName" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.createName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建人登录名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="createBy" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.createBy}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建日期：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="createDate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkVindicatecheck.createDate}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					更新人名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="updateName" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.updateName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					更新人登录名称：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="updateBy" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.updateBy}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					更新日期：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="updateDate" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkVindicatecheck.updateDate}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					所属部门：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="sysOrgCode" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.sysOrgCode}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					所属公司：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="sysCompanyCode" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.sysCompanyCode}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					关联的业务主表id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="bid" type="text" class="form-control" maxlength="36" value = "${famsWorkVindicatecheck.bid}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					左上编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="number" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.number}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkVindicatecheck.checkDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkTime" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkVindicatecheck.checkTime}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					建设单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="buildDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkVindicatecheck.buildDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业项目：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workName" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.workName}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业地点：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workPlace" type="text" class="form-control" maxlength="100" value = "${famsWorkVindicatecheck.workPlace}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业单位：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workDepart" type="text" class="form-control" maxlength="36" value = "${famsWorkVindicatecheck.workDepart}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业队进场时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workStartTime" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkVindicatecheck.workStartTime}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					作业预计结束时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="workEndTime" type="text" class="form-control input-sm laydate-date" value="<fmt:formatDate pattern='yyyy-MM-dd' type='date' value='${famsWorkVindicatecheck.workEndTime}'/>"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查结果：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkResult" type="text" class="form-control" maxlength="32" value = "${famsWorkVindicatecheck.checkResult}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					整改结果：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="repairResult" type="text" class="form-control" maxlength="32" value = "${famsWorkVindicatecheck.repairResult}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					检查小结：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkResultNote" type="text" class="form-control" maxlength="100" value = "${famsWorkVindicatecheck.checkResultNote}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					整改意见：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="checkRepairNote" type="text" class="form-control" maxlength="100" value = "${famsWorkVindicatecheck.checkRepairNote}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					建设单位签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="jsSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.jsSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					飞行区管理部签字：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="fxSignature" type="text" class="form-control" maxlength="50" value = "${famsWorkVindicatecheck.fxSignature}"  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					流程实例id：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="procinstId" type="text" class="form-control" maxlength="36" value = "${famsWorkVindicatecheck.procinstId}"  ignore="ignore"  />
				</div>
			</div>
		</div>
					<div class="bt-item col-md-6 col-sm-6">
			        <div class="row">
						<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
							编号：
						</div>
						<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
						  	 	<textarea name="numberContent" class="form-control input-sm" rows="4"  ignore="ignore" >${famsWorkVindicatecheck.numberContent}</textarea>
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">编号</label>
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