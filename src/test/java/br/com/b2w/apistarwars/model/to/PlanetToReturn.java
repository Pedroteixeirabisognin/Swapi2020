package br.com.b2w.apistarwars.model.to;

import lombok.Data;

@Data
public class PlanetToReturn {

	private String id;
	
	private String name;
	
	private String climate;
	
	private String terrain;
	
	private int aparitions;
}
