<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common.jsp" %>
<%@ include file="/jsp/commonJs.jsp" %>
<%@ include file="/jsp/easyui.jsp" %>
	<head>
	<title>疑似车辆排查识别系统</title>
	<meta http-equiv="pragma" content="no-cache"/> <!-- 禁止从服务器的缓存里取数据 -->
	<meta http-equiv="cache-control" content="no-cache"/> 
	<meta http-equiv="expires" content="0"/>  
<!--	<script type="text/javascript" src="${path}/resources/js/plugins/jquery.easyui-common.js"></script>--> 
<!--	<link rel="stylesheet" type="text/css" href="${path}/resources/css/sys/main.css"/>--> 
<!--	<link rel="stylesheet" type="text/css" href="${path}/resources/css/sys/common.css"/>--> 
	<!-- <link rel="icon" href="${path}/resources/images/earth.png" type="image/x-icon" />  --> 
	<!--   <link rel="shortcut icon" href="${path}/resources/images/earth.png" type="image/x-icon" /> -->
	
	<!-- <script type="text/javascript" src="${path}/resources/js/system/common.js"></script>-->
	<!--<script language="javascript" src="${path}/resources/ui/My97DatePicker/WdatePicker.js"></script>-->
<!--	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=735188ce66f222510cb74c97e63ab8a8"></script>-->
	
	<style type="text/css">
body {
margin: 0px;
background-image: none;
position: relative;
left: -0px;
width: 1024px;
margin-left: auto;
margin-right: auto;
text-align: left;
}

#u8 {
position: absolute;
left: 209px;
top: 28px;
width: 554px;
height: 46px;
font-family: '微软雅黑 Regular', '微软雅黑';
font-weight: 400;
font-style: normal;
font-size: 36px;
color: #7FACF2;
}

#u14 {
line-height:40px;
width: 120px;
word-wrap: break-word;
font-family: '微软雅黑 Bold', '微软雅黑';
font-weight: 700;
font-style: normal;
font-size: 16px;
color: #000000;
text-align: left;
height:35px;
background-image: url(u13.png);
}

.u15 {
line-height:40px;
width: 120px;
word-wrap: break-word;
font-family: '微软雅黑 Bold', '微软雅黑';

font-style: normal;
font-size: 16px;
color: #000000;
text-align: left;
height:40px;
background-image: url(u13.png);
}
.u15:hover{ 
background-image: url(u15_mouseOver.png);
}
 
.u15:active{ 
background-image: url(u15_mouseOver.png);
}
#u21 {
position: absolute;
left: 0px;
top: 149px;
width: 120px;
height: 10px;
}

#u21_start {
position: absolute;
left: 0px;
top: -5px;
width: 18px;
height: 20px;
}

#u21_end {
position: absolute;
left: 103px;
top: -5px;
width: 18px;
height: 20px;
}

#u21_line {
position: absolute;
left: 0px;
top: 5px;
width: 120px;
height: 1px;
}
a{text-decoration:none;}
a:link    {color: #000000;text-decoration:none;}
a:visited {color: #000000;text-decoration:none;}
a:hover   {color: #000000;text-decoration:none;}
a:active  {color: #000000;text-decoration:none;}

</style>
	
	<script>
	$(function(){
		addCenterContent('/quotaQuery/init');
	});
	
	
	function addCenterContent(url){
		$.ajax({
			type: "get",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			url: "${path}"+url,
			cache:false,
			dataType:"html",
			success: function(msg){
				$("#center-content").empty().html(msg);
			
			}
		});
	}
	
		
		
	</script>
</head>
<body  class="body-sty">
	<div style="height:108px;background-image: url(u1.png);"> 
		<div style="padding:15px 0px 0px 15px">
			<img id="u6_img" class="img " src="u6.png">
			<span id="u8">疑似车辆排查识别系统</span>
			<img id="u6_img" class="img " style="float:right;margin:20px 20px 0px 0px" src="logout.jpg">
		</div>
		
	</div>
	<div style="margin-top:12px;">
		<div style="float:left;height:280px;width:130px;background-image: url(u11.png);">
			<div id="u14" style="" class="text">
         		 <span>系统菜单</span>
        	</div>
        	<div style="height:5px;background:#fff;width:120px;border-top:1px solid #111"></div>
        	
		    <div style="" class="u15">
         		 <span><a href="#"  onclick="addCenterContent('/quotaQuery/init')"> 案件关联</a></span>
        	</div>
        	<div style="" class="u15">
        		<span><a href="#"  onclick="addCenterContent('/quotaModify/init')"> 车辆关联</a></span>
         	</div>
         	   			
		</div>
		
		<div style="float:right;height:400px;width:870px;border:0px solid #111">
			<div id="center-content"></div>
		
		</div>
	</div>
	
</body>
</html>
