package ru.novo_estate.web.tests.novostroyki;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import ru.novo_estate.test_data.GenerationData;
import ru.novo_estate.web.enums.CityEnum;
import ru.novo_estate.web.enums.DeveloperEnum;
import ru.novo_estate.web.enums.RoomEnum;
import ru.novo_estate.web.pages.components.SearchNovostroykiFilters;
import ru.novo_estate.web.enums.BuildingEnum;
import ru.novo_estate.web.pages.NovostroykiPage;
import ru.novo_estate.web.tests.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class SearchNovostroykiFiltersTests extends TestBase {
    NovostroykiPage novostroykiPage = new NovostroykiPage();
    SearchNovostroykiFilters searchFilters = new SearchNovostroykiFilters();

//    @BeforeEach
//    void beforeEach() {
//        novostroykiPage.openNovostroykiPage();
//    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить результаты поиска по ЖК на странице /novostroyki")
    void searchNovostroyka() {
        open("https://novo-dom.ru/novostroyki");
        String searchBuildingName = GenerationData.setRandomBuilding(BuildingEnum.values());
        searchFilters.setValueInGeoSearchFilter(searchBuildingName)
                .selectDropdownItem(searchBuildingName);
        novostroykiPage.verifySearchBuildingTitleText(searchBuildingName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить результаты поиска ЖК по району на странице /novostroyki")
    void searchDistrict() {
        open("https://novo-dom.ru/novostroyki");
        String searchDistrictName = "Арбат";
        searchFilters.setValueInGeoSearchFilter(searchDistrictName)
                .selectDropdownDistrict(searchDistrictName);
        novostroykiPage.verifyResultSearchBuildingContent(searchDistrictName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить результаты поиска ЖК по городу на странице /novostroyki")
    void searchCity() {
        open("https://novo-dom.ru/novostroyki");
        String searchCityName = GenerationData.setRandomCity(CityEnum.values());
        searchFilters.setValueInGeoSearchFilter(searchCityName)
                .selectDropdownCity(searchCityName);
        novostroykiPage.verifyResultSearchBuildingContent(searchCityName);
    }


    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить результаты поиска ЖК по округу на странице /novostroyki")
    void searchСounty() {
        open("https://novo-dom.ru/novostroyki");
        String searchCountyName = "Восточный административный округ";
        String verifyCountyName = "ВАО";
        searchFilters.setValueInGeoSearchFilter(searchCountyName)
                .selectDropdownItem(searchCountyName);
        novostroykiPage.verifyResultSearchBuildingContent(verifyCountyName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить результаты поиска ЖК по застройщику на странице /novostroyki")
    void searchDeveloper() {
        open("https://novo-dom.ru/novostroyki");
        String searchDeveloperName = GenerationData.setRandomDeveloper(DeveloperEnum.values());
        searchFilters.setValueInGeoSearchFilter(searchDeveloperName)
                .selectDropdownItem(searchDeveloperName);
        novostroykiPage.verifyResultSearchBuildingDeveloper(searchDeveloperName);
    }


    @ParameterizedTest(name = "Проверить выдачу ЖК после примения фильтра 'Комнатность' {0} на странице /novostroyki")
    @EnumSource(RoomEnum.class)
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    void setFilterRoomsAndVerifyResultSearch(RoomEnum roomEnum) {
        open("https://novo-dom.ru/novostroyki");
        String rooms = roomEnum.name;
        searchFilters.clickCheckboxFilterRooms(rooms);
        novostroykiPage.verifyResultSearchByFilterRooms(rooms);
    }

    @CsvSource(value = {"15000000, От 15 млн. ₽", "100, От 100 ₽"})
    @ParameterizedTest(name = "Ввести значение {0} в фильтр 'Цена от' и проверить появление тега {1}")
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    void setPriceFromFilter(String data1, String data2) {
        open("https://novo-dom.ru/novostroyki");
        searchFilters.setPriceFrom(data1);
        novostroykiPage.verifyTagVisible(data2);
    }

    @CsvSource(value = {"1000000, До 1 млн. ₽", "8000000, До 8 млн. ₽"})
    @ParameterizedTest(name = "Ввести значение {0} в фильтр 'Цена до' и проверить появление тега {1}")
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    void setPriceToFilter(String data1, String data2) {
        open("https://novo-dom.ru/novostroyki");
        searchFilters.setPriceTo(data1);
        novostroykiPage.verifyTagVisible(data2);
    }


    @Test
    @Disabled
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("")
    void scrollNovostroykiItemsToLastPage() throws InterruptedException {
        Selenide.executeJavaScript("arguments[0].scrollIntoView()",$$(".search-item__main").last());
//        Selenide.executeJavaScript(document.querySelector('.infinity-scroll__viewport').scrollTop = document.querySelector('.search-novostroyki-content__items').offsetHeight);

}


}
