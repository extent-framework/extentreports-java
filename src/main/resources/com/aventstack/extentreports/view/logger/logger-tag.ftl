<#include "../commons/commons-variables.ftl">

<!DOCTYPE html>
<html lang="en">
	<#include "partials/logger-head.ftl">
	<body class="${theme}">
		<div class="app" id="app">
			<#include "partials/logger-nav.ftl">
			<div id="content" class="app-content box-shadow-0" role="main">
				<div class="content-header white box-shadow d-lg-none" id="content-header">
					<div class="navbar navbar-expand-lg">
						<a class="d-lg-none mx-2" data-toggle="modal" data-target="#aside">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 512 512">
								<path d="M80 304h352v16H80zM80 248h352v16H80zM80 192h352v16H80z"/>
							</svg>
						</a>
						<div class="navbar-text nav-title flex" id="pageTitle"><img src="logo.png"></div>
					</div>
				</div>
				<div class="content-main d-flex flex" id="content-main">
					<div class="d-flex flex fixed-height">
						<#include "../commons/commons-tag.ftl">
						<div class="d-flex flex" id="content-body">
							<div class="d-flex flex-column flex white">
								<div class="navbar flex-nowrap box-shadow">
									<a data-toggle="modal" data-target="#content-aside" data-modal class="mr-1 d-md-none">
									<span class="btn btn-sm btn-icon primary">
									<i class="fa fa-th"></i>
									</span>
									</a>
									<div class="input-group flex mr-1 hidden-sm-down">
						              	<input id="qc" type="text" class="form-control px-0 no-bg no-border no-shadow search" placeholder="Search..." required="">
						              	<span class="input-group-btn">
						                	<button class="btn no-bg no-border no-shadow" type="button"><i class="fa fa-search text-muted text-xs"></i></button>
						              	</span>
						            </div>
									<ul class="nav nav-xs flex-row no-border pull-right">
								        <li class="nav-item dropdown">
									        <a class="nav-link b-l" data-toggle="dropdown">
									            <span class="text-xs ">
										      		<i class="fa fa-warning"></i>
										      		<i class="fa fa-caret-down"></i>
										        </span>
									        </a>
								        </li>
								        <#include "partials/logger-nav-right-items.ftl">
								    </ul>
								</div>
								<div class="scroll-y">
									<div id="tcp" class="p-3"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<#assign p="https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/commons/js/">
		<#if offline=="true"><#assign p=config.getValue("offlineDirectory")></#if>
		<script src="${p}attr.js"></script>
		<#if config.containsKey("scripts") && config.getValue("scripts")?has_content>
		<#include "../commons/commons-inject-js.ftl">
		</#if>
	</body>
</html>
