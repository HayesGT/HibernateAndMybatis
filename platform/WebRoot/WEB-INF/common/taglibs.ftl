<#assign path=(rc.getContextPath())!''>
<#assign themeSetting=(AuthenToken.themeSetting)!>
<#function subStr str len>
	<#if (len)?exists>
		<#assign len=15>
	</#if>
	<#if str?length lt len>
	   <#return str>
	   <#else>
	   <#return str.substring(0,len)+'...'>
	</#if>
</#function>

