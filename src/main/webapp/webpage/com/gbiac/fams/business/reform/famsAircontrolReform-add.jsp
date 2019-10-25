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
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
	<jsp:useBean id="nowdate" class="java.util.Date"/>
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
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolReformController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>

		<%--<div class="form-group">--%>
			<%--<label for="number" class="col-sm-3 control-label">编号：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<input id="number" name="number" type="text" maxlength="32" value="${no}"  readonly="readonly" class="form-control input-sm" placeholder="请输入编号"  ignore="ignore" />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>

		<%--<div class="form-group">--%>
			<%--<div class="row">--%>
				<%--<div class="col-md-3 col-sm-3 col-xs-3 bt-label">--%>
					<%--编号：--%>
				<%--</div>--%>
				<%--<div class="col-md-9 col-sm-9 col-xs-9 bt-content" >--%>
					<%--<input id="no" name="no" type="text" value="${no}" class="form-control input-sm" readonly="readonly"/>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
			<label for="decideDate" class="col-sm-3 control-label"><span style="color:red;">*</span>发现时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input name="decideDate" type="text" class="form-control laydate-datetime" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${nowdate}'/>" ignore="checked" datatype="*"  />
					<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
		<%--<div class="form-group">--%>
			<%--<label for="dutyCompany" class="col-sm-3 control-label">责任单位：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<t:dictSelect field="dutyCompany" type="list" extendJson="{class:'form-control input-sm'}"   typeGroupCode="reform_d_c"  hasLabel="false"  title="责任单位"></t:dictSelect>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
				<label for="dutyCompany" class="col-sm-3 control-label"><span style="color:red;">*</span>责任单位：</label>

				<div class="col-sm-7">
					<input id="dutyCompany" name="dutyCompany" type="text" readonly="readonly" class="form-control input-sm" datatype="*" ignore="checked"  value="${dutyCompany}"/>
					<input id="dutyId" name="dutyId" type="hidden"  value="${dutyId} "/>
					<input id="dutyOrgCode" name="dutyOrgCode" type="hidden"  value="${dutyOrgCode} "/>


					<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="departSearch" onclick="openDepartmentSelect()">选择</a>
					<a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="departRedo" onclick="callbackClean()">清空</a>
				</div>
		</div>
        <div class="form-group">
            <label for="isEnable" class="col-sm-3 control-label">图片：</label>
            <div class="col-sm-7">
                <jsp:include page= "/webpage/common/jsp/upload.jsp">

					<jsp:param name= "name" value= "picture"/>
					<jsp:param name= "ids" value= ""/>
					<jsp:param name= "value" value= ""/>
					<jsp:param name= "fileNames" value= ""/>
					<jsp:param name= "sizes" value= ""/>
					<jsp:param name= "directory" value= "reform"/>
					<jsp:param name= "multiple" value= "multiple"/>
					<jsp:param name= "required" value= ""/>
					<jsp:param name= "acceptFileType" value= "image/*"/>


				</jsp:include>
            </div>
        </div>

		<div class="form-group">
			<label for="dutyPersonName" class="col-sm-3 control-label">当事人姓名：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="dutyPersonName" name="dutyPersonName" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入当事人姓名"  ignore="ignore" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="dutyPersonMobile" class="col-sm-3 control-label">联系方式：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="dutyPersonMobile" name="dutyPersonMobile" type="text" maxlength="11" class="form-control input-sm" placeholder="请输入当事人联系方式"  ignore="ignore"  datatype="n0-11" oninput = "value=value.replace(/[^\d]/g,'')"/>
				</div>
			</div>
		</div>
        <div class="form-group">
            <label for="violationName" class="col-sm-3 control-label"><span style="color:red;">*</span>违章行为：</label>
            <div class="col-sm-7">
                <div class="input-group" style="width:100%">
                    <textarea name="violationName" class="form-control input-sm" rows="6" maxlength="1000" ignore="checked" datatype="*" placeholder="请输入违章行为(小于1000字)" ></textarea>
                    <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    <label class="Validform_label" style="display: none">违章行为</label>
                </div>
            </div>
        </div>
		<%--<div class="form-group">--%>
			<%--<label for="panishResult" class="col-sm-3 control-label">处理结果：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<t:dictSelect field="panishResult" type="checkbox" extendJson="{class:'i-checks'}"  typeGroupCode="reform_p_r"  hasLabel="false"  title="处理结果"></t:dictSelect>		--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>


			 <div class="form-group">
						<label for="remark" class="col-sm-3 control-label">备注：</label>
						<div class="col-sm-7">
						<div class="input-group" style="width:100%">
						  	 	<textarea name="remark" class="form-control input-sm" rows="6" maxlength="1000" placeholder="请输入备注(小于1000字)" ignore="ignore" >无。</textarea>
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
		  elem: this,
		  format: 'yyyy-MM-dd HH:mm',
		  type: 'datetime',
			max: crtTimeFtt2(new Date())
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
/**
 * 选择组织机构
 */
function openDepartmentSelect() {
    $.dialog.setting.zIndex = getzIndex();
    var orgIds = $("#dutyId").val();

    $.dialog({
        content: 'url:departController.do?departSelectRemoveFeiguan&orgIds=' + orgIds,
        zIndex: getzIndex(),
        title: '组织机构列表',
        lock: true,
        width: '400px',
        height: '350px',
        opacity: 0.4,
        button: [{
            name: '<t:mutiLang langKey="common.confirm"/>',
            callback: callbackDepartmentSelect,
            focus: true
        },
            {
                name: '<t:mutiLang langKey="common.cancel"/>',
                callback: function() {}
            }]
    }).zindex();

}

function callbackDepartmentSelect() {
    var iframe = this.iframe.contentWindow;
    var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
    var nodes = treeObj.getCheckedNodes(true);
    if(nodes.length>1){
        showMeg("仅可选择一个单位");
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
        $('#dutyCompany').val(names);
        $('#dutyCompany').blur();
        //$('#orgIds').val(ids);
        $('#dutyId').val(ids);
        $('#dutyOrgCode').val(orgcode);

    }
}

function callbackClean(){
    $('#dutyCompany').val('');
    $('#dutyId').val('');
}

function setOrgIds() {
    return true;
}
$(function(){
    $("#dutyCompany").prev().hide();
});
</script>
</body>
</html>