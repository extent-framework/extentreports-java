<#assign resourceCDN=config.getValue('resourceCDN') cdnURI="cdn.rawgit.com/extent-framework/extent-github-cdn/" csscommit="cd00a5e" jscommit="cd00a5e" iconcommit="d74480e">
<#if resourceCDN=="extentreports">
    <#assign cdnURI="extentreports.com/resx" csscommit="" jscommit="" iconcommit="">
</#if>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${config.getValue("documentTitle")}</title>
    <link rel="apple-touch-icon" href="https://${cdnURI}${iconcommit}/commons/img/logo.png">
    <link rel="shortcut icon" href="https://${cdnURI}${iconcommit}/commons/img/logo.png">
    <link href="https://${cdnURI}${csscommit}/spark/css/style.css" rel="stylesheet" />
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style><#include "../../commons/commons-inject-css.ftl"></style>
</head>