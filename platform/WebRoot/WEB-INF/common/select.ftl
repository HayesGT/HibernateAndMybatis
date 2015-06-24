<#--  
自定义指令commonSelect的参数说明，如下  
id           必填。用于指定<select>的id和name属性值
list         必填    列表
defaultText  选填。根据文本内容选中option  
defaultValue 选填。根据value值选中option  
headKey      选填。用于指定所显示的第一个<option>，其具有提示信息的意义  
headText     选填。同headKey
className    className 校验样式 
-->  
<#macro commonSelect id list disabled="" readonly="" defaultValue="" defaultText="" headKey="" headText=""  className="" changeEventname="">  
    <select id="${id}" name="${id}" <#if disabled!="">disabled=${disabled}</#if> class="${className}"  onChange="${changeEventname}">  
	    <#-- 判断是否需要显示提示性的<option> -->  
	    <#if headText!="">  
	        <option value="${headKey}">${headText}</option>  
	    </#if>
	     <#list list  as subject>
     			<#if defaultValue==subject.getSubCode() || defaultText==subject.getSubName()>
            		<option value="${subject.getSubCode()}" selected>${subject.getSubName()}</option>
            	<#else>
            		<option value="${subject.getSubCode()}">${subject.getSubName()}</option>
            	</#if>
	    </#list>
    </select>  
</#macro>