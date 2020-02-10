package br.com.b2w.apistarwars.model.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "name","climate","terrain","films" })
public class PlanetTo {

	private String name;
	
	private String climate;
	
	private String terrain;
	
	private List<String> films;
}
