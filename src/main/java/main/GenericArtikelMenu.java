package main;


import service.*;
import springconfig.*;

import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pojo.*;


public class GenericArtikelMenu { 

	private static final Logger logger =  LoggerFactory.getLogger(GenericArtikelMenu.class);

	public static void main(String[] args){

		
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		GenericDaoService artikelService = (GenericDaoService) ctx.getBean("genericDaoService");
		Klant klant = ctx.getBean(Klant.class);
		Artikel artikel = ctx.getBean(Artikel.class);
		
		System.out.println("\t-------------------------------");
		System.out.println("\t Artikel Domain (GENERIC DAO)  ");
		System.out.println("\t-------------------------------");
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
				//Artikel nieuweArtikel = new Artikel();
				
				input.nextLine();
				System.out.println("Voer het artikelnaam in wat in het assortiment geplaatst wordt: ");
				String artikelNaam = input.nextLine();		
				
				artikel.setArtikel_naam(artikelNaam);
				artikel.setArtikel_nummer();
				
				System.out.println("Voer de omschrijving van het artikel in: ");
				String artikelOmschrijving = input.nextLine();	
												
				artikel.setOmschrijving(artikelOmschrijving);
				
				System.out.println("Bepaal de prijs van het artikel: ");
				double artikelPrijs = input.nextDouble();	
				
				artikel.setArtikel_prijs(artikelPrijs);
				
				artikelService.persist(artikel);
				System.out.println("Artikel toegevoegd: " + artikel);
				
				GenericArtikelMenu.main(null);
				break;

			case 2:
				
				System.out.println("*** Update - start ***");	
				
				System.out.print("Voer het ID in van het artikel die u wilt aanpassen: ");				
				long artikelId = input.nextLong();
				input.nextLine();
				
				artikel = artikelService.findArtikelById(artikelId);
				
				System.out.println("Voer de naam van het artikel in: ");
				String nieuwArtikelNaam = input.nextLine();
				
				artikel.setArtikel_naam(nieuwArtikelNaam);
				
				System.out.println("Voer de artikel omschrijving in: ");
				String nieuwArtikelOmschrijving = input.nextLine();
				
				artikel.setOmschrijving(nieuwArtikelOmschrijving);	
				
				System.out.println("Bepaal de prijs van het artikel: ");
				double nieuwArtikelPrijs = input.nextDouble();
				
				artikel.setArtikel_prijs(nieuwArtikelPrijs);
				
				System.out.println("het artikelnummer blijft hetzelfde");
				//System.out.println(artikelService.fId(artikelId));			

				logger.info("Artikel is: " + artikel);
				artikelService.saveOrUpdate(artikel); 
				
				GenericArtikelMenu.main(null);
				break;

			case 3:
				System.out.println("*** findById - start ***");			
							
				System.out.print("Voer het ID in van het artikel die je wil zoeken: ");
				artikelId = input.nextLong();
				
				System.out.println(artikelService.findArtikelById(artikelId));				
				
				GenericArtikelMenu.main(null);
				break;

			case 4:
				System.out.println("*** Delete - start ***");
				//bestaandeArtikel = new Artikel();	
				
				System.out.print("Voer het ID in van het artikel die je wil deleten: ");				
				artikelId = input.nextLong();				
				artikelService.deleteArtikel(artikelId);
				
				GenericArtikelMenu.main(null);
				break;

			case 5:
				logger.info("findAll artikellen aangeroepen");
				List<Artikel> artikellen = artikelService.findAllArtikelen();

				System.out.println("De volgende artikellen staan in het assortiment:");

				for (Artikel a : artikellen) {
					System.out.println("-" + a.toString());
				}

				GenericArtikelMenu.main(null);
				break;
				
			case 6:
				GenericMainApp.main(null);
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
