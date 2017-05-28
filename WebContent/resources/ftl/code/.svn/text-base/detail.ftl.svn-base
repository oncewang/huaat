<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="grid_detail">
	<form id="${pojoName}_form" method="post">
		<input type="hidden" name="id" value="${"$"+"{o.id}"}"/> 
		<ul>
			<#list gridColumns as column>
			<li>
				<label>${column.comment}:</label>
				<input type="text" class="easyui-validatebox" name="${column.columnName}" required="true"  value="${"$"+"{o.${column.columnName}}"}"/>
			</li>	
			</#list>
		</ul>
	</form>
</div>
