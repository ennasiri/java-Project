package modele.hebergement;
public class Hebergement {
    private int hebergId;
    private String mail;
    private String photo1;
    private String photo2;
    private String titre;
    private String categorie;
    private String lieu;
    private float note;
    private float prix;
    private int capacite;
    private int nbChambres;
    private boolean piscine;
    private boolean terrasse;

    //Setters
    public void setHebergId(int hebergId) {
        this.hebergId = hebergId;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public void setNote(float note) {
        this.note = note;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public void setNbChambres(int nbChambres) {
        this.nbChambres = nbChambres;
    }
    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }
    public void setTerrasse(boolean terrasse) {
        this.terrasse = terrasse;
    }

    //Getters
    public String getMail() {
        return mail;
    }
    public int getHebergId() {
        return hebergId;
    }
    public String getPhoto1() {
        return photo1;
    }
    public String getPhoto2() {
        return photo2;
    }
    public String getTitre() {
        return titre;
    }
    public String getCategorie() {
        return categorie;
    }
    public String getLieu() {
        return lieu;
    }
    public float getNote() {
        return note;
    }
    public float getPrix() {
        return prix;
    }
    public int getCapacite() {
        return capacite;
    }
    public int getNbChambres() {
        return nbChambres;
    }
    public boolean isPiscine() {
        return piscine;
    }
    public boolean isTerrasse() {
        return terrasse;
    }
}
