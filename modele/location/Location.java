package modele.location;
public class Location {
    private int locationId;
    private static String mail;
    private static String photo1;
    private static String photo2;
    private  static String modele;
    private static String categorie;
    private static String nomvoiture;
    private float note;
    private static float prix;
    private static int capacite;
    private int nbPorte;
    private boolean electrique;
    private boolean automatique;

    //Setters
    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
    public void setModele(String modele) {
        this.modele = modele;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public void setNomvoiture(String nomvoiture) {
        this.nomvoiture = nomvoiture;
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
    public void setNbPorte(int nbPorte) {
        this.nbPorte = nbPorte;
    }
    public void setElectrique(boolean electrique) {
        this.electrique = electrique;
    }
    public void setAutomatique(boolean automatique) {
        this.automatique = automatique;
    }

    //Getters
    public static String getMail() {
        return mail;
    }
    public int getLocationId() {
        return locationId;
    }
    public static String getPhoto1() {
        return photo1;
    }
    public static String getPhoto2() {
        return photo2;
    }
    public static String getModele() {
        return modele;
    }
    public static String getCategorie() {
        return categorie;
    }
    public static String getNomvoiture() {
        return nomvoiture;
    }
    public float getNote() {
        return note;
    }
    public static float getPrix() {
        return prix;
    }
    public static int getCapacite() {
        return capacite;
    }
    public int getNbPorte() {  return nbPorte;}


    public boolean isElectrique() {
        return electrique;
    }
    public boolean isAutomatique() {
        return automatique;
    }
}
