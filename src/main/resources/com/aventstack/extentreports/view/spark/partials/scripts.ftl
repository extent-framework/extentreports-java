<script src="https://cdn.rawgit.com/extent-framework/extent-github-cdn/543bfe2/spark/js/script.js"></script>
<#if config.containsKey("scripts") && config.getValue("scripts")?has_content>
<#include "../commons/commons-inject-js.ftl">
</#if>
