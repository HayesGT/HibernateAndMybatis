<#--分页-->
<#assign path=(rc.getContextPath())!''>
<#macro pageTag formName="pageForm" showHidden="true" > 
  <#if (page.pageCount)?? && (page.pageCount gt -1)> 
  		<#if showHidden=="true">
			<input type="hidden" name="page.total" value="${(page.total)!0}"/>
			<input type="hidden" name="page.pageSize" id="pageSize" value="${(page.pageSize)!0}"/>
			<input id="pageNo" type="hidden" name="page.index" value="${(page.index)!0}"/>
			<#if sortParas?? &&(sortParas.length>0)>
				<#list sortParas as sort>
					<input type="hidden" name="orders[${(sort.property)!''},,]" value="${(sort.order)!''}"/>
					<input type="hidden" id="orderBy" value="${sort.property}"/>
				</#list>
			</#if>
		</#if>
		<#if (page.pageCount)?? && ((page.pageCount)>0)>
		 <span class="pageR">
			第 <span class="page_orange">${(page.index)!0}/${(page.pageCount)!0}</span>页&nbsp;
			共<span class="page_green" >${(page.total)!0}</span>条记录&nbsp;&nbsp;
			<a href="javascript:chanagePageNO(1)"><img src="${path}/assets/images/p_1.png" align="AbsBottom" /></a>
			<a href="javascript:chanagePageNO(${(page.upNo)!0})"><img src="${path}/assets/images/p_2.png" align="AbsBottom" /></a>
				
			&nbsp;&nbsp;	
			<span>
			    <#if (page.pageCount)?? && (page.pageCount lt 10)>
			    	 <#list 1..page.pageCount as num>
			    	 	  <a href="javascript:chanagePageNO(${num})" <#if page.index==num> class="page_s activity"</#if> > ${(num)!0}</a>
			    	 </#list>
			    <#else>
			         <#if (page.index)?? && (page.index gt 6)>
			            <#if ((page.index+4) gt page.pageCount)>
			               <#list (page.index-5)..page.pageCount as num>
			    	 		   <a href="javascript:chanagePageNO(${num})" <#if page.index==num> class="page_s activity"</#if>  >${(num)!0}</a>
			    	 	   </#list>
			    	 	<#else>
			    	 		<#list (page.index-5)..(page.index+4) as num>
			    	 			<a href="javascript:chanagePageNO(${num})" <#if page.index==num> class="page_s activity"</#if>  >${num}</a>
			    	 	   </#list>
			            </#if>
			          <#else>
			             <#list 1..10 as num>
			    	 	     <a href="javascript:chanagePageNO(${num})" <#if page.index==num> class="page_s activity"</#if>  >${num}</a>
			    	      </#list>
			          </#if>
			    </#if>
			</span>
			&nbsp;&nbsp;
			<a href="javascript:chanagePageNO(${(page.nextNo)!0})"><img src="${path}/assets/images/p_4.png" align="AbsBottom" /></a>
			<a href="javascript:chanagePageNO(${(page.pageCount)!0})"><img src="${path}/assets/images/p_5.png" align="AbsBottom" /></a>
			
			<!--每页显示-->
            <span style="padding-left:10px;">每页</span> 
			<span>
				<select  id="zdypageSize" onchange="chanagePageSize(this.value);">
				  <option value="10" <#if (page.pageSize)?? && page.pageSize==10>selected</#if> >10</option>
	              <option value="20" <#if (page.pageSize)?? && page.pageSize==20>selected</#if> >20</option>
	              <option value="50" <#if (page.pageSize)?? && page.pageSize==50>selected</#if> >50</option>
	              <option value="100" <#if (page.pageSize)?? && page.pageSize==100>selected</#if> >100</option>
	              <option value="200" <#if (page.pageSize)?? && page.pageSize==200>selected</#if> >200</option>
				</select>
			</span> 
			<span>条</span>
       </span>	
</#if>
		<#if showHidden=="true">
			<script type="text/javascript">
				//换页
				function chanagePageNO(pageIndex){
					if(${page.index}==pageIndex){
					//	return;
					}
					var pageNo = document.getElementById("pageNo");
					pageNo.value = pageIndex;
					//document.forms['${formName}'].submit();
					//最得表单的target属性
					var reponseTarget =document.forms['${formName}'].target;
					if(!reponseTarget){//如果没有则提交表单
						document.forms['${formName}'].submit();
					}else{//如果有则异步提交表单,注意主页面要引入:jquery.form.js
						$("#pageForm").ajaxForm({ 
						url:document.forms['${formName}'].action,    
					    type:     'post',    
					    success:function(msg){  
					    	 $("#"+reponseTarget).html(msg);
					    	  $(".dzlist tr:odd").css("background-color","#f4ffff");  
					    	}
						});  	
					    $("#pageForm").submit(); 
					} 
					
				}
				
				//改变页大小 
				function chanagePageSize(size){
					var pageSize = document.getElementById("pageSize");
					pageSize.value = size; 
				    // 写入cookie
				    //setCookie("pageSize",pageSize);
					//document.forms['${formName}'].submit();
					//最得表单的target属性
					var reponseTarget =document.forms['${formName}'].target;
					if(!reponseTarget){//如果没有则提交表单
						document.forms['${formName}'].submit();
					}else{//如果有则异步提交表单,注意主页面要引入:jquery.form.js
						$("#pageForm").ajaxForm({ 
						url:document.forms['${formName}'].action,    
					    type:     'post',    
					    success:function(msg){  
					    	 $("#"+reponseTarget).html(msg);
					    	 $(".dzlist tr:odd").css("background-color","#f4ffff");  
					    	}
						});  	
					    $("#pageForm").submit(); 
					} 
				}
				
				function setOrder(e){
					var ename=e.name;
					orderby(ename);
				}
				//排序
				function orderby(title){
					var pageform=document.forms['${formName}'];
					var order = $("input[name*='orders']");
					var orderValue = order.val();
					if(orderValue=="desc"){
						order.val("asc");
					}else{
					 	order.val("desc");
					}
					order.attr("name","orders["+title+",,]");
					pageform.submit();
				}
				
				//在document加载中,需要设置排序的图片.
				function setOrderImg(){
					var order = $("input[name*='orders']");
					var orderValue = order.val();
					var orderName=jQuery("#orderBy").val();
					if(orderName==""){
						return;
					}
					if(jQuery("a[name='"+orderName+"']")==null){
						return;
					}
					if (orderValue == "desc"){
						jQuery("a[name='"+orderName+"'] span").html("<img src='${path}/images/arrows/arrow1_down.gif'/>");
					}else if(orderValue == "asc"){
						jQuery("a[name='"+orderName+"'] span").html("<img src='${path}/images/arrows/arrow1_up.gif'/>");
					}
				}
			</script> 
		</#if>
  </#if>
</#macro>
			
			
		
			
			