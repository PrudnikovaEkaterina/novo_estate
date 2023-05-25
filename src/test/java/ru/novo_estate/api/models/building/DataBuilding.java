package ru.novo_estate.api.models.building;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.novo_estate.api.models.building.Building;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBuilding {
    public ArrayList<Building> data;
}
