package springconfig;

import org.springframework.context.annotation.Bean;

import pojo.*;
import service.*;

public class SpringConfig {
	
	
	@Bean
	public Artikel artikel(){
		return new Artikel();
	}

	@Bean
	public ArtikelDaoService artikelDaoService(){
		return new ArtikelDaoService();
	}
	
	@Bean
	public BestelArtikel bestelArtikel(){
		return new BestelArtikel();
	}
	
	@Bean
	public BestelArtikelDaoService bestelArtikelDaoService(){
		return new BestelArtikelDaoService();
	}
	
	@Bean
	public Bestelling bestelling(){
		return new Bestelling();
	}
	
	@Bean
	public BestellingDaoService bestellingDaoService(){
		return new BestellingDaoService();
	}
	
	@Bean
	public Betaling betaling(){
		return new Betaling();
	}
	
	@Bean
	public Betaalwijze betaalWijze(){
		return new Betaalwijze();
	}
	
	@Bean
	public BetalingDaoService betalingDaoService(){
		return new BetalingDaoService();
	}
	
	@Bean
	public Factuur factuur(){
		return new Factuur();
	}

	@Bean
	public FactuurDaoService factuurDaoService(){
		return new FactuurDaoService();
	}
	
	@Bean
	public Klant klant(){
		return new Klant();
	}

	@Bean
	public Account account(){
		return new Account();
	}
	
	@Bean
	public KlantDaoService klantDaoService(){
		return new KlantDaoService();
	}

	@Bean
	public AccountDaoService accountDaoService(){
		return new AccountDaoService();
	}
	
	@Bean
	public AdresDaoService adresDaoService(){
		return new AdresDaoService();
	}
	
	@Bean
	public KlantAdresDaoService klantAdresDaoService(){
		return new KlantAdresDaoService();
	}
	
	@Bean
	public Adres adres(){
		return new Adres();
	}
	
	@Bean
	public KlantAdres klantAdres(){
		return new KlantAdres();
	}
	
	@Bean
	public AdresType adresType(){
		return new AdresType();
	}
	
}
