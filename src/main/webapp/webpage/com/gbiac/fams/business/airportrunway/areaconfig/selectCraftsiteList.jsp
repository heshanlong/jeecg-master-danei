<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>机位</title>
<meta name="viewport" content="width=device-width" />
<t:base type="bootstrap,bootstrap-table,layer"></t:base>
</head>
<body>

<t:datagrid style="width:400px;height:250px;" height="500px;" width="600" name="famsAttentionCraftsiteList" component="bootstrap-table"  checkbox="true" sortName="id"   sortOrder="asc"  pagination="true" fitColumns="true" title="机位" actionUrl="famsAreaConfigController.do?selectCraftsiteDatagrid" idField="id"  fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="编号"  field="serialNumber"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机位"  field="craftsite"  queryMode="single"  width="120"></t:dgCol>
</t:datagrid>

<script type="text/javascript">

	
</script>

</body>
</html>