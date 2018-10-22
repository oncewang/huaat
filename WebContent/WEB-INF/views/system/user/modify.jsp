<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common.jsp" %>
<script type="text/javascript">
function modify(){
	jQuery.post("${path}/user/user/modify",{password:$("#password").val()},
			function(msg){
		$.messager.alert('提示',msg.success,'info');
	},"json");
}
</script>
<div class="modify_sty" >
<ul>
			<li style="width: 800px">
				<label>用户名:</label>
				<input type="text" class="easyui-validatebox" readonly="readonly" value="${userName}"/>
			</li>	
			<li style="width: 400px">
				<label>新密码:</label>
				<input type="text" class="easyui-validatebox" required="true"  id="password"/>
			</li>
			<li style="width: 400px">
				<label></label>
				<a href="#" class="easyui-linkbutton" onclick="modify()">修改</a> 
			</li>
</ul>
</div>