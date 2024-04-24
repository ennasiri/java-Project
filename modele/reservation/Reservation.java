package modele.reservation;
import java.util.Date;

public class Reservation {
    private int reservId;
    private String mailClient;
    private String nomClient;
    private Date datelocation;
    private Date daterevien;
    private int locaId;
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
    public void setDatelocation(Date datelocation) {
        this.datelocation = datelocation;
    }
    public void setDaterevien(Date daterevien) {
        this.daterevien = daterevien;
    }
    public void setLocaId(int locaId) {
        this.locaId = locaId;
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
    public Date getDatelocation() {
        return datelocation;
    }
    public Date getDaterevien() {
        return daterevien;
    }
    public int getLocaId() {
        return locaId;
    }
    public int getClientId() {
        return clientId;
    }
}
