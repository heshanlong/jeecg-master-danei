<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>通知通告表</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>
<t:datagrid name="famsAnnounceNotifyReceiveList" component="bootstrap-table"
            checkbox="true" sortName="sendTime"  sortOrder="desc"
            pagination="true" fitColumns="true" title="通知通告表"
            actionUrl="famsAnnounceNotifyReceiveController.do?datagrid"
            idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="number"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"  query="true"  queryMode="single"  width="200" showLen="16"></t:dgCol>
   <t:dgCol title="内容"  field="content"  query="true"  queryMode="single"  width="300" showLen="26"></t:dgCol>
   <t:dgCol title="类型"  field="type"  query="true"  queryMode="single"  width="120" dictionary="anouType"></t:dgCol>
   <t:dgCol title="发送模式"  field="sendPattern"  query="true"  queryMode="single"  width="160" dictionary="sePattern"></t:dgCol>
   <t:dgCol title="发送人"  field="sender"  query="true"  queryMode="single"  width="120" showLen="8"></t:dgCol>
   <t:dgCol title="发送时间"  field="sendTime"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="200"></t:dgCol>
   <t:dgCol title="分组维护主表id"  field="groupId"  hidden="true"  queryMode="single"  width="36"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <!-- 添加状态 -->
   <t:dgCol title="状态" field="readState" query="true"   queryMode="single" dictionary="readState" sortable="false"  width="120"></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
   <%--exp="state#ne#2" state不等于2时按钮隐藏--%>
   <%--<t:dgDelOpt exp="state#ne#2" title="撤回" url="famsAnnounceNotifyController.do?doDel&id={id}"--%>
               <%--urlclass="ace_button state" urlfont="fa-edit"></t:dgDelOpt>--%>
   <%--<t:dgToolBar title="录入" icon="icon-add" url="famsAnnounceNotifyController.do?goAdd" funname="add"  width="100%" height="100%"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="famsAnnounceNotifyController.do?goUpdate" funname="update"  width="100%"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="批量删除"  icon="icon-remove" url="famsAnnounceNotifyController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" funname="details" width="100%"></t:dgToolBar>

   <%--<t:dgFunOpt funname="doRead(id,isRead)" title="查看qq" urlclass="ace_button"  urlfont="fa-wrench"></t:dgFunOpt>--%>
   <%--<t:dgToolBar title= "导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
<%--<script src="webpage/com/gbiac/fams/business/anounce/js/famsAnnounceCommon.js"></script>--%>
  <script type="text/javascript">
	 $(document).ready(function(){
         // 初始化检索,时间设置默认值
         // $('#btn_collapse_search').attr('state',0); //初始化添加未展开状态
         // clickEnvent('#btn_collapse_search','#sendTime_begin','#sendTime_end');
         // var $trs = $('tr');
         // console.log('$tr',$trs);
     });

     //查看
     function details(){
         var $tr = $('#famsAnnounceNotifyReceiveList tr.selected');
         if($tr.length === 1){
             var id = $tr.attr('data-uniqueid');
             var width = window.top.document.body.offsetWidth;
             var height = window.top.document.body.offsetHeight-100;
             $.dialog.setting.zIndex = getzIndex();
             $.dialog({
                 content : 'url:famsAnnounceNotifyController.do?goDetail&load=detail&id='+id,
                 zIndex : getzIndex(),
                 title : '通知详情',
                 lock : true,
                 width : width,
                 height : height,
                 button : [ {
                     name : '关闭',
                     callback : function () {

                     }
                 }],
                 close:function(){  //关闭时刷新页面
                     var tdText = $tr.find('td:last').text();
                     if(tdText === '未读'){
                         //bootstrap表格刷新
                         $('#famsAnnounceNotifyReceiveList').bootstrapTable('refresh');
                     }
                 },
                 opacity : 0.4
             }).zindex()
         }else if($tr.length === 0){
             layerAlert('请选择查看项目')
         }else if($tr.length > 1){
             layerAlert('只能选择一条项目')
         }
     }

    function layerAlert(str) {
        layer.alert(str,{
            icon: 0,
            shade: false, //遮罩
            offset: 'rb', //右下角弹出
            time: 2000, //2秒后自动关闭
            anim: 2,
            btn:''
        })
    }

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'famsAnnounceNotifyController.do?upload', "famsAnnounceNotifyList");
	}
	
	//导出
	function ExportXls() {
		JeecgExcelExport("famsAnnounceNotifyController.do?exportXls","famsAnnounceNotifyList");
	}
	
	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("famsAnnounceNotifyController.do?exportXlsByT","famsAnnounceNotifyList");
	}

	 </script>
</body>
</html>