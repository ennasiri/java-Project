package vue;
import modele.client.Client;
import modele.resultats.Resultats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static vue.SetBackGroundImage.setBackgroundImage;

/**
 * The type Page recherche.
 */
public class PageRecherche extends JFrame {

    private Client client;
    private JFrame frame;
    private JPanel panneau;

    /**
     * Instantiates a new Page recherche.
     *
     * @throws IOException         the io exception
     * @throws FontFormatException the font format exception
     */
    public PageRecherche() throws IOException, FontFormatException {
        panneau = new JPanel(null);
        frame = new JFrame();
        panneau = setBackgroundImage(new File("images/fond_d'ecran.jpg"));
        frame.setTitle("LOCATION VOITURE");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        File file = new File("fonts/agaaler.ttf");
        Font fontTitre = Font.createFont(Font.TRUETYPE_FONT, file);
        fontTitre = fontTitre.deriveFont(Font.PLAIN, 24);
        Font font = new Font("Arial", Font.PLAIN, 16);

        // Placement logo et bouton connexion
        // Placement logo et bouton connexion
        ImageIcon logoIcon = new ImageIcon("images/LOGOECLOCAR.jpg.png");
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
                    new ClientRegister(client, frame, 1, null, null);
            }
        });
        JButton hebergButton = new JButton("Ajouter une voiture");
        hebergButton.setBounds(950,5,150,80);
        hebergButton.setBackground(new Color(100,201,212));
        hebergButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProprioRegister();
            }
        });
        panneau.add(logo);
        panneau.add(loginButton);
        panneau.add(hebergButton);
        //panneau.add(image);

        //BANNER
        JLabel text = new JLabel("Welcome To location voiture");
        text.setFont(new Font("Welcome To location voiture", Font.ITALIC, 30));
        text.setBackground(new Color(159,201,212));
        text.setBounds(600, 100, 400, 100);
        panneau.add(text);

        //Lieu
        JLabel lieu = new JLabel("Nom de voiture : ");
        lieu.setFont(new Font("Nom de voiture", Font.BOLD, 16));
        lieu.setBounds(200, 260, 250, 50);
        panneau.add(lieu);
        JTextField searchField = new JTextField(20);
        searchField.setBounds(200,300,250,50);

        //Calender
        //date d'arrivée

        JSpinner dateArrive = new JSpinner(new SpinnerDateModel());
        SimpleDateFormat DatA = new SimpleDateFormat("dd/MM/yyyy");
        JSpinner.DateEditor editA = new JSpinner.DateEditor(dateArrive, DatA.toPattern());
        dateArrive.setEditor(editA);
        dateArrive.setBounds(450,300,150,50);
        JLabel DateA = new JLabel("Date de location : ");
        DateA.setFont(new Font("Date de recuperation", Font.BOLD, 16));
        DateA.setBounds(450, 260, 150, 50);
        panneau.add(DateA);
        panneau.add(dateArrive);
        

        //date de départ
        JSpinner dateDepart = new JSpinner(new SpinnerDateModel());
        SimpleDateFormat DatD = new SimpleDateFormat("dd/MM/yyyy");
        JSpinner.DateEditor editD = new JSpinner.DateEditor(dateDepart, DatD.toPattern());
        dateDepart.setEditor(editD);
        dateDepart.setBounds(600,300,150,50);
        panneau.add(dateDepart);

        JLabel DateD = new JLabel("Date de location : ");
        DateD.setFont(new Font("Date de recuperation ", Font.BOLD, 16));
        DateD.setBounds(600, 260, 150, 50);
        panneau.add(DateD);

        // nbr personne
        JSpinner nbrpersonne = new JSpinner();
        nbrpersonne.setBounds(750,300,120,50);
        nbrpersonne.setModel(new SpinnerNumberModel(1, 1, 15, 1));
        JLabel nbrP = new JLabel(" Nombre de Personnes : ");
        nbrP.setFont(new Font("Nombre de Personnes", Font.BOLD, 16));
        nbrP.setBounds(750, 260, 120, 50);
        panneau.add(nbrP);

        // searchButton
        JButton searchButton = new JButton("Rechercher");
        searchButton.setBounds(870,300,200,50);
        searchButton.setBackground(new Color(159,201,212));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Resultats resultats = new Resultats();
                resultats.setSearchTex(searchField.getText());
                resultats.setDateA((Date) dateArrive.getValue());
                resultats.setDateR((Date) dateDepart.getValue());
                resultats.setCapacite((int) nbrpersonne.getValue());
                frame.remove(panneau);
                try {
                    //if(resultats.getDateA().compareTo(resultats.getDateR()) > 0)
                        new PageResultats(resultats, client, getFrame());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Affichage
        panneau.add(lieu);
        panneau.add(searchField);
        panneau.add(dateArrive);
        panneau.add(dateDepart);
        panneau.add(nbrpersonne);
        panneau.add(searchButton);

        frame.getContentPane().add(panneau);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
