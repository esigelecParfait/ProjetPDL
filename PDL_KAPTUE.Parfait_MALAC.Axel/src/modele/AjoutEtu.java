package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import dao.ConnexionBDD;
import dao.EtudiantBDD;
import gui.Etudiant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class AjoutEtu extends MenuAdministrateur {

	 JFrame frame;
	private JTextField identifiant;
	private JTextField nom;
	private JTextField prenom;
	private JPasswordField password;
	private JTextField date;
	private JTextField classement;
	private JTextField statut;
	private JTextField entreprise;
	private JTextField contrat;
	private JTextField mobilite;
	private JTextField idpromo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutEtu window = new AjoutEtu();
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
	public AjoutEtu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel ajout = CreateAddPanel();
		frame.getContentPane().add(ajout);
	}

	private JPanel CreateAddPanel() {
		JPanel ajout = new JPanel();
		ajout.setBounds(0, 0, 639, 335);
		ajout.setLayout(null);
		JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(20, 234, 96, 19);
        ajout.add(dateChooser);
		
		identifiant = new JTextField();
		identifiant.setBounds(20, 48, 96, 19);
		ajout.add(identifiant);
		identifiant.setColumns(10);
		
		JLabel id = new JLabel("Identifiant");
		id.setBounds(10, 34, 45, 13);
		ajout.add(id);
		
		nom = new JTextField();
		nom.setBounds(20, 87, 96, 19);
		ajout.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setBounds(20, 137, 96, 19);
		ajout.add(prenom);
		prenom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(10, 64, 45, 19);
		ajout.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prénom");
		lblNewLabel_1.setBounds(10, 116, 45, 13);
		ajout.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(20, 182, 96, 19);
		ajout.add(password);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setBounds(10, 166, 75, 13);
		ajout.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_3 = new JLabel("Date de Naissance");
		lblNewLabel_3.setBounds(10, 211, 75, 13);
		ajout.add(lblNewLabel_3);
		
		classement = new JTextField();
		classement.setBounds(20, 285, 96, 19);
		ajout.add(classement);
		classement.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Classement");
		lblNewLabel_4.setBounds(10, 262, 45, 13);
		ajout.add(lblNewLabel_4);
		
		String[] options = {"Veuillez sélectionner le statut"," Classique", "Apprenti"};
        JComboBox<String> statut = new JComboBox<>(options);
		statut.setBounds(203, 48, 174, 19);
		ajout.add(statut);
		
		
		JLabel lblNewLabel_5 = new JLabel("Statut");
		lblNewLabel_5.setBounds(200, 34, 45, 13);
		ajout.add(lblNewLabel_5);
		
		entreprise = new JTextField();
		entreprise.setBounds(203, 87, 96, 19);
		ajout.add(entreprise);
		entreprise.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Entreprise");
		lblNewLabel_6.setBounds(203, 67, 45, 13);
		ajout.add(lblNewLabel_6);
		
		contrat = new JTextField();
		contrat.setBounds(203, 137, 96, 19);
		ajout.add(contrat);
		contrat.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Contrat");
		lblNewLabel_7.setBounds(200, 116, 45, 13);
		ajout.add(lblNewLabel_7);
		
		mobilite = new JTextField();
		mobilite.setBounds(203, 182, 96, 19);
		ajout.add(mobilite);
		mobilite.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("mobilite");
		lblNewLabel_8.setBounds(200, 166, 45, 13);
		ajout.add(lblNewLabel_8);
		
		idpromo = new JTextField();
		idpromo.setBounds(203, 234, 96, 19);
		ajout.add(idpromo);
		idpromo.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Promotion");
		lblNewLabel_10.setBounds(203, 211, 45, 13);
		ajout.add(lblNewLabel_10);
		
		JButton btnNewButton = new JButton("Ajouter un étudiant");
		btnNewButton.setBounds(475, 284, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EtudiantBDD aj = new EtudiantBDD();
				java.util.Date selectedDate = dateChooser.getDate();
				if (selectedDate == null) {
                    JOptionPane.showMessageDialog(frame, "Veuillez choisir une date.");
                    
                    return;
                }
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(selectedDate);
				Etudiant etudiant = new Etudiant(identifiant.getText(),nom.getText(),prenom.getText(),password.getText(),dateStr,Integer.valueOf(classement.getText()),(String) statut.getSelectedItem(),entreprise.getText(),contrat.getText(),mobilite.getText(),Integer.valueOf(idpromo.getText()));
				
			   int b= aj.addEtudiant(etudiant);
			   System.out.println(b);
			
			}
		});
		ajout.add(btnNewButton);
		return ajout;
	}
}
