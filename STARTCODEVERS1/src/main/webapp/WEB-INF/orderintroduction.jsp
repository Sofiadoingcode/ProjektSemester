<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:body>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
        <script>
            $(document).ready(function () {
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
                    <img src="${pageContext.request.contextPath}/images/OrderIntroIMG/CARPORTINTROPAGE2.jpg"
                         class="fogflagImg"/>

                </div>
                <div class="index-text-box">
                    <h3 class="ointro-about-header">KOM GODT I GANG</h3>
                    <br>
                    <p class="index-description">Bliver din bil ofte kold og beskidt, når den står ubeskyttet og alene i
                        regnen?</p>
                    <p class="index-description">Så er det på tide at du giver din bil det halvtag, som den
                        fortjener!</p>
                    <br>
                    <p style="font-size: 2.5rem; line-height: 1; margin-bottom: 3rem">Bestil din carport i dag!</p>


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

                    <form action="fc/showTempUser?command=showTempUser" method="post">

                        <div class="container align-items-center">

                            <br>
                            <br>

                            <form action="fc/generateBom?command=generateBom" method="post">

                                <div class="row align-items-start">

                                    <div class="col">
                                        <div class="input-group mb-3">
                                            <input type="number" class="form-control" name="height"
                                                   value="${requestScope.height}"
                                                   placeholder="Højde"
                                                   aria-label="Recipient's username" aria-describedby="basic-addon2"
                                                   required
                                                   oninvalid="this.setCustomValidity('Indtast højde')"
                                                   oninput="this.setCustomValidity('')"
                                                   step="any"
                                                   min="2"
                                                   max="5">
                                            <span class="input-group-text" id="height">m</span>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="input-group mb-3">
                                            <input type="number" class="form-control" name="length" placeholder="Længde"
                                                   aria-label="Recipient's username" aria-describedby="basic-addon2" value="${requestScope.length}"
                                                   required
                                                   oninvalid="this.setCustomValidity('Indtast længde')"
                                                   oninput="this.setCustomValidity('')"
                                                   step="any"
                                                   min="3"
                                                   max="8">
                                            <span class="input-group-text" id="length">m</span>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="input-group mb-3">
                                            <input type="number" class="form-control" name="width" placeholder="Bredde"
                                                   aria-label="Recipient's username" aria-describedby="basic-addon2" value="${requestScope.width}"
                                                   required
                                                   oninvalid="this.setCustomValidity('Indtast bredde')"
                                                   oninput="this.setCustomValidity('')"
                                                   step="any"
                                                   min="2.5"
                                                   max="8">
                                            <span class="input-group-text" id="width">m</span>
                                        </div>
                                    </div>


                                </div>

                                <h3>Tag</h3>
                                <div class="row">
                                    <div class=" col-md-4">
                                        <div class="input-group">
                                            <label class="input-group-text" for="inputGroupSelect01">materiale</label>
                                            <select class="form-select" name="tagMateriale" id="inputGroupSelect02"
                                                    required
                                                    oninvalid="this.setCustomValidity('Vælg tag materiale')"
                                                    oninput="this.setCustomValidity('')">
                                                <option value="" disabled>Vælg</option>
                                                <option value="Plasttrapez" selected>Plasttrapez</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <label class="input-group-text" for="inputGroupSelect01">Tag</label>
                                            <select class="form-select" name="tag" id="inputGroupSelect01" required
                                                    oninvalid="this.setCustomValidity('Vælg tag')"
                                                    oninput="this.setCustomValidity('')">
                                                <option value="" selected disabled>Vælg</option>
                                                <option value="Flat tag" selected>Flat tag</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-4" id="inputCheck1">
                                        <div class="input-group mb-3">
                                            <label class="input-group-text" for="angle">Hældning</label>
                                            <input type="number" class="form-control" id="angle" name="angle"
                                                   placeholder="0" value="${requestScope.angle}"
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
                                    <input class="form-check-input" type="checkbox" name="shedCheckbox" value="shed"
                                           id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Skur
                                    </label>
                                </div>

                                <br>

                                <div class="row align-items-start">
                                    <div class="col-md-2">
                                        <div class="input-group mb-3">
                                            <input type="number" class="form-control" name="shedLength"
                                                   placeholder="Længde"
                                                   aria-label="" aria-describedby="">
                                            <span class="input-group-text" id="shedLength">m</span>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group mb-3">
                                            <input type="number" class="form-control" name="shedWidth"
                                                   placeholder="Bredde"
                                                   aria-label="" aria-describedby="">
                                            <span class="input-group-text" id="shedWidth">m</span>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" name="floorMaterial"
                                                   placeholder="Gulvmateriale"
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
                                                       placeholder="Password" value="${requestScope.name}"
                                                       required
                                                       oninvalid="this.setCustomValidity('Indtast navn')"
                                                       oninput="this.setCustomValidity('')">
                                                <label for="floatingName">Navn</label>
                                            </div>

                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-floating mb-3">
                                                <input type="email" class="form-control" id="floatingInput" name="email"
                                                       placeholder="name@example.com" value="${requestScope.email}"
                                                       required
                                                       oninvalid="this.setCustomValidity('Indtast email')"
                                                       oninput="this.setCustomValidity('')">
                                                <label for="floatingInput">Email adresse</label>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-floating mb-3">
                                                <input type="text" pattern="[0-9]{4}" class="form-control"
                                                       id="floatingzipCode" name="zipCode" placeholder="" value="${requestScope.zipCode}"
                                                       required
                                                       oninvalid="this.setCustomValidity('Indtast postnummer')"
                                                       oninput="this.setCustomValidity('')">
                                                <label for="floatingzipCode">Postnummer</label>
                                            </div>
                                        </div>
                                        <div class="col-md-2">
                                            <div class="form-floating mb-3">
                                                <input type="text" pattern="[0-9]{8}" class="form-control"
                                                       id="floatingPhoneNumber" name="phoneNumber" placeholder="" value="${requestScope.phoneNumber}"
                                                       required
                                                       oninvalid="this.setCustomValidity('Indtast telefonnummer')"
                                                       oninput="this.setCustomValidity('')">
                                                <label for="floatingPhoneNumber">Telefonnummer</label>
                                            </div>
                                        </div>


                                    </div>
                                    <br>
                                    <div class="gy-6 row align-content-start">
                                        <!-- Button trigger modal -->
                                        <div class="col buttonBoxOI">
                                            <div class="divMinimizer">
                                                <button type="submit" formaction="fc/generateBom?command=generateBom"
                                                        formmethod="post" class="SeeDrawingButton">
                                                    Se tegning
                                                </button>
                                            </div>
                                            <div style="">
                                                <button type="submit" class="SubmitButton"
                                                        formaction="fc/showTempUser?command=showTempUser"
                                                        formmethod="post">
                                                    Send Forespørgsel
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </form>

                        </div>


                    </form>
                </div>
                <br>
                <div class="index-box">
                    <div class="">
                        <p>
                                ${requestScope.svg}
                        </p>
                    </div>
                    <div>
                        <p class="index-description">${requestScope.description}</p>
                    </div>
                </div>

                <!-- Modal -->
                <c:if test="${requestScope.tempUser!=null}">
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 style="font-size: 25px" class="modal-title" id="exampleModalLabel">Tak for din
                                        forespørgsel</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div style="font-size: 12px" class="modal-body">
                                    Din forespørgsel er nu blevet sendt!
                                    <br>
                                    Nedenfor ses en midlertidig konto, hvor du kan se specifikationerne til din carport.
                                    Når en af vores kollegaer har accepteret carporten, skal du betale via denne konto
                                    og
                                    så sender vi dig materialerne!
                                    <br>
                                    <div style="font-size: 23px">
                                        Konto:
                                    </div>
                                    <div style="font-size: 18px">
                                        Brugernavn: ${requestScope.tempUser.username}
                                        <br>
                                        Kodeord: ${requestScope.tempUser.password}
                                    </div>
                                    <div style="font-size: 10px; color: red">
                                        Husk dit login!
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <form>
                                        <button type="submit" class="btn btn-primary" name="loginCredentials"
                                                formaction="fc/tempLogin?command=tempLogin" formmethod="post"
                                                value="${requestScope.tempUser.username} ${requestScope.tempUser.password}">
                                            Log Ind
                                        </button>
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

                <br>
            </div>


        </section>


    </jsp:body>

</t:pagetemplate>