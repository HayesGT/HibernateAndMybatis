<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>新增用户</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<script type="text/javascript">
</script>
</head>
<body>
<div id="titleShow" class="page-title">资源注册</div>
<table class="table table-condensed">
   <input type="hidden" id="item_id" name="id" value="${(item.id)!}"/>
   <tr>
       <td width="20%">角色名称</td>
       <td>${(item.name)!}</td>
   </tr>
   <tr>
       <td>角色编码</td>
       <td>${(item.code)!}</td>
   </tr>
   <tr>
      <td>备注</td>
      <td>${(item.remark)!""}</td>
   </tr>
</table>
</body>
</html>

