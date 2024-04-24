package modele.reservation;

import modele.client.Client;
import modele.location.Location;

public interface ReservDao {
    public boolean addReservation(Reservation reservation, Client client, Location location) throws ClassNotFoundException;
    public boolean searchInfo(Reservation reservation, Location location, Client client) throws ClassNotFoundException;
    public void sendMail(Reservation reservation, Client client, Location location);
}