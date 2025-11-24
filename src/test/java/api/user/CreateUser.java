package api.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUser {
    @Step("Create user")
    public Response createUser(CreateUserData createUserData){
        return given()
                .spec(RequestSpec.getSpec())
                .header("Content-type", "application/json")
                .and()
                .body(createUserData)
                .post("/api/signup");
    }
}
