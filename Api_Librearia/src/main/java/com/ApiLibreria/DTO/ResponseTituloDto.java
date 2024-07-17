package com.ApiLibreria.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data
public class ResponseTituloDto extends RespuestaApiDto{

    private String titulo;
    private String autor;
    private String idioma;
    private int numeroDescargas;

}
