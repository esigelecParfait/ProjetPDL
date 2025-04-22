package gui;

import java.util.ArrayList;

public class Dominante {
	
	    private int idDom;
	    private String nom;
	    private String acronyme;
	    private int  departement;
	    private int nbPlaces;
	    private int nbPlacesApprenti;
	    private ArrayList<Etudiant>listeEtudiantsInscrits;

	    
	    public Dominante(int idDom,String nom, String acronyme, int nbPlaces,int nbPlacesApprenti,int departement) {
	    	this.idDom = idDom;
	        this.nom = nom;
	        this.acronyme = acronyme;
	        this.departement = departement;
	        this.nbPlaces = nbPlaces;
	        this.nbPlacesApprenti = nbPlacesApprenti;
	       
	    }
	    public Dominante(int idDom,String nom,int nbPlaces) {
	    	this.idDom = idDom;
	    	this.nom = nom;
	    	this.nbPlaces = nbPlaces;
	    }

	    
	    public void display() {
	        System.out.println("Nom: " + nom);
	        System.out.println("Acronyme: " + acronyme);
	        System.out.println("Département: " + departement);
	        System.out.println("Nombre de places: " + nbPlaces);
	    }

	    

	    
	    public String getNom() {
	        return nom;
	    }

	    public String getAcronyme() {
	        return acronyme;
	    }

	    public int getDepartement() {
	        return departement;
	    }

	    public int getNbPlaces() {
	        return nbPlaces;
	    }
	    public int getNbPlacesApprenti() {
	        return nbPlacesApprenti;
	    }
	    public int getidDom() {
	    	return idDom;
	    }

	    
	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public void setAcronyme(String acronyme) {
	        this.acronyme = acronyme;
	    }

	    public void setDepartement(int departement) {
	        this.departement = departement;
	    }

	    public void setNbPlaces(int nb) {
	        this.nbPlaces = nb;
	    }
	    

}


