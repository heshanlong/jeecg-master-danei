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

            <div class="form-group">
                <label for="workName" class="col-sm-3 control-label"><span style="color:red;">*</span>作业项目：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="workName" name="workName" type="text" maxlength="100" class="form-control input-sm"
                               placeholder="请输入作业项目" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workDepart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业单位：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="workDepart" name="workDepart" type="text" maxlength="200" class="form-control input-sm"
                               placeholder="请输入作业单位" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workApproveDepart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业申报单位：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input type="text" maxlength="200" readonly="readonly" class="form-control input-sm" value="${famsWorkApprove.workApproveDepartStr}"
                               placeholder="请输入作业申报单位" datatype="*" ignore="checked"/>
                        <input name="workApproveDepart" type="hidden" value="${famsWorkApprove.workApproveDepart}"/>
                        <a class="easyui-linkbutton" plain="true" icon="icon-search" onClick="openDepartmentSelect(this)">选择</a>
                        <a class="easyui-linkbutton" plain="true" icon="icon-redo" onClick="callbackClean(this)">清空</a>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workArea" class="col-sm-3 control-label"><span style="color:red;">*</span>作业区域：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <textarea id="workArea" name="workArea" class="form-control input-sm" rows="2" maxlength="250"
                               placeholder="请输入作业区域" datatype="*" ignore="checked"></textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>作业类型：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect id="workTypeId" field="workTypeId" type="list" extendJson="{class:'form-control input-sm'}"
                                      dictTable="fams_work_type" dictField="id" dictText="type_name" hasLabel="false" defaultVal="8a0790e9684a3d5301684a6521d90003"
                                      title="作业类型" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否连续作业：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isContinueWork" type="radio" extendJson="{class:'isContinueWork i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="N" hasLabel="false" title="是否连续作业" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>作业日期：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <div class="col-sm-5" style="padding-left: 0px">
                            <input id="dateStart" placeholder="请输入作业开始日期" value="<%=tomorrow%>" name="dateStart" type="text" style="height: 28px;" class="form-control laydate-date"  datatype="*" ignore="checked"  />
                        </div>
                        <div class="col-sm-2" style="text-align: center">~</div>
                        <div class="col-sm-5" style="padding-right: 0px">
                            <input id="dateEnd" placeholder="请输入作业结束日期"  value="<%=tomorrow%>" name="dateEnd" type="text" style="height: 28px;" class="form-control laydate-date"  datatype="*" ignore="checked"  />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>作业时间：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <div class="col-sm-5" style="padding-left: 0px">
                            <input id="workStartTime" placeholder="请输入作业进场时间" value="<%=tomorrow%> 00:00"  name="workStartTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="checked"  />
                        </div>
                        <div class="col-sm-2" style="text-align: center">~</div>
                        <div class="col-sm-5" style="padding-right: 0px">
                            <input id="workEndTime" placeholder="请输入作业结束时间"  value="<%=tomorrow%> 23:59" name="workEndTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="checked"  />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workAffectArea" class="col-sm-3 control-label"><span style="color:red;">*</span>作业影响区域：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
	                    <textarea name="workAffectArea" class="form-control input-sm" rows="4" maxlength="1000" placeholder="请输入作业影响区域" datatype="*" ignore="checked"></textarea>
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
                                      typeGroupCode="sf_yn" defaultVal="N" hasLabel="false" title="是否影响机场运行" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workAffectAreaMap" class="col-sm-3 control-label">影响区域地图标注：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <input id="workAffectAreaMap" name="workAffectAreaMap" type="hidden"/>
                        <a id="mapBtn" class="easyui-linkbutton"  >点击标注</a>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否需要车辆避让：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isAffectCar" type="radio" extendJson="{class:'isAffectCar i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="N" hasLabel="false" title="是否需要车辆避让" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否动火：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isUseFire" type="radio" extendJson="{class:'isUseFire i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="N" hasLabel="false" title="是否动火" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div id="useFireTime" style="display: none">
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span style="color:red;">*</span>动火时间：</label>
                    <div class="col-sm-7">
                        <div class="input-group" style="width:100%">
                            <div class="col-sm-5" style="padding-left: 0px">
                                <input id="useFireStartTime" placeholder="请输入动火开始时间"  value="<%=tomorrow%> 00:00" name="useFireStartTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="ignore"  />
                            </div>
                            <div class="col-sm-2" style="text-align: center">~</div>
                            <div class="col-sm-5" style="padding-right: 0px">
                                <input id="useFireEndTime" placeholder="请输入动火结束时间"  value="<%=tomorrow%> 23:59" name="useFireEndTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="ignore"  />
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
                                      typeGroupCode="sf_yn" defaultVal="N" hasLabel="false" title="是否动火" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>作业内容：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <textarea name="workContent" class="form-control input-sm" rows="4" maxlength="1000" placeholder="请输入小于1000字的信息" datatype="*" ignore="checked"></textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">作业内容</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">作业备注：</label>
                <div class="col-sm-7">
                    <div class="input-group" style="width:100%">
                        <textarea name="workNote" class="form-control input-sm" rows="4" maxlength="1000" placeholder="请输入小于1000字的信息" ignore="ignore"></textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">作业备注</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>人员信息：</label>
                <div class="col-sm-7" id="workSafePersons">
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
                            <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onClick="if($('#workSafePersons').children().length>1){$(this).parent().parent().remove()}"></a>
                            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="if($('#workSafePersons').children().length<5){var clone=$(this).parent().parent().clone();$(clone).find('input').val('');$('#workSafePersons').append(clone)}"></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">附件：</label>
                <div class="col-sm-7">
                    <jsp:include page= "/webpage/common/jsp/upload.jsp">
                        <jsp:param name= "name" value= "files"/>
                        <jsp:param name= "ids" value= ""/>
                        <jsp:param name= "value" value= ""/>
                        <jsp:param name= "fileNames" value= ""/>
                        <jsp:param name= "sizes" value= ""/>
                        <jsp:param name= "directory" value= "work"/>
                        <jsp:param name= "multiple" value= "multiple"/>
                        <jsp:param name= "required" value= ""/>
                        <jsp:param name= "disable" value= ""/>
                        <jsp:param name= "message" value= "(控制程序或动火证)"/>
                        <jsp:param name= "acceptFileType" value= ".doc,.docx,.ppt,.pptx,.xlsx,.xlsm,.xltx,.xltm,.xlsb,.xlam,.pdf,.bmp,.pct,.png,.jpeg,.gif,.jpg,.ico"/>
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
            increaseArea: '20%',
            checked:function (a) {
                alert(a);
            },
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
			beforeCheck:function(curform){
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
        var  str=localStorage.setItem("mapPostion",$('#workAffectAreaMap').val());
        $.dialog({
            content: 'url:webpage/com/gbiac/fams/business/construction/workapprove/superMap/page/map.html',
            zIndex: getzIndex(),
            lock : true,
            width:window.top.document.body.offsetWidth,
            height: window.top.document.body.offsetHeight-100,
            title:'标注地图',
            opacity : 0.3,
            cache:false,
            button: [{
                name: '确定',
                callback: function(){
                    var  str=localStorage.getItem("mapPostion");
                    $('#workAffectAreaMap').val(str);
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