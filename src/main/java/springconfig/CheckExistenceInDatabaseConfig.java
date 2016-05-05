package springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;


@Configuration
public class CheckExistenceInDatabaseConfig {
	
	@Bean
	public CheckExistenceInDatabase checkExistenceInDatabase(){
		return new CheckExistenceInDatabase();
	}


	
	
}



