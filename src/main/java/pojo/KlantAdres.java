package pojo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import pojo.*;

@Entity
@Table
public class KlantAdres implements Serializable {
	
	public enum AdresType {
		Postadres,
		Factuuradres,
		Bezoekadres;
	}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "klantadres_id")
		private long id;
		
		@ManyToOne//(cascade = CascadeType.ALL)	
		@JoinColumn(name = "klant_id")
		//@Cascade({org.hibernate.annotations.CascadeType.ALL})
		private Klant klant;

		
		@ManyToOne//cascade = CascadeType.ALL)
		@JoinColumn(name = "adres_id")
		//@Cascade({org.hibernate.annotations.CascadeType.ALL})
		private Adres adres;
		
		@Enumerated(EnumType.STRING)
		@JoinColumn(name  = "adrestype")
		private AdresType adrestype;
		
		public KlantAdres(){}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		public Klant getKlant() {
			return klant;
		}

		public void setKlant(Klant klant) {
			this.klant = klant;
		}

		public Adres getAdres() {
			return adres;
		}

		public void setAdres(Adres adres) {
			this.adres = adres;
		}


		@Override
		public String toString() {
			return  "\nKlantAdres id: " + getId() +
					// "\nKlantnummer: " 	+ getKlant() +
					"\nAdres: " 	+ getAdres() + 
					"\nAdrestype: " 	+ getAdresType() + 
					"\n";
		}
		
		public AdresType getAdresType() {
			return adrestype;
		}

		public void setAdresType(AdresType adrestype) {
			this.adrestype = adrestype;
		}
		
		
		/* @Override
		public int hashCode(){
				int hash = 7;
		       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
		        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
		        hash = 67 * hash + Objects.hashCode(this.klant);
		        hash = 67 * hash + Objects.hashCode(this.adres);
		        hash = 67 * hash + Objects.hashCode(this.adrestype);
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
	        final KlantAdres other = (KlantAdres) obj;
	        if (!Objects.equals(this.klant, other.klant)) {
	            return false;
	        }
	        if (!Objects.equals(this.adres, other.adres)) {
	            return false;
	        }
	        if (!Objects.equals(this.adrestype, other.adrestype)) {
	           return false;
	        }
	        return true;
		} */
		
		
}
