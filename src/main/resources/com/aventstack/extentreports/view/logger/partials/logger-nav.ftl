<div id="aside" class="app-aside fade box-shadow-x folded dark">
	<div class="sidenav modal-dialog dk">
		<div class="navbar">
			<a href="#" class="navbar-brand">
			<span class="d-inline small"><img src="https://cdn.rawgit.com/extent-framework/extent-github-cdn/d74480e/commons/img/logo.png"></span>
			</a>
		</div>
		<div class="flex hide-scroll">
			<div class="scroll">
				<div class="nav-border b-primary">
					<ul class="nav bg">
						<li>
							<a href="index.html">
							<span class="nav-icon">
							<i class="fa fa-align-left"></i>
							</span>
							<span class="nav-text">Tests</span>
							</a>
						</li>
						<#if config.getValue("enableDashboard")=="true">
						<li>
							<a href="dashboard.html">
							<span class="nav-icon">
							<i class="fa fa-dashboard"></i>
							</span>
							<span class="nav-text">Dashboard</span>
							</a>
						</li>
						</#if>
						<#if categoryContext?? && categoryContext?size != 0>
						<li>
							<a href="tag.html">
							<span class="nav-icon">
							<i class="fa fa-tag"></i>
							</span>
							<span class="nav-text">Tags</span>
							</a>
						</li>
						</#if>
						<#if exceptionContext?? && exceptionContext?size != 0>
						<li>
							<a href="exception.html">
							<span class="nav-icon">
							<i class="fa fa-warning"></i>
							</span>
							<span class="nav-text">Exceptions</span>
							</a>
						</li>
						</#if>
						<li class="pb-2 hidden-folded"></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="no-shrink">
			<div class="nav-fold">
				<a id="lightsout" class="d-flex p-2-3"><span><i class="fa fa-desktop"></i></span></a>
				<div class="hidden-folded flex p-2-3 bg">
					<div class="d-flex p-1">
						<a href="http://github.com/extent-framework/extentreports" class="flex text-nowrap"><i class="fa fa-github"></i></a>
						<a href="http://extentreports.com" class="px-2"><i class="fa fa-book"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>