<#import "parts/common.ftl" as c>
<#import "parts/signout.ftl" as l>

<@c.page>
<@l.signout/>
<div>
    <form method="get" action="/shop" style="width: 0">
        <fieldset>
            <legend>Поле покупки</legend>
            <div><label> Product id: <input type="number" name="product_id" placeholder="id"/></label></div>
            <div><label> Quantity: <input type="number" name="quantity"/></label></div>
            <input type="submit" value="Buy"/>
            <#if isRenderIsBuy>
                <#if isBuy>
                    <p style="color: green">Успешная покупка</p>
                <#else>
                    <p style="color: red">Покупка не возможна</p>
                </#if>
            </#if>
        </fieldset>
    </form>
</div>
<div>Список товаров</div>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
            </tr>
        </#list>
    </table>
</div>
</@c.page>
