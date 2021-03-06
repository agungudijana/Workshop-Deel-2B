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


public class GenericAccountMenu { 

	private static final Logger logger =  LoggerFactory.getLogger(GenericAccountMenu.class);

	public static void main(String[] args){

		
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		GenericDaoService service = (GenericDaoService) ctx.getBean("genericDaoService");
		Klant klant = ctx.getBean(Klant.class);
		Account account = ctx.getBean(Account.class);
		
		System.out.println("\t------------------------------");
		System.out.println("\t Account Domain (GENERIC DAO) ");
		System.out.println("\t------------------------------");
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
				//Account nieuweAccount = new Account();
				input.nextLine();
				System.out.print("Voer uw klant ID in waarvoor u een Account wilt aanmaken: ");
				long klant_id = input.nextLong();	

				account.setDateCreated();
				account.setKlant(service.findKlantById(klant_id));
				
				System.out.print("Voer de naam van uw nieuwe account in: ");
				String nieuweAccountNaam = input.next();	
												
				account.setAccountNaam(nieuweAccountNaam);
				

				System.out.println("Toe te voegen nieuwe account: " + account);
				service.persist(account);
				System.out.println("Account toegevoegd: " + account);
				
				GenericAccountMenu.main(null);
				break;

			case 2: //werkt nog niet
				
				System.out.println("*** Update - start ***");
				//Account bestaandeAccount = new Account();	
				
				System.out.println("Voer uw klant id in: ");
				klant_id = input.nextLong();
				
				System.out.print("Voer het ID in van het Account dat u wilt aanpassen: ");				
				long account_id = input.nextLong();
				
				System.out.println("Voer uw nieuwe accountnaam in: ");
				String accountNaam = input.next();
				
				
				account.setId(account_id);
				account.setKlant(service.findKlantById(klant_id));
				account.setAccountNaam(accountNaam);
				account.setDateCreated();

				logger.info("Account is: " + account);
				service.saveOrUpdate(account); 
				
				GenericAccountMenu.main(null);
				break;

			case 3:
				System.out.println("*** findById - start ***");							
			
				System.out.print("Voer het ID in van uw Account: ");
				account_id = input.nextLong();
				
				System.out.println(service.findAccountById(account_id));
				
				GenericAccountMenu.main(null);
				break;

			case 4:
				System.out.println("*** Delete - start ***");
				//bestaandeAccount = new Account();	
				
				System.out.print("Voer het ID in van uw account die u wilt deleten: ");				
				account_id = input.nextLong();				
				service.deleteAccount(account_id);
				
				GenericAccountMenu.main(null);
				break;

			case 5:
				logger.info("findAll klanten aangeroepen");
				List<Account> accounts = service.findAllAccounten();

				System.out.println("De volgende klanten staan in de Klant tabel :");

				for (Account a : accounts) {
					System.out.println("-" + a.toString());
				}
				GenericAccountMenu.main(null);
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


	GenericAccountMenu.main(null);	
		
		

	}

}
