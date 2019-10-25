<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="contextPath.jsp" %>
<%--非当前组织机构的用户列表--%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<style>
    /*表格滚动条显示齐全*/
    .datagrid-body{width: 99.5%!important;}
    /*查询选项一行显示*/
    #noCurDepartUserListForm>span>span>span{width: 80px!important;}
    .datagrid-row-selected{color: #2272CE!important;} /*选中颜色*/
</style>
<div id="main_depart_list" class="easyui-layout" fit="true">
    <div region="center"  style="padding:0px;border:0px;">
        <t:datagrid name="noCurDepartUserList" title="common.operation"
                    actionUrl="userController.do?customDatagrid" fit="true" fitColumns="true"
                    idField="id" checkbox="true" queryMode="group" pagination="true"
                    onLoadSuccess="initCheck">
            <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="common.username" sortable="false" field="userName" query="true"></t:dgCol>
            <t:dgCol title="common.real.name" field="realName" query="true"></t:dgCol>
            <t:dgCol title="common.department" sortable="false" field="userOrgList.tsDepart.departname" query="true"></t:dgCol>
            <%--<t:dgCol title="common.status" sortable="true" field="status" replace="common.active_1,common.inactive_0,super.admin_-1"></t:dgCol>--%>
        </t:datagrid>
        <!-- <div style="position: fixed;bottom:2px;right: 0;">
	    	<button id="submit" style="width:55px;line-height:26px;border-radius:3px;background:#18a689;color:#fff;" onclick="submit();">确定</button>
	    	<button style="width:55px;line-height:26px;border-radius:3px;" onclick="close()">取消</button>
    	</div>  -->
    </div>
    
    <!-- <div class="ui_buttons" style=""><input type="button" value="确定" class="ui_state_highlight"><input type="button" value="关闭"></div> -->
</div>

<div style="display: none">
        <%--<input id="userIds" name="userIds">--%>
</div>
<script>
   var ids = "${userIds}";
   var realNames = "${realNames}";
   var idArr=[],realNameArr=[];
   if (ids != '') {
       idArr = ids.split(",");
       realNameArr = realNames.split(",");
   }


    //列表加载完成后记录选中的
    function initCheck(data){
        for(var i=0;i<idArr.length;i++){
            if(idArr[i]!=""){
                $("#noCurDepartUserList").datagrid("selectRecord",idArr[i]);
            }
        }
        //单选和全选
        changeCheckbox()
    }

    function changeCheckbox() {
        //单选,选中则放入数组,否则从数组移除
        $('.datagrid-body tr').on('click',function () {
            var rowIndex = $(this).attr('datagrid-row-index');
            var id = $('.datagrid-view2 tr'+'[datagrid-row-index='+rowIndex+']').find('td[field="id"]>div').text();
            var realName = $('.datagrid-view2 tr'+'[datagrid-row-index='+rowIndex+']').find('td[field="realName"]>div').text();
            if(!$(this).hasClass('datagrid-row-selected')){ //是否选中
                idArr.push(id);
                realNameArr.push(realName);
            }else {
                for(var i=0;i<idArr.length;i++){
                    if(idArr[i] == id){
                        //从数组中移除
                        idArr.splice(i,1);
                        realNameArr.splice(i,1);
                        break;   //匹配完成中断循环
                    }
                }
            }

        });
        //全选
        $('.datagrid-header-check input[type="checkbox"]').on('click',function () {
            var checkState = $(this).prop('checked');  //选中状态
            if(checkState){ //是否选中
                var $selectedTr = $('.datagrid-view2 tr.datagrid-row-selected');
                $.each($selectedTr,function (i,item) {
                    var id =$(item).find('td[field="id"]>div').text();
                    var realName =$(item).find('td[field="realName"]>div').text();
                    idArr.push(id);
                    realNameArr.push(realName);
                });
                idArr = arrUnique(idArr);  //数组去重
                realNameArr = arrUnique(realNameArr);  //数组去重
            }else {
                //获取当前页的每个Tr
                var $Tr = $('.datagrid-view2 tr.datagrid-row');
                $.each($Tr,function (i,item) {
                    var id =$(item).find('td[field="id"]>div').text();
                    for(var j=0;j<idArr.length;j++){
                        if(idArr[j] == id){ //比较id
                            //从数组中移除
                            idArr.splice(j,1);
                            realNameArr.splice(j,1);
                            break;   //匹配完成中断循环
                        }
                    }
                })
            }
        })

    }

   //数组去重
   function arrUnique(arr){
       var res = [];
       var json = {};
       for(var i = 0; i < arr.length; i++){
           if(!json[arr[i]]){
               res.push(arr[i]);
               json[arr[i]] = 1;
           }
       }
       return res;
   }

</script>
