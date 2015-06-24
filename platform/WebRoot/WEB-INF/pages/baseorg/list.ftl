<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>组织架构</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<#import "/pager.ftl" as pager>
<script src="${path}/assets/bus-js/orglist.js"></script>						
<script type="text/javascript">
    $(function($){
       window.dialog = dialog;
      $("#tabs").tabs({
          beforeActivate: function(event, ui) {  
	         var url =$(ui.newTab).attr("url");// $.data(ui.tab, 'load.tabs');  
	         if( url ) {  
	             location.href = url;  
	             return false;  
	         }  
	         return true;  
	      }
       });
    })
    function add(pId){
        if(!pId){
           pId=0;
        }
        var _url = "${path}/baseorg/add/"+pId;
        window.location.href=_url;
    }
      
    function edit(id){
        var _url = "${path}/baseorg/edit/"+id;
         window.location.href=_url;
    }
  
    function changeStatus(id,status){
        alert("您确认要更改该组织的状态吗？",function(opt){
        if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseorg/changeStatus?id="+id+"&status="+status,
					dataType:'json',
					success:function(msg){
						if(msg.flag=='succ'){
						   if(status==1){
						      $("#status_"+id).removeClass("blue icon-unlock").addClass("icon-lock");
						   }else{
						      $("#status_"+id).removeClass("icon-lock").addClass("blue icon-unlock");
						   }
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
    function deleteRecursiveById(id){
       alert("您确认要删除该组织和该组织下的所有组织吗？",function(opt){
         if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseorg/deleteRecursiveById?id="+id,
					dataType:'json',
					success:function(msg){
						if(msg.flag=='succ'){
						 var leftValue=$("#tr_"+id).attr("leftvalue");
						 $("#tr_"+id).remove();
						 $(".row_"+leftValue).remove();
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
			<li class="active">组织架构</li>
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
		<div  class="page-content">
		  <div  class="page-header">
			<h1>
				系统资源
				<small>
					<i class="icon-double-angle-right"></i>
					组织架构
				</small>
			</h1>
		  </div><!-- /.page-header -->
		  <div id="tabs">
	      	<ul class="nav nav-tabs tab-expand">
	      	    <li id="tab1" class="active" url="${path}/baseorg/list"><a href="#tabs-1"><i class="green icon-home bigger-110"></i>组织架构</a></li>
	      	    <li id="tab2" url="${path}/baseorg/add/0"><a href="#tabs-2">新增</a></li>
			</ul>
		   <div id="tabs-1" class="row">
			<div  class="col-xs-12 active">
			
			<!-- PAGE CONTENT BEGINS -->
			<form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/baseorg/list" method="post" style="width:100%">
			   <div style="float:left;" >
			     <input type="text" id="name" name="name" value="${(item.name)!}"  class="input-medium" placeholder="组织名称"/>
			     &nbsp;&nbsp;<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查询</button>
			   </div>
			   <div style="clear: left;"></div>
			
			<div class="space-4"></div>
			<div class="table-responsive">
				<table class="table  table-striped ">
				 <thead>
				  <tr>
				     <th></td>
				     <th ></th>
				     <th>组织名称</th>
				     <th></th>
				     <th>组织编码</th>
				     <th>备注</th>
				     <th>组织状态</th>
				     <th>操作</th>
				   </tr>
				 </thead>
				 <#if list??>
				   <tbody>
				    <#list list as r>
				      <tr id="tr_${(r.id)!''}" leftValue="${r.leftValue}" rightValue="${r.rightValue}" >
				        <td><input type="checkbox" id="classId" name="checkIds" value="${(r.id)!''}" class="checkbox"></td>
				        <td ><img ectype="flex" fieldid="${(r.id)!''}" leftValue="${r.leftValue}" status="open" src="${path}/assets/images/tr-expandable.gif"></td>
				        <td>${(r.name)!""}</td>
				        <td>
				          <a href='javascript:void(0);' onclick="add('${r.id}');">新增下级</a> 
				        </td>
				        <td>${(r.code)!''}</td>
				        <td>${(r.remark)!""}</td>
				        <td> <#if (r.status)?? && r.status==1><a href='javascript:void(0)' title="可编辑该资源是否在左侧显示" onclick="changeStatus('${r.id}',${(r.status)!''})"<i id="status_${r.id}" class="blue icon-unlock"></i> <#else><i id="status_${r.id}"  class="blue icon-lock"></i></a></#if> </td>
				        <td>
				          <a href='javascript:void(0);' onclick="edit('${r.id}');">修改</a> 
				          <a href='javascript:void(0);' onclick="deleteRecursiveById('${r.id}');">删除</a> 
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
			 <!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
			</div><!-- /.row -->
		  </div><!--/#tabsNav-->
	    </div><!-- /.page-content -->
      </div><!-- /.main-content -->
	  <#include "/setting-box.ftl">
    </div><!-- /.main-container-inner -->
	<#include "/containerfooter.ftl">
  </div><!-- /.main-container -->
  <#include "/footer.ftl">
</body>
</html>

