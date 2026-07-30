package io.github.nabilahss.tests;

import io.github.nabilahss.config.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookingTest extends BaseTest {

    @Test
    public void getBookingByIdReturnsExpectedFields() {
        given()
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Sally"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true));
    }

    @Test
    public void getBookingByIdReturnsBookingDates() {
        given()
                .when()
                .get("/booking/42")
                .then()
                .statusCode(200)
                .body("bookingdates.checkin", equalTo("2013-02-23"))
                .body("bookingdates.checkout", equalTo("2014-10-23"))
                .body("additionalneeds", equalTo("Breakfast"));
    }
}