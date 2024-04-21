package vue;
import modele.client.Client;
import modele.hebergement.Hebergement;
import modele.reservation.ReservDao;
import modele.reservation.ReservDaoImpl;
import modele.reservation.Reservation;
import modele.resultats.Resultats;
import modele.resultats.ResultatsDao;
import modele.resultats.ResultatsDaoImpl;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static vue.SetBackGroundImage.setBackgroundImage;

/**
 * The type Page description.
 */
public class PageDescription extends JPanel{

    private static Hebergement x;
    private Client C1;
    private JComboBox<String> notation;

    private JTextField commentaire;

    /**
     * Instantiates a new Page description.
     *
     * @param x         the hebergement
     * @param client    the client
     * @param resultats the resultats
     * @param frame     the frame
     * @throws IOException            the io exception
     * @throws FontFormatException    the font format exception
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
    public PageDescription (Hebergement x, Client client, Resultats resultats, JFrame frame) throws IOException, FontFormatException, SQLException, ClassNotFoundException {
        ArrayList<String> listeAvis = new ArrayList<>();
        this.x = x;
        C1 = client;
        JPanel panneau = setBackgroundImage(new File("images/fond_d'ecran.jpg"));

        // Creation des polices
        File file = new File("fonts/agaaler.ttf");
        Font fontTitre = Font.createFont(Font.TRUETYPE_FONT, file);
        fontTitre = fontTitre.deriveFont(Font.PLAIN, 24);
        Font font = new Font("Arial", Font.PLAIN, 16);

        // Placement logo et bouton connexion
        ImageIcon logoIcon = new ImageIcon("images/fond_d'ecran.png");
        Image image = logoIcon.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH); // redimensionner l'image
        ImageIcon resizedLogo = new ImageIcon(image); // créer une nouvelle icône avec l'image redimensionnée
        JLabel logo = new JLabel(resizedLogo);
        logo.setBounds(645,5,150,80);
        JButton loginButton = new JButton("Mon Compte");
        loginButton.setBounds(1100,5,150,80);
        loginButton.setBackground(new Color(159,201,212));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(client == null)
                    new ClientRegister(client, frame, 3, resultats, x);
            }
        });
        JButton hebergButton = new JButton("Ajouter une voiture ");
        hebergButton.setBounds(950,5,150,80);
        hebergButton.setBackground(new Color(159,201,212));
        hebergButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProprioRegister();
            }
        });
        panneau.add(logo);
        panneau.add(loginButton);
        panneau.add(hebergButton);

        // Recuperation photo 1 et photo 2
        String photo1 = x.getPhoto1();
        ImageIcon icon1 = new ImageIcon(photo1);
        Image img1 = icon1.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH); // redimensionner l'image
        ImageIcon resizedIcon1 = new ImageIcon(img1); // créer une nouvelle icône avec l'image redimensionnée
        JLabel label1 = new JLabel(resizedIcon1);

        String photo2 = x.getPhoto2();
        ImageIcon icon2 = new ImageIcon(photo2);
        Image img = icon2.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH); // redimensionner l'image
        ImageIcon resizedIcon2 = new ImageIcon(img); // créer une nouvelle icône avec l'image redimensionnée
        JLabel label2 = new JLabel(resizedIcon2);

        // Recuperation des avis
        ResultatsDao resultat = new ResultatsDaoImpl();
        listeAvis = resultat.avis(x);

        for(int i=0;i<listeAvis.size();i++){
            JLabel avis = new JLabel(listeAvis.get(i));
            avis.setBounds(150,i*100 +600, 200,200);
            avis.setFont(font);
            panneau.add(avis);
        }
        if (listeAvis.size()==0){
            JLabel avis = new JLabel("Pas encore d'avis");
            avis.setBounds(400,600, 200,200);
            avis.setFont(font);
            panneau.add(avis);
        }

        //Bouton reserver
        JButton reserver = new JButton("Réserver");
        reserver.setBounds(850, 440 , 100, 50);
        reserver.setBackground(Color.ORANGE);
        reserver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reservation reservation = new Reservation();
                reservation.setHebergId(x.getHebergId());
                reservation.setDateDebut(resultats.getDateA());
                reservation.setDateFin(resultats.getDateR());
                if(C1 == null) {
                    C1 = new Client();
                    new ClientRegister(C1, x, reservation, frame);
                }
                else {
                    reservation.setClientId(C1.getClientId());
                    reservation.setMailClient(C1.getMail());
                    reservation.setNomClient(C1.getName());
                    System.out.println(reservation.getNomClient() + reservation.getMailClient());
                    try {
                        frame.remove(panneau);
                        new PageReserv(x, C1, reservation, frame);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        panneau.add(reserver);


        //Calcul prix total
        Date dateA=resultats.getDateA();
        Date dateR=resultats.getDateR();
        Period nuits = Period.between(new java.sql.Date(dateA.getTime()).toLocalDate(), new java.sql.Date(dateR.getTime()).toLocalDate());

        int nbNuits= nuits.getDays();

        if (client == null){
            float prix = x.getPrix()*nbNuits* resultats.getCapacite();
            JLabel prixFinal = new JLabel("Prix total : " +prix+ " €");
            prixFinal.setBounds(840,  410 , 300, 200);
            prixFinal.setFont(font.deriveFont(font.BOLD, 16));
            panneau.add(prixFinal);
        }
        else{
            int nbVoyages = client.getNbVoyages();
            float prix = x.getPrix()*nbNuits;
            float prixReduit = prix - (prix*nbVoyages/100);
            JLabel prixFinal = new JLabel("Réduction client régulier"+String.valueOf(prixReduit)+" €");
            prixFinal.setBounds(840,  400 , 300, 200);
            prixFinal.setFont(font);
            panneau.add(prixFinal);
        }

        JLabel recap = new JLabel(nbNuits+" nuits, "+(nbNuits+1)+" jours");
        JLabel nbP = new JLabel(String.valueOf(resultats.getCapacite())+" personnes");
        JLabel titre = new JLabel(x.getTitre());
        JLabel note = new JLabel(String.valueOf(x.getNote())+ " / 5.0");
        JLabel lieu = new JLabel(x.getLieu());
        JLabel categorie = new JLabel("Catégorie - " + x.getCategorie());
        JLabel lesAvis = new JLabel("Avis");
        JLabel equip = new JLabel("Équipements");
        String[] tab = {"1","2","3","4","5"};
        notation = new JComboBox<>(tab);
        commentaire = new JTextField();
        JButton ajoutCom = new JButton("Envoyer");
        ajoutCom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(client == null)
                    new ClientRegister(client, frame, 4, resultats, x);
                else{
                    ResultatsDao essai = new ResultatsDaoImpl();
                    int not = notation.getSelectedIndex();
                    String com=commentaire.getText();
                    try {
                        essai.ajoutAvis(com,not,client.getClientId(),x.getHebergId());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        ajoutCom.setBounds(400,700,100,50);
        notation.setBounds(350,700,50,50);
        commentaire.setBounds(150,700,200,50);
        label1.setBounds(100,  100, 500, 300);
        label2.setBounds(500,  100, 500, 300);
        titre.setBounds(150, 300, 600, 200);
        note.setBounds(850, 300, 300, 200);
        lieu.setBounds(150,  360, 300, 200);
        categorie.setBounds(150, 330,300,200);
        lesAvis.setBounds(150,500,300,200);
        equip.setBounds(150,390,300,200);
        recap.setBounds(850,  430 , 300, 200);
        nbP.setBounds(850,450,300,200);

        titre.setFont(fontTitre);
        note.setFont(fontTitre);
        lieu.setForeground(new Color(159,201,212));
        lieu.setFont(font);
        categorie.setFont(font);
        lesAvis.setFont(fontTitre);
        equip.setFont(font);

        panneau.add(label1);
        panneau.add(label2);
        panneau.add(titre);
        panneau.add(note);
        panneau.add(lieu);
        panneau.add(reserver);
        panneau.add(categorie);
        panneau.add(lesAvis);
        panneau.add(recap);
        panneau.add(nbP);
        panneau.add(equip);
        panneau.add(notation);
        panneau.add(commentaire);
        panneau.add(ajoutCom);

        if (x.isPiscine()){
            JLabel piscine = new JLabel("- Piscine");
            piscine.setBounds(200, 420 , 300, 200);
            panneau.add(piscine);
        }
        if (x.isTerrasse() && x.isPiscine()){
            JLabel terrasse = new JLabel("- Terrasse");
            terrasse.setBounds(200, 440  , 300, 200);
            panneau.add(terrasse);
        }
        else if (x.isTerrasse()){
            JLabel terrasse = new JLabel("- Terrasse");
            terrasse.setBounds(200, 420 , 300, 200);
            panneau.add(terrasse);
        }

        JButton retourButton = new JButton("Retour");
        retourButton.setBounds(100,50,100,50);
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panneau);
                try {
                    new PageResultats(resultats, client, frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panneau.add(retourButton);

        JPanel rectangle = new JPanel();
        rectangle.setBounds(90, 100, 1000, 700); // Position et taille du rectangle
        rectangle.setBackground(Color.white); // Couleur du rectangle
        panneau.add(rectangle);



        // Ajouter le label à la fenêtre
        frame.getContentPane().add(panneau);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.revalidate();
        frame.repaint();
        // Rendre la fenêtre visible à l'écran
        frame.setVisible(true);

    }

    /**
     * Gets heberg.
     *
     * @return the heberg
     */
    public static Hebergement getHeberg() {
        return x;
    }

    /**
     * Sets c 1.
     *
     * @param c1 the c 1
     */
    public void setC1(Client c1) {
        C1 = c1;
    }
}