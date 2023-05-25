package ru.novo_estate.web.tests.novostroyki;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.novo_estate.test_data.GenerationData;
import ru.novo_estate.web.enums.HousingClassEnum;
import ru.novo_estate.web.pages.components.SearchNovostroykiFilters;
import ru.novo_estate.web.pages.NovostroykiPage;
import ru.novo_estate.web.tests.TestBase;

public class MoreFilterModalTests extends TestBase {
    NovostroykiPage novostroykiPage = new NovostroykiPage();
    SearchNovostroykiFilters searchFilters = new SearchNovostroykiFilters();

    @BeforeEach
    void beforeEach() {
        novostroykiPage.openNovostroykiPage();
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить открытие модального окна по клику на кнопку Все фильтры странице /novostroyki")
    void checkOpenMoreFiltersModal() {
        novostroykiPage.openMoreFiltersModal();
    }


    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить выдачу на странице /novostroyki  после примения фильтра Класс жилья")
    void clickCheckboxHousingClassAndVerifyResultSearch() {
        String housingClass= GenerationData.setRandomHousingClass(HousingClassEnum.values());
        String numberOfFiltersSelected = "1";
        novostroykiPage.openMoreFiltersModal().clickCheckboxHousingClass(housingClass).clickShowButton();
        novostroykiPage.verifyResultSearchByFilterHousingClass(housingClass+" класс");
        novostroykiPage.verifyNotificationIndicator(numberOfFiltersSelected);
    }

}
