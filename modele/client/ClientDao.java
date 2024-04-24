/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.client;

import modele.client.Client;

/**
 *
 * @author maithili
 */
public interface ClientDao {

  //Ajouter un client régulier à la base de donnée
  public boolean addClient(Client client) throws ClassNotFoundException;

  //Chercher un client dans la base de données
  public boolean searchClient(Client client) throws ClassNotFoundException;

}
