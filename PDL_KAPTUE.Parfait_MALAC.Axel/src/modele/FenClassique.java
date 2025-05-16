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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ChoixBDD;
import dao.DominanteBDD;
import gui.Choix;
import gui.Dominante;

public class FenClassique {

	 JFrame frame;
	private DefaultTableModel model;

	private String idEtudiant;

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
	public FenClassique(String idEtudiant) {
		this.idEtudiant = idEtudiant;
		System.out.println(idEtudiant);
		initialize();
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
		frame.setBounds(100, 100, 697, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel classique = createAddPanel();
		frame.getContentPane().add(classique);
	}

	private JPanel createAddPanel() {
		 JPanel tab  = new JPanel();
		 DominanteBDD recupdom = new DominanteBDD();
	     ArrayList<Dominante>dom = recupdom.getListDom();
	     //Colonnes de notre tableau à choix
	     String[] colonnes = { "Choix 1","Choix 2"};

	        //Lignes de notre tableau à choix
	        Object[][]donnees = {
	        		{ false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false},
		            { false,false}
	            
	            
	        };
	        //Objet qui permet de d'organiser notre tableau
	        model = new DefaultTableModel(donnees, colonnes) {
	        	/**
	        	 * Methode qui permet de retourner les types de nos colonnes
	        	 */
	            public Class<?> getColumnClass(int columnIndex) {
	            	switch (columnIndex) {
	           
	            case 0: return Boolean.class;  
	            case 1: return Boolean.class;  
	           
	            
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
	    		   for(int i = 0; i <2; i++) {
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
	    			   for(int j = 0 ; j< table.getColumnCount(); j++) {
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
	        btnAfficher.setBounds(271, 298, 65, 21);
	        btnAfficher.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	int cpt=0;
	            	ChoixBDD aj = new ChoixBDD();
	            	DominanteBDD dominante = new DominanteBDD();
	            	ArrayList<Dominante>domi= dominante.getListDom();
	            	System.out.println(domi.size());
	                StringBuilder result = new StringBuilder("Options sélectionnées :\n");
	                for(int j = 0 ; j < model.getColumnCount();j++) {
	                for (int i = 0; i < model.getRowCount(); i++) {
	                	//Recupere les choix
	                    boolean isSelected = (boolean) model.getValueAt(i, j);
	                    //Si l'etudiant a choisi cette domminance en choix j 
	                    if (isSelected) {
	                    	System.out.println(" L id de l etudiant vaut " +idEtudiant);
	                    	System.out.println("Sa dominante choisie est " +domi.get(i).getidDom());
	                    	System.out.println("Son choix numero " +j);
	          
	                    	Choix choix = new Choix(dom.get(i).getidDom(),idEtudiant,j);
	                         int  d= aj.addChoix(choix);
	                    	System.out.println(d);
	                    	cpt++;
	                    	
	                    	
	                        result.append(model.getValueAt(i, 0)).append("\n");
	                    }
	                }
	                //JOptionPane.showMessageDialog(null, result.toString());
	            }
	                if(cpt != 2) {
	                	JOptionPane.showMessageDialog(null,	"Veuillez sélectionner 2 choix ","Erreur",JOptionPane.ERROR_MESSAGE);
	                	aj.delete(idEtudiant);
	                	
	                }
	                JOptionPane.showMessageDialog(null, result.toString());
	            }
	        });
	        tab.setLayout(null);
	        
	        tab.add(btnAfficher);
	        tab.add(table);
	        JLabel lblNewLabel = new JLabel(dom.get(0).getNom());
	        lblNewLabel.setBounds(10, 6, 167, 13);
	        tab.add(lblNewLabel);
	        
	        JLabel lblNewLabel_33 = new JLabel(dom.get(3).getNom());
	        lblNewLabel_33.setBounds(25, 95, 167, 13);
	        tab.add(lblNewLabel_33);
	        
	        JLabel lblNewLabel_111 = new JLabel(dom.get(1).getNom());
	        lblNewLabel_111.setBounds(25, 60, 167, 13);
	        tab.add(lblNewLabel_111);
	        
	        JLabel lblNewLabel_44 = new JLabel(dom.get(4).getNom());
	        lblNewLabel_44.setBounds(25, 111, 167, 13);
	        tab.add(lblNewLabel_44);
	        
	        JLabel lblNewLabel_22 = new JLabel(dom.get(2).getNom());
	        lblNewLabel_22.setBounds(25, 80, 167, 13);
	        tab.add(lblNewLabel_22);
	        
	        JLabel lblNewLabel_5 = new JLabel(dom.get(5).getNom());
	        lblNewLabel_5.setBounds(25, 128, 167, 13);
	        tab.add(lblNewLabel_5);
	        
	        JLabel lblNewLabel_11 = new JLabel(dom.get(11).getNom());
	        lblNewLabel_11.setBounds(25, 225, 167, 13);
	        tab.add(lblNewLabel_11);
	        
	        JLabel lblNewLabel_99 = new JLabel(dom.get(9).getNom());
	        lblNewLabel_99.setBounds(25, 194, 167, 13);
	        tab.add(lblNewLabel_99);
	        
	        JLabel lblNewLabel_66 = new JLabel(dom.get(6).getNom());
	        lblNewLabel_66.setBounds(25, 143, 167, 13);
	        tab.add(lblNewLabel_66);
	        
	        JLabel lblNewLabel_12 = new JLabel(dom.get(12).getNom());
	        lblNewLabel_12.setBounds(25, 242, 167, 13);
	        tab.add(lblNewLabel_12);
	        
	        JLabel lblNewLabel_10 = new JLabel(dom.get(10).getNom());
	        lblNewLabel_10.setBounds(25, 210, 167, 13);
	        tab.add(lblNewLabel_10);
	        
	        JLabel lblNewLabel_77 = new JLabel(dom.get(7).getNom());
	        lblNewLabel_77.setBounds(25, 158, 167, 13);
	        tab.add(lblNewLabel_77);
	        
	        JLabel lblNewLabel_88 = new JLabel(dom.get(8).getNom());
	        lblNewLabel_88.setBounds(25, 174, 167, 13);
	        tab.add(lblNewLabel_88);
	     
		
		
		return tab;
	



		
	}

}
