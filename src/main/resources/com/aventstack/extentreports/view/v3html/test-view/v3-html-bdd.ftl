<div class="sr-filters bdd-filters">
    <a class="btn-floating waves-effect waves-light pass green" title="pass"><i class='material-icons'>${MaterialIcon.getIcon(Status.PASS)}</i></a>
    <a class="btn-floating waves-effect waves-light fail red" title="fail"><i class='material-icons'>${MaterialIcon.getIcon(Status.FAIL)}</i></a>
    <a class="btn-floating waves-effect waves-light skip blue" title="skip"><i class='material-icons'>${MaterialIcon.getIcon(Status.SKIP)}</i></a>
    <a class="btn-floating waves-effect waves-light clear grey" title="clear"><i class='material-icons'>clear</i></a>
</div>
<#if test.description?? && test.description?has_content>
<div class='test-desc'>${test.description}</div>
</#if>
<#list test.nodeContext.all as node>
<div class='${node.behaviorDrivenTypeName?lower_case} node' test-id='${node.getId()}' status='${node.status}'>
    <#if TestService.testHasCategory(node)>
    <div class='category-list'>
        <#list node.categoryContext.all as category>
        <span class='category label'>${category.name}</span>
        </#list>
    </div>
    </#if>
    <span class='duration right label'>${TestService.getRunDuration(node)}</span>
    <div class="bdd-test">
        <div class="scenario-name"><span class='status ${node.status}' title='${node.status}'><i class='material-icons'>${MaterialIcon.getIcon(node.status)}</i></span> ${node.behaviorDrivenTypeName}: ${node.name}</div>
        <#if TestService.testHasScreenCapture(node)>
        <ul class='screenshots right'>
            <#list node.screenCaptureContext.all as sc>
            <li><a data-featherlight="image" href="${sc.path}"><i class='material-icons'>panorama</i></a></li>
            </#list>
        </ul>
        </#if>
        <#if node.description?? && node.description?has_content>
        ${node.description}
        </#if>
    </div>
    <#if TestService.testHasChildren(node)>
    <ul class='steps'>
        <#list node.nodeContext.all as child>
        <li test-id='${child.getId()}' class='node ${child.behaviorDrivenTypeName?lower_case} ${child.status}' status='${child.status}'>
            <div class="step-name" title="${child.description}"><span class='status ${child.status}' title='${child.status}'><i class='material-icons'>${MaterialIcon.getIcon(child.status)}</i></span>${child.name}</div>
            <#if TestService.testHasScreenCapture(child)>
            <ul class='screenshots right'>
                <#list child.screenCaptureContext.all as sc>
                <li><a data-featherlight="image" href="${sc.path}"><i class='material-icons'>panorama</i></a></li>
                </#list>
            </ul>
            </#if>
            <#list child.logContext.all as log>
            <#if log.exceptionInfo??>
                <textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
            <#else>
                <div class="node-step">${log.details}</div>
            </#if>
            </#list>
            <#if TestService.testHasChildren(child)>
            <ul class='gc steps'>
                <#list child.nodeContext.all as gc>
                    <li test-id='${gc.getId()}' class='gc ${gc.behaviorDrivenTypeName?lower_case} ${gc.status}' status='${gc.status}'>
                        <h6 class="step-name" title="${gc.description}"><span class='status ${gc.status}' title='${gc.status}'><i class='material-icons'>${MaterialIcon.getIcon(gc.status)}</i></span>${gc.name}</h6>
                        <#if TestService.testHasScreenCapture(gc)>
                        <ul class='screenshots right'>
                            <#list gc.screenCaptureContext.all as sc>
                            <li><a data-featherlight="image" href="${sc.path}"><i class='material-icons'>panorama</i></a></li>
                            </#list>
                        </ul>
                        </#if>
                        <#list gc.logContext.all as log>
                            <#if log.exceptionInfo??><textarea disabled class="code-block">${log.exceptionInfo.stackTrace}</textarea>
                            <#else><div class="node-step">${log.details}</div>
                        </#if>
                        </#list>
                    </li>
                </#list>
            </ul>
            </#if>
        </li>
        </#list>
    </ul>
    </#if>
</div>
</#list>