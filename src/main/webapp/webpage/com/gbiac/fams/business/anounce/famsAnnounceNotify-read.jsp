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
	<title>通知通告查看</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<t:base type="jquery,easyui,bootstrap,bootstrap-table,layer,validform,DatePicker,bootstrap-form"></t:base>
	<link rel="stylesheet" href="webpage/common/css/basic.css">
	<link rel="stylesheet" href="plug-in/hoverIntent/css/hoverIntent.css">
	<style>
		.leverUl>li{border-bottom: 1px #e1e2e2 dashed;padding: 6px 0;}
		.panel-header,.panel-body{border: none;}
		.panel{box-shadow: none;-webkit-box-shadow: none}
		.panel-body{
            overflow: visible;
        }
		.input-sm{height: 30px!important;}
        .headImg{width: 16px;vertical-align: sub;}
		/*修改easyui*/
		.tabs{width: auto;height: 30px;}
        .readContent .th{
            width: 100px;
            text-align: right;
        }
        .readContent td{
            padding: 10px 5px 10px 30px;
            width: 100%;
        }
	</style>
</head>
<body style="overflow: hidden; overflow-y: auto;">
<div class="container" style="width: 100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
        <div style="width: 600px;margin: 30px auto 0;">
            <table class="readContent">
                <tbody>
                <tr>
                    <th><div class="th">标题：</div></th>
                    <td><div class="">${famsAnnounceNotify.title}</div></td>
                </tr>
                <tr>
                    <th class="vertical-top pdt10"><div class="th">内容：</div></th>
                    <td>
                        <div class="">${famsAnnounceNotify.content}</div>
                    </td>
                </tr>
                <tr>
                    <th><div class="th">类型：</div></th>
                    <td>${famsAnnounceNotify.type}</td>
                </tr>
                <tr>
                    <th><div class="th">发送人：</div></th>
                    <td>${famsAnnounceNotify.sender}</td>
                </tr>
                <tr>
                    <th><div class="th">发送时间：</div></th>
                    <td><fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='both' value='${famsAnnounceNotify.sendTime}'/></td>
                </tr>
                <tr>
                    <th><div class="th">发送模式：</div></th>
                    <td>${famsAnnounceNotify.sendPattern}</td>
                </tr>
                <tr>
                    <th><div class="th">接收人：</div></th>
                    <td>
                        <div id="receiveUser" class="float-left mgr20">${realName}</div>
                         <c:if test="${famsAnnounceNotify.state != 2 }">
                        <div class="features inline-block">
                            <div id="numbers">
                                (${readSum}/${receiveSum}已读)
                                <a helf="javascript:;" id="userDetail" class="customBtn green">查看详情</a>
                            </div>
                            <div id="featuresPop" class="features-pop up overflow-visible" style="bottom: 29px;height: 180px;">
                                <i class="allow-down-icon"></i>
                                <div id="tabCard" class="easyui-tabs overflow-y-auto" style="width:300px;">
                                    <div title="已读" style="padding:20px;">
                                        <div style="overflow-y: auto;height: 100px">
                                            <c:forEach items = "${receive}" var="rec">
                                                <c:if test="${rec.state == 1}">
                                                    <div class="clearfix">
                                                        <div class="float-left" style="margin-top: 7px;">
                                                            <img class="headImg"
                                                                 src = "${rec.headUrl ? rec.headUrl:'plug-in/ace/avatars/avatar2.png'}">
                                                            <span>${rec.realName }</span><!-- 姓名 -->
                                                        </div>
                                                        <div class="float-right">
                                                            <span>阅读时间</span>
                                                            <address>${fn:substring(rec.viewTime, 0, 16)}</address>
                                                        </div>

                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div title="未读" style="padding:20px;">
                                        <div style="overflow-y: auto;height: 100px">
                                            <c:forEach items = "${receive}" var="rec">
                                                <c:if test="${rec.state == 2}">
                                                    <div>
                                                        <img class="headImg"
                                                             src = "${rec.headUrl ? rec.headUrl:'plug-in/ace/avatars/avatar2.png'}">
                                                        <span>${rec.realName }</span><!-- 姓名 -->
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th><div class="th">附件：</div></th>
                    <td>
                        <div class="">
                            <jsp:include page="/webpage/common/jsp/upload.jsp">
                                <jsp:param name="name" value="files" />
                                <jsp:param name="ids" value="${files.ids}" />
                                <jsp:param name= "fileNames" value= "${files.fileNames}"/>
                                <jsp:param name="value" value="${files.paths}" />
                                <jsp:param name= "sizes" value= "${files.sizes}"/>
                                <jsp:param name="types" value="${files.types}" />
                                <jsp:param name="directory" value="notify" />
                                <jsp:param name= "disable" value= "disable"/>
                                <jsp:param name="multiple" value="multiple" />
                            </jsp:include>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <%--撤回理由--%>
     <c:if test="${not empty withdraw}"><div style="border-top:1px solid #ccc" class="mgt20">
            <div style="width: 600px;margin: 20px auto;">
                <form id="formobj" class="form-horizontal" role="form" action="famsAnnounceNotifyController.do?doDel"
                      method="POST">
                    <input type="hidden" id="btn_sub" class="btn_sub" />
                    <input type="hidden" id="businessId" name="id" value="${famsAnnounceNotify.id}" />
                    <table class="readContent">
                        <tbody>
                        <tr>
                            <th>
                                <div class="th"><span class="color-red">*</span>撤回理由:</div>
                            </th>
                            <td>
                                <div>
                                    <textarea id="reason" maxlength="100" placeholder="请填写撤回理由"
                                              name="reason" datatype="*" ignore="checked"
                                              class="receiveText" style="width:100%;border:1px solid #ccc;
                                              padding:0 5px;resize:none" rows="6"></textarea>
                                    <label class="Validform_label" style="display: none">撤回理由</label>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
	</c:if>
		<%--<form class="form-horizontal" role="form">
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
						<div class="pdt7">${famsAnnounceNotify.title}</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-3 control-label"><span class="color-red">*</span>内容：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<div class="pdt7">${famsAnnounceNotify.content}</div>
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
						<div class="pdt7">${famsAnnounceNotify.type}</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="sender" class="col-sm-3 control-label"><span class="color-red">*</span>发送人：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<div class="pdt7">${famsAnnounceNotify.sender}</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="sendTime" class="col-sm-3 control-label"><span class="color-red">*</span>签发时间：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<div class="pdt7">
							<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='both' value='${famsAnnounceNotify.sendTime}'/>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="sendPattern" class="col-sm-3 control-label"><span class="color-red">*</span>发送模式：</label>
				<div class="col-sm-7">
					<div class="input-group" style="width: 100%">
						<div class="pdt7">${famsAnnounceNotify.sendPattern}</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="groupId" class="col-sm-3 control-label"><span class="color-red">*</span>接收人：</label>
				<div class="col-sm-7">
					<div class="input-group p-relative" style="width: 100%"  >
						<input id="updateGroupId" type="hidden" value="${famsAnnounceNotify.groupId}">
						<input id="updateRealName" type="hidden" value="${realName}">
						<div id="">
							<input type="hidden" id="userId" name="userId" value="${famsAnnounceNotify.groupId}">

							<div class="pdt7">${famsAnnounceNotify.groupId}</div>
						</div>
					</div>
					<div class="features inline-block">
						<div id="numbers">
							(${readSum}/${receiveSum}已读)
							<a helf="javascript:;" id="userDetail" class="customBtn green">查看详情</a>
						</div>
						<div id="featuresPop" class="features-pop up" style="bottom: 29px;height: 180px;">
							<i class="allow-down-icon"></i>
							<div id="tabCard" class="easyui-tabs overflow-y-auto" style="width:300px;">
								<div title="已读" style="padding:20px;">
									<div style="overflow-y: auto;height: 100px">
										<c:forEach items = "${receive}" var="rec">
											<c:if test="${rec.state == 1}">
												<div class="clearfix">
													<div class="float-left" style="margin-top: 7px;">
														<img class="headImg"
															 src = "${rec.headUrl ? rec.headUrl:'plug-in/ace/avatars/avatar2.png'}">
														<span>${rec.realName }</span><!-- 姓名 -->
													</div>
													<div class="float-right">
														<span>阅读时间</span>
														<address>${fn:substring(rec.viewTime, 0, 16)}</address>
													</div>

												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
								<div title="未读" style="padding:20px;">
									<div style="overflow-y: auto;height: 100px">
										<c:forEach items = "${receive}" var="rec">
											<c:if test="${rec.state == 2}">
												<div>
													<img class="headImg"
														 src = "${rec.headUrl ? rec.headUrl:'plug-in/ace/avatars/avatar2.png'}">
													<span>${rec.realName }</span><!-- 姓名 -->
												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">附件：</label>
				<div class="col-sm-7">
					<jsp:include page="/webpage/common/jsp/upload.jsp">
						<jsp:param name="name" value="files" />
						<jsp:param name="ids" value="${files.ids}" />
						<jsp:param name="value" value="${files.paths}" />
						<jsp:param name="directory" value="announce" />
						<jsp:param name="multiple" value="multiple" />
					</jsp:include>
				</div>
			</div>
		</form>--%>
		<!--评论-->
		<%--<div class="rightContent col-sm-8">
			<div class="replyHead clearfix">
				<div class="float-left font14 pdl10" style="margin-top: 6px;">回复 <span id="relyCount">${relyCount}</span></div>
				<c:if test="${famsAnnounceNotify.state != 2}">
					<a helf="javascript:;" id="addreplyBtn" class="customBtn green margin5 float-right"
					   onclick="addreplyFun(this)" style="padding: 0 5px;">添加回复</a>
				</c:if>
			</div>
			<div id="addReplyText" style="display: none;">
                        <textarea id="addReplyContent" class="receiveText width100"
								  rows="2" maxlength="200"></textarea>
			</div>
			<div id="itemList" class="text-left">
				<ul class="leverUl">
					<c:forEach items="${relies}" var="item" varStatus="status">
						<li class="line-height30">
							<div class="clearfix receiveBox pdr10">
								<input type="hidden" class="btn_sub toUerId"
									   value="${item.id}" />
								<div class="receiveName float-left">
									<span class="color-green">${item.recordName}</span>-<span class="font-bold">${item.recordDepartName}:</span>
									<span class="receiveMsg ">${item.recordContent}</span>
								</div>

								<span class="receiveTime float-right color-999">${item.recordTime}</span>
								<c:if test="${famsAnnounceNotify.state != 2 && item.recordName != currentUser.realName}">
									<div class="receiveBtn float-right" onclick="clickReceive(this)">回复</div>
									<div class="receiveContent hide">
											<textarea maxlength="200" class="receiveText width100"
													  rows="1"></textarea>
									</div>
								</c:if>
							</div>
							<div class="leverItem">
								<ul class="leverItemUl">
									<c:forEach items="${item.recordList}" var="it">
										<li>
											<div class="clearfix replyBox receiveBox">
												<input type="hidden"  class="btn_sub toUerId"
													   value="${it.id}" />
												<div class="receiveName float-left">
													<span class="color-green">${it.name}</span>-
													<span class="font-bold">${it.department}</span>
													回复
													<span class="color-green">${it.replyName}</span>-
													<span class="font-bold">${it.replyDepartment}:</span>
													<span class="receiveMsg ">${it.content}</span>
												</div>

												<span class="receiveTime float-right color-999">${fn:substring(it.date, 0, 16)}</span>

												<c:if test="${it.name != currentUser.realName }">
													<div class="receiveBtn float-right" onclick="clickReceive(this)">回复</div>
													<div class="receiveContent hide">
											<textarea maxlength="200" class="receiveText width100"
													  rows="1"></textarea>
													</div>
												</c:if>
											</div>
											<ul class="leverItemUl"></ul>
										</li>
									</c:forEach>
								</ul>
								<a href="javascript:;" class="moreBtn" style="display: none"
								   onclick="moreList(this)">查看更多回复></a>
							</div>
						</li>
					</c:forEach>
				</ul>
				<a href="javascript:;" class="moreBtn" style="display: none"
				   onclick="moreList(this)">查看更多评论></a>
			</div>
		</div>--%>


	</div>
</div>
<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceNotify.js"></script>
<script src="plug-in/hoverIntent/jquery.hoverIntent.min.js"></script>
<script type="text/javascript">

    var subDlgIndex = '';
    $(document).ready(
        function() {

            //初始化去掉disabled
            $('.receiveText').attr('disabled', false);
            //处理接收人文字过长
            var receiveUser = $('#receiveUser').text();
            if(receiveUser.length>30){
                $('#receiveUser').html(renderSubstring(receiveUser,30));
            }

			//tab切换
            $('#tabCard').tabs({
                border:false,
                onSelect:function(title){

                }
            });
            //已读和未读人详情
			$('#userDetail').on('click',function (e) {
                e.stopPropagation(); //阻止冒泡
                $('#featuresPop').fadeToggle('fast')
            });
			$('#featuresPop').on('click',function (e) {
                e.stopPropagation(); //阻止冒泡
            });
            $('body').click(function () {
                $('#featuresPop').hide(); //点击空白处收起
            })

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
        });


</script>
</body>
</html>