<@media test.media />
<#if TestService.testHasAnyLog(test)>
<div class="detail-body mt-4">
  <#list test.generatedLog as l>${l.details}</#list>                                            
  <#if TestService.testHasLog(test)><@log test=test /></#if>
</div>
</#if>
<#if TestService.testHasChildren(test)>
  <div class="mt-4"><@recurse_nodes test=test /></div>
</#if>