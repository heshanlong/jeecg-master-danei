<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_airportrunway_surfaceclean</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<style>
	input[type=checkbox]{
		margin: auto;!important;
		padding-right: 4px;!important;
	}
	.panel-body{
		border-style: none;
	}
	.poptip{
		left:150px!important;
    	margin-top:-35px!important;	
	}
</style>
<script type="text/javascript" src="webpage/common/js/common.js"></script>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAirportrunwaySurfacecleanController.do?doUpdate" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id" value="${famsAirportrunwaySurfaceclean.id}"/>
		<div class="row">
		
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					编号：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="no" name="no" value = "${famsAirportrunwaySurfaceclean.no}" type="text" class="form-control input-sm" readonly="readonly" maxlength="100" ignore="checked" datatype="s1-100"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="createByCn" name="createByCn"  value = "${famsAirportrunwaySurfaceclean.createByCn}" type="text" class="form-control input-sm" readonly="readonly"/>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					创建时间：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="createDate" name="createDate"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='date'  value='${famsAirportrunwaySurfaceclean.createDate}'/>" type="text" class="form-control input-sm" readonly="readonly"/>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="people" type="text" class="form-control" maxlength="100"  placeholder="请填写作业人,多位作业人需用逗号分隔" value = "${famsAirportrunwaySurfaceclean.people}" ignore="checked" datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业开始时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="startTime" name="startTime" type="text"   readonly="readonly" class="Wdate form-control laydate-datetime" value="${famsAirportrunwaySurfaceclean.startTime}"  ignore="checked"  datatype="*"  />
					</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业结束时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="endTime"  name="endTime"   type="text" readonly="readonly" class="Wdate form-control laydate-datetime"  value="${famsAirportrunwaySurfaceclean.endTime}"  ignore="checked"  datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>清扫位置：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<c:forEach var="tSTypeList" items="${tSTypeList}">
						<input name="cleanLocation" type="checkbox"  value="${tSTypeList.typecode}" onClick="cleanLocationType()"  datatype='*'  >${tSTypeList.typename}</input>	
						<input id="${tSTypeList.typename}Other" name="cleanLocationOther"  readonly="readonly" type="text"  maxlength="2000" placeholder="${tSTypeList.typecode}位置" style="width:30x " ignore="ignore" /><br><br>
					</c:forEach>
					<input id="typename" type="hidden" value="${typename}" maxlength="2000"  ignore="ignore" style="width:111px;height:111px" />
					<input id="cleanLocation" type="hidden" value="${famsAirportrunwaySurfaceclean.cleanLocation}" maxlength="2000"  />
					<input id="cleanLocationOther" type="hidden" value="${famsAirportrunwaySurfaceclean.cleanLocationOther}" maxlength="2000"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>清扫原因：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<t:dictSelect field="cleanWhy"  id="cleanWhy" typeGroupCode="cleanWhy" hasLabel="false" defaultVal="${famsAirportrunwaySurfaceclean.cleanWhy}" type="checkbox"  datatype="*"  extendJson="{onclick:'cleanWhyType()'}"></t:dictSelect>
					<input id="cleanWhyOther" name="cleanWhyOther" type="text" readonly="readonly" placeholder="其他原因"  value = "${famsAirportrunwaySurfaceclean.cleanWhyOther}" maxlength="300"  style="width:30x "  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>清扫设备：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<t:dictSelect field="cleanEquipment" id="cleanEquipment"  typeGroupCode="cEquipment" hasLabel="false" defaultVal="${famsAirportrunwaySurfaceclean.cleanEquipment}" type="checkbox"  datatype="*"  extendJson="{onclick:'cleanEquipmentType()'}"></t:dictSelect>
					<input id="cleanEquipmentOther" name="cleanEquipmentOther"  readonly="readonly" type="text" placeholder="其他设备" value = "${famsAirportrunwaySurfaceclean.cleanEquipmentOther}" maxlength="300"  style="width:30x "  ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>清扫面积：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<%--datatype="n1-100"--%>
					<input id="cleanArea" name="cleanArea" type="text"  ignore="checked"  datatype="*"  value = "${famsAirportrunwaySurfaceclean.cleanArea}" maxlength="100"  onblur='checkFloatTwo("cleanArea")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"/>平方米
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
						<jsp:param name= "directory" value= "airportrunwaySurfaceclean"/>
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
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					备注：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content" style="padding-left: 5px">
					<textarea id="note" name="note" cols="55" rows="5" maxlength="2000" placeholder="请填写小于1000字的信息" value = "${famsAirportrunwaySurfaceclean.note}"  ignore="ignore" style="width:100%"></textarea>
					<input id="added" type="hidden"  readonly="readonly"  value = "${famsAirportrunwaySurfaceclean.note}"  ignore="ignore"  />
					<!-- <input name="note" type="text" class="form-control input-sm" maxlength="255"  ignore="ignore" style="width:111px;height:111px" /> -->
					
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
function cleanLocationType(){
	var strgetSelectValue="";
	var getSelectValueMenbers = $("input[name='cleanLocation']:checked").each(function(j) {
		if (j >= 0) {
			if(strgetSelectValue.length > 0){
				strgetSelectValue += ",";
			}
			strgetSelectValue += $(this).val();
	 
		}
	});

	var cleanWhyArray = $("#typename").val().split(',');
	var cleanWhyArray2 =$("#typename").val();
	var strgetSelect = strgetSelectValue.split(',');
	for(var i=0;i<cleanWhyArray.length ;i++){
		var n = 0;
		for(var q=0;q<strgetSelect.length ;q++){
			if(cleanWhyArray[i] == strgetSelect[q]){
				//alert(cleanWhyArray[i]);
				$("#" + cleanWhyArray[i]+ "Other").removeAttr("readonly");
				$("#" + cleanWhyArray[i]+ "Other").attr("datatype","*");
				$("#" + cleanWhyArray[i]+ "Other").attr("ignore","checked");
				
				cleanWhyArray2 = cleanWhyArray2.replace(strgetSelect[q], "");
				n =1;
			}
		}
	}

	var cleanWhyArray3 = cleanWhyArray2.split(',');
	for(var i=0;i<cleanWhyArray3.length ;i++){
		$("#" + cleanWhyArray3[i]+ "Other").attr("readonly","readonly");
		$("#" + cleanWhyArray3[i]+ "Other").val('');
		$("#" + cleanWhyArray3[i]+ "Other").attr("ignore","ignore");
		$("#" + cleanWhyArray3[i]+ "Other").attr("datatype","");
	}
}

