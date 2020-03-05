<#import "parts/common.ftl" as c>
<#import "parts/signout.ftl" as l>

<@c.page>
    <@l.signout/>
    <form action="/admin/listPurchase" method="get">
        <div><input type="submit" value="List purchase"/></div>
    </form>
    <div style="display: inline-block">
        <form method="post" action="/admin/addProduct" style="width: 100%">
            <fieldset>
                <legend>Создать товар</legend>
                <div><label> Name: <input type="text" name="name"/></label></div>
                <div><label> Description: <input type="text" name="description"/></label></div>
                <div><label> Price: <input type="number" name="price"/></label></div>
                <div><label> Quantity: <input type="number" name="quantity"/></label></div>
                <input type="submit" value="Add"/>
                <#if isRenderIsAdd>
                    <#if isAdd>
                        <p style="color: green">Успешно</p>
                    <#else>
                        <p style="color: red">Что-то пошло не так :/</p>
                    </#if>
                </#if>
            </fieldset>
        </form>
    </div>

    <div style="display: inline-block">
        <form method="post" action="/admin/deleteProduct" style="width: 100%">
            <fieldset>
                <legend>Удалить товар по ID</legend>
                <div><label> ID: <input type="number" name="product_id"/></label></div>
                <input type="submit" value="Delete"/>
                <#if isRenderIsDelete>
                    <#if isDelete>
                        <p style="color: green">Успешно</p>
                    <#else>
                        <p style="color: red">Что-то пошло не так :/</p>
                    </#if>
                </#if>
            </fieldset>
        </form>
    </div>

    <div style="display: inline-block">
        <form method="post" action="/admin/editProduct" style="width: 100%">
            <fieldset>
                <legend>Редактировать товар по ID</legend>
                <div><label> ID: <input type="number" name="product_id"/></label></div>
                <div><label> Name: <input type="text" name="name"/></label></div>
                <div><label> Description: <input type="text" name="description"/></label></div>
                <div><label> Price: <input type="number" name="price"/></label></div>
                <div><label> Quantity: <input type="number" name="quantity"/></label></div>
                <input type="submit" value="Add"/>
                <#if isRenderIsEdit>
                    <#if isEdit>
                        <p style="color: green">Успешно</p>
                    <#else>
                        <p style="color: red">Что-то пошло не так :/</p>
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
