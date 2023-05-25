package ru.novo_estate.web.enums;

public enum HousingClassEnum {
    ECONOMY("Эконом"), COMFORT("Комфорт"), BUSINESS("Бизнес"), Elite("Элит");

    public final String name;

    HousingClassEnum(String name) {
        this.name =name;
    }

    @Override
    public String toString() {
        return name;
    }
}
