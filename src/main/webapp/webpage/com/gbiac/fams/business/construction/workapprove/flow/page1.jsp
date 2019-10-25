<%
    String load=request.getParameter("load");
    request.setAttribute("load",load);
%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>作业申请</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form,jquery,easyui,DatePicker"></t:base>
    <link rel="stylesheet" href="webpage/common/css/layui.css">
    <style>
        .panel-body{
            border-style: none;
            display: flex;
        }
        .div-right{
            flex: 1;
        }
        .form-horizontal {
            flex: 1;
        }
        .layui-p-time{
            display: flex;
        }
        .layui-p-time>div:nth-child(1){
            width: 75%;
        }
        .layui-p-time>div:nth-child(2){

        }
        .line{
            height: 1px;
            width: 90%;
            background-color: grey;
            margin-left: 25px;
            margin-top: 15px;
        }
        .line2{
            height: 1px;
            width: 200%;
            /*background-color: grey;*/
            background-color: #ccc;
            margin: 10px 0;
        }
        a{
            color: #337ab7;!important;
        }
        .icrle{
            border: 1px solid #5FB878;
            background-color:#5FB878;
            font-style: normal;
        }
        .ellipsis {
            position: relative;
            max-height: 44px;
            line-height: 22px;
            overflow: hidden;
        }
        .ellipsis:hover {
            max-height: 99999px;
        }
        .ellipsis-container {
            position: relative;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            font-size: 50px;
            color: transparent;
        }
        .ellipsis-content {
            color: #000;
            display: inline;
            vertical-align: top;
            font-size: 12px;
            word-break: break-all;
            word-wrap: break-word;
        }
        .ellipsis-ghost {
            position:absolute;
            z-index: 1;
            top: 0;
            left: 50%;
            width: 100%;
            height: 100%;
            color: #000;
        }
        .ellipsis-ghost:before {
            content: "";
            display: block;
            float: right;
            width: 50%;
            height: 100%;
        }
        .ellipsis:hover .ellipsis-ghost {
            display: none;
        }
        .ellipsis-placeholder {
            content: "";
            display: block;
            float: right;
            width: 50%;
            height: 44px;
        }
        .ellipsis-more {
            position: relative;
            float: right;
            font-size: 12px;
            width: 50px;
            height: 22px;
            margin-top: -22px;
            background-color: #fff;
        }
    </style>
