<#setting number_format="0">
<#list code as c>
 <div class='json-tree' id='code-block-json-${index}'></div>
 <script>
  function jsonTreeCreate${index}() { 
   document.getElementById('code-block-json-${index}').innerHTML = JSONTree.create(${c}); 
  }
  jsonTreeCreate${index}();
 </script>
</#list>