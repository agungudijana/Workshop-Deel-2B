package main;


import service.*;

import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.*;


public class GenericMenu { 

	private static final Logger logger =  LoggerFactory.getLogger(GenericMenu.class);

	public static void main(String[] args){

		GenericDaoService service = new GenericDaoService();

		System.out.println("\t-----------------------");
		System.out.println("\t Generic Klant Domain  ");
		System.out.println("\t-----------------------");
		System.out.println("1. persist");
		System.out.println("2. update");
		System.out.println("3. findById");
		System.out.println("4. delete");
		System.out.println("5. findAll");
		System.out.println("6. <Terug Naar het Hoofdmenu>");
		System.out.println("7. <Stoppen>");
		System.out.print("Voer optie in en druk op Enter:");


		try (Scanner input = new Scanner(System.in);) {		     

			int keuze = input.nextInt();

			switch (keuze) {
			case 1:
				System.out.println("*** Persist - start ***");
				Klant nieuweKlant = new Klant();
				input.nextLine();
				System.out.print("Voer voornaam in: ");
				String voornaam = input.nextLine();		
				System.out.print("Voer tussenvoegsel in: ");
				String tussenvoegsel = input.nextLine();
				System.out.print("Voer achternaam in: ");
				String achternaam = input.nextLine();
				System.out.print("Voer email in: ");
				String email = input.nextLine();

				nieuweKlant.setVoornaam(voornaam);						
				nieuweKlant.setTussenvoegsel(tussenvoegsel);
				nieuweKlant.setAchternaam(achternaam);
				nieuweKlant.setEmail(email);

				System.out.println("Toe te voegen nieuwe klant: " + nieuweKlant);
				//logger.info("klant is: " + nieuweKlant);
				service.saveOrUpdate(nieuweKlant);
				System.out.println("Klant toegevoegd: " + nieuweKlant);
				
				GenericMenu.main(null);
				break;

			case 2:
				
				System.out.println("*** Update - start ***");
				Klant bestaandeKlant = new Klant();	
				Scanner input2 = new Scanner(System.in);
				System.out.print("Voer het ID in van de klant die je wil aanpassen: ");				
				int id = input2.nextInt();
				input2.nextLine();
				System.out.print("Voer nieuwe voornaam in: ");
				voornaam = input2.nextLine();
				System.out.print("Voer nieuwe tussenvoegsel in: ");
				tussenvoegsel = input2.nextLine();
				System.out.print("Voer nieuwe achternaam in: ");
				achternaam = input2.nextLine();
				System.out.print("Voer nieuwe email in: ");
				email = input2.nextLine();

				bestaandeKlant.setId(id);
				bestaandeKlant.setVoornaam(voornaam);						
				bestaandeKlant.setTussenvoegsel(tussenvoegsel);
				bestaandeKlant.setAchternaam(achternaam);
				bestaandeKlant.setEmail(email);
				logger.info("klant is: " + bestaandeKlant);
				service.saveOrUpdate(bestaandeKlant); 
				
				GenericMenu.main(null);
				break;

			case 3:
				System.out.println("*** findById - start ***");			
				bestaandeKlant = new Klant();				
				Scanner input3 = new Scanner(System.in);
				System.out.print("Voer het ID in van de klant die je wil zoeken: ");
				Long id2 = input3.nextLong();
				//bestaandeKlant.setId(id2);
				System.out.println(service.findById(bestaandeKlant, id2));	
				
				GenericMenu.main(null);
				break;

			case 4:
				System.out.println("*** Delete - start ***");
			
				System.out.print("Voer het ID in van de klant die je wil deleten: ");				
				id2 = input.nextLong();	
			
				service.delete(id2);
				
				GenericMenu.main(null);
				break;

			case 5:
				logger.info("findAll klanten aangeroepen");
				List<Klant> entities = service.findAll();

				System.out.println("De volgende klanten staan in de Klant tabel :");

				for (Klant k : entities) {
					System.out.println("-" + k.toString());
				}

				GenericMenu.main(null);
				break;
				
				
			case 6:
				MainApp.main(null);
				break;
				
			case 7:
				System.out.println("De applicatie wordt nu afgesloten...");
				System.exit(0);
				break;

			default:
				System.out.println("\n! Ongeldige optie!\n");

			} 

		}

		finally {
			// zinnige code			
		}	



	}

}
