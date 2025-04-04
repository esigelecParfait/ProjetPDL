package gui;

public class EtudiantsClassique extends Etudiant{
	/**
	 * Semestre de mobilite de l'etudiant classique
	 */
	private int semestreMobilite;
	public EtudiantsClassique(String nom, String prenom, String dateDeNaissance, int classement1A, String identifiant, String mdp,Promotion promo,int semestreMobilite) {
		super(nom,prenom,dateDeNaissance,classement1A,identifiant,mdp,promo);
		this.semestreMobilite =  semestreMobilite;
		
	}
	public void choixDom(Dominante dom) {
		
	}
	public int getSemestreMobilite() {
		return semestreMobilite;
	}
	public void setSemestreMobilite(int semestreMobilite) {
		this.semestreMobilite = semestreMobilite;
		}

}
