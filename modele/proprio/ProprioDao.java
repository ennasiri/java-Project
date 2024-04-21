package modele.proprio;

import modele.proprio.Proprio;

public interface ProprioDao {
    //Ajouter un priopriétaire d'un hébergement à la base de données
    public boolean addPropio(Proprio proprio) throws ClassNotFoundException;

    //Chercher un priopriétaire d'un hébergement dans la base de données
    public boolean searchPropio(Proprio proprio) throws ClassNotFoundException;
}
