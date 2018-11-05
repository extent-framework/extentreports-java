<#macro attributes test>
	<#if test.hasCategory()>
		<span class="category-list attr-x">
		<#list test.categoryContext.all as category>
		<span class="category badge"><i class="fa fa-tag"></i> ${category.name}</span>
		</#list>
		</span>
	</#if>
	<#if test.hasAuthor()>
		<span class="author-list attr-x">
		<#list test.authorContext.all as author>
		<span class="author badge"><i class="fa fa-user"></i> ${author.name}</span>
		</#list>
		</span>
	</#if>
	<#if test.hasDevice()>
		<span class="device-list attr-x">
		<#list test.deviceContext.all as device>
		<span class="device badge"><i class="fa fa-tablet text-sm"></i> ${device.name}</span>
		</#list>
		</span>
	</#if>
</#macro>

<#macro media el>
	<#if el.hasScreenCapture()>
		<#if el.screenCaptureContext??>${el.screenCaptureContext.last.source}
		<#else>${el.screenCaptureList[0].source}</#if>
	</#if>
</#macro>

<#macro row test level>
<tr class="s-${test.status}">
	<td><span class="w-32 avatar circle ${test.status}"><span class="badge">${test.status}</span></span></td>
	<td class="_600">${test.name} <#if test.level!=0><br/><span class="text-muted text-sm">${test.parent.name}</span></#if></td>
	<td>${test.runDuration}c</td>
	<td><@attributes test=test /></td>
	<td>
		<@media el=test />
		<#list test.logContext.all as log>
		<@media el=log />
		</#list>
	</td>
</tr>
<#if test.status==Status.FAIL || test.status==Status.SKIP>
<tr class="details">
	<td colspan="5">
	<#list test.logContext.all as log>
		<#if log.details??><pre>${log.details}</pre></#if>
		<#if log.exceptionInfo??><pre>${log.exceptionInfo.stackTrace}</pre></#if>
	</#list>
	</td>
</tr>
</#if>
</#macro>