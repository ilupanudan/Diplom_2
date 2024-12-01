package org.example.clients;

import io.restassured.response.ValidatableResponse;
import org.example.models.User;
import org.example.models.UserCreds;
import org.example.steps.UserSteps;

import static io.restassured.RestAssured.given;
import static org.example.utils.Constants.*;
import static org.example.utils.Constants.USER_PATH;

public class UserClient extends RestClient {

    public static ValidatableResponse createUserClient(User user){
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER_PATH)
                .then();

    }

    public static ValidatableResponse loginUserClient(UserCreds userCreds) {
        return given()
                .spec(getBaseSpec())
                .header("Content-type", "application/json")
                .body(userCreds)
                .when()
                .post(LOGIN_USER_PATH)
                .then();

    }

    public static ValidatableResponse changeUserDataClient(User user, String bearerToken) {
        return given()
                .spec(getBaseSpec())
                .header("authorization", bearerToken)
                .body(user)
                .when()
                .patch(USER_PATH)
                .then();

    }

    public static void deleteClient() {

        given()
                .spec(getBaseSpec())
                .header("authorization", UserSteps.bearerToken)
                .when()
                .delete(USER_PATH)
                .then();
    }


}
