<#if offline=="true">
    <script src="${config.getConfig("offlineDirectory")}spark-script.js"></script>
<#else>
    <script src="https://${cdnURI}${jscommit}/spark/js/spark-script.js"></script>
</#if>
<#include "../../commons/commons-inject-js.ftl">
