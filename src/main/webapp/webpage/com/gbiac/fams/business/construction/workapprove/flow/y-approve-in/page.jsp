<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page= "../page1.jsp"><jsp:param name="load" value="detail"></jsp:param></jsp:include>
<div class="form-group">
    <label class="col-sm-3 control-label"><span style="color:red;">*</span>审批是否通过：</label>
    <div class="col-sm-7">
        <div class="input-group" style="width:100%">
            <t:dictSelect field="approveState" type="radio" extendJson="{class:'approveState i-checks'}"
                          typeGroupCode="sf_yn" hasLabel="false" defaultVal="Y" title="审批是否通过"
                          datatype="*"></t:dictSelect>
        </div>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">审批意见：</label>
    <div class="col-sm-7">
        <div class="input-group" style="width:100%">
                        <textarea name="approveStateNote" maxlength="1000" class="form-control input-sm" rows="4"
                                  datatype="*" ignore="ignore">审批通过</textarea>
            <span class="Validform_checktip" style="float:left;height:0px;"></span>
            <label class="Validform_label" style="display: none">审批意见</label>
        </div>
    </div>
</div>
<%--流程操作区域--%>
<div style="text-align: center">
    <button type="button" class="ace_button" style="background-color: #2272CE"  onclick="${funname}('submitBtn')">确认</button>
    <button id="submitBtn" class="hidden" type="submit" onclick="ValidformForBMP('formobj')"></button>
</div>
<jsp:include page= "../page2.jsp"><jsp:param name="load" value="detail"></jsp:param></jsp:include>
<script type="text/javascript">
    /**
     * 提交审批
     */
    function y_approve_in_fun(submitBtn){
        layer.confirm('确定提交该操作吗？',{
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