<#include "log.ftl">
<#macro recurse_nodes test>
<#if test.hasChildren()>
  <div class="accordion" role="tablist">
    <#list test.children as node>
      <div class="card">
        <div class="card-header">
          <div class="card-title">
            <div class="node-meta">
              <#if TestService.testHasScreenCapture(node, true)><span class='badge badge-default mr-1'><i class="fa fa-paperclip"></i></span></#if>
              <span class='badge badge-default mr-1'>${node.timeTaken()?number_to_time?string("mm:ss:SSS")}</span>
              <span class="badge ${node.status.toLower()}-bg log p-1">${node.status}</span>
            </div>
            <a class="node <#if !node.hasChildren() && node.status.toLower()!='fail'>collapsed</#if>" id="${node.getId()}">
              <span>${node.name}</span> 
            </a>
            <div class="node-attr">
                <#if node.hasAttributes()><@attributes test=node /></#if>
            </div>
          </div>
        </div>
        <#if node.hasLog()>
        <div class="<#if node.status.toLower()!='fail'>collapse</#if>">
          <div class="card-body">
            <@log test=node />
            <@media node.media />
          </div>
        </div>
        </#if>
        <#if node.hasChildren()>
          <@recurse_nodes test=node />
        </#if>
      </div>
    </#list>
  </div>
</#if>
</#macro>