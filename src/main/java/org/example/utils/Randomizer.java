package org.example.utils;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;



public class Randomizer {
   public static String randomString() {
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-US"), new RandomService());

       return fakeValuesService.bothify("???????");
}

    public static String randomEmail(){

        return randomString() + "@yandex.ru";
    }

}