<#macro attributes test>
	<#if TestService.testHasAuthor(test)>
		<#list test.authorContext.all as author>
			<span class="badge badge-pill badge-default"><i class="fa fa-user-o"></i> ${author.name}</span>
		</#list>
	</#if>
	<#if TestService.testHasCategory(test)>
		<#list test.categoryContext.all as category>
			<span class="badge badge-pill badge-default"><i class="fa fa-tag"></i> ${category.name}</span>
		</#list>
	</#if>
	<#if TestService.testHasDevice(test)>
		<#list test.deviceContext.all as device>
			<span class="badge badge-pill badge-default"><i class="fa fa-tablet"></i> ${device.name}</span>
		</#list>
	</#if>
</#macro>