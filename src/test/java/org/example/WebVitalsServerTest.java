package org.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WebVitalsServerTest {

    @Test
    public void testGetWVWithId() {
        given()
                .pathParam("id", "123")
                .when()
                .get("/metrics/{id}")
                .then()
                .statusCode(200)
                .body(is("")); // Assert the initial state is an empty list
    }

    @Test
    public void testReceiveWVWithId() {
        given()
                .pathParam("id", "123")
                .body("{\"a\": 1}")
                .contentType(ContentType.JSON)
                .when()
                .post("/metrics/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", is("success"));

        given()
                .pathParam("id", "123")
                .when()
                .get("/metrics/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].a", is(1)); // Assert that the received data is stored in the list
    }
}
