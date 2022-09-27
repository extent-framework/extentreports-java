<#include "log.ftl">
<#macro recurse_nodes test>
<#if test.hasChildren()>
  <div class="accordion" role="tablist">
    <#list test.children as node>
      <div class="card">
        <div class="card-header">
          <div class="card-toolbar node-info">
            <ul class="list-inline d-none d-sm-block">
              <li><span class="badge ${node.status.toLower()}-bg log ">${node.status}</span></li>
              <li><span class='badge badge-default'>${node.timeTaken()?number_to_time?string("mm:ss:SSS")}</span></li>
              <li><#if TestService.testHasScreenCapture(node, true)><span><i class="fa fa-paperclip"></i></span></#if></li>
            </ul>
          </div>
          <div class="card-title">
            <a class="node <#if !node.hasChildren() && node.status=='FAIL'>collapsed</#if>" id="${node.getId()}"><span>${node.name}</span></a>
            <div class="node-attr">
                <#if node.hasAttributes()><@attributes test=node /></#if>
            </div>
          </div>
        </div>
        <#if node.hasLog()>
        <div class="<#if node.status.toLower()!='fail'>collapse</#if>">
          <div class="card-body">
            <#if node.description?has_content><p>${node.description}</p></#if>
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