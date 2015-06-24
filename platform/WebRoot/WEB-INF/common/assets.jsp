<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"); 
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="${path }/assets/css/content.css" rel="stylesheet">
<!-- 通用弹出框样式 -->
<link href="${path }/assets/js/artdialog/ui-dialog1.css" rel="stylesheet">
<!-- Load  JavaScript Libraries -->
<script type="text/javascript"  src="${path }/assets/js/jquery/jquery.min.js"></script>
<!-- artdialog弹出框 -->
<script type="text/javascript"  src="${path }/assets/js/artdialog/dialog-min.js"></script>
<script type="text/javascript"  src="${path }/assets/js/artdialog/dialog-plus-min.js"></script>
<!-- 验证框架 -->
<link href="${path }/assets/js/jQuery-Validation/css/validationEngine.jquery.css" rel="stylesheet">
<script type="text/javascript"  src="${path }/assets/js/jQuery-Validation/js/jquery.validationEngine.min.js"></script>
<script type="text/javascript"  src="${path }/assets/js/jQuery-Validation/js/jquery.validationEngine-zh_CN.js"></script>

<script type="text/javascript"  src="${path }/assets/js/jquery.placeholder/jquery.placeholder.min.js"></script>
<script type="text/javascript"  src="${path }/assets/js/custom.js"></script>


