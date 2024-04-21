package modele.resultats;

import modele.hebergement.Hebergement;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * The type Resultats dao.
 */
public class ResultatsDaoImpl implements ResultatsDao{
    public ArrayList<Hebergement> resultats(String localisation, Date dateA, Date dateR, int capacite) throws SQLException, ClassNotFoundException {
        ArrayList<Hebergement> maliste = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateDebut = dateFormat1.format(dateA);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateFin = dateFormat2.format(dateR);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "travece";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les appartements par localisation
        /*select * from hébergements 
        where Lieu ='Marseille' And Capacite*nb_chambres>=1
        AND Id_H NOT IN (SELECT Id_H FROM reservations Where (DateArrivee<'2023-04-22' and '2023-04-22'<DateDepart) OR (DateArrivee<'2023-04-19' and '2023-04-19'<DateDepart))*/
        String requeteSQL = "SELECT * FROM hébergements WHERE Lieu = '" + localisation + "' " +
                            "AND Capacite*nb_chambres>= " + capacite + " " +
                            "AND Id_H NOT IN (SELECT Id_H FROM reservations " +
                            "Where (DateArrivee<"+dateFin+" AND " +dateFin+ " <DateDepart) " +
                            "OR (DateArrivee< "+dateDebut+" AND "+dateDebut+"< DateDepart));";
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
             // Préparation de la requête avec le paramètre de localisation recherchée
             Statement st = conn.createStatement()) {
            System.out.println("UI");

            // Exécution de la requête
            ResultSet resultSet = st.executeQuery(requeteSQL);

            // Parcours des résultats et affichage des appartements
            while (resultSet.next()) {
                Hebergement hebergement = new Hebergement();
                hebergement.setHebergId(resultSet.getInt("Id_H"));
                hebergement.setPhoto1(resultSet.getString("Photo1"));
                hebergement.setPhoto2(resultSet.getString("Photo2"));
                hebergement.setTitre(resultSet.getString("Titre"));
                hebergement.setCategorie(resultSet.getString("Categorie"));
                hebergement.setLieu(resultSet.getString("Lieu"));
                hebergement.setNote(resultSet.getInt("Note"));
                hebergement.setPrix(resultSet.getInt("Prix"));
                hebergement.setCapacite(resultSet.getInt("Capacite"));
                hebergement.setPiscine(resultSet.getBoolean("Piscine"));
                hebergement.setTerrasse(resultSet.getBoolean("Terrasse"));
                maliste.add(hebergement);
                System.out.println(hebergement.getTitre());
            }
            // Fermeture des ressources
            resultSet.close();
        } catch (SQLException e) {
//                System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
        return maliste;
    }


    public ArrayList<String> avis(Hebergement x) throws SQLException, ClassNotFoundException {
        ArrayList<String> listeAvis = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "travece";
        String loginDatabase = "root";
        String passwordDatabase = "root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les appartements par localisation
        String requeteSQL = "SELECT * FROM avis WHERE Id_H = '" + x.getHebergId() + "';";
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
    public ArrayList<Hebergement> filtrage(Resultats resultats,float prix, String categorie) throws SQLException, ClassNotFoundException {
        ArrayList<Hebergement> maliste = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateDebut = dateFormat1.format(resultats.getDateA());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateFin = dateFormat2.format(resultats.getDateR());
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "travece";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les appartements par localisation
        /*select * from hébergements
        where Lieu ='Marseille' And Capacite*nb_chambres>=1
        AND Id_H NOT IN (SELECT Id_H FROM reservations Where (DateArrivee<'2023-04-22' and '2023-04-22'<DateDepart) OR (DateArrivee<'2023-04-19' and '2023-04-19'<DateDepart))*/
        String requeteSQL = "SELECT * FROM hébergements WHERE Lieu = '" + resultats.getSearchTex() + "' " +
                            "AND Capacite*nb_chambres>= " + resultats.getCapacite() + " " +
                            "AND Prix<= " + prix + " AND Categorie in " + categorie  +
                            "AND Id_H NOT IN (SELECT Id_H FROM reservations " +
                            "Where (DateArrivee<"+dateFin+" AND " +dateFin+ " <DateDepart) " +
                            "OR (DateArrivee< "+dateDebut+" AND "+dateDebut+"< DateDepart));";
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
                Hebergement hebergement = new Hebergement();
                hebergement.setHebergId(resultSet.getInt("Id_H"));
                hebergement.setPhoto1(resultSet.getString("Photo1"));
                hebergement.setPhoto2(resultSet.getString("Photo2"));
                hebergement.setTitre(resultSet.getString("Titre"));
                hebergement.setCategorie(resultSet.getString("Categorie"));
                hebergement.setLieu(resultSet.getString("Lieu"));
                hebergement.setNote(resultSet.getInt("Note"));
                hebergement.setPrix(resultSet.getInt("Prix"));
                hebergement.setCapacite(resultSet.getInt("Capacite"));
                hebergement.setPiscine(resultSet.getBoolean("Piscine"));
                hebergement.setTerrasse(resultSet.getBoolean("Terrasse"));
                maliste.add(hebergement);
                System.out.println(hebergement.getTitre());
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
    public void ajoutAvis(String avis,int note,int Id_CR,int Id_H) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "travece";
        String loginDatabase = "root";
        String passwordDatabase = "root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        // Requête SQL pour récupérer les appartements par localisation
        String requeteSQL = "insert into avis(Id_CR, Id_H, Note,Commentaire) " +
                            "values('" + Id_CR + "', '" + Id_H + "', '" + note + "', "+ avis + "');";
        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
             // Préparation de la requête avec le paramètre de localisation recherchée
             Statement st = conn.createStatement()) {

            // Exécution de la requête
            ResultSet resultSet = st.executeQuery(requeteSQL);
        }
    }

}
