<%--作业管理列表--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>作业申请</title>
    <meta name="viewport" content="width=device-width"/>
    <t:base type="bootstrap,bootstrap-table,layer,common"></t:base>
</head>
<body>
<t:datagrid name="famsWorkApproveList" component="bootstrap-table" checkbox="true" sortName="createDate"
            sortOrder="desc" pagination="true" fitColumns="true" title="作业申请"
            actionUrl="famsWorkApproveController.do?manager" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键" field="id" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="创建用户" field="createUserId" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="创建人名称" field="createName" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称" field="createBy" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="更新人名称" field="updateName" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称" field="updateBy" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="所属部门" field="sysOrgCode" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="所属公司" field="sysCompanyCode" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="更新日期" field="updateDate" formatter="yyyy-MM-dd" hidden="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="编号"  field="number" query="true" hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="编号"  field="numberContent" hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="作业项目"  field="workName"  showLen="10" query="true" queryMode="single"  width="120"></t:dgCol>
    <%--<t:dgCol title="作业内容" field="workContent" showLen="10" query="true" queryMode="single" width="120"></t:dgCol>--%>
    <t:dgCol title="作业申报单位"  field="workDepart"  hidden="false" query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="作业区域" field="workArea" showLen="10" query="true" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="作业申报单位"  field="workApproveDepart"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="作业类型" field="workTypeId" query="true" queryMode="single" dictionary="fams_work_type,id,type_name" width="120"></t:dgCol>
    <t:dgCol title="审批类型" field="applyType" query="true" queryMode="single" dictionary="apply_type" width="120"></t:dgCol>
    <t:dgCol title="作业开始日期" field="dateStart" query="true" formatter="yyyy-MM-dd" queryMode="group" width="120"></t:dgCol>
    <t:dgCol title="作业结束日期" field="dateEnd" query="true" formatter="yyyy-MM-dd" queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="作业开始时间" field="workStartTime" query="true" formatter="yyyy-MM-dd hh:mm" queryMode="group" width="120"></t:dgCol>
    <t:dgCol title="作业结束时间" field="workEndTime" query="true" formatter="yyyy-MM-dd hh:mm" queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="提交申请时间" field="createDate" formatter="yyyy-MM-dd hh:mm" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="作业影响区域" hidden="true" field="workAffectArea" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="是否动火" hidden="true" field="isUseFire" queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
    <t:dgCol title="动火开始时间"  field="useFireStartTime"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="动火结束时间"  field="useFireEndTime"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="是否高空"  field="isHigh"  hidden="true" queryMode="single"  dictionary="sf_yn"  width="120"></t:dgCol>
    <t:dgCol title="作业备注" hidden="true" field="workNote" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="流程实例Id" hidden="true" field="procinstId" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="状态" field="taskKey" query="true" dictionary="fams_work_node_info,task_key,task_state" queryMode="single" width="120"></t:dgCol>
    <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <t:dgDelOpt title="删除" operationCode="#delete" url="famsWorkApproveController.do?doDel&id={id}" urlfont="fa-trash-o"/>
    <t:dgToolBar title="查看" icon="icon-search" url="famsWorkApproveController.do?goUpdate" funname="detail"  width="100%" height="100%"></t:dgToolBar>
    <t:dgToolBar title="编辑" operationCode="#update" icon="icon-edit" url="famsWorkApproveController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
    <t:dgToolBar title="批量删除" operationCode="#batchDelete" icon="icon-remove" url="famsWorkApproveController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    <t:dgToolBar title="明日审批类导出" operationCode="#applyExport" icon="icon-putout" funname="applyExportXls"></t:dgToolBar>
    <t:dgToolBar title="明日报备类导出" operationCode="#reportExport" icon="icon-putout" funname="reportExportXls"></t:dgToolBar>
    <t:dgToolBar title="导出" operationCode="#allExport" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
    $(document).ready(function () {
    });
    //导入
    function ImportXls() {
        openuploadwin('Excel导入', 'famsWorkApproveController.do?upload', "famsWorkApproveList");
    }

    //导出
    function ExportXls() {
        JeecgExcelExport("famsWorkApproveController.do?exportXls&exportType=all", "famsWorkApproveList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("famsWorkApproveController.do?exportXlsByT", "famsWorkApproveList");
    }

    //审批类导出
    function applyExportXls() {
        JeecgExcelExport("famsWorkApproveController.do?exportXls&exportType=apply", "famsWorkApproveList");
    }

    //报备类导出
    function reportExportXls() {
        JeecgExcelExport("famsWorkApproveController.do?exportXls&exportType=report", "famsWorkApproveList");
    }
</script>
</body>
</html>