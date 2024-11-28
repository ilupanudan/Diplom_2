import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.utils.Randomizer;
import org.junit.After;
import org.junit.Test;

import static org.example.steps.UserSteps.*;
import static org.example.utils.Constants.*;

import static org.junit.Assert.assertNotEquals;

public class UserChangeDataTest {


    @After
    public void CleanUp() {
        deleteUser();
    }


    @Test
    @DisplayName("Изменение данных юзера с авторизацией;")
    public void userChangeDataWithAuthTest() {
        ValidatableResponse responseBefore = createUser();
        ValidatableResponse responseAfter = changeUserData(bearerToken);

        checkStatusCode(responseAfter,OK);
        assertNotEquals(responseAfter.extract().path("user.email"),responseBefore.extract().path("user.email"));
        assertNotEquals(responseAfter.extract().path("user.name"),responseBefore.extract().path("user.name"));
    }

    @Test
    @DisplayName("Изменение данных юзера без авторизации;")
    public void userChangeDataWithoutAuthTest() {
        createUser();
        ValidatableResponse response = changeUserData(Randomizer.randomString());

        checkStatusCode(response,UNAUTHORIZED);
        checkMessage(response, NOT_AUTHORIZED);

    }

}


