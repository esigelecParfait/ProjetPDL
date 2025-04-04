package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import gui.Dominante;

public class DominanteBDD extends ConnexionBDD{
	public DominanteBDD() { 

		super();  

		} 
	
		/**  

	 

		 * Qui permet de retourner les identifiants et le mdp de l'etudiant de la BDD  
		 * * @return  
         */  

	 public  ArrayList<Dominante> getListDom(){  

		 // Objet qui permet d'etablir une connexion a notre BDD
			Connection con = null;  
		//Objet pour executer des requetes SQL  
          PreparedStatement ps = null;
          //Objet qui permet de stocker le resultat des donnees d'une requete SQL 
          ResultSet rs = null;  
          ArrayList<Dominante> returnValue = new ArrayList<Dominante>();  

	      // connexion a la base de donnee
	      
	      try { 
	        	  
	              con = DriverManager.getConnection(URL, LOGIN, PASS);  
                  ps = con.prepareStatement("SELECT * FROM dominante ");  

               // on execute la requete  
                  rs = ps.executeQuery();  
			// on parcourt les lignes du result

			      while (rs.next()) {  
			    	  
			    	  returnValue.add(new Dominante(rs.getString("dom_nom"), rs.getInt("dom_nb_places"))); 
} 

	} catch (Exception ee) {  
                ee.printStackTrace();  
              } finally {  
     // fermeture du rs, du preparedStatement et de la connexion
            	try {  

	              if (rs != null) 
	            	  rs.close();  

	           } catch (Exception ignore) {  
             
	           }  
 
            	try {  

	               if (ps != null)  
                   ps.close();  

	 

			} catch (Exception ignore) {  

	           }  

	 

			try {  
                 
				if (con != null)  
              con.close();  
	 

			} catch (Exception ignore) {  

			}  

              }  

	        return returnValue;  

	 }  
	 public int addDom(Dominante dom) {
			Connection con = null;
			PreparedStatement ps = null;
			int returnValue = 0;

			// connexion a la base de donnees
			try {

				// tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				// preparation de l'instruction SQL, chaque ? represente une valeur
				// a communiquer dans l'insertion.
				// les getters permettent de recuperer les valeurs des attributs souhaites
				ps = con.prepareStatement("INSERT INTO dominante(dom_id, dom_nom, dom_accronyme, dom_nb_places,dom_nb_places_apprentis,dom_departement_id) VALUES(?, ?, ?, ?,?,?)");
				ps.setInt(1, dom.getidDom());
				ps.setString(2, dom.getNom());
				
				ps.setString(3, dom.getAcronyme());
				
				ps.setInt(4, dom.getNbPlaces());
				ps.setInt(5, dom.getNbPlacesApprenti());
				ps.setInt(6, dom.getDepartement());

				// Execution de la requete
				returnValue = ps.executeUpdate();

			} catch (Exception e) {
				if (e.getMessage().contains("ORA-00001"))
					System.out.println("Cette dominance existe deja !");
				else
					e.printStackTrace();
			} finally {
				// fermeture du preparedStatement et de la connexion
				try {
					if (ps != null) {
						ps.close();
					}
				} catch (Exception ignore) {
				}
				try {
					if (con != null) {
						con.close();
					}
				} catch (Exception ignore) {
				}
			}
			return returnValue;
		}
	}  

	 

	 



