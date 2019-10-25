<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page= "../page1.jsp"><jsp:param name="load" value="detail"></jsp:param></jsp:include>
<div class="form-group">
    <label class="col-sm-3 control-label">附言：</label>
    <div class="col-sm-7">
        <div class="input-group" style="width:100%">
                        <textarea name="approveStateNote" maxlength="1000" class="form-control input-sm" rows="4"
                                  datatype="*" ignore="ignore"></textarea>
            <span class="Validform_checktip" style="float:left;height:0px;"></span>
            <label class="Validform_label" style="display: none">附言</label>
        </div>
    </div>
</div>
<%--流程操作区域--%>
<div style="text-align: center">
    <c:choose>
        <c:when test="${famsWorkApprove.isContinueWork=='Y'}">
            <button type="button" class="ace_button" style="background-color: #2272CE"  onclick="${funname}('submitBtn')">完成施工</button>
        </c:when>
        <c:otherwise>
            <button type="button" class="ace_button" style="background-color: #2272CE"  onclick="${funname}('submitBtn')">申请离场</button>
        </c:otherwise>
    </c:choose>
    <button id="submitBtn" class="hidden" type="submit" onclick="ValidformForBMP('formobj')"></button>
</div>
<jsp:include page= "../page2.jsp"><jsp:param name="load" value="detail"></jsp:param></jsp:include>
<script type="text/javascript">
    <c:choose>
        <c:when test="${famsWorkApprove.isContinueWork=='Y'}">
            /**
             * 提交申请
             */
            function s_out_fun(submitBtn){
                layer.confirm('确定完成施工吗？',{
                    btn:['确认','取消']
                },function(index) {
                    $("#"+submitBtn).trigger("click");
                    layer.close(index);
                },function(){
                    return;
                })
            }
        </c:when>
        <c:otherwise>
            /**
             * 提交申请
             */
            function s_out_fun(submitBtn){
                layer.confirm('确定申请离场吗？',{
                    btn:['确认','取消']
                },function(index) {
                    $("#"+submitBtn).trigger("click");
                    layer.close(index);
                },function(){
                    return;
                })
            }
        </c:otherwise>
    </c:choose>
</script>
<jsp:include page= "../page3.jsp"></jsp:include>