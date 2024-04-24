package modele.location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Loca dao.
 */
public class LocaDaoImpl implements LocaDao {
    //Ajouter un location à la base de données
    public boolean addLocation(Location location) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        String sql = "INSERT INTO `vehicules` (MailLoca, Photo1, Photo2, nomvoiture, description, categorie, modele, Note, Prix, Capacite, nb_porte, nb_cheveaux, electrique, automatique) VALUES ('"
                + Location.getMail() + "', '" + Location.getPhoto1() + "', '" + Location.getPhoto2() + "', '"
                + Location.getNomvoiture() + "', '', '" + Location.getCategorie() + "', '', 0, " // `description` et `Note` sont vides ou à 0
                + Location.getPrix() + ", " + Location.getCapacite() + ", 0, 0, 0, 0);"; // `nb_porte`, `nb_cheveaux`, `electrique` et `automatique` sont tous à 0



        try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
            Statement statement = conn.createStatement();){
            int count = statement.executeUpdate(sql);
            System.out.println("number of rows affected by this query= " + count);
            if(count==0)
                return false;
            else return true;
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
