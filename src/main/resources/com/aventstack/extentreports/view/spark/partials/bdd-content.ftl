<#macro stepdetails test>
	<#if TestService.testHasLog(test)>
		<#list test.logContext.all as log>
			<#if log.exceptionInfo??>
				<textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
			<#else>
				<div class="details">${log.details}</div>
			</#if>
			<#if LogService.logHasScreenCapture(log)>
				<div class="details">${log.screenCaptureContext.last.source}</div>
			</#if>
		</#list>
	</#if>
</#macro>

<#if TestService.testHasChildren(test)>
<div class="accordion mt-4">
	<#list test.nodeContext.all as node>
	<div class="card">
		<div class="card-header" role="tab">
			<div class="card-title pl-3">
				<div class="node">${node.name}</div>
				<div class="status-avatar float-left ${node.status}-bg">
					<i class="fa fa-${Icon.getIcon(node.status)} text-white"></i>
				</div>
				<#if TestService.testHasScreenCapture(node, true)>
					<div class="status-avatar float-right mr-4">
						<i class="fa fa-paperclip"></i>
					</div>
				</#if>
			</div>
		</div>
		<#if TestService.testHasChildren(node)>
			<#if node.bddType?? && node.behaviorDrivenTypeName=="Scenario Outline">
				<div class="collapse scenario_outline">
					<#list node.nodeContext.all as child>
						<div class="card-body l1">
							<div class="card-header" role="tab">
								<div class="card-title outline-child">
									<div class="node">${child.name}</div>
									<div class="status-avatar float-left ${child.status}-bg">
										<i class="fa fa-${Icon.getIcon(child.status)} text-white"></i>
									</div>
									<#if TestService.testHasScreenCapture(child, true)>
										<div class="status-avatar float-right">
											<i class="fa fa-paperclip"></i>
										</div>
									</#if>
								</div>
							</div>
							<div class="card-body collapse mt-3">
								<#list child.nodeContext.all as step>
									<div class="d-flex align-items-center justify-content-start ${step.behaviorDrivenTypeName?replace(' ','')?replace('*','asterisk')?lower_case}" title="${step.description}">
										<span class="alert-icon ${step.status}-bg">
											<i class="fa fa-${Icon.getIcon(step.status)} text-white"></i>
										</span>
										<span>${step.name}</span>
									</div>
									<@stepdetails test=step />
								</#list>
							</div>
						</div>
					</#list>
				</div>
			<#else>
				<div class="collapse">
					<div class="card-body">
						<#list node.nodeContext.all as child>
							<div class="d-flex align-items-center justify-content-start <#if child.bddType??>${child.behaviorDrivenTypeName?replace(' ','')?replace('*','asterisk')?lower_case}</#if>" title="${child.description}">
								<span class="alert-icon ${child.status}-bg">
									<i class="fa fa-${Icon.getIcon(child.status)} text-white"></i>
								</span>
								<span>${child.name}</span>
							</div>
							<@stepdetails test=child />
						</#list>
					</div>
				</div>
			</#if>
		</#if>
	</div>
	</#list>
</div>
</#if>