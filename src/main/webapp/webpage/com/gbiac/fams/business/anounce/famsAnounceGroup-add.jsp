<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>分组维护主表</title>
<!-- <link rel="stylesheet" href="/jeecg/plug-in/themes/fineui/common/layui/css/layui.css"></link> -->
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
<link rel="stylesheet" href="webpage/common/css/basic.css">
	<style>
		.color-s{color: #71b83d;}
		.color-e{color: red;}
	</style>
</head>
<body style="overflow: hidden; overflow-y: auto;">
	<div class="container" style="width: 100%;">
		<div class="panel-heading"></div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="formobj"
				action="famsAnounceGroupController.do?doAdd" method="POST">
				<input type="hidden" id="btn_sub" class="btn_sub" /> <input
					type="hidden" id="id" name="id" />
				<div class="form-group">
					<label for="name" class="col-sm-3 control-label"><span class="color-red">*</span>分组名称：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<input id="name" name="name" type="text" maxlength="32" tiptype="1" datatype="s2-32" errormsg="名称必须是2-32的合法字符"
								class="form-control input-sm" placeholder="请输入分组名称"
								ignore="checked" />
						</div>
					</div>
					<span class="tip line-height30" style="display: none"></span>
				</div>
				<div class="form-group">
					<label for="user" class="col-sm-3 control-label"><span class="color-red">*</span>分组成员：</label>
					<div class="col-sm-7">
						<div class="input-group" style="width: 100%">
							<input id="names" name="names" type="text" maxlength="32"
								   class="form-control input-sm"
								placeholder="请选择成员" datatype="*" ignore="checked"
								readonly="readonly" value="${names}"/>
							<input id="userIds" name="userIds"
								type="hidden" value="${userIds}"/>
							<a href="#"
								class="easyui-linkbutton" plain="true" icon="icon-search"
								id="departSearch" onclick="openUesrSelect()"
								style="margin-right: 5px">选择</a>
							<a href="#"
								class="easyui-linkbutton" plain="true" icon="icon-redo"
								id="departRedo" onclick="clears()">清空</a>
							<div
								style="font-size: 14px; display: inline-block; margin-left: 30px;">
								已选择 <label id="numbers">0</label>人
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- <script src="/jeecg/plug-in/themes/fineui/common/layui/layui.js"></script> -->
	<script type="text/javascript">
        //人员点击事件
		function openUesrSelect() {
			var width = $(window).width() * 0.6;
			var height = $(window).height() * 0.7;
            $.dialog.setting.zIndex = getzIndex();
            var valueId = $('#userIds').val();
            var realNames = $('#names').val();

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
		function callbackDepartmentSelect() {
            var iframe = this.iframe.contentWindow;
            //拿到子弹窗的数组
            var idsArr = iframe.idArr;
            var realNamesArr = iframe.realNameArr;

            //用于提交表单
			$('#userIds').val(idsArr.join(','));
			//显示在输入框上
			$('#names').val(realNamesArr.join(',')).blur();
			$('#numbers').text(idsArr.length);
		}
		function clears() {
			$('#names').val('');
			$('#numbers').text(0);
            $('#userIds').val(''); //清空已选择的id
        }
        //校验是否含有特殊符号和字符长度
        function check_val(str){
            var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\]%<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]");
            if (pattern.test(str) || str.length<=1 || str.length>32){
                return false;
            }else {
                return true;
            }
        }
		var subDlgIndex = '';
		$(document).ready(function() {
            //分组名称判断是否重复
            var submitFlag = false;
            $('#name').on('change',function () {
                var value = $(this).val();
                if(value !== '' && check_val(value)){
                    $.ajax({
                        type: "get",
                        dataType: "json",
                        url: "famsAnounceGroupController.do?isRepeatName",
                        data:{
                            name:value
                        },
                        success:function (data) {
                            submitFlag = false;
                            if(data.ok){
                                $('.tip').removeClass('color-e').addClass('color-s')
									.show().text(data.message);
                                submitFlag = true;
                            }else {
                                $('.tip').removeClass('color-s').addClass('color-e')
									.show().text(data.message);
                                return;
                            }
                        }
                    });
                }else {
                    $('.tip').removeClass('color-s color-e').hide().text('')
				}
            })

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
                    //分组名称判断是否重复
                    if(!submitFlag){
                        layer.msg('该分组名称重复');
                        return false;
					}
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