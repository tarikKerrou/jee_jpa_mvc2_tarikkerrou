package app.jpa.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="produit")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_prod;
	
	@Column( nullable = true , length = 45)
	private String nom_prod;
	
	@Column(nullable = true)
	private int qte_prod;
	
	@Column(nullable = true)
	private float pu;
	

	
	@ManyToMany
    @JoinTable( name = "produit_panier",
                joinColumns = @JoinColumn( name = "id_prod" ),
                inverseJoinColumns = @JoinColumn( name = "id_panier" ) )
    private List<User> paniers = new ArrayList<>();
	
	
	public Produit() {
		super();
	}


	public Produit(int id_prod, String nom_prod, int qte_prod, float pu) {
		super();
		this.id_prod = id_prod;
		this.nom_prod = nom_prod;
		this.qte_prod = qte_prod;
		this.pu = pu;
	}

	public List<User> getPaniers() {
		return paniers;
	}


	public void setPaniers(List<User> paniers) {
		this.paniers = paniers;
	}


	public int getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public String getNom_prod() {
		return nom_prod;
	}

	public void setNom_prod(String nom_prod) {
		this.nom_prod = nom_prod;
	}

	public int getQte_prod() {
		return qte_prod;
	}

	public void setQte_prod(int qte_prod) {
		this.qte_prod = qte_prod;
	}

	public float getPu() {
		return pu;
	}

	public void setPu(float pu) {
		this.pu = pu;
	}


	@Override
	public String toString() {
		return "Produit [id_prod=" + id_prod + ", nom_prod=" + nom_prod + ", qte_prod=" + qte_prod + ", pu=" + pu +"]";
	}
	
}
