//测试服务器
//var serverMap = "http://10.10.12.29:8090/";

//生产网31服务器地图服务访问地址
//var serverMap = "http://10.128.15.31:8090/";

//DMZ区61服务器地图服务访问地址
var serverMap = "http://10.128.103.61:8090/"

// var urlMap = serverMap + "iserver/services/map-ugcv5-FeiHangQuDaoMianXunJianZhuanTiTu3/rest/maps/飞行区道面巡检专题图";
var urlMap =getRootPath()+"/飞行区道面巡检专题图";


function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath=curWwwPath.substring(0,pos);
    if(localhostPath.indexOf("10.128.106.32")!=-1){
        return "http://10.128.106.32:8090/maps"
    }if(localhostPath.indexOf("10.10.220.16")!=-1){
        return "http://10.10.220.16:8090/maps"
    }else{
        return "http://219.137.228.26:18090/maps"
    }
    //获取带"/"的项目名，如：/uimcardprj
    　
}


var map, baseLayer, drawPolygon, polygonLayer, selectFeature;
var drawArr = [];

//区域样式
var styleInflu = {
	fillColor: "#52A6FF",
	fillOpacity: 0,
	strokeColor: "#E41A1C",
	strokeWidth: 5
};

var styleInfluNo = {
	fillColor: "#52A6FF",
	fillOpacity: 0,
	strokeColor: "#333333",
	strokeWidth: 5
};
var testdata = [
// var testdata = [{
// 		"id": 111,
// 		"number": 1,
// 		"influence": false,
// 		"point": "113.29358266037,23.397563787385;113.29340440093,23.39524641466;113.29619713217,23.395008735406;113.29613771235,23.397682627012;113.29358266037,23.397563787385",
// 		"content": "城规515机位施工",
// 		"startDate": "1552147200",
// 		"endDate": "1552233599"
// 	},
// 	{
// 		"id": 222,
// 		"number": 2,
// 		"influence": true,
// 		"point": "113.30332750978,23.395959452422;113.30303041072,23.394949315592;113.30546662307,23.394355117458;113.30594198158,23.395602933541;113.30332750978,23.395959452422",
// 		"content": "XXXXXX施工",
// 		"startDate": "1552147200",
// 		"endDate": "1552233599"
// 	},
// 	{
// 		"id": 333,
// 		"number": 3,
// 		"influence": true,
// 		"point": "113.29122963691,23.385993559995;113.29149108409,23.385280522234;113.29220412186,23.385494433562;113.29189513883,23.386183703399;113.29122963691,23.385993559995",
// 		"content": "T航站楼西一指廊施工",
// 		"startDate": "1552147200",
// 		"endDate": "1552233599"
// 	},
// 	{
// 		"id": 444,
// 		"number": 4,
// 		"influence": false,
// 		"point": "113.29541279178,23.389796428058;113.29538902386,23.389463677102;113.29581684652,23.389463677102;113.29586438237,23.389820195983;113.29541279178,23.389796428058",
// 		"content": "T1航站楼33号门外施工",
// 		"startDate": "1552147200",
// 		"endDate": "1552233599"
// 	}
];

var vm = new Vue({
	el: '#vm',
	data: {
		list: [],
		searchList:[],
		keyword: '',
		dateshow: new XDate().toString('yyyy/MM/dd'),
		path:""
	},
	created: function() {
		this.getPath();
	},
	mounted: function() {
		//时间选择控件
		var that = this
		laydate.render({
			elem: '#date',
			format: 'yyyy/MM/dd',
			max: 1,
			value: that.dateshow,
			theme: '#52A6FF',
			btns: ['now', 'confirm'],
			done: function(value, date) {
				//value: 得到日期生成的值，如：2017-08-18
				//date: 得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
				that.dateshow = value;
			}
		});
	},
	watch: {
		dateshow: function(val, oldVal) {
			ajaxRequest();
		}
	},
	methods: {
		setPickDate: function(i) {
			//选择[今天]或[明天]
			this.dateshow = new XDate().addDays(i).toString('yyyy/MM/dd');
		},
		listPick: function(itm, idx) {
			//勾选or取消勾选, 重新渲染施工区域
			addPloygon(this.list);
		},
		search: function() {
			//回车搜索施工区域
			if (this.keyword.trim() == ''){
                this.list=this.searchList;
                return;
			}
			var hasResult = false;
            this.list=[];
			for (var r = 0; r < this.searchList.length; r++) {
				if (this.searchList[r].content.indexOf(this.keyword) != -1) {
					setMapCenter(this.searchList[r]);

					this.list.push(this.searchList[r]);
					hasResult = true;

				}
			}
			
			if (!hasResult) {
				initMapCenter();
				this.list=this.searchList;
				layer.msg('搜索无结果。', {time: 2000, icon: 5});
			}else{
                addPloygon(this.list);
			}
		},
        searchInput:function(){
			console.log(2222);
            //回车搜索施工区域
            if (this.keyword.trim() == ''){
                this.list=this.searchList;
                addPloygon(this.list);
                initMapCenter();
                return;
            }
        },
        getPath:function (){
            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0,index+1);
            this.path=result;
		}
	}
})

