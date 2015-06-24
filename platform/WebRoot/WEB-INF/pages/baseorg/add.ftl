<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title></title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<script type="text/javascript">
   window.dialog = dialog;
 $(function($){
      $("#tabs").tabs({
          active: 1,
          beforeActivate: function(event, ui) {  
	         var url =$(ui.newTab).attr("url");// $.data(ui.tab, 'load.tabs');  
	         if( url ) {  
	             location.href = url;  
	             return false;  
	         }  
	         return true;  
	      }
       });
   var pId="${(item.pId)!''}";
   if(pId||pId=='0'){
       $("#treeLeveal2").attr("checked",true);
   }
   var _title="";
   if($("#item_id").val()){
      _title="编辑组织";
   }else{
      _title="新增组织";
   }
   document.title=_title;
   $(".icon-double-angle-right").next(_title);
 })
    function save(){
        $.ajax({
			type:'post',
			url:"${path}/baseorg/save",
			data:$("#form").serialize(),
			dataType:'json',
			success:function(msg){
				if(msg.flag=='succ'){
				   $("#userId").val(msg.id);
				   window.location.href="${path}/baseorg/list";
				}else{
				   alert(msg.msg);
				}
			}
		});
     }
   function goHistory(){
       window.location.href="${path}/baseorg/list";
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
			<li class="active">后台模板</li>
		  </ul><!-- .breadcrumb -->
		</div><!-- .breadcrumbs -->
		<div class="page-content">
		  <div class="page-header">
			<h1>
				后台模板
				<small>
					<i class="icon-double-angle-right"></i>
					<span id=""> 资源列表</span> 
				</small>
			</h1>
		  </div><!-- /.page-header -->
		  <div id="tabs">
	      	<ul class="nav nav-tabs tab-expand">
	      	     <li id="tab1" url="${path}/baseorg/list"><a href="#tabs-1"><i class="green icon-home bigger-110"></i>组织机构</a></li>
	      	    <li id="tab2" class="active"  url="${path}/baseorg/add/0"><a href="#tabs-2"><#if (item.id)??>修改<#else>新增</#if></a></li>
			</ul>
			<div id="tabs-2">
		  <form id="form" name="form" action="${path}/baseuser/save" method="post">
			<table class="table table-condensed">
  			  <input type="hidden" id="item_id" name="id" value="${(item.id)!}"/>
   			  <tr>
       			<td width="20%">组织名称</td>
       			<td><input type="text" id="name" placeholder="名称" name="name" class="col-xs-8 col-sm-5" value="${(item.name)!}"></td>
   			  </tr>
   			  <tr>
   			    <td>上级组织</td>
   			    <td>
   			         <#if parents??>
   			          <select id="pId" name="pId">
   			           <option value="0">请选择</option>
   			           <#list parents as p>
   			             <option <#if (p.ID)?? && (item.pId)?? &&  item.pId==p.ID>selected</#if> value="${(p.ID)!''}">${(p.NAME)!''}</option>
   			           </#list>
   			           </select>
   			         </#if>
   			    </td>
   			  </tr>
   			  <tr>
       			<td>排序</td>
       			<td>
          		    <input type="text" id="sort" placeholder="sort" name="sort" class="col-xs-8 col-sm-5" value="${(item.sort)!}">
       		    </td>
   			  </tr>
   			  <tr>
     			 <td>备注</td>
     			 <td><textarea rows="3" id="item_remark" name="remark" class="col-xs-8 col-sm-5">${(item.remark)!""}</textarea></td>
   			  </tr>
		   </table>
		   <div class="bottomBtn">
			  <button type="button" class="btn btn-info" id="addBtn" onclick="save();"><i class="icon-ok bigger-110"></i>保存</button>
			  <button type="button" class="btn" onclick="goHistory()"><i class="icon-undo bigger-110"></i>返回</button>
		   </div>
		 </form>
		 </div><!--/#tabs-2-->
	    </div><!-- /.page-content -->
      </div><!-- /.main-content -->
	  <#include "/setting-box.ftl">
    </div><!-- /.main-container-inner -->
	<#include "/containerfooter.ftl">
  </div><!-- /.main-container -->
  <#include "/footer.ftl">
</body>
</html>

