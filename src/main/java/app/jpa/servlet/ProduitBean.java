package app.jpa.servlet;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import app.jpa.persistence.Produit;
import app.jpa.persistence.ProduitTransactions;


@SessionScoped
@ManagedBean(name = "ProduitBean")
public class ProduitBean {
	
	private int id_prod = 0;
	private String nom_prod;
	private int qte_prod;
	private Float pu;

	public String Save(ProduitBean bean) throws IOException {

		return ProduitTransactions.creerProduit(bean.nom_prod, bean.qte_prod, bean.pu);
	}
	

	public List<Produit> listProduits() {
		return ProduitTransactions.getAllProduits();
	}

	public String redirectUpdate(int id_prod) 
	{
		System.out.println("id_prod:  " + id_prod);
		Produit p = ProduitTransactions.getProduitById(id_prod);

		this.nom_prod = p.getNom_prod();
		this.qte_prod = p.getQte_prod();
		this.pu = p.getPu();

		return "updateProduit.xhtml?faces-redirect=true&id_prod=" + id_prod;
	}

	public String redirectDelete(int id_prod) {
		Produit p = ProduitTransactions.getProduitById(id_prod);
		return ProduitTransactions.deleteProduit(p.getId_prod());
	}

	public String redirectAddProd() {
		return "addProduit.xhtml?faces-redirect=true";
	}


	public String updateProduit(ProduitBean bean) throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		Produit prod = new Produit(Integer.parseInt(params.get("id_prod")), bean.nom_prod, bean.qte_prod, bean.pu);
		
		return ProduitTransactions.updateProduit(prod);
	}
	

	public String delete(int id_prod) {
		return ProduitTransactions.deleteProduit(id_prod);
	}

	
	// Pour récupérer le id passé dans l'url
	public int setProduit(int id_prod) {
		this.id_prod = ProduitTransactions.getProduitById(id_prod).getId_prod();
		this.nom_prod = ProduitTransactions.getProduitById(id_prod).getNom_prod();
		return this.id_prod;
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


	public Float getPu() {
		return pu;
	}


	public void setPu(Float pu) {
		this.pu = pu;
	}

}
