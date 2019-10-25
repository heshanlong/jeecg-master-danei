<%@ page language="java" import="java.util.*"
		 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- 添加jstl标准库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--JSP使用函数声明 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>通知通告表</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<t:base type="jquery,easyui,bootstrap,bootstrap-table,layer,validform,DatePicker,bootstrap-form"></t:base>
	<link rel="stylesheet" href="webpage/common/css/basic.css">
	<style>
		.leverItem{background: #efefef;padding-left: 10px;padding-right: 10px;}
		.panel-header,.panel-body{border: none;}
		.panel{box-shadow: none;-webkit-box-shadow: none}
		.panel-body{overflow: hidden!important;padding-right: 0!important;}
		.input-sm{height: 30px!important;}
		.peopleNumber{position: absolute;top: 6px;right: -80px;}
	</style>
</head>
<body style="overflow: hidden; overflow-y: auto;">
<div class="container" style="width: 100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
		<form class="form-horizontal" role="form" id="formobj"
			  action="famsAnnounceNotifyController.do?doUpdate" method="POST">
			<input type="hidden" id="btn_sub" class="btn_sub" /> <input
				type="hidden" id="businessId" name="id" value="${famsAnnounceNotify.id}" />
			<div class="form-group hide">
				<label for="number" class="col-sm-3 control-label"><span class="color-red">*</span>编号：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<input id="number" name="number" readonly="readonly"
							   value='${famsAnnounceNotify.number}' type="text" maxlength="50"
							   class="form-control input-sm" placeholder="请输入编号" datatype="*"
							   ignore="checked" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="title" class="col-sm-3 control-label"><span class="color-red">*</span>标题：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<input id="title" name="title"
							   value='${famsAnnounceNotify.title}' type="text" maxlength="100"
							   tiptype="6" datatype="*1-100" errormsg="标题必须是1-100的合法字符"
							   class="form-control input-sm" placeholder="请输入标题"
							   ignore="checked" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="content" class="col-sm-3 control-label"><span class="color-red">*</span>内容：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
							<textarea name="content" class="form-control" rows="6"
									  maxlength="1000" tiptype="6" datatype="*1-1000"
									  errormsg="内容必须是1-1000的字符" ignore="checked">${famsAnnounceNotify.content}</textarea>
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
									  typeGroupCode="anouType" hasLabel="false" title="类型"
									  defaultVal="${famsAnnounceNotify.type}"></t:dictSelect>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="sender" class="col-sm-3 control-label"><span class="color-red">*</span>签发人：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<input id="sender" name="sender" readonly="readonly"
							   value='${famsAnnounceNotify.sender}' type="text" maxlength="100"
							   tiptype="6" datatype="*1-100" errormsg="签发人必须是1-100的合法字符"
							   class="form-control input-sm" placeholder="请输入签发人"
							   ignore="checked" />
					</div>
				</div>
			</div>

			<div class="form-group hide">
				<label for="sendTime" class="col-sm-3 control-label"><span class="color-red">*</span>发送时间：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<input id="sendTime" name="sendTime" type="text" readonly="readonly"
							   class="form-control input-sm laydate-datetime"
							   placeholder="请输入发送时间" datatype="*" ignore="checked"
						<%--onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"--%>
							   onblur="checkTimeSequence(this)"
							   value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='both' value='${famsAnnounceNotify.sendTime}'/>" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="sendPattern" class="col-sm-3 control-label"><span class="color-red">*</span>发送模式：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<t:dictSelect field="sendPattern" type="list" id="send_pattern"
									  extendJson="{class:'form-control input-sm'}" datatype="*"
									  typeGroupCode="sePattern" hasLabel="false" title="发送模式"
									  defaultVal="${famsAnnounceNotify.sendPattern}"
									  readonly="readonly"></t:dictSelect>
					</div>
				</div>
			</div>


			<div class="form-group">
				<label for="groupId" class="col-sm-3 control-label"><span class="color-red">*</span>接收人：</label>
				<div class="col-sm-7">
					<div class="input-group p-relative" style="width: 100%"  >
						<input id="updateGroupId" type="hidden" value="${famsAnnounceNotify.groupId}">
						<input id="updateRealName" type="hidden" value="${realName}">
						<div id="receiverSelect" <c:if test="${not empty realName}">class="hide" </c:if>>
							<t:dictSelect  field="groupId" type="list" id="receiver"
										   extendJson="{class:'form-control input-sm'}"
										   typeGroupCode="sePattern" hasLabel="false" title="分组维护主表id"
										   defaultVal="${famsAnnounceNotify.groupId}" readonly="readonly">

							</t:dictSelect>
						</div>
						<div id="dropBox" class="input-group position-relative <c:if test="${empty realName}">hide</c:if>"
							 style="width: 100%; ">
							<input type="hidden" id="userId" name="userId" value="${userIds}">
							<input id="names" type="text" readonly
								   class="form-control input-sm" placeholder="请选择" value="${realName }" />
							<%--暂不需要--%>
							<%--<c:if test="${load != 1}">
								<div>
									<a href="javascript:;" id="chooseUser" onclick="openUesrSelect(this)">选择</a>
									<a href="javascript:;" onclick="clears(this)">清空</a>
								</div>
							</c:if>--%>
						</div>
						<%--全部人数--%>
						<input type="hidden" id="allNumber" value="${allNumbers}">
						<%--飞管内部人数--%>
						<input type="hidden" id="internalNumber" value="${internalNumbers}">
						<div class="peopleNumber">总人数 <span id="peopleNumber" count="${receiveNumbers}">${receiveNumbers}</span> 人</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">附件：</label>
				<div class="col-sm-7">
					<jsp:include page="/webpage/common/jsp/upload.jsp">
						<jsp:param name="name" value="files" />
						<jsp:param name="ids" value="${files.ids}" />
						<jsp:param name= "fileNames" value= "${files.fileNames}"/>
						<jsp:param name="value" value="${files.paths}" />
						<jsp:param name= "sizes" value= "${files.sizes}"/>
						<jsp:param name="types" value="${files.types}" />
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
    $(document).ready(
        function() {
            /*发送时间暂时不能选择
            //laydate时间初始化
            $(".laydate-datetime").each(function(){
                var _this = this;
                laydate.render({
                    elem: this,
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
                    elem: this
                });
            });
            */
            //初始化下拉框不可编辑
            $('#send_pattern').attr('disabled', true);
            $('#receiver').attr('disabled', true);
            <%--var getGroupId = '${famsAnnounceNotify.groupId}';--%>
            //单选框/多选框初始化
            $('.i-checks').iCheck({
                labelHover : false,
                cursor : true,
                checkboxClass : 'icheckbox_square-green',
                radioClass : 'iradio_square-green',
                increaseArea : '20%'
            });

            //表单提交
            $("#formobj").Validform(
                {
                    tiptype : function(msg, o, cssctl) {
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
                        /*if($('#dropBox').is(':visible')){
                            if($('#names').val()===''){
                                layer.msg('接收人不能为空!');
                                return false;
							}
						}*/
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
            updateUserAjax();
        });
    //打开人员选择弹窗
    /*function openUesrSelect() {
        var width = $(window).width() * 0.5;
        var height = $(window).height() * 0.7;
        $.dialog.setting.zIndex = getzIndex();
        var valueId = $('#userId').val();
        // console.log('valueId',valueId);
        //选择人员弹窗
        $.dialog({
            content : 'url:famsAnounceGroupController.do?goAddUsers&userIds='+valueId,
            zIndex : getzIndex(),
            title : '选择人员',
            lock : true,
            width : width,
            height : height,
            button : [ {
                name : '<%--<t:mutiLang langKey="common.confirm"/>--%>',
                callback : callbackDepartmentSelect,
                focus : true
            }, {
                name : '<%--<t:mutiLang langKey="common.cancel"/>--%>',
                callback : function() {
                }
            } ],
            opacity : 0.4
        }).zindex()

    }*/
    //弹窗回调
    /*function callbackDepartmentSelect() {
        var iframe = this.iframe.contentWindow;
        var ids = [];
        var names = [];
        var id = 'id';
        var name = 'realName';
        var rows = iframe.$('#noCurDepartUserList').datagrid(
            'getSelections');
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i][id]);
            names.push(rows[i][name]);
        }
        $('#userId').val(ids.join(','));
        $('#names').val(names.join(','));
        $('#numbers').text(ids.length);
        //总人数
        $('#peopleNumber').text(ids.length).attr('count',ids.length);
    }*/
    //清空按钮
    /*function clears() {
        $('#names').val('');
        $('#numbers').text(0);
        $('#userId').val(''); //清空已选择的id
        //总人数
        $('#peopleNumber').text('0')
    }*/


</script>
</body>
</html>