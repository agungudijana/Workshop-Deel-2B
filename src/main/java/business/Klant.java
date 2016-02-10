package business;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table
public class Klant implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "klant_id")
	private long id;
	
	@Column
	private String voornaam;
	
	@Column
	private String achternaam;
	
	@Column
	private String tussenvoegsel;
	
	@Column
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="bestelNummer")
	private Set<Bestelling> bestellingSet;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="factuurNummer") 
	private Set<Factuur> factuurSet;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="accountNaam")  
	private Set<Account> accountSet;
	
	
	@ElementCollection
	@MapKeyColumn(name="adres_id")
	@JoinTable(
		name = "klant_adres", 
		joinColumns = @JoinColumn(name = "klant_id", referencedColumnName= "id"), 
		inverseJoinColumns = {@JoinColumn(name = "adres_id", referencedColumnName= "id"),
		@JoinColumn(name = "adrestype_id", referencedColumnName= "adrestype_id")})
	private Map<Adres, AdresType> adresMap = new HashMap <Adres, AdresType>();
	
	
	public Klant() {}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		EmailValidator emailValidator = EmailValidator.getInstance();
		Scanner input = new Scanner(System.in);
	
		while (emailValidator.isValid(email)== false){
			System.out.println("Incorrecte email. Voer uw emailadres opnieuw in");
			email = input.next();
	}
		
		this.email = email;
	}

	public void setBestellingSet(Set<Bestelling> bestellingSet){
		this.bestellingSet = bestellingSet;
	}

	public Set<Bestelling> getBestellingSet(){
		return bestellingSet;
	}

	public void setAccountSet(Set<Account> accountSet){
		this.accountSet = accountSet;
	}

	public Set<Account> getAccountSet(){
		return accountSet;
	}

	public void setFactuurSet(Set<Factuur> factuurSet){
		this.factuurSet = factuurSet;
	}

	public Set<Factuur> getFactuurSet(){
		return factuurSet;
	}
	
	public void setAdresMap(Map<Adres, AdresType> adresMap){
		this.adresMap = adresMap;
	}
	
	public Map<Adres, AdresType> getAdresMap(){
		return adresMap;
	}
	
	@Override 
	public String toString(){
		return  "\n\tKlant_id: " 	+ id +
				"\nVoornaam: "		+ voornaam + 
				"\nTussenvoegsel: "	+ tussenvoegsel + 
				"\nAchternaam: " 	+ achternaam + 
				"\nEmail: " 		+ email + 
				"\n";
	}
	
	@Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.voornaam);
	        hash = 67 * hash + Objects.hashCode(this.tussenvoegsel);
	        hash = 67 * hash + Objects.hashCode(this.achternaam);
	        hash = 67 * hash + Objects.hashCode(this.email);
	        hash = 67 * hash + Objects.hashCode(this.accountSet);
	        hash = 67 * hash + Objects.hashCode(this.bestellingSet);
	        hash = 67 * hash + Objects.hashCode(this.factuurSet);
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
        final Klant other = (Klant) obj;
        if (!Objects.equals(this.voornaam, other.voornaam)) {
            return false;
        }
        if (!Objects.equals(this.tussenvoegsel, other.tussenvoegsel)) {
            return false;
        }
        if (!Objects.equals(this.achternaam, other.achternaam)) {
           return false;
        }
        if (!Objects.equals(this.email, other.email)){
        	return false;
        }
        if (!Objects.equals(this.factuurSet, other.factuurSet)) {
            return false;
        }
        if (!Objects.equals(this.bestellingSet, other.bestellingSet)) {
           return false;
        }
        if (!Objects.equals(this.accountSet, other.accountSet)){
        	return false;
        }
        return true;
	}
}



