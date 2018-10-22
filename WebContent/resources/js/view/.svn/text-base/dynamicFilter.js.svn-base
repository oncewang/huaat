function selectAll(obj,cName){
			if($(obj).val()=='全选'){
				$(obj).val('全不选');
				var checkboxs = document.getElementsByName(cName);
				for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = true;}
			}else{
				$(obj).val('全选');
				var checkboxs = document.getElementsByName(cName);
				for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = false;}
			}
}



function isExistMetaId(id){
	if(existMetaId.match(id)!=null){
	return true;
	}else{
	return false;
	}
}
 
var existMetaId="";




function addField(name,id,valueType,dicTypeId){
	id=$.trim(id);
	if(isExistMetaId(id)){
		$.messager.show({
			title:'提示',
			msg:"不能重复添加!",
			timeout:500,
			showType:'slide'
		});
		$("#val2"+id).focus();
		return;
		}else{
		existMetaId+=id;
		}

	var fieldHtml="<tr id='"+id+"' class='filterTr' style='height: 30px'><td>"+name+"</td>";
	
	fieldHtml+="<td   align='left'>"+createFilterHtml(valueType,id,dicTypeId)+"</td>";
	
	fieldHtml+="<td><div style=display:none>"+id+"</div><div style=display:none>"+valueType+"</div></td>";
	fieldHtml+="<td><div style=block:none><a href='#' onclick=removeField('"+id+"')><img src='/bestv/resources/images/delete.gif'/></a></div></td></tr>";
	$("#insTr").before(fieldHtml);
	
	if(valueType=="4"){
		$("#val1"+id).datebox({  
		    required:true  
		});
		
		$("#val2"+id).datebox({  
		    required:true  
		});
	}
	
	/*if(valueType=="3"){
		$("#start"+id).omNumberField();
		$("#end"+id).omNumberField();
	}*/
}

function removeField(id){
	id=$.trim(id);
	existMetaId=existMetaId.replace(id,"")
	$("#"+id).remove();
	
}
/**
 * 根据值类型生成不同的条件
 * @param {Object} valueType
 */
function createFilterHtml(valueType,id,dicTypeId){
	var filterHtml="";
	//字符型
	if(valueType=="1"){
		filterHtml+="<input type='text' id='val1"+id+"'/>"
	}
	//枚举型
	if(valueType=="2"){
		$.ajax({
			async:false,
			type: "post",
			url: "/bestv/panorama/customerView/getEnumItems",
			data: "typeId="+dicTypeId,
			success: function(msg){
				var data = $.evalJSON(msg);
				filterHtml+="<select id=val1"+id+">";
				$.each(data,function(i,n){
					filterHtml+="<option value="+n.dicId+">"+n.dicValue+"</option>"
				});
				filterHtml+="</select>";
			}
		});	
	}
	//数值型
	if(valueType=="3"){
		filterHtml+="<input type='text' id='val1"+id+"'/> - <input type='text' id='val2"+id+"'/>"
	}
	
	//数值型
	if(valueType=="4"){
		filterHtml+="<input style='width:124px' id='val1"+id+"'></input>  - <input style='width:124px' id='val2"+id+"'/>"
	}
	return filterHtml;
}
/**
 * 信息元为数字型时有起始两个值，字符型与枚举均一个值
 * @memberOf {TypeName} 
 * @return 验证不通过，返回true
 */
function getFilterValue(){
	var metaArray = new Array();
	var startDate = {};
	var endDate = {};
	var c1Date = {};
	startDate.metaId="startDate";
	startDate.type="4";
	startDate.value=$("#startDate").datebox('getValue');
	startDate.relation=">=";
	
	endDate.metaId="endDate";
	endDate.type="4";
	endDate.value=$("#endDate").datebox('getValue');
	endDate.relation="<=";
	

	
	c1Date.metaId="DATEID";
	c1Date.type="4";
	c1Date.value=$("#endDate").datebox('getValue');
	c1Date.relation="=";
	
	metaArray.push(startDate);
	metaArray.push(endDate);
	metaArray.push(c1Date);
	
	var filter = {};
	var filter2 = {};
	var isValid=true;
	$(".filterTr").each(function(){
		filter = {}; 
		filter.metaId		=$(this).children().eq(2).children().eq(0).text();
		filter.type=$(this).children().eq(2).children().eq(1).text();
		filter.value=$.trim($("#val1"+filter.metaId).val());
		if(filter.type=="1"){
			filter.value=encodeURI(encodeURI(filter.value));
			filter.relation="like";
		}
		if(filter.type=="2"){
			filter.relation="=";
		}
		if(filter.type=="3"){
			filter2 = {};
			filter2.metaId =filter.metaId;
			filter2.type=filter.type;
			filter2.value	=$.trim($("#val2"+filter.metaId).val());
			filter.relation=">";
			filter2.relation="<=";
			
			//validate
			if(filter2.value==""||isNaN(filter.value)||isNaN(filter2.value)){
				$.messager.show({
					title:'提示',
					msg:$(this).children().eq(0).text()+" 需输入数字!",
					timeout:1000,
					showType:'slide'
				});
				$("#val2"+filter.metaId).focus();
				
				isValid=false;
				return false;
			}
			
		}
		
		if(filter.type=="4"){
			filter.value=$("#val1"+filter.metaId).datebox('getValue');
			filter2 = {};
			filter2.metaId =filter.metaId;
			filter2.type=filter.type;
			filter2.value	=$("#val2"+filter.metaId).datebox('getValue');
			filter.relation=">";
			filter2.relation="<=";
			
			//validate
			if(filter2.value==""){
				$.messager.show({
					title:'提示',
					msg:$(this).children().eq(0).text()+" 需输入时间!",
					timeout:1000,
					showType:'slide'
				});
				$("#val2"+filter.metaId).focus();
				
				isValid=false;
				return false;
			}
		}
		//validate
		if(filter.value==""){
			$.messager.show({
				title:'提示',
				msg:$(this).children().eq(0).text()+" 需输入值!",
				timeout:1000,
				showType:'slide'
			});
			 $("#val1"+filter.metaId).focus();
			isValid=false;
			return false;
		}
		
		if(filter.type=="3"||filter.type=="4"){
			metaArray.push(filter2);
		}
		metaArray.push(filter);
	});
	if(!isValid){
		return true;
	}
	
	//增加默认查询条件，不用验证是否输入值
	metaArray=getDefaultFilterValue(metaArray);
	
	//alert($.toJSON(metaArray));
	return $.toJSON(metaArray);
}



