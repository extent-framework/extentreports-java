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

<#macro media media>
  <#if media?? && media?is_enumerable><#list media as m><@mediaSingle m /></#list>
  <#else><@mediaSingle media />
  </#if>
</#macro>

<#macro mediaSingle m>
    <div class="row mb-3"><div class="col-md-3">
    
	<#if TestService.isVideo(m)>	    
	    <video width=100% height=auto controls src='${TestService.videoSrc(m)}'/>
     </#if>
  
    <#if TestService.isScreenCapture(m)>
	    <#if m.base64??>
	      <#if config.thumbnailForBase64()?? && config.thumbnailForBase64()>
	        <a href="${m.base64}" class="base64-img" data-featherlight="image"><img src=""></a>
	      <#else>
	        <a href="${m.base64}" data-featherlight="image"><span class="badge badge-gradient-primary">base64 img</span></a>
	      </#if>
	    <#elseif m.resolvedPath??><img data-featherlight='${m.resolvedPath}' src="${m.resolvedPath}">
	    <#elseif m.path??><img data-featherlight='${m.path}' src="${m.path}">
	    </#if>    
     </#if>
  
  	    <#if m.title??><div class="title">${m.title}</div></#if>

    </div></div>
</#macro>
