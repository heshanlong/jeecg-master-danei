<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>航班保障作业检查单主记录</title>
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
	<form class="form-horizontal" role="form" id="formobj" action="famsAircontrolSafecheckController.do?doAdd" method="POST">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="airLine" name="airLine"/>
		<input type="hidden" id="airPlanId" name="airPlanId"/>

	<%--<div class="form-group">--%>
			<%--<label for="number" class="col-sm-3 control-label">编号：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<input id="number" name="number" type="text" maxlength="32" value="${no}"  readonly="readonly" class="form-control input-sm" placeholder="请输入编号"  ignore="ignore" />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="form-group">--%>
			<%--<label for="airNumber" class="col-sm-3 control-label"><span style="color:red;">*</span>航班号：</label>--%>
			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>
					<%--<input id="airNumber" name="airNumber" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入航班号"  ignore="checked" datatype="*" />--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>

		<%--<div class="form-group">--%>
			<%--<label for="airNumber" class="col-sm-3 control-label"><span style="color:red;">*</span>航班号：</label>--%>

			<%--<div class="col-sm-7">--%>
				<%--<div class="input-group" style="width:100%">--%>

					<%--<input id="airNumber" name="airNumber" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入航班号"  ignore="checked" datatype="*"  >--%>

					<%--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">--%>
						<%--<span class="caret"></span>--%>
					<%--</button>--%>
					<%--<ul class="dropdown-menu dropdown-menu-right" role="menu">--%>
					<%--</ul>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>

		<div class="form-group">
			<label for="airNumber" class="col-sm-3 control-label"><span style="color:red;">*</span>航班号：</label>

			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="airNumber" name="airNumber" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入航班号"  ignore="checked" datatype="*"  >
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-menu-right" role="menu">
						</ul>
					</div>
					<!-- /btn-group -->
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="airTail" class="col-sm-3 control-label"><span style="color:red;">*</span>机尾号：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input readonly="readonly" id="airTail" name="airTail" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入机尾号"  ignore="checked" datatype="*" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="airModel" class="col-sm-3 control-label"><span style="color:red;">*</span>机型：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input  readonly="readonly" id="airModel" name="airModel" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入机型" ignore="checked" datatype="*" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="airSlots" class="col-sm-3 control-label"><span style="color:red;">*</span>停机位：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input readonly="readonly" id="airSlots" name="airSlots" type="text" maxlength="32" class="form-control input-sm" placeholder="请输入停机位"  ignore="checked" datatype="*" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="checkDate" class="col-sm-3 control-label"><span style="color:red;">*</span>检查日期：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input name="checkDate" type="text" class="form-control laydate-datetime"  ignore="checked" datatype="*"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='both' value='${defaultDate}' />"/>
				<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="actualDate" class="col-sm-3 control-label"><span style="color:red;">*</span>实际时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input name="actualDate" type="text" class="form-control laydate-datetime"  ignore="checked"  datatype="*" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='both' value='${defaultDate}'/>"/>
					<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="preDate" class="col-sm-3 control-label"><span style="color:red;">*</span>预计时间：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
				<input name="preDate" type="text" class="form-control laydate-datetime"  ignore="checked" datatype="*"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' type='both' value='${defaultDate}' />"/>
				<span class="input-group-addon" ><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
        <div class="form-group">
            <label for="isEnable" class="col-sm-3 control-label">图片和视频：</label>
            <div class="col-sm-7">
                <jsp:include page= "/webpage/common/jsp/upload.jsp">

					<jsp:param name= "name" value= "picture"/>
					<jsp:param name= "ids" value= ""/>
					<jsp:param name= "value" value= ""/>
					<jsp:param name= "fileNames" value= ""/>
					<jsp:param name= "sizes" value= ""/>
					<jsp:param name= "directory" value= "safecheck"/>
					<jsp:param name= "multiple" value= "multiple"/>
					<jsp:param name= "required" value= ""/>
					<jsp:param name= "typeSwf" value= "0"/>

				</jsp:include>
            </div>

        </div>
		<%--<div class="bt-item col-md-9 col-sm-6">--%>
			<%--<input type="hidden" id="radioResult" name="radioResult" value=""/>--%>



			<%--<c:forEach items="${famsAircontrolSafecheckconfigListEntity}" var="configvar" varStatus="vs">--%>

				<%--<div class="col-md-3 col-sm-3 col-xs-3 bt-label">--%>
						<%--${configvar.projectName}--%>
				<%--</div>--%>
					<%--<c:forEach items="${configvar.configList}" var="configLisVar" varStatus="vs">--%>
						<%--<div class="row">--%>

								<%--<p class="rd1">--%>
										<%--${configLisVar.projectDetail}--%>
										<%--<input type="radio" name="${configLisVar.id}" value="${configLisVar.id}:1" checked />符合--%>
										<%--<input type="radio" name="${configLisVar.id}" value="${configLisVar.id}:2" />不符合--%>
										<%--<input type="radio" name="${configLisVar.id}" value="${configLisVar.id}:3" />不适用--%>
								<%--</p>--%>

						<%--</div>--%>
					<%--</c:forEach>--%>

			<%--</c:forEach>--%>
		<%--</div>--%>
        <div style="line-height: 200%;">
            <input type="hidden" id="radioResult" name="radioResult" value="" />
            <table style="width: 100%">
                <c:forEach items="${famsAircontrolSafecheckconfigListEntity}" var="configvar" varStatus="vs">
                    <tr>
                        <th style="text-align: left;">${configvar.projectName}</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${configvar.configList}" var="configLisVar" varStatus="vs">
                        <tr>
                            <td>${configLisVar.projectDetail}</td>
                            <td class="rd1">
								<input type="radio" name="${configLisVar.id}" value="${configLisVar.id}:1" checked />符合
                                <input type="radio" name="${configLisVar.id}" value="${configLisVar.id}:2" />不符合
                                <input type="radio" name="${configLisVar.id}" value="${configLisVar.id}:3" />不适用
							</td>
                        </tr>
                    </c:forEach>

                </c:forEach>
            </table>
        </div>
	</form>
	</div>
 </div>
 <script src="plug-in/hplus/plugins/suggest/bootstrap-suggest.min.js"></script>

 <script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
    //console.log(famsAircontrolSafecheckconfigListEntity)
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
            setradioResult();
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
function setradioResult() {


    var str = '';
    // console.log(aa)

    var aa = $(".rd1 input[type='radio']:checked");
    for(var j = 0;j<aa.length;j++){
        str += aa[j].value +';'
    }



    console.log(str)
    $("#radioResult").val(str);

}
var resultTemp;
var testBsSuggest = $("#airNumber").bsSuggest({
	url: "famsCommonController/loadFlightData.do?keyword="+$("#airNumber").val(),
   // url: "famsCommonController.do?loadFlightData?"+$("#airNumber").val(),

    idField: "rownum",
    showBtn: false,
    showHeader: false,
    getDataMethod: 'data',
    allowNoKeyword: false,
    inputWarnColor: 'rgba(255,0,0,.1)', //输入框内容不是下拉列表选择时的警告色
    effectiveFields: ['flightno','crafttype','airlinefullcn'],            //有效显示于列表中的字段，非有效字段都会过滤，默认全部。
    effectiveFieldsAlias: {'flightno':'航班号','crafttype':'机型','airlinefullcn':'航线'},       //有效字段的别名对象，用于 header 的显示
    delay: 0.1,
    keyField:"flightno",  //每组数据的哪个字段作为输入框内容，优先级高于 indexKey 设置（推荐）
    indexId: 0,                     //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
    indexKey: 1                    //每组数据的第几个数据，作为input输入框的内容

}).on('onDataRequestSuccess', function (e, result) {
    resultTemp=result;
    //console.log('onDataRequestSuccess: ', result);
}).on('onSetSelectValue', function (e, keyword) {
    //console.log('onSetSelectValue: ', keyword);

    //console.log('onSetSelectValue: ', resultTemp.value[keyword.id-1].depart);
    $("#airTail").val(resultTemp.value[keyword.id-1].craftno);
	$("#airModel").val(resultTemp.value[keyword.id-1].crafttype);
    $("#airSlots").val(resultTemp.value[keyword.id-1].craftsite);

    $("#airPlanId").val(resultTemp.value[keyword.id-1].id);
    $("#airLine").val(resultTemp.value[keyword.id-1].airlinefullcn);

}).on('onUnsetSelectValue', function (e) {
    $("#airTail").val(null);
    $("#airModel").val(null);
    $("#airSlots").val(null);

    $("#airPlanId").val(null);
    $("#airLine").val(null);
});
</script>
</body>
</html>