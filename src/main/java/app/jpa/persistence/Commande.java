package app.jpa.persistence;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="commande")
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cmd;
	
	@Column( nullable = true)
	private Date date_cmd;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commande(int id_cmd, Date date_cmd, int id_user) {
		super();
		this.id_cmd = id_cmd;
		this.date_cmd = date_cmd;
		this.user.setId_user(id_user);;
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

	@Override
	public String toString() {
		return "Commande [id_cmd=" + id_cmd + ", date_cmd=" + date_cmd + "]";
	}
	
	
	
}
