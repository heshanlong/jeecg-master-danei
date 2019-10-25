<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/themes/fineui/css/mainform.css" type="text/css"/>
<style>
.conc-wrapper input:not([type='radio']){
width:95%;
}
.conc-wrapper input[type='checkbox'],input[type='radio']{
width:auto;
}
.conc-wrapper select{
width:95% !important;
}
</style>
<div style="margin: 0 15px;padding-bottom:10px;">
<!-- 若页面内容太多需要滚动条 ,可自己根据页面添加overflow: auto;样式 -->
<div class="conc-wrapper" style="margin-top:10px">
			<div class="main-tab">
				<ul class="tab-info">
				  <li role="presentation" class="active">
					<a href="javascript:void(0);"> &nbsp;&nbsp;抽查记录</a>
				  </li>
				</ul>
				<!-- tab内容 -->
				<div class="con-wrapper1">
				  <div class="row form-wrapper">
			<div class="row show-grid">
		<div class="col-xs-1 text-center">
			<b>抽查日期：</b>
		</div>
		<div class="col-xs-2">
				<input id="checkDate" name="checkDate" type="text"  validType="fams_spot_check,check_date,id" ignore="ignore"  style="background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;"  class="form-control" onClick="WdatePicker()" value="<fmt:formatDate value='${famsSafeSpotCheckPage.checkDate}' type='date' pattern='yyyy-MM-dd'/>" />
			<span class="Validform_checktip" style="float:left;height:0px;"></span>
			<label class="Validform_label" style="display: none">抽查日期</label>
		</div>
		<div class="col-xs-1 text-center">
			<b>当日小结：</b>
		</div>
		<div class="col-xs-2">
				<input id="checkDetail" name="checkDetail" maxlength="255" type="text" class="form-control"  ignore="ignore"  value='${famsSafeSpotCheckPage.checkDetail}' />
			<span class="Validform_checktip" style="float:left;height:0px;"></span>
			<label class="Validform_label" style="display: none">当日小结</label>
		</div>
			</div>
					</div>
				</div>
			</div>
	
</div>
</div>
<script type="text/javascript">
   $(function(){
	    //查看模式情况下,删除和上传附件功能禁止使用
		if(location.href.indexOf("load=detail")!=-1){
			$(".jeecgDetail").hide();
			$("input,select,textarea").attr("disabled","disabled");
		}
   });
</script>