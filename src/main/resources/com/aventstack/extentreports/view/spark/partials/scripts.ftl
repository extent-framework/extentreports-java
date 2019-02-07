<#if offline=="true">
        <script src="${config.getValue("offlineDirectory")}spark-script.js"></script>
    <#else>
        <script src="https://${cdnURI}${jscommit}/spark/js/script.js"></script>
    </#if>
<#if config.containsKey("scripts") && config.getValue("scripts")?has_content>
<#include "../../commons/commons-inject-js.ftl">
</#if>
