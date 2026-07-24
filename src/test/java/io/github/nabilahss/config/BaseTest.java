package io.github.nabilahss.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
}