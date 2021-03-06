package pojo;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component("account")
@Entity
@Table
public class Account implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private long id;
	
	@Column(name = "accountnaam")
	private String accountNaam;
	
	@ManyToOne
	@JoinColumn(name = "klant_id")
	private Klant klant;
	
	@Column
	private Date dateCreated;
	
	public Account(){
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getAccountName(){
		return accountNaam;
	}
	
	public void setAccountNaam(String accountNaam){
		this.accountNaam = accountNaam;
	}
	
	public Klant getKlant(){
		return klant;
	}
	
	public void setKlant(Klant klant){
		this.klant = klant;
	}
	
	public Date getDateCreated(){
		return dateCreated;
	}
	
	public void setDateCreated(){
		this.dateCreated = new Date();
	}
	
	@Override
	public String toString(){
		return  "\n\tAccount_id: " 	+ id +
				"\nAccount naam: "	+ accountNaam + 
				"\nvan klant: "	+ klant + 
				"\naangemaakt op datum: " 	+ dateCreated;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.accountNaam, other.accountNaam)) {
            return false;
        }
        if (!Objects.equals(this.klant, other.klant)) {
            return false;
        }
        //if (!Objects.equals(this.dateCreated, other.dateCreated)) {
        //    return false;
        //}
        return true;
    }
	
	@Override
	public int hashCode(){
		int hash = 7;
		//hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
		hash = 89 * hash + ((accountNaam == null) ? 0 : Objects.hashCode(this.accountNaam));
		hash = 89 * hash + ((klant == null) ? 0 : Objects.hashCode(this.klant));
		
		return hash;
	}
}
