package modele.resultats;

import modele.hebergement.Hebergement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface ResultatsDao {
    public ArrayList<Hebergement> resultats(String localisation, Date dateA, Date dateR, int capacite) throws SQLException, ClassNotFoundException;

    public ArrayList<String> avis(Hebergement x) throws SQLException, ClassNotFoundException;
    public ArrayList<Hebergement> filtrage(Resultats resultats, float Prix, String categorie) throws SQLException, ClassNotFoundException;

    public void ajoutAvis(String avis, int note, int Id_CR, int Id_H) throws SQLException, ClassNotFoundException;
}
