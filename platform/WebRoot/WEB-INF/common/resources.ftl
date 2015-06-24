<!--[if lt IE 9]>
<script src="${path}/assets/global/plugins/respond.min.js"></script>
<script src="${path}/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->

<script src="${path}/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${path}/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${path}/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${path}/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${path}/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${path}/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${path}/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<#--bootstrap modal-->
<script src="${path}/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${path}/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
   Metronic.init("${path}"); // init metronic core componets
   <#--左侧菜单-->
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
   <#--配置切换-->
   Demo.init(); // init demo features 
   Index.init();   
    // general settings
    $.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = 
      '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' +
        '<div class="progress progress-striped active">' +
          '<div class="progress-bar" style="width: 100%;"></div>' +
        '</div>' +
      '</div>';

    $.fn.modalmanager.defaults.resize = true;
    
});
</script>