

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
            <c:forEach var="bom" items="${requestScope.fullbom}">
                <p>Forespørgsel: ${bom.orderId}</p>
            </c:forEach>
        </div>


        <section class="orderlist-tablesection" style="font-weight: bold;">
            <br>
            <div class="tables-title">
                <h2 class="requestlist-tables-title">TRÆ OG TAGPLADER</h2>
            </div>

            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Længde</td>
                    <td class="tableHeader-RequestList">Antal</td>
                    <td class="tableHeader-RequestList">Enhed</td>

                    </thead>

                    <c:forEach var="category1" items="${requestScope.category1BOM}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${category1.name}</td>
                            <td class="tableRows-RequestList">${category1.length}</td>
                            <td class="tableRows-RequestList">${category1.amount}</td>
                            <td class="tableRows-RequestList">${category1.unit}</td>



                        </tr>
                    </c:forEach>


                </table>
            </div>
        </section>

        <section class="orderlist-tablesection" style="font-weight: bold;">
            <br>
            <div class="tables-title">
                <h2 class="requestlist-tables-title">BESLAG OG SKRUER</h2>
            </div>

            <div class="orderlist-tablediv ">
                <table class="orders-table tableStyle">
                    <thead class="orders-th">
                    <td class="tableHeader-RequestList">Navn</td>
                    <td class="tableHeader-RequestList">Længde</td>
                    <td class="tableHeader-RequestList">Antal</td>
                    <td class="tableHeader-RequestList">Enhed</td>

                    </thead>

                    <c:forEach var="category2" items="${requestScope.category2BOM}">
                        <tr class="orders-tr">

                            <td class="tableRows-RequestList">${category2.name}</td>
                            <td class="tableRows-RequestList">Ingen</td>
                            <td class="tableRows-RequestList">${category2.amount}</td>
                            <td class="tableRows-RequestList">${category2.unit}</td>


                        </tr>
                    </c:forEach>


                </table>
            </div>
        </section>








    </jsp:body>

</t:pagetemplate>
