<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>例行检查表</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<t:base type="layer,validform,bootstrap-form"></t:base>
</head>
<style>
	.tablebox{
		width: 100%;
	}
	table{
		border-collapse: collapse;
		border: none;
	}
	th{
		border: 1px solid black;
	}
	#tab{
		width: 100%;
		margin: auto;
	}

	h1 {
		width: 100%;
		margin: auto;
		text-align: center
	}

	h4 {
		width: 100%;
		margin: auto;
	}

	h5 {
		width: 100%;
		margin: auto;
		text-align: right
	}

	.fontSize {
		font-size: 15px;
	}

	.text {
		text-align: justify
	}

	.bottom {
		width: 100%;
		height: 30px;
		margin: auto;
		display: flex;
		border: 1px solid black;
		box-sizing: border-box
	}

	.bottomtitle {
		flex: 1;
		text-align: center;
		line-height: 30px;
	}

	.bottomcontent {
		flex: 7
	}

	.other {
		border-top: none !important;
		border-bottom: none !important;
	}
	.foot{
		width: 100%;
		height: 50px;
		margin: auto;
		display: flex;
		border: 1px solid black;
		border-top: none;
		box-sizing: border-box
	}
	.footb{
		width: 100%;
		height: 50px;
		margin: auto;
		display: flex;
		border: 1px solid black;
		border-bottom: none;
		box-sizing: border-box
	}
	.foota{
		flex: 1;
		display: flex;
		line-height: 50px
	}
	.inputtext{
		height: 90%;
		border: none;
		flex: 1
	}
</style>
 <body style="overflow:hidden;overflow-y:auto;">
 <div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
	<form class="form-horizontal" role="form" id="formobj" action="famsWorkCheckController.do?doAdd" method="POST">
		<div style="width: 80%;margin: auto">
			<h4>编号：0991</h4>
			<h1>不停航施工现场例行检查登记表</h1>
			<h5>编号:</h5>
			<!-- <div class="headdiv">
                <span style="border-right:2px solid black">检查时间：</span>
                <span>建设单位：</span>
            </div>
            <div class="headdiv">
                <span style="border-right:2px solid black">施工项目：</span>
                <span>施工地点：</span>
            </div> -->
			<div class="footb">
				<div class="foota" style="border-right:1px solid black">
					<span style="margin-left:10px">检查时间：</span>
					<input type="text" class="inputtext">
				</div>
				<div class="foota">
					<span style="margin-left:10px">建设单位：</span>
					<input type="text" class="inputtext">
				</div>
			</div>
			<div class="footb">
				<div class="foota" style="border-right:1px solid black">
					<span style="margin-left:10px">施工项目：</span>
					<input type="text" class="inputtext">
				</div>
				<div class="foota">
					<span style="margin-left:10px">施工地点：</span>
					<input type="text" class="inputtext">
				</div>
			</div>
			<div class="tablebox">
				<table id="tab" cellpadding="2" cellspacing="0" >
					<tr>
						<th rowspan="4">检<br>查<br>事<br>项</th>
					</tr>
					<tr>
						<th>序号</th>
						<th class="fontSize">施工人员检查</th>
						<th>符<br>合</th>
						<th>不<br>符<br>合</th>
						<th>不<br>适<br>用</th>
					<tr>
						<th>1</th>
						<th>所有施工人员证件齐全</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th>1</th>
						<th>所有施工人员证件齐全</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th rowspan="4">专<br>项<br>检<br>查</th>
					</tr>
					<tr>
						<th>序号</th>
						<th class="fontSize">车辆管理</th>
						<th>符<br>合</th>
						<th>不<br>符<br>合</th>
						<th>不<br>适<br>用</th>
					<tr>
						<th>1</th>
						<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<tr>
						<th>1</th>
						<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</table>
			</div>
			<div class="bottom other">
				<span class="bottomtitle" style="border-right:1px solid black">检查小结</span>
				<span class="bottomcontent"></span>
			</div>
			<div class="bottom">
				<span class="bottomtitle" style="border-right:1px solid black">整改意见</span>
				<span class="bottomcontent"></span>
			</div>
			<div class="foot">
				<div class="foota" style="border-right:1px solid black">
					<span style="margin-left:10px">安全员（签字）</span>
					<input type="text" class="inputtext">
				</div>
				<div class="foota">
					<span style="margin-left:10px">飞行区管理部机坪监管部（签字）</span>
					<input type="text" class="inputtext">
				</div>
			</div>




		</div>
	</form>
	</div>
 </div>
<script type="text/javascript">
var subDlgIndex = '';
$(document).ready(function() {
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
		
});
</script>
</body>
</html>