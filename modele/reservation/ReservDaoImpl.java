package modele.reservation;

import modele.client.Client;
import modele.location.Location;

import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * The type Reserv dao.
 */
public class ReservDaoImpl implements ReservDao{
    public boolean addReservation(Reservation reservation, Client client, Location location) throws ClassNotFoundException {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateDebut = dateFormat1.format(reservation.getDatelocation());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateFin = dateFormat2.format(reservation.getDaterevien());
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        String sql = "insert into reservations(Mailclient, Nomclient, Datelocation, Daterevien, Id_L, Id_CR) values('"
                + reservation.getMailClient() + "', '" + reservation.getNomClient() + "', '"+ dateDebut + "', '"
                + dateFin + "', " + reservation.getLocaId() + ", " + reservation.getClientId() + ");";

        try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
            Statement statement = conn.createStatement()){
            int count = statement.executeUpdate(sql);
            System.out.println("number of rows affected by this query= " + count);
            if(count==0)
                return false;
            else{
                //sendMail(reservation, client, location);
                return true;
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean searchInfo(Reservation reservation, Location location, Client client) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String nameDatabase = "ecelocarbase";
        String loginDatabase = "root";
        String passwordDatabase="root";
        String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
        String sql = null;
        if(client != null) {
            sql = "select * from clientr where Mail = '" + client.getMail() + "';";
        }
        String sql2 = "select * from vehicules where Nomvoitute = '" + location.getNomvoiture() + "';";

        try(Connection conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
            Statement statement = conn.createStatement();
            Statement statement2 = conn.createStatement()){
            int count = 0;
            if(sql != null) {
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    count++;
                    reservation.setClientId(resultSet.getInt("Id_CR"));
                    reservation.setNomClient(resultSet.getString("Name"));
                    client.setClientID(resultSet.getInt("Id_CR"));
                    client.setMail(resultSet.getString("Mail"));
                    client.setName(resultSet.getString("Name"));
                    client.setPassword(resultSet.getString("password"));
                    client.setAge(resultSet.getInt("Age"));
                    client.setNumTel(resultSet.getString("num_tel"));
                    client.setNblocation(resultSet.getInt("Nb location"));
                }
                if(count==0){
                    reservation.setClientId(0);
                }
            }
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            count = 0;
            while(resultSet2.next()){
                count++;
                reservation.setLocaId(resultSet2.getInt("Id_L"));
                location.setLocationId(resultSet2.getInt("Id_L"));
                location.setCategorie(resultSet2.getString("Categorie"));
                location.setMail(resultSet2.getString("Mailproprio"));
                location.setNomvoiture(resultSet2.getString("Nombre voiture"));
            }
            if(count==0){
                return false;
            }
            else {
                addReservation(reservation, client, location);
                return true;
            }
        }
        catch( SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendMail(Reservation reservation, Client client, Location location){
        SimpleDateFormat debut = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fin = new SimpleDateFormat("dd/MM/yyyy");
        String dateD = debut.format(reservation.getDatelocation());
        String dateF = fin.format(reservation.getDaterevien());
        // Envoi de l'e-mail
        String username = "travece@outlook.com";
        String password = "trav.ECE2023";
        String smtpHost = "smtp.office365.com";
        int smtpPort = 587;

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.smtp.port", String.valueOf(smtpPort));
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reservation.getMailClient()));
            emailMessage.setSubject("Reservation ECELOCAR chez '" + location.getNomvoiture() + "' !");
            emailMessage.setText("Bonjour " + reservation.getNomClient() + " !  \n\nNous avons le plaisir de vous annoncer que " +
                    "votre réservation chez " + location.getNomvoiture() + " a été confirmée !\n\nVous avez réservé du "
                    + dateD + " au " + dateF + "\nNom de voiture : "
                    + location.getModele() + "\n\nBon conduite !");

            Transport.send(emailMessage);

            System.out.println("envoyé !");
        } catch (MessagingException ex) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + ex.getMessage());
        }
    }
}
