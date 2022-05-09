<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontpage.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/orderintro.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/requestlistpage.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald&family=Roboto:wght@300&family=Rubik:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />



    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #193f76; font-size: 13px">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}/images/fogLogo.png" width="85px;" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/requestList?command=requestList" style="color: white; padding-left: 25px; padding-right: 25px;">Anmodninger</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/about?command=about" style="color: white; padding-left: 25px; padding-right: 25px;">Om os</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/orderintroduction.jsp" style="color: white; padding-left: 25px; padding-right: 25px;">Bestil carport</a>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc" style="color: white; padding-left: 25px; padding-right: 25px;">Kontakt os</a>
                    <c:if test="${sessionScope.user == null }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp" style="color: white; padding-left: 25px; padding-right: 15px;">Login</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/logout?command=logout" style="color: white; padding-left: 15px; padding-right: 15px;">Log out</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px; min-width: 100%">
    <h1><jsp:invoke fragment="header"/></h1>
    <jsp:doBody/>
</div>
<br>
<br>

<!-- Footer -->
<footer class="footer">


        <div class="container mt-3 footer-box" >

            <br>

            <hr/>
            <div class="row mt-4">
                <div class="col">
                    <h3 class="footer-headers">&copy; JOHANNES FOG A/S</h3>
                    <br>
                    <div class="footer-allcontact-box">
                        <div class="footer-symbols-box">
                            <div class="email-box">
                                <span class="material-symbols-outlined">
                                    call
                                </span>
                            </div>
                            <div>
                                <span class="material-symbols-outlined">
                                    mail
                                </span>
                            </div>



                        </div>

                        <div class="footer-contact-box">
                            <div class="email-box">
                                <h3>45 87 10 01</h3>
                            </div>
                            <div class="email-box-ext">
                                <h4>Send en Email</h4>
                            </div>

                        </div>

                    </div>

                    <div>
                        <h4 class="footer-headers footer-address-box">Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby</h4>
                    </div>

                </div>

                <div class="col">
                    <h3 class="footer-headers">AKTUELT</h3>
                    <p>

                        Tilbudsaviser<br>
                        Nej Tak+<br>
                        Tilbudsmail<br>
                        Konkurrencer<br>
                        Facebook<br>
                        Instagram<br>
                        LinkedIn<br>

                    </p>
                </div>

                <div class="col">
                    <h3 class="footer-headers">OM OS</h3>

                    Åbningstider<br>
                    Udlejning<br>
                    Om Fog<br>
                    Fogs Fond<br>
                    Job<br>
                    CSR<br>
                    Cookie-politik<br>
                    Persondata<br>
                    Prismatch<br>
                    Kontakt Fog<br>


                </div>

            </div>

            <br>
            <hr/>

            <br>
            <br>

        </div>



</footer>





    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>



</body>
</html>
