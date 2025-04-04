package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.Administrateur;
import gui.Etudiant;


    public class AdministrateurBDD extends ConnexionBDD {

/**
 * Constructeur
 * 
 */
   public AdministrateurBDD() {
     super();
}


/**
* Permet d'ajouter un administrateur dans la table admin.
* Le mode est auto-commit par defaut : chaque insertion est validee
* 
* @param admin le fournisseur a ajouter
* @return retourne le nombre de lignes ajoutees dans la table
*/
        public static   int add(Administrateur admin) {
          Connection con = null;
          PreparedStatement ps = null;
          int returnValue = 0;

    // connexion à la BDD
          try {

    // tentative de connexion
   con = DriverManager.getConnection(URL, LOGIN, PASS);
   // preparation de l'instruction SQL, chaque ? represente une valeur
   // a communiquer dans l'insertion.
   // les getters permettent de recuperer les valeurs des attributs souhaites
  ps = con.prepareStatement("INSERT INTO admin(ad_id, ad_nom, ad_prenom, ad_mdp) VALUES(?, ?, ?, ?)");
  ps.setString(1, admin.getId());
  ps.setString(2, admin.getNom());
  ps.setString(3, admin.getPrenom());
 ps.setString(4, admin.getMdp());

// Execution de la requete
  returnValue = ps.executeUpdate();

} catch (Exception e) {
if (e.getMessage().contains("ORA-00001"))
System.out.println("Cet identifiant admin existe déjà. Ajout impossible !");
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
* Permet de recuperer un 
* 
* @param 
* @return 
* 	
*/
public static   Administrateur get(String ad_id) {
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


/**
* Permet de recuperer tous les administrateurs  stockes dans la table admin
* 
* @return une ArrayList de fournisseur
*/

 public  static ArrayList<Administrateur> getList() {
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;
ArrayList<Administrateur> returnValue = new ArrayList<Administrateur>();

// connexion a la base de donnees

try {

	con = DriverManager.getConnection(URL, LOGIN, PASS);

	ps = con.prepareStatement("SELECT * FROM admin ORDER BY ad_id");

// on execute la requete

	rs = ps.executeQuery();
// on parcourt les lignes du resultat

	while (rs.next()) {

		returnValue.add(new Administrateur(null, null, rs.getString("AD_ID"), rs.getString("AD_MDP")));

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


public static void main(String[] args) throws SQLException {
 int returnValue;
AdministrateurBDD admin = new AdministrateurBDD();
// Ce test va utiliser directement votre BDD, on essaie d'éviter les collisions avec vos données en prenant de grands ID
String[] ids = {"jeandupont1", "424242", "idid"};
// test du constructeur
Administrateur ad1 = new Administrateur("Nom de l'admin", "Prenom de l'admin", ids[1], "mdp de l'admin");
Administrateur ad2 = new Administrateur("Malac", "Axel", ids[2], "Mdp34+");

// test de la methode add
returnValue = AdministrateurBDD.add(ad1);
System.out.println(returnValue + " admin ajoute");
returnValue = AdministrateurBDD.add(ad2);
System.out.println(returnValue + " admin ajoute");
System.out.println();

// test de la methode get
Administrateur sg = AdministrateurBDD.get("424242");
// appel implicite de la methode toString de la classe Object (a eviter)
System.out.println(sg);
System.out.println();

// test de la methode get list
ArrayList<Administrateur> list = new ArrayList<Administrateur>();
for (Administrateur ad : list) {
// appel explicite de la methode toString de la classe Object (a privilegier)
System.out.println(ad.toString());
}


}
/**
 * Methode qui permet de modifier les elements d un etudiant
 * @param etudiant
 * @return
 */



}


