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
     var  treeObj;
    //-----------配置信息------------
    var setting = {
			check: {
				enable: true,
				chkboxType:  { "Y" : "", "N" : "" }
			},	
			data: {
				simpleData: {
					idKey: "id",
					pIdKey: "pid",
					rootPId:"0",
					enable: true
				}
			},
			callback: {
				onCheck: zTreeOnCheck,
				onClick: zTreeOnClick
			}			
    };
    //-----------json数据------------
	var zNodes=jQuery.parseJSON('${(jsonData)!""}');
	//-----------复选框的选中事件-增加业务处理rescCache 追加选中的所有节点id串------------
	function zTreeOnCheck(event, treeId, treeNode) {
	   var nodes =treeObj.getCheckedNodes(true);
	   var ids = "";
	   for (var i=0, l=nodes.length; i<l; i++) {
		  ids += nodes[i].id+",";
	   }
	   if (ids.length > 0 ) {
		  ids = ids.substring(0, ids.length-1);
	   }
	   $("#rescCache").val(ids);
	};	
	function zTreeOnClick(event, treeId, treeNode){
	  var _url;
	   if(treeNode.treelevel<4){
		   _url="${path}/baseorg/findOrgTree/"+treeNode.id;
	   }else{
	       _url="${path}/baseorg/orgTreeWithUser/"+treeNode.id;
	   }
	    window.location.href=_url;
	  /* var tId=treeNode.tId;
	   var node = treeObj.getNodeByTId(tId);
	   treeObj.selectNode(node);*/
	}
	$(function($){
     	treeObj=$.fn.zTree.init($("#rescZtree"), setting, zNodes);
     	var pId="${(pId)!''}";
	 	treeObj.expandAll(true);
	 	var node = treeObj.getNodeByParam("id", pId, null);
     	treeObj.selectNode(node);
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
       $("#contentlist").width($("#tabs-1").width()-$("#contentleft").width()-20);
   })
    function add(pId){
        if(!pId){
           pId=0;
        }
        var _url = "${path}/baseorg/add/"+pId;
        window.location.href=_url;
    }
   $(window).resize(function() {
	 $("#contentlist").width($("#tabs-1").width()-$("#contentleft").width()-20);
   });
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
			<div id="contentleft" style="float:left;width:200px;border:1px;">
				<!-- 树容器   UL-->
       			<div id="rescZtree" class="ztree"></div>
				<!-- 业务数据：资源id串-->
				<input name="rescCache" id="rescCache" type="hidden" value="" />
			</div>
			<div id="contentlist" style="width:auto;">
			 <form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/baseorg/list" method="post" style="width:100%">
			  <div class="table-responsive">
				<table class="table  table-striped " style="padding-left:200px;width:100%" >
				 <thead>
				  <tr>
				     <th></td>
				     <th>登录名称</th>
				     <th>用户名称</th>
				     <th>用户性别</th>
				     <th>用户手机</th>
				     <th>身份证号</th>
				   </tr>
				 </thead>
				 <#if users?? &&(users.length>0)>
				   <tbody>
				    <#list users as u>
				      <tr id="tr_${(u.id)!''}"  >
				        <td><input type="checkbox" id="classId" name="checkIds" value="${(u.id)!''}" class="checkbox"></td>
				        <td>${(u.loginName)!""}</td>
				        <td>
				             ${(u.nickName)!''} 
				        </td>
				        <td>${(u.sex)!''}<#if (u.sex)?? &&(u.sex==0)>女<#else>男</#if> </td>
				        <td>${(u.mobile)!""}</td>
				        <td>${(u.idCard)}</td>
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
	         </div>
			</form>
			</div>
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


