package gui;

import java.util.ArrayList;

public class Administrateur {
	
	    
	    private String nom;
	    private String prenom;
	    private String identifiant;
	    private String mdp;

	    
	    public Administrateur(String nom, String prenom, String identifiant, String mdp) {
	        this.nom = nom;
	        this.prenom = prenom;
	        this.identifiant = identifiant;
	        this.mdp = mdp;
	    }

	    
	    public String getNom() {
	        return nom;
	    }
	    public String getPrenom() {
	    	return prenom;
	    }

	    public String getId() {
	        return identifiant;
	    }

	    public String getMdp() {
	        return mdp;
	    }

	    
	    public void setNom(String nom) {
	        this.nom = nom;
	    }
	    public void setPrenom(String prenom) {
	    	this.prenom = prenom;
	    }

	    public void setId(String id) {
	        this.identifiant = id;
	    }

	    public void setMdp(String mdp) {
	        this.mdp = mdp;
	    }


		
	}



