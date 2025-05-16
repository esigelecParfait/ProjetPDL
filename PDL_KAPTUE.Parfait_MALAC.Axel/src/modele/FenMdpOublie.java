package modele;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import gui.*;
import dao.*;
 
public class FenMdpOublie {
    JFrame frame;
    private JTextField id;
    private JPasswordField nvMdp;
    
    
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenMdpOublie window = new FenMdpOublie();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public FenMdpOublie() {
        initialize();
        
    }
 
    private void initialize() {
        frame = new JFrame("Réinitialisation du mot de passe");
        frame.setBounds(100, 100, 1060, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = panel();
        frame.getContentPane().add(panel);
    }
 
    private JPanel panel() {
        JPanel panel = new JPanel();
        panel.setForeground(Color.BLACK);
        panel.setBounds(0, 0, 436, 263);
        panel.setLayout(null);
        
        // Titre
        JLabel titre = new JLabel("Réinitialisation du mot de passe");
        titre.setFont(new Font("Tahoma", Font.BOLD, 14));
        titre.setBounds(116, 20, 250, 30);
        panel.add(titre);
        
        // Identifiant
        JLabel idLabel = new JLabel("Identifiant :");
        idLabel.setBounds(50, 70, 100, 30);
        panel.add(idLabel);
        
        id = new JTextField();
        id.setBounds(150, 70, 200, 30);
        panel.add(id);
        
        // Nouveau mot de passe
        JLabel mdpLabel = new JLabel("Nouveau mot de passe :");
        mdpLabel.setBounds(50, 120, 150, 30);
        panel.add(mdpLabel);
        
        nvMdp = new JPasswordField();
        nvMdp.setBounds(150, 120, 200, 30);
        panel.add(nvMdp);
        
        // Bouton de validation
        JButton validerButton = new JButton("Valider");
        validerButton.setBounds(150, 180, 100, 30);
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	nvMotDePasse();
            	
            }
        });
        panel.add(validerButton);
        
        // Bouton annuler
        JButton annulerButton = new JButton("Annuler");
        annulerButton.setBounds(260, 180, 100, 30);
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
        		
        		//affiche la fênetre pour le changement du mot de passe
        	    Application appli = new Application();
        	    appli.frame.setVisible(true);
            }
        });
        panel.add(annulerButton);
        
        return panel;
    }
 
    private void nvMotDePasse() {
        String identifiant = new String(id.getText());
        String nouveauMdp = new String(nvMdp.getPassword());
        
        // Validation des champs
        if (identifiant.isEmpty() || nouveauMdp.isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Veuillez remplir tous les champs", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Vérification dans la base de données
        EtudiantBDD etubdd = new EtudiantBDD();
        ArrayList<Etudiant> etudiants = etubdd.getListeEtudiant();
        AdministrateurBDD adbdd = new AdministrateurBDD();
        ArrayList<Administrateur> administrateur = adbdd.getListeAdmin();
        
        boolean found = false;

  
        if (!found) {
            for (Administrateur admin : administrateur) {
                if (identifiant.equals(admin.getId())) {
                    // Mettre à jour le mot de passe
                    admin.setMdp(nouveauMdp);
                    AdministrateurBDD.update(admin);
                    found = true;
                    JOptionPane.showMessageDialog(frame, 
                            "Mot de passe réinitialisé avec succès", 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        Application appli = new Application();
                        appli.frame.setVisible(true);
                    break;
                }
            }
            for (Etudiant etu : etudiants) {
            	if (identifiant.equals(etu.getId())) {
            		etu.setMdp(nouveauMdp);
            		EtudiantBDD.update(etu);
            		found=true;
            		JOptionPane.showMessageDialog(frame, 
                            "Mot de passe réinitialisé avec succès", 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        Application appli = new Application();
                        appli.frame.setVisible(true);
            		break;
            	}
            }
        }
        
        else {
            JOptionPane.showMessageDialog(frame, 
                "Identifiant non trouvé", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
 