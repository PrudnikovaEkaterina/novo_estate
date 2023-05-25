package ru.novo_estate.api.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginBodyModel {
    private String password;
    private String phone;

}
