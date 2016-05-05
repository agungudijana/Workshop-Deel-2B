package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pojo.Adres;
import pojo.KlantAdres;

@Configuration
public class AdresConfig {
		
	@Bean
	public Adres adres(){
		return new Adres();
	}
			
	@Bean
	public KlantAdres klantAdres(){
		return new KlantAdres();
	}
}