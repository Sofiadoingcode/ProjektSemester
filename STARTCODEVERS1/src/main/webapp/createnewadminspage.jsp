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
        <section>

            <div style="text-align: center">

                <form>

                    <p style="font-size: 3.4rem; font-weight: 500">Kontokonfiguration</p>
                    <br>
                    <input style="margin-left: 10px" type="text" class="UsernameInputField" id="username"
                           name="username"
                           placeholder=" Username" required
                           oninvalid="this.setCustomValidity('Indtast brugernavn')"
                           oninput="this.setCustomValidity('')"/>
                    <br>
                    <input style="margin-left: 10px" type="password" class="PasswordInputField" id="password"
                           name="password"
                           placeholder=" Password" required
                           oninvalid="this.setCustomValidity('Indtast kodeord')"
                           oninput="this.setCustomValidity('')"/>
                    <br>
                    <button type="submit" style="" formaction="fc/createAdminAccount?command=createAdminAccount"
                            formmethod="post" class="btn-createnewadminspage-betal">Lav konto
                    </button>

                    <button type="submit" name="delete" value="${sessionScope.user.idUser}"
                            formaction="fc/deleteAccount?command=deleteAccount" formmethod="post"
                            class="btn-createnewadminspage-delete">Slet konto
                    </button>
                </form>
            </div>
        </section>

    </jsp:body>

</t:pagetemplate>