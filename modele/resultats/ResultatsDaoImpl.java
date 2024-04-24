package modele.resultats;

import modele.location.Location;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * The type Resultats dao.
 */
public class ResultatsDaoImpl implements ResultatsDao{
    public ArrayList<Location> resultats(String localisation, Date dateA, Date dateR, int capacite) throws SQLException, ClassNotFoundException {
        ArrayList<Location> maliste = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateDebut = dateFormat1.format(dateA);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateFin = dateFormat2.format(dateR);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les voitures par
        /*select * from vehicile
        where Modele ='g63' And Capacite*nb_chambres>=1
        AND Id_H NOT IN (SELECT Id_H FROM reservations Where (DateArrivee<'2023-04-22' and '2023-04-22'<DateDepart) OR (DateArrivee<'2023-04-19' and '2023-04-19'<DateDepart))*/
        String requeteSQL = "SELECT * FROM vehicules WHERE nomvoiture = '" + localisation + "' " +
                           // "AND Capacite*nbporte>= " + capacite + " " +
                            "AND Id_L NOT IN (SELECT Id_L FROM reservations " +
                            "Where (DateLocation<"+dateFin+" AND " +dateFin+ " <DateRevien) " +
                            "OR (DateLocation< "+dateDebut+" AND "+dateDebut+"< DateRevien));";
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
             // Préparation de la requête avec le paramètre de localisation recherchée
             Statement st = conn.createStatement()) {
            System.out.println("UI");

            // Exécution de la requête
            ResultSet resultSet = st.executeQuery(requeteSQL);

            // Parcours des résultats et affichage des appartements
            while (resultSet.next()) {
                Location location = new Location();
                location.setLocationId(resultSet.getInt("Id_L"));
                location.setPhoto1(resultSet.getString("Photo1"));
                location.setPhoto2(resultSet.getString("Photo2"));
                location.setModele(resultSet.getString("MODELE"));
                location.setCategorie(resultSet.getString("Categorie"));
                location.setNomvoiture(resultSet.getString("nomvoiture"));
                location.setNote(resultSet.getInt("Note"));
                location.setPrix(resultSet.getInt("Prix"));
                location.setCapacite(resultSet.getInt("Capacite"));
                location.setElectrique(resultSet.getBoolean("Electrique"));
                location.setAutomatique(resultSet.getBoolean("Automatique"));
                maliste.add(location);
                System.out.println(location.getModele());
            }
            // Fermeture des ressources
            resultSet.close();
        } catch (SQLException e) {
//                System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
        return maliste;
    }


    public ArrayList<String> avis(Location x) throws SQLException, ClassNotFoundException {
        ArrayList<String> listeAvis = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase = "root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les appartements par localisation
        String requeteSQL = "SELECT * FROM avis WHERE Id_L = '" + x.getLocationId() + "';";
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
             // Préparation de la requête avec le paramètre de localisation recherchée
             Statement st = conn.createStatement()) {

            // Exécution de la requête
            ResultSet resultSet = st.executeQuery(requeteSQL);

            // Parcours des résultats et affichage des appartements
            while (resultSet.next()) {
                listeAvis.add(resultSet.getString("Commentaire"));
                System.out.println(resultSet.getString("Commentaire"));
            }

        }
        return listeAvis;
    }

    @Override
    public ArrayList<Location> filtrage(Resultats resultats,float prix, String categorie) throws SQLException, ClassNotFoundException {
        ArrayList<Location> maliste = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateDebut = dateFormat1.format(resultats.getDateA());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateFin = dateFormat2.format(resultats.getDateR());
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les voitutea
        /*select * from location
        where Lieu ='' And Capacite*nb_chambres>=1
        AND Id_H NOT IN (SELECT Id_H FROM reservations Where (DateArrivee<'2023-04-22' and '2023-04-22'<DateDepart) OR (DateArrivee<'2023-04-19' and '2023-04-19'<DateDepart))*/
        String requeteSQL = "SELECT * FROM vehicules WHERE nomvoiture = '" + resultats.getSearchTex() + "' " +
                            "AND Capacite*nbPorte>= " + resultats.getCapacite() + " " +
                            "AND Prix<= " + prix + " AND Categorie in " + categorie  +
                            "AND Id_L NOT IN (SELECT Id_L FROM reservations " +
                            "Where (Daterevien<"+dateFin+" AND " +dateFin+ " <Datelocation) " +
                            "OR (Daterevien< "+dateDebut+" AND "+dateDebut+"< Datelocation));";
        System.out.println(requeteSQL);
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
             // Préparation de la requête avec le paramètre de localisation recherchée
             Statement st = conn.createStatement()) {
            System.out.println("UI");

            // Exécution de la requête
            ResultSet resultSet = st.executeQuery(requeteSQL);

            // Parcours des résultats et affichage des appartements
            while (resultSet.next()) {
                 Location location = new Location();
                location.setLocationId(resultSet.getInt("Id_L"));
                location.setPhoto1(resultSet.getString("Photo1"));
                location.setPhoto2(resultSet.getString("Photo2"));
                location.setModele(resultSet.getString("Modele"));
                location.setCategorie(resultSet.getString("Categorie"));
                location.setNomvoiture(resultSet.getString("Nom voiture"));
                location.setNote(resultSet.getInt("Note"));
                location.setPrix(resultSet.getInt("Prix"));
                location.setCapacite(resultSet.getInt("Capacite"));
                location.setElectrique(resultSet.getBoolean("Electrique"));
                location.setAutomatique(resultSet.getBoolean("Automatique"));
                maliste.add(location);
                System.out.println(location.getNomvoiture());
            }
            // Fermeture des ressources
            resultSet.close();
        } catch (SQLException e) {
//                System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
        return maliste;
    }

    @Override
    public void ajoutAvis(String avis,int note,int Id_CR,int Id_L) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase = "root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les voiture
        String requeteSQL = "insert into avis(Id_CR, Id_L, Note,Commentaire) " +
                            "values('" + Id_CR + "', '" + Id_L + "', '" + note + "', "+ avis + "');";
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
             // Préparation de la requête avec le paramètre de localisation recherchée
             Statement st = conn.createStatement()) {

            // Exécution de la requête
            ResultSet resultSet = st.executeQuery(requeteSQL);
        }
    }

}
