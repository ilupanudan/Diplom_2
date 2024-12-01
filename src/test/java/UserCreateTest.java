import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.example.steps.UserSteps.*;
import static org.example.steps.UserSteps.deleteUser;
import static org.example.utils.Constants.*;

public class UserCreateTest {


    @After
    public void tearDown() {
        deleteUser();
    }

    @Test
    @DisplayName("Cоздание уникального пользователя;")
    public void userCreateTest() {
        ValidatableResponse response = createUser();

        checkStatusCode(response, OK);
    }

    @Test
    @DisplayName("Нельзя создать пользователя, который уже зарегестрирован;")
    public void userDoubleCreateTest() {
        ValidatableResponse response;
        createUser();
        response = createSameUser();

        checkStatusCode(response, FORBIDDEN);
        checkMessage(response, USER_ALREADY_EXISTS);

    }

    @Test
    @DisplayName("Нельзя создать пользователя, не заполнив поле email;")
    public void userCreateWithoutEmailTest() {
        ValidatableResponse response = createUserWithoutEmail();

        checkStatusCode(response, FORBIDDEN);
        checkMessage(response, NOT_ENOUGH_DATA_FOR_CREATE);


    }
    @Test
    @DisplayName("Нельзя создать пользователя, не заполнив поле password;")
    public void userCreateWithoutPasswordTest() {
        ValidatableResponse response = createUserWithoutPassword();
        checkStatusCode(response, FORBIDDEN);
        checkMessage(response, NOT_ENOUGH_DATA_FOR_CREATE);

    }
    @Test
    @DisplayName("Нельзя создать пользователя, не заполнив поле name;")
    public void userCreateWithoutNameTest() {
        ValidatableResponse response = createUserWithoutName();

        checkStatusCode(response, FORBIDDEN);
        checkMessage(response, NOT_ENOUGH_DATA_FOR_CREATE);
    }



}
