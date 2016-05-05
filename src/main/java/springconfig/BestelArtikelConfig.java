package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.*;
import service.*;


@Configuration
public class BestelArtikelConfig {
	
	@Bean
	public BestelArtikel bestelArtikel(){
		return new BestelArtikel();
	}
	
	@Bean
	public Artikel artikel(){
		return new Artikel();
	}

	@Bean
	public Bestelling bestelling(){
		return new Bestelling();
	}

	@Bean
	public BestelArtikelDaoService bestelArtikelDaoService(){
		return new BestelArtikelDaoService();
	}
	
	
}



