<head>
	<meta charset='${ config.getValue('encoding') }' /> 
	<meta name='description' content='' />
	<meta name='robots' content='noodp, noydir' />
	<meta name='viewport' content='width=device-width, initial-scale=1' />
	<meta id="timeStampFormat" name="timeStampFormat" content='${timeStampFormat}'/>
	
	<#if offline=="true">
        <link rel="apple-touch-icon" href="${config.getValue("offlineDirectory")}material-icons.css">
        <link rel="stylesheet" href="${config.getValue("offlineDirectory")}v3html-style.css">
        <link rel="fontstylesheet" href="${config.getValue("offlineDirectory")}font-awesome.min.css">
    <#else>
        <link href='${ config.getValue('protocol') }://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600' rel='stylesheet' type='text/css' />
        <link href="${ config.getValue('protocol') }://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
        <link href='${ config.getValue('protocol') }://${cdnURI}${csscommit}/v3html/css/extent.css' type='text/css' rel='stylesheet' />
    </#if>
	
	<title>${ config.getValue('documentTitle') }</title>

	<style type='text/css'>
		/* json-tree */
		.jstBracket,.jstComma,.jstValue{white-space:pre-wrap}.jstValue{font-size:10px;font-weight:400;font-family:"Lucida Console",Monaco,monospace}.jstProperty{color:#666;word-wrap:break-word}.jstBool{color:#2525CC}.jstNum{color:#D036D0}.jstNull{color:gray}.jstStr{color:#2DB669}.jstFold:after{content:' -';cursor:pointer}.jstExpand{white-space:normal}.jstExpand:after{content:' +';cursor:pointer}.jstFolded{white-space:normal!important}.jstHiddenBlock{display:none}
		<#if config.containsKey('css')>
			${ config.getValue('css') }
		</#if>
		<#if config.containsKey('styles')>
			${ config.getValue('styles') }
		</#if>
	</style>
	
	<script type="text/javascript">
		/*! json-tree - v0.2.2 - 2017-09-25, MIT LICENSE */
		var JSONTree=function(){var n={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#x27;","/":"&#x2F;"},t=0,r=0;this.create=function(n,t){return r+=1,N(u(n,0,!1),{class:"jstValue"})};var e=function(t){return t.replace(/[&<>'"]/g,function(t){return n[t]})},s=function(){return r+"_"+t++},u=function(n,t,r){if(null===n)return f(r?t:0);switch(typeof n){case"boolean":return l(n,r?t:0);case"number":return i(n,r?t:0);case"string":return o(n,r?t:0);default:return n instanceof Array?a(n,t,r):c(n,t,r)}},c=function(n,t,r){var e=s(),u=Object.keys(n).map(function(r){return j(r,n[r],t+1,!0)}).join(m()),c=[g("{",r?t:0,e),N(u,{id:e}),p("}",t)].join("\n");return N(c,{})},a=function(n,t,r){var e=s(),c=n.map(function(n){return u(n,t+1,!0)}).join(m());return[g("[",r?t:0,e),N(c,{id:e}),p("]",t)].join("\n")},o=function(n,t){var r=e(JSON.stringify(n));return N(v(r,t),{class:"jstStr"})},i=function(n,t){return N(v(n,t),{class:"jstNum"})},l=function(n,t){return N(v(n,t),{class:"jstBool"})},f=function(n){return N(v("null",n),{class:"jstNull"})},j=function(n,t,r){var s=v(e(JSON.stringify(n))+": ",r),c=N(u(t,r,!1),{});return N(s+c,{class:"jstProperty"})},m=function(){return N(",\n",{class:"jstComma"})},N=function(n,t){return d("span",t,n)},d=function(n,t,r){return"<"+n+Object.keys(t).map(function(n){return" "+n+'="'+t[n]+'"'}).join("")+">"+r+"</"+n+">"},g=function(n,t,r){return N(v(n,t),{class:"jstBracket"})+N("",{class:"jstFold",onclick:"JSONTree.toggle('"+r+"')"})};this.toggle=function(n){var t=document.getElementById(n),r=t.parentNode,e=t.previousElementSibling;""===t.className?(t.className="jstHiddenBlock",r.className="jstFolded",e.className="jstExpand"):(t.className="",r.className="",e.className="jstFold")};var p=function(n,t){return N(v(n,t),{})},v=function(n,t){return Array(2*t+1).join(" ")+n};return this}();
	</script>
</head>
