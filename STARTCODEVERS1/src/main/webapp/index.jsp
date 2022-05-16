<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>


    <jsp:body>
        <div class="container mt-3 footer-box index-header-section">
            <div class="row mt-4 index-header-box">
                <div class="col index-col-header">
                    <h1 class="index-heading-primary">BOLIG OG DESIGN</h1>
                </div>

                <div class="col index-logoimg22-box index-col-header">
                    <img src="${pageContext.request.contextPath}/images/FrontPageIMG/FOGLOGO22.png" class="foglogo22"/>
                </div>

                <div class="col index-col-header">
                    <h1 class="index-heading-primary">TRÆLAST OG BYGGECENTER</h1>
                </div>
            </div>
        </div>


        <div class="index-section">
            <div class="index-box">
                <div class="index-text-box">
                    <p class="index-description"> Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum hase like Aldus PageMaker including versions of Lorem Ipsum.</p>
                    <c:if test="${sessionScope.user == null }">
                        <a href="${pageContext.request.contextPath}/orderintroduction.jsp" class="btn-index btn-index--full">Lav din egen Carport</a>

                        <a href="${pageContext.request.contextPath}/login.jsp" class="btn-index btn-index--login">Log Ind</a>
                    </c:if>


                </div>
                <div class="index-image-box">
                    <img src="${pageContext.request.contextPath}/images/FrontPageIMG/fogFlagImg.JPG" class="fogflagImg"/>

                </div>
            </div>
        </div>

        <!-- Gallery -->
        <div class="index-gallery-section">
            <div class="row">
                <div class="col-lg-4 col-md-12 mb-4 mb-lg-0">
                    <img
                            src="${pageContext.request.contextPath}/images/FrontPageIMG/CARPORT4.JPG"
                            class="w-100 shadow-1-strong rounded mb-4"
                            alt="Boat on Calm Water"
                    />
                    
                </div>

                <div class="col-lg-4 mb-4 mb-lg-0">
                    <img
                            src="${pageContext.request.contextPath}/images/FrontPageIMG/CARPORT3.JPG"
                            class="w-100 shadow-1-strong rounded mb-4"
                            alt="Mountains in the Clouds"
                    />

                </div>

                <div class="col-lg-4 mb-4 mb-lg-0">
                    <img
                            src="${pageContext.request.contextPath}/images/FrontPageIMG/CARPORT1.JPG"
                            class="w-100 shadow-1-strong rounded mb-4"
                            alt="Waves at Sea"
                    />

                </div>
            </div>
        </div>
        <!-- Gallery -->


    </jsp:body>

</t:pagetemplate>