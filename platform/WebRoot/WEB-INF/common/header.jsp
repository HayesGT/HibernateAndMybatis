<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   pageContext.setAttribute("path",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"); 
%>
<<style>
<!--
  #changePwd,#loginOut{
    cursor: pointer;
  }
-->
</style>
<script type="text/javascript">
<!--
  $(function($){
 
     $("#changePwd").on("click",function(){
        var _url = "${path}/portal/userInfo/toEditPwd.html";
         var  _title="修改密码";
         top.dialog({
             id:'addUserdialog',
             url:_url,
             title: _title,
 			 width: 300,
 			 height: 180,
 			 data:_title,//传数据到弹出页面
 			 onclose:function(){
 			 }
         }).showModal();
     });
     $("#loginOut").on("click",function(){
        window.location.href="${path}/loginOut.html";
     });
  });
//-->
</script>
<div id="header">
	<div class="top">
    <table border="0" cellspacing="0" cellpadding="0" align="right">
      <tr>
        <td align="right">您好！ ${AuthenToken.cn}</td>
        <td class="paddingL50"><img  class="img-t" src="${path }/assets/images/icon-t1.png" style="padding-top:5px;"/></td>
        <td class="paddingR40"><span id="changePwd">修改密码</span></td>
        <td align="center" class="orange paddingLR20" ><span id="loginOut">退出系统</span></td>
      </tr>
    </table>
  </div>
</div>