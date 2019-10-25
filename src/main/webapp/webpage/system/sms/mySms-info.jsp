<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="jquery,tools"></t:base>
</head>
<body >
	<div style="width:95%;text-align: center;">
	    <legend style="width: 65%;margin:20px auto;">${tSSms.esTitle}</legend>
			<div class="form" style="font-size: 13px;">
				<label class="form"><fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='both' value='${tSSms.createDate}'/></label>
			</div>
	    <hr style="BORDER-RIGHT: #00686b 1px dotted; BORDER-TOP: #00686b 1px dotted; BORDER-LEFT: #00686b 1px dotted; BORDER-BOTTOM: #00686b 1px dotted" noShade SIZE=1/>
			<div class="form" style="text-align: left;text-indent: 20px;">
				${tSSms.esContent}
		    </div>
	    </div>
    </div>
</body>
</html>