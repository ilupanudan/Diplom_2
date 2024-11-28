import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.example.models.User.randomUser;
import static org.example.steps.UserSteps.*;
import static org.example.utils.Constants.*;

public class UserLoginTest {

    @Before
    public void setUp(){
        createUser();
    }

    @After
    public void tearDown() {
        deleteUser();
    }


    @Test
    @DisplayName("Логин существующим пользователем;")
    public void userLoginTest() {
        ValidatableResponse response =loginUser(user);

        checkStatusCode(response, OK);
    }


    @Test
    @DisplayName("Логин с неверным логином и паролем;")
    public void userLoginWithWrongCredsTest() {
        ValidatableResponse response = loginUser(randomUser());

        checkStatusCode(response, UNAUTHORIZED);
        checkMessage(response, INCORRECT_LOGIN_DATA);
    }




}
