<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<%@ include file="/jsp/common.jsp" %>
<script type="text/javascript">
//百度地图API功能
function G(id) {
	return document.getElementById(id);
}

var map = new BMap.Map("map_con");
var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
map.addControl(ctrl_nav);
map.enableScrollWheelZoom();//启用地图滚轮放大缩小
map.centerAndZoom("上海",12);                   // 初始化地图,设置城市和地图级别。
map.addEventListener("click", showInfo);
var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
	{"input" : "suggestId"
	,"location" : map
});

//显示经纬度信息
function showInfo(e){
 //alert("经度："+e.point.lng + ", 纬度：" + e.point.lat);
 $("#lnglatMsg").empty().html("坐标："+e.point.lng + "," + e.point.lat);
}

ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
var str = "";
	var _value = e.fromitem.value;
	var value = "";
	if (e.fromitem.index > -1) {
		value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	}    
	str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
	
	value = "";
	if (e.toitem.index > -1) {
		_value = e.toitem.value;
		value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	}    
	str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
	G("searchResultPanel").innerHTML = str;
});

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
var _value = e.item.value;
	myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
	G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
	$("#lnglatMsg").empty();
	setPlace();
});

function setPlace(){
	map.clearOverlays();    //清除地图上所有覆盖物
	function myFun(){
		var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		
		$("#lnglatMsg").empty().html("坐标："+pp.lng+","+pp.lat);
		map.centerAndZoom(pp, 18);
		
		var marker = new BMap.Marker(pp); 
		map.addOverlay(marker);
		var opt = { width : 200,     // 信息窗口宽度
				  height: 55,     // 信息窗口高度
				  enableMessage:false
				};
		var infoWindow = new BMap.InfoWindow($("#suggestId").val(), opt);  // 创建信息窗口对象 
		marker.addEventListener("mouseover", function(){          
			map.openInfoWindow(infoWindow,pp); //开启信息窗口
		});
	}
	var local = new BMap.LocalSearch(map, { //智能搜索
	  onSearchComplete: myFun
	});
	local.search(myValue);
}

function queryMap(){
	$("#lnglatMsg").empty();
	map.clearOverlays();    //清除地图上所有覆盖物
	var myGeo = new BMap.Geocoder();
	myGeo.getPoint($("#suggestId").val(), function(point){
		if (point) {
			$("#lnglatMsg").empty().html("坐标："+point.lng+","+point.lat);
			map.centerAndZoom(point, 16);
			var marker = new BMap.Marker(point); 
			map.addOverlay(marker);
			var opt = { width : 200,     // 信息窗口宽度
					  height: 55,     // 信息窗口高度
					  enableMessage:false
					};
			var infoWindow = new BMap.InfoWindow($("#suggestId").val(), opt);  // 创建信息窗口对象 
			marker.addEventListener("mouseover", function(){          
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			});
		}
	}, $("#suggestId").val());
}

</script>


<div style="width:100%;height:600px">
<div class="easyui-layout" fit="true">
	<div region="center" border="false"  style="margin-left:5px;">
		<div style="margin:5px;padding:5px;border: 1px solid #ccc;background: #f1f1f1;">
			<div id="r-result">
				请输入:<input type="text" id="suggestId" size="20"  style="width:150px;" />
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryMap()">查询</a>
				 <span id="lnglatMsg" style="font-size:14px;margin-left:200px"></span>   		
			</div>
			<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
			
		</div>
		<div id="map_con" style="border: 1px solid #ccc;margin:5px;height:500px"></div>
	</div>
</div>
</div>