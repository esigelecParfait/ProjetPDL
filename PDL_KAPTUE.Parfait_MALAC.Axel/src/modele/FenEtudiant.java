package modele;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ConnexionBDD;
import dao.DominanteBDD;
import dao.EtudiantBDD;
import dao.ChoixBDD;
import gui.Choix;
import gui.Dominante;
import gui.Etudiant;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;

public class FenEtudiant extends Application {

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
					FenEtudiant window = new FenEtudiant();
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
	public FenEtudiant() {
		
		initialize();
	}


	public FenEtudiant(String idEtudiant) {
		this.idEtudiant = idEtudiant;
		System.out.println(idEtudiant);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame("Fenetre etudiant");
		frame.setBounds(100, 100, 699, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel etudiant = createAddPanel();
		frame.getContentPane().add(etudiant);
		
	}
    /**
     * Method which return the window to display
     * @return
     */
	@SuppressWarnings("serial")
	private JPanel createAddPanel() {
	     JPanel tab  = new JPanel();
	     DominanteBDD recupdom = new DominanteBDD();
	     ArrayList<Dominante>dom = recupdom.getListDom();
	     
	     //Colonnes de notre tableau à choix
	     String[] colonnes = { "Choix 1","Choix 2","Choix 3","Choix 4 ","Choix 5"};

	        //Lignes de notre tableau à choix
	        Object[][]donnees = {
	        		
	        			
	        		
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            {false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	            { false,false,false,false,false},
	        		  
	            
	            
	        };
	       
	      //  ButtonColumn buttonColumn = new ButtonColumn();
	        //Object which allow the organizqtion  of our tables choices
	        model = new DefaultTableModel(donnees, colonnes) {
	        	/**
	        	 * Methode qui permet de retourner les types de nos colonnes
	        	 */
	        	public Class<?> getColumnClass(int columnIndex) {
	                return columnIndex == 0 ? Boolean.class : Boolean.class;
	            }

	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return column >= 0&& column <= 5; // Désactive l'édition de la colonne 0
	            }
	            /**
	             * 
	             */
 
	            
	        };

	       JTable table = new JTable(model);
	       table.setBounds(191, 47, 450, 208);
	       table.addMouseListener(new MouseAdapter() {
	    	   public void mouseClicked(MouseEvent e) {
	    		   //Retrieve  the checked line 
	    		   int rowselect =table.getSelectedRow();
	    		   //recupere la colonne coche
	    		   int columnselect = table.getSelectedColumn();
	    		   //Parcours tous les colonnes
	    		   for(int i = 0;i <5;i++) {
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

	        // Bouton qui va permettre de stocker les choix des etudiants dans la BDD
	        JButton btnAfficher = new JButton("Valider");
	        btnAfficher.setBounds(271, 298, 92, 21);
	        btnAfficher.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	int cpt =0;
	            	ChoixBDD aj = new ChoixBDD();
	            	DominanteBDD dominante = new DominanteBDD();
	            	ArrayList<Dominante>domi= dominante.getListDom();
	            	System.out.println(domi.size());
	                StringBuilder result = new StringBuilder("Options sélectionnées :\n");
	                for(int j = 0; j < model.getColumnCount();j++) {
	                for (int i = 0; i < model.getRowCount(); i++) {
	                	//Recupere les choix
	                    boolean isSelected = (boolean) model.getValueAt(i, j);
	                    //Si l'etudiant a choisi cette domminance en choix j 
	                    if (isSelected) {
	                    	System.out.println(idEtudiant);
	                    	System.out.println(domi.get(i).getidDom());
	                    	System.out.println(j);
	          
	                    	Choix choix = new Choix(dom.get(i).getidDom(),idEtudiant,j+1);
	                         int  d= aj.addChoix(choix);
	                    	System.out.println(d);
	                    	cpt++;
	                    	
	                  
	                    	
	                    	
	                        result.append(model.getValueAt(i, 0)).append("\n");
	                    }
	                }
	                
	            }
	               // JOptionPane.showMessageDialog(null, result.toString());
	                if(cpt != 5) {
	                	JOptionPane.showMessageDialog(null, "Veuillez selectionner 5 choix", "Erreur", JOptionPane.ERROR_MESSAGE);
	                	aj.delete(idEtudiant);
	                	
	                }
	            }
	        });
	        tab.setLayout(null);
	        
	        tab.add(btnAfficher);
	        tab.add(table);
	        
	        JLabel lblNewLabel = new JLabel(dom.get(0).getNom());
	        lblNewLabel.setBounds(25, 48, 167, 13);
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
