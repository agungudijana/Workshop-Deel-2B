package pojo;

import java.util.Objects;
import java.util.Random;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
public class Artikel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artikel_id")
	private long id;
	
	@Column
	private String artikel_nummer;
	
	@Column
	private String artikel_naam;
	
	@Column
	private String omschrijving;
	
	@Column
	private double artikel_prijs;
	
	
	public Artikel(){
	}


	public long getId() {
		return id;
	}
 
	public void setId(long id) {
		this.id = id;
	}
	
	public String getArtikel_nummer() {
		return artikel_nummer;
	}
	
	public void setArtikel_nummer() {
		int lengthOfRandomString = 10;
		Random rand = new Random();
		String alphaNumericCharacters = "abcdefghijklmnopqrstuvwxyz"
				+ "ABCDEFGHIJLMNOPQRSTUVWXYZ"
				+ "1234567890";

		StringBuilder result = new StringBuilder();

		for (int i =0; i< lengthOfRandomString ; i++) {
			result.append(
					alphaNumericCharacters.
					charAt(rand.nextInt(alphaNumericCharacters.length())));
		}
		artikel_nummer = result.toString();           
		System.out.println("artikelnummer is: " + artikel_nummer);
	}
	
	public String getArtikel_naam() {
		return artikel_naam;
	}
	
	public void setArtikel_naam(String artikel_naam) {
		this.artikel_naam = artikel_naam;
	}
	
	public String getOmschrijving() {
		return omschrijving;
	}
	
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	public double getArtikel_prijs() {
		return artikel_prijs;
	}
	
	public void setArtikel_prijs(double artikel_prijs) {
		this.artikel_prijs = artikel_prijs;
	}
	
	
	public String toString(){
		return 	"\nArtikel Id: " + id + 		
				"\tArtikel naam: " + artikel_naam + 
				"\tArtikel prijs: " + artikel_prijs;
	
	}
	
	@Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.artikel_nummer);
	        hash = 67 * hash + Objects.hashCode(this.artikel_naam);
	        hash = 67 * hash + Objects.hashCode(this.omschrijving);
	        hash = 67 * hash + (int) (this.artikel_prijs);
	        return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artikel other = (Artikel) obj;
        if (!Objects.equals(this.artikel_nummer, other.artikel_nummer)) {
            return false;
        }
        if (!Objects.equals(this.artikel_naam, other.artikel_naam)) {
            return false;
        }
        if (!Objects.equals(this.omschrijving, other.omschrijving)) {
           return false;
        }
        if (!Objects.equals(this.artikel_prijs, other.artikel_prijs)){
        	return false;
        }
        return true;
	}
}