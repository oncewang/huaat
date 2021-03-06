<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf8" />

<title>疑似车辆排查识别系统</title>
<link  rel="stylesheet" type="text/css"  href="${path}/resources/css/sys/login_dpi.css"/>
<link rel="icon" href="${path}/resources/images/earth.png" />
<script type="text/javascript" src="${path}/resources/ui/easyui/jquery-1.7.2.min.js"></script>
</head>

<body>
<div id="outer">  
    <div id="middle">  
        <div id="inner">  
             <div id="content">
             	<form  action="${path}/system/login/in" method="post">
             	
                	<div id="divform">
                <%-- 	<c:if test="${not empty msg}">
                			<p style="color: red;padding-left:50px;height:15px;margin-bottom: 5px;">${msg}</p>
                    	</c:if>
                  --%>	
                    	<ul>
                        	<li>用户名&nbsp;<input name="name" type="text" class="name textfield_login" id="1" maxlength="33"/></li>
                            <li>密　码&nbsp;<input name="password" type="password"  class="password textfield_login" id="2" maxlength="18"/></li>
                  <%--  <li>验证码&nbsp;<input name="kaptcha" type="text"  class="textfield_login_s" id="2" maxlength="18"/>  --%>  
         <%-- &nbsp;<a href="#" onclick="changeCode()" ><img src="${path}/kaptcha.jpg" id="jcaptcha" title="验证码" width="78" height="27" /></a></li> --%>
           <li class="button"><input type="submit" class="bottom"  value="" style="width:145px;height:40px;margin-top:5px;border:0"></input></li>
                    	</ul>
		          </div>
              </form>
                <div class="footer">
                </div>
                <%--
                <script type="text/javascript">
					function changeCode() {
	  					$("#jcaptcha").attr("src","${path}/kaptcha.jpg?xx="+ new Date());
		  				}
				 </script>
				 --%>
             </div>
        </div>
    </div>
</div>
</body>
</html>