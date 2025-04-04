package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import gui.Administrateur;
import gui.Choix;
import gui.Dominante;
import gui.Etudiant;


/**
 * Classe d'accèes à la base de données
 */

public class ConnexionBDD {
	/**
	 * Parametres de connexion a la base de donnees oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	// � utiliser si vous �tes sur une machine personnelle :
	final static String URL   = "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl";
	
	// � utiliser si vous �tes sur une machine de l'�cole :
	//final static String URL   = "jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int";

	final static String LOGIN = "C##BDD4_2";   // remplacer les ********. Exemple C##BDD1_1
	final static String PASS  = "BDD42";   // remplacer les ********. Exemple BDD11
	
	
	public ConnexionBDD() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}


/**
 * Method which aloww to add a choice in the table choixe
 * @param choix
 */
	
public int add(Choix choix) {
	Connection con = null;
	PreparedStatement ps = null;
	int returnValue =0;

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
			System.out.println("Ce choix a deja ete ajouté !");
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
/**
 * Method which get a student from his Id
 * @param id
 * @return
 */
	/*
public Etudiant  get(String id) {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Etudiant  returnValue = null;

	// connexion a la base de donnees
	try {

		con = DriverManager.getConnection(URL, LOGIN, PASS);
		ps = con.prepareStatement("SELECT * FROM etudiant WHERE etu_identifiant  = ?");
		ps.setString(1, id);

		// on execute la requete
		// rs contient un pointeur situe juste avant la premiere ligne retournee
		rs = ps.executeQuery();
		// passe a la premiere (et unique) ligne retournee
		if (rs.next()) {
			returnValue = new Etudiant(rs.getString("etu_identifiant"),
								       rs.getString("etu_nom"),
								       rs.getString("etu_prenom"),
								       rs.getString("etu_mdp"),
								       rs.getString("etu_date_naissance"),
								       rs.getInt("etu_classement"),
								       rs.getString("etu_statut"),
								       rs.getString("etu_entreprise"),
								       rs.getString("etu_contrat"),
								       rs.getString("etu_mobilite"),
								       rs.getInt("etu_id_promo"),
								       rs.getInt("etu_choix_final_id"));
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
/**
 * Mehod which add a student in the BDD
 * @param etudiant
 * @return
 */
/*
public int add(Etudiant  etudiant) {
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
		ps = con.prepareStatement("INSERT INTO etudiant (etu_identifiant,etu_nom,etu_prenom,etu_mdp,etu_date_naissance,etu_classement,etu_statut,etu_entreprise,etu_contrat,etu_mobilite,etu_choix_final_id,etu_id_promo) VALUES(?, ?, ?, ?,?,?,?,?,?,?,?,?)");
		ps.setString(1, etudiant .getId());
		ps.setString(2, etudiant.getNom());
		ps.setString(3, etudiant.getPrenom());
		ps.setString(4, etudiant.getMdp());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(etudiant.getDatedeNaissance());

		ps.setString(5, formattedDate);
		ps.setInt(6, etudiant.getClassement());
		ps.setString(7, etudiant.getStatut());
		ps.setString(8, etudiant.getEntreprise());
		ps.setString(9, etudiant.getContrat());
		ps.setString(10, etudiant.getMobilite());
		ps.setInt(11, etudiant.getChoixFinal());
		ps.setInt(12, etudiant.getIdentifiantPromo());
		

		// Execution de la requete
		returnValue = ps.executeUpdate();

	} catch (Exception e) {
		if (e.getMessage().contains("ORA-00001"))
			System.out.println("Cet Etudiant existe déjà. Ajout impossible !");
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
/**
 * Method which modify the paramaters of a student 
 * @param etudiant
 * @return
 */
/*
public int update(Etudiant etudiant ) {
	Connection con = null;
	PreparedStatement ps = null;
	int returnValue = 0;

	// connexion a la base de donnees
	try {

		// tentative de connexion
		con = DriverManager.getConnection(URL, LOGIN, PASS);
		// preparation de l'instruction SQL, chaque ? represente une valeur
		// a communiquer dans la modification.
		// les getters permettent de recuperer les valeurs des attributs souhaites
		ps = con.prepareStatement("UPDATE etudiant  set  etu_nom = ?, etu_prenom = ?, etu_mdp =?, etu_date_naissance = TO_DATE(?, 'YYYY-MM-DD') , etu_classement = ?, etu_statut = ?, etu_entreprise =?,etu_contrat =?, etu_mobilite =?,etu_choix_final_id =?, etu_id_promo=?  WHERE etu_identifiant = ?");
		ps.setString(1, etudiant.getNom());
		ps.setString(2, etudiant.getPrenom());
		ps.setString(3,etudiant.getMdp());
		ps.setString(4, etudiant.getDatedeNaissance().substring(0, 10));
		ps.setInt(5, etudiant.getClassement());
		ps.setString(6, etudiant.getStatut());
		ps.setString(7, etudiant.getEntreprise());
		ps.setString(8, etudiant.getContrat());
		ps.setString(9, etudiant.getMobilite());
		ps.setInt(10, etudiant.getChoixFinal());
		ps.setInt(11, etudiant.getIdentifiantPromo());
		ps.setString(12, etudiant.getId());
		
		
		
		
		

		// Execution de la requete
		returnValue = ps.executeUpdate();

	} catch (Exception e) {
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
/*
public int add(Dominante dom) {
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
/*
public  Administrateur getA(String ad_id) {
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;
Administrateur returnValue = null;

// connexion a la base de donnees
try {
	con = DriverManager.getConnection(URL, LOGIN, PASS);

	ps = con.prepareStatement("SELECT * FROM admin WHERE ad_id = ?");

	ps.setString(1, ad_id);


	// on execute la requete

	// rs contient un pointeur situe juste avant la premiere ligne retournee

	rs = ps.executeQuery();

	// passe a la premiere (et unique) ligne retournee

	if (rs.next()) {

		returnValue = new Administrateur(null, null, rs.getString("AD_ID"), rs.getString("AD_MDP"));

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
*/
public  ArrayList<Etudiant> getListe(){
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
public ArrayList <Choix> get(String id) {
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

}