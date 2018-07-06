<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="container">
<p>${message?ifExists}</p>
<@l.login "/login" false/>
</div>
</@c.page>
