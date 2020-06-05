<#include "../commons/commons-variables.ftl">
<#include "../commons/commons-macros.ftl">
<#include "macros/attributes.ftl">
<#include "macros/log.ftl">
<#include "macros/recurse_nodes.ftl">

<#assign 
  isbdd=false 
  pageClass="">
<#if ReportService.isBDD(report)>
  <#assign
    pageClass="bdd" 
    isbdd=true>
</#if>

<!DOCTYPE html>
<html>
<#include "partials/head.ftl">
<body class="spa ${reportType}-report ${theme}">
  <div class="app">
    <div class="layout">
      <#include "partials/navbar.ftl">
      <#include "partials/sidenav.ftl">
      <div class="vcontainer">
        <div class="main-content">
          <#include "partials/test.ftl">
          <#if report.categoryCtx.set?size != 0><#include "partials/tag.ftl"></#if>
          <#if report.exceptionInfoCtx.set?size != 0><#include "partials/exception.ftl"></#if>
          <#include "partials/dashboard.ftl">
        </div>
      </div>
    </div>
  </div>
  <#include "partials/scripts.ftl">
</body>
</html>