function init() {
	//声明一个矢量图层，第一个参数是图层名
	polygonLayer = new SuperMap.Layer.Vector("施工区域");

	//实例化一个DrawFeature控件，调用绘制多边形的事件处理器Handler.Polygon
	drawPolygon = new SuperMap.Control.DrawFeature(polygonLayer, SuperMap.Handler.Polygon);

	//监听 featureadded 事件，当添加要素时会触发此事件
	//会传入事件参数，事件参数包含了绘制的要素 feature 信息
	drawPolygon.events.on({
		"featureadded": drawPolygonCompleted
	});

	//定义要素选中控件，在polygonLayer上进行要素选择
	selectFeature = new SuperMap.Control.SelectFeature(polygonLayer, {
		callbacks: {
            click: function (currentFeature) {
            	toDetail(currentFeature.attributes.id);
            }
        }
	});

	//在id为map的页面元素中创建一个map地图对象，并添加控件
	map = new SuperMap.Map("map", {
		controls: [
			new SuperMap.Control.Zoom(),
			new SuperMap.Control.Navigation(),
			new SuperMap.Control.LayerSwitcher(),
			drawPolygon,
			selectFeature
		]
	});

	//激活控件
	selectFeature.activate();

	//向服务端发送请求获取后，获取透明、使用服务端缓存的图层
	baseLayer = new SuperMap.Layer.TiledDynamicRESTLayer("飞行区道面巡检专题图", urlMap, null, {
		maxResolution: "auto"
	});
	//图层初始化完成后的回调函数
	baseLayer.events.on({
		"layerInitialized": addLayer
	});

	ajaxRequest();
}

function toDetail(id) {
    $.dialog({
        content: 'url:famsWorkApproveController.do?goUpdate&id=' + id + "&load=detail",
        zIndex: getzIndex(),
        lock: true,
        width: window.top.document.body.offsetWidth,
        height: window.top.document.body.offsetHeight - 100,
        title: '查看',
        opacity: 0.3,
        cache: false,
        cancelVal: '关闭',
        cancel: true /*为true等价于function(){}*/
    });
}

//这里模拟从后台获取坐标点的字符串
function ajaxRequest() {
	//这里模拟从后台获取坐标点的字符串
	layer.load(2, {shade: [0.2, '#000']});
    testdata=[];
	$.ajax({
		type: 'GET',
		url: vm.path+'/famsCommonMapController.do?getMaps&time='+Math.ceil(new Date(vm.dateshow).getTime()/1000),
		timeout: 10 * 1000,
		success: function(msg) {
			var data=JSON.parse(msg);
			console.log(data.obj);
			//console.log(JSON.parse(msg).obj);
			layer.closeAll('loading');
			for(var i=0;i<data.obj.length;i++){
				var json=JSON.parse(data.obj[i].map_points);
				console.log(json);
				json[0].id=data.obj[i].id;
                json[0].number=i+1;
                json[0].content=data.obj[i].work_name;
                if(data.obj[i].work_start_time.length==10){
                    json[0].startDate="00:00";
				}else{
                    json[0].startDate=data.obj[i].work_start_time.substring(10,data.obj[i].work_start_time.length)
				}
				if(data.obj[i].work_end_time.length==10){
                    json[0].endDate="23:59";
				}else{
                    json[0].endDate=data.obj[i].work_end_time.substring(10,data.obj[i].work_end_time.length)
				}
				if(data.obj[i].is_affect_flight=="Y"){
                    json[0].influence=true;
				}else{
                    json[0].influence=false;
				}
                testdata=testdata.concat(json);
			}
			console.log(testdata);
			handleList(testdata);
		},
		error: function(err) {
			layer.closeAll('loading');
			layer.msg('获取施工区域有误，请稍后重新打开地图。', {time: 3000, icon: 5, shade: 0.2});
		}
	});
}

//地图要显示的图层
function addLayer() {
	//地图要显示的图层
	map.addLayers([baseLayer, polygonLayer]);
	//显示地图范围
	initMapCenter();
}

//调用 active 方法激活绘制多边形控件
function draw_polygon() {
	drawPolygon.activate();
}

//退出绘制
function exit_polygon() {
	drawPolygon.deactivate();
}

//绘面完成后执行方法，取折点坐标
function drawPolygonCompleted(event) {
	var _feat = event.feature;
	var _components = _feat.geometry.components[0].components;
	var str = "";

	//取消画图，激活矢量平台编辑
	exit_polygon();

	drawArr.push(_feat);
	for(var k = 0; k < _components.length; k++) {
		str += _components[k]["x"];
		str += ",";
		str += _components[k]["y"];
		str += ";";
	}
	if(str.length > 0) {
		str = str.substring(0, str.length - 1);
//		document.getElementById("polyLonlat").value = str;
	}
	
	//drawArr是存放画图区域对象的数组, str是点坐标的字符串
	console.log(drawArr, str);
}

