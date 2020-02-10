package br.com.b2w.apistarwars.model.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "results" })
public class ResponsePlanetTo {

	private String next;
	
	private List<PlanetTo> results;
	
}
