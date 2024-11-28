package org.example.clients;

import io.restassured.response.ValidatableResponse;
import org.example.models.Order;

import static io.restassured.RestAssured.given;
import static org.example.utils.Constants.GET_INGREDIENTS;
import static org.example.utils.Constants.ORDER_PATH;


public class OrderClient extends RestClient {

    public static ValidatableResponse createOrderClient(Order order, String bearerToken) {
        return given()
                .spec(getBaseSpec())
                .header("authorization", bearerToken)
                .body(order)
                .post(ORDER_PATH)
                .then();

    }

    public static ValidatableResponse getUserOrderClient(String bearerToken) {
        return given()
                .spec(getBaseSpec())
                .header("authorization", bearerToken)
                .get(ORDER_PATH)
                .then();

    }


    public static ValidatableResponse getIngredientsClient() {
        return given()
                .spec(getBaseSpec())
                .get(GET_INGREDIENTS)
                .then();

    }




}

