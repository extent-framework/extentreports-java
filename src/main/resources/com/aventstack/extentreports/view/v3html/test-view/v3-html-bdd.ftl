<#if test.description?? && test.description?has_content>
<div class='test-desc'>${ test.description}</div>
</#if>
<#list test.nodeContext.all as node>
<div class='${ node.getBehaviorDrivenType().getSimpleName()?lower_case } node' test-id='${ node.getID() }' status='${ node.status }'>
	<#if node.hasCategory()>
	<div class='category-list'>
		<#list node.categoryContext.all as category>
		<span class='category label'>${ category.name }</span>
		</#list>
	</div>
	</#if>
	<span class='duration right label'>${ node.runDuration }</span>
	<div class='desc'>
		<h6 class="desc-text">${node.getBehaviorDrivenType().getSimpleName()}: ${ node.name }</h6>
		<#if node.screenCaptureList?? && node.screenCaptureList?size != 0>
		<ul class='screenshots right'>
			<#list node.screenCaptureList as sc>
			<li><a data-featherlight="image" href="${ sc.path }"><i class='material-icons'>panorama</i></a></li>
			</#list>
		</ul>
		</#if>
		<#if node.description?? && node.description?has_content>
		${ node.description }
		</#if>
	</div>
	<#if node.hasChildren()>
	<ul class='steps'>
		<#list node.nodeContext.all as child>
		<li test-id='${ child.getID() }' class='node ${ child.getBehaviorDrivenType().getSimpleName()?lower_case } ${ child.status }' status='${ child.status }'>
			<h6 class="bdd-step-name">${ child.name }</h6>
			<#if child.screenCaptureList?? && child.screenCaptureList?size != 0>
			<ul class='screenshots right'>
				<#list child.screenCaptureList as sc>
				<li><a data-featherlight="image" href="${ sc.path }"><i class='material-icons'>panorama</i></a></li>
				</#list>
			</ul>
			</#if>
			<#if child.description?? && child.description?has_content>
			${ child.description }
			</#if>
			<#list child.logContext.all as log>
			<#if log.exceptionInfo??>
				<pre>${log.exceptionInfo.stackTrace}</pre>
			<#else>
				${log.details}
			</#if>
			</#list>
			<#if child.hasChildren()>
			<ul class='gc steps'>
				<#list child.nodeContext.all as gc>
					<li test-id='${ gc.getID() }' class='gc ${ gc.getBehaviorDrivenType().getSimpleName()?lower_case } ${ gc.status }' status='${ gc.status }'>
						<h6 class="bdd-step-name">${ gc.name }</h6>
						<#if gc.screenCaptureList?? && gc.screenCaptureList?size != 0>
						<ul class='screenshots right'>
							<#list gc.screenCaptureList as sc>
							<li><a data-featherlight="image" href="${ sc.path }"><i class='material-icons'>panorama</i></a></li>
							</#list>
						</ul>
						</#if>
						<#if gc.description?? && gc.description?has_content>
						${ gc.description }
						</#if>
						<#list gc.logContext.all as log>
						<#if log.exceptionInfo??>
							<pre>${log.exceptionInfo.stackTrace}</pre>
						<#else>
							${log.details}
						</#if>
						</#list>
					</li>
				</#list>
			</ul>
			</#if>
		</li>
		</#list>
	</ul>
	</#if>
</div>
</#list>