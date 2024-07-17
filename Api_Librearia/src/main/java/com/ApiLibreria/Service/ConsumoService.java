package com.ApiLibreria.Service;


import com.ApiLibreria.DTO.RequesTituloDto;
import com.ApiLibreria.DTO.ResponseTituloDto;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;

public interface ConsumoService {


    HttpsURLConnection generarConexion(String url);
    ResponseTituloDto consultaLibro(String urlConsumo, RequesTituloDto requestEntity);
    StringBuilder respuesta (HttpsURLConnection connection, int code) throws IOException;
}
