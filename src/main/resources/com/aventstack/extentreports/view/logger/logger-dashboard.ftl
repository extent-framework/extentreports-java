<#include "../commons/commons-variables.ftl">

<!DOCTYPE html>
<html lang="en">
	<#include "partials/logger-head.ftl">
	<body class="${theme}">
		<div class="app" id="app">
			<#include "partials/logger-nav.ftl">
			<div id="content" class="app-content box-shadow-0" role="main">
				<div id="content" class="app-content box-shadow-2 box-radius-2" role="main">
					<div class="content-header white  box-shadow-2" id="content-header">
						<div class="navbar navbar-expand-lg">
							<a class="d-lg-none mx-2" data-toggle="modal" data-target="#aside">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 512 512">
									<path d="M80 304h352v16H80zM80 248h352v16H80zM80 192h352v16H80z"/>
								</svg>
							</a>
							<div class="navbar-text nav-title flex" id="pageTitle">Dashboard</div>
							<ul class="nav nav-xs flex-row no-border pull-right">
								<#include "partials/logger-nav-right-items.ftl">
							</ul>
						</div>
					</div>
					<#include "../commons/commons-dashboard.ftl">
				</div>
			</div>
		</div>
		<#assign p="https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/commons/js/">
		<#if offline=="true"><#assign p=config.getConfig("offlineDirectory")></#if>
		<script src="${p}dashboard.js"></script>
		<#if config.containsKey("scripts") && config.getConfig("scripts")?has_content>
		<#include "../commons/commons-inject-js.ftl">
		</#if>
		<#include "../commons/commons-dashboard-scripts.ftl">
	</body>
</html>