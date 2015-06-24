<#include "/taglibs.ftl">
<#--引入分页控件-->
<#import "/pager.ftl" as pager>
<#--html头 head头，和meta信心-->
<#include "/meta.ftl">
    <title>资源管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<#--css样式文件和jquery -->
<#include "/assets.ftl">
<#--自定义css样式和脚本-->
<script type="text/javascript">
    $(function(){
       window.dialog = dialog;
    })
</script>
<#--head结束  body开始，topbar-->
<#include "/header.ftl">
<#--左侧菜单-->
<#include "/sidebarmenu.ftl">
    <!-- BEGIN PAGE HEADER-->
	<h3 class="page-title">
	   仪表盘 <small> 首页</small>
	</h3>
	<div class="page-bar">
		<ul class="page-breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a href="${path}/index.htm">首页</a>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<a href="#">地区列表</a>
			</li>
		</ul>
		<div class="page-toolbar">
		</div>
	</div>
		  <div class="row">
			<div class="col-xs-12">
			
			<!-- PAGE CONTENT BEGINS -->
			<form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/region/list" method="post" style="width:100%">
			   <div style="float:left;" >
			     <input type="text" id="loginName" name="loginName" value="${(item.name)!}"  class="input-medium" placeholder="名称"/>
			     &nbsp;&nbsp;<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查询</button>
			   </div>
			   <div style="clear: left;"></div>
			
			<div class="space-4"></div>
<div class="table-responsive">
			<table class="table  table-striped table-bordered">
			 <thead>
			  <tr>
			     <th>地区编码</th>
			     <th>地区名称</th>
			     <th>所属地区编码</th>
			   </tr>
			 </thead>
			 <#if list??>
			   <tbody>
			    <#list list as r>
			      <tr>
			        <td>${(r.id)!""}</td>
			        <td>${(r.name)!""}</td>
			        <td>${(r.parentId)!""}</td>
			      </tr>
			    </#list>
			   </tbody>
			 <#else>
			   <tbody>
			     <tr><td colspan="6">没有符合条件的记录</td></tr>
			   </tbody>
			 </#if>
			 </table>
</div>
			 <@pager.pageTag formName="addForm"/>
			 </form>
<#include "/footer.ftl">
<#--js资源文件-->
<#include "/resources.ftl">
<#--自定义css样式和脚本-->
<script type="text/javascript">
   $(function($){
   
   })
</script>
<#--html end-->
<#include "/endhtml.ftl">
