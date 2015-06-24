<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<title>角色列表显示</title>
		<#include "../common/meta.ftl"/>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<h1>这是FileInfoController对应的新增页面</h1>
		<div>
			<div><h4>通过FileInfoController的redirect_user资源定位重定向到UserController</h4></div>
			<h3><a href="${path}/role/redirect_user">点击链接，执行重定向，这个URL可以直接改为${path}/user</a></h3>
			<h3><a href="${path}/role/new">新增</a></h3>
		</div>
		<div>
		<form action="${path}/role/save" method="post">
			<input type="hidden" name="id" value="${(entity.id)!''}" />
			<div>
				<table width="60%" border="0" cellpadding="3" cellspacing="1">
					<tr>
						<td width="15%">角色名</td>
						<td><input type="text" name="name" value="${(entity.name)!''}" /></td>
					</tr>
				</table>
			</div>
			<div>
				<h3><input type="submit" value="保存" /><a href="${path}/role">返回</a></h3>
			</div>
		</form>
		<div>
			<h3><a href="${path}/hello">返回</a></h3>
		</div>
	</body>
</html>