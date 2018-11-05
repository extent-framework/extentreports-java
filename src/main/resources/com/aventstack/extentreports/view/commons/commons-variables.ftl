<#assign config=report.configContext>
<#assign offline="false">
<#if config.getValue("enableOfflineMode")??><#assign offline=config.getValue("enableOfflineMode")></#if>

<#assign theme=config.containsKey('theme')?then(config.getValue('theme')?lower_case, '')>
<#assign systemAttributeContext=report.getSystemAttributeContext().getSystemAttributeList()>
<#assign categoryContext=report.getCategoryContextInfo().getTestAttributeTestContextList()>
<#assign authorContext=report.getAuthorContextInfo().getTestAttributeTestContextList()>
<#assign deviceContext=report.getDeviceContextInfo().getTestAttributeTestContextList()>
<#assign exceptionContext=report.getExceptionContextInfo().getExceptionTestContextList()>

<#assign reportType="" parentHeading="Tests" childHeading="Steps" grandChildHeading="" size=2>
<#if report.analysisStrategy=="SUITE">
	<#assign parentHeading="Suite" childHeading="Tests" grandChildHeading="Tests" size=2>
	<#if report.reportStatusStats.grandChildCount!=0>
		<#assign childHeading="Classes" grandChildHeading="Tests" size=3>
	</#if>
</#if>
<#if report.analysisStrategy=="BDD">
	<#assign reportType="bdd" parentHeading="Features" childHeading="Scenarios" grandChildHeading="Steps" size=3>
</#if>
<#if report.analysisStrategy=="CLASS">
	<#assign parentHeading="Class" childHeading="Methods" grandChildHeading="" size=2>
</#if>
