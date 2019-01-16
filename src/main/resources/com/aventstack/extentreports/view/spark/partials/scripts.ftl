<script src="https://${cdnURI}${jscommit}/spark/js/script.js"></script>
<#if config.containsKey("scripts") && config.getValue("scripts")?has_content>
<#include "../commons/commons-inject-js.ftl">
</#if>
