package modele.resultats;

import java.util.Date;

public class Resultats {
    String searchTex;
    Date dateA;
    Date dateR;
    int capacite;

    public void setSearchTex(String searchTex) {
        this.searchTex = searchTex;
    }
    public String getSearchTex() {
        return searchTex;
    }
    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }
    public Date getDateA() {
        return dateA;
    }
    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }
    public Date getDateR() {
        return dateR;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public int getCapacite() {
        return capacite;
    }
}
