/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.client;

/**
 *
 * @author maithili
 */

/* */
public class Client {
    private int clientId;
    private String Name;
    private String Mail;
    private String Password;
    private String NumTel;
    private String CarteBleue;
    private int NbVoyages;
    private int Age;

    //Setters
    public void setClientID(int eID){
        clientId=eID;
    }
    public void setName(String eName){
        Name=eName;
    }
    public void setMail(String mail) {
        Mail = mail;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setNumTel(String numTel) {
        NumTel = numTel;
    }
    public void setCarteBleue(String carteBleue) {
        CarteBleue = carteBleue;
    }
    public void setNbVoyages(int nbVoyages) {
        NbVoyages = nbVoyages;
    }
    public void setAge(int eAge){
        Age=eAge;
    }

    //Getters
    public int getClientId(){
        return clientId;
    }
    public String getName(){
        return Name;
    }
    public String getMail() {
        return Mail;
    }
    public String getPassword() {
        return Password;
    }
    public String getNumTel() {
        return NumTel;
    }
    public String getCarteBleue() {
        return CarteBleue;
    }
    public int getNbVoyages() {
        return NbVoyages;
    }
    public int getAge(){
        return Age;
    }

}
