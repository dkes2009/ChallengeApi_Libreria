package com.ApiLibreria.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data
public class AutorDto {

    private String name;

    private int birth_year;

    private int death_year;


}
