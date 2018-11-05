<head>
	<meta charset="${config.getValue('encoding')}" />
	<title>${config.getValue("documentTitle")}</title>
	<meta name="description" content="extent" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimal-ui" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-barstyle" content="black-translucent">
	<meta name="apple-mobile-web-app-title" content="extent">
	<meta name="mobile-web-app-capable" content="yes">
	<#if offline=="true">
	<link rel="stylesheet" href="${config.getValue("offlineDirectory")}font-awesome.min.css">
	<link rel="stylesheet" href="${config.getValue("offlineDirectory")}logger-style.css" type="text/css" />
	<#else>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/logger/css/logger-style.css" type="text/css" />
	</#if>
	<#if config.containsKey("styles")>
	<#include "../../commons/commons-inject-css.ftl">
	</#if>
</head>
