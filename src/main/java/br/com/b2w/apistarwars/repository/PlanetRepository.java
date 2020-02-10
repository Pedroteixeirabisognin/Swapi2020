package br.com.b2w.apistarwars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.b2w.apistarwars.model.dao.PlanetDao;


@Repository
public interface PlanetRepository extends MongoRepository<PlanetDao, String> {

	Optional<List<PlanetDao>> findByNameContaining(String nome);
	
}
