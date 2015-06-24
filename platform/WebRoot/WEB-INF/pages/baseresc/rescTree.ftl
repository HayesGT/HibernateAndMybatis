<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资源树</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
</head>
<body>
<!-- 树容器   UL-->
<div id="rescZtree" class="ztree"></div>

<!-- 业务数据：资源id串-->
<input name="rescCache" id="rescCache" type="hidden" value="" />
</body>
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
					enable: true,
					idKey: "id",
					pIdKey: "pid",
					rootPId: "0"
				}
			},
			callback: {
				onCheck: zTreeOnCheck
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
	   alert(ids)
	   $("#rescCache").val(ids);
	};	
	$(function($){
     	treeObj=$.fn.zTree.init($("#rescZtree"), setting, zNodes);
	 	treeObj.expandAll(true);
   })
</script>
</html>

