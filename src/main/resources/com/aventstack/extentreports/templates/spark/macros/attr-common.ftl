<#macro attrCommon view ctx>
<#if ctx?size != 0>
<#compress>
<div class="test-wrapper row view ${view?lower_case}-view attributes-view">
  <div class="test-list">
    <div class="test-list-tools">
      <ul class="tools pull-left"><li><a href=""><span class="font-size-14">${view}</span></a></li></ul>
      <ul class="tools text-right"><li><a href="#"><span class="badge badge-primary">${ctx?size}</span></a></li></ul>
    </div>
    <div class="test-list-wrapper scrollable">
      <ul class="test-list-item">
        <#list ctx as c>
        <li class="test-item">
          <div class="test-detail">
            <span class="meta">
            <#if c.passed!=0><span class='badge log pass-bg'>${c.passed}</span></#if>
            <#if c.failed!=0><span class='badge log badge-danger'>${c.failed}</span></#if>
            <#if c.skipped!=0><span class='badge log badge-skip'>${c.skipped}</span></#if>
            <#if c.others!=0><span class='badge log badge-warning'>${c.others}</span></#if>
            </span>
            <p class="name">${c.attr.name}</p>
            <p class="duration text-sm">${c.size()} tests</p>
          </div>
          <div class="test-contents d-none">
            <div class="info">
              <h4>${c.attr.name}</h4>
              <#if c.passed!=0><span status="pass" class='badge log pass-bg'>${c.passed} passed</span></#if>
              <#if c.failed!=0><span status="fail" class='badge log badge-danger'>${c.failed} failed</span></#if>
              <#if c.skipped!=0><span status="skip" class='badge log badge-skip'>${c.skipped} skipped</span></#if>
              <#if c.others!=0><span status="skip" class='badge log badge-warning'>${c.others} others</span></#if>
            </div>
            <table class='table table-sm mt-4'>
              <thead>
                <tr>
                  <th class="status-col">Status</th>
                  <th class="timestamp-col">Timestamp</th>
                  <th>TestName</th>
                </tr>
              </thead>
              <tbody>
                <#list c.testList as test>
                <tr class="tag-test-status" status="${test.status.toLower()}">
                  <td><span class="badge log ${test.status.toLower()}-bg">${test.status?string}</span></td>
                  <td>${test.startTime?string[("HH:mm:ss a")]}</td>
                  <td>
                    <a href="#" class="linked" test-id='${test.getAncestor().getId()}' id='${test.getId()}'>${test.name}</a>
                    <#if test.parent??>
                    <div class="">
                      <span class="badge badge-default">${test.getFullName()}</span>
                    </div>
                    </#if>
                  </td>
                </tr>
                </#list>
              </tbody>
            </table>
          </div>
        </li>
        </#list>
      </ul>
    </div>
  </div>
  <div class="test-content scrollable">
    <div class="test-content-detail">
      <div class="detail-body"></div>
    </div>
  </div>
</div>
</#compress>
</#if>
</#macro>