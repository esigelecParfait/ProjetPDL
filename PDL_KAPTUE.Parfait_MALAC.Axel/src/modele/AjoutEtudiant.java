package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ConnexionBDD;
import dao.EtudiantBDD;
import gui.Etudiant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AjoutEtudiant extends MenuAdministrateur {

	 JFrame frame;
	private JTextField nom;
	private JTextField identifiant;
	private JTextField DateNaissance;
	private JTextField Classement;
	private JTextField Prenm;
	private JTextField Statut;
	private JTextField Contrat;
	private JTextField Entreprise;
	private JTextField IdPromo;
	private JTextField ChoixFinal;
	private JTextField mobilite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutEtudiant window = new AjoutEtudiant();
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
	public AjoutEtudiant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel aj = CreateAddPanel();
		frame.getContentPane().add(aj);
	}

	private JPanel CreateAddPanel() {
		JPanel aj = new JPanel();
		aj.setLayout(null);
		
		nom = new JTextField();
		nom.setBounds(20, 66, 123, 19);
		aj.add(nom);
		nom.setColumns(10);
		
		identifiant = new JTextField();
		identifiant.setBounds(20, 25, 123, 19);
		aj.add(identifiant);
		identifiant.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Identifiant de l'etudiant\r\n");
		lblNewLabel.setBounds(10, 10, 133, 13);
		aj.add(lblNewLabel);
		
		DateNaissance = new JTextField();
		DateNaissance.setBounds(20, 146, 123, 19);
		aj.add(DateNaissance);
		DateNaissance.setColumns(10);
		
		Classement = new JTextField();
		Classement.setBounds(20, 192, 123, 19);
		aj.add(Classement);
		Classement.setColumns(10);
		
		Prenm = new JTextField();
		Prenm.setBounds(20, 106, 123, 19);
		aj.add(Prenm);
		Prenm.setColumns(10);
		
		String[] options = {" Classique", "Apprenti"};
        JComboBox<String> statut = new JComboBox<>(options);
		statut.setBounds(20, 256, 96, 19);
		aj.add(statut);
		
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(10, 54, 45, 13);
		aj.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prénom");
		lblNewLabel_2.setBounds(10, 95, 45, 13);
		aj.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date de Naissance\r\n");
		lblNewLabel_3.setBounds(10, 135, 45, 13);
		aj.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Classement\r\n");
		lblNewLabel_4.setBounds(10, 181, 85, 13);
		aj.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Statut\r\n");
		lblNewLabel_5.setBounds(10, 233, 45, 13);
		aj.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Entreprise");
		lblNewLabel_6.setBounds(158, 57, 45, 13);
		aj.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Contrat");
		lblNewLabel_7.setBounds(171, 10, 45, 13);
		aj.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Choix Final");
		lblNewLabel_8.setBounds(158, 181, 45, 13);
		aj.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Id Promo\r\n");
		lblNewLabel_9.setBounds(158, 120, 45, 13);
		aj.add(lblNewLabel_9);
		
		Contrat = new JTextField();
		Contrat.setBounds(158, 25, 96, 19);
		aj.add(Contrat);
		Contrat.setColumns(10);
		
		Entreprise = new JTextField();
		Entreprise.setBounds(153, 92, 96, 19);
		aj.add(Entreprise);
		Entreprise.setColumns(10);
		
		IdPromo = new JTextField();
		IdPromo.setBounds(158, 146, 96, 19);
		aj.add(IdPromo);
		IdPromo.setColumns(10);
		mobilite = new JTextField();
		mobilite.setBounds(158, 256, 96, 19);
		aj.add(mobilite);
		mobilite.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Mobilite");
		lblNewLabel_10.setBounds(158, 233, 45, 13);
		aj.add(lblNewLabel_10);
		
		
		ChoixFinal = new JTextField();
		ChoixFinal.setBounds(158, 204, 96, 19);
		aj.add(ChoixFinal);
		ChoixFinal.setColumns(10);
		
		JButton btnNewButton = new JButton("Rechercher\r\n");
		btnNewButton.setBounds(368, 270, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			

			@Override
			// je dois optimiser le programme avec une boucle for qui va parcourir tous les id des etudiants
			
			public void actionPerformed(ActionEvent e) {
				EtudiantBDD rech = new EtudiantBDD();
				boolean vrai =false;
				ArrayList<Etudiant> liste = rech.getListeEtudiant();
				for ( Etudiant etudiant: liste) {
					if(identifiant.getText().equals(etudiant.getId())== true) {
						vrai =true;
						break;
					}
				}

				
				if(identifiant.getText().length()> 0 && vrai== true) {
				    
					
					Etudiant e1 = rech.getEtudiant(identifiant.getText());
					nom.setText(e1.getNom());
					Prenm.setText(e1.getPrenom());
					String date = e1.getDatedeNaissance(); // par exemple
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dateStr = sdf.format(date);
					DateNaissance.setText(dateStr); // ici monTextField est ton JTextField

					
				    Classement.setText(String.valueOf(e1.getClassement()));
				    Statut.setSelected(e1getStatut());
				    IdPromo.setText(String.valueOf(e1.getIdentifiantPromo()));
				    ChoixFinal.setText(String.valueOf(e1.getChoixFinal()));
				    Entreprise.setText(e1.getContrat());
				    mobilite.setText(e1.getMobilite());
				    Contrat.setText(e1.getEntreprise());
					
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous devez entrer un identifiant d'etudiant valide", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		aj.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mettre à jour");
		btnNewButton_1.setBounds(510, 270, 85, 21);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ide =identifiant.getText();
				EtudiantBDD  recup = new EtudiantBDD();
				Etudiant etu =  recup.getEtudiant(ide);
				Etudiant etudiant = new Etudiant(ide,nom.getText(),Prenm.getText(), etu.getMdp(),DateNaissance.getText(),Integer.valueOf(Classement.getText()),Statut.getText(),Entreprise.getText(),Contrat.getText(), mobilite.getText(), Integer.valueOf(ChoixFinal.getText()),Integer.valueOf(IdPromo.getText()));
				EtudiantBDD ajout = new EtudiantBDD();
				 int c =ajout.updateEtudiant(etudiant);
				System.out.println(c);
			}
			
		});
		aj.add(btnNewButton_1);
		

		
		return aj;
	}
}
