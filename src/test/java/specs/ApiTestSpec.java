package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class ApiTestSpec {

        public static RequestSpecification updateRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON);

        public static ResponseSpecification updateResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(200)
                .build();

        public static RequestSpecification listRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .baseUri("https://reqres.in")
                .basePath("/api/unknown");

        public static ResponseSpecification listResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(200)
                .build();
}


