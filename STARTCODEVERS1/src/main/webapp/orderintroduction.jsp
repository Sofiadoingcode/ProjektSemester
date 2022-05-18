
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

        <jsp:body>
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
                <script>
                        $(document).ready(function(){
                                $("#exampleModal").modal('show');
                        });
                </script>
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

                                        <form action="fc/generateBom?command=generateBom" method="post">

                                                <div class="row align-items-start">

                                                        <div class="col">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="height" placeholder="højde"
                                                                               aria-label="Recipient's username" aria-describedby="basic-addon2" required
                                                                               oninvalid="this.setCustomValidity('Indtast højde')"
                                                                               oninput="this.setCustomValidity('')">
                                                                        <span class="input-group-text" id="height">m</span>
                                                                </div>
                                                        </div>
                                                        <div class="col">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="length" placeholder="længde"
                                                                               aria-label="Recipient's username" aria-describedby="basic-addon2" required
                                                                               oninvalid="this.setCustomValidity('Indtast længde')"
                                                                               oninput="this.setCustomValidity('')">
                                                                        <span class="input-group-text" id="length">m</span>
                                                                </div>
                                                        </div>
                                                        <div class="col">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="width" placeholder="bredde"
                                                                               aria-label="Recipient's username" aria-describedby="basic-addon2" required
                                                                               oninvalid="this.setCustomValidity('Indtast bredde')"
                                                                               oninput="this.setCustomValidity('')">
                                                                        <span class="input-group-text" id="width">m</span>
                                                                </div>
                                                        </div>


                                                </div>

                                                <h3>Tag</h3>
                                                <div class="row">
                                                        <div class=" col-md-4">
                                                                <div class="input-group">
                                                                        <label class="input-group-text" for="inputGroupSelect01">materiale</label>
                                                                        <select class="form-select" name="tagMateriale" id="inputGroupSelect02" required
                                                                                oninvalid="this.setCustomValidity('Vælg tag materiale')"
                                                                                oninput="this.setCustomValidity('')">
                                                                                <option value="" selected disabled>Choose</option>
                                                                                <option value="Plasttrapez">Plasttrapez</option>
                                                                                <option value="Ståltag">Ståltag</option>
                                                                                <option value="Betontag">Betontag</option>

                                                                        </select>
                                                                </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-4">
                                                                <div class="input-group">
                                                                        <label class="input-group-text" for="inputGroupSelect01">Tag</label>
                                                                        <select class="form-select" name="tag" id="inputGroupSelect01" required
                                                                                oninvalid="this.setCustomValidity('Vælg tag')"
                                                                                oninput="this.setCustomValidity('')">
                                                                                <option value="" selected disabled>Choose</option>
                                                                                <option value="Flat tag">Flat tag</option>
                                                                                <option value="Tag med rejsning">Tag med rejsning</option>
                                                                                <option value="Three">Three</option>
                                                                        </select>
                                                                </div>
                                                        </div>
                                                        <div class="col-md-4" id="inputCheck1">
                                                                <div class="input-group mb-3">
                                                                        <label class="input-group-text" for="angle">Hældning</label>
                                                                        <input type="number" class="form-control" id="angle" name="angle" placeholder="0"
                                                                               aria-label="" aria-describedby="" required
                                                                               oninvalid="this.setCustomValidity('Indtast hældning på tag')"
                                                                               oninput="this.setCustomValidity('')">
                                                                        <span class="input-group-text" id="angleSymbol">°</span>
                                                                </div>
                                                        </div>
                                                </div>
                                                <br>

                                                <h3>Skur</h3>

                                                <br>


                                                <div class="form-check">
                                                        <input class="form-check-input" type="checkbox" name="shedCheckbox" value="shed" id="flexCheckDefault">
                                                        <label class="form-check-label" for="flexCheckDefault">
                                                                Skur
                                                        </label>
                                                </div>

                                                <br>

                                                <div class="row align-items-start">
                                                        <div class="col-md-2">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="shedLength" placeholder="længde"
                                                                               aria-label="" aria-describedby="">
                                                                        <span class="input-group-text" id="shedLength">m</span>
                                                                </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                                <div class="input-group mb-3">
                                                                        <input type="number" class="form-control" name="shedWidth" placeholder="bredde"
                                                                               aria-label="" aria-describedby="">
                                                                        <span class="input-group-text" id="shedWidth">m</span>
                                                                </div>
                                                        </div>
                                                        <div class="col-md-2">
                                                                <div class="input-group mb-3">
                                                                        <input type="text" class="form-control" name="floorMaterial" placeholder="Gulvmateriale"
                                                                               aria-label="" aria-describedby="">
                                                                </div>
                                                        </div>

                                                        <br>



                                                        <br>
                                                        <h3>Personlig information</h3>

                                                        <div class="row">
                                                                <div class="col-md-2">
                                                                        <div class="form-floating">
                                                                                <input type="text" class="form-control" name="name" id="floatingName"
                                                                                       placeholder="Password" required
                                                                                       oninvalid="this.setCustomValidity('Indtast navn')"
                                                                                       oninput="this.setCustomValidity('')">
                                                                                <label for="floatingName">Navn</label>
                                                                        </div>

                                                                </div>
                                                                <div class="col-md-2">
                                                                        <div class="form-floating mb-3">
                                                                                <input type="email" class="form-control" id="floatingInput" name="email" placeholder="name@example.com" required
                                                                                       oninvalid="this.setCustomValidity('Indtast email')"
                                                                                       oninput="this.setCustomValidity('')">
                                                                                <label for="floatingInput">Email address</label>
                                                                        </div>
                                                                </div>
                                                                <div class="col-md-2">
                                                                        <div class="form-floating mb-3">
                                                                                <input type="text" pattern="[0-9]{4}" class="form-control" id="floatingzipCode" name="zipCode" placeholder="" required
                                                                                       oninvalid="this.setCustomValidity('Indtast postnummer')"
                                                                                       oninput="this.setCustomValidity('')">
                                                                                <label for="floatingzipCode">Postnummer</label>
                                                                        </div>
                                                                </div>
                                                                <div class="col-md-2">
                                                                        <div class="form-floating mb-3">
                                                                                <input type="text" pattern="[0-9]{8}" class="form-control" id="floatingPhoneNumber" name="phoneNumber" placeholder="" required
                                                                                       oninvalid="this.setCustomValidity('Indtast telefonnummer')"
                                                                                       oninput="this.setCustomValidity('')">
                                                                                <label for="floatingPhoneNumber">Telefonnummer</label>
                                                                        </div>
                                                                </div>


                                                        </div>
                                                        <br>
                                                        <div class="gy-6 row align-content-start">
                                                                <!-- Button trigger modal -->
                                                                <div class="col">
                                                                        <button type="submit" class=" btn-primary" >
                                                                                Se tegning
                                                                        </button>
                                                                </div>

                                                                <div class="col">

                                                                        <button type="submit" class="">
                                                                                Submit
                                                                        </button>
                                                                </div>


                                                        </div>
                                                </div>

                                        </form>
                                </div>

                                <!-- Modal -->
                                <c:if test="${requestScope.tempUser!=null}">
                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                                <div class="modal-content">
                                                        <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Tak for din forespørgsel</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                                Brugernavn: ${requestScope.tempUser.username}
                                                                <br>
                                                                Kodeord: ${requestScope.tempUser.password}
                                                        </div>
                                                        <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                <form>
                                                                <button type="submit" class="btn btn-primary" name="loginCredentials" formaction="fc/tempLogin?command=tempLogin" formmethod="post" value="${requestScope.tempUser.username} ${requestScope.tempUser.password}">Login</button>
                                                                </form>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                                </c:if>
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








        </jsp:body>

</t:pagetemplate>