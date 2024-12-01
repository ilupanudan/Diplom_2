package org.example.models;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.utils.Randomizer;

import static io.restassured.RestAssured.given;

public class User {
    private String email;
    private String password;
    private String name;


    public User(String email, String password, String name ){
        this.email=email;
        this.password = password;
        this.name = name;
    }
    public User(){};

    public static User randomUser() {
        return new User(
                Randomizer.randomEmail(),
                Randomizer.randomString(),
                Randomizer.randomString());
    }

    public static User withoutEmail() {
        return new User(null,
                randomUser().getPassword(),
                randomUser().getName());
    }

    public static User withoutPassword() {
        return new User(randomUser().getEmail(),
                null,
                randomUser().getName());
    }

    public static User withoutName() {
        return new User(randomUser().getEmail(),
                randomUser().getPassword(),
                null);
    }



    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getName() {
        return name;}

    public void setName(String name) {

        this.name = name;
    }


}
