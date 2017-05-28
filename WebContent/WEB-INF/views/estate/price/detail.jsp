<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common.jsp" %>
	<head>
	<script>
		$(function(){	
			var pri_opts="";
			da = new Date();
	       	var  nowYear = +da.getFullYear();
			for(var i=nowYear;i>1900;i--){
				pri_opts=options+"<option value='"+i+"'>"+i+"</option>";
			}
			$("#pri_build_date").empty().append(pri_opts);
		});
		
		
		function getShengfen01(){
			var cit = "";  
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
		           			$("#province").empty().append(cit); 
		           			$("#province").attr("value",'10');//默认选中上海
		           			getCity();
						}else{
							alert("查询出错");
						}
					}
				}
			});
			
		}
		
		function getCity01(){
			var cit = ""; 
			$("#province_input").val($("#province").find("option:selected").text());
			
			$.ajax({
				type: "post",
				url: "${path}/areas/getList",
				data:{
					"parentid":$("#province").val()
				},
				success: function(msg,status){
					if(msg!='timeout'){
						var rs = $.evalJSON(msg);
						if(rs.success){
							$.each(rs.list, function(i, n){
		               			 cit+="<option value="+n[0]+">"+n[1]+"</option>" ;
							});
		           			$("#city").empty().append(cit);
		           			getquxian();
						}else{
							alert("查询出错");
						}
					}
				}
			});
			
		}
		
		function getquxian01(){
			var cit = ""; 
			$("#city_input").val($("#city").find("option:selected").text());
			$("#area_input").val($("#area").val());
			$.ajax({
				type: "post",
				url: "${path}/areas/getList",
				data:{
					"parentid":$("#city").val()
				},
				success: function(msg,status){
					if(msg!='timeout'){
						var rs = $.evalJSON(msg);
						if(rs.success){
							$.each(rs.list, function(i, n){
		               			 cit+="<option value="+n[0]+">"+n[1]+"</option>" ;
							});
		           			$("#area").empty().append(cit); 
		           			$("#area_input").val($("#area").find("option:selected").text());
						}else{
							alert("查询出错");
						}
					}
				}
			});
			
		}
		
	</script>
</head>
<div class="grid_detail">
	<form id="estate_price_form" method="post">
		<input type="hidden" name="id" value="${o.id}"/>
		<ul>
			<li>
				<label>省份</label>
				<input  id="province_input"  value="${o.province}"  style="width:100px" required="true"  name="province"/>
													
			</li>	
			<li>
				<label>地市</label>
				<input id="city_input"  value="${o.city}"  required="true"  style="width:100px" name="city"/>
													
			</li>
			<li>
				<label>区县</label>
				<input  id="area_input"  value="${o.area}"  required="true"  style="width:100px" name="area"/>
														
			</li>
			<li>
				<label>地址</label>
				<input type="text" class="easyui-validatebox" name="address" required="true"   value="${o.address}"/>
			</li>	
			<li>
				<label>小区</label>
				<input type="text" class="easyui-validatebox" name="community"   value="${o.community}"/>
			</li>
			<li>
				<label>均价</label>
				<input id="room_area" style="width:98px;margin:0px" class="easyui-numberbox" precision="0"  value="${o.price}" name="price" required="true"  />
			</li>
			<li>
				<label>房产证号</label>
				<input type="text" class="easyui-validatebox" name="estateCard"     value="${o.estateCard}"/>
			</li>
			<li>
				<label>房屋用途</label>
				<select id="estateType" name="estateType"  class="easyui-combobox"  panelHeight="80px" style="width:102px;">
					<option value="">--请选择--</option>
					<option value="1"  ${o.estateType=='1'?'selected':''}>住宅</option>
   				 	<option value="2"  ${o.estateType=='2'?'selected':''}>商用</option>
   				 	<option value="3"  ${o.estateType=='3'?'selected':''}>商住两用</option>
								
				</select> 
			</li>
			<li>
				<label>建造年份</label>
				<input style="width:98px;margin:0px" class="easyui-numberbox" precision="0"  value="${o.estateTime}"  name="estateTime"   />
		
			</li>
		</ul>
	</form>
</div>
