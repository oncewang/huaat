<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function(){
		$('#selector_grid').datagrid({
	        url:'${path}/${url}',  
	        //title:'数据列表',
	        //iconCls:'icon-save',
			//height:355,
			//width:960,
			method : "get",
			nowrap: false,
			striped: true,
			collapsible:true,
			remoteSort: false,
			idField:'id',
			//pagination:true,
			rownumbers:true,
			singleSelect:${singleSelect=='true'?true:false},
	        columns:[[
					<c:if test="${singleSelect!='true'}">
					{field:'ck',checkbox:true},
					</c:if>
	        		{field:'name',title:'名称',width:200,sortable:true},
	        		{field:'des',title:'描述',width:200,sortable:true}
	        ]],
	        toolbar:[{
				text:'选择',
				iconCls:'icon-ok',
				handler:function(){
					${callback}();
					$("#commonDialog").dialog('close');
				}
			}],
			onDblClickRow:function(){
				${callback}();
				$("#commonDialog").dialog('close');
			}
	    });
	    });
</script>
<div>${url}
	<table id="selector_grid"></table>
</div>