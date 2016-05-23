package jUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pojo.*;
import service.*;
import springconfig.AppConfig;


public class BestellingTest {

	private static final Logger logger =  LoggerFactory.getLogger(BestellingTest.class);

	private Klant klant = new Klant();	
	private KlantAdres klantAdres = new KlantAdres();	
	private Adres adres = new Adres();
	private Account account = new Account();

	private Artikel artikel = new Artikel();
	private BestelArtikel bestelArtikel = new BestelArtikel();
	private Bestelling bestelling = new Bestelling();
	private Factuur factuur = new Factuur();
	private Betaling betaling = new Betaling();

	private Artikel nieuweArtikel = new Artikel();
	private Artikel nieuweArtikel2 = new Artikel();
	private BestelArtikel nieuweBestelArtikel = new BestelArtikel();
	private Bestelling nieuweBestelling = new Bestelling();
	private Factuur nieuweFactuur = new Factuur();
	private Betaling nieuweBetaling = new Betaling();
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	private GenericDaoService service = (GenericDaoService) ctx.getBean("genericDaoService");


	@Before
	public void setUp() {

		klant.setVoornaam("jUnitTest");
		klant.setTussenvoegsel("van");
		klant.setAchternaam("BestellingDao");
		klant.setEmail("test@test.com");
		service.persist(klant);

		artikel.setArtikel_naam("jUnitTest");
		artikel.setArtikel_nummer();
		artikel.setArtikel_prijs(69.69);
		artikel.setOmschrijving("jUnit testartikel");
		service.persist(artikel);

		adres.setHuisnummer(123);
		adres.setPostcode("90210");
		adres.setStraatnaam("jUnitstraat");
		adres.setToevoeging("A");
		adres.setWoonplaats("Amsterdam");
		service.persist(adres);

		klantAdres.setKlant(klant);
		klantAdres.setAdres(adres);
		klantAdres.setAdresType(KlantAdres.AdresType.Postadres);
		service.persist(klantAdres);

		account.setAccountNaam("jUnit account");
		account.setDateCreated();
		account.setKlant(klant);
		service.persist(account);

		bestelling.setBestelDatum();
		bestelling.setBestelNummer();
		bestelling.setKlant(klant);
		service.persist(bestelling);

		bestelArtikel.setArtikel(artikel);
		bestelArtikel.setAantal(69);
		bestelArtikel.setBestelling(bestelling);
		bestelling.bestellingHasArtikelen.add(bestelArtikel);
		service.persist(bestelArtikel);

		bestelling.setBestellingHasArtikelen(bestelling.bestellingHasArtikelen);
		service.saveOrUpdate(bestelling);

		factuur.setFactuurDatum();
		factuur.setFactuurNummer();
		factuur.setKlant(klant);
		factuur.setBestelling(bestelling);

		betaling.setBetaalDatum();
		betaling.setBetaalwijze(Betaling.Betaalwijze.Pinbetaling);    
		betaling.setBetalingsGegevens("jUnit testbetaling");
		betaling.setKlant(klant);			
		betaling.setFactuur(factuur);

		factuur.betalingSet.add(betaling);

		service.persist(factuur);	
	}


	@After
	public void tearDown() {

		service.deleteKlant(klant.getId());
		service.deleteArtikel(artikel.getId());
		service.deleteAdres(adres.getId());		
	} 


	@Test
	public void testCreate() {

		logger.info("klant is: " + klant);	

		nieuweBestelling.setBestelDatum();
		nieuweBestelling.setBestelNummer();
		nieuweBestelling.setKlant(klant);
		service.persist(nieuweBestelling);

		nieuweArtikel.setArtikel_naam("jUnitCreate");
		nieuweArtikel.setArtikel_nummer();
		nieuweArtikel.setArtikel_prijs(123.99);
		nieuweArtikel.setOmschrijving("jUnit createartikel");
		service.persist(nieuweArtikel);

		nieuweBestelArtikel.setArtikel(nieuweArtikel);
		nieuweBestelArtikel.setAantal(123);
		nieuweBestelArtikel.setBestelling(nieuweBestelling);
		nieuweBestelling.bestellingHasArtikelen.add(nieuweBestelArtikel);
		service.persist(nieuweBestelArtikel);

		nieuweBestelling.setBestellingHasArtikelen(nieuweBestelling.bestellingHasArtikelen);
		service.saveOrUpdate(nieuweBestelling);

		nieuweFactuur.setFactuurDatum();
		nieuweFactuur.setFactuurNummer();
		nieuweFactuur.setKlant(klant);
		nieuweFactuur.setBestelling(nieuweBestelling);

		nieuweBetaling.setBetaalDatum();
		nieuweBetaling.setBetaalwijze(Betaling.Betaalwijze.IDeal);    
		nieuweBetaling.setBetalingsGegevens("jUnit testbetaling");
		nieuweBetaling.setKlant(klant);			
		nieuweBetaling.setFactuur(nieuweFactuur);

		nieuweFactuur.betalingSet.add(nieuweBetaling);

		service.persist(nieuweFactuur);	

		assertEquals("the first bestelling_id + 1 must equal the second bestelling_id", ((int)bestelling.getId() + 1), (int)nieuweBestelling.getId());
		assertEquals("the first factuur_id + 1 must equal the second factuur_id", ((int)factuur.getId() + 1), (int)nieuweFactuur.getId());
		assertEquals("the first betaling_id + 1 must equal the second betaling_id", ((int)betaling.getId() + 1), (int)nieuweBetaling.getId());
		assertEquals("the first artikel_id + 1 must equal the second artikel_id", ((int)artikel.getId() + 1), (int)nieuweArtikel.getId());
		assertEquals("the first bestelartikel_id + 1 must equal the second bestelartikel_id", ((int)bestelArtikel.getId() + 1), (int)nieuweBestelArtikel.getId());
	} 



