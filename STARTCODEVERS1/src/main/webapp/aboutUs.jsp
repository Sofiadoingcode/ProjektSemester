<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="footer">
            About us
    </jsp:attribute>

    <jsp:body>
        <section class="aboutUs-section">
            <div>
                <div class="centerText">
                    <h2 class="aboutUsPageHeader">OLSKER CUPCAKES</h2>
                </div>

                <br>
                <br>
                <section>
                    <div class="positionCards">
                        <div class="card" class="sideCardsSize">
                            <img src="images/AboutUs/CupcakeProject-Baker.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h4 class="card-title">Our Beloved Baker</h4>
                                <p class="card-text">This is not only our wonderful baker,
                                    but it is also the grandson of a priceless man. You see, his grandfather is the
                                    founder
                                    of Olsker Cupcakes. <br>He fought through tough
                                    terrain
                                    and wild animals to bring home the very recipe that makes our cupcakes, even to this
                                    day!
                                </p>
                                <p>Email: <strong>CoolBaker@gmail.com</strong></p>
                                <p>Tlf: <strong>8591-8493</strong></p>
                            </div>
                        </div>
                        <div class="card" class="middleCardSize">
                            <img src="images/AboutUs/CashRegi.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h4 class="card-title">Cash Register Employee</h4>
                                <p class="card-text">This mighty woman is one of the leading cash register workers in
                                    not
                                    only the country, but the entire continent. With her bare eyes, she can spot fake
                                    dollar
                                    bills over a mile away.</p>
                                <p>Email: <strong>MoneyDealer@hotmail.com</strong></p>
                                <p>Tlf: <strong>4201-0952</strong></p>
                            </div>
                        </div>
                        <div class="card" class="sideCardsSize">
                            <img src="images/AboutUs/CupcakeProject-Assistant.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h4 class="card-title">Assistant Secretary</h4>
                                <p class="card-text">Our lives could not continue to flourish, if not for our wonderful
                                    assistant. She is the clue that holds the entire establishment together and running.
                                    <br>Whether we need new supplies of deliveries or a customer needs advice in the
                                    middle
                                    of the night, she'll be there to take your call!</p>
                                <p>Email: <strong>OnTheLine@coldmail.com</strong></p>
                                <p>Tlf: <strong>4619-1952</strong></p>
                            </div>
                        </div>
                        <div class="shapeAroundAboutUsText">
                            <p class="aboutUsPageText"> Olsker Cupcakes is a great place for the whole family. You could
                                be
                                out celebration a birthday,
                                <br>enjoying valentines day or just in need of a little snack after a hard day of work.
                                <br>No matter what, you will always feel welcome in our humble shop.
                                <br>
                                <br>So if you are ever in need of joy, delicately placed within a cute little
                                cupcake-form with sprinkles and a cherry on top, feel free to make an order and see your
                                dreams come true.
                            </p>
                        </div>
                    </div>
                </section>


            </div>

        </section>
        <section class="aboutUs-FooterSection">
            <div style="float: right">

                <a href="https://www.twitch.tv/sofiastunts">
                    <img alt="Link to twitch" src="images/SocialMedia/TwitchIcon.png"
                         width=60" height="40"></a>

                <a href="https://www.twitter.com/sofiastunts">
                    <img alt="Link to twitter" src="images/SocialMedia/Twitter-icon.png"
                         width=50" height="40"></a>

                <a href="https://www.instagram.com/sofiadoingstunt">
                    <img alt="Link to instagram" src="images/SocialMedia/Instagram-icon.png"
                         width=50" height="40"></a>

                <a href="https://discord.com/6FHtZCQsTC">
                    <img alt="Link to discord" src="images/SocialMedia/Discord-icon.png"
                         width=50" height="40"></a>


            </div>
        </section>
        <br>
        <br>
    </jsp:body>
</t:pagetemplate>