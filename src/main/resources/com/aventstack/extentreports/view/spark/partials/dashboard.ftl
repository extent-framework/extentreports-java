<div class="container-fluid p-4 dashboard-view">
	<div class="row">
		<div class="${boxsize}">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">${parentHeading}</h6>
				</div>
				<div class="card-body">
					<div class="">
						<canvas id='parent-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
					</div>
				</div>
				<div class="card-footer">
					<div><small data-tooltip='${report.reportStatusStats.parentPercentagePass}%'>
						<b>${report.reportStatusStats.parentCountPass}</b> ${parentHeading?lower_case} passed
						</small>
					</div>
					<div>
						<small data-tooltip='${report.reportStatusStats.parentPercentageFail}%'><b>${report.reportStatusStats.parentCountFail + report.reportStatusStats.parentCountFatal}</b> ${parentHeading?lower_case} failed, 
						<b data-tooltip='${report.reportStatusStats.parentPercentageOthers}%'>${report.reportStatusStats.parentCountError + report.reportStatusStats.parentCountWarning + report.reportStatusStats.parentCountSkip}</b> others
						</small>
					</div>
				</div>
			</div>
		</div>
		<#if report.reportStatusStats.childCount != 0>
		<div class="${boxsize}">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">${childHeading}</h6>
				</div>
				<div class="card-body">
					<div class="">
						<canvas id='child-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
					</div>
				</div>
				<div class="card-footer">
					<div><small data-tooltip='${report.reportStatusStats.childPercentagePass}%'><b>${report.reportStatusStats.childCountPass}</b> ${childHeading?lower_case} passed</small></div>
					<div>
						<small data-tooltip='${report.reportStatusStats.childPercentageFail}%'><b>${report.reportStatusStats.childCountFail + report.reportStatusStats.childCountFatal}</b> ${childHeading?lower_case} failed, 
						<b data-tooltip='${report.reportStatusStats.childPercentageOthers}%'>${report.reportStatusStats.childCountError + report.reportStatusStats.childCountWarning + report.reportStatusStats.childCountSkip + report.reportStatusStats.childCountInfo}</b> others
						</small>
					</div>
				</div>
			</div>
		</div>
		</#if>
		<#if report.reportStatusStats.grandChildCount != 0>
		<div class="${boxsize}">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">${grandChildHeading}</h6>
				</div>
				<div class="card-body">
					<div class="">
						<canvas id='grandchild-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
					</div>
				</div>
				<div class="card-footer">
					<div><small data-tooltip='${report.reportStatusStats.grandChildPercentagePass}%'><b>${report.reportStatusStats.grandChildCountPass}</b> ${grandChildHeading?lower_case} passed</small></div>
					<div>
						<small data-tooltip='${report.reportStatusStats.grandChildPercentageFail}%'><b>${report.reportStatusStats.grandChildCountFail + report.reportStatusStats.grandChildCountFatal}</b> ${grandChildHeading?lower_case} failed, 
						<b data-tooltip='${report.reportStatusStats.grandChildPercentageOthers}%'>${report.reportStatusStats.grandChildCountError + report.reportStatusStats.grandChildCountWarning + report.reportStatusStats.grandChildCountSkip + report.reportStatusStats.grandChildCountInfo}</b> others
						</small>
					</div>
				</div>
			</div>
		</div>
		</#if>
	</div>
	<div class="row">
		<div class="col-md-3">
			<div class="card">
				<div class="card-body box-height-150">
					<div class="media justify-content-between">
						<div>
							<p class="">${parentHeading}</p>
							<h2 class="font-size-28 font-weight-light">${report.reportStatusStats.parentCount}</h2>
							<span class="text-semibold text-success font-size-15">
							<i class="ti-arrow-up font-size-11"></i> 
							<span>${report.reportStatusStats.parentPercentagePass?string("#.00")}%</span>
							</span>
						</div>
						<div class="align-self-end">
							<i class="fa fa-align-left font-size-70 opacity-01"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<#if report.reportStatusStats.childCount != 0>
		<div class="col-md-3">
			<div class="card">
				<div class="card-body box-height-150">
					<div class="media justify-content-between">
						<div>
							<p class="">${childHeading}</p>
							<h2 class="font-size-28 font-weight-light">${report.reportStatusStats.childCount}</h2>
							<span class="text-semibold text-success font-size-15">
							<i class="ti-arrow-up font-size-11"></i> 
							<span>${report.reportStatusStats.childPercentagePass?string("#.00")}%</span>
							</span>
						</div>
						<div class="align-self-end">
							<i class="fa fa-align-left font-size-70 opacity-01"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		</#if>
		<div class="col-md-3">
			<div class="card">
				<div class="card-body box-height-150">
					<div class="media justify-content-between">
						<div>
							<p class="">Start</p>
							<h6 class="font-weight-light">${report.startTime?datetime?string["${timeStampFormat}"]}</h6>
							<span class="text-semibold text-success font-size-15">
						</div>
						<div class="align-self-end">
							<i class="fa fa-clock-o font-size-70 text-success opacity-01"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="card">
				<div class="card-body box-height-150">
					<div class="media justify-content-between">
						<div>
							<p class="">Duration</p>
							<h6 class="font-weight-light">${report.longRunDuration}</h6>
							<span class="text-semibold font-size-15">
						</div>
						<div class="align-self-end">
							<i class="fa fa-clock-o font-size-70 opacity-01"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#if config.getConfig("enableTimeline")=='true'>
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">Timeline</h6>
				</div>
				<div class="card-body">
					<div class="">
						<canvas id="timeline" height="120"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
	</#if>
	<div class="row">
		<#if (authorContext?? && authorContext?size != 0)>
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">Author</h6>
				</div>
				<div class="table-overflow">
					<table class="table table-sm">
						<thead>
							<tr class="bg-gray">
								<th>Name</th>
								<th>Passed</th>
								<th>Failed</th>
								<th>Others</th>
								<th>Passed %</th>
							</tr>
						</thead>
						<tbody>
							<#list authorContext as author>
							<tr>
								<td>${author.name}</td>
								<td>${author.passed}</td>
								<td>${author.failed}</td>
								<td>${author.others}</td>
								<td>
									<#if author.size()!=0>
									${(author.passed/author.size())*100}%
									<#else>
									0%
									</#if>
								</td>
							</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</#if>
		<#if (categoryContext?? && categoryContext?size != 0)>
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">Tags</h6>
				</div>
				<div class="table-overflow">
					<table class="table table-sm">
						<thead>
							<tr class="bg-gray">
								<th>Name</th>
								<th>Passed</th>
								<th>Failed</th>
								<th>Others</th>
								<th>Passed %</th>
							</tr>
						</thead>
						<tbody>
							<#list categoryContext as category>
							<tr>
								<td>${category.name}</td>
								<td>${category.passed}</td>
								<td>${category.failed}</td>
								<td>${category.others}</td>
								<td>
									<#if category.size()!=0>
									${(category.passed/category.size())*100}%
									<#else>
									0%
									</#if>
								</td>
							</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</#if>
		<#if (deviceContext?? && deviceContext?size != 0)>
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">Device</h6>
				</div>
				<div class="table-overflow">
					<table class="table table-sm">
						<thead>
							<tr class="bg-gray">
								<th>Name</th>
								<th>Passed</th>
								<th>Failed</th>
								<th>Others</th>
								<th>Passed %</th>
							</tr>
						</thead>
						<tbody>
							<#list deviceContext as device>
							<tr>
								<td>${device.name}</td>
								<td>${device.passed}</td>
								<td>${device.failed}</td>
								<td>${device.others}</td>
								<td>
									<#if device.size()!=0>
									${(device.passed/device.size())*100}%
									<#else>
									0%
									</#if>
								</td>
							</tr>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</#if>
		<#if systemAttributeContext?size != 0>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">
					<h6 class="card-title">Environment</h6>
				</div>
				<div class="table-overflow">
					<table class="table table-sm">
						<thead>
							<tr class="bg-gray">
								<th>Name</th>
								<th>Value</th>
							</tr>
						</thead>
						<tbody>
							<#list report.systemAttributeContext.systemAttributeList as sa>
							<#if sa?? && sa.name?? && sa.value??>
							<tr>
								<td>${ sa.name }</td>
								<td>${ sa.value }</td>
							</tr>
							</#if>
							</#list>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</#if>
	</div>
