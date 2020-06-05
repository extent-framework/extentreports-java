<#macro log test>
<table class="table table-sm">
  <thead><tr><th class="status-col">Status</th><th class="timestamp-col">Timestamp</th><th class="details-col">Details</th></tr></thead>
  <tbody>
    <#list test.logs as log>
      <tr class="event-row">
        <td><span class="badge log ${log.status.toLower()}-bg">${log.status?string}</span></td>
        <td>${log.timestamp?time?string}</td>
        <td>
          <#if log.exceptions?has_content>
            <#list log.exceptions as ex><textarea readonly class="code-block">${ex.stackTrace}</textarea></#list>
          <#else>${log.details}</#if>
          <@media log.media />
        </td>
      </tr>
    </#list>
  </tbody>
</table>
</#macro>