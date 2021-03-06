
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_airportrunway_mark</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<style>
	.panel-body{
		border-style: none;
	}
	input[type=checkbox]{
		margin: auto;!important;
		/*padding-right: 4px;!important;*/
	}
	.paddTop{
		padding-top: 6px;
	}
</style>
</head>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsAirportrunwayMarkController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<div class="row">
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业人：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="people" type="text" class="form-control input-sm" maxlength="100"  placeholder="请填写作业人,多位作业人需用逗号分隔" value = "${realName}"  ignore="checked" datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业开始时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="startTime" name="startTime" type="text"   value="${sdf }" readonly="readonly" class="Wdate form-control laydate-datetime"    ignore="checked"  datatype="*"  />
                </div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>作业结束时间：
				</div>
				<div class="col-md-3 col-sm-3 col-xs-3 bt-content">
					<input id="endTime" name="endTime" type="text"    value="${edf }" readonly="readonly" class="Wdate form-control laydate-datetime"     ignore="checked"  datatype="*"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					跑道标志：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content paddTop" >
					<t:dictSelect field="runwayLogo" typeGroupCode="runwayLogo" hasLabel="false"  type="checkbox" ></t:dictSelect>
				</div> 
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					滑行道标志：
				</div>
				
				
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content paddTop	" >
					<%-- <t:dictSelect field="taxiwayMark" typeGroupCode="taxiway" hasLabel="false"  type="checkbox" ></t:dictSelect> --%>
					<c:forEach var="categoryList" items="${categoryList2}">
						<input name="taxiwayMark" type="checkbox"  value="${categoryList.code}"   ignore="ignore" >${categoryList.name}</input>
						
						<c:if test="${not empty categoryList.list}">(</c:if>
						<c:forEach var="categoryListlist" items="${categoryList.list}">
							&nbsp;<input name="taxiwayMark" type="checkbox"  value="${categoryListlist.code}"   onclick="taxiwayMarkType(this.value);"  ignore="ignore" >${categoryListlist.name}</input>
						</c:forEach>
						<c:if test="${not empty categoryList.list}">)</c:if>
						
						</br>
				    </c:forEach>
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					机坪/服务车道标志：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content paddTop" >
					<c:forEach var="categoryList" items="${categoryList}">
						<input name="couldSign" type="checkbox"  value="${categoryList.code}"   ignore="ignore" >${categoryList.name}</input>
						
						<c:if test="${not empty categoryList.list}">(</c:if>
						<c:forEach var="categoryListlist" items="${categoryList.list}">
							&nbsp;<input name="couldSign" type="checkbox"  value="${categoryListlist.code}"   onclick="couldSignType(this.value);"  ignore="ignore" >${categoryListlist.name}</input>
						</c:forEach>
						<c:if test="${not empty categoryList.list}">)</c:if>
						
						</br>
				    </c:forEach>
					
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					其他标志：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="otherSigns" type="text" class="form-control input-sm" maxlength="1000" placeholder="可选" ignore="ignore"  />
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>位置：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input name="location" type="text" class="form-control input-sm" maxlength="1000" placeholder="请填写位置信息"  ignore="checked"  datatype="*"  />
				</div>
			</div>
		</div>
		
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>面积：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<%--datatype="n1-100"--%>
					<input id="area"  name="area" type="text"  ignore="checked"  maxlength="100"  placeholder="请输入面积"  datatype="*"   onblur='checkFloatTwo("area")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"/>平方米
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>油漆：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="paint"  name="paint" type="text"  ignore="checked"  maxlength="100" placeholder="请输入油漆数量"  datatype="*"   onblur='checkFloatTwo("paint")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"/>桶
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					<span style="color:red;">*</span>天那水：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<input id="dayWater"  name="dayWater" type="text"  ignore="checked"  placeholder="请输入天那水数量" maxlength="100"  datatype="*"   onblur='checkFloatTwo("dayWater")' oninput = "value=value.replace(/[^\d.]/g,'');  value=value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');if(isNaN(value)){value = value.substr(0,value.length-1)}"/>桶
				</div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="form-group">
			    <label for="isEnable" class="col-md-3 col-sm-3 col-xs-3 bt-label  control-label">图片：</label>
			    <div class="col-sm-7">
			        <jsp:include page= "/webpage/common/jsp/upload.jsp">
			        	<jsp:param name= "name" value= "files"/>
						<jsp:param name= "ids" value= ""/>
						<jsp:param name= "value" value= ""/>
						<jsp:param name= "fileNames" value= ""/>
						<jsp:param name= "sizes" value= ""/>
						<jsp:param name= "directory" value= "airportrunwayMark"/>
						<jsp:param name= "multiple" value= "multiple"/>
						<jsp:param name= "required" value= ""/>
						<jsp:param name= "disable" value= ""/>
						<jsp:param name= "typeSwf" value= "0"/>
						<jsp:param name= "acceptFileType" value= "image/*"/>
			        </jsp:include>
			    </div>
			</div>
		</div>
		<div class="bt-item col-md-6 col-sm-6" style="width:90%">
			<div class="row">
				<div class="col-md-3 col-sm-3 col-xs-3 bt-label">
					备注：
				</div>
				<div class="col-md-9 col-sm-9 col-xs-9 bt-content">
					<textarea name="note" cols="55" rows="5" maxlength="2000" placeholder="请填写小于1000字的信息" style="width:100%"></textarea>
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
function couldSignType(val){
	$("input[name='couldSign']").each(function(){
	//alert($(this).val());
	if($(this).val()==val.substr(0,6)){
			$(this).attr("checked","checked");
		}
	})
	
}
function taxiwayMarkType(val){
	$("input[name='taxiwayMark']").each(function(){
	//alert($(this).val());
	if($(this).val()==val.substr(0,6)){
			$(this).attr("checked","checked");
		}
	})
	
}
/**
 * 检查时间大小顺序，id1<id2
 * @param obj 操作对象
 * @param id1 小的id
 * @param id2 大的id
 */
function checkTimeSequence(obj,id1,id2){
	var time1=$("#"+id1).val();
	var time2=$("#"+id2).val();
	if(time1!=""&&time2!=""&&new Date(time1)>=new Date(time2)){
        showMeg("开始时间必须小于结束时间！");
        $(obj).val('');
	}

	
}

$(document).ready(function() {
	$(".laydate-datetime").each(function(){
		var _this = this;
		laydate.render({
		  elem: this,
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
	//判断是否有勾选
	function cleanWhyType(){
		//console.log($("input[name='runwayLogo']:checked").length)
		if ($("input[name='runwayLogo']:checked").length==0&&$("input[name='taxiwayMark']:checked").length==0&&$("input[name='couldSign']:checked").length==0){
			//$("input[name='runwayLogo']).eq(1).attr({datatype:'*',ignore="checked"}
			$("input[name='runwayLogo']").attr("ignore","checked");
			$("input[name='runwayLogo']").attr("datatype","*");
			
		}else{
			$("input[name='runwayLogo']").removeAttr("datatype");
			$("input[name='runwayLogo']").removeAttr("ignore");
		}
		
}
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
		beforeCheck:function(curform){
		//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
		//这里明确return false的话将不会继续执行验证操作;
		//如果三个input一个都没有选中，给第一个input添加一个datatype *

		cleanWhyType();
		
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