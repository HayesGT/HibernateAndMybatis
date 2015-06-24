<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>地区列表2</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<#import "/pager.ftl" as pager>
							
<script type="text/javascript">
$(document).ready(function() {
    $('#tableId').dataTable( {
       // "pagingType":"full_numbers",//simple只有上一页，下一页，simple_numbers 上一页 1,2,3...下一页，full，只有四个按钮，full_numbers：四个按钮+数字导航
        "processing": true,
        "serverSide": true,
        "language": {//下面是一些汉语翻译
            "search": "搜索",
            "lengthMenu": "每页显示  _MENU_ 条记录",
            "zeroRecords": "没有检索到数据",
            "info": "显示<span class='page_orange'>_START_</span>至 <span class='page_orange'>_END_</span>条 &nbsp;&nbsp;共 <span class='page_green'>_TOTAL_</span>条",
            "infoFiltered": "(筛选自  _MAX_ 条数据)",
            "infoEmtpy": "没有数据",
            "processing": "正在加载数据...",
            "processing": "<img src='${path}/assets/images/loading.gif' />", //这里是给服务器发请求后到等待时间显示的 加载gif
			"paginate": {
				"first":    "首页",
				"previous": "上一页",
				"next":     "下一页",
				"last":     "尾页"
			}
        },
      //  "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        
        "ajax": $.fn.dataTable.pipeline( {
            url: '${path}/region/listAjax1',
            pages: 5 // 缓存的页数
        } ),
        "order":[[0,'asc'],[1,'desc'],[2,'asc']],
        "columns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
            {"data": 'id'},//mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
            {"data": 'name'},
            {"data": 'parentId'},
            {"defaultContent": ''},
            {"defaultContent": '', "sClass": "optTdClass"},//sClass 表示给本列加class
        ],
        "columnDefs":[//配置某几列不能排序
           { "bSortable": false, "aTargets": [ 3 ] },
           { "bSortable": false, "aTargets": [ 4 ] }
        ],
        "rowCallback": function(nRow, data, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
            if (data.id =='11') {
                $('td:eq(3)', nRow).html("<span class='text-error'>北京</span>");
            } else if (data.id=='41') {
                $('td:eq(3)', nRow).html("<span class='text-error'>河南</span>");
            } 
            $('td:eq(4)', nRow).html("<a href='' user_id='" + data.id + "'  class='ace_detail'>详情</a>");
            return nRow;
        },
    } );
} );
    $(function(){
       window.dialog = dialog;
			
    })
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
			<li class="active">控制台</li>
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
		<div class="page-content">
		  <div class="page-header">
			<h1>
				控制台
				<small>
					<i class="icon-double-angle-right"></i>
					 地区列表2
				</small>
			</h1>
		  </div><!-- /.page-header -->
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
			 <!-- PAGE CONTENT ENDS -->
			</div><!-- /.col -->
		  </div><!-- /.row -->
	    </div><!-- /.page-content -->
      </div><!-- /.main-content -->
	  <#include "/setting-box.ftl">
    </div><!-- /.main-container-inner -->
	<#include "/containerfooter.ftl">
  </div><!-- /.main-container -->
  <#include "/footer.ftl">
</body>
</html>

