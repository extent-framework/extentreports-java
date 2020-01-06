<#include "log.ftl">

<#macro recurse_nodes test>
<#if TestService.testHasChildren(test)>
	<div class="accordion">
		<#list test.nodeContext.all as node>
			<div class="card">
				<div class="card-header" role="tab">
					<div class="card-title">
						<div class="node">${node.name}</div> 
						<div class="status-avatar float-right ${node.status}-bg">
						    <i class="fa fa-${Icon.getIcon(node.status)} text-white"></i>
						</div>
					</div>
				</div>
				<div class="collapse">
					<div class="card-body">
						<#if TestService.testHasLog(node)>
							<@log test=node />
						</#if>
						<#list node.screenCaptureContext.all as sc>
							${sc.source}
						</#list>
					</div>
				</div>
				<#if TestService.testHasChildren(node)>
					<@recurse_nodes test=node />
				</#if>
			</div>
		</#list>
	</div>
</#if>
</#macro>