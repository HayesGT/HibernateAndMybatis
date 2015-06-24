<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<#import "/pager.ftl" as pager>
							
<script type="text/javascript">
    $(function(){
       window.dialog = dialog;
    })
    }
</script>
</head>
<body>
<table class="table  table-striped table-bordered">
			 <thead>
			  <tr>
			     <th>登录名</th>
			     <th>用户昵称</th>
			     <th>用户邮箱</th>
			     <th>操作</th>
			   </tr>
			 </thead>
			 <#if list??>
			   <tbody>
			    <#list list as user>
			      <tr>
			        <td>${(user.loginName)!""}</td>
			        <td>${(user.nickName)!""}</td>
			        <td>${(user.email)!""}</td>
			        <td>
			          <a href='javascript:void(0);' onclick="view('${user.id}');">查看</a> 
			          <a href='javascript:void(0);' onclick="edit('${user.id}');">编辑</a> 
			          <a href='javascript:void(0);' onclick="del('${user.id}');">删除</a> 
			        </td>
			      </tr>
			    </#list>
			   </tbody>
			 <#else>
			   <tbody>
			     <tr><td colspan="6">没有符合条件的记录</td></tr>
			   </tbody>
			 </#if>
			 </table>
</body>
</html>

