<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<%@ include file="/jsp/common.jsp" %>
<script type="text/javascript">
$(function(){
	getPro();
	
	
});

function getPro(){
	var cit = "<option value='全国' >全国</option>" ;
	$.ajax({
		type: "post",
		url: "${path}/areas/getList",
		data:{
			"parentid":'0'
		},
		success: function(msg,status){
			if(msg!='timeout'){
				var rs = $.evalJSON(msg);
				if(rs.success){
					$.each(rs.list, function(i, n){
               			 cit+="<option value="+n[0]+" >"+n[1]+"</option>" ;
					});
           			$("#province_home").empty().append(cit); 
           			//$("#province_home").attr("value",'10');//默认选中上海
           			//getCity();
           			getMapByAddress();
				}else{
					alert("查询出错");
				}
			}
		}
	});
	
}

function getMapByAddress(){
	
	$.ajax({
		type: "post",
		url: "${path}/estate/home/getData",
		data:{
			"province":$("#province_home").find("option:selected").text(),
			"city":$("#city_home").find("option:selected").text()
		},
		success: function(msg,status){
			if(msg!='timeout'){
				var rs = $.evalJSON(msg);
				if(rs.success){
					getNewmap(rs.address,rs.i);
					for (var i = rs.list.length - 1; i >= 0; i--) {
						getBoundary(rs.list[i][0],rs.list[i][1]);
					}
				
				}else{
					alert("查询出错");
				}
			}
		}
	});
	
	
}

function getCityBypro(){
	var cit = "<option value='全部'>全部</option>"; 
	if($("#province_home").val()=='全国'){
		$("#city_home").empty().append(cit);
			getMapByAddress();
	}else{
		$.ajax({
			type: "post",
			url: "${path}/areas/getList",
			data:{
				"parentid":$("#province_home").val()
			},
			success: function(msg,status){
				if(msg!='timeout'){
					var rs = $.evalJSON(msg);
					if(rs.success){
						$.each(rs.list, function(i, n){
	               			 cit+="<option value="+n[0]+">"+n[1]+"</option>" ;
						});
	           			$("#city_home").empty().append(cit);
	           			getMapByAddress();
					}else{
						alert("查询出错");
					}
				}
			}
		});
		
	}
	
	
}


var home_map ;

function getNewmap(address,i){
	home_map = new BMap.Map("home_map");
	home_map.addControl(new BMap.MapTypeControl({
	    mapTypes:[BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP, BMAP_HYBRID_MAP]
	}));
	home_map.addControl(new BMap.NavigationControl());
	home_map.enableScrollWheelZoom();
	home_map.enableContinuousZoom();

	//map.centerAndZoom(new BMap.Point(121.681086,31.163653), 10);
	home_map.centerAndZoom(address,i); 

}


function getBoundary(address,price) {
	var bdary = new BMap.Boundary();
	//alert(address);
	var add=address+"";
	var color="#"+add.substring(add.indexOf("-")+1,add.length);
	var addre=add.substring(0,add.indexOf("-"));
	//alert(add.substring(0,address.indexOf("-")));
	bdary.get(addre, function(rs){       //获取行政区域    
		var count = rs.boundaries.length; //行政区域的点有多少个
		for(var i = 0; i < count; i++){
			var ply = new BMap.Polygon(rs.boundaries[i], {fillColor:color, strokeWeight:1,fillOpacity:0.9, strokeColor: "#FFffff"}); //建立多边形覆盖物
			ply.addEventListener("mouseover",function(e){
				ply.setFillColor("red");
				ply.setStrokeWeight(2);
				ply.setStrokeColor("#FFffff");
				
				
			});
			
			ply.addEventListener("click",function(e){
				var latlng = e.point;
				var info = new BMap.InfoWindow("<div style='margin:4px 0px 0px 10px;'>平均房价："+price+"元/平方米</div>", {width:200,title : "<div style='font-size:14;color: #15428b;font-weight:bold'>"+addre+"</div>",enableMessage:false});
				
				home_map.openInfoWindow(info, latlng);
			});
			ply.addEventListener("mouseout",function(){
				ply.setFillColor(color);
				//ply.setStrokeColor(color);
				ply.setStrokeWeight(1);
				
			});
			  
			
			//为每个区添加名称
			var myGeo = new BMap.Geocoder();
			
			myGeo.getPoint(addre, function(point){
				if (point) { 
					var opts = {
					  position : point,    // 指定文本标注所在的地理位置
					  offset   : new BMap.Size(-20,-10)    //设置文本偏移量
					};
					var label = new BMap.Label(addre, opts);  // 创建文本标注对象
						label.setStyle({
							 color : "red",
							 border: "1px solid #fff",
							 fontSize : "10px"
						 });
					home_map.addOverlay(label); 
					label.addEventListener("click",function(e){
						var latlng = e.point;
						var info = new BMap.InfoWindow("<div style='margin:4px 0px 0px 10px;'>平均房价："+price+"元/平方米</div>", {width:200,title : "<div style='font-size:14;color: #15428b;font-weight:bold'>"+addre+"</div>",enableMessage:false});
						home_map.openInfoWindow(info, latlng);
					});
					label.addEventListener("mouseover",function(e){
						var latlng = e.point;
						var info = new BMap.InfoWindow("<div style='margin:4px 0px 0px 10px;'>平均房价："+price+"元/平方米</div>", {width:200,title : "<div style='font-size:14;color: #15428b;font-weight:bold'>"+addre+"</div>",enableMessage:false});
						home_map.openInfoWindow(info, latlng);
					});
				}
			}, "");
			home_map.addOverlay(ply);  //添加覆盖物    
		}                
	});  
} 

</script>


<div style="width:100%;height:550px">
		省份：<select id="province_home"   onchange="getCityBypro()"></select>
		地市：<select id="city_home"   onchange="getMapByAddress()"><option value="全部">全部</option></select>
		<div id="home_map" style="border: 1px solid #ccc;margin:5px;height:500px"></div>
		
</div>