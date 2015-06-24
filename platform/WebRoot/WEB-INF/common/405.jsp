<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>异常信息页面 </title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<script type="text/javascript">

</script>
</head>
<body>
<#include "/navbar.ftl">
  <div class="main-container" id="main-container">
    <script type="text/javascript">
	   try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>
    <div class="main-container-inner">
	  <#include "/leftnav.ftl">
	  <div class="main-content">
	    <div class="breadcrumbs" id="breadcrumbs">
		  <script type="text/javascript">
			try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		  </script>
		  <ul class="breadcrumb">
			<li>
				<i class="icon-home home-icon"></i>
				<a href="#">首页</a>
			</li>
			<li class="active">控制台</li>
		  </ul><!-- .breadcrumb -->
          <!--
		  <div class="nav-search" id="nav-search">
			<form class="form-search">
				<span class="input-icon">
					<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
					<i class="icon-search nav-search-icon"></i>
				</span>
			</form>
		  </div>--><!-- #nav-search -->
		</div><!-- .breadcrumbs -->
		<div class="page-content">
		  <div class="page-header">
			<h1>
				控制台
				<small>
					<i class="icon-double-angle-right"></i>
					 异常信息页面
				</small>
			</h1>
		  </div><!-- /.page-header -->
		  <div class="row">
			<div class="col-xs-12">
     		<!-- PAGE CONTENT BEGINS -->
			    <div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert">
						<i class="icon-remove"></i>
					</button>
			
					<strong>
						<i class="icon-remove"></i>
						错误信息!
					</strong>
			             ${(exceptionMsg)!''}
					<br>
				</div>
			<!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		  </div><!-- /.row -->
	    </div><!-- /.page-content -->
      </div><!-- /.main-content -->
	  <#include "/setting-box.ftl">
    </div><!-- /.main-container-inner -->
	<#include "/containerfooter.ftl">
  </div><!-- /.main-container -->
  <#include "/footer.ftl">
</body>
</html>
