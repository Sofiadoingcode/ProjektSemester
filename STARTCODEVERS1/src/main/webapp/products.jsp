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
                <td class="tableHeader-RequestList">Bredde</td>
                <td class="tableHeader-RequestList">Produkt Type</td>
                <td class="tableHeader-RequestList"> </td>
                </thead>


                <c:forEach var="product" items="${applicationScope.productsList}">

                    <tr class="orders-tr">
                        <form>
                            <td class="tableRows-RequestList"> ${product.productId}

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
                                        <c:forEach var="option" items="${applicationScope.categoryList}">
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
                                        <c:forEach var="option" items="${applicationScope.unitList}">
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
                                <div class="input-group"><input type="number" class="form-control"
                                                                value="${product.price}" name="product_price" step = "0.01"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="number" class="form-control"
                                                                value="${product.amount}" name="product_amount" step = "1"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="number" class="form-control"
                                                                value="${product.height}" name="product_height" step = "0.01"> <span
                                        class="input-group-append"> </span></div>
                            </td>
                            <td class="tableRows-RequestList">
                                <div class="input-group"><input type="number" class="form-control"
                                                                value="${product.width}" name="product_width" step = "0.01"> <span
                                        class="input-group-append"> </span></div>
                            </td>

                            <td class="tableRows-RequestList">
                                <div class="form-floating">
                                    <select class="form-select" name="product_producttype"
                                            aria-label="Floating label select example">
                                        <c:forEach var="option" items="${applicationScope.productTypeList}">
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
                                <button type="submit" name="product_id" value="${product.productId}"
                                        formaction="fc/modifyproduct?command=modifyproduct" formmethod="post"
                                        class="orders-btn-Checkmark orders-complete orders-btn--right-margin">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                    </svg>
                                </button>
                                <button type="submit" name="product_id" value="${product.productId}" formaction="fc/deleteproduct?command=deleteproduct" formmethod="post"
                                        class="orders-btn-DeleteX orders-delete">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-x" viewBox="0 0 16 16">
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
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

        <h1> Nyt Produkt</h1>
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
                    <input type="number" class="form-control" name="amount" id="amount" placeholder="name@example.com" step="1">
                    <label for="amount">amount</label>
                </div>
                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="price" id="price" placeholder="name@example.com" step="0.01">
                    <label for="price">pris</label>
                </div>
                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="height" id="Height" placeholder="name@example.com" step="0.01">
                    <label for="Height">Højde</label>
                </div>
                <br>
                <div class="form-floating mb-3">
                    <input type="number" class="form-control" name="width" id="Width" placeholder="name@example.com" step="0.01">
                    <label for="width">Bredde</label>
                </div>
                <br>
                <div class="form-floating">
                    <select class="form-select" name="product_producttype"
                            aria-label="Floating label select example">
                        <c:forEach var="option" items="${applicationScope.productTypeList}">
                            <option value="${option.value}"> ${option.value} </option>
                        </c:forEach>
                    </select>
                    <label for="category">Produkt Type</label>
                </div>
                <br>
                <div id="login-btn-login-div">
                    <input type="submit" id="login-btn" class="loginFormText" value="Lav vare"/>
                </div>


            </form>

        </div>
    </jsp:body>
</t:pagetemplate>