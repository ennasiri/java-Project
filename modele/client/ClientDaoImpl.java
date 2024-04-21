/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.client;

import modele.client.Client;
import modele.client.ClientDao;

import java.sql.*;

/**
 * The type Client dao.
 *
 * @author maithili
 */
public class ClientDaoImpl implements ClientDao {
    //Ajouter un client régulier à la base de donnée
   public boolean addClient(Client client) throws ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String nameDatabase = "travece";
    String loginDatabase = "root";
    String passwordDatabase="root";
    String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
    String sql = "insert into clientr(Name, Mail, password, age, num_tel) values('" + client.getName() + "', '" + client.getMail()
            + "', '" + client.getPassword() + "', "+ client.getAge() + ", '" + client.getNumTel() + "');";

    try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
        Statement statement = conn.createStatement();){
      int count = statement.executeUpdate(sql);
      System.out.println("number of rows affected by this query= " + count);
      System.out.println("Record is inserted into Employee table for  Employee : " + client.getName());
      if(count==0)
        return false;
      else return true;
    }
    catch( SQLException e ) {
      e.printStackTrace();
    }
    return false;
  }

    //Chercher un client dans la base de données
    public boolean searchClient(Client client) throws ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String nameDatabase = "travece";
    String loginDatabase = "root";
    String passwordDatabase="root";
    String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
    String sql = "select * from clientr where Mail = '" + client.getMail() + "' and password = '" + client.getPassword() + "';";

    try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
        Statement statement = conn.createStatement();){
      ResultSet resultSet = statement.executeQuery(sql);
      int count = 0;
      while(resultSet.next())
          count++;
      System.out.println("number of rows affected by this query= " + count);
      System.out.println("Record is inserted into Employee table for  Employee : " + client.getName());
      if(count==0)
        return false;
      else return true;
    }
    catch( SQLException e ) {
      e.printStackTrace();
    }
    return false;
  }
}
