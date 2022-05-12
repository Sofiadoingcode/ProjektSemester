<%--
  Created by IntelliJ IDEA.
  User: Micky
  Date: 05-05-2022
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
                Requests site
    </jsp:attribute>

    <jsp:body>
        <section style="text-align: center">

            <form action="fc/createAdminAccount" method="post" style="text-align: center">

                <p style="font-size: 3.4rem; font-weight: 500">Kontokonfiguration</p>
                <br>
                <input style="margin-left: 10px" type="text" class="UsernameInputField" id="username" name="username"
                       placeholder=" Username"/>
                <br>
                <input style="margin-left: 10px" type="password" class="PasswordInputField" id="password"
                       name="password" placeholder=" Password"/>
                <br>
                <input type="submit" style="" class="btn-createnewadminspage-betal" value="Lav Konto">
            </form>

            <input type="submit" style="" class="btn-createnewadminspage-betal" value="Lav Konto">
        </section>

    </jsp:body>

</t:pagetemplate>