package ru.novo_estate.web.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ApartmentQuizModal {

    private final SelenideElement
            APARTMENT_QUIZ_MODAL_TITLE = $(".apartment-quiz-modal__title");

    String apartmentQuizModalTitle = "Бесплатно подберем квартиру по вашим критериям!";

    void verifyApartmentQuizModalTitle() {
        APARTMENT_QUIZ_MODAL_TITLE.shouldBe(Condition.visible)
                .shouldHave(Condition.text(apartmentQuizModalTitle));

    }
}
