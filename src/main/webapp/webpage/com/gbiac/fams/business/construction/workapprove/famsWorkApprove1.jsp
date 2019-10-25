<%--施工审批列表--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>部门施工申请</title>
    <meta name="viewport" content="width=device-width"/>
    <t:base type="bootstrap,bootstrap-table,layer,common"></t:base>
</head>
<body>
<t:datagrid name="famsWorkApproveList" component="bootstrap-table" checkbox="true" sortName="createDate"
            sortOrder="desc" pagination="true" fitColumns="true" title="施工申请"
            actionUrl="famsWorkApproveController.do?approve1" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键" field="id" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="创建用户" field="createUserId" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="创建人名称" field="createName" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称" field="createBy" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="更新人名称" field="updateName" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称" field="updateBy" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="所属部门" field="sysOrgCode" hidden="false" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="所属公司" field="sysCompanyCode" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="更新日期" field="updateDate" formatter="yyyy-MM-dd" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="编号"  field="number" query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="编号"  field="numberContent" hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="施工项目"  field="workName"  showLen="10" query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="施工申报单位"  field="workDepart" query="true" hidden="false" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="施工申报单位id"  field="workApproveDepart"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <%--<t:dgCol title="施工内容" field="workContent" showLen="10" query="true" queryMode="single" width="120"></t:dgCol>--%>
    <t:dgCol title="施工区域" field="workArea" showLen="10" query="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="施工类型" field="workTypeId" query="true" queryMode="single" dictionary="fams_work_type,id,type_name" width="120"></t:dgCol>
    <t:dgCol title="是否连续"  field="isContinueWork" queryMode="single"  dictionary="sf_yn"  width="120"></t:dgCol>
    <t:dgCol title="施工开始时间" field="workStartTime" query="true" formatter="yyyy-MM-dd hh:mm" queryMode="group" width="120"></t:dgCol>
    <t:dgCol title="施工结束时间" field="workEndTime" query="true" formatter="yyyy-MM-dd hh:mm" queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="提交申请时间" field="createDate" formatter="yyyy-MM-dd hh:mm" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="施工影响区域" hidden="true" field="workAffectArea" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="是否动火" hidden="true" field="isUseFire" queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
    <t:dgCol title="动火开始时间"  field="useFireStartTime"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="动火结束时间"  field="useFireEndTime"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="是否高空"  field="isHigh"  hidden="true" queryMode="single"  dictionary="sf_yn"  width="120"></t:dgCol>
    <t:dgCol title="施工备注" hidden="true" field="workNote" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="流程实例Id" hidden="true" field="procinstId" queryMode="single" width="120"></t:dgCol>
    <c:choose>
        <c:when test="${canApprove}">
            <t:dgCol title="状态" field="taskKey" query="true" dictionary="v_work_state,id,name" queryMode="single" width="120"></t:dgCol>
        </c:when>
        <c:otherwise>
            <t:dgCol title="状态" field="taskKey" query="true" dictionary="fams_work_node_info,task_key,task_state" queryMode="single" width="120"></t:dgCol>
        </c:otherwise>
    </c:choose>
    <t:dgCol title="操作" field="buttonStr" sortable="false" queryMode="single" width="100"></t:dgCol>
    <t:dgToolBar title="查看" icon="icon-search" url="famsWorkApproveController.do?goUpdate" funname="detail"  width="100%" height="100%"></t:dgToolBar>
		<t:dgToolBar title="流程图预览" icon="icon-search"
			url="famsWorkApproveController.do?viewBMP" funname="add" width="100%"
			height="100%"></t:dgToolBar>
	</t:datagrid>
