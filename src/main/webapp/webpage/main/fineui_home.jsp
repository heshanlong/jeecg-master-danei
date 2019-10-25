<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>Jeecg 微云快速开发平台</title>
    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="plug-in/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in/hplus/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="plug-in/themes/fineui/main/iconfont.css">
	<script src="plug-in/laydate/laydate.js"></script> 
    <style type="text/css">
	.gray-bg{
		background-color: #e9ecf3;
	}
	.col-sm-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}
	.p-lg{
		padding:0px 0px 10px 0px;
	}
	.widget{
		margin-top: 0px;
	}
	.iconfont{
		font-size: 30px;
		color: white;
	}
	h2 {
        font-size: 19px;
    }
    .echart_div{
    	height:240px;width:100%;
    }
	.ibtn{
		cursor: pointer;
	}
	.flot-chart{
		height:400px;
	}
   /*  .top-navigation .wrapper.wrapper-content{padding:20px 5px !important;}
	.container {
    	 width:99% !important; margin:10px;
    	 padding-right: 1px !important;
    	 padding-left: 1px !important;
	}
	.color_red{color:#e55555;}
	.col-cs-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}*/
	
	@media (min-width: 992px){
		.col-cs-2 {
		    width: 11.11%;
			padding-left: 5px;
			padding-right: 5px;
			float: left;
		}
	}
    .home-list li{
        list-style: none;
        line-height: 200%;
        position: relative;
        background-color: transparent;
        list-style: outside none none;
        margin: 0;
        padding: 7px 0;
        padding-left: 22px;
        height: 40px;
        overflow:hidden;white-space:nowrap;text-overflow:ellipsis
    }
    .home-list li:before,.home-list li:after{
        content: "\f142";
        top: 14px;
        left: 14px;
        position: absolute;
        font: normal normal normal 14px/1 FontAwesome;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        z-index: 99;
        color: #99abb4;
    }
    .home-list li:after{
        left: 10px;
    }
    .home-table.bootstrap-table .table {
        border-bottom: none;
    }
    .home-table .th-inner{
        vertical-align: top;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
     .home-none{
        text-align: center;
        line-height: 100px;

    }



    </style>
</head>
 <body class="gray-bg">
        <div class="wrapper wrapper-content">
            <input type="hidden"  id="orgId" value="${userOrg}">
            <!--
			<div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>航班动态</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content" >
                            <div class="flot-chart-content" style="height: 300px;" id="chart11"></div>

                        </div>
                    </div>
                </div>


			</div>
            -->

            <!--异常事件-->
            <div class="row"  id="errorPage" style="display: none">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>异常事件</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="row">
                                <div class="col-sm-5">
                                    <div style="height: 300px;width: 500px" id="eventchart"></div>
                                </div>
                                <div class="col-sm-7">

                                        <ul class="home-list" id="eventlist">
                                            <%--<li><a href="javascript:void(0)" onclick="openWin()">12-15 14:25  进港航班CZ3389报轮胎扎伤</a></li>--%>
                                            <%--<li><a href="#">12-15 11:25  进港航班CA63589报疑似鸟击</a></li>--%>
                                            <%--<li><a href="#">12-15 14:25  215机位飞机漏油</a></li>--%>
                                            <%--<li><a href="#">12-15 14:25  进港航班CZ3389报轮胎扎伤</a></li>--%>
                                            <%--<li><a href="#">12-15 11:25  进港航班CA63589报疑似鸟击</a></li>--%>
                                        </ul>
                                    <div class="home-none" id="eventlistnone" style="display:none">
                                        近期没有异常事件
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

				
			</div>

            <!--施工信息-->
            <div class="row"  id="constract"  style="display: none">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>施工信息</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="row">
                                <div class="col-sm-12">
                                        <div class="home-table bootstrap-table">
                                            <div class="home-none" id="homeNone" hidden="hidden">当前没有施工信息</div>

                                                <div class="fixed-table-body" id="workTableHas"  hidden="hidden">
                                                <table class="table table-hover table-striped">
                                                    <thead>
                                                    <tr><th><div class="th-inner ">编号</div></th><th><div class="th-inner ">施工项目</div></th><th><div class="th-inner ">施工单位</div></th><th><div class="th-inner ">联系人</div></th><th><div class="th-inner ">施工区域</div></th><th><div class="th-inner ">影响区域</div></th><th><div class="th-inner ">起止时间</div></th></tr>
                                                    </thead>
                                                    <tbody id="workTable">
                                                     </tbody>
                                                </table>
                                                </div>

                                        </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

            <div class="row"  id="contractPage"  style="display: none">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>施工动态</h5>
                            <div class="ibox-tools">
                                <a style="color:#337ab7" onClick="openTag('workapprove')">更多>>></a>
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="row">

                                <div class="col-sm-12">

                                    <ul class="home-list" id="workapprove">

                                    </ul>
                                    <div class="home-none" id="eventlistWorknone" style="display:none">
                                        近期没有施工动态
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>





            </div>
            <%--整改单动态--%>
            <div class="row"  id="reform"  style="display: none">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>整改单动态</h5>
                            <div class="ibox-tools">
                                <a style="color:#337ab7" onClick="openTag('reform')">更多>>></a>
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="row">

                                <div class="col-sm-12">

                                    <ul class="home-list" id="apronreform">
                                        <%--<li><a href="javascript:void(0)" onclick="openWin()">12-15 14:25  进港航班CZ3389报轮胎扎伤</a></li>--%>
                                        <%--<li><a href="#">12-15 11:25  进港航班CA63589报疑似鸟击</a></li>--%>
                                        <%--<li><a href="#">12-15 14:25  215机位飞机漏油</a></li>--%>
                                        <%--<li><a href="#">12-15 14:25  进港航班CZ3389报轮胎扎伤</a></li>--%>
                                        <%--<li><a href="#">12-15 11:25  进港航班CA63589报疑似鸟击</a></li>--%>
                                    </ul>
                                    <div class="home-none" id="apronreformnone" style="display:none">
                                        近期没有整改单动态
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>





            </div>

            <%--违章告知单动态--%>
            <div class="row"  id="violate"  style="display: none">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>违章告知单动态</h5>

                            <div class="ibox-tools">
                                <a style="color:#337ab7" onClick="openTag('violation')">更多>>></a>
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <div class="row">

                                <div class="col-sm-12">

                                    <ul class="home-list" id="apronviolate">
                                        <%--<li><a href="javascript:void(0)" onclick="openWin()">12-15 14:25  进港航班CZ3389报轮胎扎伤</a></li>--%>
                                        <%--<li><a href="#">12-15 11:25  进港航班CA63589报疑似鸟击</a></li>--%>
                                        <%--<li><a href="#">12-15 14:25  215机位飞机漏油</a></li>--%>
                                        <%--<li><a href="#">12-15 14:25  进港航班CZ3389报轮胎扎伤</a></li>--%>
                                        <%--<li><a href="#">12-15 11:25  进港航班CA63589报疑似鸟击</a></li>--%>
                                    </ul>
                                    <div class="home-none" id="apronviolatenone" style="display:none">
                                        近期没有违章告知单动态
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>





            </div>


            <div class="wrapper wrapper-content">

</div>
<!-- 全局js -->
<script src="plug-in/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="plug-in/hplus/js/content.js"></script>
<script type="text/javascript" src="plug-in/echart/echarts.min.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/i18n/jquery.i18n.properties.js"></script>
<t:base type="tools"></t:base>
<%--<script type="text/javascript" src="plug-in/login/js/getMsgs.js"></script>--%>
<script>
$(document).ready(function() {
    var orgId = $("#orgId").val();
    if(orgId.indexOf("A39A15")!=-1){
        $("#contractPage").hide();
        $("#errorPage").show();
        $("#constract").show();
        $("#reform").hide();
        $("#violate").hide();
    }else{
        $("#contractPage").show();
        $("#errorPage").hide();
        $("#constract").hide();
        $("#reform").show();
        $("#violate").show();

    }
	//直接嵌套显示
    laydate.render({
      elem: '#calendar'
      ,position: 'static'
      ,theme: 'molv'
    	
    });

    // var myChart = echarts.init(document.getElementById('chart11'));
    //
    // var option = {
    //     tooltip: {
    //         trigger: 'axis',
    //         axisPointer: {
    //             type: 'cross',
    //             crossStyle: {
    //                 color: '#999'
    //             }
    //         }
    //     },
    //     toolbox: {
    //         feature: {
    //             dataView: {show: false, readOnly: false},
    //             magicType: {show: false, type: ['line', 'bar']},
    //             restore: {show: false},
    //             saveAsImage: {show: false}
    //         }
    //     },
    //     legend: {
    //         data:['进港航班','出港航班','实际进港','实际出港']
    //     },
    //     xAxis: [
    //         {
    //             type: 'category',
    //             data: ['2','4','6','8','10','12','14','16','18','20','22','24'],
    //             axisPointer: {
    //                 type: 'shadow'
    //             }
    //         }
    //     ],
    //     yAxis: [
    //         {
    //             type: 'value',
    //             name: '航班数量',
    //             min: 0,
    //             max: 250,
    //             interval: 50,
    //             axisLabel: {
    //                 formatter: '{value} '
    //             }
    //         },
    //         {
    //             type: 'value',
    //             name: '航班数量',
    //             min: 0,
    //             max: 25,
    //             interval: 5,
    //             axisLabel: {
    //                 formatter: '{value} '
    //             }
    //         }
    //     ],
    //     series: [
    //         {
    //             name:'进港航班',
    //             type:'bar',
    //             data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7,135.6, 135.6, 162.2, 32.6, 20.0, 6.4]
    //         },
    //         {
    //             name:'出港航班',
    //             type:'bar',
    //             data:[2.6, 5.9, 9.0, 26.4, 28.7,135.6, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0]
    //         },
    //         {
    //             name:'实际进港',
    //             type:'line',
    //             yAxisIndex: 1,
    //             data:[2.0, 2.2, 3.3, 4.5, 6.3, 20.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0]
    //         },
    //         {
    //             name:'实际出港',
    //             type:'line',
    //             yAxisIndex: 1,
    //             data:[2.0, 2.2, 3.3, 5.5, 6.3,22.4, 15.2, 20.3, 22.4, 23.0, 16.5, 11.0]
    //         }
    //     ]
    // };
    // myChart.setOption(option, true);
// 5分钟刷新一次

    reform();

    //违章告知单
    violate();

    //异常事件
    eventList();

    //施工单
    workapprove();

    setInterval("loadData()",5000*60);



});

//异常事件-饼状图
var eventChart = echarts.init(document.getElementById('eventchart'));
var eventOption = {
	title : {
        text: '近七天异常事件统计',
        x:'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'horizontal',
        y: 'bottom',
        data:['鸟击','轮胎损伤','航空器漏油']
    },
    series: [
        {
            name:'异常事件',
            type:'pie',
            radius : '72%',
            avoidLabelOverlap: false,
            data:[
                {
                value:0,
                name:'鸟击'
                },
                {
                    value:0,
                    name:'轮胎损伤'
                },
                {
                    value:0,
                    name:'航空器漏油'
                },]
        }
    ]
};
eventChart.setOption(eventOption, true);
function loadData(){
    console.log(2222222222222);
    //整改单动态
    reform();

    //违章告知单
    violate();

    //异常事件
    eventList();

    //施工单
    workapprove();
    }
    //跳转列表页
    function openTag(tagname){
        if(tagname=="workapprove"){
            addOneTab("施工申请","famsWorkApproveController.do?list&toPage=Apply","");
        }else if(tagname=="reform"){
            addOneTab("整改单","famsAircontrolReformController.do?list","");
        }else if(tagname=="violation"){
            addOneTab("违章告知单","famsAircontrolViolationController.do?list","");
        }


    }
//获取施工单的数据
function workapprove(){
    $.ajax({
        type:'POST',
        // dataType:'json',
        url:'indexController.do?workInfo',
        // contentType:'application/json',
        data:{
        },
        success:function (data) {
            var d = JSON.parse(data);
            console.log(d);
            if(d.obj.length!=0){

            for(var j=0;j<d.obj.length;j++){
                var item=d.obj[j];
                if(item.name!="apply"){
                    $("#contractPage").hide();
                    $("#constract").show();
                    if(item.value.length!=0){
                        var str="";

                        for(var i=0;i<item.value.length;i++){
                            if(item.value[i].work_start_time.length==10){
                                item.value[i].work_start_time=item.value[i].work_start_time+" 00:00";
                            }
                            str+='<tr><td><a href="#" onclick="openWinforEvent(\''+item.value[i].id+'\',\''+'shigong'+'\')">'+item.value[i].number+'</a></td><td>'+item.value[i].work_name+'</td><td>'+item.value[i].work_depart+'</td><td>'+item.value[i].person_name+'</td><td>'+item.value[i].work_area+'</td><td>'+item.value[i].work_affect_area+'</td><td>'+item.value[i].work_start_time+'-'+item.value[i].work_end_time+'</td></tr>';
                        }
                        $("#workTable").html(str);
                        $("#workTableHas").show();
                    }else {
                        $('#homeNone').show();
                    }


                }else{
                    $("#constract").hide();
                    $("#contractPage").show();
                    var str="";
                    var s=item.value;
                    console.log(s);

                    if(s.length!=0) {
                        for (var i = 0; i < s.length; i++) {
                            str += '<li><a href="#" onclick="openWinforEvent(\'' + s[i].bid + '\',\'' + 'workapprove' + '\')">' + s[i].time + '  ' + s[i].departname + '-' + s[i].username + s[i].note + ':' + s[i].workname + '</a></li>'

                        }
                        $('#workapprove').html(str);
                    }else{

                        $('#eventlistWorknone').show();
                    }


                }
            }
            }
        },
        error:function (e) {

        }
    })
}
//获取异常事件的数据
function eventList() {
    //获取异常事件的数据
    $.ajax({
        type:'POST',
        dataType:'json',
        url:'indexRestController/getMain.do?day=7',
        contentType:'application/json',
        data:{},
        success:function (data) {
            if(data.ok){
                var oArr = [];
                var nArr = [];
                for(var j=0;j<data.data.length;j++){
                    if(data.data[j].val != 0){
						oArr.push({
							'value':data.data[j].val,
							'name':data.data[j].name
						})
						nArr.push(data.data[j].name);
					}
                }
                
                var eventOption = {
               		title : {
               	        text: '近七天异常事件统计',
               	        x:'center'
               	    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'horizontal',
                        y: 'bottom',
                        data:nArr
                    },
                    series: [
                        {
                            name:'异常事件',
                            type:'pie',
                            radius : '72%',
                            avoidLabelOverlap: false,
                            data:oArr
                        }
                    ]
                };
				if(nArr.length>0){
                	eventChart.setOption(eventOption, true);
                	eventChart.resize();
				}

                //最新五条异常事件
                var str = '';
                if(data.data[0].indexFive.length>0) {
                    var l = 5;
                    if(data.data[0].indexFive.length<5){
                        l = data.data[0].indexFive.length;
                    }
                    for (var i = 0; i < l; i++) {

                        var obj = data.data[0].indexFive[i];
                        str += '<li><a href="javascript:void(0)" onclick="openWinforEvent(\''+obj.id+'\',\''+obj.code+'\')">'
                            + obj.time
                            + ' '
                            + obj.title
                            + '</a></li>';
                    }
                    $("#eventlist").html(str);
                }else{
                    $("#eventlist").hide();
                    $("#eventlistnone").show();
                }

            }else{

            }
        },
        error:function (e) {

        }
    })

}
//获取整改单动态
function reform(){
    $.ajax({
        type:'POST',
        // dataType:'json',
        url:'indexRestController.do?list',
        // contentType:'application/json',
        data:{
           'module':'reform',
            'pageNo':0,
            'pageSize':5

        },
        success:function (data) {
            var d = JSON.parse(data);
            if(d.ok){
                //最新五条
                var str = '';
                if(d.data.length>0) {
                    var l = 5;
                    if(d.data.length<5){
                        l = d.data.length;
                    }
                    for (var i = 0; i < l; i++) {

                        var obj = d.data[i];
                        str += '<li><a href="javascript:void(0)" onclick="openWinforEvent(\''+obj.business_id+'\',\''+obj.module+'\')">'
                            + obj.create_time
                            + ' '
                            + obj.reply_depart_id
                            + ''
                            + obj.reply_content
                            + ':关于'
                            + obj.violation_name
                            + '</a></li>';
                    }
                    $("#apronreform").html(str);
                }else{
                    $("#apronreform").hide();
                    $("#apronreformnone").show();
                }

            }else{

            }
        },
        error:function (e) {

        }
    })
}
//获取违章告知单
function violate(){
    $.ajax({
        type:'POST',
        // dataType:'json',
        url:'indexRestController.do?list',
        // contentType:'application/json',
        data:{
            'module':'violation',
            'pageNo':0,
            'pageSize':5
        },
        success:function (data) {
            var d = JSON.parse(data);
            if(d.ok){
                //最新五条
                var str = '';
                if(d.data.length>0) {
                    var l = 5;
                    if(d.data.length<5){
                        l = d.data.length;
                    }
                    for (var i = 0; i < l; i++) {

                        var obj = d.data[i];
                        str += '<li><a href="javascript:void(0)" onclick="openWinforEvent(\''+obj.business_id+'\',\''+obj.module+'\')">'
                            + obj.create_time
                            + ' '
                            + obj.reply_depart_id
                            + ''
                            + obj.reply_content
                            + ':关于'
                            + obj.violation_name
                            + '</a></li>';
                    }
                    $("#apronviolate").html(str);
                }else{
                    $("#apronviolate").hide();
                    $("#apronviolatenone").show();
                }

            }else{

            }
        },
        error:function (e) {

        }
    })

}
//打开详情
function openWinforEvent(id,title){
    var url;
    var t;
    if(title =="birdstrike" ){
        t="鸟击";
        url = "famsUnsafeeventBirdstrikeController.do?goUpdate&load=detail&id="+id;
    }else if(title == "tiredamage"){
        t="轮胎损伤";
        url = "famsUnsafeeventTiredamageController.do?goUpdate&load=detail&id="+id;
    }else if(title == "aircraftleakage"){
        t="航空器漏油";
        url = "famsUnsafeeventAircraftleakageController.do?goUpdate&load=detail&id="+id;
    }else if(title == "reform"){
        t="整改单";
        url = "famsAircontrolReformController.do?goCheck&load=detail&id="+id;

    }else if(title == "violation"){
        t="违章告知单";
        url = "famsAircontrolViolationController.do?goReceive&load=detail&id="+id;
    }else if(title=="workapprove"){
        t="施工单详情";
        url = "famsWorkApproveController.do?goUpdate&load=detail&id="+id;
    }else if(title=="shigong"){
        t="施工单详情";
        url = "famsWorkApproveController.do?goUpdate&load=detail&id="+id;
    }
    $.dialog({
        content: 'url:'+ url,
        zIndex: getzIndex(),
        lock : true,
        width:window.top.document.body.offsetWidth,
        height: window.top.document.body.offsetHeight-100,
        title:t,
        opacity : 0.3,
        cache:false,
        okVal: '确定',
        cancelVal: '关闭',
        cancel: true /*为true等价于function(){}*/
    });
}

</script>
<!--统计代码，可删除-->
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>