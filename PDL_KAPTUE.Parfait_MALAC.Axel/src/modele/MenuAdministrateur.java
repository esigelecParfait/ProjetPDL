package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		JButton finale = new JButton("Attribuer les dominantes");
		finale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int m=0;
				ChoixBDD stock = new ChoixBDD();
				ArrayList<Choix> ch = stock.getListeChoix();
				EtudiantBDD recupetu = new EtudiantBDD();
				DominanteBDD recupdom = new DominanteBDD();
				ArrayList<Etudiant> etudiant = recupetu.getListeEtudiant();
				for (Etudiant etu : etudiant) {
					//je compte le nombre d'etudiants
					m++;
					}
				System.out.println(m);
				//Je cree une matrice qui va stocker tous les choix des etudiants par ordre de leur classement scolaire
				int tab[][]= new int[m][5];
				//Je remplis la matrice en respectant le classement académique  des etudiants 
				for(int i=0 ; i< m ; i++) {
					//Je recupere l id d un etudiant aleatoire 
					String id=etudiant.get(i).getId();
					//je recupere son classement 
					int cla = etudiant.get(i).getClassement();
				
					//Je stocke tous ses choix 
					ArrayList <Choix>stocker = stock.getChoix(id);
					//je remplis ma matrice avec les choix de chaque etudiant
					if (stocker.size() >= 5) {
					for(int j=0 ; j< 5; j++) {
						tab[cla-1][j]= stocker.get(j).getdomId();
					}
					}
					for (int p= 0; p< 5 ; p++) {
						// je recupere la dominante du choix p de mon etudiant i
						Dominante dom = recupdom.getDom(tab[cla-1][p]);
						//je recupere l indice de son choix
						int g= tab[cla-1][p];
						if(dom != null && dom.getNbPlaces() != 0) {
							//Je recupere mon etudiant dans la base de donnees
							Etudiant etudian = recupetu.getEtudiant(id);
							//J'attribue a mon etudiant un choix final
							int b=0;
							etudian.setChoix(g);
							int f = recupetu.updateEtuPlaces(etudian);
							
							if (f > 0) { // Vérifie si la mise à jour a réussi
							    System.out.println("Étudiant " + id + " affecté à la dominante " + g);
							    
							}
							//Je recupere la dominante qu on a affecté mon etudiant
							Dominante supDom = recupdom.getDom(f);
							int nb = supDom.getNbPlaces()-1;
							Dominante suppeDom = new Dominante(supDom.getidDom(),supDom.getNom(),supDom.getAcronyme(),nb,supDom.getNbPlacesApprenti(),supDom.getDepartement());
							int z = recupdom.updateNbPlaces(suppeDom);
							
						}
						
						
					}
				}
				
				
				JOptionPane.showMessageDialog(null, "Attribution des dominantes terminée !");
			}
			
		});
		
		menu.add(finale);
		menu.add(ajou);
	    menu.add(ajout);
		menu.add(ajouter);
		return menu;
	}
	
}
	


