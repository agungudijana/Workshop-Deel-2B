package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.*;
import service.*;


@Configuration
public class BestellingConfig {
	
	@Bean
	public Bestelling bestelling(){
		return new Bestelling();
	}
	
	/*@Bean
	public BestelArtikel bestelartikel(){
		return new BestelArtikel();
	} 

	@Bean
	public Factuur factuur(){
		return new Factuur();
	} 

	@Bean
	public Klant klant(){
		return new Klant();
	} */
	
	@Bean
	public BestellingDaoService bestellingDaoService(){
		return new BestellingDaoService();
	}
	
	
}