</head>
<body style="overflow:hidden;overflow-y:auto;">
<div class="container" style="width:100%;">
    <div class="panel-heading"></div>
    <div class="panel-body">

        <form class="form-horizontal" role="form" id="formobj" action="famsWorkController.do?${funname}"
              method="POST">
            <input type="hidden" id="btn_sub" class="btn_sub"/>
            <input type="hidden" id="id" name="id" value="${famsWorkApprove.id}"/>

            <div class="form-group">
                <label for="number" class="col-sm-3 control-label">编号：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <input id="number" name="number" value='${famsWorkApprove.number}' readonly="readonly" type="text" maxlength="50" class="form-control input-sm" placeholder="请输入编号"  ignore="ignore" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workName" class="col-sm-3 control-label"><span style="color:red;">*</span>作业项目：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <input id="workName" name="workName" value='${famsWorkApprove.workName}' type="text" maxlength="100" class="form-control input-sm"
                               placeholder="请输入作业项目" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workDepart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业单位：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <input id="workDepart" name="workDepart" value='${famsWorkApprove.workDepart}' type="text" maxlength="200" class="form-control input-sm"
                               placeholder="请输入作业单位" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workApproveDepart" class="col-sm-3 control-label"><span style="color:red;">*</span>作业申报单位：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <input type="text" maxlength="200" value="${tsDepart.departname}" readonly="readonly" class="form-control input-sm"
                               placeholder="请输入作业申报单位" datatype="*" ignore="checked"/>
                        <input name="workApproveDepart" type="hidden"  value="${famsWorkApprove.workApproveDepart}"/>
                        <c:if test="${load==null}">
                            <a class="easyui-linkbutton" plain="true" icon="icon-search" onclick="openDepartmentSelect(this)">选择</a>
                            <a class="easyui-linkbutton" plain="true" icon="icon-redo" onclick="callbackClean(this)">清空</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workArea" class="col-sm-3 control-label"><span style="color:red;">*</span>作业区域：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <input id="workArea" name="workArea" value='${famsWorkApprove.workArea}' type="text"
                               maxlength="100" class="form-control input-sm" placeholder="请输入作业区域" datatype="*" ignore="checked"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workTypeId" class="col-sm-3 control-label"><span style="color:red;">*</span>作业类型：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect id="workTypeId" field="workTypeId" type="list" extendJson="{class:'form-control input-sm'}"
                                      dictTable="fams_work_type" dictField="id" dictText="type_name" hasLabel="false"
                                      title="作业类型" datatype="*" defaultVal="${famsWorkApprove.workTypeId}"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否连续作业：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isContinueWork" type="radio" extendJson="{class:'isContinueWork i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="${famsWorkApprove.isContinueWork}" hasLabel="false" title="是否连续作业" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workStartTime" class="col-sm-3 control-label"><span style="color:red;">*</span>作业日期：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <div class="col-sm-5" style="padding-left: 0px">
                            <input id="dateStart" placeholder="请输入作业开始日期"  name="dateStart" value='<fmt:formatDate value="${famsWorkApprove.dateStart}" pattern="yyyy-MM-dd"/>' type="text" style="height: 28px;" class="form-control laydate-date"  datatype="*" ignore="checked"  />
                        </div>
                        <div class="col-sm-2" style="text-align: center">~</div>
                        <div class="col-sm-5" style="padding-right: 0px">
                            <input id="dateENd" placeholder="请输入作业结束日期"  name="dateEnd" value='<fmt:formatDate value="${famsWorkApprove.dateEnd}" pattern="yyyy-MM-dd"/>' type="text" style="height: 28px;" class="form-control laydate-date"  datatype="*" ignore="checked"  />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workStartTime" class="col-sm-3 control-label"><span style="color:red;">*</span>作业时间：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <div class="col-sm-5" style="padding-left: 0px">
                            <input id="workStartTime" placeholder="请输入作业队进场时间"  name="workStartTime" value='<fmt:formatDate value="${famsWorkApprove.workStartTime}" pattern="yyyy-MM-dd HH:mm"/>' type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="checked"  />
                        </div>
                        <div class="col-sm-2" style="text-align: center">~</div>
                        <div class="col-sm-5" style="padding-right: 0px">
                            <input id="workEndTime" placeholder="请输入作业队结束时间"  name="workEndTime" value='<fmt:formatDate value="${famsWorkApprove.workEndTime}" pattern="yyyy-MM-dd HH:mm"/>' type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="checked"  />
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
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isAffectFlight" type="radio" extendJson="{class:'isAffectFlight i-checks'}"
                                      typeGroupCode="sf_yn" defaultVal="${famsWorkApprove.isAffectFlight}" hasLabel="false" title="是否影响机场运行" datatype="*"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="workAffectAreaMap" class="col-sm-3 control-label">影响区域地图标注：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <textarea id="workAffectAreaMap"  name="workAffectAreaMap" hidden="hidden" >${maps}</textarea>
                        <c:if test="${maps!=''}">
                            <a id="mapBtn" href="#" class="easyui-linkbutton" data-options="">查看标注</a>
                        </c:if>
                        <c:if test="${maps==''}">
                            无
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
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <t:dictSelect field="isUseFire" type="radio" extendJson="{class:'isUseFire i-checks'}"
                                      typeGroupCode="sf_yn" hasLabel="false" title="是否动火" datatype="*" defaultVal="${famsWorkApprove.isUseFire}"></t:dictSelect>
                    </div>
                </div>
            </div>
            <div id="useFireTime"  <c:if test="${famsWorkApprove.isUseFire!='Y'}">style="display: none"</c:if>>
                <div class="form-group">
                    <label for="workStartTime" class="col-sm-3 control-label"><span style="color:red;">*</span>动火时间：</label>
                    <div class="col-sm-8">
                        <div class="input-group" style="width:100%">
                            <div class="col-sm-5" style="padding-left: 0px">
                                <input id="useFireStartTime" placeholder="请输入动火开始时间"  name="useFireStartTime" value='<fmt:formatDate value="${famsWorkApprove.useFireStartTime}" pattern="yyyy-MM-dd HH:mm"/>' type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="ignore"  />
                            </div>
                            <div class="col-sm-2" style="text-align: center">~</div>
                            <div class="col-sm-5" style="padding-right: 0px">
                                <input id="useFireEndTime" placeholder="请输入动火结束时间"  name="useFireEndTime" value='<fmt:formatDate value="${famsWorkApprove.useFireEndTime}" pattern="yyyy-MM-dd HH:mm"/>' type="text" style="height: 28px;" class="form-control laydate-datetime Wdate"  datatype="*" ignore="ignore"  />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label"><span style="color:red;">*</span>是否高空：</label>
                <div class="col-sm-8">
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
                <div class="col-sm-8">
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
                <div class="col-sm-8">
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
                <div class="col-sm-8" id="workSafePersons">
                    <c:choose>
                        <c:when test="${fn:length(workSafes)>0}">
                            <c:forEach items="${workSafes}" var="item" varStatus="vs">
                                <div class="form-group">
                                    <input name="workSafePerson_id" type="hidden" value="${item.id}">
                                    <div class="col-sm-3">
                                        <input name="workSafePerson_role" type="text" maxlength="100" class="form-control input-sm" value="${item.personRole}" placeholder="人员职位或角色"  datatype="*" ignore="ignore" />
                                    </div>
                                    <div class="col-sm-2">
                                        <input name="workSafePerson_name" type="text" maxlength="100" class="form-control input-sm" value="${item.personName}" placeholder="姓名"  datatype="*" ignore="checked" />
                                    </div>
                                    <div class="col-sm-4">
                                        <input name="workSafePerson_phone" type="text" maxlength="20" class="form-control input-sm" value="${item.personPhone}" placeholder="联系电话(手机或固话)"  datatype="*" ignore="checked" />
                                    </div>
                                    <div class="col-sm-4">
                                        <input name="workSafePerson_intercom" type="text" maxlength="50" class="form-control input-sm" value="${item.personIntercom}" placeholder="对讲机呼号"  datatype="*" ignore="checked" />
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
                                    <input name="workSafePerson_phone" type="text" maxlength="20" class="form-control input-sm" placeholder="联系电话(手机或固话)"  datatype="*" ignore="checked" />
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
            <c:if test="${funname!='y_approve_apply_fun' && funname!='s_approve_fun' && funname!='s_apply_fun'}">
            <div class="form-group">
                <label for="workAffectAreaMap" class="col-sm-3 control-label"><span style="color:red;">*</span>审批类型：</label>
                <div class="col-sm-8">
                    <div class="input-group" style="width:100%">
                        <c:choose>
                            <c:when test="${famsWorkApprove.applyType==null or famsWorkApprove.applyType==''}">
                                无
                            </c:when>
                            <c:otherwise>
                                <t:dictSelect field="applyType_bak" type="radio" extendJson="{class:'i-checks'}"
                                              typeGroupCode="apply_type" hasLabel="false" defaultVal="${famsWorkApprove.applyType}" title="报备类型"
                                              datatype="*"></t:dictSelect>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            </c:if>
            <div class="form-group">
                <label class="col-sm-3 control-label">附件：</label>
                <div class="col-sm-8">
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
            <div class="form-group">
                <div class="line2"></div>
            </div>
            <div id="myOptionArea">