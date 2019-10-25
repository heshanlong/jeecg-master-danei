<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>抽查详情</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <t:base type="jquery,aceform,DatePicker,validform,ueditor"></t:base>
								
   <script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>

 <body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="famsSafeSpotCheckDetailController.do?doAdd" tiptype="1" >
			<input type="hidden" id="btn_sub" class="btn_sub"/>
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" name="fckId" value="${mainId}"/>
			<div class="tab-wrapper">
			    <!-- tab -->
			    <ul class="nav nav-tabs">
			      <li role="presentation" class="active"><a href="javascript:void(0);">抽查详情</a></li>
			    </ul>
			    <!-- tab内容 -->
			    <div class="con-wrapper" id="con-wrapper1" style="display: block;">
			      <div class="row form-wrapper">
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>检查人：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="checkPerson" name="checkPerson" maxlength="100" type="text" class="form-control"  datatype="*"  ignore="checked" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">检查人</label>
			          </div>
						</div>
						<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>航班号：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="flightno" name="flightno" maxlength="100" type="text" class="form-control"  datatype="*"  ignore="checked" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">航班号</label>
			          </div>
						</div>
						<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>机号：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="immediately" name="immediately" maxlength="100" type="text" class="form-control"  datatype="*"  ignore="checked" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">机号</label>
			          </div>
						</div>
						<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>机型：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="models" name="models" maxlength="100" type="text" class="form-control"  datatype="*"  ignore="checked" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">机型</label>
			          </div>
						</div>
						<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>停机位：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="slots" name="slots" maxlength="100" type="text" class="form-control"  datatype="*"  ignore="checked" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">停机位</label>
			          </div>
						</div>
						<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>检查情况：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="checkDetail" name="checkDetail" maxlength="100" type="text" class="form-control"  datatype="*"  ignore="checked" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">检查情况</label>
			          </div>
						</div>
			          
			        
							<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>检查开始时间：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="checkDateStart" name="checkDateStart" type="text"  datatype="*"  ignore="checked" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">检查开始时间</label>
			          </div>
						</div>
			          	<div class="row show-grid">
			          <div class="col-xs-3 text-center">
			          	<b>检查结束时间：</b>
			          </div>
			          		<div class="col-xs-3">
								<input id="checkDateEnd" name="checkDateEnd" type="text"  datatype="*"  ignore="checked" style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;" class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						<span class="Validform_checktip" style="float:left;height:0px;"></span>
						<label class="Validform_label" style="display: none">检查结束时间</label>
			          </div>
						</div>
     
			          <div class="row" id = "sub_tr" style="display: none;">
				        <div class="col-xs-12 layout-header">
				          <div class="col-xs-6"></div>
				          <div class="col-xs-6"><button type="button" onclick="neibuClick();" class="btn btn-default">提交</button></div>
				        </div>
				      </div>
			     </div>
			   </div>
			   
			   <div class="con-wrapper" id="con-wrapper2" style="display: block;"></div>
			 </div>
  </t:formvalid>

<script type="text/javascript">
   $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
	if(location.href.indexOf("load=detail")!=-1){
		$(".jeecgDetail").hide();
	}
	
	if(location.href.indexOf("mode=read")!=-1){
		//查看模式控件禁用
		$("#formobj").find(":input").attr("disabled","disabled");
	}
	if(location.href.indexOf("mode=onbutton")!=-1){
		//其他模式显示提交按钮
		$("#sub_tr").show();
	}
   });

  var neibuClickFlag = false;
  function neibuClick() {
	  neibuClickFlag = true; 
	  $('#btn_sub').trigger('click');
  }

</script>
 </body>
</html>