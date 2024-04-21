package modele.proprio;

import modele.proprio.Proprio;
import modele.proprio.ProprioDao;

import java.sql.*;

/**
 * The type Proprio dao.
 */
public class ProprioDaoImpl implements ProprioDao {
    //Ajouter un priopriétaire d'un hébergement à la base de données
    public boolean addPropio(Proprio proprio) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "travece";
        String loginDatabase = "root";
        String passwordDatabase="Abdelhadi19@";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        String sql = "insert into compteh(Name, Mail, password, age, num_tel) values('" + proprio.getName() + "', '" + proprio.getMail()
                + "', '" + proprio.getPassword() + "', "+ proprio.getAge() + ", '" + proprio.getNumTel() + "');";

        try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
            Statement statement = conn.createStatement();){
            int count = statement.executeUpdate(sql);
            System.out.println("number of rows affected by this query= " + count);
            if(count==0)
                return false;
            else return true;
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    //Chercher un priopriétaire d'un hébergement dans la base de données
    public boolean searchPropio(Proprio proprio) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "travece";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        String sql = "select * from compteh where Mail = '" + proprio.getMail() + "' and password = '" + proprio.getPassword() + "';";

        try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
            Statement statement = conn.createStatement();){
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
            while(resultSet.next())
                count++;
            System.out.println("number of rows affected by this query= " + count);
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
