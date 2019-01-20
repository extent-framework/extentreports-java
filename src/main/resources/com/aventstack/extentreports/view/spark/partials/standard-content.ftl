<#if test.hasLog()>
<div class="detail-body mt-4">                                              
	<@log test=test />
</div>
</#if>
<#if test.hasScreenCapture()>
<div class="detail-foot">
	<ul class="attachments">
		<#list test.screenCaptureList as sc>
		<li>
			<a href="${sc.screenCapturePath}" data-featherlight="image">
				<div class="file-icon">
					<i class="fa fa-file-image-o"></i> 
				</div>
				<div class="file-info">
					<#if sc.isBase64()>
					<span class="file-name">base64</span>
					<span class="file-size">0Kb</span>
					<#else>
					<span class="file-name">${sc.name}</span>
					<span class="file-size"> ${sc.fileSize}Kb</span>
					</#if>
				</div>
			</a>
		</li>
		</#list>
	</ul>
</div>
</#if>
<#if test.hasChildren()>
<div class="mt-4">
	<@recurse_nodes test=test />
</div>
</#if>