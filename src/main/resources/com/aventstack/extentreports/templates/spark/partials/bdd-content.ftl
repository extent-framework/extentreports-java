<#macro stepdetails test>
  <#if TestService.testHasLog(test)>
    <#list test.logs as log>
      <#if log.exceptions?has_content>
        <#list log.exceptions as ex><textarea disabled class="code-block">${ex.stackTrace}</textarea></#list>
      <#else>${log.details}</#if>
      <@media log.media />
    </#list>
  </#if>
</#macro>

<#if TestService.testHasChildren(test)>
<div class="accordion mt-4">
  <#list test.children as node>
  <div class="card">
    <div class="card-header" role="tab">
      <div class="card-title">
        <div class="node"><span class="badge log ${node.status.toLower()}-bg mr-2">${node.status?string}</span>${node.name}</div>
        <#if TestService.testHasScreenCapture(node, true)>
          <div class="status-avatar float-right mr-4">
            <i class="fa fa-paperclip"></i>
          </div>
        </#if>
      </div>
    </div>
    <#if TestService.testHasChildren(node)>
      <#if node.bddType?? && node.bddType.simpleName=="ScenarioOutline">
        <div class="scenario_outline <#if node.status.toLower()=='pass'>collapse</#if>">
          <#list node.children as child>
            <div class="card-body l1">
              <div class="card-header">
                <div class="card-title outline-child">
                  <div class="node"><span class="badge log ${child.status.toLower()}-bg mr-2">${child.status?string}</span>${child.name}</div>
                  <#if TestService.testHasScreenCapture(child, true)>
                    <div class="status-avatar float-right">
                      <i class="fa fa-paperclip"></i>
                    </div>
                  </#if>
                </div>
              </div>
              <div class="card-body mt-3 <#if child.status.toLower()=='pass'>collapse</#if>">
                <#list child.children as step>
                  <div class="step ${step.status.toLower()}-bg">
                    <span>${step.name}</span>
                    <@stepdetails test=step />
                  </div>
                </#list>
              </div>
            </div>
          </#list>
        </div>
      <#else>
        <div class="collapse">
          <div class="card-body">
            <#list node.children as child>
              <div class="step ${child.status.toLower()}-bg" <#if child.description??>title="${child.description}"</#if>>
                <span>${child.name}</span>
              </div>
              <@stepdetails test=child />
            </#list>
          </div>
        </div>
      </#if>
    </#if>
  </div>
  </#list>
</div>
</#if>