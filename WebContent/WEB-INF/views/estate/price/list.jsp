<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function() {
		$('#estate_price_grid').datagrid(
				{
					url : '${path}/estatePrice/list',
					title:'菜单列表',
					//iconCls:'icon-save',
					height : 355,
					width : 960,
					nowrap : false,
					striped : true,
					remoteSort : false,
					idField : 'id',
					pagination : true,
					rownumbers : true,
					singleSelect : true,
					columns : [ [ 
						{field : 'province',title : '省份',width : 60,sortable : true}, 
						{field : 'city',title : '地市',width : 60,sortable : true}, 
						{field : 'area',title : '区/县',width : 60,sortable : true},
						{field : 'address',title : '地址',width : 120,sortable : true},
					 	{field : 'community',title : '小区',width : 120,sortable : true},
					 	{field : 'price',title : '均价',width : 60,sortable : true},
						{field : 'estateType',title : '房屋用途',width : 60,sortable : true}, 
						{field : 'estateTime',title : '建造时间',width : 60,sortable : true},
						{field : 'dtaDate',title : '更新时间',width : 130,sortable : true},
						{field : 'longitude',title : '经度',width : 120,sortable : true},
						{field : 'latitude',title : '纬度',width : 120,sortable : true} 
					] ],
					 toolbar:[{
							text:'新增',
							iconCls:'icon-add',
							handler:function(){
						 		newEstate_price();
							}
						},'-',{
							text:'修改',
							iconCls:'icon-edit',
							handler:function(){
								modifyEstate_price();
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								delEstate_price();
							}
						}],
					onDblClickRow : function(rowIndex, rowData) {
						$("#estate_price_detail").dialog('open').dialog("refresh",'${path}/estatePrice/detail?id=' + rowData.id);
					}
				});
		$("#estate_price_detail").dialog( {
			title : '明细信息',
			width : 450,
			height : 400,
			closed : true,
			cache : false,
			//href: '${path}/estatePrice/detail',
			collapsible : true,
			minimizable : true,
			resizable : true,
			maximizable : true,
			modal : true,
			buttons : [{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					saveEstate_price();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#estate_price_detail').dialog('close');
				}
			}]  
		});
	})
	function newEstate_price() {
		$("#estate_price_detail").dialog('open').dialog("refresh", '${path}/estatePrice/detail');
	}

	function modifyEstate_price() {
		var row = $('#estate_price_grid').datagrid('getSelected');
		if (row) {
			$("#estate_price_detail").dialog('open').dialog("refresh",'${path}/estatePrice/detail?id=' + row.id);
		} else {
			$.messager.alert('提示','请选择待操作的记录！');
		}
	}

	function saveEstate_price() {
		$('#estate_price_form').form('submit', {
			url : '${path}/estatePrice/detail',
			onSubmit : function() {
				return $('#estate_price_form').form('validate');
			},
			success : function(data) {
				$('#estate_price_detail').dialog('close');
				$('#estate_price_grid').datagrid('reload');
				easyuiMsg(data);
		}
		});
	}

	function delEstate_price() {
		var row = $('#estate_price_grid').datagrid('getSelected');
		var index = $('#estate_price_grid').datagrid('getRowIndex', row);
		//alert(index);
		if (row) {
			$.messager.confirm('请确认', '请确认是否删除选择的记录?', function(r) {
				if (r) {
					jQuery.get("${path}/estatePrice/delete/" + row.id, function(
							msg) {
						$('#estate_price_grid').datagrid('deleteRow', index);
						$('#estate_price_grid').datagrid('reload');
						//easyuiMsg($.toJSON(msg));
						easyuiMsg(msg);
					});
				}
			});
		} else {
			$.messager.alert('提示','请选择要删除的记录！');
		}
	}
	function query_estate_price() {
		$('#estate_price_grid').datagrid('options').queryParams = {
			"queryParams" : $.toJSON(getQueryParamArray("estate_price_filter_param"))
		};
		$('#estate_price_grid').datagrid('reload');
	}
	
	function updateLngAndLat(){
		
		updateByid('');
		
	}
	
	function updateByid(id){
		$.ajax({
			type: "get",
			url: "${path}/estate/assess/UpdateLngAndLat?id="+id,
			success: function(msg,status){
				if(msg!='timeout'){
									
				}
			}
		});
	}
</script>
<div style="width:100%;height:660px">
	<div class="easyui-layout" fit="true">
		<div region="north" border="false" split="false" style="height: 45px;" title="">
			<div fit="true" class="easyui-panel"  collapsible="false" border="false"   align="center">
				<div class="filter">
					<fieldset>
					    	<ul>
								<li class="estate_price_filter_param"> 
									省份:<input type="text"/>
									<div style="display: none;">province</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">2</div> 
								</li>
								<li class="estate_price_filter_param">
									地市:<input type="text"/>
									<div style="display: none;">city</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">2</div> 
								</li>
								<li class="estate_price_filter_param">
									区县:<input type="text"/>
									<div style="display: none;">area</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">2</div> 
								</li>
								<li class="estate_price_filter_param">
									地址:<input type="text"/>
									<div style="display: none;">address</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">2</div> 
								</li>
					    		<li>
									<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query_estate_price()">查询</a>	 
					    		</li>
					    		<li>
					    			<a href="#" onclick="updateLngAndLat()" class="easyui-linkbutton"  plain="true">地址库坐标更新</a>
					    		</li>
					    	</ul>
					</fieldset>
				</div>
			</div>
		</div>
		<div region="center" border="false"  style="margin-left:5px;">
			<table id="estate_price_grid" fit="true" border="false"></table>
		</div>
	</div>
</div>
<div id="estate_price_detail"></div>