<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<%@ include file="/jsp/common.jsp" %>
<%@ include file="/jsp/commonJs.jsp" %>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=735188ce66f222510cb74c97e63ab8a8"></script>

<script type="text/javascript">
	$(function() {
		var map = new BMap.Map("allmap");            // 创建Map实例
		//map.centerAndZoom(new BMap.Point(16.404, 39.915), 11);
		map.centerAndZoom("上海",15);
		map.enableScrollWheelZoom();
		var local = new BMap.LocalSearch(map, {
		  renderOptions:{map: map, autoViewport:true}
		});
		local.searchNearby("川菜", "上海");
		
		
	});


</script>
<div style="width:100%;height:500px">
12345
	<div id="allmap" style="width:600px;height:300px"></div>
</div>