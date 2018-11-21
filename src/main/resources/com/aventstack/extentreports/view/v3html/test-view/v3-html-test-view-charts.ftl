<#assign sizeLarge='s12 m12 l12'>
<#if report.reportStatusStats.childCount!=0>
    <#assign sizeLarge='s12 m6 l6'>
</#if>
<#if bddReport || (report.reportStatusStats.childCount != 0 && report.reportStatusStats.grandChildCount != 0)>
    <#assign sizeLarge='s12 m4 l4'>
</#if>
<#assign chartWidth="90" chartHeight="70" chartBoxHeight="94">
<div id='test-view-charts' class='subview-full'>
    <div id='charts-row' class='row nm-v nm-h'>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ parentViewChartsHeading }</div>
                <div class='chart-box' style="max-height:${chartBoxHeight}px;">
                    <canvas id='parent-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
                </div>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.parentPercentagePass}%'><span class='strong'>${ report.reportStatusStats.parentCountPass }</span> ${parentLabel} passed</span>
                </div>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.parentPercentageFail}%'>${ report.reportStatusStats.parentCountFail + report.reportStatusStats.parentCountFatal }</span> ${parentLabel} failed, <span class='strong tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.parentPercentageOthers}%'>${ report.reportStatusStats.parentCountError + report.reportStatusStats.parentCountWarning + report.reportStatusStats.parentCountSkip }</span> others
                </div>
            </div>
        </div>
        <#if report.reportStatusStats.childCount != 0>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ childViewChartsHeading }</div>
                <div class='chart-box' style="max-height:${chartBoxHeight}px;">
                    <canvas id='child-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
                </div>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.childPercentagePass}%'><span class='strong'>${ report.reportStatusStats.childCountPass }</span> ${childLabel} passed</span>
                </div>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.childPercentageFail}%'>${ report.reportStatusStats.childCountFail + report.reportStatusStats.childCountFatal }</span> ${childLabel} failed, <span class='strong tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.childPercentageOthers}%'>${ report.reportStatusStats.childCountError + report.reportStatusStats.childCountWarning + report.reportStatusStats.childCountSkip + report.reportStatusStats.childCountInfo }</span> others
                </div>
            </div>
        </div>
        </#if>
        <#if report.reportStatusStats.grandChildCount != 0>
        <div class='col ${ sizeLarge } np-h'>
            <div class='card-panel nm-v'>
                <div class='left panel-name'>${ grandChildViewChartsHeading }</div>
                <div class='chart-box' style="max-height:${chartBoxHeight}x;">
                    <canvas id='grandchild-analysis' width='${chartWidth}' height='${chartHeight}'></canvas>
                </div>
                <div class='block text-small'>
                    <span class='tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.grandChildPercentagePass}%'><span class='strong'>${ report.reportStatusStats.grandChildCountPass }</span> ${grandChildLabel} passed</span>
                </div>
                <div class='block text-small'>
                    <span class='strong tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.grandChildPercentageFail}%'>${ report.reportStatusStats.grandChildCountFail + report.reportStatusStats.grandChildCountFatal }</span> ${grandChildLabel} failed, <span class='strong tooltipped' data-position='top' data-tooltip='${report.reportStatusStats.grandChildPercentageOthers}%'>${ report.reportStatusStats.grandChildCountSkip + report.reportStatusStats.grandChildCountError + report.reportStatusStats.grandChildCountWarning + report.reportStatusStats.grandChildCountInfo }</span> others
                </div>
            </div>
        </div>
        </#if>
    </div>
    <#if enableTimeline=='true'>
    <div id="timeline-chart" class="row nm-v nm-h">
        <div class="col s12 m12 l12 np-h">
            <div class="card-panel">
                <div class='left panel-name'>Timeline (seconds)</div>
                <div class="chart-box" style="width:98%;max-height:145px;">
                    <canvas id="timeline" height="120"></canvas>
                </div>
            </div>
        </div>
    </div>
    </#if>
</div>