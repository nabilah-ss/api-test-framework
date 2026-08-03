package io.github.nabilahss.client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingClient {

    public Response getBookings() {
        return given()
                .when()
                .get("/booking");
    }

    public Response getBooking(int id) {
        return given()
                .when()
                .get("/booking/" + id);
    }
}