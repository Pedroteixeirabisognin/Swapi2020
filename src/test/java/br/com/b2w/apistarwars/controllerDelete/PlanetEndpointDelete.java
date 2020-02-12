package br.com.b2w.apistarwars.controllerDelete;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
import br.com.b2w.apistarwars.service.PlanetService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetEndpointDelete {

	@MockBean
	private PlanetService planetService;
	
	protected final String BASE_PATH = "http://localhost:";

	@Autowired
	private TestRestTemplate restTeamplate;
	
	@LocalServerPort
	private int port;
	

	@Test
	public void _16_testa_Deletar() {

		PlanetDao planeta = new PlanetDao("Obi-Wan","Teste", "Teste");
		BDDMockito.when(planetService.deletePlanet("123")).thenReturn("123");
		ResponseEntity<String> respostaBusca  = restTeamplate.exchange(BASE_PATH + port +"/planets/"+"123", HttpMethod.DELETE, criaHeader() , String.class,planeta);
		Assertions.assertEquals(204, respostaBusca.getStatusCodeValue());
	}
	
	private HttpEntity<String> criaHeader() {
	    HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}
