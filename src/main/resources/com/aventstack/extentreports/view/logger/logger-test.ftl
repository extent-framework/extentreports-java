<#include "../commons/commons-variables.ftl">

<!DOCTYPE html>
<html lang="en">
	<#include "partials/logger-head.ftl">
	<script type="text/javascript">
		/*! json-tree - v0.2.2 - 2017-09-25, MIT LICENSE */
		var JSONTree=function(){var n={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;","/":"&#x2F;"},t=0,r=0;this.create=function(n,t){return r+=1,N(u(n,0,!1),{class:"jstValue"})};var e=function(t){return t.replace(/[&<>'"]/g,function(t){return n[t]})},s=function(){return r+"_"+t++},u=function(n,t,r){if(null===n)return f(r?t:0);switch(typeof n){case"boolean":return l(n,r?t:0);case"number":return i(n,r?t:0);case"string":return o(n,r?t:0);default:return n instanceof Array?a(n,t,r):c(n,t,r)}},c=function(n,t,r){var e=s(),u=Object.keys(n).map(function(r){return j(r,n[r],t+1,!0)}).join(m()),c=[g("{",r?t:0,e),N(u,{id:e}),p("}",t)].join("\n");return N(c,{})},a=function(n,t,r){var e=s(),c=n.map(function(n){return u(n,t+1,!0)}).join(m());return[g("[",r?t:0,e),N(c,{id:e}),p("]",t)].join("\n")},o=function(n,t){var r=e(JSON.stringify(n));return N(v(r,t),{class:"jstStr"})},i=function(n,t){return N(v(n,t),{class:"jstNum"})},l=function(n,t){return N(v(n,t),{class:"jstBool"})},f=function(n){return N(v("null",n),{class:"jstNull"})},j=function(n,t,r){var s=v(e(JSON.stringify(n))+": ",r),c=N(u(t,r,!1),{});return N(s+c,{class:"jstProperty"})},m=function(){return N(",\n",{class:"jstComma"})},N=function(n,t){return d("span",t,n)},d=function(n,t,r){return"<"+n+Object.keys(t).map(function(n){return" "+n+'="'+t[n]+'"'}).join("")+">"+r+"</"+n+">"},g=function(n,t,r){return N(v(n,t),{class:"jstBracket"})+N("",{class:"jstFold",onclick:"JSONTree.toggle('"+r+"')"})};this.toggle=function(n){var t=document.getElementById(n),r=t.parentNode,e=t.previousElementSibling;""===t.className?(t.className="jstHiddenBlock",r.className="jstFolded",e.className="jstExpand"):(t.className="",r.className="",e.className="jstFold")};var p=function(n,t){return N(v(n,t),{})},v=function(n,t){return Array(2*t+1).join(" ")+n};return this}();
	</script>
	<body class="${theme}">
		<div class="app" id="app">
			<#include "partials/logger-nav.ftl">
			<div id="content" class="app-content box-shadow-0" role="main">
				<div id="content" class="app-content box-shadow-2 box-radius-2" role="main">
					<div class="content-header white  box-shadow-2" id="content-header">
						<div class="navbar navbar-expand-lg">
							<div class="input-group flex mr-1">
				              	<input id="qt" type="text" class="form-control px-0 no-bg no-border no-shadow search" placeholder="Search..." required="">
				              	<span class="input-group-btn">
				                	<button class="btn no-bg no-border no-shadow" type="button"><i class="fa fa-search text-muted text-xs"></i></button>
				              	</span>
				            </div>
							<ul class="nav nav-xs flex-row no-border pull-right">
								<li class="nav-item dropdown">
									<a class="nav-link b-l" data-toggle="dropdown">
									<span class="text-xs">
									<i class="fa fa-warning"></i>
									<i class="fa fa-caret-down"></i>
									</span>
									</a>
									<div class="dropdown-menu pt-0 mt-2 animate fadeIn">
										<#if report.containsStatus(Status.PASS)><a class="dropdown-item" href="#"><span class="tf" status="pass"><i class="fa fa-dot-circle-o text-success"></i>Pass</span></a></#if>
										<#if report.containsStatus(Status.FAIL)><a class="dropdown-item" href="#"><span class="tf" status="fail"><i class="fa fa-dot-circle-o text-danger"></i>Fail</span></a></#if>
										<#if report.containsStatus(Status.FATAL)><a class="dropdown-item" href="#"><span class="tf" status="fatal"><i class="fa fa-dot-circle-o text-danger"></i>Fatal</span></a></#if>
										<#if report.containsStatus(Status.ERROR)><a class="dropdown-item" href="#"><span class="tf" status="error"><i class="fa fa-dot-circle-o text-warning"></i>Error</span></a></#if>
										<#if report.containsStatus(Status.WARNING)><a class="dropdown-item" href="#"><span class="tf" status="warning"><i class="fa fa-dot-circle-o text-warning"></i>Warning</span></a></#if>
										<#if report.containsStatus(Status.SKIP)><a class="dropdown-item" href="#"><span class="tf" status="skip"><i class="fa fa-dot-circle-o text-blue"></i>Skip</span></a></#if>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#"><span class="tf" status="clear"><i class="fa fa-dot-circle-o text-warn"></i>Clear</span></a>
									</div>
								</li>
						        <#if categoryContext?? && categoryContext?size != 0>
						        <li class="nav-item dropdown">
							        <a class="nav-link b-l" data-toggle="dropdown">
							            <span class="text-xs">
								      		<i class="fa fa-tag"></i>
								      		<i class="fa fa-caret-down"></i>
								        </span>
							        </a>
							        <div class="dropdown-menu pt-0 mt-2 animate fadeIn">
							        	<#list categoryContext as category>
						    	        <a class="dropdown-item" href="#"><span class="cf">${category.name}</span></a>
						    	        </#list>
						    	        <div class="dropdown-divider"></div>
						    	        <a class="dropdown-item" href="#"><span class="tf" status="clear"><i class="fa fa-dot-circle-o text-warn"></i>Clear</span></a>
						    	      </div>
						        </li>
						        </#if>
						        <#if deviceContext?? && deviceContext?size != 0>
						        <li class="nav-item dropdown">
							        <a class="nav-link b-l" data-toggle="dropdown">
							            <span class="text-xs">
								      		<i class="fa fa-tablet text-sm"></i>
								      		<i class="fa fa-caret-down"></i>
								        </span>
							        </a>
							        <div class="dropdown-menu pt-0 mt-2 animate fadeIn">
							        	<#list deviceContext as device>
						    	        <a class="dropdown-item" href="#"><span class="df">${device.name}</span></a>
						    	        </#list>
						    	        <div class="dropdown-divider"></div>
						    	        <a class="dropdown-item" href="#"><span class="tf" status="clear"><i class="fa fa-dot-circle-o text-warn"></i>Clear</span></a>
						    	      </div>
						        </li>
						        </#if>
						        <li class="nav-item lightsout"><a class="nav-link b-l" href="#"><span><i class="fa fa-desktop"></i></span></a></li>
								<li><a href="#" class="b-l"><span class="badge badge-pull">${config.getValue("reportName")}</span></a></li>
								<li><a href="#" class="b-l"><span class="badge badge-pull">4.0</span></a></li>
							</ul>
						</div>
					</div>
					<div class="content-main " id="content-main">
						<div class="padding">
							<div class="row justify-content-md-center">
								<div class="col-md-8 col-sm-12">
									<div class="logger-content">
										<h6 class="mb-3 ml-1">Test Logs</h6>
										<#macro content test>
											<#assign spacer="" level=test.level>
											<#if level!=0>
												<#if level!=1>
													<#list 2..level as l>
														<#assign spacer+="&nbsp;&nbsp;&nbsp;&nbsp;">
													</#list>
												</#if>
												<#assign spacer+="|---">
											</#if>
											<div class="st ${test.status} test l${level}" 
												tags="<#list test.categoryContext.all as tag>${tag.name}<#if tag_has_next> </#if></#list>"
												devices="<#list test.deviceContext.all as device>${device.name}<#if device_has_next> </#if></#list>">
												<span class="tt">${test.startTime?string("MM.dd.yyyy HH:mm:ss")}</span>&nbsp;
												<span class="ts">${test.status?replace("ing","")}</span><#list test.status.toString()?replace("ing","")?length..5 as x>&nbsp;</#list>
												<span class="tn">${spacer}[${test.name}] started</span>
												<@attributes test=test />
												
												<#list test.logContext.all as log>
												<div class="st ${log.status}">
													<span class="tt">${log.timestamp?string("MM.dd.yyyy HH:mm:ss")}</span>&nbsp;
													<span class="ts">${log.status?replace("ing","")}</span><#list log.status.toString()?replace("ing","")?length..5 as x>&nbsp;</#list> 
													<span class="tn">${spacer}[${test.name}]</span>
													<#if log.hasScreenCapture()><span class="tm">${log.screenCaptureContext.last.sourceWithIcon}</span></#if>
													<span class="td"><#if log.details??>${log.details}</#if><#if log.exceptionInfo??>threw an exception<br><pre>${log.exceptionInfo.stackTrace}</pre></#if></span>
												</div>
												</#list>
											</div>
										</#macro>
										<#macro attributes test>
											<#if test.hasCategory()>
												<span class="category-list">
													<#list test.categoryContext.all as category>
													<span class="category badge"><i class="fa fa-tag"></i> ${category.name}</span>
													</#list>
												</span>
											</#if>
											<#if test.hasAuthor()>
												<span class="author-list">
													<#list test.authorContext.all as author>
													<span class="author badge"><i class="fa fa-user"></i> ${author.name}</span>
													</#list>
												</span>
											</#if>
											<#if test.hasDevice()>
												<span class="device-list">
													<#list test.deviceContext.all as device>
													<span class="device badge"><i class="fa fa-tablet text-sm"></i> ${device.name}</span>
													</#list>
												</span>
											</#if>
										</#macro>
										<#list report.testList as test>
											<div class="tsc" status="${test.status}">
												<@content test=test />
												<#list test.nodeContext.all as node>
													<@content test=node />
													<#list node.nodeContext.all as c>
														<@content test=c />
														<#list c.nodeContext.all as gc>
															<@content test=gc />
														</#list>
													</#list>
												</#list>
											</div>
										</#list>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<#assign p="https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/logger/js/">
		<#if offline=="true"><#assign p=config.getValue("offlineDirectory")></#if>
		<script src="${p}logger-scripts.js"></script>
		<#if config.containsKey("scripts") && config.getValue("scripts")?has_content>
		<#include "../commons/commons-inject-js.ftl">
		</#if>
	</body>
</html>
