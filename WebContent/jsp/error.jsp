<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
<head>

<style type="text/css">
	table{border: 0px;width: 600px;height: 300px;}
	table tr th{background-color:#DDDDDD;}
</style>

<script type="text/javascript">
	$(function(){ 	
	});
</script>
</head>
	<body>
	<div align="center">
		<div class="sys_box" align="center">
			<table cellspacing="0" align="center" width="400px" height="200px">
			<tr height="60px">
				    <th>提 示 信 息</th>
			</tr>
			<tr>
				<td align="center">${msg}<a href="${pageContext.request.contextPath}/login.jsp">重新登录</a>!</td> 
			</tr>
		</table>
	</div>
	</div>
	
	
	</body>
	
</html>