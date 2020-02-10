package br.com.b2w.apistarwars.model.dao;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Planet")
public class PlanetDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String climate;
	
	@NotNull
	private String terrain;
	
	
}
