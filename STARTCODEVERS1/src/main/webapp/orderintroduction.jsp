
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
                                        <h2 class="form-box-header">DESIGN DIN EGEN CARPORT</h2>

                                </div>

                                <div class="container align-items-center">

                                        <br>
                                        <br>

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
                                                                        <input type="number" class="form-control" name=width" placeholder="bredde"
                                                                               aria-label="Recipient's username" aria-describedby="basic-addon2">
                                                                        <span class="input-group-text" id="width">m</span>
                                                                </div>
                                                        </div>


                                                </div>

                                                <h3>Tag</h3>
                                                <div class="row">
                                                        <div class=" col-md-4">
                                                                <div class="input-group">
                                                                        <label class="input-group-text" for="inputGroupSelect01">materiale</label>
                                                                        <select class="form-select" name="tag" id="inputGroupSelect02">
                                                                                <option selected>Choose</option>
                                                                                <option value="1">plasttrapez</option>
                                                                                <option value="2">ståltag</option>
                                                                                <option value="3">betontag</option>

                                                                        </select>
                                                                </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-4">
                                                                <div class="input-group">
                                                                        <label class="input-group-text" for="inputGroupSelect01">Tag</label>
                                                                        <select class="form-select" name="tag" id="inputGroupSelect01">
                                                                                <option selected>Choose</option>
                                                                                <option value="1">Flat tag</option>
                                                                                <option value="2">Tag med resning</option>
                                                                                <option value="3">Three</option>
                                                                        </select>
                                                                </div>
                                                        </div>
                                                        <div class="col-md-4" id="inputCheck1">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="angle" placeholder="0"
                                                                               aria-label="" aria-describedby="">
                                                                        <span class="input-group-text" id="width">angle</span>
                                                                </div>
                                                        </div>
                                                </div>
                                                <br>

                                                <h3>Skur</h3>

                                                <br>


                                                <div class="form-check">
                                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                                        <label class="form-check-label" for="flexCheckDefault">
                                                                Skur
                                                        </label>
                                                </div>

                                                <br>

                                                <div class="row align-items-start">
                                                        <div class="col-md-2">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="length" placeholder="længde"
                                                                               aria-label="" aria-describedby="">
                                                                        <span class="input-group-text" id="shedLength">m</span>
                                                                </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name=width" placeholder="brede"
                                                                               aria-label="" aria-describedby="">
                                                                        <span class="input-group-text" id="shedWidth">m</span>
                                                                </div>
                                                        </div>

                                                        <br>



                                                        <br>
                                                        <h3>Personlig information</h3>

                                                        <div class="row">
                                                                <div class="col-md-2">
                                                                        <div class="form-floating">
                                                                                <input type="text" class="form-control" name="name" id="floatingName"
                                                                                       placeholder="Password">
                                                                                <label for="floatingName">Navn</label>
                                                                        </div>

                                                                </div>
                                                                <div class="col-md-2">
                                                                        <div class="form-floating mb-3">
                                                                                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                                                                                <label for="floatingInput">Email address</label>
                                                                        </div>
                                                                </div>


                                                        </div>
                                                        <br>
                                                        <div class="gy-6 row align-content-start">
                                                                <!-- Button trigger modal -->
                                                                <div class="col">
                                                                        <button type="button" class=" btn-primary" >
                                                                                Se tegning
                                                                        </button>
                                                                </div>

                                                                <div class="col">

                                                                        <button type="button" class="" data-bs-toggle="modal"
                                                                                data-bs-target="#exampleModal">
                                                                                Submit
                                                                        </button>
                                                                </div>


                                                        </div>
                                                </div>

                                        </form>
                                </div>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                                <div class="modal-content">
                                                        <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Tak for din forespørgelse</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                                Kodeord og brugernavn...
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



                        </div>


                </section>









        </jsp:body>

</t:pagetemplate>