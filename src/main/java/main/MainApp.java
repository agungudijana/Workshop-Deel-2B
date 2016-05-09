package main;

import java.util.Scanner;

public class MainApp {

	public static void main(String[] args){

		System.out.println("\t-----------------");
		System.out.println("\t De Bestel App   ");
		System.out.println("\t-----------------");
		System.out.println("1. Klant menu");
		System.out.println("2. Account menu");
		System.out.println("3. Adres menu");
		System.out.println("4. Artikel menu");
		System.out.println("5. Bestelling menu");
		//System.out.println("6. Generic menu");
		System.out.println("6. <Stoppen>");
		System.out.print("Voer optie in en druk op Enter:");


		try (Scanner input = new Scanner(System.in);) {		     

			int keuze = input.nextInt();

			switch (keuze) {
			case 1:
				KlantMenu klantMenu = new KlantMenu();
				klantMenu.main(null);
				break;			
			case 2:			
				AccountMenu accountMenu = new AccountMenu();
				accountMenu.main(null);
				break;				
			case 3:			
				AdresMenu adresMenu = new AdresMenu();
				adresMenu.main(null);
				break;		
			case 4:			
				ArtikelMenu artikelMenu = new ArtikelMenu();
				artikelMenu.main(null);
				break;
			case 5:			
				BestellingMenu bestellingMenu = new BestellingMenu();
				bestellingMenu.main(null);
				break;
			case 6:			
				GenericMenu genericMenu = new GenericMenu();
				genericMenu.main(null);
				break;
			case 7:
				System.out.println("De applicatie wordt nu afgesloten...");
				System.exit(0);
				break;
			}	
			
		}	


	}
}
