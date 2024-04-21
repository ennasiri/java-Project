package modele.reservation;
import java.util.Date;

public class Reservation {
    private int reservId;
    private String mailClient;
    private String nomClient;
    private Date dateDebut;
    private Date dateFin;
    private int hebergId;
    private int clientId;

    //Setters
    public void setReservId(int reservId) {
        this.reservId = reservId;
    }
    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setHebergId(int hebergId) {
        this.hebergId = hebergId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    //Getters
    public int getReservId() {
        return reservId;
    }
    public String getMailClient() {
        return mailClient;
    }
    public String getNomClient() {
        return nomClient;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public int getHebergId() {
        return hebergId;
    }
    public int getClientId() {
        return clientId;
    }
}
