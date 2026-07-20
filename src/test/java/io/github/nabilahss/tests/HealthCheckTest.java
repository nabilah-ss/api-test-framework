package io.github.nabilahss.tests;

import io.github.nabilahss.config.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HealthCheckTest extends BaseTest {

    @Test
    public void pingReturns201() {

        given()
                .when()
                    .get("/ping")
                .then()
                    .statusCode(201);
    }

    @Test
    public void getBookingIdsReturnsNonEmptyList() {

        given()
                .when()
                    .get("/booking")
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0))
                    .body("[0].bookingid", notNullValue());
    }
}
