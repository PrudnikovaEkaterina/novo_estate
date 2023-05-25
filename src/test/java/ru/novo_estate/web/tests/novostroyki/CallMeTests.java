package ru.novo_estate.web.tests.novostroyki;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.novo_estate.web.pages.NovostroykiPage;
import ru.novo_estate.web.tests.TestBase;
import ru.novo_estate.data_base.managers.CallbackPhonesManager;
import ru.novo_estate.test_data.GenerationData;
import ru.novo_estate.web.domain.CallbackPhonesBD;

import java.util.List;

public class CallMeTests extends TestBase {
    NovostroykiPage novostroykiPage = new NovostroykiPage();
   CallbackPhonesManager callbackPhonesManager=new CallbackPhonesManager();

    @BeforeEach
    void beforeEach() {
        novostroykiPage.openNovostroykiPage();
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Отправить заполненную форму Заказать звонок и проверить добавление соответсвующей записи в БД")
    void orderCallBackAndСheckAdditionInDatabase() {
        String callbackPhoneModalTitle ="Укажите Ваш номер телефона и мы перезвоним!";
        String phoneNumber= GenerationData.setRandomPhoneNumber();
        String phoneThanksModalTitle= "Спасибо!\n" + "Мы уже обрабатываем вашу заявку";
        novostroykiPage.hoverSearchItemContent().openCallMeWidget().verifyCallbackPhoneModalTitle(callbackPhoneModalTitle)
                .setPhoneNumber(phoneNumber).clickCallMeButton().verifyPhoneThanksModalTitle(phoneThanksModalTitle);
        List<CallbackPhonesBD> result =callbackPhonesManager.selectLastEntryFromCallbackPhonesTables();
        Assertions.assertEquals(phoneNumber, result.get(0).getPhone());
        Assertions.assertEquals("https://novo-estate.ru/novostroyki", result.get(0).getLink());


    }

}
