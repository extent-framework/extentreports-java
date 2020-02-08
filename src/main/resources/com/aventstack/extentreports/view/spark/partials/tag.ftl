<div class="test-wrapper row tag-view attributes-view">
	<div class="test-list">
		<div class="test-list-tools">
			<ul class="tools pull-left">
				<li>
					<a href="">
					<span class="font-size-14">Tags</span>
					</a>
				</li>
			</ul>
			<ul class="tools text-right">
				<li>
					<a href="#">
					<span class="badge badge-primary">${categoryContext?size}</span>
					</a>
				</li>
			</ul>
		</div>
		<div class="test-list-wrapper scrollable">
			<ul class="test-list-item">
				<#list categoryContext as context>
				<li class="test-item">
					<div class="open-test">
						<div class="test-detail">
							<p class="name">${context.name}</p>
							<p class="duration text-sm">${context.size()} tests</p>
							<span class="datetime">
							<#if context.passed!=0><span class='badge badge-success'>${context.passed}</span></#if>
							<#if context.failed!=0><span class='badge badge-danger'>${context.failed}</span></#if>
							<#if context.skipped!=0><span class='badge badge-skip'>${context.skipped}</span></#if>
							<#if context.others!=0><span class='badge badge-warning'>${context.others}</span></#if>
							</span>
						</div>
					</div>
					<div class="test-contents d-none">
						<div class="info">
							<h4>${context.name}</h4>
							<#if context.passed!=0><span status="pass" class='badge badge-success'>${context.passed} passed</span></#if>
							<#if context.failed!=0><span status="fail" class='badge badge-danger'>${context.failed} failed</span></#if>
							<#if context.skipped!=0><span status="skip" class='badge badge-skip'>${context.skipped} skipped</span></#if>
							<#if context.others!=0><span status="skip" class='badge badge-warning'>${context.others} others</span></#if>
						</div>
						<table class='table table-sm mt-4'>
							<thead>
								<tr>
									<th class="status-col">Status</th>
									<th class="timestamp-col">Timestamp</th>
									<th>TestName</th>
								</tr>
							</thead>
							<tbody>
								<#list context.tests as test>
								<tr class="tag-test-status" status="${test.status}">
									<td>
										<div class='status-avatar ${test.status}-bg'>
											<i class="fa fa-${Icon.getIcon(test.status)} text-white"></i>
										</div>
									</td>
									<td>${test.startTime?string[("HH:mm:ss a")]}</td>
									<td class='linked' test-id='${test.getId()}'>
										${test.name}
										<#if test.parent??>
										<div class="">
											<span class="badge badge-default">${TestService.getHierarchicalName(test)}</span>
										</div>
										</#if>
									</td>
								</tr>
								</#list>
							</tbody>
						</table>
					</div>
				</li>
				</#list>
			</ul>
		</div>
	</div>
	<div class="test-content scrollable">
		<div class="test-content-tools">
			<ul>
				<li>
					<a class="back-to-test" href="javascript:void(0)">
					<i class="fa fa-arrow-left"></i>
					</a>
				</li>
			</ul>
		</div>
		<div class="test-content-detail">
			<div class="detail-body"></div>
		</div>
	</div>
</div>