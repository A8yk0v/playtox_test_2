<#macro login path>
    <form action="${path}" method="post">
        <div><label> User Name: <input type="text" name="login"/></label></div>
        <div><label> Password <input type="password" name="password"/></label></div>
        <div><input type="submit" value="Submit"/></div>
    </form>
</#macro>