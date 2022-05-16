<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>


    <jsp:body>
        <div class="container">

            <h1> Din Carport</h1>

            <div class="row">
                <div class="col-md-3">
                    <p>
                        Description
                    </p>


                </div>
                <div class="col-md-9">
                    <p>
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                data-bs-target="#multiCollapseExample1" aria-expanded="false"
                                aria-controls="multiCollapseExample2">
                            Se tegning 1
                        </button>
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                data-bs-target="#multiCollapseExample2" aria-expanded="false"
                                aria-controls="multiCollapseExample2">
                            Se tegning 2
                        </button>

                    </p>


                    <div>
                        <div class="collapse multi-collapse" id="multiCollapseExample1">
                            <div class="card card-body">
                                <img src="https://pbs.twimg.com/media/FR9W2eDXoAAMv-j?format=png&name=medium"
                                     width="100rem">

                            </div>
                        </div>
                    </div>
                    <div class="collapse multi-collapse" id="multiCollapseExample2">
                        <div class="card card-body">
                            <img src="https://walfiegif.files.wordpress.com/2021/12/out-transparent-1.gif?w=1000"
                                 width="100rem">
                        </div>
                    </div>
                </div>

                <div class="container align-items-center">

                    <br>
                    <br>


                    <div class="row align-items-start">

                        <div class="col">
                            <div class="input-group mb-3">
                                <label for="height" class="input-group-text">Højde</label>
                                <input type="number" class="form-control" name="height" placeholder="${sessionScope.carportChoices.height}" disabled>
                                <span class="input-group-text" id="height">m</span>
                            </div>
                        </div>
                        <div class="col">
                            <div class="input-group mb-3">
                                <label for="length" class="input-group-text">Længde</label>
                                <input type="number" class="form-control" name="length" placeholder="${sessionScope.carportChoices.length}" disabled>
                                <span class="input-group-text" id="length">m</span>
                            </div>
                        </div>
                        <div class="col">
                            <div class="input-group mb-3">
                                <label for="width" class="input-group-text">Bredde</label>
                                <input type="number" class="form-control" name=width" placeholder="${sessionScope.carportChoices.width}" disabled>
                                <span class="input-group-text" id="width">m</span>
                            </div>
                        </div>


                    </div>

                    <h3>Tag</h3>
                    <div class="row">
                        <div class=" col-md-4">
                            <div class="input-group">
                                <label class="input-group-text" for="materiale">materiale</label>
                                <input type="text" class="form-control" name="materiale" id="materiale"
                                       placeholder="${sessionScope.carportChoices.roofMaterial}" disabled>
                            </div>
                        </div>
                        <br>
                        <div class="col-md-4">
                            <div class="input-group">
                                <label class="input-group-text" for="tag">Tag</label>
                                <input type="text" class="form-control" name="tag" id="tag" placeholder="${sessionScope.carportChoices.roofShape}" disabled>
                            </div>
                        </div>
                        <div class="col-md-4" id="inputCheck1">
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="angle">Hældning</label>
                                <input type="number" class="form-control" name="angle" id="angle" placeholder="${sessionScope.carportChoices.roofAngle}"
                                       disabled>
                                <span class="input-group-text" id="angleSymbol">°</span>
                            </div>
                        </div>
                    </div>
                    <br>
                    <c:if test="${sessionScope.shedChoices != null}">
                    <h3>Skur</h3>

                    <br>


                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            Skur
                        </label>
                    </div>

                    <br>

                    <div class="row align-items-start">
                        <div class="col-md-2">
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="shedLength">Længde</label>
                                <input type="number" class="form-control" name="length" id="shedLength" placeholder="${sessionScope.shedChoices.length}"
                                       aria-label="" aria-describedby="" disabled>
                                <span class="input-group-text" id="shedLengthm">m</span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="shedWidth">Bredde</label>
                                <input type="number" class="form-control" name=width" id="shedWidth" placeholder="${sessionScope.shedChoices.width}"
                                       aria-label="" aria-describedby="" disabled>
                                <span class="input-group-text" id="shedWidthm">m</span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="shedMaterial">Gulvmateriale</label>
                                <input type="text" class="form-control" name="floorMaterial" id="shedMaterial" placeholder="${sessionScope.shedChoices.floorMaterial}"
                                       aria-label="" aria-describedby="" disabled>
                            </div>
                        </div>
                        <br>


                        <br>
                    </div>
                </c:if>
                </div>


                <section>

                    <div class="gy-6 row align-content-start">
                        <!-- Button trigger modal -->
                        <c:if test="${!sessionScope.usersRequest.accepted && !sessionScope.usersRequest.paid}">
                        <div style="text-align: center">
                            <p class="textPaymentMessageDecoration">Din forespørgsel bliver behandlet.</p>
                            <p class="textPaymentMessageDecoration">Når din forespørgsel er blevet accepteret kan du betale på denne side.</p>
                        </div>
                        </c:if>
                        <c:if test="${sessionScope.usersRequest.accepted && !sessionScope.usersRequest.paid}">
                            <div style="text-align: center">
                            <p class="textPaymentMessageDecoration">Din forespørgsel er blevet accepteret!</p>
                            <p class="textPaymentMessageDecoration">Betal nu!</p>
                            </div>

                            <div style="margin-bottom: 5%" class="col">
                                <form class="col">

                                    <a href="${pageContext.request.contextPath}/#" style="margin-left: 48.5%"
                                       class="btn-viewcarportorder-betal">Betal</a>
                                </form>
                            </div>
                            <br>

                        </c:if>

                        <c:if test="${sessionScope.usersRequest.accepted && sessionScope.usersRequest.paid}">

                            <div style="text-align: center">
                                <p class="textPaymentMessageDecoration">Din betaling er modtaget</p>
                                <p class="textPaymentMessageDecoration">Se din stykliste nu!!</p>
                            </div>

                        <div style="margin-bottom: 5%; text-align: right" class="col">
                            <form>
                                <a href="${pageContext.request.contextPath}/#" style="margin-right: 46%"
                                   class="btn-viewcarportorder-betal">Se stykliste</a>
                            </form>
                        </div>
                        </c:if>
                    </div>
                </section>

            </div>
        </div>
    </jsp:body>

</t:pagetemplate>