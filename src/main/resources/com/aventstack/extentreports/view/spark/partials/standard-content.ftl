<#if TestService.testHasLog(test)>
<div class="detail-body mt-4">                                              
	<@log test=test />
</div>
</#if>
<#if TestService.testHasScreenCapture(test)>
<div class="detail-foot">
	<ul class="attachments">
		<#list test.screenCaptureContext.all as sc>
		<li>
			<a href="#" data-featherlight="image">
				${sc.source}
			</a>
		</li>
		</#list>
	</ul>
</div>
</#if>
<#if TestService.testHasChildren(test)>
<div class="mt-4">
	<@recurse_nodes test=test />
</div>
</#if>