<script type="text/javascript">

	function add(title, addurl, id, gname, width, height) {
		gridname = gname;
		var rowsData = $('#' + id).bootstrapTable('getSelections');
		if (!rowsData || rowsData.length == 0) {
			tip('请选择编辑项目');
			return;
		}
		if (rowsData.length > 1) {
			tip('请选择一条记录再编辑');
			return;
		}
		addurl += '&isContinueWork='+rowsData[0].isContinueWork+'&workTypeId='+rowsData[0].workTypeId;
	    createwindow(title, addurl,width,height);
	}
	
    $(document).ready(function () {
    });
    //操作栏中的重新提交施工申请方法
    function myApplyAgain(id){
        createwindow("重新申请进场","famsWorkApproveController.do?goUpdateForApplyAgain&id="+id,"100%","100%");
    }
    //操作栏中的撤回提交施工申请方法
    function undo(title,url, id,width,height,isRestful) {
        gridname=id;
        var rowsData = $('#'+id).bootstrapTable('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip('请选择编辑项目');
            return;
        }
        if (rowsData.length>1) {
            tip('请选择一条记录再编辑');
            return;
        }
        //查询该申请是否可以进行撤回操作
        $.ajax({
            url:"famsWorkApproveController.do?canUndo",
            data:{
                id:rowsData[0].id
            },
            dataType:"json",
            success:function(result){
                if(result.success){
                    jumpBMPPage(rowsData[0].id,'s_undo_flag','s_undo_fun','s_undo','flow/s-undo/page','撤回');
                }else{
                    tip(result.msg);
                }
            }
        });
    }
    //操作栏中的编辑方法
    function myUpdate(id){
        createwindow("编辑","famsWorkApproveController.do?goUpdate&id="+id,"100%","100%");
    }
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'famsWorkApproveController.do?upload', "famsWorkApproveList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("famsWorkApproveController.do?exportXls", "famsWorkApproveList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("famsWorkApproveController.do?exportXlsByT", "famsWorkApproveList");
    }


    /**
     * 跳转流程页面方法
     * @param id 施工申请id
     * @param flag 流程标识
     * @param funname 流程方法
     * @param taskkey 流程key
     * @param jumpurl 流程跳转页面
     */
    function jumpBMPPage(id,flag,funname,taskkey,jumpurl,taskname){
        mycreatedetailwindow(taskname,"famsWorkApproveController.do?jumpBMPPage&id="+id+"&flag="+flag+"&funname="+funname+"&taskkey="+taskkey+"&jumpurl="+jumpurl+"&taskname="+taskname+"",'100%','100%');
    }

    /**
     * 重写的录入方法
     * @param title
     * @param url
     * @param list
     * @param width
     * @param height
     */
    function addRewrite(title,url,list,width,height){
        width = width?width:700;
        height = height?height:400;
        if(width=="100%" || height=="100%"){
            width = window.top.document.body.offsetWidth;
            height =window.top.document.body.offsetHeight-100;
        }
        $.dialog({
            content: 'url:'+url,
            lock : true,
            zIndex: getzIndex(),
            width:width,
            height:height,
            title:title,
            opacity : 0.3,
            cache:false,
            button: [{
                name: '提交',
                callback: function(){
                    iframe = this.iframe.contentWindow;
                    if(window.localStorage.getItem("uploadSuccess")!=undefined){
                        var uploadSuccess=window.localStorage.getItem("uploadSuccess");
                        if(uploadSuccess!="true"){
                            iframe.showMe("文件未上传完，请耐心等待！");
                            return false;
                        }
                    }
                    window.localStorage.removeItem("uploadSuccess");

                    $('#formobj', iframe.document).attr('action','famsWorkApproveController.do?doAdd&comfrom=submit');
                    saveObj();
                    return false;
                },
                focus: true
            },{
                name: '暂存',
                callback: function(){
                    iframe = this.iframe.contentWindow;
                    if(window.localStorage.getItem("uploadSuccess")!=undefined){
                        var uploadSuccess=window.localStorage.getItem("uploadSuccess");
                        if(uploadSuccess!="true"){
                            iframe.showMe("文件未上传完，请耐心等待！");
                            return false;
                        }
                    }
                    window.localStorage.removeItem("uploadSuccess");
                    $('#formobj', iframe.document).attr('action','famsWorkApproveController.do?doAdd');
                    saveObj();
                    return false;
                },
                focus: false
            }],
            cancelVal: '关闭',
            cancel: true /*为true等价于function(){}*/
        });
    }

    /**
     * 更新事件打开窗口
     * @param title 编辑框标题
     * @param addurl//目标页面地址
     * @param id//主键字段
     */
    function updateRewrite(title,url, id,width,height,isRestful) {
        gridname=id;
        var rowsData = $('#'+id).bootstrapTable('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip('请选择编辑项目');
            return;
        }
        if (rowsData.length>1) {
            tip('请选择一条记录再编辑');
            return;
        }
        if(isRestful!='undefined'&&isRestful){
            url += '/'+rowsData[0].id;
        }else{
            url += '&id='+rowsData[0].id;
        }
        addRewrite(title,url,width,height);
    }
</script>
</body>
</html>