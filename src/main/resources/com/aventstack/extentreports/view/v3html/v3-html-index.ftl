<#include "../commons/commons-variables.ftl">

<#assign parentCount=report.reportStatusStats.parentCount>
<#assign childCount=report.reportStatusStats.childCount>
<#assign grandChildCount=report.reportStatusStats.grandChildCount>
<#assign theme=config.containsConfig('theme')?then(config.getConfig('theme')?lower_case, 'standard')>
<#assign testViewChartLocation=config.containsConfig('chartLocation')?then(config.getConfig('chartLocation')?lower_case, 'top')>
<#assign chartVisibleOnOpen=config.containsConfig('chartVisibilityOnOpen')?then(config.getConfig('chartVisibilityOnOpen'), 'false')>
<#assign klovUrl=config.containsConfig('klov-url')?then(config.getConfig('klov-url'), '')>
<#assign disableToggleActionForPassedNode=config.containsConfig('disableToggleActionForPassedNode')?then(config.getConfig('disableToggleActionForPassedNode'), '')>
<#assign enableTimeline=config.containsConfig('enableTimeline')?then(config.getConfig('enableTimeline'), 'true')>
<#assign bddReport=false>
<#assign bddClass=''>
<#if report.testList?? && report.testList?size != 0>
<#assign firstTest=report.testList[0]>
<#assign bddReport = (TestService.testHasChildren(firstTest) && firstTest.nodeContext.get(0).isBehaviorDrivenType())?then(true, false)>
</#if>
<#assign parentViewChartsHeading='Classes' childViewChartsHeading='Tests' grandChildViewChartsHeading='Steps'>
<#assign parentLabel='class(es)' childLabel='test(s)' grandChildLabel='log(s)'>
<#if bddReport>
<#assign parentViewChartsHeading='Features' childViewChartsHeading='Scenarios' grandChildViewChartsHeading='Steps'>
<#assign parentLabel='feature(s)' childLabel='scenario(s)' grandChildLabel='step(s)'>
<#assign bddClass='bdd-report'>
<#else>
<#if (childCount == 0 || grandChildCount == 0)>
<#assign parentViewChartsHeading='Tests' childViewChartsHeading='Steps' grandChildViewChartsHeading=''>
<#assign parentLabel='test(s)' childLabel='step(s)' grandChildLabel=''>
</#if>
<#if report.analysisStrategy?string == 'SUITE'>
<#assign parentViewChartsHeading='Suites' childViewChartsHeading='Tests' grandChildViewChartsHeading='Test Methods'>
<#assign parentLabel='suite(s)' childLabel='test(s)' grandChildLabel='method(s)'>
</#if>
</#if>

<#assign timeStampFormat = config.getConfig('timeStampFormat')>
<#assign resourceCDN=config.getConfig('resourceCDN') cdnURI="cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@" csscommit="ff53917fbbdb5ef820abbbe4d199a6942dc771ff" jscommit="ff53917fbbdb5ef820abbbe4d199a6942dc771ff">
<#if resourceCDN=="extentreports">
    <#assign cdnURI="extentreports.com/resx" csscommit="" jscommit="">
</#if>

<#include "../commons/commons-variables.ftl">

