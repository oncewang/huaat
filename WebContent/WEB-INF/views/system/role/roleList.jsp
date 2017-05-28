<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function(){
		$('#TRole_grid').datagrid({
	        url:'${path}/sys/role/list',  
	        title:'角色列表',
	       // iconCls:'icon-save',
	       	height:355,
			width:960,
			nowrap: false,
			striped: true,
			remoteSort: false,
			idField:'id',
			pagination:true,
			rownumbers:true,
			singleSelect:true,
	        columns:[[
	        		{field:'name',title:'角色名称',width:200,sortable:true},
	        		{field:'des',title:'角色描述',width:200,sortable:true}
	        ]],
	        toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
	        		newTRole();
				}
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					modifyTRole();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					delTRole();
				}
			}],
	        onDblClickRow:function(rowIndex, rowData){
	        	$("#TRole_detail").dialog('open').dialog("refresh",'${path}/sys/role/detail?id='+rowData.id);
	        }
	    });
		$("#TRole_detail").dialog({   
			title: '明细信息',   
			width: 600,   
			height: 300,   
			closed: true,   
			cache: false,   
			//href: '${path}/sys/role/detail',
			collapsible:true,
			minimizable:true,
			resizable:true,
			maximizable:true,
			modal: true,
			buttons : [{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					saveTRole();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#TRole_detail').dialog('close');
				}
			}]  
		});
	})
	function newTRole(){
		$("#TRole_detail").dialog('open').dialog("refresh",'${path}/sys/role/detail');
	}
	
	function modifyTRole(){
		var row = $('#TRole_grid').datagrid('getSelected');
		if(row){
			$("#TRole_detail").dialog('open').dialog("refresh",'${path}/sys/role/detail?id='+row.id);
		}else{
			$.messager.alert('提示','请选择待操作的记录！');  
		}
	}
	
	function saveTRole(){
		$('#TRole_form').form('submit', {
			url:'${path}/sys/role/detail',
			onSubmit: function(){
				return $('#TRole_form').form('validate');
			},
			success: function(data){
				$('#TRole_detail').dialog('close');
				$('#TRole_grid').datagrid('reload');
				//alert($.toJSON(msg));
				easyuiMsg(data);
			}
		});
	}
	
	function delTRole(){
		var row = $('#TRole_grid').datagrid('getSelected');
		var index=$('#TRole_grid').datagrid('getRowIndex', row);
		if(row){
			$.messager.confirm('请确认','请确认是否删除选择的记录?',function(r){   
				if (r){ 
					jQuery.get("${path}/sys/role/delete/"+row.id,function(msg){
						$('#TRole_grid').datagrid('deleteRow',index); 
						$('#TRole_grid').datagrid('reload');
						easyuiMsg(msg);
					});
				}   
			}); 
		}else{
			$.messager.alert('提示','请选择要删除的记录！'); 
		}
	}
	function queryTRole(){
		$('#TRole_grid').datagrid('options').queryParams={"queryParams":$.toJSON(getQueryParamArray("TRole_filter_param"))}; 
		$('#TRole_grid').datagrid('reload');
	}
	
</script>
<div style="width:100%;height:600px">
<div class="easyui-layout" fit="true">
	<div region="north" border="false" split="false" style="height:45px;" title="" >
		<div fit="true" class="easyui-panel"  collapsible="false" border="false"  align="center">
			<div class="filter">
				<fieldset>
				    	<ul>
							<li class="TRole_filter_param"> 
								角色名称:<input type="text"/>
								<div style="display: none;">name</div>
								<div style="display: none;">1</div>
								<div style="display: none;">1</div>
							</li>
				    		<li>
							    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryTRole()">查询</a>
				    		</li>
				    	</ul>
				</fieldset>
			</div>
		</div>
	</div>
	<div region="center" border="false"  style="margin-left:5px;">
		<table id="TRole_grid" fit="true"  border="false"></table>
	</div>
</div>
<div id="TRole_detail"></div>
</div>