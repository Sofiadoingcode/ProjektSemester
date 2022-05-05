
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

        <jsp:body>
                <section class="ointro-header-section">
                        <div class="ointro-header-box">

                                <h1 class="ointro-header">BYG DIN EGEN CARPORT</h1>

                        </div>
                </section>



                <section class="ointro-about-section">
                        <div class="index-box">
                                <div class="index-image-box">
                                        <img src="${pageContext.request.contextPath}/images/OrderIntroIMG/CARPORTINTROPAGE2.jpg" class="fogflagImg"/>

                                </div>
                                <div class="index-text-box">
                                        <h3 class="ointro-about-header">KOM GODT I GANG</h3>
                                        <p class="index-description"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum hase like Aldus PageMaker including versions of Lorem Ipsum.</p>


                                </div>

                        </div>
                </section>

                <section class="ointro-form-section">

                        <div class="ointro-form-box">
                                <div class="form-box-header-box">
                                        <br>
                                        <h2 class="form-box-header">UDFYLD SKEMAET OG SE DIN CARPORT</h2>

                                </div>

                                <form action="fc/#" method="post">
                                        <div class="container mt-3">
                                                <div class="row mt-4">
                                                        <div class="col">
                                                                <input type="text">

                                                        </div>

                                                        <div class="col">

                                                        </div>

                                                        <div class="col">

                                                        </div>
                                                </div>
                                        </div>

                                        <div class="ointro-buttons">


                                        </div>
                                </form>



                        </div>


                </section>







        </jsp:body>

</t:pagetemplate>