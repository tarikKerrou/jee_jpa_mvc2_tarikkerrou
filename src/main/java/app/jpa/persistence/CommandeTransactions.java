package app.jpa.persistence;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CommandeTransactions {

	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
	
	/*-----------créer une commande-------------*/
	public static String creerCommande(Date date_cmd, int id_user) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Commande c = new Commande(0, date_cmd,id_user);
		System.out.println(c.toString());
		entityMgrObj.persist(c);
		transactionObj.commit();
		return "listCommandes.xhtml?faces-redirect=true";
	}
	
	/* ---  récuperer toutes les commandes --- */
	@SuppressWarnings("unchecked")
	public static List getAllCategories() {
		Query queryObj = entityMgrObj.createQuery("SELECT c FROM Commande c");
		List<Commande> cList = queryObj.getResultList();			
		return cList;
	}
	
	/*-------------modefier commande------------*/
	public static String updateCommande(Commande commande) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Query queryObj = entityMgrObj.createQuery("UPDATE Commande c SET c.date_cmd=:date_cmd, c.user.id_user=:user.id_user WHERE c.id_cmd = "+ commande.getId_cmd());

		queryObj.setParameter("date_cmd", commande.getDate_cmd());
		queryObj.setParameter("user", commande.getUser());

		queryObj.executeUpdate();

		transactionObj.commit();
		return "listCategories.xhtml?faces-redirect=true";
	}
	
	/* --- Récupérer commande par id --- */
	public static Commande getCommandeById(int id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Commande cmd = entityMgrObj.find(Commande.class, id);

		System.out.println("getCommandeById : " + cmd.getId_cmd());
		return cmd;
	}
	
	/*-------------supprimer commande------------*/
	public static String deleteCommande(int id_cmd) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Commande cmd = entityMgrObj.find(Commande.class, id_cmd);

		entityMgrObj.remove(cmd);

		transactionObj.commit();

		return "listCommandes.xhtml?faces-redirect=true";
	}
	
	
}
