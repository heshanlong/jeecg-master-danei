<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String load=request.getParameter("load");
    request.setAttribute("load",load);
%>
<%@include file="/context/mytags.jsp" %>
            </div>
        </form>
        <div class="div-right">
            <%--当前状态--%>
            <div class="form-group clearfix">
                <label for="decideName" class="col-sm-6 control-label">当前状态：${state}</label>
            </div>
            <%--历史动态--%>
            <div class="form-group clearfix">
                <label for="decideName" class="col-sm-2 control-label">历史动态：</label>
                <div class="col-sm-10">
                    <ul class="layui-timeline">
                        <c:if test="${empty process}">无</c:if>
                        <c:forEach items="${process}" var="item" varStatus="vs">
                            <li class="layui-timeline-item">
                                <i class="layui-icon layui-timeline-axis"></i>
                                <div class="layui-timeline-content layui-text">
                                    <div class="layui-timeline-title">${item.departname}</div>
                                    <div class="layui-p-time">
                                        <div class="ellipsis">
                                            <div class="ellipsis-container">
                                                <div class="ellipsis-content">
                                                        ${item.realname} ${item.note}${item.content==null?"。":"："}${item.content}
                                                </div>
                                                <div class="ellipsis-ghost">
                                                    <div class="ellipsis-placeholder"></div>
                                                    <div class="ellipsis-more">...更多</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div style="margin-right: 0">
                                            <fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${item.time}'/>
                                        </div>
                                    </div>
                                </div>
                                <div class="line"></div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <%--检查记录--%>
            <div class="form-group clearfix" style="margin-top: 20px">
                <label for="decideName" class="col-sm-2 control-label">检查记录：</label>
                <div class="col-sm-10">
                    <ul class="layui-timeline">
                        <c:if test="${empty checkInfo}">无</c:if>
                        <c:forEach items="${checkInfo}" var="item" varStatus="vs">
                            <li class="layui-timeline-item">
                                <i class="layui-icon layui-timeline-axis"></i>
                                <div class="layui-timeline-content layui-text">
                                    <div class="layui-timeline-title">${item.value.departname} - ${item.value.username}</div>
                                    <div class="layui-p-time">
                                        <div>
                                            <fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${item.value.check_time}'/> 进行了${item.name}
                                            <a class="easyui-linkbutton" onclick="${item.funname}('${item.value.id}')">详情</a>
                                        </div>
                                        <div style="margin-right: 0">
                                        </div>
                                    </div>
                                    <div class="show2Line showLineClick" style="width: 400px;">
                                        <div class="ellipsis">
                                            <div class="ellipsis-container">
                                                <div class="ellipsis-content">
                                                    检查小结：${item.value.result}
                                                </div>
                                                <div class="ellipsis-ghost">
                                                    <div class="ellipsis-placeholder"></div>
                                                    <div class="ellipsis-more">...更多</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="show2Line showLineClick" style="width: 400px;">
                                        <div class="ellipsis">
                                            <div class="ellipsis-container">
                                                <div class="ellipsis-content">
                                                    整改措施：${item.value.note}
                                                </div>
                                                <div class="ellipsis-ghost">
                                                    <div class="ellipsis-placeholder"></div>
                                                    <div class="ellipsis-more">...更多</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="line"></div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#mapBtn').on('click',function(){
        var title="";
        <c:if test="${load=='detail'}">
            localStorage.setItem("isMark","2");
            title="查看标注";
        </c:if>
        <c:if test="${load==''||load==null}">
            localStorage.setItem("isMark","1");
            title="编辑标注";
        </c:if>
        var  str=localStorage.setItem("mapPostion",$('#workAffectAreaMap').html());
        $.dialog({
            content: 'url:webpage/com/gbiac/fams/business/construction/workapprove/superMap/page/map.html',
            zIndex: getzIndex(),
            lock : true,
            width:window.top.document.body.offsetWidth,
            height: window.top.document.body.offsetHeight-100,
            title:title,
            opacity : 0.3,
            cache:false,
            button: [{
                name: '确定',
                callback: function(){
                    var  str=localStorage.getItem("mapPostion");
                    $('#workAffectAreaMap').html(str);
                    $('#mapBtn').linkbutton({text:str==""?"点击标注":"已标注"});
                },
                focus: true
            },
                {
                    name: '关闭',
                    callback: function() {

                    }
                }]
        });
    })

    var subDlgIndex = "";
    var approveStateNote="";
    $(document).ready(function () {
        $(".laydate-datetime").each(function(){
            var _this = this;
            laydate.render({
                elem: this,
                format: 'yyyy-MM-dd HH:mm',
                type: 'datetime',
                ready: function(date){
                    $(_this).val(DateJsonFormat(date,this.format));
                },
                done: function(value, date, endDate){
                    if(_this.id=="useFireStartTime" || _this.id=="useFireEndTime"||_this.id=="workStartTime" || _this.id=="workEndTime"){
                        checkWorkFireTime(_this,'useFireStartTime','useFireEndTime','workStartTime','workEndTime');
                    }
                }
            });
        });
        //给施工类型绑定onchange事件
        $("#workTypeId").change(function(obj){
            var id=$(this).val();
            var text=$(this).find("option:selected").text();
            if(text=="不停航施工 "){
                $("#workSafePersons").find("input[name='workSafePerson_role']").attr("ignore","checked");
                $("#files").attr("datatype","*");
            }else{
                $("#workSafePersons").find("input[name='workSafePerson_role']").attr("ignore","ignore");
                $("#files").removeAttr("datatype");
                //非不停航施工不能为连续施工
                $(".isContinueWork").iCheck("uncheck");
                $(".isContinueWork[value='N']").iCheck("check");
            }
        });
        $('.isContinueWork').on('ifChecked', function(event){
            var value=event.target.defaultValue;
            if(value=="Y"){//若为连续施工，则必须为不停航施工
                //$("#workTypeId option").removeAttr("selected");
                $("#workTypeId option[value='8a0790e9684a3d5301684a64bde60001']").attr("selected", true);
            }
        });
        //单选框/多选框初始化
        $('.i-checks').iCheck({
            labelHover: false,
            cursor: true,
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
            increaseArea: '20%'
        });
        $('.isUseFire').on('ifChecked', function(event){
            var value=event.target.defaultValue;
            if(value=="Y"){
                $("#useFireTime").show();
                $("#useFireTime").find("input").attr("ignore","checked");
                $("#useFireTime").find("input").removeAttr("disabled");
                $("#files").attr("datatype","*");
            }else{
                $("#useFireTime").hide();
                $("#useFireTime").find("input").attr("ignore","ignore");
                $("#useFireTime").find("input").attr("disabled","disabled");
                $("#files").removeAttr("datatype");
            }
        });
        $('.approveState').on('ifChecked', function(event){
            var text=$("textarea[name='approveStateNote']").text();
            if(text!=undefined&&text!=""){
                approveStateNote=text;
            }
            var value=event.target.defaultValue;
            if(value=="Y"){
                $("textarea[name='approveStateNote']").removeAttr("datatype");
                $("textarea[name='approveStateNote']").attr("ignore","ignore");
                $("textarea[name='approveStateNote']").text(approveStateNote);
            }else{
                $("textarea[name='approveStateNote']").text("");
                $("textarea[name='approveStateNote']").attr("datatype","*");
                $("textarea[name='approveStateNote']").attr("ignore","check");
            }
        });
        <c:if test="${load=='detail'}">
            /*禁止所有输入框*/
            $("input").attr({"disabled":"disabled"});
            $("textarea").attr({"disabled":"disabled"});
            $("select").attr({"disabled":"disabled"});
            /*启用自定义区域的输入框和id输入框*/
            $("#id").removeAttr("disabled");
            $("#myOptionArea").find("input").removeAttr("disabled");
            $("#myOptionArea").find("textarea").removeAttr("disabled");
        </c:if>
    });
    /**
     * 通过检查id打开相应检查记录表
     * @param id
     */
    function check(id) {
        mycreatedetailwindow("例行检查","famsWorkCheckController.do?load=detail&goUpdate&id="+id,"100%","100%");
    }
    function unionCheck(id) {
        mycreatedetailwindow("联合检查","famsWorkUnioncheckController.do?load=detail&goUpdate&id="+id,"100%","100%");
    }
    function vindicateCheck(id) {
        mycreatedetailwindow("维护抽查","famsWorkVindicatecheckController.do?load=detail&goUpdate&id="+id,"100%","100%");
    }
</script>