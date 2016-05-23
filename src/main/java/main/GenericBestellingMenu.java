package main;

import service.*;
import springconfig.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pojo.*;


public class GenericBestellingMenu { 

	private static final Logger logger =  LoggerFactory.getLogger(GenericBestellingMenu.class);

	public static void main(String[] args){

		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext
				(AppConfig.class,
						CheckExistenceInDatabase.class);
			
		GenericDaoService service = ctx.getBean(GenericDaoService.class); 
		CheckExistenceInDatabase checkExistenceInDatabase = ctx.getBean(CheckExistenceInDatabase.class);
					
			
		System.out.println("\t---------------------------------");
		System.out.println("\t Bestelling Domain (GENERIC DAO) ");
		System.out.println("\t---------------------------------");
		System.out.println("1. persist");
		System.out.println("2. update");
		System.out.println("3. findById");
		System.out.println("4. delete");
		System.out.println("5. findAll");
		System.out.println("6. deleteAll");
		System.out.println("7. <Terug Naar het Hoofdmenu>");
		System.out.println("8. <Stoppen>");
		System.out.print("Voer optie in en druk op Enter:");


		try (Scanner input = new Scanner(System.in);) {		     

			int keuze = input.nextInt();

			switch (keuze) {
			case 1:

				logger.info("*** Persist - start ***");

				// 4/5 mei 2016. AU. uitgecomment ivm Spring implementatie
				//Bestelling nieuweBestelling = new Bestelling();
					
				// 4/5 mei 2016. AU. aangepast ivm Spring implementatie
				Bestelling nieuweBestelling = ctx.getBean(Bestelling.class); //ipv new Bestelling();    	
				BestelArtikel nieuweBestellingHasArtikel = ctx.getBean(BestelArtikel.class);
				Factuur nieuweFactuur = ctx.getBean(Factuur.class);
				Betaling nieuweBetaling = ctx.getBean(Betaling.class);

				input.nextLine();
				System.out.print("Voer ID van klant in waarvoor je een bestelling wil plaatsen: ");
				long klant_id = input.nextInt();	

				while (checkExistenceInDatabase.checkKlant_id(klant_id)!= true) { 
					System.out.print("\nVoer een ander klant ID in: ");
					klant_id = input.nextLong();
					System.out.println();
				}    
				Klant klant = service.findKlantById(klant_id);

		
				nieuweBestelling.setBestelNummer();				
				nieuweBestelling.setBestelDatum();
				nieuweBestelling.setKlant(klant);

				logger.info("Klant :" + nieuweBestelling.getKlant());
				logger.info("bestelling_id: " + nieuweBestelling.getId());

				service.persist(nieuweBestelling);

				long artikel_id = 0;


				System.out.print("Voer ID van artikel in die je wil bestellen (0 = stoppen met artikelen toevoegen): ");
				artikel_id = input.nextLong();
				

				do {

					while (checkExistenceInDatabase.checkArtikel_id(artikel_id)!= true) { 
						System.out.print("\nVoer een ander artikel ID in: ");
						artikel_id = input.nextLong();
						System.out.println();
					}    
					
					nieuweBestellingHasArtikel.setArtikel(service.findArtikelById(artikel_id));

					System.out.print("Hoeveel wil je er van bestellen?: ");
					int aantal = input.nextInt();	

					nieuweBestellingHasArtikel.setAantal(aantal);
					nieuweBestellingHasArtikel.setBestelling(nieuweBestelling);
					nieuweBestellingHasArtikel.getId();

					logger.info("bestelartikel_id: " + nieuweBestellingHasArtikel.getId());
					logger.info("id van nieuweBestellingHasArtikel is" + nieuweBestellingHasArtikel.getId());

					nieuweBestelling.bestellingHasArtikelen.add(nieuweBestellingHasArtikel);
					service.persist(nieuweBestellingHasArtikel);

					logger.info("bestelartikel_id wordt toegevoegd aan het bestelling object");

					nieuweBestelling.setBestellingHasArtikelen(nieuweBestelling.bestellingHasArtikelen);

					logger.info("object nieuweBestelling bevat nu: " + nieuweBestelling);
					logger.info("bestelling object wordt geupdate met bestelartikel_id en geupdate in bestelling tabel");

					service.saveOrUpdate(nieuweBestelling);

					logger.info("de set bestellingHasArtikelen bevat: " + nieuweBestelling.getBestellingHasArtikelen());

					System.out.print("Voer ID van artikel in die je wil bestellen (0 = stoppen met artikelen toevoegen): ");
					artikel_id = input.nextLong();

				} while (artikel_id  != 0 )  ;



				nieuweFactuur.setFactuurDatum();
				nieuweFactuur.setFactuurNummer();
				nieuweFactuur.setKlant(klant);

				logger.info("object nieuweFactuur " + nieuweFactuur);

				nieuweFactuur.setBestelling(nieuweBestelling);

				logger.info("object nieuweFactuur na  \"nieuweFactuur.setBestelling(nieuweBestelling) \" " + nieuweFactuur);

				nieuweBestellingHasArtikel.setArtikel(service.findArtikelById(artikel_id));
				nieuweBetaling.setBetaalDatum();


				System.out.print("Hoe wil je betalen? 1 = Contant, 2 = Pinbetaling, 3 = IDeal, 4 = Creditcard : ");
				int betaalwijze = input.nextInt();

				switch (betaalwijze) {
				case 1:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Contant);
					break;
				case 2:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Pinbetaling);
					break;
				case 3:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().IDeal);
					break;
				case 4:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Creditcard);
					break;				
				default:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Pinbetaling);
				} 



				System.out.print("Voer overige betalingsgegevens in c.q. een beschrijving: ");
				input.nextLine();
				String betalingsGegevens = input.nextLine();

				nieuweBetaling.setBetalingsGegevens(betalingsGegevens);
				nieuweBetaling.setKlant(klant);			
				nieuweBetaling.setFactuur(nieuweFactuur);
				
				service.persist(nieuweFactuur);	
				service.persist(nieuweBetaling);

				logger.info("object nieuweBetaling bevat:" + nieuweBetaling);									
				logger.info("object nieuweFactuur VOOR  \"nieuweFactuur.betalingSet.add(nieuweBetaling) \" " + nieuweFactuur);

				nieuweFactuur.betalingSet.add(nieuweBetaling);

				logger.info("object nieuweFactuur NA  \"nieuweFactuur.betalingSet.add(nieuweBetaling) \" " + nieuweFactuur);

				

				logger.info("*** Persist - end ***");

				
				GenericBestellingMenu.main(null);
				break;


			case 2:

				logger.info("*** Update - start ***");

				/* input.nextLine();
				System.out.print("Voer uw Klant ID in: ");
				klant_id = input.nextInt();		*/

				System.out.print("Voer het ID van de bestelling in die u wilt veranderen: ");
				long bestelling_id = input.nextLong();

				while (checkExistenceInDatabase.checkBestelling_id(bestelling_id)!= true) { 
					System.out.print("\nVoer een ander bestelling ID in: ");
					bestelling_id = input.nextLong();
					System.out.println();
				}    


				Bestelling updateBestelling = service.findBestellingById(bestelling_id);			
				updateBestelling.setBestelDatum();
				// updateBestelling.setKlant(service.findById(klant_id));

				logger.info("Klant:" + updateBestelling.getKlant());
				logger.info("bestelling_id: " + updateBestelling.getId());


				logger.info("bestaandeBestelling bevat " + updateBestelling);

				BestelArtikel updateBestelArtikel = service.findBestelArtikelById(bestelling_id);

				logger.info("bestaandeBestelArtikel bevat " + updateBestelArtikel);

				service.saveOrUpdate(updateBestelling);

				artikel_id = 0;

				input.nextLine();
				System.out.print("Voer ID van artikel in die je wil toevoegen (0 = stoppen met artikelen toevoegen): ");
				artikel_id = input.nextLong();
				

				do {

					while (checkExistenceInDatabase.checkArtikel_id(artikel_id)!= true) { 
						System.out.print("\nVoer een ander artikel ID in: ");
						artikel_id = input.nextLong();
						System.out.println();
					}    
					
					
					updateBestelArtikel.setArtikel(service.findArtikelById(artikel_id));

					logger.info("updateBestelArtikel bevat: " + updateBestelArtikel);

					System.out.print("Hoeveel wil je er van bestellen?: ");
					int aantal = input.nextInt();	

					updateBestelArtikel.setAantal(aantal);
					updateBestelArtikel.setBestelling(updateBestelling);

					updateBestelling.bestellingHasArtikelen.add(updateBestelArtikel);
					service.persist(updateBestelArtikel);

					logger.info("bestelartikel wordt geupdate");

					updateBestelling.setBestellingHasArtikelen(updateBestelling.bestellingHasArtikelen);

					logger.info("object nieuweBestelling bevat nu: " + updateBestelling);
					logger.info("bestelling object wordt geupdate met bestelartikel_id en geupdate in bestelling tabel");

					service.saveOrUpdate(updateBestelling);

					logger.info("de set bestellingHasArtikelen bevat: " + updateBestelling.getBestellingHasArtikelen());

					System.out.print("Voer ID van artikel in die je wilt toevoegen (0 = stoppen met artikelen toevoegen): ");
					artikel_id = input.nextLong();

				} while (artikel_id  != 0 )  ;			

				logger.info("*** Update - end ***"); 

				GenericBestellingMenu.main(null);
				break;

			case 3:
				System.out.println("*** findById - start ***");			

				System.out.print("Voer het bestelling id die u zoekt: ");
				bestelling_id = input.nextLong();

				System.out.println(service.findBestellingById(bestelling_id));	
				GenericBestellingMenu.main(null);
				break;

			case 4:
				System.out.println("*** Delete - start ***");

				System.out.print("Voer de bestelling id in die je wilt deleten: ");				
				bestelling_id = input.nextLong();		


				while (checkExistenceInDatabase.checkBestelling_id(bestelling_id)!= true) { 
					System.out.print("\nVoer een ander bestelling ID in: ");
					bestelling_id = input.nextLong();
					System.out.println();
				}    
				
				//Bestelling deleteBestelling = service.findById(bestelling_id);

				service.deleteBestelling(bestelling_id);

				GenericBestellingMenu.main(null);
				break;

			case 5:
				logger.info("findAll bestellingen aangeroepen");
				List<Bestelling> bestellingen = service.findAllBestellingen();

				System.out.println("De volgende bestellingen staan in het assortiment:");

				for (Bestelling b: bestellingen) {
					System.out.println("-" + b.toString());
				}
				
				GenericBestellingMenu.main(null);
				break;
				
				
			case 6:
					service.deleteAllBestellingen();
					
				GenericBestellingMenu.main(null);
				break;

				
			case 7:
				GenericMainApp.main(null);
				break;
				
			case 8:
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
	
