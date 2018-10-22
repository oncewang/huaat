<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function() {
		getShengfen();
		
		var options="";
		d = new Date();
       	var  nowYear = +d.getFullYear();
		for(var i=nowYear;i>1900;i--){
			options=options+"<option value='"+i+"'>"+i+"</option>";
		}
		
		$("#build_date").empty().append(options);
		$('#assess_grid').datagrid(
				{
					url : '${path}/estate/assess/templist',
					title:'数据列表',
					//iconCls:'icon-save',
					height : 355,
					nowrap : false,
					striped : true,
					remoteSort : false,
					queryParams : {"batch":"not_pinggu"},
					idField : 'data0',
					pagination : true,
					rownumbers : true,
					singleSelect : true,
					columns : [ [ 
									
									{field : 'province',title : '省份',width : 80,sortable : true},
									{field : 'city',title : '地市',width : 80,sortable : true},
									{field : 'area',title : '区/县',width : 80,sortable : true},
									{field : 'address',title : '地址',width : 200,sortable : true},
									{field : 'buildingNo',title : '幢\栋',width : 80,sortable : true},
									{field : 'allFloor',title : '总楼层',width : 80,sortable : true,
										formatter : function(value) {
											  return	"共 "+value+" 层";
											}
									},
									{field : 'roomFloor',title : '楼层',width : 80,sortable : true,
										formatter : function(value) {
											  return	"第 "+value+" 层";
											}	
									},
									{field : 'buildDate',title : '建造日期',width : 120,sortable : true},
									{field : 'housingProperty',title : '房屋用途',width : 120,sortable : true,
										formatter : function(value) {
											if(value=="1"){
												return "住宅";
											}else if(value=="2"){
												return "商用";
											}else if(value=="3"){
												return "商住两用";
											}else{
												return "--";
											}
										}		
									},
									{field : 'roomArea',title : '房屋面积',width : 100,sortable : true,
										formatter : function(value) {
											  return	value+" 平方米";
											}	
									},
									{field : 'estateCard',title : '房产证号',width : 100,sortable : true},
									{field : 'id',title : '删除',width : 100,sortable : true,
										formatter : function(value) {
											return "<a href='#' onclick=delteTemp('"+value+"')>删除</a>";
										}
									
									}
								] ]
				});
		
		
		$('#assessbath_grid').datagrid(
				{
					//url : '${path}/estate/assess/templist',
					title:'数据列表',
					//iconCls:'icon-save',
					height : 355,
					nowrap : false,
					striped : true,
					remoteSort : false,
					//queryParams : {"batch":"not_pinggu"},
					idField : 'data0',
					pagination : true,
					rownumbers : true,
					singleSelect : true,
					columns : [ [ 
									
									{field : 'province',title : '省份',width : 80,sortable : true},
									{field : 'city',title : '地市',width : 80,sortable : true},
									{field : 'area',title : '区/县',width : 80,sortable : true},
									{field : 'address',title : '地址',width : 200,sortable : true},
									{field : 'buildingNo',title : '幢\栋',width : 80,sortable : true,
										formatter : function(value) {
											  return	"第 "+value+" 栋";
											}	
									},
									{field : 'allFloor',title : '总楼层',width : 80,sortable : true,
										formatter : function(value) {
											  return	"共 "+value+" 层";
											}
									},
									{field : 'roomFloor',title : '楼层',width : 80,sortable : true,
										formatter : function(value) {
											  return	"第 "+value+" 层";
											}	
									},
									{field : 'buildDate',title : '建造日期',width : 120,sortable : true},
									{field : 'housingProperty',title : '房屋用途',width : 120,sortable : true,
										formatter : function(value) {
											if(value=="1"){
												return "住宅";
											}else if(value=="2"){
												return "商用";
											}else if(value=="3"){
												return "商住两用";
											}else{
												return "--";
											}
										}		
									},
									{field : 'roomArea',title : '房屋面积',width : 100,sortable : true,
										formatter : function(value) {
											  return	value+" 平方米";
											}	
									},
									{field : 'estateCard',title : '房产证号',width : 100,sortable : true},
									{field : 'id',title : '删除',width : 100,sortable : true,
										formatter : function(value) {
											return "<a href='#' onclick=delteBatchTemp('"+value+"')>删除</a>";
										}
									
									}
								] ]
				});
		
		

		
	});
	
	function saveEstateTemp() {
		
		$('#estate_temp_form').form('submit', {
			url : '${path}/estate/assess/save',
			onSubmit : function() {
				return $('#estate_temp_form').form('validate');
			},
			success : function(data) {
				queryTempList();
		}
		});
		
	}
	
	function queryTempList() {
		//$('#assess_grid').datagrid('options').url='${path}/estate/assess/templist';
		//$('#assess_grid').datagrid('reload');
		
		$('#assess_grid').datagrid('options').url='${path}/estate/assess/templist';
		$('#assess_grid').datagrid('options').queryParams = {
				"batch":"batchId"
			};
		$('#assess_grid').datagrid('reload');
		
		
		
	}
	
	function batchTempList(){
		
		$('#assessbath_grid').datagrid('options').url='${path}/estate/assess/templist';
		$('#assessbath_grid').datagrid('options').queryParams = {
				"batch":"bat_batchId"
			};
		$('#assessbath_grid').datagrid('reload');
		
		
	}
	
	function delteTemp(id){
		jQuery.get("${path}/estate/assess/delete/" +id, function(
				msg) {
				queryTempList();
				easyuiMsg(msg);
		});
		
	}
	
	function delteBatchTemp(id){
		jQuery.get("${path}/estate/assess/delete/" +id, function(
				msg) {
				batchTempList();
				easyuiMsg(msg);
		});
		
	}
	
	
	function pinggu(){
		$("#pinggu_butt").empty().html("正在评估");
		$.ajax({
			type: "post",
			url: "${path}/estate/assess/pinggu",
			data:{"batch":"batchId"},
			success: function(msg,status){
				if(msg!='timeout'){
					var rs = $.evalJSON(msg);
					if(rs.success){
						//如果显示桌面，评估结果页面索引为2，不显示为1
						if("${role.homePageUrl}"!=""){
							$('#main-tabs').tabs("select",2);
						}else{
							$('#main-tabs').tabs("select",1);
						}
						
						$('#assessResult_grid').datagrid('options').url='${path}/estate/assessResult/list';
						$('#assessResult_grid').datagrid('options').queryParams = {
							"start_date" : "${start}",
							"end_date" : "${end}"
						};
						$('#assessResult_grid').datagrid('reload');
						$("#pinggu_butt").empty().html("开始评估");
						 queryTempList();
						
					}else{
						alert("查询出错");
					}
				}
			}
		});
		
	}
	
