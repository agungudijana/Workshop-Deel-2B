package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.*;
import service.*;


@Configuration
public class ArtikelConfig {
	
	@Bean
	public Artikel artikel(){
		return new Artikel();
	}

	@Bean
	public ArtikelDaoService artikelDaoService(){
		return new ArtikelDaoService();
	}
	
	
}



