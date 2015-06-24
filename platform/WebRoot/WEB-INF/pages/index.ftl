<#include "/taglibs.ftl">
<#--html头 head头，和meta信心-->
<#include "/meta.ftl">
    <title>platform管理平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<#--css样式文件和jquery -->
<#include "/assets.ftl">
<#--自定义css样式和脚本-->
<script type="text/javascript">
   $(function($){
   
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
				<a href="#">仪表盘</a>
			</li>
		</ul>
		<div class="page-toolbar">
		</div>
	</div>
	<!-- END PAGE HEADER-->
<#include "/footer.ftl">
<#--js资源文件-->
<#include "/resources.ftl">
<#--自定义css样式和脚本-->
<script type="text/javascript">
   $(function($){
   
   })
</script>
<#--html end-->
s<#include "/endhtml.ftl">