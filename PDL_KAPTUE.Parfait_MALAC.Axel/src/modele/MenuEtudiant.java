package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuEtudiant extends Application{

	 JFrame frame;
	  String idEtudiant;
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
		JButton choix = new JButton ("Choix");
		choix.setBounds(27, 69, 140, 41);
		choix.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FenEtudiant fenetudiant = new FenEtudiant(idEtudiant );
                
                fenetudiant.frame.setVisible(true);
				
			}
			
		});
		 menu.setLayout(null);
		
		 menu.add(choix);
		 
		 JButton btnNewButton = new JButton("Visualiser le calendrier");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnNewButton.setBounds(217, 69, 154, 41);
		 menu.add(btnNewButton);
		return menu;
	}

}
