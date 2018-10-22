<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function(){
		$('#${pojoName}_grid').datagrid({
	        url:'${"$"+"{path}"}/${modelName}/${prefixClassName}/list',  
	        title:'数据列表',
	        //iconCls:'icon-save',
			height:355,
			width:960,
			nowrap: true,
			striped: true,
			collapsible:true,
			remoteSort: false,
			idField:'id',
			pagination:true,
			rownumbers:true,
			singleSelect:true,
	        columns:[[
	        		<#list gridColumns?if_exists as column>
	        		{field:'${column.columnName}',title:'${column.comment}',width:200,sortable:true}<#if column_has_next>,</#if>
	        		</#list>  
	        ]],
	        toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					new${pojoName}();
				}
			},{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					modify${pojoName}();
				}
			},{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					del${pojoName}();
				}
			}],
	        onDblClickRow:function(rowIndex, rowData){
	        	$("#${pojoName}_detail").dialog('open').dialog("refresh",'${"$"+"{path}"}/${modelName}/${prefixClassName}/detail?id='+rowData.id);
	        }
	    });
		$("#${pojoName}_detail").dialog({   
			title: '明细信息',   
			width: 600,   
			height: 300,   
			closed: true,   
			cache: false,   
			//href: '${"$"+"{path}"}/${modelName}/${prefixClassName}/detail',
			collapsible:true,
			minimizable:true,
			resizable:true,
			maximizable:true,
			modal: true,
			buttons : [{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					save${pojoName}();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#${pojoName}_detail').dialog('close');
				}
			}]  
		});
	})
	function new${pojoName}(){
		$("#${pojoName}_detail").dialog('open').dialog("refresh",'${"$"+"{path}"}/${modelName}/${prefixClassName}/detail');
	}
	
	function modify${pojoName}(){
		var row = $('#${pojoName}_grid').datagrid('getSelected');
		if(row){
			$("#${pojoName}_detail").dialog('open').dialog("refresh",'${"$"+"{path}"}/${modelName}/${prefixClassName}/detail?id='+row.id);
		}else{
			alert('请选择待操作的记录！');  
		}
	}
	
	function save${pojoName}(){
		$('#${pojoName}_form').form('submit', {
			url:'${"$"+"{path}"}/${modelName}/${prefixClassName}/detail',
			onSubmit: function(){
				return $('#${pojoName}_form').form('validate');
			},
			success: function(data){
				$('#${pojoName}_detail').dialog('close');
				$('#${pojoName}_grid').datagrid('reload');
				//alert($.toJSON(msg));
				easyuiMsg(data);
			}
		});
	}
	
	function del${pojoName}(){
		var row = $('#${pojoName}_grid').datagrid('getSelected');
		var index=$('#${pojoName}_grid').datagrid('getRowIndex', row);
			//alert(index);
		if(row){
			$.messager.confirm('请确认','请确认是否删除选择的记录?',function(r){   
				if (r){ 
					jQuery.get("${"$"+"{path}"}/${modelName}/${prefixClassName}/delete/"+row.id,function(msg){
						$('#${pojoName}_grid').datagrid('deleteRow',index); 
						$('#${pojoName}_grid').datagrid('reload');
						easyuiMsg(msg);
					});
				}   
			}); 
		}else{
			alert('请选择要删除的记录！'); 
		}
	}
	function query${pojoName}(){
		$('#${pojoName}_grid').datagrid('options').queryParams={"queryParams":$.toJSON(getQueryParamArray("${pojoName}_filter_param"))}; 
		$('#${pojoName}_grid').datagrid('reload');
	}
	
</script>

<div class="easyui-layout" fit="true">
	<div region="north" border="false" split="false" style="height: 80px;" title="搜索条件">
		<div class="filter">
			<fieldset>
			    	<ul>
			    		<#list girdQueryParams ?if_exists as queryParam>
						<li class="${pojoName}_filter_param"> 
							${queryParam.describe} :<input type="text"/>
							<div style="display: none;">${queryParam.id}</div>
							<div style="display: none;">${queryParam.type}</div><!-- 1 字符、2数字 -->
							<div style="display: none;">${queryParam.relation}</div> 
						</li>
						</#list>
			    		<li>
						    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="query${pojoName}()">查询</a>
			    		</li>
			    	</ul>
			</fieldset>
		</div>
	</div>
	<div region="center" border="false">
		<table id="${pojoName}_grid" fit="true" border="true"></table>
	</div>
</div>
<div id="${pojoName}_detail"></div>

