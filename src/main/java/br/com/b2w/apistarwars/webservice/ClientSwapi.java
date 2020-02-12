package br.com.b2w.apistarwars.webservice;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.apistarwars.model.to.ResponsePlanetTo;

@Component
public class ClientSwapi {
	
	@Value("${starwars.api.url}")
	private String url;

	
	@Cacheable(value="responseplanetto")
	public int getPlanetAparition(String name) {
		RestTemplate restTemplate = new RestTemplate();

		ResponsePlanetTo response = new ResponsePlanetTo();
		
							
		response = restTemplate.exchange(url+name, HttpMethod.GET,geraHeader(), ResponsePlanetTo.class).getBody();

		try {
			if(response.getResults().get(0).getName().equals(name))			
				return response.getResults().get(0).getFilms().size();
			else
				return 0;
		}catch (IndexOutOfBoundsException  e) {
			return 0;
		}

	}

		
	public HttpEntity<String> geraHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}
	
	

}
