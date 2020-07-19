<#assign col = code?size>
<div class='row'>
<#list code as c>
 <div class='col-md-${12/col}'>
  <textarea readonly class='code-block'>${c}</textarea>
 </div>
</#list>
</div>
