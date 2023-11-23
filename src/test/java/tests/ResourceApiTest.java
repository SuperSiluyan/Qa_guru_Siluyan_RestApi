package tests;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ResourceApiTest {

    @Test
    void listResourceTest() {
        Integer[] dataId = new Integer[]{1, 2, 3, 4, 5, 6};

        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12))
                .body("data.id", containsInAnyOrder(dataId));
    }

    @Test
    void singleResourceTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body("data.id", is(2));
    }

    @Test
    void singleResourceNotFoundTest() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .statusCode(404);
    }

    @Test
    void createUserTest() {
        String authBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUserTest() {
        String authBody = "{ \"name\": \"morpheus\", \"job\": \"leader the best\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authBody)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("leader the best"))
                .body("updatedAt", containsString("2023"));
    }
}