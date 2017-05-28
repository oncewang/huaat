<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<%@ include file="/jsp/common.jsp" %>
<script src="${path}/resources/echart/esl.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=735188ce66f222510cb74c97e63ab8a8"></script>

<script type="text/javascript">
	$(function() {
	
		$('#assessResult_grid').datagrid(
				{
					url : '${path}/estate/assessResult/list',
					title:'菜单列表',
					//iconCls:'icon-save',
					height : 355,
					nowrap : false,
					striped : true,
					queryParams:{
							"start_date" : "${start}",
							"end_date" : "${end}"
						},
					remoteSort : false,
					idField : 'data0',
					pagination : true,
					rownumbers : true,
					singleSelect : true,
					columns : [ [ 
									{field : 'data0',title : '评估时间',width : 150,sortable : true},
									{field : 'data1',title : '省份',width : 60,sortable : true},
									{field : 'data2',title : '地市',width : 60,sortable : true},
									{field : 'data3',title : '区/县',width : 70,sortable : true},
									{field : 'data04',title : '地址',width : 160,sortable : true,
										formatter : function(value) {
											if(value.data14>0&&value.data15>0){
												return "<a href='#' onclick=getMap('"+value.data4+"',"+value.data14+","+value.data15+") >"+value.data4+"</a>";
											}else{
												return value.data4;
											}
										}	
									},
									{field : 'data5',title : '幢\栋',width : 60,sortable : true,
										formatter : function(value) {
											  return	"第 "+value+" 栋";
											}	
									},
									{field : 'data6',title : '总楼层',width : 60,sortable : true,
												formatter : function(value) {
													  return	"共 "+value+" 层";
													}	
									},
									{field : 'data7',title : '楼层',width : 60,sortable : true,
														formatter : function(value) {
															  return	"第 "+value+" 层";
															}	
									},
									{field : 'data8',title : '房屋面积',width : 80,sortable : true,
										formatter : function(value) {
											  return	value+" 平方米";
											}	
									},
									{field : 'data9',title : '建造日期',width : 80,sortable : true},
									{field : 'data10',title : '房屋用途',width : 70,sortable : true,
										formatter : function(value) {
											if(value=="1"){
												return "住宅";
											}else if(value=="2"){
												return "商用";
											}else if(value=="3"){
												return "商住两用";
											}else{
												return "未知";
											}
										}		
									},
									{field : 'data17',title : '房产证号',width : 100,sortable : true},
									{field : 'data011',title : '评估价格',width : 110,sortable : true,
										formatter : function(value) {
											if(value=="-"){
												return "暂缺评估价格";
											}else{
												return value;
											}
										}	
									},
									{field : 'data012',title : '波动预警',width : 75,sortable : true,
										formatter : function(value) {
											
											if(value.data11>0){
												if (value.data12 == 0) {
													return '<div style="width:70px;height:20px;background-color:#76EE00"><a href="#" onclick=bodong("'+value.data16+'","'+value.data1+'","'+value.data2+'","'+value.data3+'","'+value.data4+'","'+value.data5+'","'+value.data6+'","'+value.data7+'","'+value.data8+'","'+value.data11+'")>波动稳定</a> <div>';
												}else if (value.data12 == 1) {
													return '<div style="width:70px;height:20px;background-color:#FFFF00"><a href="#" onclick=bodong("'+value.data16+'","'+value.data1+'","'+value.data2+'","'+value.data3+'","'+value.data4+'","'+value.data5+'","'+value.data6+'","'+value.data7+'","'+value.data8+'","'+value.data11+'")>下调20%</a> <div>';
												}else if (value.data12 == 2) {
													return '<div style="width:70px;height:20px;background-color:#CCFF99"><a href="#" onclick=bodong("'+value.data16+'","'+value.data1+'","'+value.data2+'","'+value.data3+'","'+value.data4+'","'+value.data5+'","'+value.data6+'","'+value.data7+'","'+value.data8+'","'+value.data11+'")>下调30%</a><div>';
												}else if (value.data12 == 3) {
													return '<div style="width:70px;height:20px;background-color:red"><a href="#" onclick=bodong("'+value.data16+'","'+value.data1+'","'+value.data2+'","'+value.data3+'","'+value.data4+'","'+value.data5+'","'+value.data6+'","'+value.data7+'","'+value.data8+'","'+value.data11+'")>下调40%</a><div>';
												}
												
											}else{
												return "无预警";
												
											}
										}	
									}
									] ]
				}); 
	});



	function queryAssessResultList() {
		
			$('#assessResult_grid').datagrid('options').url='${path}/estate/assessResult/list';
			$('#assessResult_grid').datagrid('options').queryParams = {
				"start_date" : $('#start_date').datebox('getValue'),
				"end_date" : $('#end_date').datebox('getValue')
			};
			$('#assessResult_grid').datagrid('reload');
		
	}
	
	function openNearDig(proRow){
		var ht="";
		 $.each(proRow,function(index,pro){
			ht+="<div style='margin:20px 10px 10px 20px;'>地址："+pro['address']+"<span style='margin-left:30px;'>评估价格：</span>"+pro['price']+"</div>";
		});
		$("#near_dialog").dialog('setTitle','附近小区估价').dialog('open');
		$("#near_dialog_detail").empty().html(ht);
	}
	
	function bodong(id,data1,data2,data3,data4,data5,data6,data7,data8,data11){
	
		$("#fangzi_detail").empty().html("<div style='margin-bottom:5px;float:left;width:350px;'>地址："+data1+data2+data3+data4+"</div><div style='margin-bottom:5px;float:left;width:350px;'>幢\栋："+data5+"</div><div style='margin-bottom:5px;float:left;width:350px;'>楼高：共"+data6+"层</div><div style='margin-bottom:5px;float:left;width:350px;'>楼层：第"+data7+"层</div><div style='margin-bottom:5px;float:left;width:350px;'>面积："+data8+"平米</div><div style='margin-bottom:5px;float:left;width:350px;'>评估价格："+data11+"</div>");
		$("#earlyWarning_detail").dialog('setTitle','波动预警分析').dialog('open');
		echart_qingkong('bodongchart');
		$("#bodongchart").empty().html("正在查询！");
		$.ajax({
			type: "post",
			url: "${path}/estate/earlyWarning/chart",
			data:{
				"id":id
			},
			success: function(msg,status){
				var rs = $.evalJSON(msg);
				if(rs.success){
					$("#chart01_msg").empty();
					$("#bodongchart").empty();
					getEchart(rs);
				}else{
					$("#chart01_msg").empty().html("<div style='margin:10px;font-size:13px;color:red' >没有符合条件的数据,请重新查询！</div>");
					echart_qingkong('bodongchart');
				}
			}
		});
		
	}
	
	
	
	function exportResultList(){
		$("#downloadFrame").attr("src","${path}/estate/assessResult/export?start_date="+$('#start_date').datebox('getValue')+"&end_date="+$('#end_date').datebox('getValue'));
		
		
	}
	
	var listmap = new BMap.Map("listmap");            // 创建Map实例
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	listmap.addControl(ctrl_nav);
	listmap.enableScrollWheelZoom();//启用地图滚轮放大缩小
	var listopts = { width : 200,     // 信息窗口宽度
			  height:55,     // 信息窗口高度
			  enableMessage:false
			};
	function getMap(address,lng,lat){
		$("#map_dialog").dialog('setTitle','地址').dialog('open');
		var map = new BMap.Map("allmap");            // 创建Map实例
		var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
		map.addControl(ctrl_nav);
		map.enableScrollWheelZoom();//启用地图滚轮放大缩小
		var point = new BMap.Point(lng,lat);
		map.centerAndZoom(point,16);
		// 创建地址解析器实例
		var marker = new BMap.Marker(point); 
		map.addOverlay(marker);
		var opts = { width : 200,     // 信息窗口宽度
				  height: 55,     // 信息窗口高度
				  enableMessage:false
				};
		var infoWindow = new BMap.InfoWindow(address, opts);  // 创建信息窗口对象 
		marker.addEventListener("mouseover", function(){          
			map.openInfoWindow(infoWindow,point); //开启信息窗口
		});
	}
	
	
	function getMapList(){
		$("#mapAll_dialog").dialog('setTitle','我评估过的房子').dialog('open');
	
		 $.ajax({
			type: "post",
			url: "${path}/estate/assessResult/getAllList",
			data:{
				"start_date" : $('#start_date').datebox('getValue'),
				"end_date" : $('#end_date').datebox('getValue')
			},
			success: function(msg,status){
				var rs = $.evalJSON(msg);
				if(rs.success){
					var list=rs.list;
					var po = new BMap.Point(list[0][1],list[0][2]);
					listmap.centerAndZoom(po,13);
					
					for(var i=0;i<=list.length;i++){
						var point = new BMap.Point(list[i][1],list[i][2]);
						// 创建地址解析器实例
						var marker = new BMap.Marker(point); 
						var content = list[i][0];
						listmap.addOverlay(marker);               // 将标注添加到地图中
						marker.addEventListener("mouseover",openInfo.bind(null,content));
					}
				}else{
					
				}
			}
		});
	}
	
	function openInfo(content,e){
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,listopts);  // 创建信息窗口对象 
		listmap.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	
	

	require.config({
	    paths:{ 
	        'echarts':'${path}/resources/echart/echarts',
	        'echarts/chart/bar' : '${path}/resources/echart/echarts',
	        'echarts/chart/line': '${path}/resources/echart/echarts'
	    }
	});

	function getEchart(rs){
		
	    require(
	        [
	            'echarts',
	            'echarts/chart/bar',
	            'echarts/chart/line'
	        ],
	        function (ec) {
	            //--- 折柱 ---
	            var myChart = ec.init(document.getElementById('bodongchart'));
	           
	            myChart.setOption({
	            	title : {
	                    text: '房屋价值波动分析'
	                },
	            	tooltip : {
	                    trigger: 'axis'
	                },
	                toolbox: {
	                    show : true,
	                    feature : {
	                        dataView : {show: true, readOnly: false},
	                        magicType : {show: true, type: ['line', 'bar']},
	                        restore : {show: true},
	                        saveAsImage : {show: true}
	                    }
	                },
	                calculable : true,
	                xAxis : [
	                    {
	                    	name:'时间',
	                        type : 'category',
	                        data : rs.date
	                    }
	                ],
	                yAxis : [
	                    {
	                        type : 'value',
	                        splitArea : {show : true}
	                    }
	                ],
	                series : [
	                    {
	                        name:"金额(元)",
	                        type:'line',
	                        
	                        data:rs.value
	                    }
	                ]
	            });
	            
	            
	        }
	    );
	}
	
