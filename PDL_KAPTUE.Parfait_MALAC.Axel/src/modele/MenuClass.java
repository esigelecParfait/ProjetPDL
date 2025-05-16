package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.ChoixBDD;
import dao.DominanteBDD;
import gui.Choix;
import gui.Dominante;

public class MenuClass {

	 JFrame frame;
      String idEtudiant;
      LocalDateTime dateOuvertureApprenti;
      LocalDateTime dateFermetureApprenti;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuClass window = new MenuClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MenuClass(String inputId) {
		this.idEtudiant = inputId;
		System.out.println("ID de l'étudiant connecté : " + idEtudiant);
		initialize();
	}

	/**
	 * Create the application.
	 */
	public MenuClass() {
		initialize();
	}

	public MenuClass(LocalDateTime dateOuvertureApprenti, LocalDateTime dateFermetureApprenti) {
		this.dateOuvertureApprenti= dateOuvertureApprenti;
		this.dateFermetureApprenti= dateFermetureApprenti;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel menu = cretaeAddPanel();
		frame.add(menu);
	}

	private JPanel cretaeAddPanel() {
		JPanel menu = new JPanel();
		JButton choix = new JButton ("Choix");
		LocalDateTime maintenant = LocalDateTime.now();
		choix.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (maintenant.isBefore(dateOuvertureApprenti)) {
			        JOptionPane.showMessageDialog(null,
			                "La période d'accès n'a pas encore commencé.\nOuverture le : " + dateOuvertureApprenti,
			                "Accès non autorisé",
			                JOptionPane.WARNING_MESSAGE);
			        System.exit(0);
			    } else if (maintenant.isAfter(dateFermetureApprenti)) {
			        JOptionPane.showMessageDialog(null,
			                "La période d'accès est terminée.\nFermeture le : " + dateFermetureApprenti,
			                "Accès expiré",
			                JOptionPane.ERROR_MESSAGE);
			        System.exit(0);
			    }else {
				frame.setVisible(false);
				FenClassique fenetudiant = new FenClassique(idEtudiant);
                
                fenetudiant.frame.setVisible(true);
			    }
			}
			
		});
		 JButton btnNewButton = new JButton("Visualiser le calendrier");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnNewButton.setBounds(217, 69, 154, 41);
		 menu.add(btnNewButton);
		 JButton btnNewButto = new JButton("Consulter mes choix");
		 btnNewButto.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		ChoixBDD ch = new ChoixBDD();
				DominanteBDD dom = new DominanteBDD();
				ArrayList<Choix> choix = ch.getChoix(idEtudiant);
				ArrayList<Dominante>dominante  = dom.getListDom();
				StringBuilder result = new StringBuilder("Dominantes Choisies:\n");
				
				for(int i =0 ; i < 2 ; i++) {
					result.append("Choix numéro " + choix.get(i).getchoixPriorite() + ", Dominante choisie : " + dominante.get(i).getNom() + "\n");
					}
				 JOptionPane.showMessageDialog(null, result.toString());
		 	     
		 	}
		 });
		 btnNewButto.setBounds(217, 69, 154, 41);
		 menu.add(btnNewButto);
		 JButton btnNewButto1 = new JButton("Consulter le nombre de places restantes");
		 btnNewButto.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		ChoixBDD ch = new ChoixBDD();
				DominanteBDD dom = new DominanteBDD();
				ArrayList<Choix> choix = ch.getChoix(idEtudiant);
				ArrayList<Dominante>dominante  = dom.getListDom();
				StringBuilder result = new StringBuilder("Dominantes Choisies:\n");
				
				for(int i =0 ; i < 13 ; i++) {
					result.append("Dominante" + dominante.get(i).getNom() + ", Nombre de places restantes: " + dominante.get(i).getNbPlaces() + "\n");
					}
				 JOptionPane.showMessageDialog(null, result.toString());
		 	     
		 	}
		 });
		 btnNewButto.setBounds(217, 69, 154, 41);
		 menu.add(btnNewButto1);
		
		 menu.add(choix);
		return menu;
	}

}

