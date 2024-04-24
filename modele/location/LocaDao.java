package modele.location;

public interface LocaDao {
    //Pour ajouter un location à la base de données
    public boolean addLocation(Location location) throws ClassNotFoundException;
}
