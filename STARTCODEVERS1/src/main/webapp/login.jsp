<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Log ind
    </jsp:attribute>

    <jsp:attribute name="footer">
            Log ind
    </jsp:attribute>

    <jsp:body>
        <!DOCTYPE html>
        <html lang="da">
        <head>
        <link rel="stylesheet" href="/css/login.css">
        </head>
        <body>
        <div>
        <form action="fc/login" method="post">
            <input type="hidden" name="command" value="login" required/>
            <label for="username">Brugernavn: </label>
            <input type="text" id="username" name="username"/>
            <label for="password">Kodeord: </label>
            <input type="password" id="password" name="password" required/>
            <input type="submit"  value="Log ind"/>
        </form>
        <img src="images/FogFlag.png">
        </div>
        </body>
    </jsp:body>
</t:pagetemplate>