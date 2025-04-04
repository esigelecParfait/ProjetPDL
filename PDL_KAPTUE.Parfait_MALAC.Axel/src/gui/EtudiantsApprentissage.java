package gui;

public class EtudiantsApprentissage extends Etudiant {
	private String entreprise;
	private int typeDecontrat;
	
	public EtudiantsApprentissage(String nom, String prenom, String dateDeNaissance, int classement1A, String identifiant, String mdp,Promotion promo,int typeDecontrat,String entreprise) {
		super(nom,prenom,dateDeNaissance,classement1A,identifiant,mdp,promo);
		this.entreprise = entreprise;
		this.typeDecontrat =  typeDecontrat;
	}
	public void choixDom(Dominante dom) {
		
	}
	/**
	 * getter pour l'attribut entreprise
	 * @return
	 */
	public String getEntreprise() {
		return entreprise;
	}
	/**
	 * getter pour l'attribut type de contrat
	 * @return
	 */
	public int getTypeDecontrat() {
		return typeDecontrat;
	}
	/**
	 * setter pour l'attribut entreprise
	 * @param entreprise : nouveau nom de l'entreprise
	 */
	public void setEntreprise(String entreprise) {
		this.entreprise =  entreprise;
	}
	/**
	 * setter pour l'attribut typedecontrat
	 * @param typeDecontrat : nouveau type de contrat
	 */
	public void setTypeDecontrat(int typeDecontrat) {
		this.typeDecontrat = typeDecontrat;
	}
}
