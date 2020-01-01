<#assign config=report.configurationStore>
<#assign offline="false">
<#if config.getConfig("enableOfflineMode")??><#assign offline=config.getConfig("enableOfflineMode")></#if>

<#assign theme=config.containsConfig('theme')?then(config.getConfig('theme')?lower_case, '')>
<#assign systemAttributeContext=report.getSystemAttributeContext().getSystemAttributeList()>
<#assign categoryContext=report.getCategoryContextInfo().getTestAttributeTestContext()>
<#assign authorContext=report.getAuthorContextInfo().getTestAttributeTestContext()>
<#assign deviceContext=report.getDeviceContextInfo().getTestAttributeTestContext()>
<#assign exceptionContext=report.getExceptionContextInfo().getExceptionTestContext()>

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

<#assign timeStampFormat=config.getConfig('timeStampFormat')>