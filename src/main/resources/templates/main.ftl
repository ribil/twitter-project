<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/addform.ftl" as a>
<#include "parts/security.ftl">

<@c.page>

<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-6 mt-5">
    <#if isUser>
        ${warning?ifExists}
        <@a.addform "/add" />
    </#if>

        <#--<p><@l.logout /></p>-->
            <h5 class="mt-5">Все сообщения</h5>

            <ul class="list-group mt-3">

                <#list messages as message>
                    <li class="list-group-item">${message.text}
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </li>
                <#else>
                    <h5>Нет сообщений =(</h5>
                </#list>
            </ul>

        </div><!--End col-sm-md-->
    </div><!--End row-->
</div><#--End container-->
</@c.page>