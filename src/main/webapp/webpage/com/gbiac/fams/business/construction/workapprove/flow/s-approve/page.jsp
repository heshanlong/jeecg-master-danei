<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page= "../page1.jsp"></jsp:include>
<%--流程操作区域--%>
<div style="text-align: center">
    <button type="button" class="ace_button" style="background-color: #2272CE"  onclick="${funname}('submitBtn')">提交申请</button>
    <button id="submitBtn" class="hidden" type="submit" onclick="ValidformForBMP('formobj')"></button>
</div>
<jsp:include page= "../page2.jsp"></jsp:include>
<script type="text/javascript">
    /**
     * 提交申请
     */
    function s_apply_fun(submitBtn){
        layer.confirm('确定提交该申请吗？',{
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