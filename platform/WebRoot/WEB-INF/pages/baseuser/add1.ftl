<#include "/taglibs.ftl">
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>新增用户</title>
<#include "/meta.ftl"/>
<#include "/resources.ftl"/>
<script type="text/javascript">
   $(function($){
      window.dialog = dialog;
   })
     //回显省市县
     var pronviceValue="${(item.province)!''}";
	 var cityValue="${(item.city)!''}";
	 var countyValue="${(item.county)!''}";
     $(function(){
        //获取dialog传递过来的数据
        var dialog = top.dialog.get(window);
		var data = dialog.data;
		$('#titleShow').text(data);
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
        var url="${path}/region/list/"+dataId;
	    if(!("province"==selectId)&&index>0){
	       $("#"+selectId).chosen("destroy");
	    }
	    if("city"==selectId&&index>0){
	       $("#county").empty();
	       $("#county").chosen("destroy");
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
		 $("#"+selectId).chosen({
		    no_results_text:"没有匹配的"+suffix,
	        width:"120px"
		 });
		 if("city"==selectId&&index>0){
	        $("#county").chosen({
			    no_results_text:"没有匹配的"+suffix,
		        width:"120px"
			 });
	    }
     }
     function save(){
        $.ajax({
			type:'post',
			url:"${path}/baseuser/save",
			data:$("#form").serialize(),
			dataType:'json',
			success:function(msg){
				if(msg.flag=='succ'){
				   $("#userId").val(msg.id);
				   var time=3000;
				   alertAutoClose(msg.msg,time);
				   setTimeout(function () {
	            	  dialog.close().remove();
	         	   }, time);
				}else{
				   alert(msg.msg);
				}
			}
		});
     }
     
     function openRole(uId){
        var _url="${path}/baseresc/findRescTree";
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
</head>
<body>
<div id="titleShow" class="page-title">用户注册信息</div>
<form id="form" name="form" action="${path}/baseuser/save" method="post">
<table class="table table-condensed">
   <input type="hidden" id="id" name="id" value="${(item.id)!}"/>
   <tr>
       <td width="20%">登录名称</td>
       <td><input type="text" id="loginName" placeholder="登录名" name="loginName" class="col-xs-8 col-sm-5" value="${(item.loginName)!}"></td>
   </tr>
   <tr>
       <td>登录密码</td>
       <td><input type="password" id="password" name="password" placeholder="Password" class="col-xs-8 col-sm-5" value="${(item.password)!}"></td>
   </tr>
   <tr>
       <td>用户昵称</td>
       <td><input type="text" id="nickName" placeholder="中文" name="nickName" class="col-xs-8 col-sm-5" value="${(item.nickName)!}"></td>
   </tr>
   <tr>
       <td>用户邮箱</td>
       <td><input type="text" id="email" placeholder="example@yxkong.com" name="email" class="col-xs-8 col-sm-5" value="${(item.email)!}"></td>
   </tr>
   <tr>
       <td>身份证号</td>
       <td><input type="text" id="idCard" placeholder="身份证号码" name="idCard" class="col-xs-8 col-sm-5" value="${(item.idCard)!}"></td>
   </tr>
   <tr>
       <td>用户性别</td>
       <td>
         <#--  <input type="text" id="sex" placeholder="" name="sex" class="col-xs-8 col-sm-5" value="${(item.sex)!}">-->
              <input type="radio" id="sex1" name="sex" value="1" <#if (item.sex)??&&item.sex==1 >checked</#if> ><span>&nbsp;男&nbsp;</span>
              <input type="radio" id="sex0" name="sex" value="0" <#if (item.sex)??&&item.sex==0 >checked</#if>><span >&nbsp;女&nbsp;</span>
       </td>
   </tr>
   <tr>
       <td>出生日期</td>
       <td><input type="text" id="birthday" placeholder="1988-06-05" name="birthday" class="col-xs-8 col-sm-5" value="<#if (item.birthday)??>${(item.birthday)?string('yyyy-MM-dd')}</#if>"></td>
   </tr>
   <tr>
      <td>所在地区</td>
      <td>
          <select  data-placeholder="请选择省" id="province" name="province" class="chosen-select" ><option value="">请选择省</option></select>
          <select  data-placeholder="请选择市" id="city" name="city" class="chosen-select"  ><option value="">请选择市</option></select>
          <select  data-placeholder="请选择县" id="county" name="county" class="chosen-select"><option value="">请选择县</option></select>
      </td>
   </tr>
   <tr>
     <td>详细地址</td>
     <td><input type="text" id="address" placeholder="*区*街道*号" name="address" class="col-xs-8 col-sm-5" value="${(item.address)!}">
     </td>
   </tr>
   <tr>
       <td>用户手机</td>
       <td><input type="text" id="mobile" placeholder="15652000269" name="mobile" class="col-xs-8 col-sm-5" value="${(item.mobile)!}"></td>
   </tr>
   <tr>
       <td>用户座机</td>
       <td><input type="text" id="telPhone" placeholder="010-5888888" name="telPhone" class="col-xs-8 col-sm-5" value="${(item.telPhone)!}"></td>
   </tr>
   <tr>
     <td>备注</td>
        <td><textarea rows="3" id="remark" name="remark" class="input-xlarge">${(item.remark)!''}</textarea></td>
   </tr>
   <tr>
      <td>关联角色</td>
      <td><a href="javascript:void(0)"  onclick="openRole('${(item.id)!''}')" ><span class="icon-plus-square" style="font-size:18px;"></span></a></td>
   </tr>
</table>
<div id="bottomnav" >
<button type="button" class="btn btn-info" id="addBtn" onclick="save();"><i class="icon-ok bigger-110"></i>保存</button>
<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重置</button>
</div>
</form>
</body>
</html>

