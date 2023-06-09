package ru.novo_estate.api.models.building;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
    private Integer id;
    private Integer distance_mode;
    private Integer distance;
    private String duration;
    private Integer duration_value;
}
