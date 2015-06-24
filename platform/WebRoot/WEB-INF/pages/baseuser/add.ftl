<#include "/taglibs.ftl">

<script type="text/javascript">
     //回显省市县
     var pronviceValue="${(item.province)!''}";
	 var cityValue="${(item.city)!''}";
	 var countyValue="${(item.county)!''}";
     $(function(){
        if(!cityValue){
           $("#city").select2({
		        width:"120px"
			 });
          
        }
        if(!countyValue){
            $("#county").select2({
		        width:"120px"
			 });
        }
        
		//var data = dg.data;
		var provinceIndex=0;
		var cityIndex=0;
		//获取省份信息,并选中填写的省份
		selectChange("province","0",'省',provinceIndex,pronviceValue);
		//获取市信息并选中
		if(pronviceValue){
		   provinceIndex++;
		   selectChange("city",pronviceValue,'市',cityIndex,cityValue);
		   cityIndex++;
		}
		//获取县信息并选中
		if(cityValue){
		   selectChange("county",cityValue,'县',0,countyValue);
		}
		
		$("#province").change(function(){
		   selectChange("city",$("#province").val(),'市',provinceIndex);
		   provinceIndex++;
		});
		$("#city").change(function(){
		   selectChange("county",$("#city").val(),'县',cityIndex);
		   cityIndex++;
		});
		//
		$("#idCard").change(function(){
		   cidInfo(this.value,'province','birthday','sex');
		})
     })
		  
     /**获取省市区方法
      * @param selectId 要绑定数据的id
      * @param dataId  要查询的parentId的值
      * @param suffix  查询不到，提示的后缀
      * @param index  查询的次数，从0开始，0的时候要做一些处理
      * @param selectValue 初始化查询要绑定的值
      */
     function selectChange(selectId,dataId,suffix,index,selectValue){
        var url="${path}/region/list/"+dataId+".json";
	    if(!("province"==selectId)&&index>0){
	       $("#"+selectId).select2("destroy");
	    }
	    if("city"==selectId&&index>0){
	       $("#county").empty();
	       $("#county").select2("destroy");
	    }
	    var columnSelect=$("#"+selectId).empty();
		columnSelect.append("<option vlaue=''></option>");
		$.ajax({
	      url: url,
	      type:'post',
	      async:false,
	      dataType:'json',
	      success: function(jsonData){
	         if(jsonData){
				 $.each(jsonData,function(index,node){
				    if(jsonData[index]!=null){
						var $option=$("<option/>");
				        $option.attr("value",jsonData[index].id);
				        $option.append(jsonData[index].name);
				        if(selectValue&&selectValue==jsonData[index].id){
				            $option.attr("selected",true);
				        }
				        columnSelect.append($option);
				    }
				 });
				 $("#"+selectId).append(columnSelect);
	         }
		 }});
		 $("#"+selectId).select2({
	        width:"120px"
		 });
		 if("city"==selectId&&index>0){
	        $("#county").select2({
		        width:"120px"
			 });
	    }
     }
     function save(){
        $.ajax({
			type:'post',
			url:"${path}/baseuser/save.json",
			data:$("#form").serialize(),
			dataType:'json',
			success:function(msg){
				if(msg.flag=='succ'){
				   $("#userId").val(msg.id);
				   var time=3000;
				   alertAutoClose(msg.msg,time);
				   setTimeout(function () {
	            	  dg.close().remove();
	         	   }, time);
				}else{
				   alert(msg.msg);
				}
			}
		});
     }
     
     function openRole(uId){
        var _url="${path}/baseresc/findRescTree.json";
        var _title="关联角色";
        top.dialog({
             id:'openRole',
             url:_url,
             title: _title,
			 width: 400,
			 height: 350
        }).showModal();
     }
