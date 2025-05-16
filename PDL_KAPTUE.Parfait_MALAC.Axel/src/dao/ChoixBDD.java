package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.Choix;
import gui.Etudiant;


public class ChoixBDD extends ConnexionBDD {
	public ChoixBDD() { 

		super();  

		} 
	/**
	 * 
	 * @param choix
	 * @return
	 */
	
	public ArrayList<Choix> getListeChoix() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Choix> returnValue = new ArrayList<Choix>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix ");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Choix(rs.getInt("ch_dom_id"),
						                     rs.getString("ch_etu_id"),
						                     
						                     rs.getInt("ch_priorite")));
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
	/**
	 * Method which return the choices by an 
	 */
	
 
	public ArrayList <Choix> getChoix(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Choix> returnValue = new ArrayList<Choix>();

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM choix  WHERE ch_etu_id  = ? ORDER BY ch_priorite ASC");
			ps.setString(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			while (rs.next()) {
				returnValue.add(new Choix(rs.getInt("ch_dom_id"),
	                     rs.getString("ch_etu_id"),
	                     
	                     rs.getInt("ch_priorite")));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
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
	public  ArrayList<Etudiant> getListeEtudiant(){
		// Objet  qui permet d'etablir une connexion a notre BDD
		Connection con = null;
		//Objet pour executer des requetes SQL
		PreparedStatement ps = null;
		//Objet qui permet de stocker le resultat des donnees d'une requete SQL
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant ");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			
			while (rs.next()) {
				returnValue.add(new Etudiant(
						                     rs.getString("etu_identifiant"),
						                     rs.getString("etu_mdp"),
						                     rs.getString("etu_statut")));
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
	
	public int delete(String  id) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ID
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID du fournisseur
			ps = con.prepareStatement("DELETE FROM choix  WHERE ch_etu_id = ?");
			ps.setString (1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("");
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
	public int addChoix(Choix choix) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    int returnValue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASS);
	        ps = con.prepareStatement("INSERT INTO choix(ch_dom_id, ch_etu_id, ch_priorite) VALUES (?, ?, ?)");
	        ps.setInt(1, choix.getdomId());
	        ps.setString(2, choix.getId());
	        ps.setInt(3, choix.getchoixPriorite());

	        returnValue = ps.executeUpdate();

	    } catch (SQLException e) {
	        if (e.getMessage().contains("ORA-00001")) {
	            System.out.println("Ce choix existe déjà. Suppression de l'ancien et ajout du nouveau...");

	            try {
	                // Suppression de l'ancien choix
	                ps = con.prepareStatement("DELETE FROM choix WHERE ch_etu_id = ?");
	                ps.setString(1, choix.getId());
	                ps.executeUpdate();

	                // Réinsertion du nouveau choix
	                ps = con.prepareStatement("INSERT INTO choix(ch_dom_id, ch_etu_id, ch_priorite) VALUES (?, ?, ?)");
	                ps.setInt(1, choix.getdomId());
	                ps.setString(2, choix.getId());
	                ps.setInt(3, choix.getchoixPriorite());

	                returnValue = ps.executeUpdate();
	            } catch (SQLException e1) {
	                if (e1.getMessage().contains("ORA-02292")) {
	                    System.out.println("Suppression impossible : contrainte d'intégrité.");
	                } else {
	                    e1.printStackTrace();
	                }
	            }
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        try { if (ps != null) ps.close(); } catch (Exception ignore) {}
	        try { if (con != null) con.close(); } catch (Exception ignore) {}
	    }

	    return returnValue;
	}

	public static void main(String[] args) throws SQLException {
		int returnValue;
		ChoixBDD choix = new  ChoixBDD();
		Choix ch1= new Choix(1,"emma5",1);
		returnValue = choix.addChoix(ch1);
		System.out.println(returnValue);
		
	}
}

