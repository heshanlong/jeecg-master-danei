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
	<t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
</head>
<style>
	.tablebox{
		width: 100%;
		border: 1px solid black;
		display: flex
	}
	.boxleft{
		width: 50px;
		height: 100%;
		font-size: 30px;
		text-align: center;
	}
	.boxright{
		flex: 1;
		box-sizing: border-box
	}
	.ji{
		display: table
	}
	.zi{
		display: table-cell;
		vertical-align: middle
	}
	table{
		width: 100%;
		height: 100%;
		border-top: none;
		border-right: none;
		border-bottom: none
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
		margin: auto;
		display: flex;
		border: 1px solid black;
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
		border-top: none
	}
	.footb{
		width: 100%;
		/*height: 50px;*/
		margin: auto;
		display: flex;
		border: 1px solid black;
		border-bottom: none
	}
	.foota{
		flex: 1;
		display: flex;
		/*line-height: 50px*/
		padding-top: 10px;
		padding-bottom: 10px;
	}
	.inputtext{
		height: 90%;
		border: none;
		flex: 1
	}
	.borderleft{
		border-left: none
	}
	.borderright{
		border-right: none
	}
	th{
		text-align: center;
	}
</style>
<body style="overflow:hidden;overflow-y:auto;">
<div class="container" style="width:100%;">
	<div class="panel-heading"></div>
	<div class="panel-body">
		<form class="form-horizontal" role="form" id="formobj" action="famsWorkCheckController.do?doAdd" method="POST">
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" id="id" name="id"/>
			<div style="width: 80%;margin: auto">

				<h4>编号：0991</h4>
				<h1>不停航施工现场例行检查登记表</h1>
				<h5>编号:</h5>
				<div class="footb">
					<div class="foota" style="border-right:1px solid black">
						<div class="form-group" style="margin:0px;width: 100%">
							<label for="checkTime" class="col-sm-4 control-label">检查时间：</label>
							<div class="col-sm-8">
								<div class="input-group" style="width:100%">
									<input id="checkTime" placeholder="请输入检查时间" name="checkTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate" onClick="WdatePicker_bak({dateFmt:'yyyy-MM-dd HH:mm'})" datatype="*" ignore="checked"  />
									<span class="Validform_checktip"></span>
									<label class="Validform_label" style="display: none;">检查时间</label>
								</div>
							</div>
						</div>
					</div>
					<div class="foota">
						<div class="form-group" style="margin:0px;width: 100%">
							<label for="buildDepart" class="col-sm-4 control-label">建设单位：</label>
							<div class="col-sm-8">
								<div class="input-group" style="width:100%">
									<t:dictSelect field="buildDepart" type="list" extendJson="{class:'form-control input-sm'}"
												  dictTable="fams_work_type" dictField="id" dictText="type_name" hasLabel="false"
												  title="建设单位" datatype="*"></t:dictSelect>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="footb">
					<div class="foota" style="border-right:1px solid black">
						<div class="form-group" style="margin:0px;width: 100%">
							<label for="workName" class="col-sm-4 control-label">施工项目：</label>
							<div class="col-sm-8">
								<div class="input-group" style="width:100%">
									<input id="workName" name="workName" type="text" maxlength="200"
										   class="form-control input-sm" placeholder="请输入施工项目" datatype="*" ignore="checked"/>
								</div>
							</div>
						</div>
					</div>
					<div class="foota">
						<div class="form-group" style="margin:0px;width: 100%">
							<label for="workPlace" class="col-sm-4 control-label">施工地点：</label>
							<div class="col-sm-8">
								<div class="input-group" style="width:100%">
									<input id="workPlace" name="workPlace" type="text" maxlength="200"
										   class="form-control input-sm" placeholder="请输入施工地点" datatype="*" ignore="checked"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tablebox ji">
					<div class="boxleft zi">
						检查事项
					</div>
					<div class="boxright">
						<table id="tab" cellpadding="2" cellspacing="0" border="1">
							<tr>
								<th>序号</th>
								<th class="fontSize">施工人员检查</th>
								<th>符<br>合</th>
								<th>不<br>符<br>合</th>
								<th class="">不<br>适<br>用</th>
							<tr>
								<th>1</th>
								<th>所有施工人员证件齐全</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>所有施工人员证件齐全</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>所有施工人员证件齐全</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>所有施工人员证件齐全</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>所有施工人员证件齐全</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>所有施工人员证件齐全</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>序号</th>
								<th class="fontSize">车辆管理</th>
								<th>符<br>合</th>
								<th>不<br>符<br>合</th>
								<th class="">不<br>适<br>用</th>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
							<tr>
								<th>1</th>
								<th>车辆引领人员持有航空器活动区驾驶证或相关引领资质文件。</th>
								<th></th>
								<th></th>
								<th class=""></th>
							</tr>
						</table>
					</div>

				</div>
				<div class="bottom other">
					<label for="checkTime" class="col-sm-2 control-label">检查小结：</label>
					<div class="col-sm-10 control-label">
						<textarea name="checkResultNote" class="form-control input-sm" rows="4" ignore="ignore"></textarea>
					</div>
				</div>
				<div class="bottom">
					<label for="checkTime" class="col-sm-2 control-label">整改意见：</label>
					<div class="col-sm-10 control-label">
						<textarea name="checkRepairNote" class="form-control input-sm" rows="4" ignore="ignore"></textarea>
					</div>
				</div>
				<div class="foot">
					<div class="foota" style="border-right:1px solid black">
						<label for="safetyName" class="col-sm-4 control-label">安全员（签字）</label>
						<div class="col-sm-8">
							<div class="input-group" style="width:100%">
								<input id="safetyName" name="safetyName" type="text" maxlength="200"
									   class="form-control input-sm" placeholder="" datatype="*" ignore="checked"/>
							</div>
						</div>
					</div>
					<div class="foota">
						<label for="supdepName" class="col-sm-6 control-label">飞行区管理部机坪监管部（签字）</label>
						<div class="col-sm-6">
							<div class="input-group" style="width:100%">
								<input id="supdepName" name="supdepName" type="text" maxlength="200"
									   class="form-control input-sm" placeholder="" datatype="*" ignore="checked"/>
							</div>
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
                format: 'yyyy-MM-dd HH:mm',
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