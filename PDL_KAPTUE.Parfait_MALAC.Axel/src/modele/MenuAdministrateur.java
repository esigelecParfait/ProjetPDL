package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.ChoixBDD;
import dao.ConnexionBDD;
import dao.DominanteBDD;
import dao.EtudiantBDD;
import gui.Choix;
import gui.Etudiant;
import gui.Dominante;

public class MenuAdministrateur {

	 JFrame frame;
	 private static final String DATE_OUVERTURE_STR = "2025-05-15T08:00";
	  private static final String DATE_FERMETURE_STR = "2025-05-17T08:00";
	  private static final LocalDateTime DATE_OUVERTURE =
	            LocalDateTime.parse(DATE_OUVERTURE_STR, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	    private static final LocalDateTime DATE_FERMETURE =
	            LocalDateTime.parse(DATE_FERMETURE_STR, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrateur window = new MenuAdministrateur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuAdministrateur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel menu = CreateAddPane();
		frame.add(menu);
	}

	private JPanel CreateAddPane() {
		JPanel menu = new JPanel();
		JButton ajouter = new JButton("Mettre à jour un etudiant ");
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AjoutEtudiant  men = new AjoutEtudiant();
				men.frame.setVisible(true);
				
			}
			
		});
		JButton ajout = new JButton("Ajouter une dominante");
		ajout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AjoutDom men = new AjoutDom();
				men.frame.setVisible(true);
				
			}
			
		});
		JButton ajou = new JButton("Ajouter un etudiant ");
		ajou.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AjoutEtu men = new AjoutEtu();
				men.frame.setVisible(true);
				
			}
			
		});
		JButton supprimer = new JButton("Supprimer une dominante");
		supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SuppDom supp = new SuppDom();
				supp.frame.setVisible(true);
				
				
			}
			
		});
		JButton finale = new JButton("Attribuer les dominantes des Classiques");
		finale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int m=0;
				ChoixBDD stock = new ChoixBDD();
				ArrayList<Choix> ch = stock.getListeChoix();
				EtudiantBDD recupetu = new EtudiantBDD();
				DominanteBDD recupdom = new DominanteBDD();
				ArrayList<Etudiant> classique = recupetu.getListeClassique();
				
                    for (Etudiant etudi : classique) {
					
						//je compte le nombre d'etudiants classique
						m++;
					
				}
				System.out.println(m);
				//Je cree une matrice qui va stocker tous les choix des etudiants par ordre de leur classement scolaire
				int tab[][]= new int[m][5];
				//Je remplis la matrice en respectant le classement académique  des etudiants 
				for(int i=0 ; i< m ; i++) {
					//Je recupere l id d un etudiant aleatoire 
					String id=classique.get(i).getId();
					//System.out.println(id);
					//je recupere son classement 
					int cla=0;
					cla = classique.get(i).getClassement();
				   // System.out.println(cla);
					//Je stocke tous ses choix 
					ArrayList <Choix>stocker = stock.getChoix(id);
					//je remplis ma matrice avec les choix de chaque etudiant
					if(stocker.size()>=5) {
					for(int j=0 ; j< 5; j++) {
						tab[cla-1][j]= stocker.get(j).getdomId();
					}
					}
				}
				for (int i = 0 ; i < m ; i++) {
					String id = classique.get(i).getId();
					System.out.println(id);
					Etudiant etudian = recupetu.getEtudiant(id);
					ArrayList <Choix>stocker = stock.getChoix(id);
					if(stocker.size()==5) {
					for (int p= 0; p< 5 ; p++) {
						// je recupere la dominante du choix p de mon etudiant i
						Dominante dom = recupdom.getDom(tab[i][p]);
						//je recupere l indice de son choix qui est l'id d'une dominante
						int g= tab[i][p];
						if(dom != null && dom.getNbPlaces() != 0) {
							//Je recupere mon etudiant dans la base de donnees
							
							//J'attribue a mon etudiant un choix final
							int b=0;
							etudian.setChoix(g);
							int f = recupetu.updateEtuPlaces(etudian);
							
							if (f > 0) { // Vérifie si la mise à jour a réussi
							    System.out.println("Étudiant " + id + " affecté à la dominante " + g);
							    
							}
							//Je recupere la dominante qu on a affecté mon etudiant
							Dominante supDom = recupdom.getDom(g);
							int nb = (supDom.getNbPlaces() )-1;
							Dominante suppeDom = new Dominante(supDom.getidDom(),supDom.getNom(),supDom.getAcronyme(),nb,supDom.getDepartement());
							int z = recupdom.updateNbPlaces(suppeDom);
							break;
						}
					}
						
					}
				}
				for(int i =0 ; i < m ; i++) {
					String id = classique.get(i).getId();
					System.out.println(id);
					ArrayList <Choix>stocker = stock.getChoix(id);
					ArrayList<Dominante> dom = recupdom.getListDom();
					Etudiant etudian = recupetu.getEtudiant(id);
					if(stocker.size()!=5) {
						for (int j = 0 ; j < dom.size(); j++) {
							if(dom.get(j).getNbPlaces()!=0) {
					              int idDom = dom.get(j).getidDom();
								etudian.setChoix(idDom);
								int f = recupetu.updateEtuPlaces(etudian);
								
								if (f > 0) { // Vérifie si la mise à jour a réussi
								    System.out.println("Étudiant " + id + " affecté à la dominante " + idDom);
								    
								}
								//Je recupere la dominante qu on a affecté mon etudiant
								Dominante supDom = recupdom.getDom(idDom);
								int nb = (supDom.getNbPlaces() )-1;
								Dominante suppeDom = new Dominante(supDom.getidDom(),supDom.getNom(),supDom.getAcronyme(),nb,supDom.getDepartement());
								int z = recupdom.updateNbPlaces(suppeDom);
								break;
							}
						}
						
					}
					
				}
				
				
				JOptionPane.showMessageDialog(null, "Attribution des dominantes terminée !");
			}
			
		});
		JButton fina = new JButton("Attribuer les dominantes des Apprentis");
		fina.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int m=0;
				ChoixBDD stock = new ChoixBDD();
				//je recupere les choix de tous les etudiants
				ArrayList<Choix> ch = stock.getListeChoix();
				EtudiantBDD recupetu = new EtudiantBDD();
				DominanteBDD recupdom = new DominanteBDD();
				//je recupere tous les etudiants
				
				ArrayList<Etudiant> apprenti = recupetu.getListeApprenti();
				
				for (Etudiant etudi : apprenti) {
					
					
						m++;
					
				}
				
				System.out.println(m);
				//Je cree une matrice qui va stocker tous les choix des etudiants par ordre de leur classement scolaire
				int tab[][]= new int[m][2];
				//Je remplis la matrice en respectant le classement académique  des etudiants 
				for(int i=0 ; i< m ; i++) {
					//Je recupere l id d un etudiant aleatoire 
					String id=apprenti.get(i).getId();
					//System.out.println(id);
					//je recupere son classement 
					int cla =0;
					cla = apprenti.get(i).getClassement();
				    //System.out.println(cla);
					//Je stocke tous ses choix 
					ArrayList <Choix>stocker = stock.getChoix(id);
					//je remplis ma matrice avec les choix de chaque etudiant
					if (stocker.size()>=2) {
						
					
					for(int j=0 ; j< 2; j++) {
						tab[cla-1][j]= stocker.get(j).getdomId();
					}
					}
				}
				for(int i = 0 ; i < m ; i++) {
					String id = apprenti.get(i).getId();// je recupere l id de l etudiant 
					System.out.println(id);
					ArrayList <Choix>stocker = stock.getChoix(id);
					if(stocker.size()==2) {
						
					
					for (int p= 0; p< 2 ; p++) {

						Dominante dom = recupdom.getDom(tab[i][p]);// je recupere la dominante du choix p de mon etudiant i
						//je recupere l indice de son choix
						int g= tab[i][p];
						if(dom != null && dom.getNbPlaces() != 0) {
							//Je recupere mon etudiant dans la base de donnees
							
							Etudiant etudian = recupetu.getEtudiant(id);
							//J'attribue a mon etudiant un choix final
							int b=0;
							etudian.setChoix(g);
							int f = recupetu.updateEtuPlaces(etudian);// 
							
							if (f > 0) { // Vérifie si la mise à jour a réussi
							    System.out.println("Étudiant " + id + " affecté à la dominante " + g);
							    
							}
							//Je recupere la dominante qu on a affecté mon etudiant
							Dominante supDom = recupdom.getDom(g);
							int nb = (supDom.getNbPlaces())-1;
							//Je retire une place dans la c=dominante choisie
							Dominante suppeDom = new Dominante(supDom.getidDom(),supDom.getNom(),supDom.getAcronyme(),nb,supDom.getDepartement());
							 recupdom.updateNbPlaces(suppeDom);
							break;
						}
					}
						
					}
				}
				for(int i =0 ; i < m ; i++) {
					String id = apprenti.get(i).getId();
					System.out.println(id);
					ArrayList <Choix>stocker = stock.getChoix(id);
					ArrayList<Dominante> dom = recupdom.getListDom();
					Etudiant etudian = recupetu.getEtudiant(id);
					if(stocker.size()!=2) {
						for (int j = 0 ; j < dom.size(); j++) {
							if(dom.get(j).getNbPlaces()!=0) {
					              int idDom = dom.get(j).getidDom();
								etudian.setChoix(idDom);
								int f = recupetu.updateEtuPlaces(etudian);
								
								if (f > 0) { // Vérifie si la mise à jour a réussi
								    System.out.println("Étudiant " + id + " affecté à la dominante " + idDom);
								    
								}
								//Je recupere la dominante qu on a affecté mon etudiant
								Dominante supDom = recupdom.getDom(idDom);
								int nb = (supDom.getNbPlaces() )-1;
								Dominante suppeDom = new Dominante(supDom.getidDom(),supDom.getNom(),supDom.getAcronyme(),nb,supDom.getDepartement());
								int z = recupdom.updateNbPlaces(suppeDom);
								break;
							}
						}
						
					}
					
				}
				
				JOptionPane.showMessageDialog(null, "Attribution des dominantes terminée !");
			}
			
		});
		JButton btnNewButton = new JButton("Gérer le calendrier");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		frame.setVisible(false);
		 		Calendrier calendrier = new Calendrier();
		 		calendrier.frame.setVisible(true);
		 		}
		 });
	 menu.add(btnNewButton);
		menu.add(fina);
		menu.add(finale);
		menu.add(ajou);
	    menu.add(ajout);
		menu.add(ajouter);
		return menu;
	}
	
}
	


