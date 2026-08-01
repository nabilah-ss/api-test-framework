package io.github.nabilahss.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class BaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() throws IOException {
        String baseUrl = System.getenv("BASE_URL");

        if (baseUrl == null || baseUrl.isBlank()) {
            Properties props = new Properties();
            try (InputStream is = getClass().getClassLoader()
                    .getResourceAsStream("config.properties")) {
                props.load(is);
            }
            baseUrl = props.getProperty("base.uri");
        }

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.requestSpecification = requestSpec;
    }

    protected void verifyRequest(String method, String urlPattern) {
        String wiremockAdmin = System.getenv("WIREMOCK_ADMIN");
        if (wiremockAdmin == null || wiremockAdmin.isBlank()) {
            wiremockAdmin = "http://localhost:8080"; // local fallback
        }

        String verifyUrl = wiremockAdmin + "/__admin/requests/find";
        String requestJson = String.format(
                "{\"method\":\"%s\",\"urlPattern\":\"%s\"}",
                method, urlPattern
        );

        given()
                .body(requestJson)
                .contentType(ContentType.JSON)
                .when()
                .post(verifyUrl)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}