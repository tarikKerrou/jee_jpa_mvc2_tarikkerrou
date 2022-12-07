package app.jpa.persistence;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_user;
	
	@Column( nullable = true , length = 45)
	private String name_user;
	
	@Column( nullable = true , length = 45)
	private String email_user;
	
	@Column( nullable = true , length = 45)
	private String address_user;
	
	@Column( nullable = true , length = 45)
	private String tele_user;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private Collection<Commande> commandes;

	public User() {
		super();
	}

	public User(int id_user, String name_user, String email_user, String address_user, String tele_user) {
		super();
		this.id_user = id_user;
		this.name_user = name_user;
		this.email_user = email_user;
		this.address_user = address_user;
		this.tele_user = tele_user;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getAddress_user() {
		return address_user;
	}

	public void setAddress_user(String address_user) {
		this.address_user = address_user;
	}

	public String getTele_user() {
		return tele_user;
	}

	public void setTele_user(String tele_user) {
		this.tele_user = tele_user;
	}

	public Collection<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(Collection<Commande> commandes) {
		this.commandes = commandes;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", name_user=" + name_user + ", email_user=" + email_user
				+ ", address_user=" + address_user + ", tele_user=" + tele_user + "]";
	}
	
	
}
