package ru.novo_estate.api.tests.auth;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.novo_estate.api.steps.auth.AuthSteps;

public class AuthTests {
    String phoneNumber = "79085040794";

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Api")
    @DisplayName("Авторизация пользователя")
    void auth() {
        AuthSteps.auth(phoneNumber);

    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Api")
    @DisplayName("Логаут пользователя")
    void logout() {
        String token = AuthSteps.getAccessToken(phoneNumber);
        AuthSteps.logout(token);
    }

}
