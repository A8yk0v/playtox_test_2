<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    Sign up
    <@l.login "/signup" />
    <a href="/signin">Sign In</a>
</@c.page>