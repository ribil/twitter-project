<#import "parts/common.ftl" as c>


<@c.page>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-6 mt-5">

            <h5 class="mt-5">Твит:</h5>

            <h3>${currentMessage.getText()}&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="badge badge-secondary"><#if currentMessage.getAuthorNick()??>
                    ${currentMessage.getAuthorNick()}<#else>anon</#if>
                </span>
            </h3>

            <form class="mt-5" method="post" action="/addreply">
                <div class="input-group mb-3">
                    <input type="hidden" class="form-control" name="currentMessageId"
                           value="${currentMessage.getId()}" aria-describedby="basic-addon2">
                    <input type="text" class="form-control" name="text" placeholder="Написать ответ"
                           aria-describedby="basic-addon2">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="input-group-append">
                        <button class="btn btn-dark" type="submit">Ответить</button>
                    </div>

                </div>
            </form>

            <h5 class="mt-5">Ответы:</h5>

            <ul class="list-group mt-3">

                <#if replies??>
                <#list replies as reply>
                    <li class="list-group-item">${reply.text}
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="badge badge-secondary"><#if reply.getAuthorNick()??>
                            ${reply.getAuthorNick()}<#else>anon</#if>
                        </span>
                    </li>

                </#list>
                <#else>
                    <h5>Нет ответов =(</h5>
                </#if>
            </ul>

        </div><!--End col-sm-md-->
    </div><!--End row-->
</div><#--End container-->
</@c.page>