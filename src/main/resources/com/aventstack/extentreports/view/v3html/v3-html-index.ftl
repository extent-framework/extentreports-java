<#assign config=report.getConfigContext()>
<#assign parentCount=report.reportStatusStats.parentCount>
<#assign childCount=report.reportStatusStats.childCount>
<#assign grandChildCount=report.reportStatusStats.grandChildCount>
<#assign theme=config.containsKey('theme')?then(config.getValue('theme')?lower_case, 'standard')>
<#assign testViewChartLocation=config.containsKey('chartLocation')?then(config.getValue('chartLocation')?lower_case, 'top')>
<#assign chartVisibleOnOpen=config.containsKey('chartVisibilityOnOpen')?then(config.getValue('chartVisibilityOnOpen'), 'false')>
<#assign extentxUrl=config.containsKey('extentx-url')?then(config.getValue('extentx-url'), '')>
<#assign klovUrl=config.containsKey('klov-url')?then(config.getValue('klov-url'), '')>
<#assign disableToggleActionForPassedNode=config.containsKey('disableToggleActionForPassedNode')?then(config.getValue('disableToggleActionForPassedNode'), '')>
<#assign enableTimeline=config.containsKey('enableTimeline')?then(config.getValue('enableTimeline'), 'true')>
<#assign systemAttributeContext=report.getSystemAttributeContext().getSystemAttributeList()>
<#assign categoryContext=report.getCategoryContextInfo().getTestAttributeTestContextList()>
<#assign authorContext=report.getAuthorContextInfo().getTestAttributeTestContextList()>
<#assign exceptionContext=report.getExceptionContextInfo().getExceptionTestContextList()>
<#assign bddReport=false>
<#assign bddClass=''>
<#if report.testList?? && report.testList?size != 0>
<#assign firstTest=report.testList[0]>
<#assign bddReport = (firstTest.hasChildren() && firstTest.nodeContext.get(0).isBehaviorDrivenType())?then(true, false)>
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
			<#if config.getValue('enableCategoryView')?? && config.getValue('enableCategoryView') == 'true'>
			<#include 'category-view/v3-html-category-view.ftl'>
			</#if>
			<#if config.getValue('enableExceptionView')?? && config.getValue('enableExceptionView') == 'true'>
			<#include 'exception-view/v3-html-exception-view.ftl'>
			</#if>
			<#if config.getValue('enableAuthorView')?? && config.getValue('enableAuthorView') == 'true'>
			<#include 'author-view/v3-html-author-view.ftl'>			
			</#if>
			<#include 'dashboard-view/v3-html-dashboard-view.ftl'>
			<#if config.getValue('enableTestRunnerLogsView')?? && config.getValue('enableTestRunnerLogsView') == 'true'>
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
			        <#list report.testList as t>"${t.name}":${t.runDurationMillis/1000}<#if t_has_next>,</#if></#list>
			   </#if>
			</#macro>
			var timeline = {
			    <@listTestNameDuration testList=report.testList />
			};
		</script>
		</#if>
		<#if config.getValue('offline')?string == 'true'>
		<script src='extent/js/extent.js' type='text/javascript'></script>
		<#else>
		<script src='${ config.getValue('protocol') }://cdn.rawgit.com/extent-framework/extent-github-cdn/e9ccc29bd18c7914697075f44948d0913e462478/v3html/js/extent.js' type='text/javascript'></script>
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
			<#if config.containsKey('js')>
				${ config.getValue('js') }
			</#if>
			<#if config.containsKey('scripts')>
			${ config.getValue('scripts') }
			</#if>
		</script>
	</body>
</html>