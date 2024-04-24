package modele.resultats;

import modele.location.Location;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface ResultatsDao {
    public ArrayList<Location> resultats(String localisation, Date dateA, Date dateR, int capacite) throws SQLException, ClassNotFoundException;

    public ArrayList<String> avis(Location x) throws SQLException, ClassNotFoundException;
    public ArrayList<Location> filtrage(Resultats resultats, float Prix, String categorie) throws SQLException, ClassNotFoundException;

    public void ajoutAvis(String avis, int note, int Id_CR, int Id_L) throws SQLException, ClassNotFoundException;
}
