package main;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import pojo.*;
import service.*;
import springconfig.*;

//@Configuration
//@ComponentScan
public class GenericAdresMenu {
	private static final Logger logger =  LoggerFactory.getLogger(GenericAdresMenu.class);
	
	static ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	static GenericDaoService service = (GenericDaoService) ctx.getBean("genericDaoService");
	
	public static Adres CreateAdres(){
		Scanner input = new Scanner(System.in);
		
	    	Adres adres = ctx.getBean(Adres.class); 
		
				System.out.print("Voer straatnaam in: ");
				String straatnaam = input.nextLine();
				System.out.print("Voer huisnummer in: ");
				int huisnummer = input.nextInt();
				input.nextLine();
				System.out.print("Voer toevoeging in: ");
				String toevoeging = input.nextLine();
				System.out.print("Voer postcode in: ");
				String postcode = input.nextLine();
				System.out.print("Voer woonplaats in: ");
				String woonplaats = input.nextLine();
				System.out.println("");

					adres.setStraatnaam(straatnaam);						
					adres.setHuisnummer(huisnummer);
					adres.setToevoeging(toevoeging);
					adres.setPostcode(postcode);
					adres.setWoonplaats(woonplaats);
		
		return adres;
		}
		
		public static void setAdresTypeKeuzeMenu(KlantAdres klantAdres){
			Scanner input = new Scanner(System.in);	
			//AdresTypeType adresTypeType = ctx.getBean(AdresTypeType.class);
			
				System.out.println("Geef het adrestype op: ");
				System.out.println("1. Postadres");
				System.out.println("2. Factuuradres");
				System.out.println("3. Bezoekadres");
			
					int keuze = input.nextInt();		
					switch (keuze) {
					case 1:
						klantAdres.setAdresType(klantAdres.getAdresType().Postadres);
						break;
					case 2:
						klantAdres.setAdresType(klantAdres.getAdresType().Factuuradres);
						break;
					case 3:
						klantAdres.setAdresType(klantAdres.getAdresType().Bezoekadres);
						break;			
					default:
						klantAdres.setAdresType(klantAdres.getAdresType().Postadres);
			}
		}	
		
	
	public static void main(String[] args){
			
	Scanner input = new Scanner(System.in);
	
		System.out.println("\t----------------------------");
		System.out.println("\t Adres Domain (GENERIC DAO) ");
		System.out.println("\t--------------------------\n");
		System.out.println("\t1.  Persist 	adres");
		System.out.println("\t2.  Update 	adres");
		System.out.println("\t3.  FindById 	adres");
		System.out.println("\t4.  Delete 	adres");
		System.out.println("\t5.  FindAll 	adresses");		
		System.out.println("\t6.  Persist 	klantAdres");
		System.out.println("\t7.  Delete 	klantAdres");
		System.out.println("\t8.  <Terug Naar het Hoofdmenu>");
		System.out.println("\t9.  <Stoppen>");
		System.out.print("\nVoer optie in en druk op Enter:");
		
		try{
			int keuze = input.nextInt();
			long adres_id;
			long klant_id;
			long klantAdres_id;
			Adres adres = ctx.getBean(Adres.class);
			Klant klant;
			KlantAdres klantAdres = ctx.getBean(KlantAdres.class);
			AdresType adresType;
			
			
			switch (keuze) {
			case 1:// Persist
				System.out.println("\n*** Persist Adres - Start ***");
				Adres nieuwAdres = CreateAdres();
				System.out.println("Toe te voegen nieuw adres: " + nieuwAdres);
				service.persist(nieuwAdres);
				System.out.println("Adres toegevoegd: " + nieuwAdres);
				GenericAdresMenu.main(null);				
				break;

			case 2:// Update	
				System.out.println("\n*** Update Adres - Start ***");
				System.out.println("Voer het ID in van het adres die je wil aanpassen: ");				
				adres_id = input.nextInt();
					Adres bestaandAdres = CreateAdres();	
					bestaandAdres.setId(adres_id);	
						logger.info("Adres is: " + bestaandAdres);
						service.update(bestaandAdres); 				
						GenericAdresMenu.main(null);				
				break;

			case 3:// FindById
				System.out.println("\n*** FindById Adres- Start ***");						
				System.out.print("Voer het ID in van het adres dat je wil zoeken: ");
					adres_id = input.nextLong();
				System.out.println(service.findAdresById(adres_id));					
				GenericAdresMenu.main(null);				
				break;

			case 4:// Delete						FOUTMELDING: constraint violation foreign key constraint fail
				System.out.println("\n*** Delete Adres - Start ***");
					bestaandAdres = ctx.getBean(Adres.class);	
				System.out.print("Voer het ID in van het adres die je wil deleten: ");				
					adres_id = input.nextLong();								
				service.deleteAdres(adres_id);
				GenericKlantMenu.main(null);
				break;

			case 5:// FindAll
				System.out.println("\n*** FindAll Adresses - Start ***");
				logger.info("findAll adressen aangeroepen");
					List<Adres> adressen = service.findAllAdressen();
				System.out.println("De volgende adressen staan in de Adres tabel :");
					for (Adres k : adressen) {
						System.out.println("-" + k.toString());
					}				
					GenericAdresMenu.main(null);
				break;
				
			case 6://Koppel adres aan klant
				klantAdres = ctx.getBean(KlantAdres.class);			
				System.out.println("Geef het klant ID op: ");
					klant_id = input.nextInt();
					klant = service.findKlantById(klant_id);
					klantAdres.setKlant(klant);
				System.out.println("Geef het adres ID op: ");
					adres_id = input.nextLong();				
					bestaandAdres = service.findAdresById(adres_id);
					klantAdres.setAdres(bestaandAdres);
				setAdresTypeKeuzeMenu(klantAdres);				
				//logger.info("klantAdres bevat nu: " + klantAdres);
				//logger.info("klant bevat nu: " + klant);		
				service.persist(klantAdres);				
				GenericAdresMenu.main(null);
				break;
			
			case 7:// Ontkoppel adres van klant				
				System.out.println(service.findAllKlantAdressen());
				System.out.println("Geef het klanAdres nummer op");
				klantAdres_id = input.nextLong();
				service.deleteKlantAdres(klantAdres_id);
				GenericAdresMenu.main(null);
				break;	
				
			case 8:
				GenericMainApp.main(null);
				break;
				
			case 9:
				System.out.println("De applicatie wordt nu afgesloten...");
				System.exit(0);
				break;
				
						
			default:
				System.out.println("\n! Ongeldige optie!\n");
			} 			
		}			
		finally {	
		}
	}
	
	
}
