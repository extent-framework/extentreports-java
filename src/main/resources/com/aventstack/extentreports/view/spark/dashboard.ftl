<#include "../commons/commons-variables.ftl">

<#assign boxsize='col-md-12'>
<#if report.reportStatusStats.childCount!=0>
    <#assign boxsize='col-sm-12 col-md-6'>
</#if>
<#if (report.analysisStrategy=="BDD") || (report.reportStatusStats.childCount != 0 && report.reportStatusStats.grandChildCount != 0)>
    <#assign boxsize='col-sm-12 col-md-4'>
</#if>

<#assign chartWidth="115" chartHeight="90" chartBoxHeight="94">

<#assign reportType="" parentHeading="Tests" childHeading="Steps" grandChildHeading="" size=2>
<#if report.analysisStrategy=="SUITE">
	<#assign parentHeading="Suite" childHeading="Tests" grandChildHeading="Tests" size=2>
	<#if report.reportStatusStats.grandChildCount!=0>
		<#assign childHeading="Classes" grandChildHeading="Tests" size=3>
	</#if>
</#if>
<#if report.analysisStrategy=="BDD">
	<#assign reportType="bdd" parentHeading="Features" childHeading="Scenarios" grandChildHeading="Steps" size=3>
</#if>
<#if report.analysisStrategy=="CLASS">
	<#assign parentHeading="Class" childHeading="Methods" grandChildHeading="" size=2>
</#if>

<!DOCTYPE html>
<html>
	<#include "partials/head.ftl">
	<body class="dashboard-view ${theme}">
		<div class="app header-dark side-nav-folded">
			<div class="layout">
				<#include "partials/navbar.ftl">
				<#include "partials/sidenav.ftl">
				<div class="page-container">
					<div class="main-content">
						<#include "partials/dashboard.ftl">
					</div>
				</div>
			</div>
		</div>
		<#include "partials/scripts.ftl">
	</body>
</html>