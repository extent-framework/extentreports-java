<#macro log test>
<table class="table table-sm">
	<thead><tr>
		<th class="status-col">Status</th>
		<th class="timestamp-col">Timestamp</th>
		<th class="details-col">Details</th>
	</tr></thead>
	<tbody>
		<#list test.logContext.all as log>
			<tr class="event-row">
				<td><i class="fa fa-${Icon.getIcon(log.status)} text-${log.status}"></i></td>
				<td>${log.timestamp?time?string}</td>
				<td>
					<#if log.exceptionInfo??>
						<textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
					<#else>
						${log.details}
					</#if>
					<#if log.hasScreenCapture()>
						${log.screenCaptureContext.last.source}
					</#if>
				</td>
			</tr>
		</#list>
	</tbody>
</table>
</#macro>