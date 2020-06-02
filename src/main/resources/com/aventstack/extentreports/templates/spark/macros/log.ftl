<#macro log test>
<table class="table table-sm">
  <thead><tr>
    <th class="status-col">Status</th>
    <th class="timestamp-col">Timestamp</th>
    <th class="details-col">Details</th>
  </tr></thead>
  <tbody>
    <#list test.logs as log>
      <tr class="event-row">
        <td><i class="fa fa-${Ico.ico(log.status)} text-${log.status.toLower()}"></i></td>
        <td>${log.timestamp?time?string}</td>
        <td>
          <#if log.exceptions?has_content>
            <textarea readonly class="code-block">${log.exceptions.get(0).stackTrace}</textarea>
          <#else>
            ${log.details}
          </#if>
          <#if LogService.logHasMedia(log)>
            ${log.media.get(0).path}
          </#if>
        </td>
      </tr>
    </#list>
  </tbody>
</table>
</#macro>