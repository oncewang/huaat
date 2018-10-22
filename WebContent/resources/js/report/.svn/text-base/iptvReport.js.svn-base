function  tr_iptv01(){
			$.ajax({
				type: "post",
				url: "${path}/report/iptv/iptv01",
				success: function(msg,status){
					var rs = $.evalJSON(msg);
					if(rs.success){
						 var chart = new FusionCharts("${path}/resources/FusionCharts/Charts/MSColumn3D.swf?ChartNoDataText=没有符合条件的数据，请重新查询！","ChartId", "1000","450");
						 chart.setDataXML(rs.chartxml);
						   chart.render("tr_iptv01_div");
						   $("#tr_iptv01_table").empty().html(rs.trs);
					}else{
						alert("查询出错");
					}
				}
			});
		}
		
		function  tr_iptv02(){
			$.ajax({
				type: "post",
				url: "${path}/report/iptv/iptv02",
				success: function(msg,status){
					var rs = $.evalJSON(msg);
					if(rs.success){
						 var chart = new FusionCharts("${path}/resources/FusionCharts/Charts/MSColumn3D.swf?ChartNoDataText=没有符合条件的数据，请重新查询！","ChartId", "1000","450");
						 chart.setDataXML(rs.chartxml);
						   chart.render("tr_iptv02_div");
						   $("#tr_iptv02_table").empty().html(rs.trs);
					}else{
						alert("查询出错");
					}
				}
			});
		}