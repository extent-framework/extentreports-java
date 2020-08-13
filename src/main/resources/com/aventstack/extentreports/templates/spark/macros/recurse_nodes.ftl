<#include "log.ftl">
<#macro recurse_nodes test>
<#if test.hasChildren()>
  <div class="accordion">
    <#list test.children as node>
      <div class="card">
        <div class="card-header">
          <div class="card-title">
            <div class="node" id="${node.getId()}">${node.name}</div>
            <div class="node-status float-right"><span class="badge ${test.status.toLower()}-bg log ">${node.status}</span></div>
            <div class="node-time">
              <span class='badge badge-default'>${node.timeTaken()?number_to_time?string("mm:ss:SSS")}</span>
            </div>
            <div class="node-attr">
                <#if node.hasAttributes()><div></div><@attributes test=node /></#if>
            </div>
            <#if TestService.testHasScreenCapture(node, true)>
              <div class="status-avatar float-right"><i class="fa fa-paperclip"></i></div>
            </#if>
          </div>
        </div>
        <#if node.hasLog()>
        <div class="<#if node.status.toLower()=='pass'>collapse</#if>">
          <div class="card-body">
            <@log test=node />
            <@media node.media />
          </div>
        </div>
        </#if>
        <#if test.hasChildren()>
          <@recurse_nodes test=node />
        </#if>
      </div>
    </#list>
  </div>
</#if>
</#macro>