function cleanWhyType(){
	var strgetSelectValue="";
	var getSelectValueMenbers = $("input[name='cleanWhy']:checked").each(function(j) {
		if (j >= 0) {
			strgetSelectValue += $(this).val() + ","
	 
		}
	});
	var cleanWhyArray = strgetSelectValue.split(',');
	var n = 0;
	for(var i=0;i<cleanWhyArray.length - 1;i++){
		if(cleanWhyArray[i] == '其他'){
			$("#cleanWhyOther").removeAttr("readonly");
			$("#cleanWhyOther").attr("datatype","*");
			$("#cleanWhyOther").attr("ignore","checked");
			n = 1;
		}
	}
	if(n == 1){
		$("#cleanWhyOther").removeAttr("readonly");
		$("#cleanWhyOther").attr("datatype","*");
		$("#cleanWhyOther").attr("ignore","checked");
	}else{
		$("#cleanWhyOther").attr("readonly","readonly");
		$("#cleanWhyOther").val('');
		$("#cleanWhyOther").attr("ignore","ignore");
		$("#cleanWhyOther").attr("datatype","");
	}
}

function cleanEquipmentType(){
	var strgetSelectValue="";
	var getSelectValueMenbers = $("input[name='cleanEquipment']:checked").each(function(j) {
		if (j >= 0) {
			
			strgetSelectValue += $(this).val() + ","
	 
		}
	});

	var cleanWhyArray = strgetSelectValue.split(',');
	var n = 0;
	for(var i=0;i<cleanWhyArray.length - 1;i++){
		if(cleanWhyArray[i] == '其他'){
			$("#cleanEquipmentOther").removeAttr("readonly");
			$("#cleanEquipmentOther").attr("datatype","*");
			$("#cleanEquipmentOther").attr("ignore","checked");
			n = 1;
		}
	}
	if(n == 1){
		$("#cleanEquipmentOther").removeAttr("readonly");
		$("#cleanEquipmentOther").attr("datatype","*");
		$("#cleanEquipmentOther").attr("ignore","checked");
	}else{
		$("#cleanEquipmentOther").attr("readonly","readonly");
		$("#cleanEquipmentOther").val('');
		$("#cleanEquipmentOther").attr("ignore","ignore");
		$("#cleanEquipmentOther").attr("datatype","");
		
	}
}

$(document).ready(function() {
	//alert($("#cleanLocation").val());
	//$("input[type=radio][name=infType][value=1]").prop("checked",'checked');
	$("#note").val($("#added").val());
	var couldSign = $("#cleanLocation").val();
	var couldSignOther = $("#cleanLocationOther").val().split(",");
	var checkBoxArray = couldSign.split(",");
		for(var i=0;i<checkBoxArray.length;i++){
			$("input[name='cleanLocation']").each(function(){
				if($(this).val()==checkBoxArray[i]){
				
					$("#" + checkBoxArray[i] + "Other").val(couldSignOther[i]);
					$(this).attr("checked","checked");
				}
			})
		}
		
	cleanLocationType();
	cleanWhyType();
	cleanEquipmentType();
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


    //提示信息
    function validationMessage(obj, Validatemsg) {
        // console.log(obj)

        try {
            removeMessage(obj);

            var objOffset= obj.offset();
            if(obj.is(":hidden")){
                objOffset = obj.parent().offset();
            }
            obj.focus();


            var $poptip_error = $('<div class="poptip"><span class="poptip-arrow poptip-arrow-top"><em>◆</em></span>' + Validatemsg + '</div>').css("left",objOffset.left + 'px').css("top", objOffset.top + obj.height() + 15 + 'px')
            // console.log( objOffset.top + obj.height() + 5)
            $('body').append($poptip_error);
            if (obj.hasClass('form-control') || obj.hasClass('ui-select')) {
                obj.parent().addClass('has-error');
            }
            if (obj.hasClass('ui-select')) {
                $('.input-error').remove();
            }
            obj.change(function () {
                if (obj.val()) {
                    removeMessage(obj);
                }
            });
            if (obj.hasClass('ui-select')) {
                $(document).click(function (e) {
                    if (obj.attr('data-value')) {
                        removeMessage(obj);
                    }
                    e.stopPropagation();
                });
            }
            return false;
        } catch (e) {
            alert(e)
        }
    }
    //移除提示
    function removeMessage(obj) {
        obj.parent().removeClass('has-error');
        $('.poptip').remove();
        $('.input-error').remove();
    }

});
</script>
</body>
</html>