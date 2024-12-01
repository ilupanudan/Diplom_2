import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.models.Order;
import org.example.utils.Randomizer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.example.steps.OrderSteps.*;
import static org.example.steps.UserSteps.*;
import static org.example.steps.UserSteps.checkStatusCode;
import static org.example.utils.Constants.*;

public class OrderTest {

    @Before
    public void setUp(){
        createUser();
    }

    @After
    public void tearDown() {
        deleteUser();
    }

    @Test
    @DisplayName("Cоздание заказа авторизованным пользователем;")
    public void orderCreateWithAuthTest() {
        ValidatableResponse response = createOrder(createListOfIngredientsId(), bearerToken);

        checkStatusCode(response, OK);
    }

    @Test
    @DisplayName("Cоздание заказа неавторизованным пользователем;")
    public void orderCreateWithoutAuthTest() {
        ValidatableResponse response = createOrder(createListOfIngredientsId(), Randomizer.randomString());

        checkStatusCode(response, OK);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        ValidatableResponse response = createOrder(new Order(), bearerToken);

        checkStatusCode(response, BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание заказа с неверным хэшом ингредиента")
    public void createOrderWithIncorrectIngredientsTest() {
        ValidatableResponse response = createOrder(new Order(List.of("s")), bearerToken);

        checkStatusCode(response, INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя с авторизацией")
    public void getUserOrderWithAuthTest() {
        createOrder(createListOfIngredientsId(), bearerToken);
        ValidatableResponse response = getUserOrder(bearerToken);

        checkStatusCode(response, OK);
        checkMessageContainsAnswer(response, "orders");

    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя без авторизации")
    public void getUserOrderWithoutAuthTest() {
        createOrder(createListOfIngredientsId(), bearerToken);
        ValidatableResponse response = getUserOrder(Randomizer.randomString());

        checkStatusCode(response, UNAUTHORIZED);
        checkMessage(response, NOT_AUTHORIZED);

    }


}
