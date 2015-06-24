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
<form id="form" name="form" action="${path}/baseuser/save" method="post">
<table class="table table-condensed">
   <input type="hidden" id="item_id" name="id" value="${(item.id)!}"/>
   <tr>
       <td width="20%">资源名称</td>
       <td>${(item.name)!}</td>
   </tr>
   
   <tr>
       <td>资源url</td>
       <td>${(item.url)!}</td>
   </tr>
   <tr>
       <td>资源icon</td>
       <td>${(item.icon)!}</td>
   </tr>
   <tr>
       <td>资源级别</td>
       <td>
              <#if (item.rescType)??&&item.rescType==1 >&nbsp;菜单&nbsp;</#if> 
              <#if (item.rescType)??&&item.rescType==0 >&nbsp;按钮&nbsp;</#if>
       </td>
   </tr>
   <tr>
       <td>资源级别</td>
       <td>
              <#if (item.treeLeveal)??&&item.treeLeveal==1 ><span>&nbsp;一级&nbsp;</span></#if> 
              <#if (item.treeLeveal)??&&item.treeLeveal==2 ><span >&nbsp;二级&nbsp;</span></#if>
       </td>
   </tr>
   <tr>
      <td>备注</td>
      <td>${(item.remark)!""}</td>
   </tr>
</table>
</form>
</body>
</html>

