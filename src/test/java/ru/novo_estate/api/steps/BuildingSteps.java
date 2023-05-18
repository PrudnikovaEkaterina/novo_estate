package ru.novo_estate.api.steps;

import com.beust.ah.A;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.novo_estate.api.models.Building;
import ru.novo_estate.api.models.DataBuilding;
import ru.novo_estate.api.models.Near;
import ru.novo_estate.api.models.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.novo_estate.api.specifications.Specification.requestSpec;
import static ru.novo_estate.api.specifications.Specification.responseSpec200;

public class BuildingSteps {
    @Step("Получить список ЖК с фильтром по станции метро")
    public static void getBuildingWithFilterStation(int station) {
        DataBuilding dataBuilding = given()
                .spec(requestSpec)
                .basePath("/api/buildings/")
                .param("region_code[]", 50)
                .param("region_code[]", 77)
                .param("stations[]", station)
                .get()
                .then()
                .spec(responseSpec200)
                .extract().as(DataBuilding.class);
        List <Near> nearList = dataBuilding.getData().stream().map(Building::getNear).toList();
        for (int i=0; i<nearList.size(); i++){
         List<Station> stations = nearList.get(i).getStations().stream().filter(el -> el.getId() == station).toList();
         List<Integer>  list = stations.stream().map(Station::getId).toList();
         for (int y=0; y<list.size(); y++){
             Assertions.assertEquals(list.get(y),station);
         }

     }
//        List<ArrayList<Station>> stationList= nearList.stream().map(Near::getStations).toList();
//        stationList.forEach(el->el.stream().forEach(System.out::println));
//        stationList.forEach(el->el.stream().map(Station::getId).toList());
//        List<Stream<Integer>> idList =stationList.stream().map(el->el.stream().map(Station::getId)).toList();
//        idList.forEach(System.out::println);


    }


}
