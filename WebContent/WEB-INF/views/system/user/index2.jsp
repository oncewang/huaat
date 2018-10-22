<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common.jsp" %>
<%@ include file="/jsp/commonJs.jsp" %>
<%@ include file="/jsp/easyui.jsp" %>
	<head>
	<title>华院数据房产价值实时评估平台V1.0</title>
	<meta http-equiv="pragma" content="no-cache"/> 
	<meta http-equiv="cache-control" content="no-cache"/> 
	<meta http-equiv="expires" content="0"/>  
	<script type="text/javascript" src="${path}/resources/js/plugins/jquery.easyui-common.js"></script>
	<link rel="stylesheet" type="text/css" href="${path}/resources/css/sys/main.css"/>
	<link rel="stylesheet" type="text/css" href="${path}/resources/css/sys/common.css"/>
	 <link rel="icon" href="${path}/resources/images/earth.png" type="image/x-icon" />
	 <link rel="shortcut icon" href="${path}/resources/images/earth.png" type="image/x-icon" />
	<script type="text/javascript" src="${path}/resources/js/system/common.js"></script>
	<script language="javascript" src="${path}/resources/ui/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=735188ce66f222510cb74c97e63ab8a8"></script>
	
	<style type="text/css">
	/* 	.tabs-panels .panel-header{
			width :100% !important;
		}
		
		.tabs-panels .datagrid-view{
			width :100% !important;
		}
		.tabs-panels .datagrid-view2{
			width :100% !important;
		}
		.tabs-panels .datagrid-header{
			width :100% !important;
		}
		.tabs-panels .datagrid-body{
			width :100% !important;
		}
		.tabs-panels .panel-body{
			width :100% !important;
		}
		.layout{
			width :100% !important;
		}
		.layout-panel{
			width :100% !important;
		}
		.panel{
			width :100% !important;
		} */

</style>
	
	<script>
		$(function(){
			//getMap1(121.41540254799,31.306096860113);
			//$('#main-tabs').tabs("select",2);
			//getHomepage("${role.homePageUrl}","tab_desktop");
			//getHomepage("/estate/assess/init","tab_pinggu");
			//getHomepage("/estate/assessResult/init","tab_jieguo");
			//getHomepage("/estate/earlyWarning/init","tab_yujing");
			//addTab("我的桌面","qazwsxda","${path}/estate/home/init","1" ,"desk_con");
			addTab("评估房子","c61f4fe0","${path}/estate/assess/init","1" ,"assess_con");
			addTab("评估结果","c61f4fe01","${path}/estate/assessResult/init","1" ,"assessResult_con");
			//addTab("波动预警","c61f4fe02","${path}/estate/earlyWarning/init","1" ,"earlyWarning_con");
			
			//if("${role.homePageUrl}"!=""){
			//    getHomepage("${role.homePageUrl}","tab_desk");
			//}
		
	 	});
			
		var index = 0;
		function addTab(name,tabId,url,openType,tabs){
			index++;
			var tabs=$('#'+tabs);
			if(tabs.tabs("exists",name)){
				tabs.tabs("select",name); 
			}else{
				switch(openType){
					case '1'://html片段方式
					tabs.tabs('add',{
						title:name,
						href:url,
						closable:true
					});
					break;
					case '2'://iframe方式
					tabs.tabs('add',{
						title:name,
						content:'<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>',
						closable:true
					});
					break;
					case '3'://iframe跨应用方式
						if(url.indexOf("?") > 0 ){
							url = 'http://' + location.host + '/' + url+'&username=${userName}';
						}else{
							url = 'http://' + location.host + '/' + url+'?username=${userName}';
						}
						
						tabs.tabs('add',{
							title : name,
							content : '<iframe name="' + name + '"id="'	+ tabId	+ '"src="' + url + '" width="100%" height="500" frameborder="0" scrolling="auto" ></iframe>',
							closable:true
						});
						break;
					default:
					alert("types not supported");
				}
			}
			
		}
		
		
		function getHomepage(url,id){
			$.ajax({
				type: "get",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				url: "${path}"+url,
				cache:false,
				dataType:"html",
				success: function(msg){
					$("#"+id).empty().html(msg);
				
				}
			});
		}
		
		function getDataDiv(){
			$.ajax({
				type: "get",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				url: "${path}/sys/data/init",
				cache:false,
				dataType:"html",
				success: function(msg){
					$("#data_div").empty().html(msg);
				
				}
			});
		}
		
		function secectTabs(){
			$('#main-tabs').tabs("select",2);
		}
		
		
		
	</script>
</head>
<body  class="body-sty">
	<div   class="container" >
		<div class="head " >
			<ul >
				<li class="logo" style="padding-left: 20px;"><span style="font-size:24px;color:#63666a"> <img  width="35px" height="35px"   src="${path}/resources/images/logo.png" /> </span><span style="margin-left:5px;margin-top:5px; line-height:40px;font-size:20px;color:#63666a"> 华院数据房产价值实时评估平台V1.0</span></li>
				<li class="menu01">欢迎你,${userName}!
					&nbsp;&nbsp;|&nbsp;&nbsp;<a
					href="${path}/system/login/out" style="color: #63666a">注销 </a>
				</li>
			</ul>
		</div>

		<div  id="main-tabs" class="easyui-tabs"     data-options="border:true,
			 	onSelect:function(title,index){}
			" >
			
			
			<c:if test="${role.homePageUrl!=''}">
				<script>
					$(function(){
						getHomepage("${role.homePageUrl}","tab_desk");
					});
				</script>
				<div id="tab_desk"   class="container" style="border:0;margin-top:5px;"   title="<img   style='margin-top:7px;margin-right:5px;width:16px;'  src='${path}/resources/images/tabs/home.png'>我的桌面">
					
				</div>
			</c:if>
			
			
			<div id="tab_pinggu"   class="container" style="border:0;"   title="<img   style='margin-top:7px;margin-right:5px;width:16px;'  src='${path}/resources/images/tabs/iptv.gif'>房屋评估">
					<div  id="assess_con"  class="easyui-tabs tab-styl hide-header" style=""   border="false"  >
						
					</div>
			</div>
			
			
			<div id="tab_jieguo"   class="container" style=" border:0;"   title="<img   style='margin-top:7px;margin-right:5px;width:16px;'  src='${path}/resources/images/tabs/iptv.gif'>评估结果">
					<div  style="width:auto;border:2px solid #ccc;border-bottom:0;">
						<div  id="assessResult_con"  class="easyui-tabs tab-styl hide-header" style=""   border="false"  >
						
						</div>
					</div>
			</div>
			<%-- <div id="tab_yujing"   class="container" style=" border:0;"   title="<img   style='margin-top:7px;margin-right:5px;width:16px;'  src='${path}/resources/images/tabs/iptv.gif'>波动预警">
				<div  style="width:auto;border:2px solid #ccc;border-bottom:0;">
						<div  id="earlyWarning_con"  class="easyui-tabs tab-styl hide-header" style=""   border="false"  ></div>
				</div>
			</div> --%>
			<%-- <div id="tab_menu" title="<img   style='margin-top:5px;margin-right:5px;width:16px;'  src='/bestv/resources/images/tabs/home.png'>菜单代码">
				<c:out value="${menu}" escapeXml="true"/>
			</div> --%> 
			<c:out value="${menu}" escapeXml="false"/>
			
		</div>
		
		
    	
	</div>
</body>
</html>