function batchPinggu(){
		$("#pinggu_batch_butt").empty().html("正在评估");
	
		$.ajax({
			type: "post",
			url: "${path}/estate/assess/pinggu",
			data:{"batch":"bat_batchId"},
			success: function(msg,status){
				if(msg!='timeout'){
					var rs = $.evalJSON(msg);
					if(rs.success){
						if("${role.homePageUrl}"!=""){
							$('#main-tabs').tabs("select",2);
						}else{
							$('#main-tabs').tabs("select",1);
						}
						$('#assessResult_grid').datagrid('options').url='${path}/estate/assessResult/list';
						$('#assessResult_grid').datagrid('options').queryParams = {
							"start_date" : "${start}",
							"end_date" : "${end}"
						};
						$('#assessResult_grid').datagrid('reload');
						$("#pinggu_batch_butt").empty().html("开始评估");
						batchTempList();
						 
					}else{
						alert("查询出错");
					}
				}
			}
		});
		
	}
	
 function downloadFile(){
	 $.ajax({
			type: "get",
			url: "${path}/estate/assess/downLoad",
			success: function(msg,status){
			
			}
	 });
	 
	 
 }

	
	function getListByParentId(parentid){
		$.ajax({
			type: "post",
			url: "${path}/areas/getList",
			data:{
				"parentid":$("#pg_epd_id").val()
				
			},
			success: function(msg,status){
				if(msg!='timeout'){
					var rs = $.evalJSON(msg);
					if(rs.success){
						$("#pinggu_price").empty().html("评估价格："+rs.price+"元");
					}else{
						alert("查询出错");
					}
				}
			}
		});
	}
	
	function queryAssessBathList(){
		$('#file_form').form('submit', {
			url : '${path}/estate/assess/upLoad',
			onSubmit : function() {
				return $('#file_form').form('validate');
			},
			success : function(data) {
				batchTempList();
		}
		});
		
	}
	
	

	function getShengfen(){
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
	
	function getCity(){
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
	
	function getquxian(){
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
	
	function areaOnchange(){
		$("#area_input").val($("#area").find("option:selected").text());
	}
	
	
	
	
	
	
</script>

 <div class="easyui-tabs tab-inner"  data-options="border:false,plain:true">
        <div title="单套评估" style="widht:100%;">
           	<div   style="height:auto;margin-bottom:5px;" >
							<div class="filter" >
								<form id="estate_temp_form" method="post">
									<fieldset>
									    	<ul>
									    		<li class=" ">
									    			<dl>
														<dt>省份</dt>
														<dd>
															<select id="province"   onchange="getCity()"></select>
															<input type="hidden" id="province_input" name="province"/>
														</dd>
													
													</dl>
									    		</li>
									    		<li class=" ">
									    			<dl>
														<dt>地市</dt>
														<dd>
															<select id="city"  onchange="getquxian()"></select>
															<input type="hidden" id="city_input" name="city"/>
														</dd>
													
													</dl>
									    		</li>
									    		<li class=" ">
									    			<dl>
														<dt>区/县</dt>
														<dd>
															<select id="area" onchange="areaOnchange()" ></select>
															<input type="hidden" id="area_input" name="area"/>
														</dd>
													
													</dl>
									    		</li>
												<li class=" "> 
													<dl>
														<dt>地址</dt>
														<dd class="add_dd">
														<input id="address"  class="easyui-validatebox" name="address" required="true" style="width:265px;" type="text"   />
														</dd>
													</dl>
												</li>
												<li class=" "> 
													<dl>
														<dt>幢\栋</dt>
														<dd><input id="building_no" name="buildingNo" type="text"   /></dd>
													</dl>
												</li>
											</ul>
											<ul>
												<li class=" "> 
													<dl>
														<dt>总楼层</dt>
														<dd><input id="all_floor" name="allFloor" type="text"   /></dd>
													
													</dl>
													
												</li>
												<li class=" "> 
													<dl>
														<dt>楼层</dt>
														<dd><input id="room_floor"  class="easyui-validatebox" name="roomFloor" required="true"  type="text"   /></dd>
													</dl>
												</li>
												<li class=" "> 
													<dl>
														<dt>面积</dt>
														<dd><input id="room_area" style="width:98px;margin:0px" class="easyui-numberbox" precision="2" name="roomArea" required="true"  /></dd>
													</dl>
												</li>
												<li class=" "> 
													<dl>
														<dt>建造年份</dt>
														<dd>
															<select  id="build_date" name="buildDate"  >
															</select>
														
														</dd>
													</dl>
												</li>
												<li class=" "> 
													<dl>
														<dt>房屋用途</dt>
														<dd>
															<select id="housing_property" name="housingProperty" >
																<option value="0">未知</option>
																<option value="1">住宅</option>
																<option value="2">商用</option>
																<option value="3">商住两用</option>
															</select>
														</dd>
													</dl>
												</li>
												<li class=" "> 
													<dl>
														<dt>房产证号</dt>
														<dd><input id="estateCard"  class="easyui-validatebox" name="estateCard" required="true"  type="text"  /></dd>
													</dl>
												</li>
												<!-- <li class=" ">
									    			<div style="margin-right:20px;"><a href="#" onclick="updateLngAndLat()" style="line-height:40px;margin-right:20px" ><div>地址库坐标更新</div></a></div>
									    			
									    		</li> -->
												<li>
													<dl>
													<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="saveEstateTemp()">确认添加</a>	 
									    			<span id="query_isnull" style="display:none;font-size:8px;color:red"></span>
									    			</dl>
									    		</li>
									    		<li>
									    			
									    		</li>
									    	</ul>
									</fieldset>
								</form>
							</div>
				</div>
				
				<div   style="margin:5px 5px 0px 5px;;">
						<table id="assess_grid"  border="false"></table>
						<div style="text-align:right;height:30px;font-size:12px;font-weight: bold;padding:8px 100px 5px 0px;background-color:#eee;border-bottom: 2px solid #ddd">
							<a href="#"  class="a_pinggu" id="pinggu_butt" style="text-decoration: none;" onclick="pinggu()">开始评估</a> 
						</div>
				</div>
				
        </div>
        <div title="批量评估" style="widht:100%;">
          	<div   style="height:auto;margin-bottom:5px;" >
							<div class="filter" >
							<form  id="file_form"   method="post" enctype="multipart/form-data">
									<fieldset style="height:40px;line-height:40px;padding-left:30px;">
									    	<ul>
									    		<li class=" ">
									    		<input id="bath_file" name="file" type="file" style="width:auto"  />
									    		
									    		</li>
									    		<li class=" ">
									    			<div style="margin-right:20px;"><a href="${path}/estate/assess/downLoad" style="line-height:40px;margin-right:20px" ><div>样表下载</div></a></div>
									    			
									    		</li>
												<li>
													<a href="#" class="easyui-linkbutton"  iconCls="icon-add" plain="true"  onclick="queryAssessBathList()">确认添加</a>	 
									    			<span id="ass_bath_msg" style="display:none;font-size:8px;color:red"></span>
									    		</li>
									    	</ul>
									</fieldset>
								</form>
							</div>
				</div>
				<div   style="margin:5px 5px 0px 5px;">
						<table id="assessbath_grid"  border="false"></table>
						<div style="text-align:right;padding-right:100px;height:30px;font-size:12px;font-weight: bold;padding:8px 100px 5px 0px;background-color:#eee;border: 1px solid #ddd">
							<a href="#"  class="a_pinggu" id="pinggu_batch_butt" style="text-decoration: none;" onclick="batchPinggu()">开始评估</a> 
						</div>
				</div>
				
        </div>
  </div>
