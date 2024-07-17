package com.ApiLibreria.Service.Impl;


import com.ApiLibreria.DTO.AutorDto;
import com.ApiLibreria.DTO.ListaResultsLibroDto;
import com.ApiLibreria.DTO.RequesTituloDto;
import com.ApiLibreria.DTO.ResponseTituloDto;
import com.ApiLibreria.Entity.AutorEntity;
import com.ApiLibreria.Repository.AutorRepository;
import com.ApiLibreria.Service.ConsumoService;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ConsumoServiceImpl implements ConsumoService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumoServiceImpl.class);

@Autowired
private AutorRepository autorRepository;

    private String messageLog;
    @Value("${timeOutConnect}")
    private int timeOutConnect;
    @Value("${timeOutRead}")
    private int timeOutRead;


    @Override
    public HttpsURLConnection generarConexion(String urlConsumo) {
        try {
            URL url = new URL(urlConsumo.trim());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setConnectTimeout(timeOutConnect);
            connection.setReadTimeout(timeOutRead);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //Headers necesarios para hacer el consumo
            return connection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ResponseTituloDto consultaLibro(String urlConsumo, RequesTituloDto requestEntity) {
        ResponseTituloDto response = new ResponseTituloDto();
        String stringRespuesta = "";
        int responseCode = 0;
        try {
            String nombreLibro = requestEntity.getNombreLibro();
            String nomNewLibro =  remplasar(nombreLibro);
            String ArmarUrlConsumo = urlConsumo + nomNewLibro;

            //System.out.println("servicio ArmarUrlConsumo: " + ArmarUrlConsumo);


            HttpsURLConnection connections = generarConexion(ArmarUrlConsumo);
            connections.setRequestMethod("GET");
            responseCode = connections.getResponseCode();
            stringRespuesta = respuesta(connections, responseCode).toString();

            List<ListaResultsLibroDto> listaResultsLibro = new ArrayList<>();

            System.out.println("respuesta de la api"+stringRespuesta);

            if (responseCode == 200) {

                JSONObject respuesta = new JSONObject(stringRespuesta);
                response.setSuccess(true);
                response.setCodigo(String.valueOf(responseCode));
                response.setMessage("Consulta libro Correcta");
                response.setTitulo(respuesta.getJSONArray("results").getJSONObject(0).getString("title"));
                response.setAutor(respuesta.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getString("name"));
                response.setIdioma(String.valueOf(respuesta.getJSONArray("results").getJSONObject(0).getJSONArray("languages")));
                response.setNumeroDescargas(respuesta.getJSONArray("results").getJSONObject(0).getInt("download_count"));

                    ListaResultsLibroDto librosObjeto = new ListaResultsLibroDto();

                    librosObjeto.setTitle(respuesta.getJSONArray("results").getJSONObject(0).getString("title"));

                    List<AutorEntity> autores = autorRepository.findAllByOrderByIdDesc();
                    System.out.println(autores);
                    int idIncrementable = autores.size();

                    AutorEntity autor = new AutorEntity();
                    autor.setId(idIncrementable++);
                    autor.setName(respuesta.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getString("name"));
                    autor.setBirth_year(respuesta.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getInt("birth_year"));
                    autor.setDeath_year(respuesta.getJSONArray("results").getJSONObject(0).getJSONArray("authors").getJSONObject(0).getInt("death_year"));




                    AutorEntity RespInsertAutor = autorRepository.save(autor);
                    System.out.println("Autor ;"+RespInsertAutor);

                    librosObjeto.setLanguages(respuesta.getJSONArray("results").getJSONObject(0).getJSONArray("languages"));
                    librosObjeto.setDownload_count(respuesta.getJSONArray("results").getJSONObject(0).getInt("download_count"));


                    ///Metodod para Insertar en la Bd el Autor del libro












            }else{
                response.setSuccess(false);
                response.setCodigo("400");
                response.setMessage("Error timeout: " );
            }

        } catch (Exception e) {
            response.setSuccess(false);
            response.setCodigo("504");
            response.setMessage("Error timeout: " + e);
           // response.setTrama(requestEntity.getTrasactionID() + "," + requestEntity.getConnid() + "," + response.getCodigo() + "," + requestEntity.getDocumento() + ",~");
            //logger.info(String.valueOf(e));
            //utilLogs.logApiError(response.getMessage() + " connid: " + requestEntity.getConnid());
            stringRespuesta = response.getMessage();
            responseCode = 504;

        }

        return response;


    }



    @Override
    public StringBuilder respuesta(HttpsURLConnection connection, int code) throws IOException {
        StringBuilder resultado = new StringBuilder();
        try(BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String linea;
            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }
        }catch (Exception e){
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String line;
            while ((line = errorReader.readLine()) != null) {
                resultado.append(line);
            }
            errorReader.close();
            logger.info(String.valueOf(e));
        }
        return resultado;
    }



    public String remplasar(String nombreLibro)  {
        nombreLibro = nombreLibro.replaceAll(" ","%20");
        System.out.println("Dentro del metodo"+nombreLibro);
        return nombreLibro;
    }
   }