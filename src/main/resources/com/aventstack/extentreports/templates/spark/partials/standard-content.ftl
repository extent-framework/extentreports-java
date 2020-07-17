<@media test.media />
<#if test.hasAnyLog()>
<div class="detail-body mt-4">
  <#list test.generatedLog as l>
  <div>
  	<div class="float-left"><span class="badge log ${l.status.toLower()}-bg">${l.status?string}</span></div>
  	<div class="float-right" style="width:95%;">${l.details}</div>
  </div> 
  </#list>                                            
  <#if test.hasLog()><@log test=test /></#if>
</div>
</#if>
<#if test.hasChildren()>
  <div class="mt-4"><@recurse_nodes test=test /></div>
</#if>