package vue;

import modele.client.Client;
import modele.hebergement.Hebergement;
import modele.reservation.ReservDao;
import modele.reservation.ReservDaoImpl;
import modele.reservation.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static vue.SetBackGroundImage.setBackgroundImage;

/**
 * The type Page reserv.
 */
public class PageReserv extends JPanel {
    /**
     * Instantiates a new Page reserv.
     *
     * @param hebergement the hebergement
     * @param client      the client
     * @param reservation the reservation
     * @param frame       the frame
     * @throws IOException            the io exception
     * @throws FontFormatException    the font format exception
     * @throws ClassNotFoundException the class not found exception
     */
    public PageReserv(Hebergement hebergement, Client client, Reservation reservation, JFrame frame) throws IOException, FontFormatException, ClassNotFoundException {
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        JPanel panneau = setBackgroundImage(new File("images/fond_d'ecran.jpg"));
        ReservDao reservDao = new ReservDaoImpl();


        // Creation des polices
        File file = new File("fonts/agaaler.ttf");
        Font fontTitre = Font.createFont(Font.TRUETYPE_FONT, file);
        fontTitre = fontTitre.deriveFont(Font.PLAIN, 24);
        Font font = new Font("Arial", Font.PLAIN, 16);

        ImageIcon logoIcon = new ImageIcon("images/LOGOECLOCAR.jpg.png");
        Image image = logoIcon.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH); // redimensionner l'image
        ImageIcon resizedLogo = new ImageIcon(image); // créer une nouvelle icône avec l'image redimensionnée
        JLabel logo = new JLabel(resizedLogo);
        logo.setBounds(645, 5, 150, 80);

        JLabel co = new JLabel("Confirmation de votre reservation !");
        co.setBounds(200, 50, 500, 200); // position et taille du titre
        co.setForeground(new Color(159, 201, 212));
        co.setFont(fontTitre);

        JLabel texte = new JLabel("Nous avons bien reçu votre demande de réservation et nous sommes ravis de vous confirmer que votre réservation a été acceptée.");
        texte.setBounds(150, 230, 1000, 100); // position et taille du titre
        texte.setFont(font);
        JLabel texte2 = new JLabel("Nous vous rappelons que vous pouvez annuler gratuitement 48h avant votre arrivée. Dans le cas contraire vous allez payer la totalité de votre séjour.");
        texte2.setBounds(150, 300, 1000, 100); // position et taille du titre
        texte2.setFont(font);
        JLabel texte3 = new JLabel("Nous vous remercions de votre confiance et espérons que vous passerez un excellent moment lors de votre séjour.");
        texte3.setBounds(150, 330, 1000, 100); // position et taille du titre
        texte3.setFont(font);
        JLabel texte4 = new JLabel("N'hésitez pas à nous contacter si vous avez des questions ou si vous souhaitez apporter des modifications à votre réservation.");
        texte4.setBounds(150, 400, 1000, 100); // position et taille du titre
        texte4.setFont(font);
        JLabel texte5 = new JLabel("Bon voyage !");
        texte5.setBounds(150, 470, 1000, 100); // position et taille du titre
        texte5.setFont(font);
        JLabel sejour = new JLabel("ECLOCAR.fr");
        sejour.setBounds(150, 610, 1000, 100); // position et taille du titre
        sejour.setFont(font);

        if (reservDao.searchInfo(reservation, hebergement, client)) {
            panneau.add(co);
            panneau.add(texte);
            panneau.add(texte2);
            panneau.add(texte3);
            panneau.add(texte4);
            panneau.add(texte5);
            panneau.add(sejour);
        } else {
            co.setText("Probleme avec l'etablissement de votre reservation");
            texte.setText("Nous n'arrivons pas à ajouter votre réservation");
            texte2.setText("Veuillez réessayer ultérieurement");

            panneau.add(co);
            panneau.add(texte);
            panneau.add(texte2);
        }

        JButton homeButton = new JButton("Faire une nouvelle recherche");
        homeButton.setBounds(600, 600, 300, 100);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new PageRecherche();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panneau.add(homeButton);

        JPanel rectangle = new JPanel();
        rectangle.setBounds(100, 200, 1000, 500); // Position et taille du rectangle
        rectangle.setBackground(Color.white); // Couleur du rectangle
        panneau.add(rectangle);

        frame.getContentPane().add(panneau);
        frame.setVisible(true);
    }

}
