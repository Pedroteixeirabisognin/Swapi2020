package br.com.b2w.apistarwars.model.dao;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	public PlanetDao(@NotNull String name, @NotNull String climate, @NotNull String terrain) {
		super();
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

	public PlanetDao(String id, @NotNull String name, @NotNull String climate, @NotNull String terrain) {
		super();
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	
	
	
	
	
}
