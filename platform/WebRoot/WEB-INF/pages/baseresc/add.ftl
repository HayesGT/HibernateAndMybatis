<#include "/taglibs.ftl">
<#--引入分页控件-->
<#import "/pager.ftl" as pager>
<#--html头 head头，和meta信心-->
<#include "/meta.ftl">
    <title>新增用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<#--css样式文件和jquery -->
<#include "/assets.ftl">
<#--自定义css样式和脚本-->
<script type="text/javascript">
 $(function($){
   var pId="${(item.pId)!''}";
   if(pId||pId=='0'){
       $("#treeLeveal2").attr("checked",true);
   }
 })
    function save(){
        var dialog = top.dialog.get(window);
        $.ajax({
			type:'post',
			url:"${path}/baseresc/save",
			data:$("#form").serialize(),
			dataType:'json',
			success:function(msg){
				if(msg.flag=='succ'){
				   $("#userId").val(msg.id);
				   var time=3000;
				   alertAutoClose(msg.msg,time);
				   setTimeout(function () {
	            	  dialog.close().remove();
	         	   }, time);
				}else{
				   alert(msg.msg);
				}
			}
		});
     }

</script>
</head>
<body>
<div id="titleShow" class="page-title">资源注册</div>
<form id="form" name="form" action="${path}/baseuser/save" method="post">
<table class="table table-condensed">
   <input type="hidden" id="item_id" name="id" value="${(item.id)!''}"/>
   <input type="hidden" id="pId" name="pId" value="${(item.pId)!''}"/>
   <tr>
       <td width="20%">资源名称</td>
       <td><input type="text" id="name" placeholder="名称" name="name" class="col-xs-8 col-sm-5" value="${(item.name)!}"></td>
   </tr>
   
   <tr>
       <td>资源url</td>
       <td><input type="text" id="url" placeholder="url" name="url" class="col-xs-8 col-sm-5" value="${(item.url)!}"></td>
   </tr>
   <tr>
       <td>资源icon</td>
       <td><input type="text" id="icon" placeholder="icon" name="icon" class="col-xs-8 col-sm-5" value="${(item.icon)!}"></td>
   </tr>
   <tr>
       <td>资源级别</td>
       <td>
              <input type="radio" id="rescType1" name="rescType" value="1" checked <#if (item.rescType)??&&item.rescType==1 >checked</#if> ><span>&nbsp;菜单&nbsp;</span>
              <input type="radio" id="rescType0" name="rescType" value="0" <#if (item.rescType)??&&item.rescType==0 >checked</#if> ><span >&nbsp;按钮&nbsp;</span>
       </td>
   </tr>
   <tr>
       <td>资源级别</td>
       <td>
              <input type="radio" id="treeLeveal1" name="treeLevel" value="1" <#if (item.treeLeveal)??&&item.treeLeveal==1 >checked</#if> ><span>&nbsp;一级&nbsp;</span>
              <input type="radio" id="treeLeveal2" name="treeLevel" value="2" <#if (item.treeLeveal)??&&item.treeLeveal==2 >checked</#if>><span >&nbsp;二级&nbsp;</span>
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
<div id="bottomnav" >
<button type="button" class="btn btn-info" id="addBtn" onclick="save();"><i class="icon-ok bigger-110"></i>保存</button>
</div>
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
