package app.jpa.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ProduitTransactions {

	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
	

	/*-----------créer un produit-------------*/
	public static String creerProduit(String nom_prod, int qte_prod, float pu) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Produit p = new Produit(0, nom_prod, qte_prod, pu);
		System.out.println(p.toString());
		entityMgrObj.persist(p);
		transactionObj.commit();
		return "listProduits.xhtml?faces-redirect=true";
	}

	/*-------------modefier produit------------*/
	public static String updateProduit(Produit prod) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Query queryObj = entityMgrObj.createQuery("UPDATE Produit p SET p.nom_prod=:nom, p.qte_prod=:quantite, p.pu=:pu  WHERE p.id_prod = "+ prod.getId_prod());

		queryObj.setParameter("nom", prod.getNom_prod());
		queryObj.setParameter("quantite", prod.getQte_prod());
		queryObj.setParameter("pu", prod.getPu());

		queryObj.executeUpdate();

		transactionObj.commit();
		return "listProduits.xhtml?faces-redirect=true";
	}
		
	/*-------------supprimer produit------------*/
	public static String deleteProduit(int id_prod) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Produit prod = entityMgrObj.find(Produit.class, id_prod);

		entityMgrObj.remove(prod);

		transactionObj.commit();

		return "listProduits.xhtml?faces-redirect=true";
	}

	/* --- Récupérer le produit par id --- */
	public static Produit getProduitById(int id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Produit prod = entityMgrObj.find(Produit.class, id);

		System.out.println("GetProdById : " + prod.getNom_prod());
		return prod;
	}

	/* ---  récuperer tous les produits --- */
	@SuppressWarnings("unchecked")
	public static List getAllProduits() {
		Query queryObj = entityMgrObj.createQuery("SELECT p FROM Produit p");
		List<Produit> pList = queryObj.getResultList();
			return pList;

	}

}

