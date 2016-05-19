package main;

import java.util.Scanner;

public class GenericMainApp {

	public static void main(String[] args){

		System.out.println("\t----------------------------");
		System.out.println("\t De Bestel App (GENERIC DAO)");
		System.out.println("\t----------------------------");
		System.out.println("1. Klant menu");
		System.out.println("2. Account menu");
		System.out.println("3. Adres menu");
		System.out.println("4. Artikel menu");
		System.out.println("5. Bestelling menu");
		System.out.println("6. <Stoppen>");
		System.out.print("Voer optie in en druk op Enter:");


		try (Scanner input = new Scanner(System.in);) {		     

			int keuze = input.nextInt();

			switch (keuze) {
			case 1:
				GenericKlantMenu genericKlantMenu = new GenericKlantMenu();
				genericKlantMenu.main(null);
				break;			
			case 2:			
				GenericAccountMenu genericAccountMenu = new GenericAccountMenu();
				genericAccountMenu.main(null);
				break;				
			case 3:			
				GenericAdresMenu genericAdresMenu = new GenericAdresMenu();
				genericAdresMenu.main(null);
				break;		
			case 4:			
				GenericArtikelMenu genericArtikelMenu = new GenericArtikelMenu();
				genericArtikelMenu.main(null);
				break;
			case 5:			
				GenericBestellingMenu genericBestellingMenu = new GenericBestellingMenu();
				genericBestellingMenu.main(null);
				break;
			case 6:
				System.out.println("De applicatie wordt nu afgesloten...");
				System.exit(0);
				break;
			}	
			
		}	


	}
}
