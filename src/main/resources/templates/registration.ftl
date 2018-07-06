<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="container">

<h5>Регистрация:</h5>
<p>${message?ifExists}</p>
<@l.login "/registration" true />

</div><!--End container-->
</@c.page>
