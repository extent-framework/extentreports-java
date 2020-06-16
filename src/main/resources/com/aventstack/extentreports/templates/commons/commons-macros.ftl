<#macro attributes test>
	<#if TestService.testHasCategory(test)>
		<span class="category-list attr-x">
		<#list test.categoryContext.all as category>
		<span class="category badge"><i class="fa fa-tag"></i> ${category.name}</span>
		</#list>
		</span>
	</#if>
	<#if TestService.testHasAuthor(test)>
		<span class="author-list attr-x">
		<#list test.authorContext.all as author>
		<span class="author badge"><i class="fa fa-user"></i> ${author.name}</span>
		</#list>
		</span>
	</#if>
	<#if TestService.testHasDevice(test)>
		<span class="device-list attr-x">
		<#list test.deviceContext.all as device>
		<span class="device badge"><i class="fa fa-tablet text-sm"></i> ${device.name}</span>
		</#list>
		</span>
	</#if>
</#macro>

<#macro media media>
  <#list media as m>
    <div class="row mb-3"><div class="col-md-3">
    <#if m.base64??><img src="${m.base64}">
    <#elseif m.resolvedPath??><img data-featherlight='${m.resolvedPath}' src="${m.resolvedPath}">
    <#elseif m.path??><img data-featherlight='${m.path}' src="${m.path}">
    </#if>
    </div></div>
  </#list>
</#macro>