</div>
<script>
	var statusGroup = {
	    parentCount: ${ report.reportStatusStats.parentCount?c },
		passParent: ${ report.reportStatusStats.parentCountPass?c },
		failParent: ${ report.reportStatusStats.parentCountFail?c },
		fatalParent: ${ report.reportStatusStats.parentCountFatal?c },
		errorParent: ${ report.reportStatusStats.parentCountError?c },
		warningParent: ${ report.reportStatusStats.parentCountWarning?c },
		skipParent: ${ report.reportStatusStats.parentCountSkip?c },
		exceptionsParent: ${ report.reportStatusStats.parentCountExceptions?c },
		childCount: ${ report.reportStatusStats.childCount?c },
		passChild: ${ report.reportStatusStats.childCountPass?c },
		failChild: ${ report.reportStatusStats.childCountFail?c },
		fatalChild: ${ report.reportStatusStats.childCountFatal?c },
		errorChild: ${ report.reportStatusStats.childCountError?c },
		warningChild: ${ report.reportStatusStats.childCountWarning?c },
		skipChild: ${ report.reportStatusStats.childCountSkip?c },
		infoChild: ${ report.reportStatusStats.childCountInfo?c },
		debugChild: ${ report.reportStatusStats.childCountDebug?c },
		exceptionsChild: ${ report.reportStatusStats.childCountExceptions?c },
		grandChildCount: ${ report.reportStatusStats.grandChildCount?c },
		passGrandChild: ${ report.reportStatusStats.grandChildCountPass?c },
		failGrandChild: ${ report.reportStatusStats.grandChildCountFail?c },
		fatalGrandChild: ${ report.reportStatusStats.grandChildCountFatal?c },
		errorGrandChild: ${ report.reportStatusStats.grandChildCountError?c },
		warningGrandChild: ${ report.reportStatusStats.grandChildCountWarning?c },
		skipGrandChild: ${ report.reportStatusStats.grandChildCountSkip?c },
		infoGrandChild: ${ report.reportStatusStats.grandChildCountInfo?c },
		debugGrandChild: ${ report.reportStatusStats.grandChildCountDebug?c },
		exceptionsGrandChild: ${ report.reportStatusStats.grandChildCountExceptions?c },
	};
</script>
<#if config.getConfig("enableTimeline")=='true'>
<script>
	<#macro listTestNameDuration testList>
	   <#if report.testList??>
	        <#list report.testList as t>"${t.name}":${(TestService.getRunDurationMillis(t)/1000)?c?replace(",","")}<#if t_has_next>,</#if></#list>
	   </#if>
	</#macro>
	var timeline = {
	    <@listTestNameDuration testList=report.testList />
	};
</script>
</#if>