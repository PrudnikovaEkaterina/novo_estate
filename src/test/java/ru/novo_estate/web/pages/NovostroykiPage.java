package ru.novo_estate.web.pages;

import com.codeborne.selenide.*;
import ru.novo_estate.web.pages.components.MoreFiltersModal;
import ru.novo_estate.web.pages.components.CallMeWidget;
import ru.novo_estate.web.pages.components.Footer;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class NovostroykiPage {
    private final SelenideElement
            SEARCH_ITEM_TITLE_TEXT = $(".search-item__title-text"),
            MORE_FILTERS_BUTTON = $(".more-filters__button-text"),
            NOTIFICATION_INDICATOR = $x("//a[@class='notification-indicator']"),
            SEARCH_ITEM_CONTENT = $$(".search-item__content").first(),
            CALL_ME_WIDGET_BUTTON_ICON = $x("//div[@class='call-me-widget search-item__call-me'][1]"),
            SEARCH_NOVOSTROYKI_CONTENT_TOTAL = $(".search-novostroyki-content__total"),
            SEARCH_NOVOSTROYKI_ITEMS_ITEM = $$(".search-item__main").last(),
            SEARCH_NOVOSTROYKI_CONTENT_ITEMS_TITLE = $(".search-novostroyki-content__items-title");

    private final ElementsCollection
            SEARCH_ITEM_ADDRESS_TEXT = $$(".search-item__address-text"),
            SEARCH_ITEM_DEVELOPER_TEXT = $$(".search-item__developer-text"),
            SEARCH_PRICE_LIST_TABLE = $$(".search-price-list__table"),
            TAG = $$(".base-round-link-button__text");

    MoreFiltersModal moreFiltersModal = new MoreFiltersModal();
    CallMeWidget callMeWidget = new CallMeWidget();
    Footer footer = new Footer();


    public void openNovostroykiPage() {

        open("https://novo-dom.ru/novostroyki");
    }


    public void verifySearchBuildingTitleText(String title) {
        SEARCH_ITEM_TITLE_TEXT.shouldHave(Condition.text(title));
    }

    public void verifyResultSearchBuildingContent(String content) {
        SEARCH_ITEM_ADDRESS_TEXT.shouldBe(CollectionCondition.allMatch("all elements contains text", el -> el.getText().contains(content)));
    }

    public void verifyResultSearchBuildingDeveloper(String developer) {
        SEARCH_ITEM_DEVELOPER_TEXT.shouldBe(CollectionCondition.allMatch("all elements contains text", el -> el.getText().contains(developer)));
    }

    public void verifyResultSearchByFilterRooms(String rooms) {
        SEARCH_PRICE_LIST_TABLE.shouldBe(CollectionCondition.allMatch("all elements contains text", el -> el.getText().contains(rooms)));
    }

    public void verifyTagVisible(String tagName) {
        TAG.findBy(Condition.text(tagName)).shouldBe(Condition.visible);
    }

    public MoreFiltersModal openMoreFiltersModal() {
        MORE_FILTERS_BUTTON.click();
        moreFiltersModal.verifyModalHeaderText();
        return moreFiltersModal;
    }


    public void verifyResultSearchByFilterHousingClass(String housingClass) {
        String[] mas = SEARCH_NOVOSTROYKI_CONTENT_TOTAL.getText().split(" ");
        System.out.println(Integer.parseInt(mas[1]));
        if (Integer.parseInt(mas[1]) <= 15)
            $$(".search-item-slider__tags").filter(Condition.text(housingClass)).shouldHave(size(Integer.parseInt(mas[1])));
        else
            $$(".search-item-slider__tags").filter(Condition.text(housingClass)).shouldHave(size(15));

    }

    public void verifyNotificationIndicator(String numberOfFiltersSelected) {
        NOTIFICATION_INDICATOR.shouldHave(Condition.text(numberOfFiltersSelected));
    }

    public NovostroykiPage hoverSearchItemContent() {
        SEARCH_ITEM_CONTENT.hover();
        return this;
    }

    public CallMeWidget openCallMeWidget() {
        CALL_ME_WIDGET_BUTTON_ICON.shouldBe(Condition.visible).click();
        return callMeWidget;
    }

    public void scrollNovostroykiItemsToLastPage() {
//        boolean b = true;
//        while (b) {
//            SEARCH_NOVOSTROYKI_CONTENT_ITEMS_TITLE.
//            actions().moveToElement(SEARCH_NOVOSTROYKI_ITEMS_ITEM).perform();
////            if (footer.footerContainerIsVisible())
//                b = false;
//        }
//        actions().moveToElement(SEARCH_NOVOSTROYKI_ITEMS_ITEM).perform();

    }

    public void verifySearchNovostroykiContentItemsTitle() {
      SEARCH_NOVOSTROYKI_CONTENT_ITEMS_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text("Жилые комплексы Москвы и Московской области"));
    }
}

