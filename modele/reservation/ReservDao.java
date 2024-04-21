package modele.reservation;

import modele.client.Client;
import modele.hebergement.Hebergement;

public interface ReservDao {
    public boolean addReservation(Reservation reservation, Client client, Hebergement hebergement) throws ClassNotFoundException;
    public boolean searchInfo(Reservation reservation, Hebergement hebergement, Client client) throws ClassNotFoundException;
    public void sendMail(Reservation reservation, Client client, Hebergement hebergement);
}