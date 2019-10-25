 $(function(){
		  
		//禁用Enter键表单自动提交
        document.onkeydown = function(event) {
            var target, code, tag;
            if (!event) {
                event = window.event; //针对ie浏览器
                target = event.srcElement;
                code = event.keyCode;
                if (code == 13) {
                    tag = target.tagName;
                    if (tag == "TEXTAREA") { return true; }
                    else { return false; }
                }
            }
            else {
                target = event.target; //针对遵循w3c标准的浏览器，如Firefox
                code = event.keyCode;
                if (code == 13) {
                    tag = target.tagName;
                    if (tag == "INPUT") { return false; }
                    else { return true; }
                }
            }
        };

 });
 
 
 
// 验证是否重名
function onkeyupcc(rulesName1){
  	 var pid = $("#pid").val();
  	 var rulesName2 = $("#rulesName").val();
  	 var rulesType = $("#rulesType").val();
  	 
  	 //如果编辑的 与 传过来的是一样，那就不用在去验证了
  	 if(rulesName1 == rulesName2){
  	 	return;
  	 }
  	 
  	 $.ajax({ 
 	  		type: "POST",
 	  		url: "famsRulesregulationsController.do?ifNameExist", 
 	  		data: {'pid':pid,'rulesName':rulesName2,'rulesType':rulesType},
 	  		dataType: "json",
 	  		success: function(data){
        		console.info(data);
        		console.info(data.obj);
        		if(data.obj!=0){
	    			$("#rulesName").val("");
	    			alerLayerTip("名称 ["+ rulesName2 + "]已存在");
	    		}
     		}
      });
  }
  
  
function validateValue(textbox) {
	var IllegalString = "[`~!#$^&*=|{}':;',\\[\\].<>/?~！#￥……&*——|{}【】‘；：”“'。，、？ 　]‘'";
	var textboxvalue = textbox.value;
	var index = textboxvalue.length - 1;
	var s = textbox.value.charAt(index);
	if (IllegalString.indexOf(s) >= 0) {
		s = textboxvalue.substring(0, index);
		textbox.value = s;
	}
}
  
  
  
  
  
  
  