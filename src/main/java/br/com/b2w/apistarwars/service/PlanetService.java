package br.com.b2w.apistarwars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

	@Cacheable(value="planetdao")
	public PlanetDao findById(String id) {
		return  planetRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Id not found!!"));
	}

	@Cacheable(value="listplanetdao")
	public List<PlanetDao> findByName(String name){
		return planetRepository.findByNameContaining(name)
				.orElseThrow(() -> new ObjectNotFoundException("Name not found!!"));
	}
	
	public void deletePlanet(String id) {
		planetRepository.delete(findById(id));
	}
	
	
	
}
