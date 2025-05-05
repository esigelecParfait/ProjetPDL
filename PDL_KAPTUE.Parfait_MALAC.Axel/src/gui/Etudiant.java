package gui;

import java.util.ArrayList;
import java.util.Date;

public class Etudiant {
	/**
	 * nom de l'etudiant
	 */
	    protected String nom;
	    
	  /**
	   * prenom de l'etudiant
	   */
	    protected String prenom;
	  /**
	   * Date de naissance
	   */
	    protected String dateDeNaissance;
	    protected int classement1A;
	    protected String identifiant;
	    protected String mdp;
        protected ArrayList<Dominante>Dominante;
        protected Promotion promo;
        protected int identifiantpromo;
        protected int choixfinal =0;
        protected String statut;
        protected String entreprise;
        private String contrat;
        private String mobilite;

		public String getDatedeNaissance;
	   /**
	    * Constructeur
	    * @param nom
	    * @param prenom
	    * @param dateDeNaissance
	    * @param classement1A
	    * @param identifiant
	    * @param mdp
	    * @param promo
	    * @param identifiantpromo
	    * @param choixfinal
	    */
	    public Etudiant(String nom, String prenom, String dateDeNaissance, int classement1A, String identifiant, String mdp,Promotion promo,int choixfinal,int identifiantpromo) {
	        this.nom = nom;
	        this.prenom = prenom;
	        this.dateDeNaissance = dateDeNaissance;
	        this.classement1A = classement1A;
	        this.identifiant = identifiant;
	        this.mdp = mdp;
	        this.promo = promo;
	        this.identifiantpromo = identifiantpromo;
	        this.choixfinal = choixfinal;
	        this.Dominante = new ArrayList<>();
	        
	    }
	    public Etudiant(String identifiant,String nom, String prenom,String mdp, String dateDeNaissance,int classement1A,String statut,String contrat,String entreprise,String mobilite,int identifiantpromo,int choixfinal) {
	    	this.identifiant= identifiant;
	    	this.nom= nom;
	    	this.prenom= prenom;
	    	this.mdp = mdp;
	    	this.dateDeNaissance= dateDeNaissance;
	    	this.classement1A = classement1A;
	    	this.statut = statut;
	    	this.contrat= contrat;
	    	this.entreprise = entreprise;
	    	this.identifiantpromo= identifiantpromo;
	    	this.mobilite = mobilite;
	    	this.choixfinal = choixfinal;
	    }
	    public String getMobilite() {
	    	return mobilite;
	    }
	    
	    public Etudiant(String identifiant, String mdp , String statut) {
	        
	        this.identifiant = identifiant;
	        this.mdp = mdp;
	        this.statut = statut;
	        
	    }
	    public String getEntreprise() {
	    	return entreprise;
	    }
	    public String getContrat() {
	    	return contrat;
	    }

	    
	    public void display() {
	        System.out.println("Nom: " + nom);
	        System.out.println("Prénom: " + prenom);
	        System.out.println("Date de Naissance: " + dateDeNaissance);
	        System.out.println("Classement 1A: " + classement1A);
	        System.out.println("Identifiant: " + identifiant);
	        
	    }

	    public String getDatedeNaissance() {
	    	return dateDeNaissance;
	    }
	    
	    public void choixDom(Dominante dom) {
	        System.out.println(nom + " a choisi la dominante : " + dom.getNom());
	    }

	    
	    public void consulterChoix() {
	        System.out.println("Consultation des choix en cours...");
	    }
	    public int getIdentifiantPromo() {
	    	return identifiantpromo;
	    }
	    public int getChoixFinal() {
	    	return choixfinal;
	    }
	    public void setIdentifiantPromo(int identifiantpromo) {
	    	this.identifiantpromo= identifiantpromo;
	    }
	    public void setChoix(int choixfinal) {
	    	this.choixfinal = choixfinal;
	    }

	    /**
	     * getter pour l'attribut nom
	     * @return
	     */
	    public String getNom() {
	        return nom;
	    }
	    /**
	     * getter pour l'attribut prenom
	     * @return
	     */

	    public String getPrenom() {
	        return prenom;
	    }
	    /**
	     * getter pour l'attribut mdp
	     * @return
	     */

	    public String getMdp() {
	        return mdp;
	    }
	    /**
	     * getter pour l'attribut ClassementA
	     * @return
	     */

	    public int getClassement() {
	        return classement1A;
	    }
	    /**
	     * getter pour l'attribut identifiant
	     * @return
	     */

	    public String getId() {
	        return identifiant;
	    }
	    /**
	     * 
	     * @return
	     */
	    public Promotion getPromotion() {
	    	return promo;
	    }
	    public ArrayList<Dominante> getDominante(){
	    	return Dominante;
	    }

	    /**
	     * setter pour l'attribut nom
	     * @param nom : nouveau nom de l'etudiant
	     */
	    public void setNom(String nom) {
	        this.nom = nom;
	    }
	    /**
	     * 
	     * @param prenom : nouveau prenom de l'etudiant
	     */

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }
	    /**
	     * setter pour l'attribut dateDeNaissance
	     * @param date
	     */

	    public void setDateDeNaissance(String date) {
	        this.dateDeNaissance = date;
	    }
	    /**
	     * setter pour l'attribut Classement1A
	     * @param place
	     */

	    public void setClassement1A(int place) {
	        this.classement1A = place;
	    }
	    /**
	     * setter pour l'attribut mdp
	     * @param mdp
	     */
	    

	    public void setMdp(String mdp) {
	        this.mdp = mdp;
	    }
	    /**
	     * setter pour l'attribut identifiant
	     * @param id
	     */

	    public void setId(String id) {
	        this.identifiant = id;
	    }
	    public String getStatut() {
	    	return statut;
	    }
	}

	


