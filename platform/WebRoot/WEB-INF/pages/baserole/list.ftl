<#include "/taglibs.ftl">
<#--引入分页控件-->
<#import "/pager.ftl" as pager>
<#--html头 head头，和meta信心-->
<#include "/meta.ftl">
    <title>角色管理</title>
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
    function add(){
        var _url = "${path}/baserole/add";
        var  _title="新增角色";
        top.dialog({
             id:'addUserDialog',
             url:_url,
             title: _title,
			 width: 650,
			 height: 380,
			 data:_title,//传数据到弹出页面
			 onclose: function () {
			    window.parent.location.reload(true); 
			 }
        }).showModal();
    }
          function view(id){
        var _url = "${path}/baserole/view/"+id;
    	var  _title="查看详情";
        top.dialog({
            id:'viewUserDialog',
            url:_url,
		    title: _title,
		    width: 650,
		    data:_title,
		    height: 380
		}).showModal();		
    }
    function edit(id){
        var _url = "${path}/baserole/edit/"+id;
    	var  _title="编辑角色";
       top.dialog({
             id:'editUserDialog',
             url:_url,
             title: _title,
			 width: 650,
			 height: 380,
			 data:_title,//传数据到弹出页面
			 onclose: function () {
			    window.parent.location.reload(true); 
			 }
        }).showModal();
    }
    function del(id){
       alert("您确认要删除该橘色吗？",function(opt){
         if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baserole/del/"+id,
					dataType:'json',
					success:function(msg){
						if(msg.flag=='succ'){
						   alertAutoClose(msg.msg,3000);
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
				<a href="#">角色列表</a>
			</li>
		</ul>
		<div class="page-toolbar">
		</div>
	</div>
		  <div class="row">
			<div class="col-xs-12">
			
			<!-- PAGE CONTENT BEGINS -->
			<form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/baserole/list" method="post" style="width:100%">
			   <div style="float:left;" >
			     <input type="text" id="name" name="name" value="${(item.name)!}"  class="input-medium" placeholder="资源名称"/>
			     &nbsp;&nbsp;<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查询</button>
			     &nbsp;<button type="button" class="btn" id="addBtn" onclick="add();"><i class="icon-plus"></i>新增</button>
			   </div>
			   <div style="clear: left;"></div>
			
			<div class="space-4"></div>
<div class="table-responsive">
			<table class="table  table-striped table-bordered">
			 <thead>
			  <tr>
			     <th>角色名称</th>
			     <th>角色编码</th>
			     <th>排序</th>
			     <th>操作</th>
			   </tr>
			 </thead>
			 <#if list??>
			   <tbody>
			    <#list list as r>
			      <tr>
			        <td>${(r.name)!""}</td>
			        <td>${(r.code)!""}</td>
			        <td>${(r.sort)!""}</td>
			        <td>
			          <a href='javascript:void(0);' onclick="view('${r.id}');">查看</a> 
			          <a href='javascript:void(0);' onclick="edit('${r.id}');">编辑</a> 
			          <a href='javascript:void(0);' onclick="del('${r.id}');">删除</a> 
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

