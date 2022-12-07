package app.jpa.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligne_cmd")
public class Ligne_cmd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_ligne;
	
	private int qte_cmd;
	
	@ManyToOne
	@JoinColumn(name="id_cmd")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="id_prod")
	private Produit produit;

	public Ligne_cmd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ligne_cmd(int id_ligne, int qte_cmd) {
		super();
		this.id_ligne = id_ligne;
		this.qte_cmd = qte_cmd;
	}

	public int getId_ligne() {
		return id_ligne;
	}

	public void setId_ligne(int id_ligne) {
		this.id_ligne = id_ligne;
	}

	public int getQte_cmd() {
		return qte_cmd;
	}

	public void setQte_cmd(int qte_cmd) {
		this.qte_cmd = qte_cmd;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "Ligne_cmd [id_ligne=" + id_ligne + ", qte_cmd=" + qte_cmd + ", commande=" + commande + ", produit="
				+ produit + "]";
	}
	
	
}
