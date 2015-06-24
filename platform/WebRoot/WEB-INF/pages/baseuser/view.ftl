<#include "/taglibs.ftl">
<script type="text/javascript">
   $(function(){
	   var idsArr=new Array();
	   var province="${item.province}";
	   var city="${item.city}"
	   var county="${item.county}";
	   if(province){
		   idsArr.push(province);
	   }
	   if(city){
	       idsArr.push(city);
	   }
	   if(county){
	      idsArr.push(county);
	   }
	   $.ajax({
	      url: '${path}/region/listByIds?ids='+idsArr+".json",
	      type:'post',
	      async:false,
	      dataType:'json',
	      success: function(jsonData){
	         if(jsonData){
				 $.each(jsonData,function(index,node){
				    if(jsonData[index]!=null){
				      if(jsonData[index].id==province){
				         $("#province").text(jsonData[index].name);
				      }
				      if(jsonData[index].id==city){
				         $("#city").text(jsonData[index].name);
				      }
				      if(jsonData[index].id==county){
				         $("#county").text(jsonData[index].name);
				      }
				    }
				 });
	         }
		 }});
   })
   
</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title">查看用户</h4>
</div>
<div class="modal-body">
  <div class="form">
	<form id="form" name="" action="#" class="form-horizontal" method="post">
	  <div class="form-body">
	    <#--row start-->
	    <div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">登录名称</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">${(item.loginName)!}</p>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">用户昵称</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">${(item.nickName)!}</p>
					</div>
				</div>
			</div>
		</div>
		<#--row end-->
	    <#--row start-->
	    <div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">用户邮箱</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">${(item.email)!}</p>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">身份证号</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">${(item.idCard)!}</p>
					</div>
				</div>
			</div>
		</div>
	    <#--row start-->
	    <div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">用户性别</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static"><#if (item.sex)??&&item.sex==1 >男<#else>女</#if></p>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">出生日期</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static"><#if (item.birthday)??>${(item.birthday)?string('yyyy-MM-dd')}</#if></p>
					</div>
				</div>
			</div>
		</div>
		<#--row end-->
	    <#--row start-->
	    <div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">所在地区</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">
						   <span id="province"></span>
	                       <span id="city"></span>
	                       <span id="county"></span>
	                    </p>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">详细地址</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">${(item.address)!}</p>
					</div>
				</div>
			</div>
		</div>
		<#--row end-->
	    <#--row start-->
	    <div class="row">
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">用户手机</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">
						  ${(item.mobile)!}
	                    </p>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-sm-6">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">用户座机</label>
					<div class="col-md-9 col-sm-9">
						<p class="form-control-static">${(item.telPhone)!}</p>
					</div>
				</div>
			</div>
		</div>
		<#--row end-->
	  </div>
	</form>
  </div>
</div>
<div class="modal-footer">
	<button type="button" data-dismiss="modal" class="btn">关闭</button>
</div>
