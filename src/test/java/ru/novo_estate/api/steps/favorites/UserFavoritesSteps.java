package ru.novo_estate.api.steps.favorites;

import io.qameta.allure.Step;
import ru.novo_estate.api.models.building.DataBuilding;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static ru.novo_estate.api.helpers.CustomAllureListener.withCustomTemplates;
import static ru.novo_estate.api.specifications.Specification.*;

public class UserFavoritesSteps {
    @Step("Получить список избранных ЖК пользователя")
    public static DataBuilding getUserFavoritesBuilding (String accessToken) {
       return given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/me/favorites/buildings/")
                .then()
                .spec(responseSpec200)
                .extract().as(DataBuilding.class);

    }
}
