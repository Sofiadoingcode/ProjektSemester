<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>


    <jsp:body>
        <section>

            <div class="container">

                <h1> Din Carport</h1>

                <div class="row">
                    <div class="col-md-3">
                        <p style="font-size: 1.5rem">
                            Description

                        </p>


                    </div>
                    <div class="col-md-9 ">

                    </div>

                    <div class="row textDecorationViewCarportOrder">
                        <div class="col">
                            Height:
                        </div>

                        <div class="col">
                            Width:
                        </div>

                        <div class="col">
                            Something else:
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section>

            <div class="gy-6 row align-content-start">
                <!-- Button trigger modal -->
                <c:if test="${sessionScope.usersRequest.accepted}">

                <div style="margin-bottom: 5%" class="col">
                    <form class="col">

                        <a href="${pageContext.request.contextPath}/#" style="margin-left: 20%" class="btn-viewcarportorder-betal">Betal</a>
                    </form>
                </div>

                </c:if>


                <div style="margin-bottom: 5%; text-align: right" class="col">
                    <form>
                        <a href="${pageContext.request.contextPath}/#" style="margin-right: 20%" class="btn-viewcarportorder-betal">Submit</a>
                    </form>
                </div>
            </div>
        </section>
    </jsp:body>

</t:pagetemplate>
