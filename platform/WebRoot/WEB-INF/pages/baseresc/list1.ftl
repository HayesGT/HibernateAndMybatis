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
<script src="${path}/assets/bus-js/resclist.js"></script>						
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
        var _url = "${path}/baseresc/add1/"+pId;
        window.location.href=_url;
      /*  var  _title="新增资源";
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
        }).showModal();*/
    }
          function view(id){
        var _url = "${path}/baseresc/view/"+id;
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
        var _url = "${path}/baseresc/edit1/"+id;
         window.location.href=_url;
    	/*var  _title="编辑资源";
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
        }).showModal();*/
    }
    function del(id){
       alert("您确认要删除该资源吗？",function(opt){
         if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseresc/del/"+id,
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
    function changeStatus(id,status){
        alert("您确认要更改该资源的状态吗？",function(opt){
        if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseresc/changeStatus?id="+id+"&status="+status,
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
       alert("您确认要删除该资源和该资源下的所有资源吗？",function(opt){
         if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseresc/deleteRecursiveById?id="+id,
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
		  <div id="tabs">
	      	<ul class="nav nav-tabs tab-expand">
	      	    <li id="tab1" class="active" url="${path}/baseresc/list1"><a href="#tabs-1"><i class="green icon-home bigger-110"></i>资源列表</a></li>
	      	    <li id="tab2" url="${path}/baseresc/add1/0"><a href="#tabs-2">新增</a></li>
			</ul>
		   <div id="tabs-1" class="row">
			<div  class="col-xs-12 active">
			
			<!-- PAGE CONTENT BEGINS -->
			<form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/baseresc/list1" method="post" style="width:100%">
			   <div style="float:left;" >
			     <input type="text" id="name" name="name" value="${(item.name)!}"  class="input-medium" placeholder="资源名称"/>
			     &nbsp;&nbsp;<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查询</button>
			     &nbsp;<button type="button" class="btn" id="addBtn" onclick="add();"><i class="icon-plus"></i>新增</button>
			   </div>
			   <div style="clear: left;"></div>
			
			<div class="space-4"></div>
			<div class="table-responsive">
				<table class="table  table-striped ">
				 <thead>
				  <tr>
				     <th></td>
				     <th ></th>
				     <th>资源名称</th>
				     <th></th>
				     <th>资源类型</th>
				     <th>资源编码</th>
				     <th>资源图标</th>
				     <th>备注</th>
				     <th>资源状态</th>
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
				        <td>
				           <#if (r.rescType)?? && r.rescType==1 >菜单<#else>按钮</#if>
				        </td>
				        <td>${(r.code)!''}</td>
				        <td><i class='${(r.icon)!""}'></i> </td>
				        <td>${(r.remark)!""}</td>
				        <td> <#if (r.status)?? && r.status==1><a href='javascript:void(0)' title="可编辑该资源是否在左侧显示" onclick="changeStatus('${r.id}',${(r.status)!''})"<i id="status_${r.id}" class="blue icon-unlock"></i> <#else><i id="status_${r.id}"  class="blue icon-lock"></i></a></#if> </td>
				        <td>
				          <#--<a href='javascript:void(0);' onclick="view('${r.id}');">查看</a>--> 
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

