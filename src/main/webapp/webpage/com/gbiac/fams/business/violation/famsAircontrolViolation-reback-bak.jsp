<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>违章告知单</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <t:base type="bootstrap,bootstrap-table,layer,validform,bootstrap-form"></t:base>
    <style>
        .panel-body{
            border-style: none;
        }
    </style>
</head>
<body style="overflow:hidden;overflow-y:auto;">
<div class="container" style="width:100%;">
    <div class="panel-heading"></div>
    <div class="panel-body">
        <form class="form-horizontal" role="form" id="formobj" action="famsAircontrolViolationController.do?doReback" method="POST">

            <input type="hidden" id="btn_sub" class="btn_sub"/>
            <input type="hidden" id="id" name="id" value="${famsAircontrolViolation.id}"/>

            <div class="bt-item col-md-6 col-sm-6">
                <div class="row">
                    <div class="col-md-3 col-sm-3 col-xs-3 bt-label">
                        请填写撤回理由：
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9 bt-content">
                        <textarea placeholder="请输入撤回理由(小于1000字)" maxlength="1000" name="remark" class="form-control input-sm" rows="6" ignore="checked" datatype="*" >${famsAircontrolViolation.remark}</textarea>
                        <span class="Validform_checktip" style="float:left;height:0px;"></span>
                        <label class="Validform_label" style="display: none">撤回理由</label>
                    </div>
                </div>
            </div>

    </div>
    </form>
</div>
</div>
<script type="text/javascript">
    var subDlgIndex = '';
    $(document).ready(function() {

        $(".laydate-datetime").each(function(){
            var _this = this;
            laydate.render({
                elem: this,
                format: 'yyyy-MM-dd HH:mm:ss',
                type: 'datetime'
            });
        });
        $(".laydate-date").each(function(){
            var _this = this;
            laydate.render({
                elem: this
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

    });
    /**
     * 选择组织机构
     */
    function openDepartmentSelect() {
        $.dialog.setting.zIndex = getzIndex();
        var orgIds = $("#dutyId").val();

        $.dialog({
            content: 'url:departController.do?departSelect&orgIds=' + orgIds,
            zIndex: getzIndex(),
            title: '组织机构列表',
            lock: true,
            width: '400px',
            height: '350px',
            opacity: 0.4,
            button: [{
                name: '<t:mutiLang langKey="common.confirm"/>',
                callback: callbackDepartmentSelect,
                focus: true
            },
                {
                    name: '<t:mutiLang langKey="common.cancel"/>',
                    callback: function() {}
                }]
        }).zindex();

    }

    function callbackDepartmentSelect() {
        var iframe = this.iframe.contentWindow;
        var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
        var nodes = treeObj.getCheckedNodes(true);
        if(nodes.length>1){
            alert("仅可选择一个单位");
        }
        if(nodes.length>0){
            var ids='',names='';
            for(i=0;i<nodes.length;i++){
                var node = nodes[i];
                ids += node.id;
                names += node.name;
            }
            $('#dutyCompany').val(names);
            $('#dutyCompany').blur();
            //$('#orgIds').val(ids);
            $('#dutyId').val(ids);

        }
    }

    function callbackClean(){
        $('#dutyCompany').val('');
        $('#dutyId').val('');
    }

    function setOrgIds() {
        return true;
    }
    $(function(){
        $("#dutyCompany").prev().hide();
    });
</script>
</body>
</html>