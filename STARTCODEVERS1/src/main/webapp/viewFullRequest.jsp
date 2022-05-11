

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
            <p>Forespørgsel: </p>
            <p>Navn: </p>
            <p>Bruger: </p>
        </div>


        <section class="orderlist-tablesection" style="font-weight: bold;">
            <br>
            <div class="tables-title">
                <h2 class="requestlist-tables-title">TRÆ...</h2>
            </div>

            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Længde</td>
                    <td class="tableHeader-RequestList">Antal</td>
                    <td class="tableHeader-RequestList">Enhed</td>
                    <td class="tableHeader-RequestList">Beskrivelse</td>

                    </thead>

                    <c:forEach var="productionlines" items="${requestScope.productionlines}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${productionlines.name}</td>
                            <td class="tableRows-RequestList">${productionlines.length}</td>
                            <td class="tableRows-RequestList">${productionlines.amount}</td>
                            <td class="tableRows-RequestList">${productionlines.unit}</td>
                            <td class="tableRows-RequestList"></td>



                        </tr>
                    </c:forEach>


                </table>
            </div>
        </section>

        <section class="orderlist-tablesection" style="font-weight: bold;">
            <br>
            <div class="tables-title">
                <h2 class="requestlist-tables-title">BESLAG...</h2>
            </div>

            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Længde</td>
                    <td class="tableHeader-RequestList">Antal</td>
                    <td class="tableHeader-RequestList">Enhed</td>
                    <td class="tableHeader-RequestList">Beskrivelse</td>

                    </thead>

                    <c:forEach var="request" items="${requestScope.nonAcceptedRequests}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${request.name}</td>
                            <td class="tableRows-RequestList">${request.email}</td>
                            <td class="tableRows-RequestList">${request.phonenumber}</td>
                            <td class="tableRows-RequestList">${request.city}</td>
                            <td class="tableRows-RequestList">
                                <form>
                                    <button type="submit" name="seeStykliste" formaction="seeOrderlineServlet"
                                            class="seeorder-btn">Se Stykliste
                                    </button>
                                </form>
                            </td>



                        </tr>
                    </c:forEach>


                </table>
            </div>
        </section>








    </jsp:body>

</t:pagetemplate>
