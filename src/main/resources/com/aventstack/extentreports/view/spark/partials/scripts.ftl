<#if offline=="true">
        <script src="${config.getConfig("offlineDirectory")}spark-script.js"></script>
    <#else>
        <script src="https://${cdnURI}${jscommit}/spark/js/script.js"></script>
    </#if>
<#if config.containsConfig("js") && config.getConfig("js")?has_content>
<#include "../../commons/commons-inject-js.ftl">
</#if>
