package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.*;
import service.*;

@Configuration
public class BetalingConfig {
	
	@Bean
	public Betaling betaling(){
		return new Betaling();
	}
	
	@Bean
	public Betaalwijze betaalWijze(){
		return new Betaalwijze();
	}
	
	@Bean
	public Klant klant(){
		return new Klant();
	}

	@Bean
	public Factuur factuur(){
		return new Factuur();
	}
	
	@Bean
	public BetalingDaoService betalingDaoService(){
		return new BetalingDaoService();
	}


}



