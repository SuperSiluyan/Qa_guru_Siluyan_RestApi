package tests;

import lombok.RegisteredBodyTestModel;
import models.CreateResponseTestModel;
import models.CreateTestModel;
import models.DataUserPojoModel;
import models.RegisteredTestModel;
import models.lombok.DataUserLombokModel;
import org.junit.jupiter.api.Test;
import testBases.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ApiTestSpec.*;


public class RecourceApiExtendTest extends TestBase {

    @Test
    void updateUserWithModelTest() {
        DataUserPojoModel authBody = new DataUserPojoModel();
        authBody.setName("morpheus");
        authBody.setJob("leader the best");

        DataUserLombokModel response = step("Update user request", () ->
                given(updateRequestSpec)
                        .body(authBody)
                        .when()
                        .put("users/2")
                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(DataUserLombokModel.class));

        step("Verify response", () ->
                assertEquals("morpheus", response.getName()));
    }

    @Test
    void createTest() {
        CreateTestModel authBody = new CreateTestModel();
        authBody.setName("morpheus");
        authBody.setJob("leader");

        CreateResponseTestModel response = step("Create user", () ->
                given(createRequestSpec)
                        .body(authBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(CreateResponseTestModel.class));

        step("Verify response", () ->
                assertEquals("morpheus", response.getName()));

    }

    @Test
    void singleResourceNotFoundTest() {
        step("Get resource from wrong url", () ->
                given(pageNotFoundRequestSpec)
                        .when()
                        .get("/unknown/23")
                        .then()
                        .spec(pageNotFoundResponseSpec));
    }

    @Test
    void deletePatchUserTest() {
        step("Delete user", () ->
                given(deleteRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteResponseSpec));
    }

    @Test
    void registerSuccessTest() {
        RegisteredBodyTestModel authBody = new RegisteredBodyTestModel();
        authBody.setEmail("eve.holt@reqres.in");
        authBody.setPassword("pistol");

        RegisteredTestModel response = step("Register user", () ->
                given(updateRequestSpec)
                        .body(authBody)
                        .when()
                        .post("/register")
                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(RegisteredTestModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }
}