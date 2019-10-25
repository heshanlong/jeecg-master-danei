<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Date nowdate=new Date();
    request.setAttribute("nowdate",nowdate);
%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>例行检查表</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker,common"></t:base>
</head>
<style>
    .panel-body{
        border-style: none;
    }
    .tablebox{
        width: 100%;
        border-left: 1px solid black;
        display: flex
    }
    .boxleft{
        width: 50px;
        height: 100%;
        font-size: 30px;
        text-align: center;
    }
    .boxright{
        flex: 1;
        box-sizing: border-box
    }
    .ji{
        display: table
    }
    .zi{
        display: table-cell;
        vertical-align: middle;
        border-bottom: 1px solid black;
        border-top: 1px solid black;
    }
    table{
        width: 100%;
        height: 100%;
        border-top: none;
        border-right: none;
        border-bottom: none
    }
    h1 {
        width: 100%;
        margin: auto;
        text-align: center
    }

    h4 {
        width: 100%;
        margin: auto;
    }

    h5 {
        width: 100%;
        margin: auto;
        text-align: right
    }

    .fontSize {
        font-size: 15px;
    }


    .bottom1 {
        width: 100%;
        margin: auto;
        display: flex;
        border: 1px solid black;
    }


    .other {
        border-top: none !important;
        border-bottom: none !important;
    }
    .foot{
        width: 100%;
        margin: auto;
        display: flex;
        border: 1px solid black;
        border-top: none
    }
    .footb{
        width: 100%;
        /*height: 50px;*/
        margin: auto;
        display: flex;
        border: 1px solid black;
        border-bottom: none
    }
    .foota{
        flex: 1;
        display: flex;
        /*line-height: 50px*/
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .boxright th{
        text-align: center;
        padding: 5px 15px;
        border: 1px solid black;

    }
</style>
<body style="overflow:hidden;overflow-y:auto;">
<div class="container" style="width:100%;">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" id="formobj" action="famsWorkCheckController.do?doAdd" method="POST">
            <input type="hidden" id="btn_sub" class="btn_sub"/>
            <input type="hidden" id="id" name="id"/>
            <div style="width: 80%;margin: auto">
                <h4>编号：<t:mutiLang langKey="workCheck_number"/></h4>
                <h1>不停航作业现场例行检查登记表</h1>
                <h5>&nbsp;</h5>
                <div class="footb">
                    <div class="foota" style="border-right:1px solid black">
                        <div class="form-group" style="margin:0px;width: 100%">
                            <label for="checkTime" class="col-sm-4 control-label"><span style="color:red;">*</span>检查时间：</label>
                            <div class="col-sm-8">
                                <div class="input-group bt-content" style="width:100%">
                                    <input id="checkTime" name="checkTime" type="text" class="form-control input-sm laydate-datetime"  ignore="checked" datatype="*"  value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' type='date' value='${nowdate}'/>"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="foota">
                        <div class="form-group" style="margin:0px;width: 100%">
                            <label for="workName" class="col-sm-4 control-label">
                                <span style="color:red;">*</span>作业项目：</label>
                            <div class="col-sm-8">
                                <div class="input-group" style="width:100%">
                                    <input id="bid" name="bid" type="hidden"/>
                                    <input id="trueWorkName" name="workName" type="hidden"/>
                                    <input id="workName" type="text" class="form-control" maxlength="100" datatype="*" ignore="checked"/>
                                    <div class="input-group-btn">
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footb">
                    <div class="foota" style="border-right:1px solid black">
                        <div class="form-group" style="margin:0px;width: 100%">
                            <label for="buildDepart" class="col-sm-4 control-label"><span style="color:red;">*</span>建设单位：</label>
                            <div class="col-sm-8">
                                <div class="input-group" style="width:100%">
                                    <input id="buildDepart"  name="buildDepart" type="text" readonly="readonly" maxlength="200"
                                           class="form-control input-sm" placeholder="请输入建设单位" datatype="*" ignore="checked"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="foota">
                        <div class="form-group" style="margin:0px;width: 100%">
                            <label for="workPlace" class="col-sm-4 control-label"><span style="color:red;">*</span>作业地点：</label>
                            <div class="col-sm-8">
                                <div class="input-group" style="width:100%">
                                    <input id="workPlace"  name="workPlace" type="text" readonly="readonly" maxlength="200"
                                           class="form-control input-sm" placeholder="请输入作业地点" datatype="*" ignore="checked"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footb">
                    <div class="foota" style="border-right:1px solid black">
                        <div class="form-group" style="margin:0px;width: 100%">
                            <label for="workStartTime" class="col-sm-4 control-label"><span style="color:red;">*</span>作业队进场时间：</label>
                            <div class="col-sm-8">
                                <div class="input-group" style="width:100%">
                                    <input id="workStartTime" placeholder="请输入作业队进场时间"  name="workStartTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  autocomplete="off"    datatype="*" ignore="checked"  />
                                    <span class="Validform_checktip"></span>
                                    <label class="Validform_label" style="display: none;">作业队进场时间</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="foota">
                        <div class="form-group" style="margin:0px;width: 100%">
                            <label for="workEndTime" class="col-sm-4 control-label">作业预计结束时间：</label>
                            <div class="col-sm-8">
                                <div class="input-group" style="width:100%">
                                    <input id="workEndTime" placeholder="请输入作业预计结束时间"   name="workEndTime" type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"   autocomplete="off"   datatype="*" ignore="ignore"  />
                                    <span class="Validform_checktip"></span>
                                    <label class="Validform_label" style="display: none;">作业预计结束时间</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--循环控制--%>
                <c:forEach items="${detail.children}" var="item" varStatus="vs">
                    <c:set value="${item.children}" var="list2"/>
                    <div class="tablebox ji">
                        <div class="boxleft zi">

                            ${item.name}

                        </div>
                        <div class="boxright">
                            <table cellpadding="2" cellspacing="0" border="0">
                                <c:forEach items="${list2}" var="item2" varStatus="vs2">
                                    <c:set value="${item2.children}" var="list3"/>
                                    <tr>
                                        <th style="width: 50px">序号</th>
                                        <th class="fontSize">${item2.name}</th>
                                        <th style="width: 40px">符<br>合</th>
                                        <th style="width: 40px">不<br>符<br>合</th>
                                        <th style="width: 40px">不<br>适<br>用</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${item2.name=='车辆管理'}">
                                            <tr>
                                                <th colspan="5">
                                                    <div style="float: left;width: 25%">
                                                        <div>抽查车辆车牌号:</div>
                                                        <input id="carNumber"   name="carNumber" type="text" maxlength="200" style="width: auto"
                                                               class="form-control input-sm" placeholder="请输入抽查车辆车牌号" datatype="*" ignore="ignore"/>
                                                    </div>
                                                    <div style="float: left;width: 25%">
                                                        <div>驾驶员姓名:</div>
                                                        <input id="carDriver"   name="carDriver" type="text" maxlength="200" style="width: auto"
                                                               class="form-control input-sm" placeholder="请输入驾驶员姓名" datatype="*" ignore="ignore"/>
                                                    </div>
                                                    <div style="float: left;width: 25%">
                                                        <div>驾驶证号:</div>
                                                        <input id="driverLicense"  name="driverLicense" type="text" maxlength="200" style="width: auto"
                                                               class="form-control input-sm" placeholder="请输入驾驶证号" datatype="*" ignore="ignore"/>
                                                    </div>
                                                    <div style="float: left;width: 25%">
                                                        <div>单位:</div>
                                                        <input id="carDepart"  name="carDepart" type="text" maxlength="200" style="width: auto"
                                                               class="form-control input-sm" placeholder="请输入单位" datatype="*" ignore="ignore"/>
                                                    </div>
                                                </th>
                                            </tr>
                                            <c:forEach items="${list3}" var="item3" varStatus="vs3">
                                                <tr class="ceshi">
                                                    <th>${vs3.index+1}</th>
                                                    <th style="text-align: left">${item3.name}</th>
                                                    <th class="rd1"><input name="item[${item3.id}]" class="form-control i-checks" type="radio" value="符合" /></th>
                                                    <th class="rd1"><input name="item[${item3.id}]" class="form-control i-checks" type="radio" value="不符合" /></th>
                                                    <th class="rd1"><input name="item[${item3.id}]" class="form-control i-checks" type="radio" value="不适用" /></th>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${list3}" var="item3" varStatus="vs3">
                                                <tr>
                                                    <th>${vs3.index+1}</th>
                                                    <th style="text-align: left">${item3.name}</th>
                                                    <th><input name="item[${item3.id}]" class="form-control i-checks" datatype="*" type="radio" value="符合" checked="checked" /></th>
                                                    <th><input name="item[${item3.id}]" class="form-control i-checks" datatype="*" type="radio" value="不符合" /></th>
                                                    <th><input name="item[${item3.id}]" class="form-control i-checks" datatype="*" type="radio" value="不适用" /></th>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </c:forEach>
                <%--循环控制--%>
                <div class="bottom1 other">
                    <label for="checkResultNote" class="col-sm-2 control-label"><span style="color:red;">*</span>检查小结：</label>
                    <div class="col-sm-10 control-label">
                        <textarea name="checkResultNote" class="form-control input-sm" datatype="*" ignore="checked" rows="4" maxlength="1000"   placeholder="请输入检查小结（小于1000字）"></textarea>
                    </div>
                </div>
                <div class="bottom1">
                    <label for="checkRepairNote" class="col-sm-2 control-label">整改意见：</label>
                    <div class="col-sm-10 control-label">
                        <textarea name="checkRepairNote" class="form-control input-sm" rows="4" ignore="ignore" maxlength="1000" placeholder="请输入整改意见（小于1000字）"></textarea>
                    </div>
                </div>
                <%--<div class="foot">
                    <div class="foota" style="border-right:1px solid black">
                        <label for="aqSignature" class="col-sm-4 control-label">安全员（签字）</label>
                        <div class="col-sm-8">
                            <div class="input-group" style="width:100%">
                                <input id="aqSignature" value="安全员" name="aqSignature" type="text" maxlength="200"
                                       class="form-control input-sm" placeholder="" datatype="*" ignore="checked"/>
                            </div>
                        </div>
                    </div>
                    <div class="foota">
                        <label for="fxSignature" class="col-sm-6 control-label">飞行区管理部机坪监管部（签字）</label>
                        <div class="col-sm-6">
                            <div class="input-group" style="width:100%">
                                <input id="fxSignature" value="飞行区" name="fxSignature" type="text" maxlength="200"
                                       class="form-control input-sm" placeholder="" datatype="*" ignore="checked"/>
                            </div>
                        </div>
                    </div>
                </div>--%>
                <div class="foot">
                    <label class="col-sm-2 control-label">图片：</label>
                    <div class="col-sm-10">
                        <jsp:include page= "/webpage/common/jsp/upload.jsp">
                            <jsp:param name= "name" value= "files"/>
                            <jsp:param name= "ids" value= ""/>
                            <jsp:param name= "value" value= ""/>
                            <jsp:param name= "fileNames" value= ""/>
                            <jsp:param name= "sizes" value= ""/>
                            <jsp:param name= "directory" value= "workCheck"/>
                            <jsp:param name= "multiple" value= "multiple"/>
                            <jsp:param name= "required" value= ""/>
                            <jsp:param name= "disable" value= ""/>
                            <jsp:param name= "acceptFileType" value= "image/*"/>
                        </jsp:include>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>
<script src="plug-in/hplus/plugins/suggest/bootstrap-suggest.min.js"></script>
<script type="text/javascript">
    var resultTemp;
    //作业队进场时间，结束时间
    var startTime,endTime;
    var testBsSuggest = $("#workName").bsSuggest({
        url: "famsWorkApproveController.do?bsSuggest&workType=check&keyword="+$("#workName").val(),
        idField: "id",
        showBtn: false,
        showHeader: false,
        getDataMethod: 'data',
        allowNoKeyword: true,
        inputWarnColor: 'rgba(255,0,0,.1)', //输入框内容不是下拉列表选择时的警告色
        effectiveFields: ['number','work_name','work_area'],            //有效显示于列表中的字段，非有效字段都会过滤，默认全部。
        effectiveFieldsAlias: {'number':'编号','work_name':'项目名','work_area':'作业地点'},       //有效字段的别名对象，用于 header 的显示
        delay: 0.1,
        keyField:"work_name",  //每组数据的哪个字段作为输入框内容，优先级高于 indexKey 设置（推荐）
        indexId: 0,                     //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
        indexKey: 1                    //每组数据的第几个数据，作为input输入框的内容

    }).on('onDataRequestSuccess', function (e, result) {
        resultTemp=result;
    }).on('onSetSelectValue', function (e, keyword) {
        console.info(resultTemp);
        $("#bid").val(keyword.id);
        for(var i=0;i<resultTemp.value.length;i++){
            var tmp=resultTemp.value[i];
            if(tmp.id==keyword.id){
                $("#trueWorkName").val($("#workName").val()+"["+tmp.number+"]");
                $("#workName").val($("#workName").val()+"["+tmp.number+"]");
                $("#workPlace").val(tmp.work_area);
                $("#buildDepart").val(tmp.work_depart);
                $("#workStartTime").val(tmp.work_start_time);
                $("#workEndTime").val(tmp.work_end_time);
                startTime=tmp.work_start_time;
                endTime=tmp.work_end_time;
            }
        }
    }).on('onUnsetSelectValue', function (e) {
    });
    var subDlgIndex = '';
    $(document).ready(function() {
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
                     if(_this.id=="workStartTime" || _this.id=="workEndTime"){
                         var start = $("#workStartTime").val();
                         var end = $("#workEndTime").val();
                         if(startTime!="" && endTime!=""){
                             if(start>endTime || start<startTime){
                                 $("#workStartTime").val(startTime);
                                 showMeg("作业队进场和预计结束时间必须在作业时间区间内！");
                                 return;
                             }else if(end<startTime || end>endTime){
                                 $("#workEndTime").val(endTime);
                                 showMeg("作业队进场和预计结束时间必须在作业时间区间内！");
                                 return;
                             }
                         }
                         checkWorkTime(_this,'workStartTime','workEndTime');
                     }
                }
            });
        });
        //单选框/多选框初始化
        $('.i-checks').iCheck({
            labelHover : false,
            cursor : true,
            checkboxClass : 'icheckbox_square-green',
            radioClass : 'iradio_square-green',
            increaseArea : '20%'
        });
        //表单提交
        $("#formobj").Validform({
            tiptype:function(msg,o,cssctl){
                if(o.type==3){
                    validationMessage(o.obj,msg);
                }else{
                    removeMessage(o.obj);
                }
            },
            btnSubmit : "#btn_sub",
            btnReset : "#btn_reset",
            ajaxPost : true,
            beforeSubmit : function(curform) {
            },
            usePlugin : {
                passwordstrength : {
                    minLen : 6,
                    maxLen : 18,
                    trigger : function(obj, error) {
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
            callback : function(data) {
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



    //    判断小圆点只有一个的时候点击取消，一个都没有的时候点击只有一个生效
            $(".rd1 .iCheck-helper").addClass('aa');

            $(".aa").mousedown(function () {
               // console.log($(this).parent().hasClass('checked'))
               //  console.log($(this).parent('.ceshi').has)
                if ($(this).parent().hasClass('checked')){
                    $(this).parent().removeClass('checked')

                } else{
                    $(this).parent().addClass('checked')
                }



            })
    //     $(".aa .iCheck-helper").mousedown(function () {
    //        console.log($(this).parent().hasClass('checked'))
    //        //  console.log($(this).parent('.ceshi').has)
    //         if ($(this).parent().hasClass('checked')){
    //             $(this).parent().removeClass('checked')
    //
    //         } else{
    //             $(this).parent().addClass('checked')
    //         }
    //
    //
    //
    //     })
    });
</script>
</body>
</html>