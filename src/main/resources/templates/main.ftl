<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/addform.ftl" as a>
<#import "parts/pager.ftl" as p>
<#include "parts/security.ftl">

<@c.page>

<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-6 mt-5">
            <#if !isUser>
                <p class="float-center"><strong>Default user: user</strong></p>
                <p class="float-center"><strong>Default password: 123</strong></p>
            </#if>

    <#if isUser>
        ${warning?ifExists}
        <@a.addform "/add" />
    </#if>

            <h5 class="mt-5">Все сообщения</h5>

 <@p.pager url page />
                <#list page.content as tweet>
                    <div class="card border-light mb-3" style="max-width: 22rem, border-color: gray;">
                        <div class="card-header"><#if tweet.getAuthorNick()??>
                            <strong>${tweet.getAuthorNick()}</strong><#else>anon</#if>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                <a style="color: darkslategray" href="/message/${tweet.id}">${tweet.text}</a>
                            </p>
                            <p>
                                 <#if isUser>
                                     <#list userss as us>
                                         <#if us.getUsername() != tweet.getAuthorName()>
    <a class="float-right" href="/retweet/${tweet.id}">
        <i class="fas fa-retweet" style="color: lightgray"></i>
    </a>
                                         <#else>
                                         <a class="float-right" href="user/messageedit/${tweet.id}">
                                             <i class="fas fa-pencil-alt"></i>
                                         </a>


                                         </#if>
                                     <#else>

                                     </#list>
                                     <a class="float-right mr-2" style="color: gray" href="/message/${tweet.id}">Ответить</a>
                                 </#if>
                            </p>
                        </div>
                    </div>

                <#else>
                    <h5>Нет сообщений =(</h5>
                </#list>

        </div><!--End col-sm-md-->
    </div><!--End row-->
</div><#--End container-->

</@c.page>