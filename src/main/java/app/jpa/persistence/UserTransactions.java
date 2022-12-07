package app.jpa.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserTransactions {

	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
	
	/*-----------créer un utilisateur-------------*/
	public static String creerUser(String name_user, String email_user,String address_user,String tele_user) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		User user = new User(0, name_user, email_user, address_user, tele_user);
		System.out.println(user.toString());
		entityMgrObj.persist(user);
		transactionObj.commit();
		return "listUsers.xhtml?faces-redirect=true";
	}
	
	/*-------------modefier utilisateur------------*/
	public static String updateUser(User user) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Query queryObj = entityMgrObj.createQuery("UPDATE User u SET u.name_user=:name_user, u.email_user=:email_user, u.address_user=:address_user , u.tele_user=: tele_user  WHERE u.id_user = "+ user.getId_user());

		queryObj.setParameter("name_user", user.getName_user());
		queryObj.setParameter("email_user", user.getEmail_user());
		queryObj.setParameter("address_user", user.getAddress_user());
		queryObj.setParameter("tele_user", user.getTele_user());

		queryObj.executeUpdate();

		transactionObj.commit();
		return "listUsers.xhtml?faces-redirect=true";
	}
	
	/*-------------supprimer utilisateur------------*/
	public static String deleteUser(int id_user) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		User user = entityMgrObj.find(User.class, id_user);

		entityMgrObj.remove(user);

		transactionObj.commit();

		return "listUsers.xhtml?faces-redirect=true";
	}
	
	/* --- Récupérer l'utilisateur par id --- */
	public static User getUserById(int id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		User user = entityMgrObj.find(User.class, id);

		System.out.println("GetUserById : " + user.getName_user());
		return user;
	}
	
	/* ---  récuperer tous les utilisateurs --- */
	@SuppressWarnings("unchecked")
	public static List getAllUsers() {
		Query queryObj = entityMgrObj.createQuery("SELECT u FROM User u");
		List<User> uList = queryObj.getResultList();
			return uList;

	}

}
