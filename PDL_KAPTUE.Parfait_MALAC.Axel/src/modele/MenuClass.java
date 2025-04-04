package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuClass {

	 JFrame frame;

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

	/**
	 * Create the application.
	 */
	public MenuClass() {
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
		choix.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FenClassique fenetudiant = new FenClassique();
                
                fenetudiant.frame.setVisible(true);
				
			}
			
		});
		 JButton btnNewButton = new JButton("Visualiser le calendrier");
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		 btnNewButton.setBounds(217, 69, 154, 41);
		 menu.add(btnNewButton);
		
		
		 menu.add(choix);
		return menu;
	}

}

