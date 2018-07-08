<#import "parts/common.ftl" as c>


<@c.page>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-6 mt-5">

            <h5 class="mb-5">Редактировать твит</h5>
            <form method="post" action="/editMessage">
                <div class="input-group mb-3">
                    <input type="hidden" class="form-control" name="id"
                           value="${tweet.id}">
                    <input type="text" class="form-control" name="text"
                           value="${tweet.text}">

                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="input-group-append">
                        <button class="btn btn-dark" type="submit">Сохранить</button>

                    </div>
                </div>
            </form>

            <p class="mt-3"><a class="btn btn-secondary" href="/user/${user.getId()}"
                               role="button">Отмена</a></p>

        </div><!--End col-sm-md-->
    </div><!--End row-->
</div><#--End container-->
</@c.page>