package com.ApiLibreria.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;
import org.json.JSONArray;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data
public class ListaResultsLibroDto {

    private String title;

    private AutorDto authors;

    private JSONArray languages;

    private int download_count;

}
