package app.jpa.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="panier")
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_panier;
	
	@ManyToMany
    @JoinTable( name = "produit_panier",
                joinColumns = @JoinColumn( name = "id_panier" ),
                inverseJoinColumns = @JoinColumn( name = "id_prod" ) )
    private List<Produit> produits = new ArrayList<>();

	
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Panier(int id_panier) {
		super();
		this.id_panier = id_panier;
	}


	public int getId_panier() {
		return id_panier;
	}


	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}


	public List<Produit> getProduits() {
		return produits;
	}


	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}


	@Override
	public String toString() {
		return "Panier [id_panier=" + id_panier + ", produits=" + produits + "]";
	}
	
	
}
