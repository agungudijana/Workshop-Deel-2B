package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.*;
import service.*;


@Configuration
public class KlantConfig {
	
	@Bean
	public Klant klant(){
		return new Klant();
	}
	
	@Bean
	public Bestelling bestelling(){
		return new Bestelling();
	}

	@Bean
	public Factuur factuur(){
		return new Factuur();
	}

	@Bean
	public Account account(){
		return new Account();
	}
	
	@Bean
	public KlantDaoService klantDaoService(){
		return new KlantDaoService();
	}
	
	
}



