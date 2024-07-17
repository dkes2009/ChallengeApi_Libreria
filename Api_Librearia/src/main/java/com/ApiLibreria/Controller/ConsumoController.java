package com.ApiLibreria.Controller;

import com.ApiLibreria.DTO.RequesTituloDto;
import com.ApiLibreria.DTO.ResponseTituloDto;
import com.ApiLibreria.DTO.RespuestaApiDto;
import com.ApiLibreria.Service.ConsumoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ConsumoController {
    @Autowired
    private ConsumoService endpointService;

    private static final String ERRORDATA = "ERROR EN EL INGRESO DE DATOS";


    @Value("${urlGetBuscarLibro}")
    private String urlBuscarLibro;


    @PostMapping("/consultalibro")
    public RespuestaApiDto consultacliente(@RequestBody RequesTituloDto requestEntity, HttpServletRequest request, HttpServletResponse httpResponse) {
        ResponseTituloDto response = new ResponseTituloDto();
        System.out.println(requestEntity);
        response = endpointService.consultaLibro(urlBuscarLibro,requestEntity);



        return response;
    }




}