package com.ApiLibreria.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RequesTituloDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreLibro;


    @Override
    public String toString() {
        return nombreLibro;
    }

}
