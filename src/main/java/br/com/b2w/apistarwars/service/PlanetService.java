package br.com.b2w.apistarwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.apistarwars.exception.ObjectNotFoundException;
import br.com.b2w.apistarwars.model.dao.PlanetDao;
import br.com.b2w.apistarwars.repository.PlanetRepository;

@Service
public class PlanetService {

	private PlanetRepository planetRepository; 
	
	@Autowired
	public PlanetService(PlanetRepository repo) {
		this.planetRepository = repo;
	}
		
	public PlanetDao savePlanet(PlanetDao obj) {
		return planetRepository.save(obj);
	}

	public PlanetDao findById(String id) {
		return  planetRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Id not found!!"));
	}

	public List<PlanetDao> findAll(){
		return planetRepository.findAll();				
	}
	
	public List<PlanetDao> findByName(String name){
		return planetRepository.findByNameContaining(name)
				.orElseThrow(() -> new ObjectNotFoundException("Name not found!!"));
	}
	
	public String deletePlanet(String id) {
		planetRepository.delete(findById(id));
		return id;
	}
	
	
	
}
