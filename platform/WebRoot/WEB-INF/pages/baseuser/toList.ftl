<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>用户列表</title>
<#include "/meta.ftl"/>
<#include "/script.ftl"/>
							
<script type="text/javascript">
   $(function($){
       initList();
   })
   function initList(){
      $("#userList").datagrid({    
		    url:'listall.json',    
		    columns:[[    
		        {field:'loginName',title:'登录名称',width:100},    
		        {field:'nickName',title:'用户昵称',width:100},    
		        {field:'email',title:'邮箱',width:100,align:'right'}    
		    ]]    
	  });  
   }
</script>
</head>
<body>
    <table id="userList"></table>
</body>
</html>

