<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:body>


        <div class="orderlist-tablediv ">
            <table class="orders-table tableStyle">
                <thead class="orders-th">
                <td class="tableHeader-RequestList">ID</td>
                <td class="tableHeader-RequestList">Navn</td>
                <td class="tableHeader-RequestList">Kategori</td>
                <td class="tableHeader-RequestList">enhed type </td>
                <td class="tableHeader-RequestList">pris</td>
                <td class="tableHeader-RequestList">antal</td>

                </thead>


                <c:forEach var="product" items="${applicationScope.ProductsList}">

                <tr class="orders-tr">

                    <td class="tableRows-RequestList"> ${product.productID} </td>
                    <td class="tableRows-RequestList"> ${product.name} </td>
                    <td class="tableRows-RequestList"> ${product.category} </td>
                    <td class="tableRows-RequestList"> ${product.unit} </td>
                    <td class="tableRows-RequestList"> ${product.price} </td>
                    <td class="tableRows-RequestList"> ${product.amount} </td>

                </tr>

                </c:forEach>


            </table>
        </div>




    </jsp:body>
</t:pagetemplate>