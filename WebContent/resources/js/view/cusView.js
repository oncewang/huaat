	
/*
 * 扩展jQuery
 */
$.extend({
	// 项目的basePath
	bp : function() {
		var curWwwPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		var localhostPaht = curWwwPath.substring(0, pos);
		var projectName = pathName.substring(0,
		pathName.substr(1).indexOf('/') + 1);		
		if(projectName=='/bestv'){
			return (localhostPaht + projectName);
		}else{
			return (localhostPaht);
		}		
	}
	
});
	function queryTMetaTable(){
		$('#TMetaTable_grid').datagrid('options').queryParams={"queryParams":$.toJSON(getQueryParamArray("TMetaTable_filter_param"))}; 
		$('#TMetaTable_grid').datagrid('reload');
	}
	
	function exportData(){
		var queryParams = getFilterValue();
		if(queryParams!=true){
			if($("input[name='metaId']:checked").length==0){
				alert("请先选择需要导出的字段!");
				return false;
			}
			var idArray = new Array();
			$.each($("input[name='metaId']:checked"),function(i,n){
				idArray.push(n.value);
			});
			$("#exportDataBt").attr("disabled","disabled");
			$("#downloadFrame").attr("src",$.bp() +"/panorama/customerView/exportData?queryParams="+queryParams+"&columns="+idArray+"&areaId="+$("#val1AREAID").val());
			$("#exportDataBt").attr("value","正在导出");
			setTimeout("setExport()",6000);
		}
	}
	function setExport(){
		$("#exportDataBt").removeAttr("disabled");
		$("#exportDataBt").attr("value","导出");
	}
	function queryData(){
		$('#cusView_grid').datagrid('options').queryParams = {
			"queryParams" : getFilterValue(),
			"start":$("#startDate").datebox('getValue'),
			"areaId":$("#val1AREAID").val()
		};
		
		$('#cusView_grid').datagrid('reload');
	}
	function saveGroup(){
		var queryParams = getFilterValue();
		$("#queryParams").attr("value",queryParams);
		$("#starttime").attr("value",$("#startDate").datebox('getValue'));
		$("#areaId").attr("value",$("#val1AREAID").val());
		
		if(queryParams!=true){	
			$('#group_form').form('submit', {
		   		type: "post",
				url : $.bp() +"/panorama/customerView/saveGroup",
				success : function(data) {
					$('#groupDialog').dialog('close');
				}
			});
			
		}else{
			alert("请先输入查询条件!");
		}
	}
	function getAll(){
		if($("#selectAllBt").attr("value")=="全不选"){
			$("#selectAllBt").attr("value","全选");
			$("input[name='metaId']").attr("checked",false);
		}else{
			$("#selectAllBt").attr("value","全不选");
			$("input[name='metaId']").attr("checked",true);
		}
	}
	
	/**
	 * 根据客户id和日期dateId查看单个运用全景信息
	 * @param id
	 * @return
	 */
	function viewOne(id,start,end,areaId){
		$("#viewOneDialog")
		.dialog('setTitle','客户全景视图')
		.dialog('open')
		.dialog({
		    closed: false,
		    cache: false
		})
		.dialog("refresh",$.bp() +'/panorama/customerView/viewOne?id='+id+'&start='+start+'&end='+end+'&areaId='+areaId);
	}
	
	function viewOne02(id,start,end,areaId){
		$("#viewOneDialog02")
		.dialog('setTitle','客户全景视图')
		.dialog('open')
		.dialog({
		    closed: false,
		    cache: false
		})
		.dialog("refresh",$.bp() +'/panorama/customerView/groupView?id='+id+'&start='+start+'&end='+end+'&areaId='+areaId);
	}
	
	/**
	 * 选择要导出的字段
	 * @param id
	 * @return
	 */
	function getColumn(){
		$("#exportDialog")
		.dialog('setTitle','导出字段选择');
		var column="";
		$.ajax({
			type: "get",
			url: $.bp() +"/panorama/customerView/getColumn?queryParams="+getFilterValue(),
			/*queryParams:{
				"queryParams":getFilterValue()
			},*/
			success: function(msg){
				var data = $.evalJSON(msg);
				$.each(data, function(i, n){
	               	column+="<dl><dt><input type='checkbox' checked='checked' name='metaId' value="+n.id+">" + n.name+ "</input></dt><dd></dd><dl>";   
				});
			$("#exportDialog").dialog('open');
			$("#exportColumn").empty().append(column);
			}
		});
	}
	
	/**
	 * 根据分组id查看全景视图
	 * @param id
	 * @return
	 */
	function showGroup(groupId){
		$('#cusView_grid').datagrid('options').queryParams = {
			"queryParams" : "groupId",
			"groupId":groupId
		};
		
		$('#cusView_grid').datagrid('reload');
	}
	
	function viewAreaOnchange(){
		getCityCascadeSelect('val1AREAID','val1CA0001M',"CITY");
		getAllProductsCascadeSelect('val1AREAID','val1AB0003M',true);
		getProductsCascadeSelect('val1AREAID','val1DB0002M',"TR_CONTENTNAME",true);
		 

	}
	

	
	
	


	