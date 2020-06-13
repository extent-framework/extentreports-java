<nav>
	<div class="nav-wrapper">
		<a href="#!" class="brand-logo black"><img src="https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/commons/img/logo.png"></a>
		<!-- slideout menu -->
		<ul id='slide-out' class='side-nav fixed hide-on-med-and-down'>
			<li class='waves-effect active'><a href='#!' view='test-view' onclick="configureView(0);chartsView('test');"><i class='material-icons'>dashboard</i></a></li>
			<#if config.getConfig('enableCategoryView')?? && config.getConfig('enableCategoryView')=='true' && categoryContext?? && categoryContext?size != 0>
			<li class='waves-effect'><a href='#!' view='category-view' onclick="configureView(1)"><i class='material-icons'>label_outline</i></a></li>
			</#if>
			<#if config.getConfig('enableExceptionView')?? && config.getConfig('enableExceptionView')=='true' && exceptionContext?? && exceptionContext?size != 0>
			<li class='waves-effect'><a href='#!' view='exception-view' onclick="configureView(2)"><i class='material-icons'>bug_report</i></a></li>
			</#if>
			<#if config.getConfig('enableAuthorView')?? && config.getConfig('enableAuthorView')=='true' && authorContext?? && authorContext?size != 0>
			<li class='waves-effect'><a href='#!' view='author-view' onclick="configureView(3)"><i class='material-icons'>person_outline</i></a></li>
			</#if>
			<li class='waves-effect'><a href='#!' onclick="configureView(-1);chartsView('dashboard');" view='dashboard-view'><i class='material-icons'>track_changes</i></a></li>
			<#if config.getConfig('enableTestRunnerLogsView')?? && config.getConfig('enableTestRunnerLogsView')=='true' && report.testRunnerLogs?? && report.testRunnerLogs?size != 0>
			<li class='waves-effect'><a href='#!' onclick="configureView(-1)" view='testrunner-logs-view'><i class='material-icons'>note</i></a></li>
			</#if>
		</ul>
		<!-- report name -->
		<span class='report-name'>${ config.getConfig('reportName') }</span>
		<!-- report headline -->
		<span class='report-headline'></span>
		<!-- nav-right -->
		<ul id='nav-mobile' class='right hide-on-med-and-down nav-right'>
			<#if klovUrl!=''>
			<a href='${ klovUrl }'>
			<span class='label blue darken-3'>Klov</span>
			</a>
			</#if>
			<a href='#!'>
			<span class='label blue darken-3 suite-start-time'>${ report.startTime?datetime?string["${timeStampFormat}"] }</span>
			</a>
		</ul>
	</div>
</nav>