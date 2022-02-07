package ann.blin.api;

import ann.blin.models.User;
import org.junit.jupiter.api.Test;

import static ann.blin.api.Specs.request;
import static ann.blin.api.Specs.response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiTests extends ApiTestBase {

    @Test
    void successLoginWith() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessLogin() {
        User user = new User();
        user.setEmail("peter@klaven");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void successRegistration() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(response)
                .body("token", is("QpwL5tke4Pnpja7X4"), "id", is(4));
    }

    @Test
    void unsuccessRegistration() {
        User user = new User();
        user.setEmail("sydney@fife");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void create() {
        User user = new User();
        user.setName("morpheus");
        user.setJob("leader");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", is(user.getName()), "job", is(user.getJob()));
    }

    @Test
    void singleUserNotFound() {
        given()
                .spec(request)
                .when()
                .get("/users/23")
                .then()
                .statusCode(404)
                .body(is("{}"));
    }

}
