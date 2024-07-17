package com.ApiLibreria.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;

import java.io.Serializable;


@Entity
@Table(name= "libros")
@Data

public class LibrosEntity implements Serializable {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="title")
	private String title;

	@Column(name="authors")
	private String authors;

	@Column(name="languages")
	private String languages;

	@Column(name="download_count")
	private int download_count;




	
	

}
