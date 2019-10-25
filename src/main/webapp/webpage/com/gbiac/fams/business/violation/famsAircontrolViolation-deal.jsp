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
    .beizhu{
        width: 51%;
        margin-left: -4px;
        resize: none;
    }
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolViolationController.do?doDeal" method="POST">

        <input type="hidden" id="btn_sub" class="btn_sub"/>
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
					<input placeholder="请输入当事人姓名" name="dutyPersonName" type="text" class="form-control input-sm" maxlength="32"  value = "${famsAircontrolViolation.dutyPersonName}" ignore="ignore"  />
				</div>
			</div>
		</div>

            <div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					当事人联系方式：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input placeholder="请输入当事人联系方式" name="dutyPersonMobile" type="text" class="form-control input-sm" maxlength="32" value = "${famsAircontrolViolation.dutyPersonMobile}" ignore="ignore"  oninput = "value=value.replace(/[^\d]/g,'')" />
				</div>
			</div>
		</div>

            <div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					违章地点：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input placeholder="请输入违章地点" name="violationAddr" type="text" class="form-control input-sm" maxlength="64" value = "${famsAircontrolViolation.violationAddr}" ignore="ignore"  />
				</div>
			</div>
		</div>

        <div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					控制区通行证号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input placeholder="请输入控制区通行证号" name="apronCard" type="text" class="form-control input-sm" maxlength="32" value = "${famsAircontrolViolation.apronCard}" ignore="ignore"  />
				</div>
			</div>
		</div>

		<div class="bt-item col-md-6 col-sm-6">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					车牌号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input placeholder="请输入车牌号" name="carNumber" type="text" class="form-control input-sm" maxlength="32" value = "${famsAircontrolViolation.carNumber}" ignore="ignore"  />
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



            <div class="bt-item col-md-6 col-sm-6 beizhu">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        备注：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <textarea style="resize:none;width: 98%" placeholder="请输入备注(小于1000字)" maxlength="1000" name="remark" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.remark}</textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">备注</label>
                    </div>
                </div>
            </div>

            <div class="bt-item col-md-6 col-sm-6" style="width: 49%;margin-left: -8px;
        ">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        违章行为：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <textarea readonly="readonly" style="resize:none;width: 102%" placeholder="请输入违章行为(小于1000字)" maxlength="1000"  name="violationName" class="form-control input-sm" rows="6"  iignore="checked" datatype="*" >${famsAircontrolViolation.violationName}</textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">违章行为</label>
                    </div>
                </div>
            </div>

            <%--<div class="bt-item col-md-6 col-sm-6">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-3 col-sm-3 col-xs-3 bt-label">--%>
                        <%--处罚依据：--%>
                    <%--</div>--%>
                    <%--<div class="col-md-9 col-sm-9 col-xs-9 bt-content">--%>
                        <%--<textarea name="panishRule" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.panishRule}</textarea>--%>
                        <%--<span class="Validform_checktip" style="float:left;height:0px;"></span>--%>
                        <%--<label class="Validform_label" style="display: none">处罚依据</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="bt-item col-md-6 col-sm-6">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        <span style="color:red;">*</span>处罚依据：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <input type="hidden" id="panishRule" name="panishRule" value=""/>

                        <p class="ch1"><input class="form-control i-checks" type="checkbox"  id="1" value="《民用机场运行安全管理规定》第"/>《民用机场运行安全管理规定》第<input type="text" style="width: 30px;"  maxlength="11" value=""  oninput = "value=value.replace( /[^0-9]/g,'');if(value<1)value=''">条</p>
                        <p class="ch1"><input class="form-control i-checks" type="checkbox"  id="2" value="《民用机场航空器活动区道路交通安全管理规则》第"/>《民用机场航空器活动区道路交通安全管理规则》第<input type="text" style="width: 30px;" value="" maxlength="11" oninput = "value=value.replace( /[^0-9]/g,'');if(value<1)value=''">条</p>
                        <p class="ch1"><input class="form-control i-checks" type="checkbox"  id="3" value="《广州白云国际机场航空器活动区地面运行管理规定》第"/>《广州白云国际机场航空器活动区地面运行管理规定》第<input type="text" style="width: 30px;" value="" maxlength="11" oninput = "value=value.replace( /[^0-9]/g,'');if(value<1)value=''">条</p>
                        <p class="ch1"><input class="form-control i-checks" type="checkbox"  id="4" value="《广州白云国际机场航空器活动区道路交通安全管理实施细则》第"/>《广州白云国际机场航空器活动区道路交通安全管理实施细则》第<input type="text" style="width: 30px;" maxlength="11"value="" oninput = "value=value.replace( /[^0-9]/g,'');if(value<1)value=''">条</p>
                        <p class="ch1"><input class="form-control i-checks" type="checkbox"  id="5" value="《广州白云国际机场航空器活动区车辆设备停放管理实施细则》第"/>《广州白云国际机场航空器活动区车辆设备停放管理实施细则》第<input type="text" style="width: 30px;" maxlength="11" value="" oninput = "value=value.replace( /[^0-9]/g,'');if(value<1)value=''">条</p>
                        <p class="ch1"><input class="form-control i-checks" type="checkbox"  id="6" value="《广州白云国际机场航空器活动区文明行为规范》第"/>《广州白云国际机场航空器活动区文明行为规范》第<input type="text" style="width: 30px;" value="" maxlength="11" oninput = "value=value.replace( /[^0-9]/g,'');if(value<1)value=''">条</p>

                    </div>
                </div>
            </div>
                <%--<div class="bt-item col-md-6 col-sm-6">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-3 col-sm-3 col-xs-3 bt-label">--%>
                            <%--准入处理：--%>
                        <%--</div>--%>
                        <%--<div class="col-md-9 col-sm-9 col-xs-9 bt-content">--%>
                            <%--<textarea name="accessPanishResult" class="form-control input-sm" rows="6"  ignore="ignore" >${famsAircontrolViolation.accessPanishResult}</textarea>--%>
                            <%--<span class="Validform_checktip" style="float:left;height:0px;"></span>--%>
                            <%--<label class="Validform_label" style="display: none">准入处理</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

            <div class="bt-item col-md-6 col-sm-6">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        <span style="color:red;">*</span>处理结果：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">

                        <input type="hidden" id="accessPanishResult" name="accessPanishResult" value="测试"/>

                        <p class="rd1"><input type="radio" class="form-control i-checks"  name="hu" value="暂扣人员控制区通行证" />暂扣<input class="form-control i-checks" type="radio" name="hu" value="吊销人员控制区通行证" />吊销 人员控制区通行证</p>
                        <p class="rd1"><input type="radio" class="form-control i-checks" name="group" value="暂扣人员航空器活动区驾驶证" />暂扣<input class="form-control i-checks" type="radio" name="group" value="吊销人员航空器活动区驾驶证" />吊销 人员航空器活动区驾驶证</p>
                        <p class="rd1"><input type="radio" class="form-control i-checks" name="cheliang" value="暂扣车辆航空器活动区通行证" />暂扣<input class="form-control i-checks" type="radio" name="cheliang" value="吊销车辆航空器活动区通行证" />吊销 车辆航空器活动区通行证</p>
                        <p class="ap1"><input type="checkbox" class="form-control i-checks"  id="7" value="当事人扣" />当事人扣 <input type="text" value="" maxlength="11" placeholder="分值范围1-12" oninput="value=value.replace( /[^0-9]/g,'');if(value>12)value=12;if(value.length>2)value=value.slice(0,2);if(value<1)value=''" >分</p>
                        <p class="ap1"><input type="checkbox" class="form-control i-checks"  id="8" value="当事人须参加培训" />当事人须参加培训<input type="text" maxlength="11" placeholder="请输入课时" value="" oninput="value=value.replace( /[^0-9]/g,'');if(value<1)value=''" >课时</p>
                        <p class="ap1"><input type="checkbox" class="form-control i-checks" id="9" value="所属单位扣" />所属单位扣<input type="text" value="" maxlength="11" placeholder="分值范围1-12" oninput="value=value.replace( /[^0-9]/g,'');if(value>12)value=12;if(value.length>2)value=value.slice(0,2);if(value<1)value=''" >分</p>
                    </div>
                </div>
            </div>



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
                            <jsp:param name= "directory" value= "violation"/>
                            <jsp:param name= "multiple" value= "multiple"/>
                            <jsp:param name= "required" value= ""/>
                            <jsp:param name= "acceptFileType" value= "image/*"/>
                            <jsp:param name="types" value="${types}" />

                        </jsp:include>
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
		    if(setpanishRule()||setaccessPanishResult()){
		        return false;
            }
            // setpanishRule();
            // setaccessPanishResult();
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
    var a_num=0
    var b_num=0;
    $(".ch1 .icheckbox_square-green").addClass('aa');
    $(".aa input[type='checkbox']:checkbox:checked").each(function(i,d){
        // ids.push(d.value+$(this).next().val()+"条;");
        a_num+=1;
        var ruleNum=$(this).parent().next().val();
        //console.log("ruleNum",ruleNum);

        if(ruleNum!=''){
            b_num+=1;
        }
        p_rule+=d.value+ruleNum+"条;\r\n"
       // console.log("testag",p_rule);

    });
    if(p_rule==''||(a_num!=b_num)){
        showMeg("请正确填写 处罚依据");
        return true;
    }else{
        $("#panishRule").val(p_rule);
        return false;

    }

    // console.log($("#panishRule").val());
    // console.log(p_rule);
}

function setaccessPanishResult() {


    var str = '';
    // console.log(aa)

    var aa = $(".rd1 input[type='radio']:checked");
    for(var j = 0;j<aa.length;j++){
        str += aa[j].value +';\r\n';
    }
    var a_num=0
    var b_num=0;
    $(".ap1 .icheckbox_square-green").addClass('bb');
    $(".bb input[type='checkbox']:checkbox:checked").each(function (i, d) {
        a_num+=1;
        var d_num=$(this).parent().next().val();
        if(d_num!=''){
            b_num+=1;
        }
        // console.log("i= "+i)
        // console.log("d= "+d)

        if(d.value.indexOf("培训") != -1){
            str += d.value + d_num + '课时;\r\n'
        }else{
            str += d.value + d_num + '分;\r\n'
        }

    });

    if(str==''||(a_num!=b_num)){
        showMeg("请正确填写 处理结果");
        return true;
    }else{
        //console.log(str)
        $("#accessPanishResult").val(str);
        return false;
    }



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