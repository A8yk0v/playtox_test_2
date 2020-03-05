<#import "parts/common.ftl" as c>
<#import "parts/signout.ftl" as l>

<@c.page>
    <@l.signout/>
    <form action="/admin" method="get">
        <div><input type="submit" value="Back"/></div>
    </form>
    <div>Список совершенных покупок</div>
    <div>
        <table>
            <tr>
                <th>ID</th>
                <th>User.login</th>
                <th>Product.name</th>
                <th>Date</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <#list purchases as purchase>
                <tr>
                    <td>${purchase.id}</td>
                    <td>${purchase.user.login}</td>
                    <td>${purchase.product.name}</td>
                    <td>${purchase.date}</td>
                    <td>${purchase.price}</td>
                    <td>${purchase.quantity}</td>
                </tr>
            </#list>
        </table>
    </div>
</@c.page>
