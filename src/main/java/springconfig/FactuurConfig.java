package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.*;
import service.*;


@Configuration
public class FactuurConfig {
	
	@Bean
	public Factuur factuur(){
		return new Factuur();
	}
	
	@Bean
	public Betaling betaling(){
		return new Betaling();
	}
		
	@Bean
	public Bestelling bestelling(){
		return new Bestelling();
	}
	
	@Bean
	public Klant klant(){
		return new Klant();
	}
	
	@Bean
	public FactuurDaoService factuurDaoService(){
		return new FactuurDaoService();
	}
	
	
}



