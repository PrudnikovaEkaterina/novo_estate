package ru.novo_estate.api.tests.profile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.novo_estate.api.steps.auth.AuthSteps;
import ru.novo_estate.api.steps.profile.ProfileSteps;
import ru.novo_estate.test_data.GenerationData;

public class ProfileTests {
    String phone="79085040794";
    String accessToken = AuthSteps.getAccessToken(phone);


    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Api")
    @DisplayName("Изменение имени пользователя")
    void changeUsername () {
        String userName= GenerationData.setRandomUserName();
        ProfileSteps.changeUsername( accessToken, userName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Api")
    @DisplayName("Изменение Email пользователя")
    void changeEmail () {
        String email=GenerationData.setRandomEmail();
        ProfileSteps.changeEmail( accessToken, email);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Api")
    @DisplayName("Изменение имени и Email пользователя")
    void changeUsernameAndEmail () {
        String userName= GenerationData.setRandomUserName();
        String email=GenerationData.setRandomEmail();
        ProfileSteps.changeUsernameAndEmail(accessToken, userName, email);
    }


}
