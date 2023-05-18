package ru.novo_estate.web.data;

import com.github.javafaker.Faker;

public class GenerationData {
    static Faker faker =new Faker();
    public static String setRandomDeveloper(DeveloperEnum[] allEnumValues) {
    return faker.options().option(DeveloperEnum.values()).name;
    }

    public static String setRandomBuilding(BuildingEnum [] allEnumValues) {
        return faker.options().option(BuildingEnum.values()).name;
    }

    public static String setRandomCity(CityEnum [] allEnumValues) {
        return faker.options().option(CityEnum.values()).name;
    }


}
