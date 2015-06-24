<script type="text/javascript">
	$(function($){
	   //获取url，找到对应的li，并且给该菜单和父级菜单添加active
	   var strPath=window.document.location.pathname;
	   var $pId=$(".leftnav[href='"+strPath+"']");
	   $pId.parent().addClass("active");
	   $("#"+$pId.attr("pId")).append($("<span>").addClass("selected")).parent().addClass("active open");
	})
	
</script>
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<#-- BEGIN SIDEBAR MENU -->
			<ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<li class="sidebar-search-wrapper">
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<form class="sidebar-search " action="extra_search.html" method="POST">
						<a href="javascript:;" class="remove">
						<i class="icon-close"></i>
						</a>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
						</div>
					</form>
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
			 <#if (AuthenToken.listResc)??>
	            <#list AuthenToken.listResc as resc >
	               <li class="<#if resc_index==0>start</#if> <#if resc_has_next==false>last</#if>" >
	                <a id="${resc.id}" pid="${resc.pId}" href="javascript:void(0);" treelevel="${(resc.treelevel)!''}" >
	                   <i class="${(resc.icon)!''}"></i>
	                  <span class="title">${(resc.name)!''}</span>
	                   <#if (resc.childrens)??>
					     <span class="arrow open"></span>
	                 </a>
	                     <ul class="sub-menu" >
	                        <#list (resc.childrens) as child >
	                          <li>
	                            <a id="${child.id}" pid="${child.pId}" href="${path}/${(child.url)!''}"  treelevel="${(child.treelevel)!''}" class="leftnav" >
									<i class="${(child.icon)!''}"></i>
									${(child.name)!''}
								</a>
	                          </li>
	                        </#list>
	                     </ul>
	                   <#else>
	                   </a>
	                   </#if>
	               </li>
	            </#list>
	        </#if>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<#-- END SIDEBAR -->
	<script type="text/javascript" >
	 $(function($){
	    $("#themeOptionBtn").on("click",function(){
	       var themeColor = $(".theme-colors > ul > li.current").attr("data-style");
	       $("#themeColor").val(themeColor);
		   $.ajax({
			    type:'post',
				url:"${path}/themeSetting/save.json",
				data:$("#themeOption").serialize(),
				dataType:'json',
				success:function(msg){
					if(msg.flag=='succ'){
					   $("#themeId").val(msg.id);
					}else{
					}
					alert(msg.msg);
				}
		   });
	    });
 	 })
	</script>
    <#-- BEGIN CONTENT -->
	 <div class="page-content-wrapper">
	    <div class="page-content">
	    <#-- BEGIN STYLE CUSTOMIZER -->
	     <form id="themeOption" name="form" action="${path}/themeSetting/save.json" method="post">
	        <input type="hidden" id="themeId" name="id" value="${(AuthenToken.themeSetting.id)!}"/>
		    <input type="hidden" name="themeColor" id="themeColor"/>
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>主题颜色</span>
						<ul>
							<li class="color-default <#if (themeSetting.themeColor)??&&(themeSetting.themeColor)=='default'>current</#if>  tooltips" data-style="default" data-container="body" data-original-title="Default">
							</li>
							<li class="color-darkblue <#if (themeSetting.themeColor)??&&(themeSetting.themeColor)=='darkblue'>current</#if> tooltips" data-style="darkblue" data-container="body" data-original-title="Dark Blue">
							</li>
							<li class="color-blue <#if (themeSetting.themeColor)??&&(themeSetting.themeColor)=='blue'>current</#if> tooltips"  data-style="blue" data-container="body" data-original-title="Blue">
							</li>
							<li class="color-grey <#if (themeSetting.themeColor)??&&(themeSetting.themeColor)=='grey'>current</#if> tooltips"  data-style="grey" data-container="body" data-original-title="Grey">
							</li>
							<li class="color-light <#if (themeSetting.themeColor)??&&(themeSetting.themeColor)=='light'>current</#if> tooltips"  data-style="light" data-container="body" data-original-title="Light">
							</li>
							<li class="color-light2 <#if (themeSetting.themeColor)??&&(themeSetting.themeColor)=='light2'>current</#if> tooltips"  data-style="light2" data-container="body" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>主题样式</span>
						<select class="layout-style-option form-control input-sm" name="themeStyle">
							<option value="square" <#if (themeSetting.themeStyle)??&&(themeSetting.themeStyle)=='square'>selected="selected"</#if> >方角</option>
							<option value="rounded" <#if (themeSetting.themeStyle)??&&(themeSetting.themeStyle)=='rounded'>selected="selected"</#if>>圆角</option>
						</select>
					</div>
					<div class="theme-option">
						<span>布局</span>
						<select class="layout-option form-control input-sm" name="layout">
							<option value="fluid" <#if (themeSetting.layout)??&&(themeSetting.layout)=='fluid'>selected="selected"</#if>>流体模型</option>
							<option value="boxed" <#if (themeSetting.layout)??&&(themeSetting.layout)=='boxed'>selected="selected"</#if>>盒子模型</option>
						</select>
					</div>
					<div class="theme-option">
						<span>头部</span>
						<select class="page-header-option form-control input-sm" name="header">
							<option value="fixed"   <#if (themeSetting.header)??&&(themeSetting.header)=='fixed'>selected="selected"</#if>>固定</option>
							<option value="default" <#if (themeSetting.header)??&&(themeSetting.header)=='default'>selected="selected"</#if>>默认</option>
						</select>
					</div>
					<div class="theme-option">
						<span>顶部下拉菜单样式</span>
						<select class="page-header-top-dropdown-style-option form-control input-sm" name="topMenuDropdown">
							<option value="light" <#if (themeSetting.topMenuDropdown)??&&(themeSetting.topMenuDropdown)=='light'>selected="selected"</#if>>浅色</option>
							<option value="dark"  <#if (themeSetting.topMenuDropdown)??&&(themeSetting.topMenuDropdown)=='dark'>selected="selected"</#if>>深色</option>
						</select>
					</div>
					<div class="theme-option">
						<span>侧边模式</span>
						<select class="sidebar-option form-control input-sm" name="sidebarMode">
							<option value="fixed" <#if (themeSetting.sidebarMode)??&&(themeSetting.sidebarMode)=='fixed'>selected="selected"</#if>>固定</option>
							<option value="default" <#if (themeSetting.sidebarMode)??&&(themeSetting.sidebarMode)=='default'>selected="selected"</#if>>默认</option>
						</select>
					</div>
					<div class="theme-option">
						<span>侧边二级菜单</span>
						<select class="sidebar-menu-option form-control input-sm" name="sidebarMenu">
							<option value="accordion" <#if (themeSetting.sidebarMenu)??&&(themeSetting.sidebarMenu)=='accordion'>selected="selected"</#if>>折叠</option>
							<option value="hover"     <#if (themeSetting.sidebarMenu)??&&(themeSetting.sidebarMenu)=='hover'>selected="selected"</#if>>悬浮</option>
						</select>
					</div>
					<div class="theme-option">
						<span>侧边样式</span>
						<select class="sidebar-style-option form-control input-sm" name="sidebarStyle">
							<option value="default" <#if (themeSetting.sidebarStyle)??&&(themeSetting.sidebarStyle)=='default'>selected="selected"</#if>>默认</option>
							<option value="light"   <#if (themeSetting.sidebarStyle)??&&(themeSetting.sidebarStyle)=='light'>selected="selected"</#if>>浅色</option>
						</select>
					</div>
					<div class="theme-option">
						<span>侧边位置</span>
						<select class="sidebar-pos-option form-control input-sm" name="sidebarPosition">
							<option value="left"  <#if (themeSetting.sidebarPosition)??&&(themeSetting.sidebarPosition)=='left'>selected="selected"</#if>>左侧</option>
							<option value="right" <#if (themeSetting.sidebarPosition)??&(themeSetting.sidebarPosition)=='right'>selected="selected"</#if>>右侧</option>
						</select>
					</div>
					<div class="theme-option">
						<span>底部</span>
						<select class="page-footer-option form-control input-sm" name="footer">
							<option value="fixed"   <#if (themeSetting.footer)??&&(themeSetting.footer)=='fixed'>selected="selected"</#if>>固定</option>
							<option value="default" <#if (themeSetting.footer)??&&(themeSetting.footer)=='default'>selected="selected"</#if>>默认</option>
						</select>
					</div>
					<div class="theme-option">
					    <input type="button" id="themeOptionBtn" class="btn btn-primary btn-block"  value="保存">
					</div>
				</div>
			</div>
			</form>
			<#-- END STYLE CUSTOMIZER -->