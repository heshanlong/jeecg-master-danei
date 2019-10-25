<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page= "../page1.jsp"><jsp:param name="load" value="detail"></jsp:param></jsp:include>
<%--流程操作区域--%>
<div style="text-align: center">
    <button type="button" class="ace_button" style="background-color: #2272CE"  onclick="${funname}('submitBtn')">申请进场</button>
    <button id="submitBtn" class="hidden" type="submit" onclick="ValidformForBMP('formobj')"></button>
</div>
<jsp:include page= "../page2.jsp"><jsp:param name="load" value="detail"></jsp:param></jsp:include>
<script type="text/javascript">
    /**
     * 提交审批
     */
    function s_in_fun(submitBtn){
        layer.confirm('确定申请进场吗？',{
            btn:['确认','取消']
        },function(index) {
            $("#"+submitBtn).trigger("click");
            layer.close(index);
        },function(){
            return;
        })
    }
</script>
<jsp:include page= "../page3.jsp"></jsp:include>