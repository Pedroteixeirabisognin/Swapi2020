package br.com.b2w.apistarwars.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.apistarwars.exception.BadRequest;
import br.com.b2w.apistarwars.exception.ServiceUnavailable;
import br.com.b2w.apistarwars.model.Planet;
import br.com.b2w.apistarwars.model.dao.PlanetDao;
import br.com.b2w.apistarwars.model.to.ResponsePlanetTo;
import br.com.b2w.apistarwars.service.PlanetService;
import br.com.b2w.apistarwars.util.URL;
import br.com.b2w.apistarwars.webservice.ClientSwapi;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanetController.class);

	private ClientSwapi clientSwapi;

	private PlanetService planetService;

	private List<ResponsePlanetTo> result = new ArrayList<ResponsePlanetTo>();

	@Autowired
	public PlanetController(PlanetService planetService, ClientSwapi clientSwapi) {
		this.clientSwapi = clientSwapi;
		this.planetService = planetService;
	}

	@PostMapping
	public ResponseEntity<Void> savePlanet(@RequestBody PlanetDao planet) {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(planetService.savePlanet(verifyPlanet(planet))).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Planet> findById(@PathVariable("id") String id) {
		
		try {
		PlanetDao planet = planetService.findById(id);
		this.result = clientSwapi.getPlanetsFromApi();
		return ResponseEntity.ok().body(new Planet(planet.getId(), planet.getName(), planet.getClimate(),
				planet.getTerrain(),  clientSwapi.getAparitions(this.result,planet.getName())));
	
		}catch (NullPointerException e) {
			throw new ServiceUnavailable("Error fetching planet");
		}
	}

	@GetMapping(value = "/findname")
	public ResponseEntity<List<Planet>> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
		List<Planet> response = new ArrayList<Planet>();
		this.result = clientSwapi.getPlanetsFromApi();
		int aparitions = clientSwapi.getAparitions(this.result, name);
		for (PlanetDao x : planetService.findByName(URL.decodeParam(name))) {

			response.add(new Planet(x.getId(), x.getName(), x.getClimate(), x.getTerrain(), aparitions ));
		}
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePlanet(@PathVariable String id) {
		planetService.deletePlanet(id);
		return ResponseEntity.noContent().build();
	}



	public PlanetDao verifyPlanet(PlanetDao obj) {
		try {
			if (obj.getName().isEmpty() || obj.getName().equals(null)) {
				throw new BadRequest("Name empty");
			}
			if (obj.getClimate().isEmpty() || obj.getClimate().equals(null)) {
				throw new BadRequest("Climate empty");
			}
			if (obj.getTerrain().isEmpty() || obj.getTerrain().equals(null)) {
				throw new BadRequest("Ground empty");
			}
		} catch (Exception e) {
			LOGGER.error("verifyPlanet()");
			throw new BadRequest("Error inserting null");
		}
		return obj;

	}

}
