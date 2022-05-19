<%--
  Created by IntelliJ IDEA.
  User: Micky
  Date: 05-05-2022
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<t:pagetemplate>

    <jsp:attribute name="footer">
                Requests site
    </jsp:attribute>
    <jsp:body>
        <br>

        <div class="orders-header-box">
            <h1 class="orders-header">FORESPØRGELSER</h1>
        </div>

        <section class="orderlist-tablesection" style="font-weight: bold;">
            <br>
            <div class="tables-title">
                <h2 class="requestlist-tables-title">IKKE ACCEPTEREDE ENDNU</h2>
            </div>

            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Email</td>
                    <td class="tableHeader-RequestList">Telefonnummer</td>
                    <td class="tableHeader-RequestList">By</td>
                    <td class="tableHeader-RequestList">Bestilling</td>
                    <td class="tableHeader-RequestList">Pris</td>
                    <td class="tableHeader-RequestList">Handling</td>

                    </thead>

                    <c:forEach var="request" items="${requestScope.nonAcceptedRequests}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${request.name}</td>
                            <td class="tableRows-RequestList">${request.email}</td>
                            <td class="tableRows-RequestList">${request.phonenumber}</td>
                            <td class="tableRows-RequestList">${request.city}</td>
                            <td class="tableRows-RequestList">
                                <form>
                                    <button type="submit" name="seeStykliste" value="${request.idorder}" formaction="fc/BOMlist?command=BOMlist" formmethod="post"
                                            class="seeorder-btn">Se Stykliste
                                    </button>
                                </form>
                            </td>
                            <td class="tableRows-RequestList">
                                <form method="post" action="fc/modifyFinalPrice?command=modifyFinalPrice">
                                    <div class="input-group">
                                        <button type="submit" class="input-group-addon" id="sizing-addon2" class="orders-btn-Checkmark orders-complete orders-btn--right-margin" name="modifyFP" value="${request.idorder}">

                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                                 class="bi bi-check" viewBox="0 0 16 16">
                                                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                            </svg>

                                        </button>
                                        <input type="number" class="form-control" name="finalPriceText"
                                               value="${request.finalprice}">
                                    </div>

                                </form>
                            </td>

                            <td class="tableRows-RequestList">
                                <form method="post">
                                    <button type="submit" name="accept" value="${request.idorder}" formaction="fc/acceptRequest?command=acceptRequest" formmethod="post"
                                            class="orders-btn-Checkmark orders-complete orders-btn--right-margin">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-check" viewBox="0 0 16 16">
                                            <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                        </svg>
                                    </button>

                                    <button type="submit" name="delete" value="${request.idorder}" formaction="" formmethod="post"
                                            class="orders-btn-DeleteX orders-delete">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-x" viewBox="0 0 16 16">
                                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                    </button>
                                </form>


                            </td>


                        </tr>
                    </c:forEach>


                </table>
            </div>
        </section>
        <br>
        <br>
        <br>

        <section class="orderlist-tablesection" style="font-weight: bold;">
            <div class="tables-title">
                <h2 class="requestlist-tables-title">ACCEPTEREDE</h2>
            </div>
            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Email</td>
                    <td class="tableHeader-RequestList">Telefonnummer</td>
                    <td class="tableHeader-RequestList">By</td>
                    <td class="tableHeader-RequestList">Bestilling</td>
                    <td class="tableHeader-RequestList">Pris</td>
                    <td class="tableHeader-RequestList">Handling</td>

                    </thead>
                    <c:forEach var="requests" items="${requestScope.acceptedRequests}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${requests.name}</td>
                            <td class="tableRows-RequestList">${requests.email}</td>
                            <td class="tableRows-RequestList">${requests.phonenumber}</td>
                            <td class="tableRows-RequestList">${requests.city}</td>
                            <td class="tableRows-RequestList">
                                <form>
                                    <button type="submit" name="seeOrder" formaction="seeOrder.jsp" class="seeorder-btn">
                                        Se Stykliste
                                    </button>
                                </form>
                            </td>

                            <td class="tableRows-RequestList">
                                <form method="post">
                                    <input type="number" class="form-control"
                                           value="${requests.finalprice}" name="product_height">

                                </form>

                            </td>

                            <td class="tableRows-RequestList">
                                <form method="post">
                                    <button type="submit" name="unAccept" value="${requests.idorder}" formaction="fc/unAcceptRequest?command=unAcceptRequest" formmethod="post"
                                            class="orders-btn orders-btn-ArrowUp orders-undoCompletion orders-btn--right-margin">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-arrow-up" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                  d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z"/>
                                        </svg>
                                    </button>

                                    <button type="submit" name="delete" value="${requests.idorder}" formaction="fc/deleteRequest?command=deleteRequest" formmethod="post"
                                            class="orders-btn orders-btn-DeleteX orders-delete">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-x" viewBox="0 0 16 16">
                                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                    </button>
                                </form>

                            </td>

                        </tr>
                    </c:forEach>


                </table>
            </div>
        </section>

        <section class="orderlist-collapsablesection" style="font-weight: bold">

            <button type="button" class="orderlist-collapsible">Betalte Forespørgelser</button>
            <div class="orderlist-content">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Email</td>
                    <td class="tableHeader-RequestList">Telefonnummer</td>
                    <td class="tableHeader-RequestList">By</td>
                    <td class="tableHeader-RequestList">Bestilling</td>
                    <td class="tableHeader-RequestList">Pris</td>
                    <td class="tableHeader-RequestList">Handling</td>

                    </thead>
                    <c:forEach var="requests" items="${requestScope.paidRequests}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${requests.name}</td>
                            <td class="tableRows-RequestList">${requests.email}</td>
                            <td class="tableRows-RequestList">${requests.phonenumber}</td>
                            <td class="tableRows-RequestList">${requests.city}</td>
                            <td class="tableRows-RequestList">
                                <form>
                                    <button type="submit" name="seeOrder" formaction="seeOrder.jsp" class="seeorder-btn">
                                        Se Stykliste
                                    </button>
                                </form>
                            </td>
                            <td class="tableRows-RequestList">
                                <form method="post">
                                    <input type="number" class="form-control"
                                           value="${requests.finalprice}" name="product_height">

                                </form>

                            </td>

                            <td class="tableRows-RequestList">
                                <form method="post">

                                    <button type="submit" name="delete" value="${requests.idorder}" formaction="fc/deleteRequest?command=deleteRequest" formmethod="post"
                                            class="orders-btn orders-btn-DeleteX orders-delete">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-x" viewBox="0 0 16 16">
                                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                    </button>
                                </form>

                            </td>

                        </tr>
                    </c:forEach>


                </table>

            </div>
            <script>
                var coll = document.getElementsByClassName("orderlist-collapsible");
                var i;

                for (i = 0; i < coll.length; i++) {
                    coll[i].addEventListener("click", function () {
                        this.classList.toggle("orderlist-active");
                        var content = this.nextElementSibling;
                        if (content.style.display === "block") {
                            content.style.display = "none";
                        } else {
                            content.style.display = "block";
                        }
                    });
                }
            </script>

            <!-- The Modal -->


        </section>




    </jsp:body>

</t:pagetemplate>