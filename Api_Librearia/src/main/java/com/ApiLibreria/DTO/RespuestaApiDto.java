package com.ApiLibreria.DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPOJOBuilder
@Data
public class RespuestaApiDto {

	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm";
	private boolean success;
	//Este campo si se utiliza en otras clases mediante lombook, pero no se imprime en el toString
	private String codigo;
	private String dateTime;
	//Este campo si se utiliza en otras clases mediante lombook, pero no se imprime en el toString
	private String message;
	private Object data;
	//Este campo si se utiliza en otras clases mediante lombook, pero no se imprime en el toString

	/*
	 * DTO response sucess
	 */

	public RespuestaApiDto() {
		this(true, "000", new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), null, null);
	}

	public RespuestaApiDto(String message, boolean isSuccess) {
		this(isSuccess, "ERR00", new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), message,
				null);
	}

	public RespuestaApiDto(boolean success, String codigo, String message) {
		super();
		this.success = success;
		this.dateTime = new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
		this.codigo = codigo;
		this.message = message;
	}

	public RespuestaApiDto(Object data) {
		this(true, "000", new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), null, data);
	}

	/*
	* DTO response error
	**/
	public RespuestaApiDto(boolean success, String idError, String dateTime, String message, Object data) {
		this.success = success;
		this.dateTime = dateTime;
		this.message = message;
		this.data = data;
		this.codigo = idError;
	}
	@Override
	public String toString() {
		return codigo + ","+message ;
	}

}
