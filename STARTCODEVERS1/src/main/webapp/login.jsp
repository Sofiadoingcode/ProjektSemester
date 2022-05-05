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

        <div id="login-div-login">
            <div id="loginForm-div">
            <div id="logInHeader-login">
                <h1 id="logInHeaderText">Log ind</h1>
            </div>
            <div id="loginForm-login">
                <div action="fc/login" method="post">
                    <input type="hidden" name="command" value="login"/>
                    <div id="loginFormUser">
                        <label class="loginFormText" for="username">Brugernavn: </label>
                        <input type="text" id="username" name="username" required
                        oninvalid="this.setCustomValidity('Indtast brugernavn')"
                        oninput="this.setCustomValidity('')"/>
                    </div>
                    <div id="loginFormPass">
                        <label id="passwordLabel" class="loginFormText" for="password">Kodeord: </label>
                        <input type="password" id="password" name="password" required
                        oninvalid="this.setCustomValidity('Indtast kodeord')"
                        oninput="this.setCustomValidity('')"/>
                    </div>
                    <div id="login-btn-login-div">
                        <input type="submit" id="login-btn" class="loginFormText" value="Log ind"/>
                    </div>
                </form>
                </div>
            </div>
            </div>
                <div id="loginFlag-div">
                    <img id="loginFlag" src="images/FogFlag.png">
                </div>

        </div>
    </jsp:body>
</t:pagetemplate>