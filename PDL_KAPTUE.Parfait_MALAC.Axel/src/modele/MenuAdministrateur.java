package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.ConnexionBDD;
import gui.Choix;
import gui.Etudiant;

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
		JButton finale = new JButton("Attribuer les dominantes");
		finale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int m=0;
				ConnexionBDD stock = new ConnexionBDD();
				ArrayList<Choix> ch = stock.getList();
				ArrayList<Etudiant> etudiant = stock.getListe();
				for (Etudiant etu : etudiant) {
					//je compte le nombre d'etudiants
					m++;
					}
				System.out.println(m);
				String tab[][]= new String[m][5];
				
				
				
			}
			
		});
		menu.add(finale);
		menu.add(ajou);
	    menu.add(ajout);
		menu.add(ajouter);
		return menu;
	}
	
}
	


