<#include "/taglibs.ftl">
<#--引入分页控件-->
<#import "/pager.ftl" as pager>
<#--html头 head头，和meta信心-->
<#include "/meta.ftl">
    <title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
<#--css样式文件和jquery -->
<#include "/assets.ftl">
<#--自定义css样式和脚本-->
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/select2/select2.css"/>
<script type="text/javascript" src="${path}/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${path}/assets/global/plugins/select2/select2_locale_zh-CN.js"></script>
<script src="${path}/assets/global/plugins/datatables/all.min.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>


<#--日期选择-->
<link rel="stylesheet" type="text/css" href="${path}/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

							
<script type="text/javascript">

$(document).ready(function() {
    $('#tableId').dataTable( {
        "sDom": 'tr<"pageNav"flp><"pageInfo"i>', 
        "processing": true,
        "oLanguage": {//下面是一些汉语翻译
            "sSearch": "搜索",
            "sLengthMenu": "每页显示  _MENU_ 条记录",
            "sZeroRecords": "没有检索到数据",
            "sInfo": "显示<span class='page_orange'>_START_</span>至 <span class='page_orange'>_END_</span>条 &nbsp;&nbsp;共 <span class='page_green'>_TOTAL_</span>条",
            "sInfoFiltered": "(筛选自  _MAX_ 条数据)",
            "sInfoEmtpy": "没有数据",
            "sProcessing": "正在加载数据...",
            "sProcessing": "<img src='${path}/assets/images/loading.gif' />", //这里是给服务器发请求后到等待时间显示的 加载gif
                    "oPaginate":
                    {
                        "sFirst": "首页",
                        "sPrevious": "上一页",
                        "sNext": "下一页",
                        "sLast": "末页"
                    }
        },
        "serverSide": true,
        "ajax": $.fn.dataTable.pipeline( {
            url: '${path}/baseuser/listAjax.json',
            pages: 5 // number of pages to cache
        } ),
        "columns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
            {"data": 'loginName'},//mData 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
            {"data": 'nickName'},
            {"data": 'email'},
            {"data": 'sex'},
            {"data": 'birthday'},
            {"data": 'mobile'},
            {"sDefaultContent": '', "sClass": "optTdClass"},//sClass 表示给本列加class
        ],
        "columnDefs":[//配置某几列不能排序
           { "bSortable": false, "aTargets": [ 5 ] },
           { "bSortable": false, "aTargets": [ 6 ] }
        ],
        "rowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
            if (aData.sex ==1) {
                $('td:eq(3)', nRow).html("<span class='text-error'>男</span>");
            } else if (aData.sex==0) {
                $('td:eq(3)', nRow).html("<span class='text-error'>女</span>");
            } 
             $('td:eq(4)', nRow).html(new Date(aData.birthday).format("YYYY-MM-dd"));
            var viewHtml="<a href='javascript:void(0);' onclick='view(&quot;" + aData.id + "&quot;);'><i class='icon-zoom-in bigger-130'></i></a>";
            var editHtml="   <a href='javascript:void(0);' onclick='edit(&quot;" + aData.id + "&quot;);'><i class='icon-pencil bigger-130'></i></a>";
            var delHtml="   <a href='javascript:void(0);' onclick='del(&quot;" + aData.id + "&quot;);'><i class='icon-trash bigger-130'></i></a>";
            $('td:eq(6)', nRow).html(viewHtml+editHtml+delHtml);
            return nRow;
        },
    } );
} );


 $(function(){
     //window.dialog = dialog;
      
    })
    function add(){
        var _url = "${path}/baseuser/add";
        var  _title="新增用户";
        top.dialog({
             id:'addUserDialog',
             url:_url,
             title: _title,
			 width: 650,
			 height: 550,
			 data:_title,//传数据到弹出页面
			 onclose: function () {
			    window.parent.location.reload(true); 
			 }
        }).showModal();
    }
    function view(id){
        var _url = "${path}/baseuser/view/"+id;
    	var  _title="查看用户详情";
        top.dialog({
            id:'viewUserDialog',
            url:_url,
		    title: _title,
		    width: 650,
		    data:_title,
		    height: 500
		}).showModal();		
    }
    function edit(id){
        var _url = "${path}/baseuser/edit/"+id;
    	var  _title="编辑用户";
       top.dialog({
             id:'editUserDialog',
             url:_url,
             title: _title,
			 width: 650,
			 height: 550,
			 data:_title,//传数据到弹出页面
			 onclose: function () {
			    window.parent.location.reload(true); 
			 }
        }).showModal();
    }
    function del(id){
       alert("您确认要删除该人员吗？",function(opt){
         if(opt){
             $.ajax({
					type:'post',
					url:"${path}/baseuser/del/"+id,
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
				<a href="#">用户列表</a>
			</li>
		</ul>
		<div class="page-toolbar">
		</div>
	</div>
	<!-- END PAGE HEADER-->
		  <div class="row">
			<div class="col-xs-12">
			
			<table id="tableId" class="table  table-striped table-bordered">
			 <thead>
			  <tr>
			     <th>登录名</th>
			     <th>用户昵称</th>
			     <th>用户邮箱</th>
			     <th>性别</th>
			     <th>生日</th>
			     <th>手机</th>
			     <th>操作</th>
			   </tr>
			 </thead>
			 <tbody></tbody>
			 </table>
			 </form>
<#include "/footer.ftl">
<div id="ajax-modal" class="modal  fade " data-width="700" data-backdrop="static" data-focus-on="input:first" data-keyboard="false" data-attention-animation="false" tabindex="-1"></div>
<#--js资源文件-->
<#include "/resources.ftl">
<script src="${path}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>

<#--自定义css样式和脚本-->
<script type="text/javascript">
   $(function($){
   })
</script>
<#--html end-->
<#include "/endhtml.ftl">

