<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style>
/*表格滚动条显示齐全*/
.datagrid-body {
	width: 99.5% !important;
}
/*查询选项一行显示*/
#tSSmsListForm>span>span {
	margin-left: 20px
}

#tSSmsListForm>span>span>span {
	width: auto !important;
}

.datagrid-row-selected {
	color: #2272CE !important;
} /*选中颜色*/
</style>
<![if !IE]>
<style>
select[name="isRead"] {
	padding: 0 4px;
}
</style>
<![endif]>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 0px; border: 0px">
		<t:datagrid name="tSSmsList" checkbox="true" fitColumns="false"
			title="我的消息列表" actionUrl="tSSmsController.do?mydatagrid" idField="id"
			fit="true" queryMode="group" sortName="esSendtime" sortOrder="desc"
			rowStyler="myRowStyle">
			<t:dgCol title="common.esId" field="id" hidden="true"
				queryMode="single"></t:dgCol>
			<t:dgCol title="common.isRead" field="isRead" replace="已读_1,未读_0"
				queryMode="single" query="true"></t:dgCol>
			<%-- <t:dgCol title="common.messageType"  field="esType"  query="false" queryMode="single" dictionary="msgType"></t:dgCol> --%>
			<t:dgCol title="common.messageHeader" formatterjs="formatter"
				field="esTitle" query="true" queryMode="single" width="130"></t:dgCol>
			<t:dgCol title="common.sender" field="esSender"
				formatterjs="formatter" queryMode="single" width="70"></t:dgCol>
			<t:dgCol title="common.content_2" field="esContent"
				queryMode="single" query="true" width="250"></t:dgCol>
			<%--  <t:dgCol title="common.dateCreated"  field="createDate" formatter="yyyy-MM-dd hh:mm:ss" query="false" queryMode="group" ></t:dgCol> --%>
			<t:dgCol title="common.sendtime" field="esSendtime"
				formatter="yyyy-MM-dd hh:mm" queryMode="single"></t:dgCol>
			<t:dgCol title="common.operation" field="opt" width="70"></t:dgCol>
			<t:dgFunOpt funname="doRead(id,isRead)" title="common.read"
				urlclass="ace_button" urlfont="fa-wrench"></t:dgFunOpt>
			<t:dgToolBar title="全部标为已读" icon="icon-ok" funname="allMarkAsRead"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
<script type="text/javascript" charset="utf-8">
	$(function() {
		//修改样式
		$('#tSSmsListForm>span>span:nth-child(1)>span').text('状态：')
	})

	//设置行样式
	function myRowStyle(index, row) {
		if (row.isRead != 1) {
			return 'font-weight:bold;';
		}
	}

	//详情弹窗
	function doRead(id, isRead) {
		var width = $(window).width();
		var height = $(window).height();
		$.dialog.setting.zIndex = getzIndex();
		$.dialog({
			content : 'url:tSSmsController.do?goSmsDetail&id=' + id,
			zIndex : getzIndex(),
			title : '通知详情',
			width : width,
			height : height,
			button : [ {
				name : '关闭',
				callback : function() {

				}
			} ],
			close : function() { //关闭子页面时刷新父页面
				if (isRead == 0) {
					$('#tSSmsList').datagrid('reload');
					//给父页面的标签赋值
					var msgText = parent.$('#systemMsg').attr('newNums');
					var newNum = parseInt(msgText) - 1;
					if (newNum < 1) {
						parent.$('#systemMsg').text('');
					} else if (newNum < 100) {
						parent.$('#systemMsg').text(newNum);
					}
					//更新为最新值
					parent.$('#systemMsg').attr('newNums', newNum)
				}
			},
			opacity : 0.4
		}).zindex()
	}

	//文本显示title
	function formatter(val, row) {
		if (val) {
			return '<span title="' + val + '">' + val + '</span>';
		} else {
			return val;
		}
	}
	//全部标为已读
	function allMarkAsRead() {
		layer.confirm('要将所有未读消息标为已读吗？', {
			btn : [ '确定', '取消' ]
		//按钮
		}, function() {
			//全部标记为已读
			$.ajax({
	            url:"tSSmsController.do?allRead",
	            data:{},
	            dataType:"json",
	            success:function(result){
	            	console.log(result);
	                if(result.success){
	                	layer.msg(result.obj+'条消息标为已读', {
	        				time : 1000, //1s后自动关闭
	        				btn : []
	        			});
	                	$('#tSSmsList').datagrid('reload');
						//给父页面的标签赋值更新为最新值
						parent.$('#systemMsg').text('');
						parent.$('#systemMsg').attr('newNums', 0)
	                }else{
	                	layer.msg('操作失败，请重试', {
	        				time : 1500, //1.5s后自动关闭
	        				btn : []
	        			});
	                }
	            }
	        });
			
		}, function() {
			layer.msg('操作已取消', {
				time : 1000, //1s后自动关闭
				btn : []
			});
		});
	}
</script>