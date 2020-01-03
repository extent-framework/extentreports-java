<head>
	<meta charset="${config.getConfig('encoding')}" />
	<title>${config.getConfig("documentTitle")}</title>
	<meta name="description" content="extent" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimal-ui" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-barstyle" content="black-translucent">
	<meta name="apple-mobile-web-app-title" content="extent">
	<meta name="mobile-web-app-capable" content="yes">
	<#if offline=="true">
	<link rel="stylesheet" href="${config.getConfig("offlineDirectory")}font-awesome.min.css">
	<link rel="stylesheet" href="${config.getConfig("offlineDirectory")}logger-style.css" type="text/css" />
	<#else>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@832f979664f9d68bf618db3ac87498ac6c1a6390/logger/css/logger-style.css" type="text/css" />
	</#if>
	<#if config.containsConfig("styles")>
	<#include "../../commons/commons-inject-css.ftl">
	</#if>
</head>
