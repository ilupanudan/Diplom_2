package org.example.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;
import static org.example.utils.Constants.BASE_URL;

public class RestClient {


    protected static RequestSpecification getBaseSpec() {


        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}