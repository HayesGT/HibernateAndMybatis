<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<#assign path=(rc.getContextPath())!''>
	<head>
		<title>角色列表显示</title>
		<#include "/common/meta.ftl"/>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<h1>这是FileInfoController对应的list.html${path}</h1>
		<div>
			<div><h4>通过RoleController的redirect_user资源定位重定向到UserController</h4></div>
			<h3><a href="${path}/role/redirect_user">点击链接，执行重定向，这个URL可以直接改为${path}/user</a></h3>
			<h3><a href="${path}/role/new">新增</a></h3>
		</div>
		<div>
			<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1">
				<tr align="center">
					<th>序号</th>
					<th>角色</th>
					<th>操作</th>
				</tr>
				<#if list??>
	        		<#list list as entity>
					<tr align="center" bgcolor="#FFFFFF">
						<td>${entity.id}</td>
						<td>${(entity.name)!''}</td>
						<td>
							<a href="${path}/role/view/${entity.id}">详细</a>
							<a href="${path}/role/edit/${entity.id}">修改</a>
							<a href="${path}/role/delete/${entity.id}">删除</a>
						</td>
					</tr>
					</#list>
		        </#if>
			</table>
		</div>
		<div>
			<h3><a href="${path}/hello">返回</a></h3>
		</div>
	</body>
</html>