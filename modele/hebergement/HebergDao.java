package modele.hebergement;

public interface HebergDao {
    //Pour ajouter un hebergement à la base de données
    public boolean addHebergement(Hebergement hebergement) throws ClassNotFoundException;
}
