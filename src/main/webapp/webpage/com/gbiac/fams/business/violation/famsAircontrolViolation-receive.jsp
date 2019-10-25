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
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
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
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolViolationController.do?doReceive" method="POST">

        <input type="hidden" id="btn_sub" name="btn_sub" class="btn_sub"/>
        <input type="hidden" id="id" name="id" value="${famsAircontrolViolation.id}"/>


        <div class="row">
            <div class="bt-item col-md-6 col-sm-6">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        发现时间：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <input disabled="disabled" name="decideDate" type="text" class="form-control input-sm laydate-date"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${famsAircontrolViolation.decideDate}'/>"  ignore="ignore"  />
                    </div>
                </div>
            </div>

            <c:if test="${not empty famsAircontrolViolation.dealDate}">

                <div class="bt-item col-md-6 col-sm-6">
                    <div class="row">
                        <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                            开单时间：
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                            <input disabled="disabled" name="dealDate" type="text" class="form-control input-sm laydate-date"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${famsAircontrolViolation.dealDate}'/>"  ignore="ignore"  />
                        </div>
                    </div>
                </div>
            </c:if>


            <div class="bt-item col-md-6 col-sm-6">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        单位名称：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <input disabled="disabled" name="dutyCompany" type="text" class="form-control" maxlength="32" value = "${famsAircontrolViolation.dutyCompany}"  ignore="ignore"  />
                    </div>
                </div>
            </div>


		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					当事人姓名：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dutyPersonName" type="text" class="form-control input-sm" maxlength="32"  value = "${famsAircontrolViolation.dutyPersonName}" ignore="ignore"  />
				</div>
			</div>
		</div>

            <div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					当事人联系方式：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="dutyPersonMobile" type="text" class="form-control input-sm" maxlength="32" value = "${famsAircontrolViolation.dutyPersonMobile}" ignore="ignore"  />
				</div>
			</div>
		</div>

            <div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					违章地点：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="violationAddr" type="text" class="form-control input-sm" maxlength="64" value = "${famsAircontrolViolation.violationAddr}" ignore="ignore"  />
				</div>
			</div>
		</div>

        <div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					控制区通行证号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="apronCard" type="text" class="form-control input-sm" maxlength="32" value = "${famsAircontrolViolation.apronCard}" ignore="ignore"  />
				</div>
			</div>
		</div>

		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					车牌号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="carNumber" type="text" class="form-control input-sm" maxlength="32" value = "${famsAircontrolViolation.carNumber}" ignore="ignore"  />
				</div>
			</div>
		</div>

		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					临时处理决定：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
	            	<div style="padding-top:5px">
                        <t:dictSelect  readonly="readonly" field="apronPanishResult" defaultVal = "${famsAircontrolViolation.apronPanishResult}" extendJson="{class:'i-checks'}" type="checkbox" hasLabel="false"  title="机坪监管部临时处理决定"  typeGroupCode="voi_d_str" ></t:dictSelect>
	            	</div>
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

            <c:if test="${not empty famsAircontrolViolation.panishRule}">
                <div class="bt-item col-md-6 col-sm-6">
                    <div class="row">
                        <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                            处罚依据：
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                            <textarea disable="disable" name="panishRule" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.panishRule}</textarea>
                            <span class="Validform_checktip" style="float:left;height:0px;"></span>
                            <label class="Validform_label" style="display: none">处罚依据</label>
                        </div>
                    </div>
                </div>

            </c:if>




            <c:if test="${not empty famsAircontrolViolation.accessPanishResult}">

                <div class="bt-item col-md-6 col-sm-6">
                    <div class="row">
                        <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                            准入处理：
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                            <textarea name="accessPanishResult" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.accessPanishResult}</textarea>
                            <span class="Validform_checktip" style="float:left;height:0px;"></span>
                            <label class="Validform_label" style="display: none">准入处理</label>
                        </div>
                    </div>
                </div>
            </c:if>


            <div class="bt-item col-md-6 col-sm-6">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        <%--<label for="isEnable" class="col-sm-3 control-label">图片和视频</label>--%>
                        图片:

                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <jsp:include page= "/webpage/common/jsp/upload.jsp">

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
            </div>

            <c:if test="${not empty famsAircontrolViolation.rebackDes}">

                <div class="bt-item col-md-6 col-sm-6">
                    <div class="row">
                        <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                            撤 销/回理由：
                        </div>
                        <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                            <textarea name="rebackDes" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.rebackDes}</textarea>
                            <span class="Validform_checktip" style="float:left;height:0px;"></span>
                            <label class="Validform_label" style="display: none">撤销理由</label>
                        </div>
                    </div>
                </div>
            </c:if>




		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
     // $(":input").attr("disabled","true");
     $(":input").attr("readonly","readonly");
    //$(":input").attr("style","border:0;border-bottom:1 solid black;background:white;");
     $("span").css("display","none");
    $("a").css("display","none");



    $(".jeecgDetail").css("display","none");
    //$("button").css("color","red");

    //$(".ui_state_highlight").attr("disabled","false");
    //$(".ui_state_highlight").removeAttr("disabled");


	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: this,
		  format: 'yyyy-MM-dd HH:mm:ss',
		  type: 'datetime'
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
            setpanishRule();
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

function setpanishRule() {
    var p_rule='';
    $("input[type='checkbox']:checkbox:checked").each(function(i,d){
        // ids.push(d.value+$(this).next().val()+"条;");
        p_rule+=d.value+$(this).next().val()+"条;"

    });
    $("#panishRule").val(p_rule);

    // console.log($("#panishRule").val());
    // console.log(p_rule);
}
/**
 * 选择组织机构
 */
function openDepartmentSelect() {
    $.dialog.setting.zIndex = getzIndex();
    var orgIds = $("#dutyId").val();

    $.dialog({
        content: 'url:departController.do?departSelect&orgIds=' + orgIds,
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
        alert("仅可选择一个单位");
    }
    if(nodes.length>0){
        var ids='',names='';
        for(i=0;i<nodes.length;i++){
            var node = nodes[i];
            ids += node.id;
            names += node.name;
        }
        $('#dutyCompany').val(names);
        $('#dutyCompany').blur();
        //$('#orgIds').val(ids);
        $('#dutyId').val(ids);

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