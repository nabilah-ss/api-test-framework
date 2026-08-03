package io.github.nabilahss.tests;

import io.github.nabilahss.client.BookingClient;
import io.github.nabilahss.config.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class BookingTest extends BaseTest {

    private final BookingClient bookingClient = new BookingClient();

    @Test
    public void getBookingByIdReturnsEchoedBookingId() {
        Response response = bookingClient.getBooking(99);
        response.then()
                .statusCode(200)
                .body("bookingid", equalTo("99"))
                .body("firstname", equalTo("Sally"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true));
    }

    @Test
    public void getBookingByIdReturnsBookingDates() {
        Response response = bookingClient.getBooking(42);
        response.then()
                .statusCode(200)
                .body("bookingdates.checkin", equalTo("2013-02-23"))
                .body("bookingdates.checkout", equalTo("2014-10-23"))
                .body("additionalneeds", equalTo("Breakfast"));
        verifyRequest("GET", "/booking/2");
    }
}