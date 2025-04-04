package gui;

import java.util.ArrayList;

public class Promotion {
	

	    private boolean ouvertClassique;
	    private boolean ouvertApprentis;
	    private boolean valideApprentis;
	    private boolean valideClassique;
	    private int annee;
	    private boolean enPreparation;
	    private ArrayList<Etudiant>etudiants;

	    public Promotion(int annee) {
	        this.annee = annee;
	        this.ouvertClassique = false;
	        this.ouvertApprentis = false;
	        this.valideApprentis = false;
	        this.valideClassique = false;
	        this.enPreparation = true;
	        this.etudiants = new ArrayList<>();
	    }

	    public void setOuvertClassique() {
	        this.ouvertClassique = true;
	    }

	    public void setOuvertApprentis() {
	        this.ouvertApprentis = true;
	    }

	    public void setValideClassique() {
	        this.valideClassique = true;
	    }

	    public void setValideApprentis() {
	        this.valideApprentis = true;
	    }

	    public void setAnnee(int annee) {
	        this.annee = annee;
	    }

	    public void setPreparation(boolean enPreparation) {
	        this.enPreparation = enPreparation;
	    }
	    public int getAnee() {
	    	return annee;
	    }

		public Promotion getPromotion(String string) {
			// TODO Auto-generated method stub
			return null;
		}

	    /* Getters
	    public boolean isOuvertClassique() {
	        return ouvertClassique;
	    }

	    public boolean isOuvertApprentis() {
	        return ouvertApprentis;
	    }

	    public boolean isValideApprentis() {
	        return valideApprentis;
	    }

	    public boolean isValideClassique() {
	        return valideClassique;
	    }

	  
	    public boolean isEnPreparation() {
	        return enPreparation;
	    }*/
	}


