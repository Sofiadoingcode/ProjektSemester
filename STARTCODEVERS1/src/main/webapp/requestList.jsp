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

        <div class="orders-header-box">
            <h1 class="orders-header">Forespørgsler</h1>
        </div>

        <section class="orderlist-tablesection" style="font-weight: bold;">
            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Email</td>
                    <td class="tableHeader-RequestList">Telefonnummer</td>
                    <td class="tableHeader-RequestList">By</td>
                    <td class="tableHeader-RequestList">Bestilling</td>
                    <td class="tableHeader-RequestList">Handling</td>

                    </thead>
                    <tr class="orders-tr">

                        <td class="tableRows-RequestList">Bo</td>
                        <td class="tableRows-RequestList">Bo@gmail.com</td>
                        <td class="tableRows-RequestList">75849356</td>
                        <td class="tableRows-RequestList">Valby</td>
                        <td class="tableRows-RequestList">
                            <form>
                                <button type="submit" name="seeOrder" formaction="seeOrderlineServlet"
                                        class="seeorder-btn">See Order
                                </button>
                            </form>
                        </td>
                        <td class="tableRows-RequestList">
                            <form method="post">
                                <button type="submit" name="complete" formaction="completeorder" formmethod="post"
                                        class="orders-btn-Checkmark orders-complete orders-btn--right-margin">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-check" viewBox="0 0 16 16">
                                        <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                    </svg>
                                </button>

                                <button type="submit" name="delete" formaction="deleteOrder" formmethod="post"
                                        class="orders-btn-DeleteX orders-delete">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-x" viewBox="0 0 16 16">
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                </button>
                            </form>


                        </td>


                    </tr>


                </table>
            </div>
        </section>
        <br>
        <br>
        <br>

        <section class="orderlist-collapsablesection" style="font-weight: bold">

            <button type="button" class="orderlist-collapsible">Completed Orders</button>
            <div class="orderlist-content">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Email</td>
                    <td class="tableHeader-RequestList">Telefonnummer</td>
                    <td class="tableHeader-RequestList">By</td>
                    <td class="tableHeader-RequestList">Bestilling</td>
                    <td class="tableHeader-RequestList">Handling</td>

                    </thead>
                    <tr class="orders-tr">

                        <td class="tableRows-RequestList">Kim</td>
                        <td class="tableRows-RequestList">Kim@gmail.com</td>
                        <td class="tableRows-RequestList">85748392</td>
                        <td class="tableRows-RequestList">Den skumle gyde ved siden af den lokale fiskehandler</td>
                        <td class="tableRows-RequestList">
                            <form>
                                <button type="submit" name="seeOrder" formaction="seeOrder.jsp" class="seeorder-btn">See
                                    Order
                                </button>
                            </form>
                        </td>
                        <td class="tableRows-RequestList">
                            <form method="post">
                                <button type="submit" name="complete" formaction="completeorder" formmethod="post"
                                        class="orders-btn orders-btn-ArrowUp orders-undoCompletion orders-btn--right-margin">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-arrow-up" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z"/>
                                    </svg>
                                </button>

                                <button type="submit" name="delete" formaction="deleteOrder" formmethod="post"
                                        class="orders-btn orders-btn-DeleteX orders-delete">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-x" viewBox="0 0 16 16">
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                </button>
                            </form>

                        </td>

                    </tr>


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


        <%--            <div class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="orderModalLabel" aria-hidden="true">--%>
        <%--                <div class="modal-dialog" role="document">--%>
        <%--                    <div class="modal-content">--%>
        <%--                        <div class="modal-header">--%>
        <%--                            <h5 class="modal-title" id="orderModalLabel">Modal title</h5>--%>
        <%--                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
        <%--                                <span aria-hidden="true">&times;</span>--%>
        <%--                            </button>--%>
        <%--                        </div>--%>
        <%--                        <div class="modal-body">--%>
        <%--                            <p>Hello</p>--%>
        <%--                        </div>--%>
        <%--                        <div class="modal-footer">--%>
        <%--                            <button type="button" class="" data-dismiss="modal">Close</button>--%>
        <%--                            <button type="button" class="">Save changes</button>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>


    </jsp:body>

</t:pagetemplate>