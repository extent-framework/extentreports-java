<#include "../commons/commons-variables.ftl">
<#include "../commons/commons-macros.ftl">
<#include "macros/attributes.ftl">
<#include "macros/log.ftl">
<#include "macros/recurse_nodes.ftl">

<#assign isbdd=false pageClass="">
<#if report.testList?? && report.testList?has_content && report.testList[0].isBehaviorDrivenType()>
    <#assign pageClass="bdd-report" isbdd=true>
</#if>

<!DOCTYPE html>
<html>
<#include "partials/head.ftl">
<#if offline=="true">
    <link rel="stylesheet" href="${config.getConfig("offlineDirectory")}jsontree.js">
<#else>
    <script src="https://cdn.rawgit.com/extent-framework/extent-github-cdn/7cc78ce/spark/js/jsontree.js"></script>
</#if>
<body class="spa ${reportType}-report ${theme}">
    <div class="app header-dark side-nav-folded">
        <div class="layout">
            <#include "partials/navbar.ftl">
            <#include "partials/sidenav.ftl">
            <div class="page-container">
                <div class="main-content">
                    <#include "partials/test.ftl">
                    <#if categoryContext?? && categoryContext?size != 0>
                    	<#include "partials/tag.ftl">
                    </#if>
                    <#if exceptionContext?? && exceptionContext?size != 0>
                    	<#include "partials/exception.ftl">
                    </#if>
                    <!-- dashboard -->
                    <#include "partials/dashboard.ftl">
                </div>
            </div>
        </div>
    </div>
	<#include "partials/scripts.ftl">
</body>
</html>