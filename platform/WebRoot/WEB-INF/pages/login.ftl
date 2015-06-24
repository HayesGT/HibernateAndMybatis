<#-- 
   因为在freemarkerConfig  templateLoaderPaths 属性中已经把/WEB-INF/common/指定了文件模板路径
   在调用的时候直接就可以/*.ftl找到对应的文件
 -->
<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8"/>
<title>登录注册</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="${path}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${path}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${path}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${path}/assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="${path}/assets/admin/pages/css/login-soft.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="${path}/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${path}/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${path}/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<#--弹出框样式-->
<link href="${path}/assets/artDialog/ui-dialog.css" rel="stylesheet" type="text/css"/>


<script src="${path}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/jquery-placeholder/jquery.placeholder.min.js" type="text/javascript"></script>
<#--弹出框-->
<script src="${path}/assets/artDialog/dialog-min.js" type="text/javascript"></script>
<script src="${path}/assets/artDialog/dialog-plus-min.js" type="text/javascript"></script>
<#--通用js-->
<script src="${path}/assets/js/custom.js" type="text/javascript"></script>
<style type="text/css">
/**背景全屏拉伸*/
body{
   background:url("${path}/assets/admin/pages/media/bg/1.jpg");
   background-size:cover; 
   -moz-background-size: 100% 100%; 
	-o-background-size: 100% 100%; 
	-webkit-background-size: 100% 100%; 
	background-size: 100% 100%; 
	background-repeat:no-repeat\9; 
	background-image:none\9; 
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${path}/assets/admin/pages/media/bg/1.jpg', sizingMethod='scale');
    -ms-filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${path}/assets/admin/pages/media/bg/1.jpg', sizingMethod='scale')";
}
</style>
<script type="text/javascript">
 $(function(){
       window.dialog = dialog;
       $("#loginBtn").on("click",function(){
              $.ajax({
				type:'post',
				url:"${path}/login/login.json",
				data:$("#loginform").serialize(),
				dataType:'json',
				success:function(msg){
					if(msg.flag=='succ'){
		            	  window.location.href="${path}/index.htm";
					}else{
					   alert(msg.msg);
					}
				}
			});
       });
 })
</script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="${path}/index.htm">
	<img src="${path}/assets/admin/layout/img/logo-big.png" alt=""/>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form id="loginform" class="login-form" action="${path}/login/login.json" method="post">
		<h3 class="form-title">请输入您的账户</h3>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>请输入用户名和密码！</span>
		</div>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control " type="text" autocomplete="off" placeholder="登录名" name="loginName"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control " type="password" autocomplete="off" placeholder="密码" name="password"/>
			</div>
		</div>
		<div class="form-actions">
			<label class="checkbox">
			<input type="checkbox" name="remember" value="yes"/>记住密码</label>
			<button type="button" id="loginBtn" class="btn blue pull-right">
			登录 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
		<div class="login-options">
			<h4>第三方登录</h4>
			<ul class="social-icons">
			    <li>
				    <a class="btn btn-primary" href="${path}/qquser/login">
						<i class="fa fa-qq"></i>
					</a>
				</li>
				<li>
					<a class="facebook" data-original-title="facebook" href="#">
					</a>
				</li>
				<li>
					<a class="twitter" data-original-title="Twitter" href="#">
					</a>
				</li>
				<li>
					<a class="googleplus" data-original-title="Goole Plus" href="#">
					</a>
				</li>
				<li>
					<a class="linkedin" data-original-title="Linkedin" href="#">
					</a>
				</li>
			</ul>
		</div>
		<div class="forget-password">
			<h4>忘记密码？</h4>
			<p>
				 别捉急，<a href="javascript:;" id="forget-password">点击这儿</a>重置你的密码。
			</p>
		</div>
		<div class="create-account">
			<p>
				 还没有账户？ ?&nbsp; <a href="javascript:;" id="register-btn">注册一个</a>
			</p>
		</div>
	</form>
	<!-- END LOGIN FORM -->
	<!-- BEGIN FORGOT PASSWORD FORM -->
	<form class="forget-form" action="index.html" method="post">
		<h3>忘记密码了？</h3>
		<p>
			输入你注册时的邮箱重置密码。
		</p>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-envelope"></i>
				<input class="form-control " type="text" autocomplete="off" placeholder="例如：example@5ycode.com" name="email"/>
			</div>
		</div>
		<div class="form-actions">
			<button type="button" id="back-btn" class="btn">
			<i class="m-icon-swapleft"></i> 返回 </button>
			<button type="submit" class="btn blue pull-right">
			提交 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
	</form>
	<!-- END FORGOT PASSWORD FORM -->
	<!-- BEGIN REGISTRATION FORM -->
	<form class="register-form" action="index.html" method="post">
		<h3>新用户注册</h3>
		<p>
			输入你个人的注册信息
		</p>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-font"></i>
				<input class="form-control " type="text" placeholder="用户名" name="nickName"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-envelope"></i>
				<input class="form-control " type="text" placeholder="例如：example@5ycode.com" name="email"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-check"></i>
				<input class="form-control " type="text" placeholder="详细地址" name="address"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-location-arrow"></i>
				<input class="form-control " type="text" placeholder="City/Town" name="city"/>
			</div>
		</div>
		<div class="form-group">
			<select name="country" id="select2_sample4" class="select2 form-control">
			</select>
		</div>
		<p>
			输入你的账户信息
		</p>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control " type="text" autocomplete="off" placeholder="登录名" name="loginName"/>
			</div>
		</div>
		<div class="form-group">
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control " type="password" autocomplete="off" id="register_password" placeholder="密码" name="password"/>
			</div>
		</div>
		<div class="form-group">
			<div class="controls">
				<div class="input-icon">
					<i class="fa fa-check"></i>
					<input class="form-control " type="password" autocomplete="off" placeholder="确认密码" name="rpassword"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label>
			<input type="checkbox" name="tnc"/>同意<a href="#">《用户须知》 </a>
			and <a href="#">
			Privacy Policy </a>
			</label>
			<div id="register_tnc_error">
			</div>
		</div>
		<div class="form-actions">
			<button id="register-back-btn" type="button" class="btn">
			<i class="m-icon-swapleft"></i> 返回 </button>
			<button type="submit" id="register-submit-btn" class="btn blue pull-right">
			注册 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
	</form>
	<!-- END REGISTRATION FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	 2014 &copy; <a href="http://www.5ycode.com">我要编程</a>
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${path}/assets/global/plugins/respond.min.js"></script>
<script src="${path}/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->

<script src="${path}/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${path}/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/assets/global/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${path}/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${path}/assets/admin/pages/scripts/login-soft.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {     
  Metronic.init(); // init metronic core components
  Login.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>