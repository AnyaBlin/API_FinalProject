package ann.blin.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }

}
