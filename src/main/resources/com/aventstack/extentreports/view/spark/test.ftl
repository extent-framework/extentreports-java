<#include "../commons/commons-variables.ftl">
<#include "../commons/commons-macros.ftl">
<#include "macros/attributes.ftl">
<#include "macros/log.ftl">
<#include "macros/recurse_nodes.ftl">

<#assign isbdd=false pageClass="">
<#if report.testList?? && report.testList?has_content && report.testList[0].isBehaviorDrivenType()>
    <#assign pageClass="bdd-report" isbdd=true>
</#if>

<!DOCTYPE html>
<html>
<#include "partials/head.ftl">
<script src="https://cdn.rawgit.com/extent-framework/extent-github-cdn/7cc78ce/spark/js/jsontree.js"></script>
<body class="test-view ${reportType}-report ${theme}">
    <div class="app header-dark side-nav-folded">
        <div class="layout">
            <#include "partials/navbar.ftl">
            <#include "partials/sidenav.ftl">
            <div class="page-container">
                <div class="main-content">
                    <div class="test-wrapper row">
                        <div class="test-list">
                            <div class="test-list-tools">
                                <ul class="tools pull-left">
                                    <li>
                                        <a href="">
                                            <span class="font-size-14">Tests</span>
                                        </a>
                                    </li> 
                                </ul>
                                <ul class="tools text-right">
                                    <li class="user-profile dropdown dropdown-animated scale-left">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-exclamation-circle"></i>
                                        </a>
                                        <ul id="status-toggle" class="dropdown-menu dropdown-md p-v-0">
                                            <#if report.containsStatus(Status.PASS)><a class="dropdown-item" status="pass" href="#"><span>Pass</span><span class="status success pull-right"></span></a></#if>
	                                        <#if report.containsStatus(Status.FAIL)><a class="dropdown-item" status="fail" href="#"><span>Fail</span><span class="status danger pull-right"></span></a></#if>
	                                        <#if report.containsStatus(Status.FATAL)><a class="dropdown-item" status="fatal" href="#"><span>Fatal</span><span class="status danger pull-right"></span></a></#if>
	                                        <#if report.containsStatus(Status.ERROR)><a class="dropdown-item" status="error" href="#"><span>Error</span><span class="status warning pull-right"></span></a></#if>
	                                        <#if report.containsStatus(Status.WARNING)><a class="dropdown-item" status="warning" href="#"><span>Warning</span><span class="status warning pull-right"></span></a></#if>
	                                        <#if report.containsStatus(Status.SKIP)><a class="dropdown-item" status="skip" href="#"><span>Skip</span><span class="status warning pull-right"></span></a></#if>
	                                        <div class="dropdown-divider"></div>
                                            <a status="clear" class="dropdown-item" href="#"><span>Clear</span><span class="pull-right"><i class="fa fa-close"></i></span></a>
                                        </ul>
                                    </li>
                                    <#if authorContext?? && authorContext?size != 0>
                                    <li class="user-profile dropdown dropdown-animated scale-left">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-user"></i>
                                        </a>
                                        <ul id="author-toggle" class="dropdown-menu dropdown-md p-v-0">
                                            <#list authorContext as context>
                                                <a class="dropdown-item" href="#">${context.name}</a>
                                            </#list>
                                        </ul>
                                    </li>
                                    </#if>
                                    <#if categoryContext?? && categoryContext?size != 0>
                                    <li class="user-profile dropdown dropdown-animated scale-left">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-tag"></i>
                                        </a>
                                        <ul id="tag-toggle" class="dropdown-menu dropdown-md p-v-0">
                                            <#list categoryContext as context>
                                                <a class="dropdown-item" href="#">${context.name}</a>
                                            </#list>
                                        </ul>
                                    </li>
                                    </#if>
                                    <#if deviceContext?? && deviceContext?size != 0>
                                    <li class="user-profile dropdown dropdown-animated scale-left">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-tablet"></i>
                                        </a>
                                        <ul id="device-toggle" class="dropdown-menu dropdown-md p-v-0">
                                            <#list deviceContext as context>
                                                <a class="dropdown-item" href="#">${context.name}</a>
                                            </#list>
                                        </ul>
                                    </li>
                                    </#if>
                                </ul>
                            </div>
                            <div class="test-list-wrapper scrollable">
                                <ul class="test-list-item">
                                	<#list report.testList as test>
                                    <li class="test-item"  status="${test.status}" test-id="${test.getID()}"
                                            author="<#list test.authorContext.all as auth>${auth.name}<#if auth_has_next> </#if></#list>"
                                            tag="<#list test.categoryContext.all as tag>${tag.name}<#if tag_has_next> </#if></#list>"
                                            device="<#list test.deviceContext.all as device>${device.name}<#if device_has_next> </#if></#list>">
                                        <div class="status-avatar ${test.status}-bg">
                                            <i class="fa fa-${Icon.getIcon(test.status)} text-white"></i>
                                        </div>
                                        <div class="open-test">
                                            <div class="test-detail"> 
                                                <p class="name">${test.name}</p> 
                                                <p class="duration text-sm">${test.runDuration}</p> 
                                                <span class="datetime">${test.startTime?string("HH:mm:ss a")}</span>
                                            </div>
                                        </div>
                                        <div class="test-contents d-none">
                                            <div class="detail-head">
                                                <div class="p-v-10 d-inline-block"> 
                                                    <div class="info">
                                                        <h5 class="test-status text-${test.status}">${test.name}</h5>
                                                        <span class='badge badge-success'>${test.startTime?string("MM.dd.yyyy HH:mm:ss")}</span>
                                                        <span class='badge badge-danger'>${test.endTime?string("MM.dd.yyyy HH:mm:ss")}</span>
                                                        <span class='badge badge-default'>${test.runDuration}</span>
                                                    </div>
                                                    <#if test.hasAttributes()>
                                                    <div class="m-t-15">
                                                        <@attributes test=test />
                                                    </div>
                                                    </#if>
                                                    <#if test.description??>
                                                    <div class="m-t-10 m-l-5">
                                                        ${test.description}
                                                    </div>
                                                    </#if>
                                                </div>
                                            </div>
	                                        <#if !isbdd>
	                                            <#include "partials/standard-content.ftl">
	                                        <#else>
	                                            <#include "partials/bdd-content.ftl">
	                                        </#if>
                                        </div>
                                    </li>
                                    </#list>
                                </ul>    
                            </div>
                        </div>
                        <div class="test-content scrollable">
                            <div class="test-content-tools">
                                <ul>
                                    <li>
                                        <a class="back-to-test" href="javascript:void(0)">
                                            <i class="fa fa-arrow-left"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="test-content-detail">
                                <div class="detail-body"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "partials/scripts.ftl">
</body>
</html>