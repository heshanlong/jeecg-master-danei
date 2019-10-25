//测试服务器
//var serverMap = "http://10.10.12.29:8090/";

//生产网31服务器地图服务访问地址
//var serverMap = "http://10.128.15.31:8090/";

//DMZ区61服务器地图服务访问地址
var serverMap = "http://10.128.103.61:8090/"

//var urlMap = serverMap + //"iserver/services/map-ugcv5-FeiHangQuDaoMianXunJianZhuanTiTu3/rest/maps///飞行区道面巡检专题图";
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
	fillOpacity: 0.6,
	strokeColor: "#E41A1C",
	strokeWidth: 2
};

var styleInfluNo = {
	fillColor: "#52A6FF",
	fillOpacity: 0.6,
	strokeColor: "#333333",
	strokeWidth: 2
};

var testdata = [];
var isMark=localStorage.getItem("isMark");
var hide=false;
var hideBut=false;
if(isMark=="1"){
    hide=true;
    var mapPostion= localStorage.getItem("mapPostion");
    if(mapPostion!=""){
    	var jsonPoint=JSON.parse(mapPostion);
        testdata=jsonPoint;
    	console.log(jsonPoint);
	}

}
if(isMark=="2"){
    hideBut=true;
    hide=true;
    var mapPostion= localStorage.getItem("mapPostion");
    if(mapPostion!=""){
        var jsonPoint=JSON.parse(mapPostion);
        testdata=jsonPoint;
        console.log(jsonPoint);
    }
}
console.log(hide);
var vm = new Vue({
	el: '#vm',
	data: {
		list: [],
		keyword: '',
		dateshow: new XDate().toString('yyyy/MM/dd'),
		isHide:hide,
		isHideBut:hideBut
	},
	created: function() {
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
			if (this.keyword.trim() == '') return;
			
			var hasResult = false;
			for (var r = 0; r < this.list.length; r++) {
				if (this.list[r].content.indexOf(this.keyword) != -1) {
					setMapCenter(this.list[r]);
					hasResult = true;
					break;
				}
			}
			
			if (!hasResult) {
				initMapCenter();
				layer.msg('搜索无结果。', {time: 2000, icon: 5});
			}
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
		onSelect: function(currentFeature) {
			//点击事件
			console.log(currentFeature);
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

//这里模拟从后台获取坐标点的字符串
function ajaxRequest() {
	//这里模拟从后台获取坐标点的字符串
	layer.load(2, {shade: [0.2, '#000']});
    handleList(testdata);
    layer.closeAll('loading');
	// $.ajax({
	// 	type: 'GET',
	// 	url: 'http://jsonplaceholder.typicode.com/users',
	// 	timeout: 10 * 1000,
	// 	success: function(msg) {
	// 		layer.closeAll('loading');
	//
	// 	},
	// 	error: function(err) {
	// 		layer.closeAll('loading');
	// 		layer.msg('获取施工区域有误，请稍后重新打开地图。', {time: 3000, icon: 5, shade: 0.2});
	// 	}
	// });
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
    clearFeatures();
}

//退出绘制
function exit_polygon() {
	drawPolygon.deactivate();
}
//施工区域数组
var arr=new Array();
var number=0;
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


	arr=[{
        "id": '',
        "number": number++,
        "influence": false,
        "point":str,
        "content": "",
        "startDate": "",
        "endDate": "",
		"showBubble":false
    }];
	var a=arr;
    localStorage.setItem("mapPostion",JSON.stringify(a));
    console.log(localStorage.getItem("mapPostion"));
}

function clearFeatures() {
	console.log(vm.isHide);
	if(vm.isHide){
        polygonLayer.removeAllFeatures();
        testdata=[];
        localStorage.setItem("mapPostion","");
	}else{
        var arrLen = drawArr.length;
        for(var p = 0; p < arrLen; p++) {
            polygonLayer.removeFeatures(drawArr[p]);
        }
        drawArr.splice(0, arrLen);
        exit_polygon();
	}

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
			
			var htmlString = '<div class="map-popup">' +
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
		    if(arr.showBubble){
                map.addPopup(popup);
			}

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
		var startDateNum = arr[q].startDate.length == 13 ? arr[q].startDate * 1 : arr[q].startDate * 1000;
		var endDateNum = arr[q].endDate.length == 13 ? arr[q].endDate * 1 : arr[q].endDate * 1000;
		arr[q].startDateShow = new XDate(startDateNum).toString('HH:mm');
		arr[q].endDateShow = new XDate(endDateNum).toString('HH:mm');
	}
	
	vm.list = [].concat(arr);
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