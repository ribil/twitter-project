<#macro login path isRegisterForm>

<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Имя пользователя"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Пароль"/>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="email@mail.com"/>
        </div>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if !isRegisterForm><a href="/registration">Зарегистрироваться</a></#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Войти</#if></button>
</form>
</#macro>


<#macro logout>
<#--<div class="form-group row">
    <div class="col-sm-4">
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" class="form-control" value="Выйти"/>
        </form>
    </div>
</div>-->

<form class="form-inline" action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Выйти</button>
</form>
</#macro>
