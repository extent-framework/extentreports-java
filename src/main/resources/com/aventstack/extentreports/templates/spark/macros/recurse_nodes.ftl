<#include "log.ftl">

<#macro recurse_nodes test>
<#if TestService.testHasChildren(test)>
  <div class="accordion">
    <#list test.children as node>
      <div class="card">
        <div class="card-header" role="tab">
          <div class="card-title">
            <div class="node">${node.name}</div>
            <div class="status-avatar float-right ${node.status.toLower()}-bg">
               <i class="fa fa-${Ico.ico(node.status)} text-white"></i>
            </div>
            <#if TestService.testHasScreenCapture(node, true)>
              <div class="status-avatar float-right">
                <i class="fa fa-paperclip"></i>
              </div>
            </#if>
          </div>
        </div>
        <div class="collapse">
          <div class="card-body">
            <#if TestService.testHasLog(node)>
              <@log test=node />
            </#if>
            <#list node.media as m>
              ${m.source}
            </#list>
          </div>
        </div>
        <#if TestService.testHasChildren(node)>
          <@recurse_nodes test=node />
        </#if>
      </div>
    </#list>
  </div>
</#if>
</#macro>