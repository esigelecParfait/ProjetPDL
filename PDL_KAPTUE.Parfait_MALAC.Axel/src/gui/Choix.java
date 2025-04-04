package gui;

public class Choix {
	private String idEtu ;
	private int domId;
	private int choixPriorite;
	
	public Choix(int domId,String idEtu,int choixPriorite) {
		this.idEtu = idEtu;
		this.domId = domId;
		this.choixPriorite = choixPriorite;
		
	}
	public String getId() {
		return idEtu;
	}
	public int getdomId() {
		return domId;
	}
	public int getchoixPriorite() {
		return choixPriorite;
	}

}
