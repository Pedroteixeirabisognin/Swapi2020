package br.com.b2w.apistarwars.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.apistarwars.model.to.PlanetTo;
import br.com.b2w.apistarwars.model.to.ResponsePlanetTo;

@Component
public class ClientSwapi {
	
	private static final String url = "https://swapi.co/api/planets/";
	
	private RestTemplate restTemplate = new RestTemplate();

	
	@Cacheable(value="responseplanetto")
	public List<ResponsePlanetTo> getPlanetsFromApi() {

			List<ResponsePlanetTo> list = new ArrayList<ResponsePlanetTo>();
			
			do {

				if(list.size()== 0) {
					
					list.add(restTemplate.exchange(url, HttpMethod.GET,geraHeader(), ResponsePlanetTo.class).getBody());

				}
				
				list.add(restTemplate.exchange(list.get(list.size()-1).getNext(), HttpMethod.GET,geraHeader(), ResponsePlanetTo.class).getBody());

				
			}while(list.get(list.size()-1).getNext()!=null);
			
			
			return list;



	}

	public int getAparitions(List<ResponsePlanetTo> lista, String name) {
		
		
		for(ResponsePlanetTo x :lista) {
			
		
			for(PlanetTo y : x.getResults()) {
	
				if(y.getName().equals(name)) {

					return y.getFilms().size();
				}
			
			}
		}
		return 0;
		

	}
	
	
	public HttpEntity<String> geraHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}
	
	

}
