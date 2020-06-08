<#assign 
  resourceCDN=config.resourceCDN
  cdnURI="cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@" 
  csscommit="b0a37389d0406369e217ab38b01fcdfe7e3af4bc" 
  jscommit="cc1fffcbb52bfeb6bfa6b40d7a84a96e284b537c" 
  iconcommit="b00a2d0486596e73dd7326beacf352c639623a0e">
<#if resourceCDN=="extentreports">
  <#assign 
    cdnURI="extentreports.com/resx" 
    csscommit="" 
    jscommit="" 
    iconcommit="">
</#if>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>${config.documentTitle}</title>
  <#if offline>
    <link rel="apple-touch-icon" href="spark/logo.png">
    <link rel="shortcut icon" href="spark/logo.png">
    <link rel="stylesheet" href="spark/spark-style.css">
    <link rel="stylesheet" href="spark/font-awesome.min.css">
    <script src="spark/jsontree.js"></script>
  <#else>
    <link rel="apple-touch-icon" href="https://${cdnURI}${iconcommit}/commons/img/logo.png">
    <link rel="shortcut icon" href="https://${cdnURI}${iconcommit}/commons/img/logo.png">
    <link href="https://${cdnURI}${csscommit}/spark/css/spark-style.css" rel="stylesheet" />
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.rawgit.com/extent-framework/extent-github-cdn/7cc78ce/spark/js/jsontree.js"></script>
  </#if>
  <#include "../../commons/commons-inject-css.ftl">
</head>