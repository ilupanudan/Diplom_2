package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.clients.UserClient;
import org.example.models.User;
import org.example.models.UserCreds;


import static org.example.clients.UserClient.*;
import static org.example.models.User.randomUser;
import static org.example.models.User.withoutName;
import static org.hamcrest.Matchers.equalTo;

public class UserSteps {
    public static User user;
    public static UserCreds userCreds;
    public static String bearerToken;

    @Step("Создание юзера")
    public static ValidatableResponse createUser() {
        user = randomUser();
        ValidatableResponse response = createUserClient(user);
        bearerToken = response.extract().path("accessToken");

        return response;
    }

    @Step("Логин юзера")
    public static ValidatableResponse loginUser(User user) {
        userCreds = UserCreds.from(user);

        return loginUserClient(userCreds);
    }


    @Step("Удаление юзера")
    public static void deleteUser() {
        if (bearerToken != null){
            userCreds = UserCreds.from(user);
            UserClient.deleteClient();
        }

    }

    @Step("Проверка статускода")
    public static void checkStatusCode(ValidatableResponse response, int statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    @Step("Проверка месседж")
    public static void checkMessage(ValidatableResponse response, String expectedMessage) {
        response.assertThat().body("message", equalTo(expectedMessage));
    }


    @Step("Попытка создать второго юзера с такими же параметрами, как у первого")
    public static ValidatableResponse createSameUser() {
        User same = user;
        return UserClient.createUserClient(same);
    }

    @Step("Создаем юзера без email")
    public static ValidatableResponse createUserWithoutEmail() {
        return UserClient.createUserClient(User.withoutEmail());
    }

    @Step("Создаем юзера без password")
    public static ValidatableResponse createUserWithoutPassword() {
        return UserClient.createUserClient(User.withoutPassword());
    }

    @Step("Создаем юзера без name")
    public static ValidatableResponse createUserWithoutName() {
        return UserClient.createUserClient(withoutName());
    }

    @Step("Изменение данных пользователя")
    public static ValidatableResponse changeUserData(String bearerToken){
        user = randomUser();
        return changeUserDataClient(user, bearerToken);
    }




}


