package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.ChoixBDD;
import dao.DominanteBDD;
import gui.Choix;
import gui.Dominante;

public class MenuEtudiant extends Application{

	 JFrame frame;
	  String idEtudiant;
	  LocalDateTime dateOuvertureClassique;
	  LocalDateTime dateFermetureClassique;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MenuEtudiant window = new MenuEtudiant();
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
	public MenuEtudiant() {
		initialize();
	}

	public MenuEtudiant(String inputId) {
		this.idEtudiant = inputId;
		System.out.println("ID de l'étudiant connecté : " + idEtudiant);
		initialize();
	}

	public MenuEtudiant(LocalDateTime dateOuvertureClassique,LocalDateTime dateFermetureClassique) {
		this.dateOuvertureClassique= dateOuvertureClassique;
		initialize();// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel menu = cretaeAddPanel();
		frame.getContentPane().add(menu);
	}

	private JPanel cretaeAddPanel() {
		JPanel menu = new JPanel();
		LocalDateTime maintenant = LocalDateTime.now();
		
		JButton choix = new JButton ("Choix");
		choix.setBounds(27, 69, 140, 41);
		choix.addActionListener(new ActionListener () {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if (maintenant.isBefore(dateOuvertureClassique)) {
        JOptionPane.showMessageDialog(null,
                "La période d'accès n'a pas encore commencé.\nOuverture le : " + dateOuvertureClassique,
                "Accès non autorisé",
                JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    } else if (maintenant.isAfter(dateFermetureClassique)) {
        JOptionPane.showMessageDialog(null,
                "La période d'accès est terminée.\nFermeture le : " + dateFermetureClassique,
                "Accès expiré",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }else {
				frame.setVisible(false);
				FenEtudiant fenetudiant = new FenEtudiant(idEtudiant );
                
                fenetudiant.frame.setVisible(true);
		        }
			
			}
		});
		
		
		
		
		 menu.add(choix);
		 
		 JButton btnNewButton = new JButton("Visualiser le calendrier");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 	}
		 });
		 btnNewButton.setBounds(217, 69, 154, 41);
		 JButton btnNewButto = new JButton("Consulter mes choix");
		 btnNewButto.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		ChoixBDD ch = new ChoixBDD();
				DominanteBDD dom = new DominanteBDD();
				ArrayList<Choix> choix = ch.getChoix(idEtudiant);
				ArrayList<Dominante>dominante  = dom.getListDom();
				StringBuilder result = new StringBuilder("Dominantes Choisies:\n");
				
				for(int i =0 ; i < 5 ; i++) {
					result.append("Choix numéro " + choix.get(i).getchoixPriorite() + ", Dominante choisie : " + dominante.get(i).getNom() + "\n");
					}
				 JOptionPane.showMessageDialog(null, result.toString());
		 	     
		 	}
		 });
		 btnNewButto.setBounds(384, 134, 154, 41);
		 menu.add(btnNewButto);
		 JButton btnNewButto1 = new JButton("Consulter le nombre de places restantes");
		 btnNewButto1.addActionListener(new ActionListener() {
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
		 btnNewButto1.setBounds(112, 152, 154, 41);
		 menu.add(btnNewButto1);
		return menu;
	}

}

