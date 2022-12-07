package app.jpa.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import app.jpa.persistence.Produit;
import app.jpa.persistence.ProduitTransactions;
import app.jpa.persistence.User;
import app.jpa.persistence.UserTransactions;

@SessionScoped
@ManagedBean(name = "UserBean")
public class UserBean {

	private int id_user;
	private String name_user;
	private String email_user;
	private String address_user;
	private String tele_user;
	
	public String Save(UserBean bean) throws IOException {

		return UserTransactions.creerUser(bean.name_user, bean.email_user, bean.address_user, bean.tele_user);
	}
	
	public List<User> listUsers() {
		return UserTransactions.getAllUsers();
	}
	
	public String updateUser(UserBean bean) throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		User user = new User(Integer.parseInt(params.get("id_user")), bean.name_user, bean.email_user, bean.address_user, bean.tele_user);
		
		return UserTransactions.updateUser(user);
	}
	
	public String redirectUpdate(int id_user) 
	{
		System.out.println("id_user:  " + id_user);
		User user = UserTransactions.getUserById(id_user);

		this.name_user = user.getName_user();
		this.email_user = user.getEmail_user();
		this.address_user = user.getAddress_user();
		this.tele_user = user.getTele_user();

		return "updateUser.xhtml?faces-redirect=true&id_user=" + id_user;
	}
	
	public String redirectDelete(int id_user) {
		User user = UserTransactions.getUserById(id_user);
		return UserTransactions.deleteUser(user.getId_user());
	}

	public String redirectAddUser() {
		return "addUser.xhtml?faces-redirect=true";
	}
	
	public String delete(int id_user) {
		return UserTransactions.deleteUser(id_user);
	}
	
	// Pour récupérer le id passé dans l'url
		public int setUser(int id_user) {
			this.id_user = UserTransactions.getUserById(id_user).getId_user();
			this.name_user = UserTransactions.getUserById(id_user).getName_user();
			this.email_user = UserTransactions.getUserById(id_user).getEmail_user();
			this.address_user = UserTransactions.getUserById(id_user).getAddress_user();
			this.tele_user = UserTransactions.getUserById(id_user).getTele_user();
			return this.id_user;
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


	
}
