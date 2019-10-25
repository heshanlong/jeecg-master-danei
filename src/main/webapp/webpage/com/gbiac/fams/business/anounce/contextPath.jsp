<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<% 
String path = request.getContextPath();
String basePath = "http://localhost:8080/jeecg/";/**request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/jeecg"*/;
%>
<c:set var="webRoot" value="<%=basePath%>" />