<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    Sign in
    <@l.login "/signin" />
    <#if isError>
        <p style="color: red">Ошибка</p>
    </#if>
    <a href="/signup">Sign Up</a>
</@c.page>