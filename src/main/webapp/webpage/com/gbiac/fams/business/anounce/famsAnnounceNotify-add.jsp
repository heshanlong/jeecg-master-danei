<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>通知通告表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base
	type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,tools,DatePicker,common"></t:base>
	<link rel="stylesheet" href="webpage/common/css/basic.css">
	<style>
		select.input-sm {
			line-height: 21px;
		}
		.panel-header,.panel-body{border: none;}
		.panel-body{overflow: hidden!important;}
		.input-sm{height: 30px!important;}
        .peopleNumber{position: absolute;top: 6px;right: -80px;}
	</style>
</head>
<body style="overflow: hidden; overflow-y: auto;">
	<div class="container" style="width: 100%;">
		<div class="panel-heading"></div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="formobj"
				action="famsAnnounceNotifyController.do?doAdd" method="POST">
				<input type="hidden" id="btn_sub" class="btn_sub" /> <input
					type="hidden" id="id" name="id" />
					<%--<div class="form-group">
						<label for="title" class="col-sm-3 control-label">编号：</label>
						<div class="col-sm-7" style="display:flex">
						<div class="input-group"
							style="margin-right:10px;display:block;flex-grow: 1;">
							<input id="no1" type="text" onChange="fun()" 
								class="form-control input-sm"  ignore="checked"
								maxlength="6" tiptype="6" datatype="s1-6" errormsg="编号必须是1-6的字符" />
						</div>
						<div class="input-group"
							style="margin-right:10px;display:block;flex-grow: 1;">
							<input id="no2" type="text" class="form-control input-sm"
								maxlength="32" ignore="checked" datatype="s1-32"
								disabled="disabled" />
						</div>
						<div class="input-group"
							style="display:block;flex-grow: 1;">
							<input id="no3" type="text" onChange="fun()"
								class="form-control input-sm"  ignore="checked"
								maxlength="6" tiptype="6" datatype="s1-6" errormsg="编号必须是1-6的字符" /> 
								<input id="number" name="number" type="hidden"
								onChange="fun()" class="form-control input-sm" maxlength="32"
								ignore="checked" datatype="s1-32" />
						</div>
						</div>
					</div>--%>
				

				<div class="form-group">
					<label for="title" class="col-sm-3 control-label"><span class="color-red">*</span>标题：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<input id="title" name="title" type="text" maxlength="100" tiptype="6" datatype="*1-100" errormsg="标题必须是1-100的合法字符"
								class="form-control input-sm" placeholder="请输入标题"
								ignore="checked" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="content" class="col-sm-3 control-label"><span class="color-red">*</span>内容：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<textarea name="content" class="form-control" rows="6"  tiptype="6" datatype="*1-1000" errormsg="内容必须是1-1000的字符"
								datatype="*" ignore="checked" maxlength="1000"></textarea>
							<span class="Validform_checktip"
								style="float: left; height: 0px;"></span> <label
								class="Validform_label" style="display: none">内容</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="type" class="col-sm-3 control-label"><span class="color-red">*</span>类型：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<t:dictSelect field="type" type="list"
								extendJson="{class:'form-control input-sm'}" datatype="*"
								typeGroupCode="anouType" hasLabel="false" title="类型"></t:dictSelect>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="sender" class="col-sm-3 control-label"><span class="color-red">*</span>发送人：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<input id="sender" name="sender" type="text" value="${curUserName}" maxlength="100" tiptype="6" datatype="*1-100" errormsg="签发人必须是1-100的合法字符"
								class="form-control input-sm" placeholder="请输入发送人"
								ignore="checked" readonly="readonly"/>
						</div>
					</div>
				</div>
				<div class="form-group hide">
					<label for="sendTime" class="col-sm-3 control-label"><span class="color-red">*</span>发送时间：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<input name="sendTime" type="text" readonly="readonly"
								class="form-control laydate-datetime input-sm"
								<%--onblur="checkTimeSequence(this)"--%>
								<%--onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								onchange="checkTimeSequence(this,'workStartTime','workEndTime')"--%>
								ignore="checked" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="sendPattern" class="col-sm-3 control-label"><span class="color-red">*</span>发送模式：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<t:dictSelect field="sendPattern" type="list" id="send_pattern"
								extendJson="{class:'form-control input-sm'}" datatype="*"
								typeGroupCode="sePattern" hasLabel="false" title="发送模式"></t:dictSelect>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="groupId" class="col-sm-3 control-label"><span class="color-red">*</span>接收人：</label>
					<div class="col-sm-7">
						<div class="input-group p-relative" style="width: 100%">
							<t:dictSelect field="groupId" type="list" id = "receiver"
										  extendJson="{class:'form-control input-sm'}" datatype="*"
										  typeGroupCode="sePattern" hasLabel="false" title="接收人"></t:dictSelect>
							<div id="dropBox" class="input-group position-relative" style="width: 100%;display: none">
								<input type="hidden" id="userId" name="userId">
								<input id="names" type="text" readonly
									   class="form-control input-sm" placeholder="请选择"/>
								<div>
									<a href="javascript:;" id="chooseUser" onclick="openUesrSelect(this)">选择</a>
									<a href="javascript:;" onclick="clears(this)">清空</a>
								</div>
							</div>
							<%--全部人数--%>
							<input type="hidden" id="allNumber" value="${allNumbers}">
							<%--飞管内部人数--%>
							<input type="hidden" id="internalNumber" value="${internalNumbers}">
							<%--默认显示飞管内部人数--%>
                            <div class="peopleNumber">总人数 <span id="peopleNumber">${internalNumbers}</span> 人</div>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">附件：</label>
					<div class="col-sm-7">
						<jsp:include page="/webpage/common/jsp/upload.jsp">
							<jsp:param name="name" value="files" />
							<jsp:param name="ids" value="" />
							<jsp:param name="value" value="" />
							<jsp:param name= "sizes" value= ""/>
							<jsp:param name="types" value="" />
							<jsp:param name="directory" value="notify" />
							<jsp:param name="multiple" value="multiple" />
							<jsp:param name= "acceptFileType" value= "image/*,.jpg,.png,.pdf,.xls,.doc,.docx,xlsx,.ppt,.pptx"/>
						</jsp:include>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceNotify.js"></script>
	<script type="text/javascript">
		var subDlgIndex = '';
		function fun() {
			$("#number").val($("#no1").val() + $("#no2").val() + $("#no3").val());
		}
		$(document).ready(function() {
		    /*发送时间暂时不能选择
		    //laydate时间初始化
            $(".laydate-datetime").each(function(){
                var _this = this;
                laydate.render({
                    elem: this,
                    format: 'yyyy-MM-dd HH:mm',
                    type: 'datetime',
                    /!*ready: function(date){ //第一次点击默认的日期
                        $(_this).val(DateJsonFormat(date,this.format));
                    }*!/
                });
            });
            $(".laydate-date").each(function(){
                var _this = this;
                laydate.render({
                    elem: this
                });
            });
            */
			//formatDateTime
			$("#no2").val(formatDateTime(new Date()));
			//签发时间初始化时取当前时间
            var timestamp =Date.parse(new Date()); //获取当前时间戳
			$('input[name="sendTime"]').val(formatDateTime(timestamp,'yyyy-MM-dd HH:mm'));
            //单选框/多选框初始化
			$('.i-checks').iCheck({
				labelHover : false,
				cursor : true,
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
				increaseArea : '20%'
			});
			//下拉框默认选中的选项
			$('select[name="type"] option[value="voyage"]').attr('selected',true);
			$('select[name="sendPattern"] option[value="internal"]').attr('selected',true);
			$('#receiver').attr('disabled',true).find('option[value="internal"]').attr('selected',true);

			//表单提交
			$("#formobj").Validform({
				tiptype : function(msg, o,
						cssctl) {
					if (o.type == 3) {
						validationMessage(
								o.obj, msg);
					} else {
						removeMessage(o.obj);
					}
				},
				btnSubmit : "#btn_sub",
				btnReset : "#btn_reset",
				ajaxPost : true,
				beforeSubmit : function(curform) {
                    if($('#dropBox').is(':visible')){
                        if($('#names').val()===''){
                            layer.msg('接收人不能为空!');
                            return false;
                        }
                    }
                    //解决带有disabled的select提交值为空的问题
                    $("select[disabled=disabled]").each(function() {
                        if (parseInt($(this).val()) != -1) {
                            $(this).attr("disabled", false);
                        }
                    });
				},
				usePlugin : {
					passwordstrength : {
						minLen : 6,
						maxLen : 18,
						trigger : function(obj,
								error) {
							if (error) {
								obj
										.parent()
										.next()
										.find(
												".Validform_checktip")
										.show();
								obj
										.find(
												".passwordStrength")
										.hide();
							} else {
								$(
										".passwordStrength")
										.show();
								obj
										.parent()
										.next()
										.find(
												".Validform_checktip")
										.hide();
							}
						}
					}
				},
				callback : function(data) {
					console.log('callback',data);
					var win = frameElement.api.opener;
					if (data.success == true) {
						frameElement.api
								.close();
						win.reloadTable();
						win.tip(data.msg);
					} else {
						if (data.responseText == ''
								|| data.responseText == undefined) {
							$.messager.alert(
									'错误',
									data.msg);
							$.Hidemsg();
						} else {
							try {
								var emsg = data.responseText
										.substring(
												data.responseText
														.indexOf('错误描述'),
												data.responseText
														.indexOf('错误信息'));
								$.messager
										.alert(
												'错误',
												emsg);
								$.Hidemsg();
							} catch (ex) {
								$.messager
										.alert(
												'错误',
												data.responseText
														+ "");
								$.Hidemsg();
							}
						}
						return false;
					}
				}
			});
			//接收人
			addUserAjax();

		});
		//打开人员选择弹窗
        function openUesrSelect() {
            var width = $(window).width() * 0.6;
            var height = $(window).height() * 0.7;
            $.dialog.setting.zIndex = getzIndex();
            var valueId = $('#userId').val();
            var realNames = $('#names').val();
            // console.log('valueId',valueId);
            //选择人员弹窗
            $.dialog({
                content : 'url:famsAnounceGroupController.do?goAddUsers&userIds='+valueId+'&realNames='+realNames,
                zIndex : getzIndex(),
                title : '选择人员',
                lock : true,
                width : width,
                height : height,
                button : [ {
                    name : '<t:mutiLang langKey="common.confirm"/>',
                    callback : callbackDepartmentSelect,
                    focus : true
                }, {
                    name : '<t:mutiLang langKey="common.cancel"/>',
                    callback : function() {
                    }
                } ],
                opacity : 0.4
            }).zindex()

        }
		//弹窗回调
        function callbackDepartmentSelect() {
            var iframe = this.iframe.contentWindow;
            //拿到子弹窗的数组
            var idsArr = iframe.idArr;
            var realNamesArr = iframe.realNameArr;

            //用于提交表单
            $('#userId').val(idsArr.join(','));
            //显示在输入框上
            $('#names').val(realNamesArr.join(','));
            //总人数
            $('#peopleNumber').text(idsArr.length).attr('count',idsArr.length);
        }
        //清空按钮
        function clears() {
            $('#names').val('');
            $('#userId').val(''); //清空已选择的id
            //总人数
            $('#peopleNumber').text('0')
        }
	</script>
</body>
</html>