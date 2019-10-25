<%
    String tomorrow= Util.getNextDay(new Date(),"yyyy-MM-dd");
%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.gbiac.fams.common.Util.Util" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>作业申请</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
</head>
<body style="overflow:hidden;overflow-y:auto;">
<div class="container" style="width:100%;">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" id="formobj" action="famsWorkApproveController.do?doAdd"
              method="POST">
            <div style="text-align: center;padding-bottom: 7px;font-size: 15px">注意：请各作业单位在每天16:00之前提交第二天的作业申请</div>
            <input type="hidden" id="btn_sub" class="btn_sub"/>
            <input type="hidden" id="id" name="id"/>
            <input type="hidden" id="isFirst" name="isFirst" value="${famsWorkApprove.isFirst}"/>
            <input type="hidden" id="workNameOnly" name="workNameOnly" value="${famsWorkApprove.workNameOnly}"/>
            <div class="form-group">
                <label for="workName" class="col-sm-3 control-label"><span style="color:red;">*</span>作业项目：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="workName" name="workName" value='${famsWorkApprove.workName}' type="text" readonly="readonly" maxlength="100" class="form-control input-sm"
                               placeholder="请输入作业项目" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workDepart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业单位：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="workDepart" name="workDepart" value='${famsWorkApprove.workDepart}' type="text" readonly="readonly" maxlength="200" class="form-control input-sm"
                               placeholder="请输入作业单位" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workApproveDepart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业申报单位：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input type="text" maxlength="200"  readonly="readonly" class="form-control input-sm" value="${famsWorkApprove.workApproveDepartStr}"
                               placeholder="请输入作业申报单位" datatype="*" ignore="checked"/>
                        <input name="workApproveDepart" type="hidden"  value="${famsWorkApprove.workApproveDepart}"/>
                        <a class="easyui-linkbutton" plain="true" icon="icon-search" onclick="openDepartmentSelect(this)">选择</a>
                        <a class="easyui-linkbutton" plain="true" icon="icon-redo" onclick="callbackClean(this)">清空</a>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workArea" class="col-sm-3 control-label"><span style="color:red;">*</span>作业区域：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="workArea" name="workArea" value='${famsWorkApprove.workArea}' type="text"
                               maxlength="100" class="form-control input-sm" placeholder="请输入作业区域" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workTypeId" class="col-sm-3 control-label"><span style="color:red;">*</span>作业类型：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect id="workTypeId" readonly="readonly" field="workTypeId" type="list" extendJson="{class:'form-control input-sm'}"
                                      dictTable="fams_work_type" dictField="id" dictText="type_name" hasLabel="false"
                                      title="作业类型" datatype="*" defaultVal="${famsWorkApprove.workTypeId}"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否连续作业：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isContinueWork" type="radio" extendJson="{class:'isContinueWork i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="${famsWorkApprove.isContinueWork}" hasLabel="false" title="是否连续作业" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="dateStart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业日期：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <div class="col-sm-5" style="padding-left: 0px">
                            <input id="dateStart" placeholder="请输入作业开始日期"  name="dateStart" value='<fmt:formatDate value="${famsWorkApprove.dateStart}" pattern="yyyy-MM-dd"/>' type="text" style="height: 28px;" class="form-control laydate-date"  datatype="*" ignore="checked"  />
                        </div>
                        <div class="col-sm-2" style="text-align: center">~</div>
                        <div class="col-sm-5" style="padding-right: 0px">
                            <input id="dateEnd" placeholder="请输入作业结束日期"  name="dateEnd" value='<fmt:formatDate value="${famsWorkApprove.dateEnd}" pattern="yyyy-MM-dd"/>' type="text" style="height: 28px;" class="form-control laydate-date"  datatype="*" ignore="checked"  />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workStartTime" class="col-sm-3 control-label"><span style="color:red;">*</span>作业时间：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <div class="col-sm-5" style="padding-left: 0px">
                            <input id="workStartTime" placeholder="请输入作业队进场时间"  name="workStartTime" value="<%=tomorrow%> 00:00" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="checked"  />
                        </div>
                        <div class="col-sm-2" style="text-align: center">~</div>
                        <div class="col-sm-5" style="padding-right: 0px">
                            <input id="workEndTime" placeholder="请输入作业队结束时间"  name="workEndTime" value="<%=tomorrow%> 23:59" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="checked"  />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workAffectArea" class="col-sm-3 control-label"><span style="color:red;">*</span>作业影响区域：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <textarea name="workAffectArea" class="form-control input-sm" rows="4" maxlength="1000"
                                  datatype="*" ignore="checked" placeholder="请输入作业影响区域">${famsWorkApprove.workAffectArea}</textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">作业影响区域</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否影响机场运行：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isAffectFlight" type="radio" extendJson="{class:'isAffectFlight i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="${famsWorkApprove.isAffectFlight}" hasLabel="false" title="是否影响机场运行" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workAffectAreaMap" class="col-sm-3 control-label">影响区域地图标注：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <textarea id="workAffectAreaMap" hidden="hidden" name="workAffectAreaMap">${maps}</textarea>
                        <c:if test="${maps!=''}">
                            <a id="mapBtn" href="#" class="easyui-linkbutton" data-options="">查看标注</a>
                        </c:if>
                        <c:if test="${maps==''}">
                            <a id="mapBtn" href="#" class="easyui-linkbutton" data-options="">编辑标注</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否需要车辆避让：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isAffectCar" type="radio" extendJson="{class:'isAffectCar i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="${famsWorkApprove.isAffectCar}" hasLabel="false" title="是否需要车辆避让" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="isUseFire" class="col-sm-3 control-label"><span style="color:red;">*</span>是否动火：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isUseFire" type="radio" extendJson="{class:'isUseFire i-checks'}"
                                      typeGroupCode="sf_yn" hasLabel="false" title="是否动火" datatype="*" defaultVal="${famsWorkApprove.isUseFire}"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div id="useFireTime"  <c:if test="${famsWorkApprove.isUseFire!='Y'}">style="display: none"</c:if>>
                <div class="form-group">
                    <label for="useFireStartTime" class="col-sm-3 control-label"><span style="color:red;">*</span>动火时间：</label>
                    <div class="col-sm-7">
                        <div class="input-group" style="width:100%">
                            <div class="col-sm-5" style="padding-left: 0px">
                                <input id="useFireStartTime" placeholder="请输入动火开始时间"  name="useFireStartTime" value="<%=tomorrow%> 00:00" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="ignore"  />
                            </div>
                            <div class="col-sm-2" style="text-align: center">~</div>
                            <div class="col-sm-5" style="padding-right: 0px">
                                <input id="useFireEndTime" placeholder="请输入动火结束时间"  name="useFireEndTime" value="<%=tomorrow%> 23:59" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="ignore"  />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否高空：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isHigh" type="radio" extendJson="{class:'i-checks'}"
                                      typeGroupCode="sf_yn" hasLabel="false" title="是否动火" datatype="*" defaultVal="${famsWorkApprove.isHigh}"></t:dictSelect>
                    </div>
                </div>
            </div>
            <%--<div class="form-group">
                <label for="procinstId" class="col-sm-3 control-label">流程实例Id：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="procinstId" name="procinstId" value='${famsWorkApprove.procinstId}' type="text"
                               maxlength="36" class="form-control input-sm" placeholder="请输入流程实例Id" ignore="ignore"/>
                    </div>
                </div>
            </div>--%>
            <div class="form-group">
                <label for="workContent" class="col-sm-3 control-label"><span style="color:red;">*</span>作业内容：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <textarea name="workContent" class="form-control input-sm" rows="4" maxlength="1000"
                                  datatype="*" ignore="checked">${famsWorkApprove.workContent}</textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">作业内容</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workNote" class="col-sm-3 control-label">作业备注：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <textarea name="workNote" class="form-control input-sm" rows="4" maxlength="1000"
                                  ignore="ignore">${famsWorkApprove.workNote}</textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">作业备注</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>人员信息： </label>
                <div class="col-sm-7" id="workSafePersons">
                    <c:choose>
                        <c:when test="${fn:length(workSafes)>0}">
                            <c:forEach items="${workSafes}" var="item" varStatus="vs">
                                <div class="form-group">
                                    <input name="workSafePerson_id" type="hidden" value="${item.id}">
                                    <div class="col-sm-3">
                                        <input name="workSafePerson_role" type="text" maxlength="100" class="form-control input-sm" value="${item.personRole}" placeholder="人员职位或角色" datatype="*" ignore="ignore" />
                                    </div>
                                    <div class="col-sm-2">
                                        <input name="workSafePerson_name" type="text" maxlength="100" class="form-control input-sm" value="${item.personName}" placeholder="姓名"  datatype="*" ignore="checked" />
                                    </div>
                                    <div class="col-sm-4">
                                        <input name="workSafePerson_phone" type="text" maxlength="20" class="form-control input-sm" value="${item.personPhone}" placeholder="联系电话(手机或固话)"   datatype="m|/^0\d{2,3}-?\d{7,8}$/" ignore="checked" />
                                    </div>
                                    <div class="col-sm-4">
                                        <input name="workSafePerson_intercom" type="text" maxlength="50" class="form-control input-sm" value="${item.personIntercom}" placeholder="对讲机呼号"   datatype="*" ignore="checked" />
                                    </div>
                                    <div class="col-sm-3">
                                        <c:if test="${load==null}">
                                            <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="if($('#workSafePersons').children().length>1){$(this).parent().parent().remove()}"></a>
                                            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="if($('#workSafePersons').children().length<5){var clone=$(this).parent().parent().clone();$(clone).find('input').val('');$('#workSafePersons').append(clone);$('#workSafePersons').children('div:last-child').find('input[name=workSafePerson_id]').remove()}"></a>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <input name="workSafePerson_role" type="text" maxlength="100" class="form-control input-sm" placeholder="人员职位或角色"  datatype="*" ignore="ignore" />
                                </div>
                                <div class="col-sm-3">
                                    <input name="workSafePerson_name" type="text" maxlength="100" class="form-control input-sm" placeholder="姓名"  datatype="*" ignore="checked" />
                                </div>
                                <div class="col-sm-3">
                                    <input name="workSafePerson_phone" type="text" maxlength="20" class="form-control input-sm" placeholder="联系电话(手机或固话)"  datatype="*" ignore="checked" /><!-- m|/^0\d{2,3}-?\d{7,8}$/ -->
                                </div>
                                <div class="col-sm-3">
                                    <input name="workSafePerson_intercom" type="text" maxlength="50" class="form-control input-sm" placeholder="对讲机呼号"  datatype="*" ignore="checked" />
                                </div>
                                <div class="col-sm-3">
                                    <c:if test="${load==null}">
                                        <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="if($('#workSafePersons').children().length>1){$(this).parent().parent().remove()}"></a>
                                        <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="var clone=$(this).parent().parent().clone();$(clone).find('input').val('');$('#workSafePersons').append(clone)"></a>
                                    </c:if>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">附件：</label>
                <div class="col-sm-7">
                    <jsp:include page= "/webpage/common/jsp/upload.jsp">
                        <jsp:param name= "name" value= "files"/>
                        <jsp:param name= "ids" value= "${files.ids}"/>
                        <jsp:param name= "value" value= "${files.paths}"/>
                        <jsp:param name= "fileNames" value= "${files.fileNames}"/>
                        <jsp:param name= "sizes" value= "${files.sizes}"/>
                        <jsp:param name= "directory" value= "work"/>
                        <jsp:param name= "multiple" value= "multiple"/>
                        <jsp:param name= "required" value= ""/>
                        <jsp:param name= "disable" value= ""/>
                        <jsp:param name= "message" value= "(控制程序或动火证)"/>
                        <jsp:param name="types" value="${files.types}" />
                    </jsp:include>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var subDlgIndex = '';
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
                    if(_this.id=="workEndTime"){
                    	checkEndAndStartTimeIfOneDay(_this,"workStartTime","workEndTime");
                    }
                }
            });
        });
        $(".laydate-date").each(function(){
    		var _this = this;
    		laydate.render({
    		  elem: this,
    		  format: 'yyyy-MM-dd',
    		  type: 'date',
    		  ready: function(date){
                  $(_this).val(DateJsonFormat(date,this.format));
              },
              done: function(value, date, endDate){
                  if(_this.id=="dateStart"){
                  	checkStartDate(_this,'dateStart');
                  }
                  if(_this.id=="dateEnd"){
                  	checkEndDate(_this,'dateStart','dateEnd');
                  }
    		  }
    		});
    	});
        //给作业类型绑定onchange事件
        $("#workTypeId").change(function(obj){
            var id=$(this).val();
            var text=$(this).find("option:selected").text();
            if(text=="不停航作业 "){
                $("#workSafePersons").find("input[name='workSafePerson_role']").attr("ignore","checked");
                $("#files").attr("datatype","*");
            }else{
                $("#workSafePersons").find("input[name='workSafePerson_role']").attr("ignore","ignore");
                $("#files").removeAttr("datatype");
                //非不停航作业不能为连续作业
                $(".isContinueWork").iCheck("uncheck");
                $(".isContinueWork[value='N']").iCheck("check");
            }
        });
        $('.isContinueWork').on('ifChecked', function(event){
            var value=event.target.defaultValue;
            if(value=="Y"){//若为连续作业，则必须为不停航作业
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
        //表单提交
        $("#formobj").Validform({
            tiptype: function (msg, o, cssctl) {
                if (o.type == 3) {
                    validationMessage(o.obj, msg);
                } else {
                    removeMessage(o.obj);
                }
            },
            btnSubmit: "#btn_sub",
            btnReset: "#btn_reset",
            ajaxPost: true,
            beforeSubmit: function (curform) {
            },
            usePlugin: {
                passwordstrength: {
                    minLen: 6,
                    maxLen: 18,
                    trigger: function (obj, error) {
                        if (error) {
                            obj.parent().next().find(".Validform_checktip").show();
                            obj.find(".passwordStrength").hide();
                        } else {
                            $(".passwordStrength").show();
                            obj.parent().next().find(".Validform_checktip").hide();
                        }
                    }
                }
            },
            callback: function (data) {
                var win = frameElement.api.opener;
                if (data.success == true) {
                    frameElement.api.close();
                    win.reloadTable();
                    win.tip(data.msg);
                } else {
                    if (data.responseText == '' || data.responseText == undefined) {
                        $.messager.alert('错误', data.msg);
                        $.Hidemsg();
                    } else {
                        try {
                            var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
                            $.messager.alert('错误', emsg);
                            $.Hidemsg();
                        } catch (ex) {
                            $.messager.alert('错误', data.responseText + "");
                            $.Hidemsg();
                        }
                    }
                    return false;
                }
            }
        });
    });
    $('#mapBtn').on('click',function(){
        localStorage.setItem("isMark","1");
        var  str=localStorage.setItem("mapPostion",$('#workAffectAreaMap').html());
        $.dialog({
            content: 'url:webpage/com/gbiac/fams/business/construction/workapprove/superMap/page/map.html',
            zIndex: getzIndex(),
            lock : true,
            width:window.top.document.body.offsetWidth,
            height: window.top.document.body.offsetHeight-100,
            title:'点击标注',
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
</script>
</body>
</html>