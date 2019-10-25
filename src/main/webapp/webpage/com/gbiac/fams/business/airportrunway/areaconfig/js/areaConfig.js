function popupCraftsiteList(entrance){
	$.dialog({
	 	 content: 'url:famsAreaConfigController.do?popupCraftsiteList&entrance='+entrance,
	 	 zIndex: 9999, 
	 	 title_user: '', 
	 	 lock: true, 
	 	 width:1060, 
	 	 height:620, 
	 	 opacity: 0.4,
	 	 parent:windowapi,
	 	 button: [
	        {name:'确定',callback:callbackUserCraftsite,focus:true},
	        {name:'取消',callback:function (){}}
	    ]}).zindex();
}

function callbackUserCraftsite(){
    var iframe = this.iframe.contentWindow;
    var saveState = iframe.saveTask();
    $("#craftsite").val(saveState);
    $("#checkCraftsite").val();

}
