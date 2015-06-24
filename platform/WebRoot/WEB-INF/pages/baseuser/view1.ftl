<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>新增用户</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<script type="text/javascript">
   $(function(){
	   var idsArr=new Array();
	   var province="${item.province}";
	   var city="${item.city}"
	   var county="${item.county}";
	   if(province){
		   idsArr.push(province);
	   }
	   if(city){
	       idsArr.push(city);
	   }
	   if(county){
	      idsArr.push(county);
	   }
	   $.ajax({
	      url: '${path}/region/listByIds?ids='+idsArr,
	      type:'post',
	      async:false,
	      dataType:'json',
	      success: function(jsonData){
	         if(jsonData){
				 $.each(jsonData,function(index,node){
				    if(jsonData[index]!=null){
				      if(jsonData[index].id==province){
				         $("#province").text(jsonData[index].name);
				      }
				      if(jsonData[index].id==city){
				         $("#city").text(jsonData[index].name);
				      }
				      if(jsonData[index].id==county){
				         $("#county").text(jsonData[index].name);
				      }
				    }
				 });
	         }
		 }});
   })
   
</script>
</head>
<body>
<div id="titleShow" class="page-title">用户详情</div>
<form id="form" name="" action="" class="form-wrap" method="post">
<table class="table table-condensed">
   <tr>
       <td>登录名称</td>
       <td>${(item.loginName)!}</td>
   </tr>
   <tr>
       <td>用户昵称</td>
       <td>${(item.nickName)!}</td>
   </tr>
   <tr>
       <td>用户邮箱</td>
       <td>${(item.email)!}</td>
   </tr>
    <tr>
       <td>身份证号</td>
       <td>${(item.idCard)!}</td>
   </tr>
   <tr>
       <td>用户性别</td>
       <td>
              <#if (item.sex)??&&item.sex==1 >男<#else>女</#if>
       </td>
   </tr>
   <tr>
       <td>出生日期</td>
       <td><#if (item.birthday)??>${(item.birthday)?string('yyyy-MM-dd')}</#if></td>
   </tr>
   <tr>
      <td>所在地区</td>
      <td>
         <span id="province"></span>
         <span id="city"></span>
         <span id="county"></span>
      </td>
   </tr>
   <tr>
     <td>详细地址</td>
     <td>${(item.address)!}
     </td>
   </tr>
   <tr>
       <td>用户手机</td>
       <td>${(item.mobile)!}</td>
   </tr>
   <tr>
       <td>用户座机</td>
       <td>${(item.telPhone)!}</td>
   </tr>
</table>
</form>
</body>
</html>

