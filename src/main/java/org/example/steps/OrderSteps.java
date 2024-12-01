package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.models.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.clients.OrderClient.*;
import static org.hamcrest.Matchers.containsString;

public class OrderSteps {

    public static List<String> ingredientsIdList;


    @Step("Получение списка id ингредиентов")
    public static Order createListOfIngredientsId() {
        ValidatableResponse response = getIngredientsClient();
        List<Map<String, String>> ingredientsList = response.extract().path("data");

        ingredientsIdList = ingredientsList.stream()
                .map(ingredient -> ingredient.get("_id"))
                .collect(Collectors.toList());
        return new Order(ingredientsIdList);
    }


    @Step("Создание заказа")
    public static ValidatableResponse createOrder(Order order, String bearerToken) {

        return createOrderClient(order, bearerToken);
    }

    @Step("Получение заказа юзера")
    public static ValidatableResponse getUserOrder(String bearerToken) {

        return getUserOrderClient(bearerToken);
    }

    @Step("Проверка ответа на содержание")
    public static void checkMessageContainsAnswer(ValidatableResponse response, String expectedMessage) {
        response.assertThat().body(containsString(expectedMessage));

    }
}
