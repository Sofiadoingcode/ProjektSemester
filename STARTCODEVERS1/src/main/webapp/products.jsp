<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:body>

        <h1> Produkt Liste</h1>
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
                <td class="tableHeader-RequestList">Product type</td>
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


                                <div class="form-floating">
                                    <select class="form-select" name="product_category2"
                                            aria-label="Floating label select example">
                                        <c:forEach var="option" items="${applicationScope.CategoryList}">
                                            <c:choose>
                                                <c:when test="${option.value.equals(product.category)}">
                                                    <option value="${option.value}" selected> ${option.value} </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${option.value}"> ${option.value} </option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </select>
                                    <label for="category">Kategori</label>
                                </div>

                            </td>
                            <td class="tableRows-RequestList">

                                <div class="form-floating">
                                    <select class="form-select" name="product_unit"
                                            aria-label="Floating label select example">
                                        <c:forEach var="option" items="${applicationScope.UnitList}">
                                            <c:choose>
                                                <c:when test="${option.value.equals(product.unit)}">
                                                    <option value="${option.value}" selected> ${option.value} </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${option.value}"> ${option.value} </option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </select>
                                    <label for="category">Pakke type</label>
                                </div>
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
                                <div class="form-floating">
                                    <select class="form-select" name="product_producttype"
                                            aria-label="Floating label select example">
                                        <c:forEach var="option" items="${applicationScope.ProductTypeList}">
                                            <c:choose>
                                                <c:when test="${option.value.equals(product.productType)}">
                                                    <option value="${option.value}" selected> ${option.value} </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${option.value}"> ${option.value} </option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </select>
                                    <label for="category">Produkt Type</label>
                                </div>

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
        <br>

        <h1> Ny Produkt</h1>
        <div class="d-flex justify-content-center">

            <br>
            <br>
            <form action="fc/saveproduct?command=saveproduct" method="post">


                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="name" id="floatingInput"
                           placeholder="name@example.com">
                    <label for="floatingInput">Navn</label>
                </div>
                <br>
                <div class="form-floating">
                    <select class="form-select" id="category" name="category"
                            aria-label="Floating label select example">

                        <option value="Træ & Tagplader">Træ & Tagplader</option>
                        <option value="Beslag & Skruer">Beslag & Skruer</option>
                    </select>
                    <label for="category">Kategori</label>
                </div>
                <br>
                <div class="form-floating">
                    <select class="form-select" id="type" name="unit" aria-label="Floating label select example">
                        <option value="stk">stk</option>
                        <option value="pakke">pakke</option>
                        <option value="rulle">rulle</option>
                        <option value="sæt">sæt</option>
                    </select>
                    <label for="type">Pakke types</label>
                </div>

                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="amount" id="amount" placeholder="name@example.com">
                    <label for="amount">amount</label>
                </div>
                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="price" id="price" placeholder="name@example.com">
                    <label for="price">pris</label>
                </div>
                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="height" id="Height" placeholder="name@example.com">
                    <label for="Height">Højde</label>
                </div>
                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="width" id="Width" placeholder="name@example.com">
                    <label for="width">Længde</label>
                </div>
                <br>
                <div class="form-floating">
                    <select class="form-select" name="product_producttype"
                            aria-label="Floating label select example">
                        <c:forEach var="option" items="${applicationScope.ProductTypeList}">
                            <option value="${option.value}"> ${option.value} </option>
                        </c:forEach>
                    </select>
                    <label for="category">Produkt Type</label>
                </div>
                <br>
                <div id="login-btn-login-div">
                    <input type="submit" id="login-btn" class="loginFormText" value="Lav Produkt"/>
                </div>


            </form>

        </div>
    </jsp:body>
</t:pagetemplate>