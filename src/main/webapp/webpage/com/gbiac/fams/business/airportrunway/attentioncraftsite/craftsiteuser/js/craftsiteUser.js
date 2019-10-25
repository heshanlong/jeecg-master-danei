//==================选择用户 Top============================
 //打开popup窗口
function popupUserSelect(){
	//console.info("================");
   //console.info(windowapi);

  $.dialog({
 	 content: 'url:userController.do?userSelect', 
 	 zIndex: 9999, 
 	 title_user: '', 
 	 lock: true, 
 	 width:1060, 
 	 height:620, 
 	 opacity: 0.4,
 	 parent:windowapi,
 	 button: [
        {name:'确定',callback:callbackUserSelect_user,focus:true},
        {name:'取消',callback:function (){}}
    ]}).zindex();

 }
 
 function callbackUserSelect_user(){
    var iframe = this.iframe.contentWindow;
    var rowsData = iframe.$('#userList1').datagrid('getSelections');
    if (!rowsData || rowsData.length==0) {
        tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
        return;
    }
    
    console.info(rowsData);
    
    var names="";
    var ids="";
    for(var i=0;i<rowsData.length;i++){
        ids += rowsData[i].id+",";
        names += rowsData[i].realName+",";
    }
   
    $("#userName").val(names);
    $("#userId").val(ids);
    
    //$("#"+selectedNamesInputId_user).blur();
}
//==================选择用户 end============================

//选择机位
function popupCraftsiteList(entrance){
 	$.dialog({
			content: 'url:famsAttentionCraftsiteUserController.do?popupCraftsiteList&entrance='+entrance,
			lock : true,
			zIndex: getzIndex(),
			width:922,
			height:500,
			title:'',
			opacity : 0.3,
			cache:false,
			parent:windowapi,
			okVal: '确认',
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
		    	var saveState = iframe.saveTask();
		    	if(entrance=='add'){
		    		$("#stndCode").val(saveState);
		    	}else if(entrance=='update'){
		    		var userName = $("#userName").val();
		    		ischeckcount(userName,saveState);
		    	}
				return true;
		    },
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		});
 }
 
 //检查用户是否已关注某个机位
 function ischeckcount(userName,stndCode){
 	  $.ajax({ 
 	  		type: "POST",
 	  		url: "famsAttentionCraftsiteUserController.do?ischeckcount", 
 	  		data: {'userName':userName,'stndCode':stndCode},
 	  		dataType: "json",
 	  		success: function(data){
        		//console.info(data);
        		//console.info(data.obj);
        		if(data.obj!=0){
	    			$("#stndCode").val("");
	    			alerLayerTip("机位 ["+ stndCode + "]已关注，请勿重复关注");
	    		}else{
	    			$("#stndCode").val(stndCode);
	    		}
     		}
      });
 } 