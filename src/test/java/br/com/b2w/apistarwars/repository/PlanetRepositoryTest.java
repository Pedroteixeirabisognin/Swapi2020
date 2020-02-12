package br.com.b2w.apistarwars.repository;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.b2w.apistarwars.model.dao.PlanetDao;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetRepositoryTest {

	PlanetDao planeta1, planeta2;
    
	@Autowired
    PlanetRepository repository;
    
    @Before
    public void setUp() {
        
    	planeta1 = repository.save(new PlanetDao("Boba Fett", "teste","teste"));
        planeta2 = repository.save(new PlanetDao("Lando", "teste","teste"));
    }
    
    @After
    public void tearDown() {
    	
    	repository.delete(planeta1);
    	repository.delete(planeta2);
    }
    
    @Test
    public void testa_criar_planeta() {
        
    	PlanetDao planeta = repository.save(new PlanetDao("R2","teste","teste"));
        Assert.assertFalse(planeta.getId().isEmpty());
        repository.delete(planeta);
    }

    @Test
    public void testa_buscar_por_nome() {
    	
    	Optional<List<PlanetDao>> result = repository.findByNameContaining("Boba Fett");
    	Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testa_buscar_por_id() {
    	
    	Optional<PlanetDao> obj = repository.findById("Boba Fett");
    	Assert.assertNotNull(obj);
    }

    
    @Test
    public void testa_deletar_planeta() {
    	
    	Optional<List<PlanetDao>> planeta = repository.findByNameContaining("Lando");
    	repository.delete(planeta.get().get(0));
    	Optional<List<PlanetDao>> response = repository.findByNameContaining("Lando");
    	Assert.assertTrue(response.get().isEmpty());

    	Assert.assertFalse(planeta.isEmpty());
    	 
    }
	
}
