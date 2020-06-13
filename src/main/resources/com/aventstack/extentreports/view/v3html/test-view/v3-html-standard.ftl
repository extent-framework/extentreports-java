<div class='test-time-info'>
	<span class='label start-time'>${ test.startTime?datetime?string["${timeStampFormat}"] }</span>
	<span class='label end-time'>${ test.endTime?datetime?string["${timeStampFormat}"] }</span>
	<span class='label time-taken grey lighten-1 white-text'>${ TestService.getRunDuration(test)?string }</span>
</div>
<#if test.description?? && test.description?has_content>
<div class='test-desc'>${ test.description} </div>
</#if>
<#if TestService.testHasAttributes(test)>
<div class='test-attributes'>
	<#if TestService.testHasCategory(test)>
	<div class='category-list'>
		<#list test.categoryContext.all as category>
		<span class='category label'>${ category.name }</span>
		</#list>
	</div>
	</#if>
	<#if TestService.testHasAuthor(test)>
	<div class='author-list'>
		<#list test.authorContext.all as author>
		<span class='author label'>${ author.name }</span>
		</#list>
	</div>
	</#if>
	<#if TestService.testHasDevice(test)>
    <div class='device-list'>
        <#list test.deviceContext.all as device>
        <span class='device label'>${ device.name }</span>
        </#list>
    </div>
    </#if>
</div>
</#if>
<#if TestService.testHasLog(test)>
<div class='test-steps'>
	<table class='bordered table-results'>
		<thead>
			<tr>
				<th>Status</th>
				<th>Timestamp</th>
				<#if test.getLogContext().get(0).stepName??>
				<th>StepName</th>
				</#if>
				<th>Details</th>
			</tr>
		</thead>
		<tbody>
			<#list test.getLogContext().getAll() as log>
			<tr class='log' status='${ log.status }'>
				<td class='status ${ log.status }' title='${ log.status }' alt='${ log.status }'><i class='material-icons'>${ MaterialIcon.getIcon(log.status) }</i></td>
				<td class='timestamp'>${ log.timestamp?time?string }</td>
				<#if log.stepName??>
				<td class='step-name'>${ log.stepName }</td>
				</#if>
				<td class='step-details'>
					<#if log.exceptionInfo??>
						<textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
					<#else>
						${log.details}
					</#if>
					<#if LogService.logHasScreenCapture(log)>${log.screenCaptureContext.last.source}</#if>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>
</#if>
<#if TestService.testHasChildren(test)>
<ul class='collapsible node-list' data-collapsible='accordion'>
	<#macro recurse_nodes nodeList>
	<#list nodeList as node>
	<#assign leaf=(TestService.testHasChildren(node))?then('','leaf')>
	<li class='node level-${ node.level } ${ leaf } ${ node.status }' status='${ node.status }' test-id='${ node.getId() }'>
		<div class='collapsible-header'>
			<div class='node-name'>${ node.name }</div>
			<span class='node-time'>${ node.startTime?datetime?string["${timeStampFormat}"] }</span>
			&middot; <span class='node-duration'>${ TestService.getRunDuration(node) }</span>
			<span class='test-status right ${ node.status }'>${ node.status }</span>
			<#if TestService.testHasCategory(node)>
			<div class='category-list'>
				<#list node.categoryContext.all as category>
				<span class='category label'>${ category.name }</span>
				</#list>
			</div>
			</#if>						
		</div>
		<#assign displayContent=true>
		<#if node.getStatus()=='pass' && disableToggleActionForPassedNode=='true'>
		<#assign displayContent=false>
		</#if>
		<#if TestService.testHasLog(node) && displayContent>
		<div class='collapsible-body'>
			<#if TestService.testHasLog(node)>
			<#if node.description?? && node.description?has_content>
			<div class='node-desc'>${ node.description}</div>
			</#if>
			<#if TestService.testHasAuthor(node)>
			<div class='author-list right'>
				<#list node.authorContext.all as author>
				<span class='author label white-text'>${ author.name }</span>
				</#list>
			</div>
			</#if>
			<div class='node-steps'>
				<table class='bordered table-results'>
					<thead>
						<tr>
							<th>Status</th>
							<th>Timestamp</th>
							<#if node.getLogContext().get(0).stepName??>
							<th>StepName</th>
							</#if>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<#list node.getLogContext().getAll() as log>
						<tr class='log' status='${ log.status }'>
							<td class='status ${ log.status }' title='${ log.status }' alt='${ log.status }'><i class='material-icons'>${ MaterialIcon.getIcon(log.status) }</i></td>
							<td class='timestamp'>${ log.timestamp?time?string }</td>
							<#if log.stepName??>
							<td class='step-name'>${ log.stepName }</td>
							</#if>
							<td class='step-details'>
								<#if log.exceptionInfo??>
									<textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
								<#else>
									${log.details}
								</#if>
								<#if LogService.logHasScreenCapture(log)>${log.screenCaptureContext.last.source}</#if>
							</td>
						</tr>
						</#list>
					</tbody>
				</table>
				<#if TestService.testHasScreenCapture(node)>
				<ul class='screenshots'>
					<#list node.screenCaptureContext.all as sc>
					<li>${ sc.source }</li>
					</#list>
				</ul>
				</#if>
			</div>
			</#if>
		</div>
		</#if>
		<#if TestService.testHasChildren(node)>
		<ul class='collapsible node-list' data-collapsible='accordion'>
			<@recurse_nodes nodeList=node.nodeContext.all />
		</ul>
		</#if>
	</li>
	</#list>
	</#macro>
	<@recurse_nodes nodeList=test.nodeContext.all />
</ul>
</#if>