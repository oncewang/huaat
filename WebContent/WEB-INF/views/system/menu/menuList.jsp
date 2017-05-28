<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function() {
		$('#TMenu_grid').datagrid(
				{
					url : '${path}/sys/menu/list',
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
						{field : 'name',title : '菜单名称',width : 150,sortable : true}, 
						{field : 'url',title : 'url',width : 150,sortable : true}, 
						{field : 'openType',title : '打开方式',width : 120,sortable : true,
							formatter : function(value) {
								if (value == '1') {
									return '直接打开';
								} else if (value == '2') {
									return 'iframe方式';
								}else if(value=='3'){
									return 'iframe方式（跨应用）';
								}
							}},
					 	{field : 'isLeaf',title : '菜单类型',width : 80,sortable : true,
							formatter : function(value) {
								if (value == 'N') {
									return '父级菜单';
								} else if (value == 'Y') {
									return '子级菜单';
								}else if (value == 'T') {
									return '导航栏';
								}
							}},
						{field : 'des',title : '菜单描述',width : 150,sortable : true}, 
						{field : 'orderRow',title : '显示顺序',width : 65,sortable : true}, 
						{field : 'updateTime',title : '更新时间',width : 150,sortable : true} 
					] ],
					 toolbar:[{
							text:'新增',
							iconCls:'icon-add',
							handler:function(){
						 		newTMenu();
							}
						},'-',{
							text:'修改',
							iconCls:'icon-edit',
							handler:function(){
								modifyTMenu();
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								delTMenu();
							}
						}],
					onDblClickRow : function(rowIndex, rowData) {
						$("#TMenu_detail").dialog('open').dialog("refresh",'${path}/sys/menu/detail?id=' + rowData.id);
					}
				});
		$("#TMenu_detail").dialog( {
			title : '明细信息',
			width : 600,
			height : 300,
			closed : true,
			cache : false,
			//href: '${path}/sys/menu/detail',
			collapsible : true,
			minimizable : true,
			resizable : true,
			maximizable : true,
			modal : true,
			buttons : [{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					saveTMenu();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#TMenu_detail').dialog('close');
				}
			}]  
		});
	})
	function newTMenu() {
		$("#TMenu_detail").dialog('open').dialog("refresh", '${path}/sys/menu/detail');
	}

	function modifyTMenu() {
		var row = $('#TMenu_grid').datagrid('getSelected');
		if (row) {
			$("#TMenu_detail").dialog('open').dialog("refresh",'${path}/sys/menu/detail?id=' + row.id);
		} else {
			$.messager.alert('提示','请选择待操作的记录！');
		}
	}

	function saveTMenu() {
		var val = $('#isLeaf').combobox('getValue');
		if(val=="N"){
			$("#parentId").val($("#parentIdN").combobox('getValue'));
			$("#openType").val("");
			$("#url").val("");
			$("#iconUrl").val("");
		}else if(val=="Y"){
			$("#parentId").val($("#parentIdY").combobox('getValue'));
			$("#iconUrl").val("");
		}else if(val=="T"){
			$("#parentId").val("");
			$("#openType").val("");
			$("#url").val("");
		}
		$('#TMenu_form').form('submit', {
			url : '${path}/sys/menu/detail',
			onSubmit : function() {
				return $('#TMenu_form').form('validate');
			},
			success : function(data) {
				$('#TMenu_detail').dialog('close');
				$('#TMenu_grid').datagrid('reload');
				easyuiMsg(data);
		}
		});
	}

	function delTMenu() {
		var row = $('#TMenu_grid').datagrid('getSelected');
		var index = $('#TMenu_grid').datagrid('getRowIndex', row);
		//alert(index);
		if (row) {
			$.messager.confirm('请确认', '请确认是否删除选择的记录?', function(r) {
				if (r) {
					jQuery.get("${path}/sys/menu/delete/" + row.id, function(
							msg) {
						$('#TMenu_grid').datagrid('deleteRow', index);
						$('#TMenu_grid').datagrid('reload');
						//easyuiMsg($.toJSON(msg));
						easyuiMsg(msg);
					});
				}
			});
		} else {
			$.messager.alert('提示','请选择要删除的记录！');
		}
	}
	function queryTMenu() {
		$('#TMenu_grid').datagrid('options').queryParams = {
			"queryParams" : $.toJSON(getQueryParamArray("TMenu_filter_param"))
		};
		$('#TMenu_grid').datagrid('reload');
	}
</script>
<div style="width:100%;height:660px">
	<div class="easyui-layout" fit="true">
		<div region="north" border="false" split="false" style="height: 45px;" title="">
			<div fit="true" class="easyui-panel"  collapsible="false" border="false"   align="center">
				<div class="filter">
					<fieldset>
					    	<ul>
								<li class="TMenu_filter_param"> 
									菜单名称:<input type="text"/>
									<div style="display: none;">name</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">2</div> 
								</li>
								<li class="TMenu_filter_param">
									url地址:<input type="text"/>
									<div style="display: none;">url</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">1</div> 
								</li>
								<li class="TMenu_filter_param">
									 上级菜单:<select  onchange="queryTMenu()">
												<option value="">--请选择--</option>
													<c:forEach items="${menuItem}" var="list">
		   												<option value="${list.id}" >${list.name}</option>	
													</c:forEach>
											</select>
									<div style="display: none;">parentId</div>
									<div style="display: none;">1</div><!-- 1 字符、2数字 -->
									<div style="display: none;">1</div> 
								</li>
					    		<li>
									<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryTMenu()">查询</a>	 
					    		</li>
					    	</ul>
					</fieldset>
				</div>
			</div>
		</div>
		<div region="center" border="false"  style="margin-left:5px;">
			<table id="TMenu_grid" fit="true" border="false"></table>
		</div>
	</div>
</div>
<div id="TMenu_detail"></div>