package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import modele.client.Client;
import modele.hebergement.Hebergement;
import modele.resultats.Resultats;
import modele.resultats.ResultatsDaoImpl;

import java.sql.SQLException;
import java.util.Date;

import static vue.SetBackGroundImage.setBackgroundImage;

/**
 * The type Page resultats.
 */
public class PageResultats extends JPanel {

    private static String searchText;
    private static Date dateA;
    private static Date dateR;
    private static int capacite;
    private Client C1;
    ArrayList<String> cateArray;
    private JCheckBox appart;
    private JCheckBox auberge;
    private JCheckBox chalet;
    private JCheckBox habitant;
    private JCheckBox hotel;
    private JCheckBox hotes;
    private String categorie;
    private JSlider prixSlider;

    /**
     * Instantiates a new Page resultats.
     *
     * @param resultats the resultats
     * @param client    the client
     * @param frame    the frame
     * @throws IOException         the io exception
     * @throws FontFormatException the font format exception
     */
    public PageResultats(Resultats resultats, Client client, JFrame frame) throws IOException, FontFormatException {
        this.searchText= resultats.getSearchTex();
        this.dateA = resultats.getDateA();
        this.dateR = resultats.getDateR();
        this.capacite = resultats.getCapacite();
        ArrayList<Hebergement> maliste;
        cateArray= new ArrayList<>();

        // Creation des polices
        File file = new File("fonts/agaaler.ttf");
        Font fontTitre = Font.createFont(Font.TRUETYPE_FONT, file);
        fontTitre = fontTitre.deriveFont(Font.PLAIN, 24);
        Font font = new Font("Arial", Font.PLAIN, 16);

        C1 = client;
        JPanel panneau = setBackgroundImage(new File("images/fond_d'ecran.jpg"));


        appart = new JCheckBox("Maisons/Appartements entiers");
        auberge= new JCheckBox("Auberge de jeunesse");
        chalet= new JCheckBox("Châlet");
        habitant = new JCheckBox("Séjour chez l habitant");
        hotes = new JCheckBox("Chambres d hôtes");
        hotel=new JCheckBox("Hôtel");

        appart.setBounds(1200,300,200,100);
        auberge.setBounds(1200,350,200,100);
        chalet.setBounds(1200,400,200,100);
        habitant.setBounds(1200,450,200,100);
        hotes.setBounds(1200,500,200,100);
        hotel.setBounds(1200,550,200,100);
        panneau.add(appart);
        panneau.add(auberge);
        panneau.add(hotel);
        panneau.add(hotes);
        panneau.add(chalet);
        panneau.add(habitant);


        prixSlider = new JSlider(0,200,20);
        prixSlider.setBounds(1200,200,200,100);

        //String[] cate = {"Hôtel", "Maisons/Appartements entiers", "Auberge de jeunesse", "Séjour chez l habitant", "Châlet", "Chambres d hôtes"};


        panneau.add(prixSlider);

        JButton filtrer = new JButton("Filtrer");
        filtrer.setBounds(1200,700,200,50);
        filtrer.setBackground(new Color(159,201,212));
        filtrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float newPrix = prixSlider.getValue();
                if (appart.isSelected())cateArray.add("'"+appart.getText()+"'");
                if (auberge.isSelected())cateArray.add("'"+auberge.getText()+"'");
                if (chalet.isSelected())cateArray.add("'"+chalet.getText()+"'");
                if (habitant.isSelected())cateArray.add("'"+habitant.getText()+"'");
                if (hotes.isSelected())cateArray.add("'"+hotes.getText()+"'");
                if(hotel.isSelected())cateArray.add("'"+hotel.getText()+"'");

                categorie=("(");
                for(int i=0; i<cateArray.size()-1;i++){
                    categorie=(categorie+cateArray.get(i)+",");
                }
                categorie=(categorie+cateArray.get(cateArray.size()-1)+")");
                System.out.print(categorie);
                frame.remove(panneau);
                try {
                    new PageFiltrage(resultats, newPrix, categorie, client, frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panneau.add(filtrer);

        // Appel de la methode resultat
        ResultatsDaoImpl essai = new ResultatsDaoImpl();
        try {
            maliste = essai.resultats(searchText,dateA,dateR,capacite);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


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
                    new ClientRegister(client, frame, 2, resultats, null);
            }
        });
        System.out.println("logo check");
        JButton hebergButton = new JButton("Ajouter une voiture");
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

        // Messages resultats de recherche
        if (maliste.size()==0){
            JLabel label = new JLabel("Aucun resultat pour la recherche");
            label.setBounds(100,70,500,100);
            label.setFont(fontTitre);
            label.setForeground(new Color(159,201,212));
            panneau.add(label);
        }
        else {
            JLabel label = new JLabel(searchText + " : " + maliste.size() + " hebergements");
            label.setBounds(100,70,500,100);
            label.setFont(fontTitre);
            label.setForeground(new Color(159,201,212));
            panneau.add(label);
        }

        // affichage des hebergements
        int i=1;
        for (Hebergement x : maliste) {
            //Titre Lieu Note Prix
            JLabel titre = new JLabel(x.getTitre());
            JLabel note = new JLabel(String.valueOf(x.getNote())+ " / 5.0");
            JLabel lieu = new JLabel(x.getLieu());
            JLabel prix = new JLabel("A partir de "+String.valueOf(x.getPrix())+ "€ / nuit");

            //Photo
            String photo = x.getPhoto1();
            ImageIcon icon = new ImageIcon(photo);
            Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH); // redimensionner l'image
            ImageIcon resizedIcon = new ImageIcon(img); // créer une nouvelle icône avec l'image redimensionnée
            JLabel label = new JLabel(resizedIcon);

            //Bouton reserver
            JButton reserver = new JButton("Voir plus");
            reserver.setBounds(900, i * 230 + 30, 100, 50);
            reserver.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.remove(panneau);
                        new PageDescription(x, client, resultats, frame);
                    } catch (IOException | SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            //Setbounds et font
            label.setBounds(100, i * 230 - 80, 300, 200); // position et taille du label
            titre.setBounds(420, i * 230 - 130, 600, 200); // position et taille du titre
            titre.setForeground(new Color(159,201,212));
            titre.setFont(fontTitre);
            JLabel labelPrix = new JLabel("Prix de l'hébergement");
            JLabel labelCate = new JLabel("Catégorie de l'hébergement");
            JLabel prixMin = new JLabel("0 €");
            JLabel prixMax = new JLabel("200 €");

            note.setBounds(900, i * 230 - 130, 300, 200);
            note.setFont(fontTitre);
            lieu.setBounds(420, i * 230 -100 , 300, 200);
            lieu.setFont(font);
            prix.setBounds(890, i * 230 , 300, 200);
            prix.setFont(font);
            labelPrix.setBounds(1200,150,200,100);
            labelPrix.setFont(font.deriveFont(font.BOLD, 16));
            labelCate.setBounds(1200,270,200,100);
            labelCate.setFont(font.deriveFont(font.BOLD, 16));
            prixMin.setBounds(1200,190,200,100);
            prixMax.setBounds(1350,190,200,100);


            //Ajout au panneau
            panneau.add(label);
            panneau.add(titre);
            panneau.add(note);
            panneau.add(lieu);
            panneau.add(reserver);
            panneau.add(prix);
            panneau.add(labelPrix);
            panneau.add(labelCate);
            panneau.add(prixMin);
            panneau.add(prixMax);

            if (x.isPiscine()){
                JLabel piscine = new JLabel("Piscine");
                piscine.setBounds(440, i * 230 -50 , 300, 200);
                piscine.setFont(font);
                panneau.add(piscine);
            }
            if (x.isTerrasse() && x.isPiscine()){
                JLabel terrasse = new JLabel("Terrasse");
                terrasse.setBounds(540, i * 230 -50  , 300, 200);
                terrasse.setFont(font);
                panneau.add(terrasse);
            }
            else if (x.isTerrasse()){
                JLabel terrasse = new JLabel("Terrasse");
                terrasse.setBounds(440, i * 230 -50  , 300, 200);
                terrasse.setFont(font);
                panneau.add(terrasse);
            }
            JPanel rectangle = new JPanel();
            rectangle.setBounds(90, i*230 -85, 1000, 210); // Position et taille du rectangle
            rectangle.setBackground(Color.white); // Couleur du rectangle
            panneau.add(rectangle);

            i++;
        }

        JPanel rectangle2 = new JPanel();
        rectangle2.setBounds(1150,150,400,610); // Position et taille du rectangle
        rectangle2.setBackground(Color.white); // Couleur du rectangle
        panneau.add(rectangle2);

        JButton retourButton = new JButton("Retour");
        retourButton.setBounds(100,50,100,50);
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panneau);
                frame.dispose();
                try {
                    new PageRecherche();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panneau.add(retourButton);

        // Ajouter le label à la fenêtre
        frame.getContentPane().add(panneau);

        // Définir la taille de la fenêtre
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Rendre la fenêtre visible à l'écran
        frame.setVisible(true);
    }

    /**
     * Gets c 1.
     *
     * @return the c 1
     */
    public Client getC1() {
        return C1;
    }

    /**
     * Sets c 1.
     *
     * @param c1 the c 1
     */
    public void setC1(Client c1) {
        C1 = c1;
    }

    /**
     * Gets search text.
     *
     * @return the search text
     */
    public static String getSearchText() {
        return searchText;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
}
