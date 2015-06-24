<#include "/taglibs.ftl">
<#--引入分页控件-->
<#import "/pager.ftl" as pager>
<#--html头 head头，和meta信心-->
<#include "/meta.ftl">
    <title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<#--css样式文件和jquery -->
<#include "/assets.ftl">
<#--自定义css样式和脚本-->
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/select2/select2.css"/>
<script type="text/javascript" src="${path}/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${path}/assets/global/plugins/select2/select2_locale_zh-CN.js"></script>

<#--日期选择-->
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
							
<script type="text/javascript">
    $(function($){
       /**新增用户*/
	   $('#addBtn').on('click', function(){
		  $('body').modalmanager('loading');
		  setTimeout(function(){
		      $("#addmodal").load('${path}/baseuser/add.htm', '', function(){
		         $("#addmodal").modal();
		      });
		  }, 1000);
		});
    })
    function view(id){
        var _url = "${path}/baseuser/view/"+id;
         $('body').modalmanager('loading');
		  setTimeout(function(){
		    $("#viewmodal").load(_url, '', function(){
		       $("#viewmodal").modal();
		    });
		  }, 1000);
    }
    function edit(id){
        var _url = "${path}/baseuser/edit/"+id;
    	var  _title="编辑用户";
       top.dialog({
             id:'editUserDialog',
             url:_url,
             title: _title,
			 width: 650,
			 height: 650,
			 data:_title,//传数据到弹出页面
			 onclose: function () {
			   	var returnValue=this.returnValue;
			    if(returnValue=='save'){
				    window.parent.location.reload(true); 
			    }
			 }
        }).showModal();
    }
    function del(id){
       alert("您确认要删除该人员吗？",function(opt){
         if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseuser/del/"+id,
					dataType:'json',
					success:function(msg){
						if(msg.flag=='succ'){
							window.location.reload(true); 
						}else{
							alert(msg.msg);
						}
					}
				});
         }else{
             //do other
         }
       });
    }
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
				<a href="#">用户列表</a>
			</li>
		</ul>
		<div class="page-toolbar">
		</div>
	</div>
	<!-- END PAGE HEADER-->
		  <div class="row">
			<div class="col-xs-12">
			
			<!-- PAGE CONTENT BEGINS -->
			<form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/baseuser/list" method="post" style="width:100%">
			   <div style="float:left;" >
			     <input type="text" id="loginName" name="loginName" value="${(item.loginName)!}"  class="form-control" placeholder="登录名称"/>
			     &nbsp;&nbsp;<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> 查询</button>
			     &nbsp;<button type="button" class="btn" id="addBtn" ><i class="fa fa-plus"></i> 新增用户</button>
			   </div>
			   <div style="clear: left;"></div>
			
			<div class="space-4"></div>
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
			 <@pager.pageTag formName="addForm"/>
			 </form>
<#include "/footer.ftl">
<div id="addmodal" class="modal  fade " data-width="760" data-backdrop="static" data-focus-on="input:first" data-keyboard="false" data-attention-animation="false" tabindex="-1"></div>
<div id="viewmodal" class="modal  fade " data-width="760" data-backdrop="static" data-focus-on="input:first"  tabindex="-1"></div>
<#--js资源文件-->
<#include "/resources.ftl">
<script src="${path}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>

<#--自定义css样式和脚本-->
<script type="text/javascript">
   $(function($){
   })
</script>
<#--html end-->
<#include "/endhtml.ftl">

