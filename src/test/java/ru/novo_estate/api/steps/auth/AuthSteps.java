package ru.novo_estate.api.steps.auth;

import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import org.aeonbits.owner.ConfigCache;
import ru.novo_estate.api.models.auth.AuthModel;
import ru.novo_estate.api.models.auth.UserLoginBodyModel;
import ru.novo_estate.config.AuthConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static ru.novo_estate.api.helpers.CustomAllureListener.withCustomTemplates;
import static ru.novo_estate.api.specifications.Specification.*;

public class AuthSteps {
    static AuthConfig authConfig = ConfigCache.getOrCreate(AuthConfig.class);

    @Step("Авторизация пользователя")
    public static AuthModel auth(String phone) {
        String password = authConfig.smsCode();
        String authCookieName = authConfig.authCookieName();
        String authCookieValue = authConfig.authCookieValue();
        UserLoginBodyModel userLoginBodyModel = new UserLoginBodyModel();
        userLoginBodyModel.setPhone(phone);
        Cookie authCookie = new Cookie.Builder(authCookieName, authCookieValue).build();

        given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .cookie(authCookie)
                .body(userLoginBodyModel)
                .when()
                .post("/api/auth/register")
                .then()
                .spec(responseSpec204);

        userLoginBodyModel.setPassword(password);
        return given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .cookie(authCookie)
                .body(userLoginBodyModel)
                .when()
                .post("/api/auth/login")
                .then()
                .spec(responseSpec200)
                .body("user.phone", is(phone))
                .extract().as(AuthModel.class);

    }

    public static String getAccessToken(String phone) {
        AuthModel authModel = auth(phone);
        return authModel.getAccessToken();
    }


    @Step("Логаут пользователя")
    public static void logout(String accessToken) {
        given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/api/auth/logout")
                .then()
                .spec(responseSpec200)
                .body("message", is("Successfully logged out"));
    }
}