function clearFeatures() {
	var arrLen = drawArr.length;
	for(var p = 0; p < arrLen; p++) {
		polygonLayer.removeFeatures(drawArr[p]);
	}
	drawArr.splice(0, arrLen);
	exit_polygon();
}

//默认加载已有的面要素
function addPloygon(arr) {
	polygonLayer.removeAllFeatures();
	map.removeAllPopup();
	
	var polygon_features = [];
	
	try {
		for(var i = 0; i < arr.length; i++) {
			//列表没勾选的施工区域不画
			if (!arr[i].show) continue;
			
			var _that = arr[i];
			var pointArr = _that.point.split(";");
			var points = [];
	
			for(var j = 0; j < pointArr.length; j++) {
				var point = pointArr[j].split(",");
				//几何图中各个点位置
				var pointGeo = new SuperMap.Geometry.Point(point[0], point[1]);
				points.push(pointGeo);
			}
			
			//判断是否影响机场运行, 影响则红框, 不影响则黑框
			var styleShigong = _that.influence ? styleInflu : styleInfluNo;
			
			var linearRing = new SuperMap.Geometry.LinearRing(points);
			var polygon = new SuperMap.Geometry.Polygon([linearRing]);
			var polygon_feat = new SuperMap.Feature.Vector(polygon, null, styleShigong);
	
			//添加自定义属性
			polygon_feat.attributes['id'] = _that.id;
			polygon_feat.attributes['obj'] = _that;
			polygon_features.push(polygon_feat);
			
			var htmlString = '<div class="map-popup" onclick="toDetail(\'' + _that.id + '\')">' +
								'<div class="map-popup-con">' + 
									'<div class="map-popup-text">' + _that.number + '. ' + _that.content + '</div>' +
									'<div class="map-popup-text">' + _that.startDateShow + '~' + _that.endDateShow + '</div>' +
								'</div>' +
							'</div>';
			
			var popup = new SuperMap.Popup(
				'popup_' + _that.number,
				new SuperMap.LonLat(_that.center_x, _that.center_y),
		        new SuperMap.Size(150, 45), 
		        htmlString, 
		        false, 
			    null);
		    popup.setBackgroundColor('transparent');
		    map.addPopup(popup);
		}
		polygonLayer.addFeatures(polygon_features);
	} catch (err) {
		setTimeout(function() {
			addPloygon(arr);
		}, 500);
	}
}

//处理列表数据
function handleList(arr) {
	for(var q = 0; q < arr.length; q++) {
		//默认该施工区域可见
		arr[q].show = true;
		
		var sum_x = 0, sum_y = 0;
		var sum_point = arr[q]['point'].split(';');
		var sum_len = sum_point.length - 1;//闭合区域中, 第一个点的坐标与最后一个点的坐标一样
		
		for (var s = 0; s < sum_len; s++) {
	    	sum_x += Number(sum_point[s].split(',')[0]);
	    	sum_y += Number(sum_point[s].split(',')[1]);
		}
		
		//设置该施工区域的中心点
		arr[q].center_x = sum_x / sum_len;
		arr[q].center_y = sum_y / sum_len;
		
		//设置开始时间和结束时间的显示字段
		var startDateNum =arr[q].startDate; //arr[q].startDate.length == 13 ? arr[q].startDate * 1 : arr[q].startDate * 1000;
		var endDateNum =arr[q].endDate ;//arr[q].endDate.length == 13 ? arr[q].endDate * 1 : arr[q].endDate * 1000;
		arr[q].startDateShow = startDateNum;//new XDate(startDateNum).toString('HH:mm');
		arr[q].endDateShow =endDateNum;// new XDate(endDateNum).toString('HH:mm');
	}
	
	vm.list = [].concat(arr);
	vm.searchList=[].concat(arr);
	addPloygon(arr);
}

//设置地图原始中心点
function initMapCenter() {
	map.setCenter(new SuperMap.LonLat(113.3120, 23.3440), 1);
}

//设置地图的中心点
function setMapCenter(obj) {
	map.setCenter(new SuperMap.LonLat(obj.center_x, obj.center_y), 2);
}

window.onload = function() {
	init();
};
/**
 * 设置 window的 zIndex
 * @param flag true: 不增量(因为 tip提示经常使用 zIndex, 所以如果是 tip的话 ,则不增量)
 * @returns
 */
function getzIndex(flag){
    var zindexNumber = getCookie("ZINDEXNUMBER");
    //console.log('getCookie - zindexNumber: '+ zindexNumber)
    //console.log('getCookie: '+ document.cookie)
    if(zindexNumber == null){
        zindexNumber = 1990;
    }else{
        if(!flag){
            zindexNumber = parseInt(zindexNumber) + parseInt(10);
            setCookie("ZINDEXNUMBER",zindexNumber);
            //console.log('new zindexNumber: '+ zindexNumber)
            //console.log('new getCookie: '+ document.cookie)
        }
    }
    return zindexNumber;
}
/*获取Cookie值*/
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    return (arr=document.cookie.match(reg))?unescape(arr[2]):null;
}
/* 设置 cookie  */
function setCookie(name, value){
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);

    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";

}