package br.com.b2w.apistarwars.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.b2w.apistarwars.model.dao.PlanetDao;
import br.com.b2w.apistarwars.model.to.PlanetToReturn;
import br.com.b2w.apistarwars.repository.PlanetRepository;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetEndpointTest {

	protected final String BASE_PATH = "http://localhost:";

	
	@Autowired
	private TestRestTemplate restTeamplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private PlanetRepository planetRepository;
	
	@Test
	public void _1_testa_Insercao_Planeta() {
		
		PlanetDao planeta = new PlanetDao("Luke","Teste","Teste");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		ResponseEntity<String> response = restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		Assertions.assertEquals(201, response.getStatusCodeValue());

	}
	
	
	@Test
	public void _2_testa_Insercao_Nome_Vazio() {
		PlanetDao planeta = new PlanetDao("","Teste","Teste");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		try {
			restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		}catch (Exception e) {
			Assertions.assertEquals("400 null", e.getMessage());
		}
	}
	@Test
	public void _3_testa_Insercao_Clima_Vazio() {
		PlanetDao planeta = new PlanetDao("Darth Vader","","Teste");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		try {
			restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		}catch (Exception e) {
			Assertions.assertEquals("400 null", e.getMessage());
		}
	}
	 
	@Test
	public void _4_testa_Insercao_Terreno_Vazio() {
		PlanetDao planeta = new PlanetDao("Yoda","Teste","");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		try {
			restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		}catch (Exception e) {
			Assertions.assertEquals("400 null", e.getMessage());
		}
	}
	
	@Test
	public void _5_testa_Insercao_Nome_NULL() {
		PlanetDao planeta = new PlanetDao(null,"Teste","Teste");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		try {
			restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		}catch (Exception e) {
			Assertions.assertEquals("400 null", e.getMessage());
		}
	}
	
	@Test
	public void _6_testa_Insercao_Clima_NULL() {
		PlanetDao planeta = new PlanetDao("Chewbacca",null,"Teste");
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		try {
			restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		}catch (Exception e) {
			Assertions.assertEquals("400 null", e.getMessage());
		}
	}
	
	@Test
	public void _7_testa_Insercao_Terreno_NULL() {
		PlanetDao planeta = new PlanetDao("Han Solo","Teste",null);
		BDDMockito.when(planetRepository.save(planeta)).thenReturn(planeta);

		try {
			restTeamplate.postForEntity(BASE_PATH + port +"/planets/",planeta,String.class);
		}catch (Exception e) {
			Assertions.assertEquals("400 null", e.getMessage());
		}
	}
		
	@Test
	public void _8_testa_Busca_ID() {
		Optional<PlanetDao> planeta = Optional.of(new PlanetDao("123","Léia","Teste", "Teste"));
			
		BDDMockito.when(planetRepository.findById("123")).thenReturn(planeta);

		ResponseEntity<String>  respostaBusca = restTeamplate.getForEntity(BASE_PATH + port +"/planets/" + "123", String.class);
		Assertions.assertEquals(200, respostaBusca.getStatusCodeValue());
		
	}
	
	@Test
	public void _9_testa_Busca_ID_Quando_Nome_Nao_Existe_No_Swapi() {
		Optional<PlanetDao> planeta = Optional.of(new PlanetDao("123","Léia","Teste", "Teste"));
			
		BDDMockito.when(planetRepository.findById("123")).thenReturn(planeta);

		ResponseEntity<PlanetToReturn>  respostaBusca = restTeamplate.getForEntity(BASE_PATH + port +"/planets/" + "123", PlanetToReturn.class);
		Assertions.assertEquals(0, respostaBusca.getBody().getAparitions());
		
	}
	
	@Test
	public void _10_testa_Busca_ID_Quando_Nome_Existe_No_Swapi() {
		Optional<PlanetDao> planeta = Optional.of(new PlanetDao("123","Alderaan","Teste", "Teste"));
			
		BDDMockito.when(planetRepository.findById("123")).thenReturn(planeta);

		ResponseEntity<PlanetToReturn>  respostaBusca = restTeamplate.getForEntity(BASE_PATH + port +"/planets/" + "123", PlanetToReturn.class);
		Assertions.assertEquals(2, respostaBusca.getBody().getAparitions());
		
	}
	
	@Test
	public void _11_testa_Busca_ID_Se_Nao_Existe() {
		try {
			restTeamplate.getForEntity(BASE_PATH + port +"/planetas/buscaid?id=Teste", String.class);
		}catch(Exception e) {
			Assertions.assertEquals("404 null", e.getMessage());
		}
	}
	
	@Test
	public void _12_testa_Busca_Nome() {

		List<PlanetDao> list = new ArrayList<PlanetDao>();
		list.add(new PlanetDao("123","Anakin","Teste", "Teste"));
		list.add(new PlanetDao("122","Anakin","Teste", "Teste"));
		list.add(new PlanetDao("124","Anakin","Teste", "Teste"));
		
		Optional<List<PlanetDao>>  planetas = Optional.of(list);
		BDDMockito.when(planetRepository.findByNameContaining("Anakin")).thenReturn(planetas);

		
		ResponseEntity<String>  respostaBusca = restTeamplate.getForEntity(BASE_PATH + port +"/planets/findname?name=Anakin", String.class);
		Assertions.assertEquals(200, respostaBusca.getStatusCodeValue());
		

	}
	
	@Test
	public void _13_testa_Busca_Nome_Se_Nao_Existe() {
		ResponseEntity<String> response = restTeamplate.getForEntity(BASE_PATH + port +"/planets/findname?name=Teste", String.class);
		Assertions.assertEquals(404, response.getStatusCodeValue());
	}
	
	@Test
	public void _14_testa_Busca_Todos() {
		PlanetDao planeta1 = new PlanetDao("Alderaan","Teste", "Teste");
		PlanetDao planeta2 = new PlanetDao("Yavin IV","Teste", "Teste");
		PlanetDao planeta3 = new PlanetDao("A","Teste", "Teste");
		PlanetDao planeta4 = new PlanetDao("Utapau","Teste", "Teste");
		List<PlanetDao>  planetas = new ArrayList<PlanetDao>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		
		BDDMockito.when(planetRepository.findAll()).thenReturn(planetas);

		ResponseEntity<String> response = restTeamplate.getForEntity(BASE_PATH + port +"/planets/", String.class);
		
		
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void _15_testa_Deletar_Sem_Existir_Id() {
   		ResponseEntity<String> respostaBusca  = restTeamplate.exchange(BASE_PATH + port +"/planets/"+"123", HttpMethod.DELETE, criaHeader() , String.class);
		Assertions.assertEquals(404, respostaBusca.getStatusCodeValue());
	}


	private HttpEntity<String> criaHeader() {
	    HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}
