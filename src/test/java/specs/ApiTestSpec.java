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

        public static RequestSpecification createRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON);

        public static ResponseSpecification createResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(201)
                .build();


        public static RequestSpecification pageNotFoundRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON);


        public static ResponseSpecification pageNotFoundResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(404)
                .build();


        public static RequestSpecification deleteRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON);


        public static ResponseSpecification deleteResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(204)
                .build();


        public static ResponseSpecification authResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
//                .expectStatusCode(200)
                .build();

        public static RequestSpecification requestBookSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .contentType(JSON);


}


