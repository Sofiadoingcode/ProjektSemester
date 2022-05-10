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

            <div class="row">
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



            <div class="gy-6 row align-content-start">
                <!-- Button trigger modal -->
                <div class="col">
                    <button type="submit" class=" btn-primary" value="Køb" >
                            Køb
                    </button>
                </div>

                <div class="col">
                    <input type="submit" class=" btn-primary" value="Submit">
                </div>


            </div>

        </div>
</div>
    </jsp:body>

</t:pagetemplate>