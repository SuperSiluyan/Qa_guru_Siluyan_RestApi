package tests;

import lombok.LoginTestModel;
import models.RegisteredTestModel;
import org.junit.jupiter.api.Test;
import testBases.TestBaseDemoQa;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class DeleteBookTest extends TestBaseDemoQa {

    @Test
    void deleteBookFromProfileTest() {
        String authBody = "{ \"userName\": \"Siluyan\", \"password\": \"Sil-321@rockAnime\" }";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authBody)
                .contentType(JSON)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginTestModel.class);
    }
}
