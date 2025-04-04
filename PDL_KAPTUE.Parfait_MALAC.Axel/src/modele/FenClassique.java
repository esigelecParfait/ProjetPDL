package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DominanteBDD;
import gui.Dominante;

public class FenClassique {

	 JFrame frame;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenClassique window = new FenClassique();
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
	public FenClassique() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Classique");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel classique = createAddPanel();
		frame.add(classique);
	}

	private JPanel createAddPanel() {
		 JPanel tab  = new JPanel();
		 DominanteBDD recupdom = new DominanteBDD();
	     ArrayList<Dominante>dom = recupdom.getListDom();
	     //Colonnes de notre tableau à choix
	     String[] colonnes = {"Dominante", "Choix 1","Choix 2"};

	        //Lignes de notre tableau à choix
	        Object[][]donnees = {
	            {dom.get(0).getAcronyme(), false,false},
	            {dom.get(1).getAcronyme(), false,false},
	            {"Cybersécurité des Réseaux et de l’IOT", false,false},
	            {"Big Data pour la Transformation Numérique", false,false},
	            {"Ingénieur d’Affaires : Informatique et Réseaux", false,false},
	            {"Ingénierie des Services du Numérique", false,false},
	            {"Ingénieur Finance", false,false,false,false,false},
	            {"Digitalisation, Automatisation, Robotique et Intelligence Artificielle pour l’industrie", false,false},
	            {"Énergie et Développement Durable", false,false},
	            {"Génie Électrique et Transport", false,false},
	            {"Ingénieur d’Affaires : Distribution Énergie et Signaux", false,false},
	            {"Mécatronique et Systèmes Embarqués", false,false},
	            {"Ingénierie des Systèmes Embarqués Mobiles Autonomes et Connectés", false,false},
	            {"Electronique des Systèmes pour l’Automobile et l’Aéronautique", false,false},
	            
	            
	        };
	        //Objet qui permet de d'organiser notre tableau
	        model = new DefaultTableModel(donnees, colonnes) {
	        	/**
	        	 * Methode qui permet de retourner les types de nos colonnes
	        	 */
	            public Class<?> getColumnClass(int columnIndex) {
	            	switch (columnIndex) {
	            case 0: return String.class;   
	            case 1: return Boolean.class;  
	            case 2: return Boolean.class;  
	            
	            
	            default: return Object.class;
	            	}
	            
	            }
	            /**
	             * 
	             */
 
	            
	        };

	       JTable table = new JTable(model);
	       table.setBounds(75, 5, 450, 224);
	       table.addMouseListener(new MouseAdapter() {
	    	   public void mouseClicked(MouseEvent e) {
	    		   //Recupere la ligne coche 
	    		   int rowselect =table.getSelectedRow();
	    		   //recupere la colonne coche
	    		   int columnselect = table.getSelectedColumn();
	    		   //Parcours tous les colonnes
	    		   for(int i = 1; i <3; i++) {
	    			   //Si c'est la colonne selectionnée
	    			   if(i==columnselect) {
	    				   //Pour tous les lignes
	    			   for(int j = 0 ; j< table.getRowCount(); j++) {
	    				   //Si une ligne est differente de la ligne ou on clicque
	    				   if (j != rowselect) {
	    					   //Ca met tous les lignes de la colonnes i a false
	    					   table.setValueAt(false, j, i);
	    					  
	    					   
	    				   }
	    				   
	    			   }
	    			   table.setValueAt(true, rowselect, i);
	    		   }
	    		   
	    		   } 
	    		   for(int i = 0; i <14; i++) {
	    			   //Si c'est la ligne  selectionnée
	    			   if(i==rowselect) {
	    				   //Pour tous les colonnes
	    			   for(int j = 1 ; j< table.getColumnCount(); j++) {
	    				   //Si une colonne est differente de la colonne ou on clicque
	    				   if (j != columnselect) {
	    					   //Ca met tous les lignes de la colonnes i a false
	    					   table.setValueAt(false, i, j);
	    					  
	    					   
	    				   }
	    				   
	    			   }
	    			   table.setValueAt(true, i, columnselect);
	    		   }
	    		   
	    		   } 
	    	   }
	    	   
	       });

	        // Bouton pour afficher les options sélectionnées
	        JButton btnAfficher = new JButton("Valider");
	        btnAfficher.setBounds(198, 232, 65, 21);
	        btnAfficher.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                StringBuilder result = new StringBuilder("Options sélectionnées :\n");
	                for (int i = 0; i < model.getRowCount(); i++) {
	                    boolean isSelected = (boolean) model.getValueAt(i, 1);
	                    if (isSelected) {
	                        result.append(model.getValueAt(i, 0)).append("\n");
	                    }
	                }
	                JOptionPane.showMessageDialog(null, result.toString());
	            }
	        });
	        tab.setLayout(null);
	        
	        tab.add(btnAfficher);
	        tab.add(table);
	     
		
		
		return tab;
	



		
	}

}
