<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>整改单</title>
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
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolReformController.do?doReceive" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsAircontrolReform.id}"/>

		<div class="form-group">
			<label for="decideDate" class="col-sm-3 control-label">发现时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input disabled="disabled" id="decideDate" name="decideDate" type="text" class="form-control input-sm laydate-datetime" placeholder="请输入派发时间"  ignore="ignore"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${famsAircontrolReform.decideDate}'/>" />
					<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	<div class="form-group">
		<label for="dutyCompany" class="col-sm-3 control-label">责任单位：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
               <%--<t:dictSelect field="dutyCompany" type="list" extendJson="{class:'form-control input-sm'}"  datatype="*"  typeGroupCode="reform_d_c"  hasLabel="false"  title="责任单位" defaultVal="${famsAircontrolReform.dutyCompany}" readonly="readonly"></t:dictSelect>--%>
				<input disabled="disabled" id="dutyCompany" name="dutyCompany" value='${famsAircontrolReform.dutyCompany}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入责任单位"  ignore="ignore" />

			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="dutyPersonName" class="col-sm-3 control-label">当事人姓名：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input disabled="disabled" id="dutyPersonName" name="dutyPersonName" value='${famsAircontrolReform.dutyPersonName}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入当事人姓名"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="dutyPersonMobile" class="col-sm-3 control-label">当事人联系方式：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<input disabled="disabled" id="dutyPersonMobile" name="dutyPersonMobile" value='${famsAircontrolReform.dutyPersonMobile}' type="text" maxlength="32" class="form-control input-sm" placeholder="请输入当事人联系方式"  ignore="ignore" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="violationName" class="col-sm-3 control-label">违章行为：</label>
		<div class="col-sm-7">
			<div class="input-group" style="width:100%">
				<textarea  disabled="disabled"name="violationName" class="form-control input-sm" rows="6"  datatype="*" ignore="checked" >${famsAircontrolReform.violationName}</textarea>
				<span class="Validform_checktip" style="float:left;height:0px;"></span>
				<label class="Validform_label" style="display: none">违章行为</label>
			</div>
		</div>
	</div>

	<%--<div class="form-group">--%>
		<%--<label for="panishResult" class="col-sm-3 control-label">处理结果：</label>--%>
		<%--<div class="col-sm-7">--%>
			<%--<div class="input-group" style="width:100%">--%>
				<%--<t:dictSelect readonly="readonly" field="panishResult" type="checkbox" extendJson="{class:'i-checks'}"  typeGroupCode="reform_p_r"  hasLabel="false"  title="处理结果" defaultVal="${famsAircontrolReform.panishResult}"></t:dictSelect>--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>






	<div  readonly="readonly"  class="form-group">
		<label for="isEnable" class="col-sm-3 control-label">图片：</label>
		<div   class="col-sm-7">
			<jsp:include  page= "/webpage/common/jsp/upload.jsp">
                <jsp:param name= "name" value= "picture"/>
                <jsp:param name= "ids" value= "${picIds}"/>
                <jsp:param name= "value" value= "${picValue}"/>
                <jsp:param name= "fileNames" value= "${fileNames}"/>
                <jsp:param name= "sizes" value= "${sizes}"/>
                <jsp:param name= "directory" value= "reform"/>
                <jsp:param name= "multiple" value= "multiple"/>
                <jsp:param name= "required" value= ""/>
                <jsp:param name= "disable" value= "disable"/>

            </jsp:include>
		</div>
	</div>



    <div class="form-group">
        <label for="remark" class="col-sm-3 control-label">备注：</label>
        <div class="col-sm-7">
            <div class="input-group" style="width:100%">
                <textarea readonly="readonly" name="remark" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolReform.remark}</textarea>
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