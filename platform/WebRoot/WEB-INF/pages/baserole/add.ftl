<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>新增角色</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<script type="text/javascript">
     var dialog = top.dialog.get(window);
 $(function($){
 })
    function save(){
        $.ajax({
			type:'post',
			url:"${path}/baserole/save",
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
     function choseUser(uId){
       var _url = "${path}/baseuser/listall";
       var  _title="编辑角色";
       top.dialog({
             url:_url,
             title: _title,
			 width: 650,
			 height: 380,
			 data:_title
        }).showModal();
     }
     function choseResc(uId){
     }
</script>
</head>
<body>
<div id="titleShow" class="page-title">新增角色</div>
<form id="form" name="form" action="${path}/baserole/save" method="post">
<table class="table table-condensed">
   <input type="hidden" id="item_id" name="id" value="${(item.id)!}"/>
   <tr>
       <td width="20%">角色名称</td>
       <td><input type="text" id="name" placeholder="名称" name="name" class="col-xs-8 col-sm-5" value="${(item.name)!}"></td>
   </tr>
   <tr>
       <td>排序</td>
       <td>
          <input type="text" id="sort" placeholder="sort" name="sort" class="col-xs-8 col-sm-5" value="${(item.sort)!}">
       </td>
   </tr>
   <tr>
       <td>所属资源</td>
       <td colspan='2'><a href="javascript:void(0)" onclick="choseResc('${(item.id)!}')">管理</a></td>
   </tr>
   <tr>
     <td>备注</td>
     <td><textarea rows="3" id="item_remark" name="remark" class="col-xs-8 col-sm-5">${(item.remark)!""}</textarea></td>
   </tr>
</table>
<div id="bottomnav" >
<button type="button" class="btn btn-info" id="addBtn" onclick="save();"><i class="icon-ok bigger-110"></i>保存</button>
</form>
</body>
</html>

