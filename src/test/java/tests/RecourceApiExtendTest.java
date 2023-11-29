package tests;

import lombok.TotalModel;
import models.DataUserPojoModel;
import models.lombok.DataUserLombokModel;
import org.junit.jupiter.api.Test;
import testBases.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ApiTestSpec.*;


public class RecourceApiExtendTest {

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
    void listResourceExtendTest() {

        TotalModel response = step("Make login request", () ->
        given(listRequestSpec)
                .when()
                .get()
                .then()
                .spec(listResponseSpec)
                .extract().as(TotalModel.class));

        step("Verify response", () ->
                assertEquals(12, response.getTotal()));

    }

}
