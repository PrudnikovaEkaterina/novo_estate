package ru.novo_estate.web.tests.auth;

import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.*;
import ru.novo_estate.config.AuthConfig;
import ru.novo_estate.test_data.GenerationData;
import ru.novo_estate.web.pages.AuthPage;
import ru.novo_estate.web.pages.components.Header;
import ru.novo_estate.web.tests.TestBase;


public class AuthTests extends TestBase {
    AuthPage authPage = new AuthPage();
    Header header = new Header();
    AuthConfig authConfig = ConfigCache.getOrCreate(AuthConfig.class);

    @BeforeEach
    void beforeEach() {
        authPage.openAuthPage();
    }

    @AfterEach
    void afterEach() {
        header.logout();
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Позитивная проверка регистрации нового пользователя")
    void registrationNewUserSuccessful() {
        String phoneNumber = GenerationData.setRandomPhoneNumber();
        String smsCode = authConfig.smsCode();
        String userName = GenerationData.setRandomUserName();
        authPage.
                setPhone(phoneNumber).clickAuthUserByPhoneConfirmLink().clickButtonSendCode().setSmsCode(smsCode)
                .checkAuthTitle().setUserName(userName).clickCompleteAuthNameButton();
        header.checkAccountName(userName);
        header.hoverHeaderAccountIconAndCheckUserMenuDropdownText();

    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Позитивная проверка авторизации пользователя")
    void authUserSuccessful() {
        String phoneNumber = "79085040794";
        String smsCode = authConfig.smsCode();
        authPage.
                setPhone(phoneNumber).clickAuthUserByPhoneConfirmLink().clickButtonSendCode().setSmsCode(smsCode);
        header.hoverHeaderAccountIconAndCheckUserMenuDropdownText();

    }
}
