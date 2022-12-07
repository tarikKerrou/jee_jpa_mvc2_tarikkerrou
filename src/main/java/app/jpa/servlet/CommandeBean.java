package app.jpa.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import app.jpa.persistence.Commande;
import app.jpa.persistence.CommandeTransactions;
import app.jpa.persistence.Produit;
import app.jpa.persistence.ProduitTransactions;
import app.jpa.persistence.User;

@SessionScoped
@ManagedBean(name = "CommandeBean")
public class CommandeBean {

	private int id_cmd;
	private Date date_cmd;
	private User user;
	private int id_user;
	
	public String Save(CommandeBean bean) throws IOException {
		return CommandeTransactions.creerCommande(bean.date_cmd, user.getId_user());
	}

	public String redirectAddCmd() {
		return "addCommande.xhtml?faces-redirect=true";
	}

	public List<Commande> listCommandes() {
		return CommandeTransactions.getAllCategories();
	}
	
	public String redirectUpdate(int id_cmd) 
	{
		System.out.println("id_cmd:  " + id_cmd);
		Commande cmd = CommandeTransactions.getCommandeById(id_cmd);

		this.date_cmd = cmd.getDate_cmd();
		this.user = cmd.getUser();


		return "updateCommande.xhtml?faces-redirect=true&id_cmd=" + id_cmd;
	}
	
	public String redirectDelete(int id_cmd) {
		Commande cmd = CommandeTransactions.getCommandeById(id_cmd);
		return CommandeTransactions.deleteCommande(cmd.getId_cmd());
	}
	
	


	public int getId_cmd() {
		return id_cmd;
	}


	public void setId_cmd(int id_cmd) {
		this.id_cmd = id_cmd;
	}


	public Date getDate_cmd() {
		return date_cmd;
	}


	public void setDate_cmd(Date date_cmd) {
		this.date_cmd = date_cmd;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getId_user() {
		return id_user;
	}


	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	
	
	
	
}