/**
 *默认查询条件，值可以为空
 */
function getDefaultFilterValue(metaArray){
	var filter = {};
	$(".defaultFilterTr").each(function(){
		filter = {};
		filter.metaId		=$(this).children().eq(1).children().eq(1).text();
		filter.type=$(this).children().eq(1).children().eq(2).text();
		filter.value		=$.trim($("#val1"+filter.metaId).val());
		if(filter.type=="1"){
			filter.relation="like";
		}
		if(filter.type=="2"){
			filter.relation="=";
		}
		
		//validate
		if(filter.value!=""){
			metaArray.push(filter);
		}
	})
	return metaArray;
}

function cascadeSelect(param1Id,param2Id){
	var cit = "<option value=''>全部</option>";   
	$.ajax({
			async:false,
			type: "post",
			url: "/bestv/panorama/customerView/getMetaDicByTypeIdAndParentId",
			data: "pid="+$("#"+param1Id).val()+"&typeId=CITY",
			success: function(msg){
				var data = $.evalJSON(msg);
				$.each(data, function(i, n){
	               			 cit+="<option value="+n.dicId+">"+n.dicValue+"</option>" ;
						});
	           	$('#'+param2Id).empty().append(cit);  
	           	
			}
		});	
}

function getProductsCascadeSelect(param1Id,param2Id,typeId,includeAll){
	var cit = "";  
	if(includeAll){
		cit = "<option value=''>全部</option>";  
	}
	$.ajax({
			async:false,
			type: "post",
			url: "/bestv/panorama/customerView/getMetaDicByTypeIdAndParentId",
			data: "pid="+$("#"+param1Id).val()+"&typeId="+typeId,
			success: function(msg){
				var data = $.evalJSON(msg);
				$.each(data, function(i, n){
	               			 cit+="<option value="+n.dicValue+">"+n.dicValue+"</option>" ;
						});
	           	$('#'+param2Id).empty().append(cit);  
	           	
			}
		});	
}

function getCityCascadeSelect(param1Id,param2Id,typeId){
	var cit = "<option value=''>全部</option>";   
	$.ajax({
			async:false,
			type: "post",
			url: "/bestv/panorama/customerView/getMetaDicByTypeIdAndParentId",
			data: "pid="+$("#"+param1Id).val()+"&typeId="+typeId,
			success: function(msg){
				var data = $.evalJSON(msg);
				$.each(data, function(i, n){
	               			 cit+="<option value="+n.dicId+">"+n.dicValue+"</option>" ;
						});
	           	$('#'+param2Id).empty().append(cit);  
	           	
			}
		});	
}

function getAllProductsCascadeSelect(param1Id,param2Id,includeAll){
	var cit = "";  
	var areaId=$("#"+param1Id).val();
	if($.trim(areaId)==""){
		areaId="00";
	}
	if(includeAll){
		cit = "<option value=''>全部</option>";  
	}
	if(areaId=="00"){
		$('#'+param2Id).empty().append(cit);  
	}else{
		$.ajax({
			async:false,
			type: "post",
			url: "/bestv/panorama/customerView/getAllProducts",
			data: "areaId="+areaId,
			success: function(msg){
				var data = $.evalJSON(msg);
				$.each(data, function(i, n){
	               			 cit+="<option value="+n+">"+n+"</option>" ;
						});
	           	$('#'+param2Id).empty().append(cit);  
	           	
			}
		});	
	}
	
}




function getQueryParamArray(className){
	//alert("."+className);
	var paramArray = new Array();
	var param = {};
	$("."+className).each(function(){
		if($.trim($(this).children().eq(0).val())!=""){
			param = {};
			param.id = $(this).children().eq(1).text();
			param.type = $(this).children().eq(2).text();
			param.relation = $(this).children().eq(3).text();
			param.value= $.trim($(this).children().eq(0).val());
			paramArray.push(param);
		}
	});
	//alert($.toJSON(paramArray));
	return paramArray;
}