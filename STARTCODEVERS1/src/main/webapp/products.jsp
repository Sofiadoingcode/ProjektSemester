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
                <td class="tableHeader-RequestList">Enhed type </td>
                <td class="tableHeader-RequestList">Pris</td>
                <td class="tableHeader-RequestList">Antal</td>
                <td class="tableHeader-RequestList">Højde</td>
                <td class="tableHeader-RequestList">Bredte</td>
                </thead>


                <c:forEach var="product" items="${applicationScope.ProductsList}">

                <tr class="orders-tr">

                    <td class="tableRows-RequestList"> ${product.productID} </td>
                    <td class="tableRows-RequestList"> ${product.name} </td>
                    <td class="tableRows-RequestList"> ${product.category} </td>
                    <td class="tableRows-RequestList"> ${product.unit} </td>
                    <td class="tableRows-RequestList"> ${product.price} </td>
                    <td class="tableRows-RequestList"> ${product.amount} </td>
                    <td class="tableRows-RequestList"> ${product.height} </td>
                    <td class="tableRows-RequestList"> ${product.width} </td>
                </tr>

                </c:forEach>


            </table>
        </div>

        <br>


        <form>


            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Navn</label>
            </div>

            <div class="form-floating">
                <select class="form-select" id="category" aria-label="Floating label select example">

                    <option value="Træ & Tagplader">Træ & Tagplader</option>
                    <option value="Beslag & Skruer">Beslag & Skruer</option>



                </select>
                <label for="category">Kategori</label>
            </div>

            <div class="form-floating">
                <select class="form-select" id="type" aria-label="Floating label select example">
                    <option value="1">stk</option>
                    <option value="2">pakke</option>
                    <option value="3">rulle</option>
                    <option value="4">sæt</option>
                </select>
                <label for="type">Pakke types</label>
            </div>


            <div class="form-floating mb-3">
                <input type="number" class="form-control" id="Height" placeholder="name@example.com">
                <label for="Height">Højde</label>
            </div>

            <div class="form-floating mb-3">
                <input type="number" class="form-control" id="Width" placeholder="name@example.com">
                <label for="Height">Længde</label>
            </div>


            <button type="submit" value="Lav ny product" class="btn-viewcarportorder-betal">Lav ny product</button>
        </form>


    </jsp:body>
</t:pagetemplate>