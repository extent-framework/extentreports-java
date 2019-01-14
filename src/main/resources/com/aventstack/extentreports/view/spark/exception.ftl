<#include "../commons/commons-variables.ftl">

<!DOCTYPE html>
<html>
<#include "partials/head.ftl">
<body class="attributes-view ${theme}">
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
                                            <span class="font-size-14">Exception</span>
                                        </a>
                                    </li> 
                                </ul>
                                <ul class="tools text-right">
                                    <li>
                                        <a href="#">
                                            <span class="badge badge-primary">${exceptionContext?size}</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="test-list-wrapper scrollable">
                                <ul class="test-list-item">
                                    <#list exceptionContext as context>
                                        <li class="test-item">
										  <div class="open-test">
										    <div class="test-detail">
										      <p class="name">${context.exceptionInfo.exceptionName}</p>
										      <p class="duration text-sm">${context.testList?size} tests</p>
										    </div>
										  </div>
										  <div class="test-contents d-none">
										    <div class="info">
										      <h4>${context.exceptionInfo.exceptionName}</h4>
										    </div>
										    <table class='table table-sm mt-4'>
										      <thead>
										        <tr>
										          <th class="status-col">Status</th>
										          <th class="timestamp-col">Timestamp</th>
										          <th>TestName</th>
										        </tr>
										      </thead>
										      <tbody>
										        <#list context.testList as test>
										        <tr>
										          <td>
										            <div class='status-avatar ${test.status}-bg'>
										              <i class="fa fa-${Icon.getIcon(test.status)} text-white"></i>
										            </div>
										          </td>
										          <td>${test.startTime?string[("HH:mm:ss a")]}</td>
										          <td class='linked' test-id='${test.getID()}'>
										            ${test.name}
										            <#if test.parent??>
										            <div class="">
										              <span class="badge badge-default">${test.parent.name}</span>
										            </div>
										            </#if>
										          </td>
										        </tr>
										        </#list>
										      </tbody>
										    </table>
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