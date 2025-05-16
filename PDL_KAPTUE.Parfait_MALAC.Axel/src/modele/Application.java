package modele;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.AdministrateurBDD;

import dao.EtudiantBDD;
import gui.Administrateur;
import gui.Etudiant;
import java.awt.Color;
import java.awt.Cursor;

public class Application {

	 JFrame frame;
	protected String idEtudiant;
	public String getIdEtudiant() {
		return idEtudiant;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
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
	public Application() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//
		JPanel Connexion = createAddPane();
		frame.getContentPane().add(Connexion);
		
	}
	
	private JPanel createAddPane() {
        JPanel connexion = new JPanel();
        connexion.setForeground(Color.BLACK);
        connexion.setBounds(0, -10, 436, 263);
        
        JLabel id = new JLabel("Identifiant");
        id.setBounds(10, 101, 213, 33);
        JTextField ide = new JTextField(); 
        ide.setBounds(116, 97, 213, 41);
        connexion.setLayout(null);
        connexion.add(id);
        connexion.add(ide);
        
        JLabel mdp = new JLabel("Votre mot de passe");
        mdp.setForeground(Color.BLACK);
        mdp.setBounds(10, 169, 213, 13);
        JPasswordField mdpe = new JPasswordField(15); 
        mdpe.setBounds(116, 155, 213, 41);
        connexion.add(mdp);
        connexion.add(mdpe);
        JLabel oublie = new JLabel("Mot de passe oublié");
        oublie.setForeground(Color.BLACK);
        oublie.setBounds(306, 52, 213, 13);
    	oublie.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	oublie.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	JOptionPane.showMessageDialog(null, "Mot de passe oublie");
                 FenMdpOublie mdp = new FenMdpOublie();
                 mdp.frame.setVisible(true);
                 
            }
        });
    	//Bouton qui va permettre qu on se connecte
        
        JButton connect = new JButton("Se connecter");
        connect.setForeground(Color.RED);
        connect.setBounds(116, 206, 213, 41);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	EtudiantBDD recupetu = new EtudiantBDD();
                ArrayList<Etudiant> etu = recupetu.getListeEtudiant();
                AdministrateurBDD recupadmin = new AdministrateurBDD();
                ArrayList<Administrateur> admin = recupadmin.getListeAdmin();
                
                String inputId = ide.getText();
                String inputMdp = new String(mdpe.getPassword()); 
                
                // Vérifier dans la liste des étudiants
                for (Etudiant etudiant : etu) {
                    if (inputId.equals(etudiant.getId()) && inputMdp.equals(etudiant.getMdp())) {
                    	if(etudiant.getStatut().equals("Classique")) {
                    		//C est a partir de ce If qu il va entrer dans la fentre pour les etudiants en classique 
                    	frame.setVisible(false);
                    	MenuEtudiant fenetudiant = new MenuEtudiant(inputId);
                        System.out.println(inputId);
                        fenetudiant.frame.setVisible(true);
                        idEtudiant = inputId;
                        return;
                        
                        
                        
                    }
                    	if(etudiant.getStatut().equals("Apprenti")) {
                    		frame.setVisible(false);
                    		MenuClass feneclassique = new MenuClass(inputId);
                    		System.out.println(inputId);
                    		feneclassique.frame.setVisible(true);
                    		idEtudiant = inputId;
                    		return;
                    	}
                    
                }
                    
                }
                
                
                
                // Vérifier dans la liste des administrateurs
                for (Administrateur administrateur : admin) {
                    if (inputId.equals(administrateur.getId()) && inputMdp.equals(administrateur.getMdp())) {
                    	//JOptionPane.showMessageDialog(null, "Admin valide");
                    	frame.setVisible(false);
                       MenuAdministrateur fenadmin = new MenuAdministrateur();
                       fenadmin.frame.setVisible(true);
                        return; 
                    }
                }
                
                
                JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            
        });

        connexion.add(connect);
        
        
    	connexion.add(oublie);
        return connexion;
    }
	
	

	
}