$(function($){
	//列表下拉
	$(document).on("click",'img[ectype="flex"]',function(){
		var status = $(this).attr('status');
		if(status == 'open'){
			var pr = $(this).parent('td').parent('tr');
			var id = $(this).attr('fieldid');
			var obj = $(this);
			var expandImg=getRootPath()+"/assets/images/tr-expandable.gif";
			var expandImg1=getRootPath()+"/assets/images/tr-expandable1.gif";
			var itemImg=getRootPath()+"/assets/images/tr-item.gif";
			var vertlineImg=getRootPath()+"/assets/images/vertline.gif";
			$.ajax({
				url:  getRootPath()+"/baseresc/findListByPid?pId="+id+"&random="+Math.random(),
				dataType: 'json',
				success: function(data){
					var src='';
					if(data.length>0){
					for(var i = 0; i < data.length; i++){
						var dataId=data[i].id;
						//tr 样式  
						src += "<tr class='"+pr.attr('class')+" row_"+pr.attr('leftValue')+"' id='tr_"+dataId+"'  leftValue='"+data[i].leftValue+"' rightValue='"+data[i].rightValue+"'>";
						
						//复选框
						src += "<td><input type='checkbox' id='classId' name='checkIds' value='"+data[i].id+"' class='checkbox'> </td>";
						
						//图片
						if(data[i].rightValue-data[i].leftValue != 0){
							//有子节点  显示加号
							src +="<td> <img ectype=\"flex\" fieldid='"+dataId+"' leftvalue='"+data[i].leftValue+"'  status='"+status+"' src='"+expandImg+"'/> </td>";
						}else{
							//没有字节点不显示
							src +="<td>  </td>";
						}
						
						src += "<td>";
						if(data[i].treeLevel == 1){
							src += " <img fieldid='"+dataId+"' status='open' ectype='flex' class='treelevel"+data[i].treeLevel+"' src='"+itemImg+"' />";
						}else{
							src +="<img fieldid='"+dataId+"' ectype='flex'  class='treelevel"+data[i].treeLevel+"' status='open' src='"+itemImg+"'/>";
							//src += " <img fieldid='"+dataId+"' status='none' ectype='flex' src='"+expandImg1+"' />";
						}
						//名称
						src += "<span  fieldid='"+dataId+"' >"+data[i].name+"</span></td>";
						src += "<td>";
						//新增下级
						if(data[i].rescType==1){//按钮 没有  新增下级
							src += "<a  href='javascript:void(0);'  onclick=\"add(\'"+dataId+"\')\">新增下级</a>&nbsp;";
						}
						//src += "<a class='treeup' href='javascript:void(0);' >上移</a>&nbsp;";
						//src += "<a class='treedown' href='javascript:void(0);' >下移</a>";
						src += "</td>";
						
						//code
						//src += "<td align='left'>"+data[i].code+"</td>";
						
						//资源类型
						if(data[i].rescType==0){
							src+="<td>按钮</td>";
						}
						if(data[i].rescType==1){
							src+="<td>菜单</td>";
						}
						//图标
						src+="<td><i class='"+data[i].icon+"'></i></td>";
						//备注
						src += "<td>"+data[i].remark+"</td>";
						
						//显示状态
						src += "<td>";
						var changeStatue="<a href=\"javascript:void(0);\" title=\"可编辑该资源是否在左侧显示\" onclick=\"changeStatus('"+dataId+"',"+data[i].status+")\">";
						if(data[i].status == 0){
							src +=changeStatue+"<i id='status_"+dataId+"' class=\"icon-lock\"></i></a>";
						}else {
							src +=changeStatue+"<i id='status_"+dataId+"' class=\"blue icon-unlock\"></i></a>";
						}
						src += "</td>";
						
						//操作
						src += "<td>";
						src += "<a href=\"javascript:void(0);\"  onclick=\"edit('"+dataId+"')\">修改</a>";
						src += " | <a href=\"javascript:void(0);\"  onclick=\"deleteRecursiveById('"+dataId+"')\">删除</a>";
						src += "</td>";
						src += "</tr>";
					}
					}else{
						alert("下级资源为空");
					}
					//插入
					pr.after(src);
					obj.attr('status','close');
					obj.attr('src',obj.attr('src').replace("tr-expandable","tr-collapsable"));//改变父级节点
				},
				error: function(){
					alert('获取信息失败');
				}
			});
		}
		if(status == 'close'){
			$(".row_"+$(this).attr('leftvalue')).remove();
			$(this).attr('src',$(this).attr('src').replace("tr-collapsable","tr-expandable"));
			$(this).attr('status','open'); 
		}
	});
});
