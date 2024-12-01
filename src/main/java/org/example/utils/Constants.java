package org.example.utils;

public class Constants {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static final String CREATE_USER_PATH = "/api/auth/register";
    public static final String LOGIN_USER_PATH = "/api/auth/login";
    public static final String USER_PATH =  "/api/auth/user";
    public static final String ORDER_PATH =  "/api/orders";
    public static final String GET_INGREDIENTS =  "/api/ingredients";


    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String NOT_ENOUGH_DATA_FOR_CREATE = "Email, password and name are required fields";
    public static final String INCORRECT_LOGIN_DATA = "email or password are incorrect";
    public static final String NOT_AUTHORIZED = "You should be authorised";


    public static final int OK = 200;
    public static final int FORBIDDEN = 403;
    public static final int UNAUTHORIZED = 401;
    public static final int BAD_REQUEST = 400;
    public static final int INTERNAL_SERVER_ERROR = 500;
}
