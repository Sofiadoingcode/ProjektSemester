<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
            Log ind
    </jsp:attribute>

    <jsp:body>


        <h1 id="logInHeader">Log ind</h1>
        <div id="loginForm">
        <form action="fc/login" method="post">
            <input type="hidden" name="command" value="login"/>
            <label for="username">Brugernavn: </label>
            <input type="text" id="username" name="username" required
                   oninvalid="this.setCustomValidity('Indtast brugernavn')"
                   oninput="this.setCustomValidity('')"/>
            <label id="passwordLabel" for="password">Kodeord: </label>
            <input type="password" id="password" name="password" required
                   oninvalid="this.setCustomValidity('Indtast kodeord')"
                   oninput="this.setCustomValidity('')"/>
            <input type="submit" id="login-btn"  value="Log ind"/>
        </form>
        </div>
        <div>
        <img id="loginFlag" src="images/FogFlag.png">
        </div>

    </jsp:body>
</t:pagetemplate>