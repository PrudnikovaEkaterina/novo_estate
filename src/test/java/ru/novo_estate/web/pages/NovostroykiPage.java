package ru.novo_estate.web.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class NovostroykiPage {
    private final SelenideElement GEO_SEARCH_FILTER = $(".el-select__input"),
    SEARCH_ITEM_TITLE_TEXT=$(".search-item__title-text");

    private final  ElementsCollection SEARCH_ITEM_ADDRESS_TEXT = $$(".search-item__address-text"),
    SEARCH_ITEM_DEVELOPER_TEXT =$$(".search-item__developer-text");


    public NovostroykiPage openNovostroykiPage(){
        open ("novostroyki");
        return this;
    }

    public NovostroykiPage setValueInGeoSearchFilter(String value){
        GEO_SEARCH_FILTER.setValue(value);
        return this;
    }

    public NovostroykiPage selectDropdownItem(String searchItemName){
        $x ("//li[@class='el-select-dropdown__item']//span[text()='"+searchItemName+"']").click();
        return this;
    }


    public NovostroykiPage selectDropdownDistrict(String searchDistrictName){
        $x ("//li[@class='el-select-dropdown__item']//span[text()='район "+searchDistrictName+"']").click();
        return this;
    }

    public NovostroykiPage selectDropdownCity(String searchCityName){
        $x ("//li[@class='el-select-dropdown__item']//span[text()='г. "+searchCityName+"']").click();
        return this;
    }


    public void verifySearchBuildingTitleText(String title){
        SEARCH_ITEM_TITLE_TEXT.shouldHave(Condition.text(title));
    }

    public void verifySearchBuildingContent (String content){
        SEARCH_ITEM_ADDRESS_TEXT.shouldBe(CollectionCondition.allMatch("all elements contains text", el->el.getText().contains(content)));
    }

    public void verifySearchBuildingDeveloper (String developer){
        SEARCH_ITEM_DEVELOPER_TEXT.shouldBe(CollectionCondition.allMatch("all elements contains text", el->el.getText().contains(developer)));
    }








}
