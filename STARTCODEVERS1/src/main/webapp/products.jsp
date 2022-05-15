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
                <td class="tableHeader-RequestList">Enhed type</td>
                <td class="tableHeader-RequestList">Pris</td>
                <td class="tableHeader-RequestList">Antal</td>
                <td class="tableHeader-RequestList">Højde</td>
                <td class="tableHeader-RequestList">Bredte</td>
                <td> button</td>
                </thead>


                <c:forEach var="product" items="${applicationScope.ProductsList}">

                    <tr class="orders-tr">
                        <form>
                            <td class="tableRows-RequestList"> ${product.productID}

                            </td>

                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.name}" name="product_name"> <span
                                        class="input-group-append"> </span></div>
                            </td>

                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.category}" name="product_category">
                                    <span
                                            class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.unit}" name="product_unit"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.price}" name="product_price"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.amount}" name="product_amount"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.height}" name="product_height"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="text" class="form-control"
                                                                value="${product.width}" name="product_width"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <button type="submit" name="product_id" value="${product.productID}"
                                        formaction="fc/modifyproduct?command=modifyproduct" formmethod="post"
                                        class="orders-btn-Checkmark orders-complete orders-btn--right-margin">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-check" viewBox="0 0 16 16">
                                        <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                    </svg>
                                </button>

                            </td>
                        </form>
                    </tr>

                </c:forEach>


            </table>
        </div>

        <br>


        <form action="fc/saveproduct?command=saveproduct" method="post">


            <div class="form-floating mb-3">
                <input type="text" class="form-control" name="name" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Navn</label>
            </div>

            <div class="form-floating">
                <select class="form-select" id="category" name="category" aria-label="Floating label select example">

                    <option value="Træ & Tagplader">Træ & Tagplader</option>
                    <option value="Beslag & Skruer">Beslag & Skruer</option>
                </select>
                <label for="category">Kategori</label>
            </div>

            <div class="form-floating">
                <select class="form-select" id="type" name="unit" aria-label="Floating label select example">
                    <option value="stk">stk</option>
                    <option value="pakke">pakke</option>
                    <option value="rulle">rulle</option>
                    <option value="sæt">sæt</option>
                </select>
                <label for="type">Pakke types</label>
            </div>


            <div class="form-floating mb-3">
                <input type="number" class="form-control" name="amount" id="amount" placeholder="name@example.com">
                <label for="amount">amount</label>
            </div>

            <div class="form-floating mb-3">
                <input type="number" class="form-control" name="price" id="price" placeholder="name@example.com">
                <label for="price">pris</label>
            </div>

            <div class="form-floating mb-3">
                <input type="number" class="form-control" name="height" id="Height" placeholder="name@example.com">
                <label for="Height">Højde</label>
            </div>

            <div class="form-floating mb-3">
                <input type="number" class="form-control" name="width" id="Width" placeholder="name@example.com">
                <label for="width">Længde</label>
            </div>


            <button type="submit" value="Lav ny product" class="btn-viewcarportorder-betal">Lav ny product</button>

            <div id="login-btn-login-div">
                <input type="submit" id="login-btn" class="loginFormText" value="Log ind"/>
            </div>
        </form>


    </jsp:body>
</t:pagetemplate>