package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ConnexionBDD;
import dao.DominanteBDD;
import gui.Dominante;

public class AjoutDom {

	 JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutDom window = new AjoutDom();
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
	public AjoutDom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 932, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel ajdom = CreateAddPanell();
		frame.getContentPane().add(ajdom);
		
	}

	private JPanel CreateAddPanell() {
		JPanel dom = new JPanel();
		dom.setBounds(0, 0, 931, 410);
		
		JLabel domid = new JLabel("Id de la dominante");
		domid.setBounds(5, 9, 86, 13);
		JTextField domidd = new JTextField();
		domidd.setBounds(15, 32, 112, 19);
		dom.setLayout(null);
		dom.add(domid);
		dom.add(domidd);
		JLabel domnom = new JLabel("Nom de la dominante");
		domnom.setBounds(5, 61, 98, 13);
		JTextField domnomm = new JTextField();
		domnomm.setBounds(15, 84, 112, 19);
		dom.add(domnom);
		dom.add(domnomm);
		
		JLabel domacr = new JLabel("Acronyme de la dominante");
		domacr.setBounds(5, 118, 122, 13);
		JTextField domacrr = new JTextField();
		domacrr.setBounds(15, 141, 112, 19);
		dom.add(domacr);
		dom.add(domacrr);
		JLabel domnb = new JLabel("Nombre de places de la dominante");
		domnb.setBounds(5, 178, 159, 13);
		JTextField domnbb = new JTextField();
		domnbb.setBounds(15, 201, 112, 19);
		dom.add(domnb);
		dom.add(domnbb);
		JLabel domnbA = new JLabel("Nombre de places pour les apprentis dans la dominante");
		domnbA.setBounds(0, 240, 255, 13);
		JTextField dominbaa = new JTextField();
		dominbaa.setBounds(15, 274, 112, 19);
		dom.add(domnbA);
		dom.add(dominbaa);		
		JLabel domdep = new JLabel("Departement  de cette  dominante");
		domdep.setBounds(5, 315, 157, 13);
		JTextField domdepp = new JTextField();
		domdepp.setBounds(15, 348, 112, 19);
		dom.add(domdep);
		dom.add(domdepp);
		
		JButton ajout = new JButton("Ajouter une dominante");
		ajout.setBounds(406, 365, 137, 21);
		ajout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DominanteBDD aj = new DominanteBDD();
				Dominante dominante = new Dominante(Integer.valueOf(domidd.getText()),domnomm.getText(),domacrr.getText(),Integer.valueOf(domnbb.getText()),Integer.valueOf(dominbaa.getText()),Integer.valueOf(domdepp.getText()));
				int a=aj.addDom(dominante);
			System.out.println(a);
			}
			
		});
		dom.add(ajout);
		return dom;
	}

}
