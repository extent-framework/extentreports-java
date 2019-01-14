<#include "log.ftl">

<#macro recurse_nodes test>
<#if test.hasChildren()>
	<div class="accordion">
		<#list test.nodeContext.all as node>
			<div class="card">
				<div class="card-header" role="tab">
					<h5 class="card-title">
						<div class="node">${node.name}</div> 
						<div class="status-avatar float-right ${node.status}-bg">
						    <i class="fa fa-${Icon.getIcon(node.status)} text-white"></i>
						</div>
					</h5>
				</div>
				<div class="collapse">
					<div class="card-body">
						<#if node.hasLog()>
							<@log test=node />
						</#if>
						<#if node.hasScreenCapture()>
							${node.screenCaptureList[0].source}
						</#if>
					</div>
				</div>
				<#if node.hasChildren()>
					<@recurse_nodes test=node />
				</#if>
			</div>
		</#list>
	</div>
</#if>
</#macro>