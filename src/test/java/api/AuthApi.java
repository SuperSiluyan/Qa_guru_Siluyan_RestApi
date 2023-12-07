package api;

import io.restassured.response.Response;
import lombok.LoginTestModel;


import static io.restassured.RestAssured.given;
import static specs.ApiTestSpec.authResponseSpec;
import static specs.ApiTestSpec.createRequestSpec;

public class AuthApi {


    public Response getAuth() {
        LoginTestModel authBody = new LoginTestModel();
        authBody.setUserName("Siluyan");
        authBody.setPassword("Sil-321@rockAnime");

        return given(createRequestSpec)
                .body(authBody)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(authResponseSpec)
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();
    }
}
