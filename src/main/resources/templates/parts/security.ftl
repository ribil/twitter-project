<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isUser = user.isUser()
    >
<#else>
    <#assign
    name = ""
    isUser = false
    >
</#if>