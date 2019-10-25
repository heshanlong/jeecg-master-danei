<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>fams_unsafeevent_tiredamage</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base
	type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
<style>
.rd1 {
	padding-top: 8px;
}

.panel-body {
	border-style: none;
}

.mybutton {
	line-height: 20px;
}
</style>
</head>
<body style="overflow: hidden; overflow-y: auto;">
	<div class="container" style="width: 100%;">
		<div class="panel-heading"></div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="formobj"
				action="famsUnsafeeventTiredamageController.do?doAdd" method="POST">
				<input type="hidden" id="btn_sub" class="btn_sub" /> <input
					type="hidden" id="id" name="id" />
				<div class="row">
					<input name="no" type="hidden" class="form-control input-sm"
						value="${no}" maxlength="255" ignore="ignore" readonly="readonly" />

					<div style="line-height: 200%;">
						<input type="hidden" id="radioResult" name="radioResult" value="" />
						<table style="width: 100%">
							<tr>
								<th style="text-align: left;">录入事件</th>
								<th></th>
							</tr>
							<tr>
								<td style="text-align: right;" class="col-md-1"><span
									style="color: red;">*</span>日期：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入日期"
									name="thedate" type="text" style="height: 26px"
									readonly="readonly" value="${sdf}"
									class="form-control input-sm laydate-date" ignore="checked"
									datatype="*" /></td>
								<td style="text-align: right;" class="col-md-1"><span
									style="color: red;">*</span>接报时间：</td>
								<td class="rd1 col-md-3">
									<%--hxdate是年月日时分秒  date是年月日  time是时分--%> <input
									placeholder="请输入接报时间" name="settime" type="text"
									value="${sdff}" style="height: 26px" readonly="readonly"
									class="form-control input-sm laydate-ymdate" ignore="checked"
									datatype="*" id="hxdate" />
								</td>
								<td style="text-align: right;" class="col-md-1"><span
									style="color: red;">*</span>信息来源：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入信息来源"
									name="sourceformation" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="1000" ignore="checked"
									datatype="*" /></td>

							</tr>

							<tr>
								<th style="text-align: left;">航班信息</th>
								<th></th>
							</tr>
							<tr>
								<td style="text-align: right;" class="col-md-1"><span
									style="color: red;">*</span>航班号：</td>
								<td class="rd1 col-md-3">
									<div class="input-group" style="width: 100%">
										<input id="flightno" name="flightno" type="text"
											maxlength="100" style="height: 26px"
											class="form-control input-sm" placeholder="请输入航班号"
											ignore="checked" datatype="*">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle"
												data-toggle="dropdown">
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu dropdown-menu-right" role="menu">
											</ul>
										</div>
									</div>
								</td>
								<!-- <td style="text-align: right;"  class="col-md-1" >航班注册号：</td>
                    <td class="rd1 col-md-3">
                        <input   id="flightregistrationnumber"  name="flightregistrationnumber" placeholder="系统查找自动填写" type="text" style="height:26px"  class="form-control input-sm" maxlength="100"  ignore="ignore"  />
                    </td> -->
								<td style="text-align: right;" class="col-md-1">机型：</td>
								<td class="rd1 col-md-3"><input id="models" name="models"
									type="text" style="height: 26px" class="form-control input-sm"
									maxlength="100" placeholder="系统查找自动填写" ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">机号：</td>
								<td class="rd1 col-md-3"><input id="immediately"
									name="immediately" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="100"
									placeholder="系统查找自动填写" ignore="ignore" /></td>
							</tr>
							<tr>

								<td style="text-align: right;" class="col-md-1">停机位：</td>
								<td class="rd1 col-md-3"><input id="slots" name="slots"
									type="text" style="height: 26px" class="form-control input-sm"
									maxlength="100" placeholder="系统查找自动填写" ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">航空公司：</td>
								<td class="rd1 col-md-3"><input id="airlinescn"
									name="airlinescn" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="100"
									placeholder="系统查找自动填写" ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">航线：</td>
								<td class="rd1 col-md-3"><input id="routes" name="routes"
									type="text" style="height: 26px" class="form-control input-sm"
									maxlength="100" placeholder="系统查找自动填写" ignore="ignore" /></td>
							</tr>
							<tr>

								<td style="text-align: right;" class="col-md-1">计划进港时间：</td>
								<td class="rd1 col-md-3"><input id="plannedtime"
									readonly="readonly" placeholder="系统查找自动填写" name="plannedtime"
									type="text" style="height: 26px"
									class="form-control input-sm  laydate-hxdate laydate-datetime"
									ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">计划出港时间：</td>
								<td class="rd1 col-md-3"><input id="planneddeparture"
									readonly="readonly" placeholder="系统查找自动填写"
									name="planneddeparture" type="text" style="height: 26px"
									class="form-control input-sm  laydate-hxdate laydate-datetime"
									ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">实际进港时间：</td>
								<td class="rd1 col-md-3"><input id="actualtimearrival"
									readonly="readonly" placeholder="系统查找自动填写"
									name="actualtimearrival" type="text" style="height: 26px"
									class="form-control input-sm  laydate-hxdate laydate-datetime"
									ignore="ignore" /></td>
							</tr>
							<tr>
								<td style="text-align: right;" class="col-md-1">实际出港时间：</td>
								<td class="rd1 col-md-3"><input id="actualdeparture"
									readonly="readonly" placeholder="系统查找自动填写"
									name="actualdeparture" type="text" style="height: 26px"
									class="form-control input-sm  laydate-hxdate laydate-datetime"
									ignore="ignore" /></td>
								<input id="starstation" name="starstation" type="hidden"
									class="form-control input-sm" maxlength="255" ignore="ignore"
									readonly="readonly" />
								<input id="starstationcn" name="starstationcn" type="hidden"
									class="form-control input-sm" maxlength="255" ignore="ignore"
									readonly="readonly" />
								<input id="terminalstation" name="terminalstation" type="hidden"
									class="form-control input-sm" maxlength="255" ignore="ignore"
									readonly="readonly" />
								<input id="terminalstationcn" name="terminalstationcn"
									type="hidden" class="form-control input-sm" maxlength="255"
									ignore="ignore" readonly="readonly" />
								<input id="airlines" name="airlines" type="hidden"
									class="form-control input-sm" maxlength="255" ignore="ignore"
									readonly="readonly" />
							</tr>

							<tr>
								<th style="text-align: left;">信息通报及调查</th>
								<th></th>
							</tr>
							
							<tr class="root-damages"id="root-damages">
								<td style="text-align: right;" class="col-md-1"><span
									style="color: red;">*</span>受损位置：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入受损位置"
									name="damagedlocation" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="1000" ignore="checked"
									datatype="s1-1000" /></td>
							</tr>
							<tr>
								<td style="text-align: right; white-space: nowrap"
									class="col-md-1">伤口尺寸长（毫米）：</td>
								<td class="rd1 col-md-3">
									<%--oninput = "value=value.replace(/[^\d]/g,'')"--%> <input
									placeholder="请输入伤口尺寸长（单位为毫米）" name="woundsizelong" type="text"
									style="height: 26px" class="form-control input-sm"
									maxlength="100" style="width: 20x" ignore="checked"
									datatype="d" step="0.01"
									oninput="value=value.replace(/[^\d|\/.]/g,'')" />
								</td>
								<td style="text-align: right;" class="col-md-1">宽（毫米）：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入宽度（单位为毫米）"
									name="woundsizewide" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="100" style="width:20x"
									ignore="checked" datatype="d"
									oninput="value=value.replace(/[^\d|\/.]/g,'')" /></td>
								<td style="text-align: right;" class="col-md-1">深（毫米）：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入深度（单位为毫米）"
									name="woundsizedeep" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="100" style="width:20x"
									ignore="checked" datatype="d"
									oninput="value=value.replace(/[^\d|\/.]/g,'')" /></td>
								<td>
									<div class="col-sm-3">
										<%-- 增加一行的按钮 --%>
										<a class="easyui-linkbutton"
											data-options="iconCls:'icon-remove'"
											onClick="if($('.root-damages').length>1){
												$(this).parent().parent().parent().prev().remove()
												$(this).parent().parent().parent().remove()
												}">
											</a>
										<%-- 删除当前行的按钮 --%>
										<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
											onClick="if($('.root-damages').length<5){
											var clone1=$(this).parent().parent().parent().prev().clone();
											var clone2=$(this).parent().parent().parent().clone();
											$(clone1).find('input').val('');
											$(clone2).find('input').val('');
											$('.root-damages:last').next().after(clone1)
											$(clone1).after(clone2)
											}">
											</a>
									</div>
								</td>
							</tr>
						
							<tr>
								<td style="text-align: right;" class="col-md-1">携带外来物：</td>
								<td class="rd1 col-md-3">
									<%--defaultVal="N" 控制默认选项Y or N--%> <t:dictSelect
										field="foreignmatter" typeGroupCode="is_main" hasLabel="false"
										type="radio" defaultVal="N"
										extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect>
								</td>
								<td style="text-align: right;" class="col-md-1">翻新胎：</td>
								<td class="rd1 col-md-3"><t:dictSelect
										field="renovationtire" typeGroupCode="is_main"
										hasLabel="false" type="radio" defaultVal="N"
										extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect></td>
								<td style="text-align: right;" class="col-md-1">影响后续航班：</td>
								<td class="rd1 col-md-3"><t:dictSelect
										field="impactsubsequentflights" typeGroupCode="is_main"
										hasLabel="false" type="radio" defaultVal="N"
										extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect></td>
							</tr>

							<tr>
								<td style="text-align: right;" class="col-md-1">通报场道时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入通报场道时间"
									name="announcetracktime" type="text" style="height: 26px"
									readonly="readonly"
									class="form-control input-sm laydate-hxdate" ignore="ignore" />
								</td>
								<td style="text-align: right;" class="col-md-1">场道反馈时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入场道反馈时间"
									name="trackfeedbacktime" type="text" style="height: 26px"
									readonly="readonly"
									class="form-control input-sm laydate-hxdate" ignore="ignore" />
								</td>
								<td
									style="text-align: right; padding-left: 0px; white-space: nowrap"
									class="col-md-1">通报值班领导时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入通报值班领导时间"
									name="announcedutytime" type="text"
									style="height: 26px; padding-left: 0;" readonly="readonly"
									class="form-control input-sm laydate-hxdate" ignore="ignore" />
								</td>
							</tr>

							<tr>
								<td style="text-align: right;" class="col-md-1">通报AOC时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入通报AOC时间"
									name="notificationaoctime" type="text" style="height: 26px"
									readonly="readonly"
									class="form-control input-sm laydate-hxdate" ignore="ignore" />
								</td>
								<td style="text-align: right;" class="col-md-1">判定结果：</td>
								<td class="rd1 col-md-3"><t:dictSelect
										field="determineresults" typeGroupCode="dresults"
										hasLabel="false" type="select"
										extendJson="{style:'width:100%'}"></t:dictSelect> <!-- <input  placeholder="请输入判定结果" name="determineresults" type="text" style="height:26px"  class="form-control input-sm" maxlength="255"  ignore="ignore"  /> -->
								</td>
								<td style="text-align: right;" class="col-md-1">本场运行路线：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入本场运行路线"
									name="localroute" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="255" ignore="ignore" />
								</td>
							</tr>

							<tr>
								<td style="text-align: right;" class="col-md-1">检查跑道：</td>
								<td class="rd1 col-md-3"><t:dictSelect field="checkrunway"
										typeGroupCode="is_main" hasLabel="false" type="radio"
										defaultVal="N"
										extendJson="{class:'isUseFire i-checks ischeckAt'}"></t:dictSelect>
								</td>
								<td style="text-align: right;" class="col-md-1">跑道：</td>
								<td class="rd1 col-md-3">
									<c:forEach var="categoryList"
										items="${categoryList}">
										<input name="runway" class="form-control i-checks"
											datatype="*" type="radio" value="${categoryList.code}"
											ignore="checked" readonly="readonly" />${categoryList.name}
									<%--<input name="runway" type="radio"  value="${categoryList.code}"   ignore="ignore"  >${categoryList.name}</input>--%>
									</c:forEach></td>

								<td style="text-align: right;" class="col-md-1">检查方向：</td>
								<td class="rd1 col-md-3"><t:dictSelect
										field="checdirection" readonly="readonly"
										typeGroupCode="cdirection" hasLabel="false" type="radio"
										extendJson="{  class:'isUseFire i-checks'}"></t:dictSelect></td>
							</tr>

							<tr>
								<td style="text-align: right;" class="col-md-1">查道开始时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入查道起时间"
									id="trackstarttime" name="trackstarttime" type="text"
									style="height: 26px" readonly="readonly"
									class="form-control input-sm laydate-hxdate laydate-datetime"
									ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">查道结束时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入查道止时间"
									id="trackcheckstoptime" name="trackcheckstoptime" type="text"
									style="height: 26px" readonly="readonly"
									class="form-control input-sm laydate-hxdate laydate-datetime"
									ignore="ignore" /></td>
								<td style="text-align: right;" class="col-md-1">查道结果：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入查道结果"
									name="checkresults" type="text" style="height: 26px" value="正常"
									class="form-control input-sm" maxlength="100" ignore="ignore" />
								</td>
							</tr>

							<tr>
								<th style="text-align: left;">后续计划</th>
								<th></th>
							</tr>
							<tr>
								<td style="text-align: right;" class="col-md-1">后续航班计划：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入后续航班计划"
									name="followupflightschedule" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="100" ignore="ignore" />
								</td>
								<td style="text-align: right;" class="col-md-1">后续航线：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入后续航线"
									name="subsequentcourse" type="text" style="height: 26px"
									class="form-control input-sm" maxlength="100" ignore="ignore" />
								</td>

							</tr>
							<tr>
								<td style="text-align: right;" class="col-md-1">补充说明：</td>
								<td class="rd1 col-md-3"><textarea placeholder="请输入补充说明"
										name="added" cols="30" rows="5" maxlength="1000"
										style="width: 100%"></textarea></td>

							</tr>
							<tr>
								<td
									style="text-align: right; padding-left: 0px; white-space: nowrap"
									class="col-md-1">后续计划出港时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入后续计划出港时间"
									name="subsequentplanneddeparture" type="text"
									style="height: 26px;" readonly="readonly"
									class="form-control input-sm laydate-hxdate" ignore="ignore" />
								</td>
								<td
									style="text-align: right; padding-left: 0px; white-space: nowrap"
									class="col-md-1">后续实际出港时间：</td>
								<td class="rd1 col-md-3"><input placeholder="请输入后续实际出港时间"
									name="subsequentactualdeparture" type="text"
									style="height: 26px;" readonly="readonly"
									class="form-control input-sm laydate-hxdate" ignore="ignore" />
								</td>
								<td style="text-align: right; white-space: nowrap"
									class="col-md-1">延误时间(分钟)：</td>
								<td class="rd1 col-md-3"><input
									placeholder="请输入延误时间（单位为分钟）" name="delaytime" type="text"
									style="height: 26px" class="form-control input-sm"
									maxlength="100" style="width:80%" ignore="checked"
									datatype="n0-100" /></td>

							</tr>
							<tr>
								<td style="text-align: right;" class="col-md-1">图片：</td>
								<td class="rd1 col-md-3" colspan="5">
									<div class="col-sm-7">
										<jsp:include page="/webpage/common/jsp/upload.jsp">
											<jsp:param name="name" value="files" />
											<jsp:param name="ids" value="" />
											<jsp:param name="value" value="" />
											<jsp:param name="fileNames" value="" />
											<jsp:param name="sizes" value="" />
											<jsp:param name="directory" value="famsUnsafeeventTiredamage" />
											<jsp:param name="multiple" value="multiple" />
											<jsp:param name="required" value="" />
											<jsp:param name="disable" value="" />
											<jsp:param name="typeSwf" value="0" />
											<jsp:param name="acceptFileType" value="image/*" />
										</jsp:include>
									</div>
								</td>
							</tr>
						</table>
					</div>
			</form>
		</div>
	</div>
	<script src="plug-in/hplus/plugins/suggest/bootstrap-suggest.min.js"></script>
	<script type="text/javascript">
		var subDlgIndex = '';
	
	
	
		//右边表格版本对比
		function getflightno() {
			var flightno = $("#flightno").val();
			$.ajax({
				type : 'post',
				async : true,
				url : "famsUnsafeeventTiredamageController.do?getflightno",
				dataType : 'json',
				data : {
					flightno : flightno
				},
				success : function(res) {
					// alert(res.data[0].flightno);
					$("#flightno").val(res.data[0].flightno);
					$("#flightregistrationnumber").val(res.data[0].ffid);
					$("#models").val(res.data[0].crafttype);
					$("#immediately").val(res.data[0].craftno);
					$("#slots").val(res.data[0].craftsite);
					$("#airlines").val(res.data[0].twocharcode);
					$("#routes").val(res.data[0].airlinefullcn);
					$("#plannedtime").val(res.data[0].sst);
					$("#planneddeparture").val(res.data[0].est);
					$("#actualtimearrival").val(res.data[0].sat);
					$("#actualdeparture").val(res.data[0].eat);
				},
				error : function(e) {
					layer.msg("访问数据失败")
				}
			})
		}
	
		$(document).ready(function() {

			$('.ischeckAt').on('ifChecked', function(event) {
				var value = event.target.defaultValue;
				if (value == "Y") {
					$("input[name='checdirection']").removeAttr("checked");
					$("input[name='checdirection']").removeAttr("disabled");
					$("input[name='checdirection']").attr("datatype", "*");
					// $("input[name='runway']").removeAttr("checked");
					// $("input[name='runway']").removeAttr("disabled");
					// $("input[name='runway']").attr("ignore", "checked");

				} else {
					$("input[name='checdirection']").parent().removeClass("checked");
					$("input[name='checdirection']").attr("disabled", "disabled");
					$("input[name='checdirection']").prop("checked", "");
					$("input[name='checdirection']").attr("datatype", "*0-20");
					// $("input[name='runway']").each(function(i, val) {
					// 	$(val).prop("checked", "");
					// 	$(val).parent().removeClass("checked");
					// 	$(val).attr("disabled", "disabled");
					// 	$(val).attr("ignore", "ignore");
					// });
				}
			});
			//runway
			//$("[name='runway'][value='A09A01']").prop("checked", "checked");
			//$("[name='checdirection']").prop("checked", "");
			//$("[name='checdirection']").attr("readonly", "readonly");
	
			$(".laydate-datetime").each(function() {
				var _this = this;
				laydate.render({
					elem : this,
					//format: 'HH:mm',
					//type: 'time'
					format : 'yyyy-MM-dd HH:mm',
					type : 'datetime',
					ready : function(date) {
						$(_this).val(DateJsonFormat(date, this.format));
					},
					done : function(value, date, endDate) {
						if (_this.id == "plannedtime" || _this.id == "planneddeparture") {
							checkTimeSequence(_this, 'plannedtime', 'planneddeparture');
						}
						if (_this.id == "actualtimearrival" || _this.id == "actualdeparture") {
							checkTimeSequence(_this, 'actualtimearrival', 'actualdeparture');
						}
						if (_this.id == "trackstarttime" || _this.id == "trackcheckstoptime") {
							checkTimeSequence(_this, 'trackstarttime', 'trackcheckstoptime');
						}
	
					}
				});
			});
			$(".laydate-hxdate").each(function() {
				var _this = this;
				laydate.render({
					elem : this,
					//format: 'HH:mm',
					//type: 'time'
					format : 'yyyy-MM-dd HH:mm',
					type : 'datetime',
					ready : function(date) {
						$(_this).val(DateJsonFormat(date, this.format));
					}
				});
			});
			//ymdate
			$(".laydate-ymdate").each(function() {
				var _this = this;
				laydate.render({
					elem : this,
					//format: 'HH:mm',
					//type: 'time'
					format : 'HH:mm',
					type : 'time', //datetime是完整的yyymmm什么的
					ready : function(date) {
						$(_this).val(DateJsonFormat(date, this.format));
					}
				});
			});
			$(".laydate-date").each(function() {
				var _this = this;
				laydate.render({
					elem : this,
					max : crtTimeFtt2(new Date()),
					ready : function(date) {
						$(_this).val(DateJsonFormat(date, this.format));
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
				tiptype : function(msg, o, cssctl) {
					if (o.type == 3) {
						validationMessage(o.obj, msg);
					} else {
						removeMessage(o.obj);
					}
				},
				btnSubmit : "#btn_sub",
				btnReset : "#btn_reset",
				ajaxPost : true,
				beforeSubmit : function(curform) {},
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
	
		var resultTemp;
		var testBsSuggest = $("#flightno").bsSuggest({
			url : "famsCommonController/loadFlightData.do?keyword=" + $("#flightno").val(),
			idField : "rownum",
			showBtn : false,
			showHeader : false,
			getDataMethod : 'data',
			allowNoKeyword : false,
			inputWarnColor : 'rgba(255,0,0,.1)', //输入框内容不是下拉列表选择时的警告色
			effectiveFields : [ 'flightno', 'crafttype', 'airlinefullcn' ], //有效显示于列表中的字段，非有效字段都会过滤，默认全部。
			effectiveFieldsAlias : {
				'flightno' : '航班号',
				'crafttype' : '机型',
				'airlinefullcn' : '航线'
			}, //有效字段的别名对象，用于 header 的显示
			delay : 0.1,
			keyField : "flightno", //每组数据的哪个字段作为输入框内容，优先级高于 indexKey 设置（推荐）
			indexId : 0, //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
			indexKey : 1 //每组数据的第几个数据，作为input输入框的内容
	
		}).on('onDataRequestSuccess', function(e, result) {
			resultTemp = result;
		}).on('onSetSelectValue', function(e, keyword) {
			$("#flightno").val(resultTemp.value[keyword.id - 1].flightno);
			$("#flightregistrationnumber").val(resultTemp.value[keyword.id - 1].ffid);
			/* $("#airlines").val(resultTemp.value[keyword.id-1].twocharcode); */
			$("#immediately").val(resultTemp.value[keyword.id - 1].craftno);
			$("#models").val(resultTemp.value[keyword.id - 1].crafttype);
			$("#slots").val(resultTemp.value[keyword.id - 1].craftsite);
			$("#airPlanId").val(resultTemp.value[keyword.id - 1].id);
			$("#routes").val(resultTemp.value[keyword.id - 1].airlinefullcn);
	
			$("#plannedtime").val(resultTemp.value[keyword.id - 1].est.substring(0, 16));
			$("#planneddeparture").val(resultTemp.value[keyword.id - 1].sst.substring(0, 16));
			$("#actualtimearrival").val(resultTemp.value[keyword.id - 1].eat.substring(0, 16));
			$("#actualdeparture").val(resultTemp.value[keyword.id - 1].sat.substring(0, 16));
	
			$("#starstation").val(resultTemp.value[keyword.id - 1].starstation);
			$("#starstationcn").val(resultTemp.value[keyword.id - 1].starstationcn);
			$("#terminalstation").val(resultTemp.value[keyword.id - 1].terminalstation);
			$("#terminalstationcn").val(resultTemp.value[keyword.id - 1].terminalstationcn);
			$("#airlines").val(resultTemp.value[keyword.id - 1].twocharcode);
			$("#airlinescn").val(resultTemp.value[keyword.id - 1].airlinescn);
		}).on('onUnsetSelectValue', function(e) {
			//$("#flightno").val(null);
			$("#flightregistrationnumber").val(null);
			$("#airlines").val(null);
			$("#immediately").val(null);
			$("#models").val(null);
			$("#slots").val(null);
			$("#airPlanId").val(null);
			$("#routes").val(null);
			$("#plannedtime").val(null);
			$("#planneddeparture").val(null);
			$("#actualtimearrival").val(null);
			$("#actualdeparture").val(null);
			$("#starstation").val(null);
			$("#starstationcn").val(null);
			$("#terminalstation").val(null);
			$("#terminalstationcn").val(null);
			$("#airlinescn").val(null);
		});
	</script>
</body>
</html>