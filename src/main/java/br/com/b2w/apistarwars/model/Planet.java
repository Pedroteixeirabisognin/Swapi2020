package br.com.b2w.apistarwars.model;

import lombok.Data;

@Data
public class Planet {

	private String id;
	
	private String name;
	
	private String climate;
	
	private String terrain;
	
	private int aparitions;

	public Planet(String id, String name, String climate, String terrain, int aparitions) {
		super();
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.aparitions = aparitions;
	}
	
	public Planet(String name, String climate, String terrain) {
		super();
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;

	}
	
	

}
