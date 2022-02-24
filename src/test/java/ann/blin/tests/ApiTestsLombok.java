package ann.blin.tests;

import ann.blin.lombok.CreateUserRequest;
import ann.blin.lombok.CreateUserResponse;
import ann.blin.lombok.RegisterUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ann.blin.spec.Specs.request;
import static ann.blin.spec.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTestsLombok extends ApiTestBase {

    @Test
    @DisplayName("Success registration with Lombok")
    void successRegistrationLombok() {
        RegisterUser registrationData = new RegisterUser();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("pistol");

        CreateUserResponse response = given().spec(request)
                .body(registrationData)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .extract().as(CreateUserResponse.class);


        assertEquals("4", response.getId());
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    @DisplayName("Unsuccess registration with Lombok")
    void unsuccessRegistrationLombok() {
        RegisterUser registrationData = new RegisterUser();
        registrationData.setEmail("sydney@fife");
        CreateUserResponse response = given()
                .spec(request)
                .body(registrationData)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .extract().as(CreateUserResponse.class);

        assertEquals("Missing password", response.getError());
    }

    @Test
    @DisplayName("Create user with lombok")
    void createLombok() {
        CreateUserRequest user = new CreateUserRequest("morpheus", "leader");

        CreateUserResponse response = given().spec(request)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertEquals(user.getName(), response.getName());
        assertEquals(user.getJob(), response.getJob());

    }
}

