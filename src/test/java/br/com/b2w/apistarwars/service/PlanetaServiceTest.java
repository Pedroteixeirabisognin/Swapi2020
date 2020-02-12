package br.com.b2w.apistarwars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.b2w.apistarwars.model.dao.PlanetDao;
import br.com.b2w.apistarwars.repository.PlanetRepository;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetaServiceTest {

	@Autowired
	private PlanetService planetService;
	
	@LocalServerPort
	private int port;

	@MockBean
	private PlanetRepository planetRepository;
	
	
	@Test
	public void testa_Insere() {
		PlanetDao planeta = new PlanetDao("123","Bluf","frozen","tundra");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);
		
		PlanetDao planetaRetorno = planetService.savePlanet(planeta);
		Assertions.assertEquals(planetaRetorno.getName(), planeta.getName());
	}
	
	@Test
	public void testa_Listar_Por_Nome() {
		PlanetDao planeta1 = new PlanetDao("123","Teste1","Teste", "Teste");
		PlanetDao planeta2 = new PlanetDao("122","Teste2","Teste", "Teste");
		PlanetDao planeta3 = new PlanetDao("124","Teste3","Teste", "Teste");
		PlanetDao planeta4 = new PlanetDao("125","Teste4","Teste", "Teste");
		List<PlanetDao>  planetas = new ArrayList<PlanetDao>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		
		Optional<List<PlanetDao>> list = Optional.of(planetas);
		
		BDDMockito.when(planetRepository.findByNameContaining("Teste1")).thenReturn(list);
		
		List<PlanetDao> planetasRetorno = planetService.findByName(planeta1.getName());
		Assertions.assertEquals(planetasRetorno.get(0).getName(), planeta1.getName());
	}
	
	@Test
	public void testa_Encontrar_Por_ID() {
		PlanetDao planeta = new PlanetDao("123","TesteNovo","Teste", "Teste");
		Optional<PlanetDao> planetaOpt = Optional.of(planeta);
		BDDMockito.when(planetRepository.findById(planeta.getId())).thenReturn(planetaOpt);
		
		PlanetDao planetasRetorno = planetService.findById(planeta.getId());
		Assertions.assertEquals(planetaOpt.get(), planetasRetorno);
	}
	
	@Test
	public void testa_Encontra_Por_ID_Nao_Existente() {
		try {
			planetService.findById("");
		}catch(Exception e) {
			Assertions.assertEquals("Id not found!!", e.getMessage() );
		}
	}
	

	
}
