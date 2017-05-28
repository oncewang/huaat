/**
 * jQuery easyui Plugins extends 1.0
 * Author：y_angzhiqiang@163.com
 * Branch: Shanghai
 * Date: Thu Jul 04 18:34:49 CST 2013
 * Description: Common plugs useing for jquery easyui
 * 
 * <input type="text" id="dicTypeId" name="dicTypeId"  value="${o.dicTypeId}"/>
 * 1.Param eg:$("#aggregate").treeSelect({url:'/sys/param/paramTree?paramTypeId=view-oneToMany-aggregate'});
 * 2.treeShow eg:
 * 3.treeSelect eg:$("#tMetaTable").treeSelect({cascade:false,pojo:'net.huaat.view.pojo.TMetaTable',text:'tableName'});
 */
(function($){
	/**
	 * eg:$("#q_regionId").treeShow({pojo:'net.huaat.food.pojo.DRegions',id:'regionId',text:'localName',pFieldName:'PGegionId',callback:queryTMember});
	 */
	$.fn.treeShow = function(options) {
		var defaults = {
				url:'/base/selector/combotree'
				,multiple:false	
				,value:''
				,async:true
				,cascade:true  //有上下级关系
				//,node:{pojo:'',id:'id',text:'name'}
				,pojo:''
				,id:'id'
				,text:'name'
				,pFieldName:'parentId'
				,callback:''
			};
		var opts = $.extend(defaults, options); 
		var $this = $(this);
		if(!opts.cascade){
			opts.async = false;
		}
		//Add param
		if(opts.url.indexOf("?")!=-1){
			opts.url+="&opts="+$.toJSON(opts);
		}else{
			opts.url+="?opts="+$.toJSON(opts);
		}
		//Save path
		opts.url=path+opts.url;
		$this.tree({
			url : opts.url,
			animate:true,
			onClick:opts.callback
		});
	};
	$.fn.treeSelect = function(options) {
		var defaults = {
			url:'/base/selector/combotree'
			,multiple:false	
			,value:''
			,async:true
			,cascade:true  //有上下级关系
			//,node:{pojo:'',id:'id',text:'name'}
			,pojo:''
			,id:'id'
			,text:'name'
			,pFieldName:'parentId'
		};
		var opts = $.extend(defaults, options); 
		var $this = $(this);
		if(!opts.cascade){
			opts.async = false;
		}
		//If selected by default,couldn't use asynchrony model;
		if(opts.value!=''){
			opts.async = false;
		}
		//Add param
		if(opts.url.indexOf("?")!=-1){
			opts.url+="&opts="+$.toJSON(opts);
		}else{
			opts.url+="?opts="+$.toJSON(opts);
		}
		//Save path
		opts.url=path+opts.url;
		//Init
		if(!opts.async){
			$this.combotree({ 
				url:opts.url,
				multiple:opts.multiple,
				width:142
			});
		}else{
			$this.combotree({ 
				url:opts.url,
				multiple:opts.multiple,
				onBeforeExpand:function(node) {
					$this.combotree("tree").tree("options").url = opts.url;
				},
				width:142
			});
		}
		if(opts.value!=''){
			$this.combotree('setValue',opts.value);
		}
	};
	/**
	 * eg:<form id="file_form" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file"/>
		  </form>
		  $("#file_form").uploadImage({filePath:'d:/temp/testA.jpg'});
	 */
	$.fn.uploadImage = function(options) {
		var defaults = {
				url:'/base/upload/image'
				,filePath:''
				,width:350
				,height:350
				,callback:''
			};
		var opts = $.extend(defaults, options);
		opts.url=path+opts.url;
		//alert(opts.url);
		$(this).form('submit', {
			url:opts.url+'?filePath='+opts.filePath+'&width='+opts.width+'&height='+opts.height,
			success: function(msg){
				var rs = $.evalJSON(msg);
				if(rs.error){
					alert(rs.error);
				}
			}
		});
	};
	var tempPath,clip,  
	FileReader = window.FileReader;  
	$.extend({  
	    previewImage:function(fileObj,imgObj){
	    	 clip=imgObj;
	    	 fileObj.change(function() {  
	    		 //alert("==");
	         if (FileReader) {  
	             var reader = new FileReader(),  
	                 file = this.files[0];  
	             reader.onload = function(e) {  
	            	 imgObj.attr("src", e.target.result);  
	             };  
	             reader.readAsDataURL(file);  
	         }else {  
	        	 tempPath = $(this).val();  
	             if (/"\w\W"/.test(tempPath)) {  
	                 pathpath = tempPath.slice(1,-1);  
	             }  
	             imgObj.attr("src",tempPath);  
	         }  
	     	}); 
	    }  
	});  
})(jQuery);