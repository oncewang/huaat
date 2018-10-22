function easyuiMsg(data){
	data=eval('('+data+')');
	if(data.success){
		$.messager.show(
			{
				title:'提示',
				msg:data.success,
				showType:'slide'
			}
		);
	}
	if(data.error){
		$.messager.alert('提示',data.error,'error');
	}
}
function getQueryParamArray(className){
	//alert("."+className);
	var paramArray = new Array();
	$("."+className).each(function(){
		var param = {};
		param.type = $(this).children().eq(2).text();
		param.value="";
		if(param.type=='4'){
			param.value= $.trim($(this).children().eq(0).datebox('getValue'));
		}else{
			param.value= $.trim($(this).children().eq(0).val());
		}
		if($.trim(param.value)!=""){
			param.id = $(this).children().eq(1).text();
			param.type = $(this).children().eq(2).text();
			param.relation = $(this).children().eq(3).text();
			paramArray.push(param);
		}
	});
	return paramArray;
}
function getRow(gridId){
	var row = $("#"+gridId).datagrid('getSelected');
	if(row){
		return row;
	}else{
		$.messager.alert('提示','请先选择一条记录!','info');
		return false;
	}
}
function openSelector(url,gridId,handler,width){
	$("#commonDialog").dialog({   
		title: '请选择数据',
		width:width,
		buttons : [{
			text:'选择',
			iconCls:'icon-save',
			handler:function(){
				var row=getRow(gridId);
				if(row){
					handler(row);
					$('#commonDialog').dialog('close');
				}
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#commonDialog').dialog('close');
			}
		}]}).dialog('open').dialog("refresh",url);
}
/*function openSelector(url,callback,width){
	$("#commonDialog").dialog({   
		title: '请选择数据',
		width:1000,
		buttons : [{
			text:'选择',
			iconCls:'icon-save',
			handler:function(){
				//callback;
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#commonDialog').dialog('close');
			}
		}]}).dialog('open').dialog("refresh",url);
}*/
//$('#metaTypeTree').tree("getLevel",clickNode.target);
$.extend($.fn.tree.methods,{
    getLevel: function(jq, target){
        return $(target).find('span.tree-indent,span.tree-hit').length;
    }
});

function onAfterEdit(obj,rowIndex,changes){
	var opts = $.data(obj, "datagrid").options;
	var tr = opts.finder.getTr(obj, rowIndex);
	tr.find('td').each(function(){
		if($(this).hasClass('datagrid_has_change')){
			$(this).addClass("datagrid_change_col");
			$(this).removeClass("datagrid_has_change");
		}
	});
	for(var change in changes){
		var td = tr.find("td[field=\"" +change + "\"]");
		if(!td.hasClass('datagrid_change_col')){
			td.addClass("datagrid_change_col");
		}
	}
}
function onBeforeEdit(obj,rowIndex,arr){
	var opts = $.data(obj, "datagrid").options;
	var tr = opts.finder.getTr(obj, rowIndex);
	for(var i=0;i<arr.length;i++){
		var td = tr.find("td[field=\"" +arr[i] + "\"]");
		if(td.hasClass('datagrid_change_col')){
			td.removeClass("datagrid_change_col");
			td.addClass("datagrid_has_change");
		}
	}
}


 $.ajaxSetup({ 
		 error: function (XMLHttpRequest, textStatus, errorThrown){
				if(XMLHttpRequest.status==403){
					alert('您没有权限访问此资源或进行此操作');
					return false;
				}
			},  
         complete:function(XMLHttpRequest,textStatus){   
        	 var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，  
        	 
        	 if(sessionstatus=='timeout'){   
                      //如果超时就处理 ，指定要跳转的页面  
        		      var top = getTopWinow();
        		      alert('登录超时, 请重新登录.'); 
        	          top.location.href=path+"/login.jsp"; 
        	      }   
         }   
   }); 
 
 /** 
  * 在页面中任何嵌套层次的窗口中获取顶层窗口 
  * @return 当前页面的顶层窗口对象 
  */
function getTopWinow(){  
    var p = window;  
    while(p != p.parent){  
        p = p.parent;  
    }  
    return p;  
}  