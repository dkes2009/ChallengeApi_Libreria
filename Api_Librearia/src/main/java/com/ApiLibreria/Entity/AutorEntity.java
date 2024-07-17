package com.ApiLibreria.Entity;




import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name= "DATABASEAUTOR", schema = "PUBLIC")
@Data
public class AutorEntity implements Serializable {

	private static final long serialVersionUID = -1152779434213289790L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="birthyear")
	private int birth_year;

	@Column(name="deathyear")
	private int death_year;




	
	

}
