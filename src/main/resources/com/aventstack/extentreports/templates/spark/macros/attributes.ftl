<#macro attributes test>
  <#if TestService.testHasAuthor(test)>
    <#list test.authorSet as author>
      <span class="badge badge-pill badge-default"> ${author.name}</span>
    </#list>
  </#if>
  <#if TestService.testHasCategory(test)>
    <#list test.categorySet as category>
      <span class="badge badge-pill badge-default"> ${category.name}</span>
    </#list>
  </#if>
  <#if TestService.testHasDevice(test)>
    <#list test.deviceSet as device>
      <span class="badge badge-pill badge-default"> ${device.name}</span>
    </#list>
  </#if>
</#macro>