<!DOCTYPE html>
<html>
	<#include 'v3-html-head.ftl'>
	<body class='extent ${ theme } default hide-overflow ${ bddClass }'>
		<div id='theme-selector' alt='Click to toggle theme. To enable by default, use theme configuration.' title='Click to toggle theme. To enable by default, use theme configuration.'>
			<span><i class='material-icons'>desktop_windows</i></span>
		</div>
		<#include 'v3-html-nav.ftl'>
		<!-- container -->
		<div class='container'>
			<#include 'test-view/v3-html-test-view.ftl'>
			<#if config.getConfig('enableCategoryView')?? && config.getConfig('enableCategoryView') == 'true'>
			<#include 'category-view/v3-html-category-view.ftl'>
			</#if>
			<#if config.getConfig('enableExceptionView')?? && config.getConfig('enableExceptionView') == 'true'>
			<#include 'exception-view/v3-html-exception-view.ftl'>
			</#if>
			<#if config.getConfig('enableAuthorView')?? && config.getConfig('enableAuthorView') == 'true'>
			<#include 'author-view/v3-html-author-view.ftl'>			
			</#if>
			<#include 'dashboard-view/v3-html-dashboard-view.ftl'>
			<#if config.getConfig('enableTestRunnerLogsView')?? && config.getConfig('enableTestRunnerLogsView') == 'true'>
			<#include 'logs-view/v3-html-testrunner-logs-view.ftl'>
			</#if>
		</div>
		<!-- container -->
		<script>
			var statusGroup = {
                parentCount: ${ report.reportStatusStats.parentCount?c },
				passParent: ${ report.reportStatusStats.parentCountPass?c },
				failParent: ${ report.reportStatusStats.parentCountFail?c },
				fatalParent: ${ report.reportStatusStats.parentCountFatal?c },
				errorParent: ${ report.reportStatusStats.parentCountError?c },
				warningParent: ${ report.reportStatusStats.parentCountWarning?c },
				skipParent: ${ report.reportStatusStats.parentCountSkip?c },
				exceptionsParent: ${ report.reportStatusStats.parentCountExceptions?c },
				childCount: ${ report.reportStatusStats.childCount?c },
				passChild: ${ report.reportStatusStats.childCountPass?c },
				failChild: ${ report.reportStatusStats.childCountFail?c },
				fatalChild: ${ report.reportStatusStats.childCountFatal?c },
				errorChild: ${ report.reportStatusStats.childCountError?c },
				warningChild: ${ report.reportStatusStats.childCountWarning?c },
				skipChild: ${ report.reportStatusStats.childCountSkip?c },
				infoChild: ${ report.reportStatusStats.childCountInfo?c },
				debugChild: ${ report.reportStatusStats.childCountDebug?c },
				exceptionsChild: ${ report.reportStatusStats.childCountExceptions?c },
				grandChildCount: ${ report.reportStatusStats.grandChildCount?c },
				passGrandChild: ${ report.reportStatusStats.grandChildCountPass?c },
				failGrandChild: ${ report.reportStatusStats.grandChildCountFail?c },
				fatalGrandChild: ${ report.reportStatusStats.grandChildCountFatal?c },
				errorGrandChild: ${ report.reportStatusStats.grandChildCountError?c },
				warningGrandChild: ${ report.reportStatusStats.grandChildCountWarning?c },
				skipGrandChild: ${ report.reportStatusStats.grandChildCountSkip?c },
				infoGrandChild: ${ report.reportStatusStats.grandChildCountInfo?c },
				debugGrandChild: ${ report.reportStatusStats.grandChildCountDebug?c },
				exceptionsGrandChild: ${ report.reportStatusStats.grandChildCountExceptions?c },
			};
		</script>
		<#if enableTimeline=='true'>
		<script>
			<#macro listTestNameDuration testList>
			   <#if report.testList??>
			        <#list report.testList as t>"${t.name}":${(TestService.getRunDurationMillis(t)/1000)?c?replace(",","")}<#if t_has_next>,</#if></#list>
			   </#if>
			</#macro>
			var timeline = {
			    <@listTestNameDuration testList=report.testList />
			};
		</script>
		</#if>
	
		<#if offline=="true">
		  <script src='${config.getConfig("offlineDirectory")}v3html-script.js' type='text/javascript'></script>
		<#else>
		  <script src='${ config.getConfig('protocol') }://${cdnURI}${jscommit}/v3html/js/extent.js' type='text/javascript'></script>
		</#if>
		<#assign hide=(chartVisibleOnOpen=='true')?then(false, true)>
		<#if hide>
		<script type='text/javascript'>
			$(document).ready(function() {
				$('#test-view-charts').addClass('hide');
			});
		</script>
		</#if>
		<script type='text/javascript'>
			<#if config.containsConfig('js')>
				${ config.getConfig('js') }
			</#if>
			<#if config.containsConfig('scripts')>
			${ config.getConfig('scripts') }
			</#if>
		</script>
	</body>
</html>