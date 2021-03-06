<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>

<script type="text/javascript">
	$(function(){
		var lastIndex;
		
		$('#TUser_grid').datagrid({
	        url:'${path}/user/user/list',  
	        title:'用户列表',
	        //iconCls:'icon-save',
			height:355,
			nowrap: false,
			striped: true,
			remoteSort: false,
			idField:'id',
			pagination:true,
			rownumbers:true,
			singleSelect:true,
	        columns:[[
	        		{field:'name',title:'用户名',width:150,sortable:true,editor:
                 		{
                        	type: 'validatebox',
                        	options:{required: true,missingMessage:'用户名不能为空'}
                   	 	}
                    },
	        		{field:'password',title:'密码',width:130,sortable:true,editor:
                 		{
                       		type: 'validatebox',
                        	options:{required: true,missingMessage:'密码不能为空'}
                    	}
                    },
                    {field:'roleIds',title:'角色信息',width:200,  editor:
             			{
                   			type: 'validatebox',
                    		options:{required: true}
                		},hidden:true
                	}
	        	]],
	        toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
						$('#TUser_grid').datagrid('endEdit', lastIndex);
						$('#TUser_grid').datagrid('appendRow',{
								name:'',
								password:'',
								roleIds:''
						});
						lastIndex = $('#TUser_grid').datagrid('getRows').length-1;
						$('#TUser_grid').datagrid('selectRow', lastIndex);
						$('#TUser_grid').datagrid('beginEdit', lastIndex);
						endEdit();
				}
			},'-',{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
						var rows = $('#TUser_grid').datagrid('getRows');
   						for ( var i = 0; i < rows.length; i++) {
   							if($('#TUser_grid').datagrid('validateRow', i)){
   							
   								$('#TUser_grid').datagrid('endEdit', i);
   							}else{
   								if(lastIndex!=i){
	           						$('#TUser_grid').datagrid('endEdit',lastIndex);
	           					}
   								$('#TUser_grid').datagrid('selectRow', i);
   								var name=$('#TUser_grid').datagrid('getEditor',{index:i,field:'name'}); 
   								var password=$('#TUser_grid').datagrid('getEditor',{index:i,field:'password'}); 
   	   							if((name.target.val()!='')&&(password.target.val()!='')){
   	   								$.messager.alert('提示','用户至少拥有一个角色');
   	   	   						}
   	   							return false;
   							}			
    					}
	            		if ($('#TUser_grid').datagrid('getChanges').length) {
	               			var inserted = $('#TUser_grid').datagrid('getChanges', "inserted");
	               			var updated = $('#TUser_grid').datagrid('getChanges', "updated");
        					var effectRow = new Object();
	               			if (inserted.length){
								effectRow["inserted"] = $.toJSON(inserted);
	                 		}
							if (updated.length) {
								effectRow["updated"] = $.toJSON(updated);
					 		}
							$.ajax({
						   		type: "post",
						   		url:"${path}/user/user/save",
						  		data: effectRow,
						   		async:false,
						   		success: function(msg){
											$('#TUser_grid').datagrid('acceptChanges');
											$('#TUser_grid').datagrid('unselectRow',lastIndex);
											endEdit();
											easyuiMsg(msg);
						  			 }
							});
						$('#TUser_grid').datagrid('reload');
	            	}
				}
			},'-',{
				text:'取消编辑',
				iconCls:'icon-undo',
				handler:function(){
					$('#TUser_grid').datagrid('rejectChanges');
					$('#TUser_grid').datagrid('unselectRow',lastIndex);
					endEdit();
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					delTUser();
				}
			}],
			onClickRow:function(rowIndex, rowData){
	        	$('#TUser_grid').datagrid('endEdit', lastIndex);
	        	$('#TUser_grid').datagrid('beginEdit', rowIndex);
	        	lastIndex = rowIndex;
	        	endEdit();
	        	var roleIds=rowData.roleIds;
	        	if(roleIds!=''&&roleIds!=null){
	        		var roleArr = roleIds.split(",");
	        		for(var i=0;i<roleArr.length;i++){
	        			$('#TUser_role_detail').datagrid('selectRecord',roleArr[i]);
	        		}
	        	}
	        },
	        onSortColumn:function(){
				$('#TUser_grid').datagrid('rejectChanges');
			},
			onAfterEdit:function(rowIndex,rowData,changes){
				onAfterEdit(this,rowIndex,changes);
			},
			onBeforeEdit:function(rowIndex, rowData){
				var arr=new Array();
				arr[0]='name';
				arr[1]='password';
				onBeforeEdit(this,rowIndex,arr);
			}
	    });
		$('#TUser_role_detail').datagrid({
	        url:'${path}/sys/role/list?pagination=false',  
	        title:'用户角色列表',
	       	height:355,
			nowrap: false,
			striped: true,
			remoteSort: false,
			pagination:true,
			idField:'id',
			singleSelect:false,
	        columns:[[
					{field:'ck',checkbox:true},
	        		{field:'name',title:'角色名称',width:150},
	        		{field:'des',title:'角色描述',width:150}
	        ]],
	        tools:[{  
		    		iconCls:'icon-reload',  
	        		handler:function(){
	        			$('#TUser_role_detail').datagrid('reload');
        			}  
	        	}],
	        onSelect:function(rowIndex,rowData){
	        	var row = $('#TUser_grid').datagrid('getSelected');
	        	if(row){
	        		getSelections(this,row);
	        	}else{
	        		$.messager.alert("提示", "请先选择用户！");
	        		$('#TUser_role_detail').datagrid('unselectRow',rowIndex);
	        	}
	        },
	        onUnselect:function(rowIndex, rowData){
	        	var row = $('#TUser_grid').datagrid('getSelected');
	        	if(row){
	        		getSelections(this,row);
	        	}
	        },
	        onSelectAll:function(rows){
	        	var row = $('#TUser_grid').datagrid('getSelected');
	        	if(row){
	        		getSelections(this,row);
	        	}else{
	        		$.messager.alert("提示", "请先选择用户！");
	        		endEdit();
	        	}
	        },
	        onUnselectAll:function(rows){
	        	var row = $('#TUser_grid').datagrid('getSelected');
	        	if(row){
	        		getSelections(this,row);
	        	}
	        }
	        });
		
	})
	
	function getSelections(obj,row){
			var roleRow=[];
			var opts=$.data(obj,"datagrid").options;
			var data=$.data(obj,"datagrid").data;
			opts.finder.getTr(obj,"","selected",2).each(function(){
				var index=parseInt($(this).attr("datagrid-row-index"));
				roleRow.push(data.rows[index]);
			});
			var temp='';
			for(var i=0;i<roleRow.length;i++){
		       	if(temp==''){
		       		temp=roleRow[i].id;
		    	}else{
		       		temp=temp+","+roleRow[i].id;
		       	}
			}
			var index=$('#TUser_grid').datagrid('getRowIndex', row);
			var editor=$('#TUser_grid').datagrid('getEditor',{index:index,field:'roleIds'});
			if(editor){
				editor.target.val(temp);
			}
		}	
	
	function delTUser(){
		var row = $('#TUser_grid').datagrid('getSelected');
		var index=$('#TUser_grid').datagrid('getRowIndex', row);
		if(row){
			$.messager.confirm('请确认','请确认是否删除选择的记录?',function(r){   
				if (r){ 
					jQuery.get("${path}/user/user/delete/"+row.id,function(msg){
						$('#TUser_grid').datagrid('deleteRow',index); 
						$('#TUser_grid').datagrid('reload');
						endEdit();
						//easyuiMsg($.toJSON(msg));
						easyuiMsg(msg);
					});
				}   
			}); 
		}else{
			$.messager.alert('提示','请选择要删除的记录！'); 
		}
	}
	function queryTUser(){
		$('#TUser_grid').datagrid('options').queryParams={"queryParams":$.toJSON(getQueryParamArray("TUser_filter_param"))}; 
		$('#TUser_grid').datagrid('reload');
		$('#TUser_grid').datagrid('unselectAll');
		endEdit();
	}

	function endEdit(){
		$('#TUser_role_detail').datagrid('clearSelections');
		if($('.datagrid-header-check :checkbox').attr("checked")){
			$('.datagrid-header-check :checkbox').attr("checked", false); 
		}
	}
	
	
</script>
<div style="width:100%;height:450px">
<div class="easyui-layout"  fit="true" >
	<div region="north" border="false" split="false" style="height:45px;" title="">
		<div fit="true" class="easyui-panel"  collapsible="false" border="false"  align="center">
			<div class="filter">
				<fieldset>
				    <ul>
						<li class="TUser_filter_param"> 
							用户名:<input type="text"/>
							<div style="display: none;">name</div>
							<div style="display: none;">1</div><!-- 1 字符、2数字 -->
							<div style="display: none;">2</div> 
						</li>
						<li class="TUser_filter_param"> 
							密码 :<input type="text"/>
							<div style="display: none;">password</div>
							<div style="display: none;">1</div><!-- 1 字符、2数字 -->
							<div style="display: none;">1</div> 
						</li>
				    	<li>
							<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryTUser()">查询</a>				    	
						</li>
				   	</ul>
				</fieldset>
			</div>
		</div>
	</div>
	<div region="center" style="width:450px;margin-left:5px;"  border="false" >
		<table id="TUser_grid" fit="true"  border="false"></table>
	</div>
	<div region="east" border="false" style="width:450px;margin-right:5px;"  split="true">
		<table id="TUser_role_detail"   fit="true" ></table>
	</div>
</div>
