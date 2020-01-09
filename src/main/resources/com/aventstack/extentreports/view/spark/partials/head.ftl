<#assign resourceCDN=config.getConfig('resourceCDN') cdnURI="cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@" csscommit="b00a2d0486596e73dd7326beacf352c639623a0e" jscommit="b00a2d0486596e73dd7326beacf352c639623a0e" iconcommit="b00a2d0486596e73dd7326beacf352c639623a0e">
<#if resourceCDN=="extentreports">
    <#assign cdnURI="extentreports.com/resx" csscommit="" jscommit="" iconcommit="">
</#if>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${config.getConfig("documentTitle")}</title>
    <#if offline=="true">
        <link rel="apple-touch-icon" href="${config.getConfig("offlineDirectory")}logo.png">
        <link rel="shortcut icon" href="${config.getConfig("offlineDirectory")}logo.png">
	    <link rel="stylesheet" href="${config.getConfig("offlineDirectory")}spark-style.css">
	    <link rel="stylesheet" href="${config.getConfig("offlineDirectory")}font-awesome.min.css">
	<#else>
        <link rel="apple-touch-icon" href="https://${cdnURI}${iconcommit}/commons/img/logo.png">
        <link rel="shortcut icon" href="https://${cdnURI}${iconcommit}/commons/img/logo.png">
	    <link href="https://${cdnURI}${csscommit}/spark/css/spark-style.css" rel="stylesheet" />
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	</#if>
    <#include "../../commons/commons-inject-css.ftl">
</head>