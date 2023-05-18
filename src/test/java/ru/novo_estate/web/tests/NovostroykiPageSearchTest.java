package ru.novo_estate.web.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.novo_estate.web.data.BuildingEnum;
import ru.novo_estate.web.data.CityEnum;
import ru.novo_estate.web.data.DeveloperEnum;
import ru.novo_estate.web.data.GenerationData;
import ru.novo_estate.web.pages.NovostroykiPage;

import static com.codeborne.selenide.Selenide.open;

public class NovostroykiPageSearchTest extends TestBase{
    NovostroykiPage novostroykiPage=new NovostroykiPage();

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить поиск по ЖК на странице /novostroyki")
    void searchNovostroyka (){
        String searchBuildingName=GenerationData.setRandomBuilding(BuildingEnum.values());
        novostroykiPage.openNovostroykiPage().setValueInGeoSearchFilter(searchBuildingName)
                .selectDropdownItem(searchBuildingName).verifySearchBuildingTitleText(searchBuildingName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить поиск по району на странице /novostroyki")
    void searchStation (){
        String searchDistrictName="Арбат";
        novostroykiPage.openNovostroykiPage().setValueInGeoSearchFilter(searchDistrictName)
                .selectDropdownDistrict(searchDistrictName).verifySearchBuildingContent(searchDistrictName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить поиск по городу на странице /novostroyki")
    void searchCity (){
        String searchCityName=GenerationData.setRandomCity(CityEnum.values());
        novostroykiPage.openNovostroykiPage().setValueInGeoSearchFilter(searchCityName)
                .selectDropdownCity(searchCityName).verifySearchBuildingContent(searchCityName);
    }


    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить поиск по округу на странице /novostroyki")
    void searchСounty (){
        String searchCountyName="Восточный административный округ";
        String verifyCountyName="ВАО";
        novostroykiPage.openNovostroykiPage().setValueInGeoSearchFilter(searchCountyName)
                .selectDropdownItem(searchCountyName).verifySearchBuildingContent(verifyCountyName);
    }

    @Test
    @Owner("PrudnikovaEkaterina")
    @Tag("Web")
    @DisplayName("Проверить поиск по застройщику на странице /novostroyki")
    void searchDeveloper (){
       String searchDeveloperName=GenerationData.setRandomDeveloper(DeveloperEnum.values());
        System.out.println(searchDeveloperName);
       novostroykiPage.openNovostroykiPage().setValueInGeoSearchFilter(searchDeveloperName)
               .selectDropdownItem(searchDeveloperName).verifySearchBuildingDeveloper(searchDeveloperName);
    }


}