</script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title"><#if (item.id)??>修改用户</#if><#if !(item.id)??>新增用户</#if></h4>
</div>
<div class="modal-body">
  <div class="form">
	<form id="form" class="form-horizontal" role="form" name="form" action="${path}/baseuser/save" method="post">
		<input type="hidden" id="id" name="id" value="${(item.id)!}"/>
		<div class="form-body">
			<#--row start-->
		    <div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">登录名称</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-icon">
								<i class="fa fa-user"></i>
								<input type="text" id="loginName" placeholder="登录名" name="loginName" class="form-control" value="${(item.loginName)!}">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">用户昵称</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-icon">
								<i class="fa icon-user"></i>
								<input type="text" id="nickName" placeholder="中文" name="nickName" class="form-control" value="${(item.nickName)!}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<#--row end-->
			<#--row start-->
		    <div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">登录密码</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-icon">
								<i class="fa fa-lock"></i>
								<input type="password" id="password" name="password" placeholder="密码" class="form-control" value="${(item.password)!}">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">确认密码</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-group">
							    <span class="input-group-addon">
								   <i class="fa fa-lock"></i>
								</span>
								<input type="text" class="form-control" placeholder="确认密码">
							</div>
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
							<div class="input-icon">
								<i class="fa fa-envelope"></i>
								<input type="text" id="email" placeholder="example@yxkong.com" name="email" class="form-control" value="${(item.email)!}">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">身份证号</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-icon">
								<i class="fa fa-credit-card"></i>
								<input type="text" id="idCard" placeholder="身份证号码" name="idCard" class="form-control" value="${(item.idCard)!}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<#--row end-->
			<#--row start-->
		    <div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">用户性别</label>
						<div class="col-md-9 col-sm-9">
							<div class="radio-list">
								<label class="radio-inline">
								 <div class="radio"><span class="<#if (item.sex)??&&item.sex==1 >checked</#if>"><input type="radio" id="sex1" name="sex" value="1" <#if (item.sex)??&&item.sex==1 >checked=''</#if> ></span></div>
								  男 </label>
								<label class="radio-inline">
								<div class="radio"><span class="<#if (item.sex)??&&item.sex==0 >checked</#if>"><input type="radio" id="sex2" name="sex" value="0" <#if (item.sex)??&&item.sex==0 >checked=''</#if> ></span></div>
								女 </label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">出生日期</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-group input-medium date date-picker" data-date-format="yyyy-mm-dd" >
								<input type="text" id="birthday" placeholder="1988-06-05" name="birthday" readonly class="form-control" value="<#if (item.birthday)??>${(item.birthday)?string('yyyy-MM-dd')}</#if>">
								<span class="input-group-btn">
								   <button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<#--row end-->
			<#--row start-->
		    <div class="row">
				<div class="form-group">
					<label class="control-label col-md-2">所在地区</label>
					<div class="col-md-8">
						<select  data-placeholder="请选择省" id="province" name="province" class="form-control col-md-3 col-sm-3 select2me" ><option value="">请选择省</option></select>
				        <select  data-placeholder="请选择市" id="city" name="city" class="form-control col-md-3 col-sm-3 select2me"  ><option value="">请选择市</option></select>
				        <select  data-placeholder="请选择县" id="county" name="county" class="form-control col-md-3 col-sm-3 select2me"><option value="">请选择县</option></select>
					</div>
				</div>
			</div>
			<#--row end-->
			<#--row start-->
		    <div class="row">
				<div class="form-group">
					<label class="control-label col-md-2">详细地址</label>
					<div class="col-md-8">
						<div class="input-icon">
							<i class="fa fa-map-marker"></i>
							<input type="text" id="address" placeholder="*区*街道*号" name="address" class="form-control" value="${(item.address)!}">
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
							<div class="input-icon">
								<i class="fa  fa-mobile"></i>
								<input type="text" id="mobile" placeholder="15652000269" name="mobile" class="form-control" value="${(item.mobile)!}">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">用户座机</label>
						<div class="col-md-9 col-sm-9">
							<div class="input-icon">
								<i class="fa  fa-phone"></i>
								<input type="text" id="telPhone" placeholder="010-5888888" name="telPhone" class="form-control" value="${(item.telPhone)!}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<#--row end-->
			<div class="row">
				<div class="form-group">
				    <label class="col-md-2 control-label">备注</label>
				    <div class="col-md-8">
						<textarea rows="3" id="remark" name="remark" class="form-control">${(item.remark)!''}</textarea>
				    </div>
				</div>
			</div>
		</div>
	</form>
  </div>
</div>
<div class="modal-footer">
	<button type="button" data-dismiss="modal" class="btn">关闭</button>
	<button type="button" class="btn btn-primary">保存</button>
</div>
<script type="text/javascript">
   $(function($){
       if ($.datepicker) {
            $('.date-picker').datepicker({
                rtl: Metronic.isRTL(),
                orientation: "left",
                autoclose: true
            });
            //$('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
        }
   })
</script>



