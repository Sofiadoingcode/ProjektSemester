<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <div class="container">

            <form>
                <div class="row align-items-start">

                    <div class="col">
                        <div class="input-group mb-3">
                            <input type="number" class="form-control" name="height" placeholder="højde"
                                   aria-label="Recipient's username" aria-describedby="basic-addon2">
                            <span class="input-group-text" id="height">m</span>
                        </div>
                    </div>
                    <div class="col">
                        <div class="input-group mb-3">
                            <input type="number" class="form-control" name="length" placeholder="længde"
                                   aria-label="Recipient's username" aria-describedby="basic-addon2">
                            <span class="input-group-text" id="length">m</span>
                        </div>
                    </div>
                    <div class="col">
                        <div class="input-group mb-3">
                            <input type="number" class="form-control" name=width" placeholder="brede"
                                   aria-label="Recipient's username" aria-describedby="basic-addon2">
                            <span class="input-group-text" id="width">m</span>
                        </div>
                    </div>


                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="input-group col-md-4">
                            <label class="input-group-text" for="inputGroupSelect01">Tag</label>
                            <select class="form-select" name="tag" id="inputGroupSelect01">
                                <option selected>Choose</option>
                                <option value="1">Flat tag</option>
                                <option value="2">Tag med resning</option>
                                <option value="3">Three</option>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="container gy-6 row">
                    <!-- Button trigger modal -->
                    <div class="col">
                        <button type="button" class=" btn-primary" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            Se tegning
                        </button>
                    </div>

                    <div class="col">
                        <input type="submit" value="Submit">
                    </div>


                </div>
            </form>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Insert carport tegning here
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>



        <script>

            /*
                        function off(tag){
                        let x;
                        if (tag==1){
                            x = "<input class="form-control" type="text" placeholder="Disabled input" aria-label="Disabled input example" disabled>";


                        }}
            */

        </script>
    </jsp:body>
</t:pagetemplate>>