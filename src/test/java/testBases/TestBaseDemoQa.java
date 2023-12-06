package testBases;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseDemoQa {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com";

    }
}
