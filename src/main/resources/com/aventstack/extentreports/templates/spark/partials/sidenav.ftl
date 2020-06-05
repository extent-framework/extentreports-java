<div class="side-nav">
  <div class="side-nav-inner">
    <ul class="side-nav-menu">
      <#list viewOrder as order>
    	<#if order.toString()!="author" && order.toString()!="device" && order.toString()!="logs">
    	  <#if 
    	  	order.toString()=="exception" && report.exceptionInfoCtx.hasItems()
    	  	|| order.toString()=="category" && report.categoryCtx.hasItems()
    	  	|| order.toString()=="test" || order.toString()=="dashboard">
    	  <#assign ico="align-left">
    	  <#if order.toString()=="category"><#assign ico="tag">
    	  <#elseif order.toString()=="exception"><#assign ico="bug">
    	  <#elseif order.toString()=="dashboard"><#assign ico="bar-chart">
    	  </#if>
    	  <li class="nav-item dropdown" onclick="toggleView('${order.toString()}-view')">
            <a id="nav-${order.toString()}" class="dropdown-toggle" href="#">
              <span class="ico"><i class="fa fa-${ico}"></i></span>
            </a>
          </li>
          </#if>
        </#if>
      </#list>
    </ul>
  </div>
</div>