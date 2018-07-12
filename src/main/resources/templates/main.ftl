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

            <h5 class="mt-5">Все сообщения</h5>

            <ul class="list-group mt-3">

                <#list tweets as tweet>
                    <li class="list-group-item">${tweet.text} &nbsp;&nbsp;&nbsp;
                        <#if isUser>
                            <#list userss as us>
                                <#if us.getUsername() != tweet.getAuthorName()>
    <a href="/retweet/${tweet.id}"><i class="fas fa-retweet" style="color: lightgray"></i></a>
                                </#if>
                            <#else>
                            <p>Проблема с юзерами</p>
                            </#list>

                        </#if>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </li>
                <#else>
                    <h5>Нет сообщений =(</h5>
                </#list>
            </ul>

            <nav aria-label="Page navigation">
                <ul class="pagination mt-5">
        <#list pagesList as p>
            <li class="page-item <#list pag as pa>
                 <#if p == pa>active</#if>
              </#list>
            "><a class="page-link" href="/page?pageNumber=${p}">${p}</a></li>
        </#list>
                </ul>
            </nav>


        </div><!--End col-sm-md-->
    </div><!--End row-->
</div><#--End container-->
</@c.page>