<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Твиттер</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</span></a>
            </li>
<#if isUser>
            <li class="nav-item">
                <a class="nav-link" href="/user/${user.getId()}">Мои твиты</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Профиль</span></a>
            </li>
</#if>
        </ul>
    </div>
    <div class="navbar-text mr-2">${name}</div>
    <#if !isUser>
        <form class="form-inline" action="/login">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Войти</button>
        </form>
    <#else>
        <@l.logout />
    </#if>
</nav>
