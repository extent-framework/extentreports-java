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
<#assign n=test level=n.level>
<#if level!=0><#assign n=test.parent><#if n.level!=0><#assign n=n.parent><#if n.level!=0><#assign n=n.parent></#if></#if></#if>
	
<tr class="s-${test.status}" test-id="${test.getID()}" parent-id="${n.getID()}">
	<td><span class="w-32 avatar circle ${test.status}"><span class="badge">${test.status}</span></span></td>
	<td class="_600">${test.name} <#if level!=0><br/><span class="text-muted text-sm">${test.parent.name}</span></#if></td>
	<td>${test.runDuration}c</td>
	<td><@attributes test=test /></td>
	<td>
		<@media el=test />
		<#list test.logContext.all as log>
		<@media el=log />
		</#list>
	</td>
	<td class="static"><i class="static fa fa-external-link"></i></td>
</tr>
<#if test.status==Status.FAIL || test.status==Status.SKIP>
<tr class="details">
	<td colspan="5">
	<#list test.logContext.all as log>
		<#if log.details??><textarea disabled class="code-block">${log.details}</textarea></#if>
		<#if log.exceptionInfo??><textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea></#if>
	</#list>
	</td>
</tr>
</#if>
</#macro>