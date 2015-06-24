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
<script type="text/javascript">
    $(function(){
       window.dialog = dialog;
       $('#tableId').dataTable( {
             // "sDom": 'tr<"pageNav"flp><"pageInfo"i>', //自定义搜索列表，翻页等显示在哪里
              "displayLength": 10,//每页显示10条数据
              "autoWidth": true,//宽度是否自动，感觉不好使的时候关掉试试
　　　　　　　　　　 "lengthChange": true, 
　　　　　　　　　　 "filter": true,
              "language": {//下面是一些汉语翻译
                    "search": "搜索",
                    "lengthMenu": "每页显示  _MENU_ 条记录",
                    "zeroRecords": "没有检索到数据",
                    "info": "显示<span class='page_orange'>_START_</span>至 <span class='page_orange'>_END_</span>条 &nbsp;&nbsp;共 <span class='page_green'>_TOTAL_</span>条",
                    "infoFiltered": "(筛选自  _MAX_ 条数据)",
                    "infoEmtpy": "没有数据",
                    "processing": "正在加载数据...",
                    "processing": "<img src='${path}/assets/images/loading.gif' />", //这里是给服务器发请求后到等待时间显示的 加载gif
                    "paginate":{
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "末页"
                      }
                },
                "bProcessing": true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
                "bServerSide": true, //开启服务器模式，使用服务器端处理配置datatable。注意：sAjaxSource参数也必须被给予为了给datatable源代码来获取所需的数据对于每个画。 这个翻译有点别扭。开启此模式后，你对datatables的每个操作 每页显示多少条记录、下一页、上一页、排序（表头）、搜索，这些都会传给服务器相应的值。 
                "sAjaxSource": "${path}/region/listAjax", //给服务器发请求的url
                "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
                    {"mData": 'id'}, //mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
                    {"mData": 'name'},
                    {"mData": 'parentId'},
                    {"sDefaultContent": '默认值'}, // sDefaultContent 如果这一列不需要填充数据用这个属性，值可以不写，起占位作用
                    {"sDefaultContent": '', "sClass": "action"},//sClass 表示给本列加class
                ],
               "aaSorting":[[0,'asc'],[1,'desc'],[2,'asc']], 
                "aoColumnDefs": [
				 // { "bSearchable": false, "bVisible": false, "aTargets": [ 1 ] },//bSearchable：是否可搜索；bVisible：是否可见；aTargets：哪一列
				  //{ "bVisible": false, "aTargets": [ 3 ] },//不显示某列
				  { "bSortable": false, "aTargets": [ 3 ] },//不排序某列
                  { "bSortable": false, "aTargets": [ 4 ] }
				],
                "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
                    if (aData.id =='11') {
                        $('td:eq(3)', nRow).html("<span class='text-error'>北京</span>");
                    } else if (aData.id=='41') {
                        $('td:eq(3)', nRow).html("<span class='text-error'>河南</span>");
                    } 
                    $('td:eq(4)', nRow).html("<a href='' user_id='" + aData.id + "'  class='ace_detail'>详情</a>");
                    return nRow;
                },
                "fnInitComplete": function(oSettings, json) { //表格初始化完成后调用 在这里和服务器分页没关系可以忽略
                    $("input[aria-controls='DataTables_Table_0']").attr("placeHolder", "请输入高手用户名");
                }

            });
				
				
		/*$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});
				
		});*/
			
			
		$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
		function tooltip_placement(context, source) {
			var $source = $(source);
			var $parent = $source.closest('table')
			var off1 = $parent.offset();
			var w1 = $parent.width();
	
			var off2 = $source.offset();
			var w2 = $source.width();
	
			if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
			return 'left';
		}
    })
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
				<a href="#">地区列表</a>
			</li>
		</ul>
		<div class="page-toolbar">
		</div>
	</div>
		  <div class="row">
			<div class="col-xs-12">
			
			<!-- PAGE CONTENT BEGINS -->
			<form id="addForm" name="addForm" class="form-inline  form-right" action="${path}/region/list1" method="post" style="width:100%">
			   <div style="float:left;" >
			     <input type="text" id="loginName" name="loginName" value="${(item.name)!}"  class="input-medium" placeholder="登录名称"/>
			     &nbsp;&nbsp;<button type="submit" class="btn btn-primary"><i class="icon-search"></i> 查询</button>
			     &nbsp;<button type="button" class="btn" id="addBtn" onclick="add();"><i class="icon-plus"></i>新增用户</button>
			   </div>
			   <div style="clear: left;"></div>
			
			<div class="space-4"></div>
<div class="table-responsive">
<h3 class="header smaller lighter blue">jQuery dataTables</h3>
<div class="table-header">地区详情</div>

<table id="tableId" class="table table-striped table-bordered table-hover">
			 <thead>
			  <tr>
			     <th>地区编码</th>
			     <th>地区名称</th>
			     <th>所属地区编码</th>
			     <th>空列</th>
			     <th>空列</th>
			   </tr>
			 </thead>
			 <tbody>
			 </tbody>
			 </table>
<div>
			 </form>
			  <div class="pageInfo">
			  </div>
			  <div class="pageNav">
			  </div>
			  <div class="clear"></div>
			 <!-- PAGE CONTENT ENDS -->
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