	@Test
	public void testUpdate() {

		List<BestelArtikel> bestelArtikelList = service.findAllBestelArtikelen();
		assertEquals(1, bestelArtikelList.size());

		bestelling.setBestelNummer();	//update bestelnummer met random waarde
		nieuweArtikel.setArtikel_naam("jUnitCreate");
		nieuweArtikel.setArtikel_nummer();
		nieuweArtikel.setArtikel_prijs(123.99);
		nieuweArtikel.setOmschrijving("jUnit createartikel");
		service.persist(nieuweArtikel);

		assertNotSame(artikel, nieuweArtikel);

		bestelArtikel.setArtikel(nieuweArtikel);	// vervang vorige bestelde artikel met nieuwe artikel
		bestelArtikel.setAantal(50);
		service.saveOrUpdate(bestelArtikel);

		nieuweArtikel2.setArtikel_nummer();
		nieuweArtikel2.setArtikel_prijs(55.50);
		nieuweArtikel2.setOmschrijving("jUnit createartikel2");
		nieuweArtikel2.setArtikel_naam("jUnitCreate2");
		service.persist(nieuweArtikel2);

		assertNotSame(nieuweArtikel, nieuweArtikel2);

		nieuweBestelArtikel.setArtikel(nieuweArtikel2);
		nieuweBestelArtikel.setBestelling(bestelling);
		nieuweBestelArtikel.setAantal(100);

		//bestelling.bestellingHasArtikelen.add(nieuweBestelArtikel); // voeg nog een nieuwe artikel toe

		service.persist(nieuweBestelArtikel); // voeg nog een nieuwe artikel toe

		betaling.setBetaalwijze(Betaling.Betaalwijze.Contant);    
		betaling.setBetalingsGegevens("UPDATED");
		service.update(betaling);

		factuur.setFactuurNummer();
		service.update(factuur);

		bestelArtikelList = service.findAllBestelArtikelen();
		assertEquals(2, bestelArtikelList.size());
	} 


	@Test
	public void testFind() {

		nieuweBestelling = service.findBestellingById(bestelling.getId());
		assertNotNull(nieuweBestelling);

		List<Bestelling> bestellingen = service.findAllBestellingen();		
		assertNotNull(bestellingen);

		nieuweFactuur = service.findFactuurById(factuur.getId());
		assertNotNull(nieuweFactuur);

		List<Factuur> facturen = service.findAllFacturen();			
		assertNotNull(facturen);	

		nieuweBetaling = service.findBetalingById(betaling.getId());
		assertNotNull(nieuweBetaling);

		List<Betaling> betalingen = service.findAllBetalingen();			
		assertNotNull(betalingen);	

		nieuweBestelArtikel = service.findBestelArtikelById(bestelArtikel.getId());
		assertNotNull(nieuweBestelArtikel);

		List<BestelArtikel> bestelArtikelen = service.findAllBestelArtikelen();			
		assertNotNull(bestelArtikelen);	

		nieuweArtikel = service.findArtikelById(artikel.getId());
		assertNotNull(nieuweArtikel);

		List<Artikel> artikelen = service.findAllArtikelen();			
		assertNotNull(artikelen);	
	} 


	@Test
	public void testDelete(){
		
		service.deleteBestelling(bestelling.getId());
		assertNull("bestelling must have been deleted", service.findBestellingById(bestelling.getId()));
		assertNull("factuur must have been deleted", service.findFactuurById(factuur.getId()));
		assertNull("betaling must have been deleted", service.findBetalingById(betaling.getId()));
		assertNull("bestelArtikel must have been deleted", service.findBestelArtikelById(bestelArtikel.getId()));
	} 


	
	@Test
	public void testDeleteAll(){

		service.deleteAllBestellingen();
		assertEquals("List bestelling must be empty", 0, service.findAllBestellingen().size());

		service.deleteAllBestelArtikellen();
		assertEquals("List bestelArtikel must be empty", 0, service.findAllBestelArtikelen().size());

		service.deleteAllFacturen();
		assertEquals("List factuur must be empty", 0, service.findAllFacturen().size());

		service.deleteAllKlantBetalingen();
		assertEquals("List betaling must be empty", 0, service.findAllBetalingen().size());
				
		/* service.deleteAll();
		assertEquals("List artikel must be empty", 0, service.findAll().size());
		*/
	} 


}
