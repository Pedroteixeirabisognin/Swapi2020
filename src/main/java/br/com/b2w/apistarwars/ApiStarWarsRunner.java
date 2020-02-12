package br.com.b2w.apistarwars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.b2w.apistarwars.model.dao.PlanetDao;
import br.com.b2w.apistarwars.repository.PlanetRepository;

@Component
@Profile("!test")
public class ApiStarWarsRunner implements CommandLineRunner {
	
	private PlanetRepository planetrepo;
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiStarWarsRunner.class);
	
	@Autowired
	private ApiStarWarsRunner(PlanetRepository planetrepo) {
		this.planetrepo = planetrepo;
	}
	
	@Override
	public void run(String... args) throws Exception {
		generateDataBase();	
	}
	
	public void generateDataBase() throws Exception {

		PlanetDao planet;
		LOGGER.info("A Long Time Ago, in a Galaxy Far Far Away...");
		LOGGER.info("-------------------------------");
		if(planetrepo.count() == 0) {
			planet = planetrepo.save(new PlanetDao());
			planetrepo.deleteById(planet.getId());
		}
		
	}

}
