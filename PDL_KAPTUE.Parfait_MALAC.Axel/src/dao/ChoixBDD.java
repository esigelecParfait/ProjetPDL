package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import gui.Choix;
import model.Supplier;


public class ChoixBDD extends ConnexionBDD {
	public ChoixBDD() { 

		super();  

		} 
	/**
	 * 
	 * @param choix
	 * @return
	 */
	public int add(Choix choix) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue=0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("INSERT INTO choix(ch_dom_id,ch_etu_id,ch_priorite) VALUES(?, ?, ?)");
			ps.setInt(1, choix.getdomId());
			ps.setString(2, choix.getId());
			ps.setInt(3, choix.getchoixPriorite());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de fournisseur existe déjà. Ajout impossible !");
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
	public ArrayList<Choix> getList() {
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

}
