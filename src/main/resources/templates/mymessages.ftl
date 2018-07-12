<#import "parts/common.ftl" as c>


<@c.page>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-6 mt-5">

            <h5 class="mt-5">Мои сообщения</h5>

            <ul class="list-group mt-3">

                <#list tweets as tweet>
                    <li class="list-group-item"><a href="/message/${tweet.id}">${tweet.text}</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="messageedit/${tweet.id}">
                            <i class="fas fa-pencil-alt"></i>
                        </a>
                    </li>
                <#else>
                    <h5>Нет сообщений =(</h5>
                </#list>
                 <#list retweets as retweet>
                    <li class="list-group-item">${retweet.text}&nbsp;&nbsp;&nbsp;
                        <span class="badge badge-secondary">retweet</span>
                    </li>
                 <#else>
                    <h5>Нет ретвитов...</h5>
                 </#list>
            </ul>

        </div><!--End col-sm-md-->
    </div><!--End row-->
</div><#--End container-->
</@c.page>