function echart_qingkong(id){
		
	    require(
	        [
	            'echarts',
	            'echarts/chart/bar',
	            'echarts/chart/line'
	        ],
	        function (ec) {
	            //--- 折柱 ---
	            var myChart = ec.init(document.getElementById(id));
	           
	            myChart.clear();
	        }
	    );
	}
</script>
<div style="width:100%;height:500px">
	<div  style="margin-bottom:10px;height:30px;font-size:15px;font-weight: bold;line-height:30px;padding-left:10px;background-color:#eee;border-top: 2px solid #ddd;border-bottom: 2px solid #ddd"> 我评估过的房子</div>
	<div   style="height:auto;margin-bottom:5px;" >
			<div class="filter" >
					<fieldset style="height:40px;line-height:40px;padding-left:30px;">
					    	<ul>
								<li class=" "> 
									评估时间:
									<input id="start_date" class="easyui-datebox" required="true"  value="${start}"></input>--
									<input id="end_date" class="easyui-datebox" required="true"  value="${end}"></input>
								</li>
								<li>
									<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryAssessResultList()">查询</a>	 
					    		</li>
					    		<li>
									<a href="#" class="easyui-linkbutton"  plain="true" onclick="exportResultList()">导出</a>	 
					    		</li>
					    		<li>
									<a href="#" class="easyui-linkbutton"  plain="true" onclick="getMapList()">地图</a>	 
					    		</li>
					    	</ul>
					</fieldset>
			</div>
	</div>
	<div   style="margin:5px 5px 0px 5px;;">
		<table id="assessResult_grid"  border="false"></table>
		<div id="near_dialog" class="easyui-dialog" style="width:430px;height:150px;" closed="true" cache="false" modal="true">
			<div id="near_dialog_detail" style="margin-top:20px"> </div>
		
		</div>
		
		<div id="earlyWarning_detail" class="easyui-dialog" style="width:830px;height:500px;" closed="true" cache="false" modal="true">
			<div id="fangzi_detail" style="align:center;margin:10px;height:100px;padding-left:40px;padding-top:20px;border:1px solid #ddd;background-color:#fff"> </div>
			<span id="chart01_msg" style="width:400px;"></span>
			<div id="bodongchart" style="margin:10px;height:280px;border:1px solid #ddd;background-color:#fff"></div>
		</div>
		<div id="map_dialog" class="easyui-dialog" style="width:630px;height:350px;" closed="true" cache="false" modal="true">
			<div id="allmap" style="width:600px;height:300px"></div>
			
		</div>
		<div id="mapAll_dialog" class="easyui-dialog" style="width:630px;height:350px;" closed="true" cache="false" modal="true">
			<div id="listmap" style="width:600px;height:300px"></div>
			
		</div>
	</div>
	<iframe height="0" width="0" id="downloadFrame"></iframe>
</div>