package vue;

import modele.hebergement.HebergDao;
import modele.hebergement.HebergDaoImpl;
import modele.proprio.Proprio;
import modele.hebergement.Hebergement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * The type Heberg register.
 */
public class HebergRegister  extends javax.swing.JFrame{
    private JPanel panneau;
    private JTextField photo1;
    private File p1;
    private JTextField photo2;
    private File p2;
    private JTextField titre;
    private JComboBox<String> categorie;
    private JTextField lieu;
    private JTextField prix;
    private JTextField capacite;
    private JTextField nombre;
    private JCheckBox piscine;
    private JCheckBox terrasse;
    private GridBagConstraints constraints;

    /**
     * Instantiates a new Heberg register.
     *
     * @param proprio the proprio
     */
    public HebergRegister(Proprio proprio) {
        setTitle("ECLOCAR.fr");
        setSize(1000, 500);
        //pour fermer proprement la fenêtre en vliquant sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inscription(proprio);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void inscription(Proprio proprio){
        panneau = new JPanel(new GridBagLayout());
        photo1 = new JTextField(20);
        photo2 = new JTextField(20);
        titre = new JTextField(20);
        String[] cate = {"Hôtel", "Maisons/Appartements entiers", "Auberge de jeunesse", "Séjour chez l habitant", "Châlet", "Chambres d hôtes"};
        categorie = new JComboBox<>(cate);
        lieu = new JTextField(20);
        prix = new JTextField(20);
        capacite = new JTextField(20);
        nombre = new JTextField(20);
        piscine = new JCheckBox("Piscine");
        terrasse = new JCheckBox("Terrasse");
        JButton parcours1 = new JButton("Parcourir");
        JButton parcours2 = new JButton("Parcourir");
        JButton valid = new JButton("Valider");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        //Ajouts des éléments dans le panneau
        constraints.gridx = 0;
        constraints.gridy = 0;
        panneau.add(new JLabel("Nom de l'établissement "), constraints);

        constraints.gridx = 1;
        panneau.add(titre, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panneau.add(new JLabel("Photo 1 "), constraints);

        constraints.gridx = 1;
        panneau.add(photo1, constraints);

        constraints.gridx = 2;
        parcours1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                par1ActionPerfomed(actionEvent);
            }
        });
        panneau.add(parcours1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panneau.add(new JLabel("Photo 2 "), constraints);

        constraints.gridx = 1;
        panneau.add(photo2, constraints);

        constraints.gridx = 2;
        parcours2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                par2ActionPerfomed(actionEvent);
            }
        });
        panneau.add(parcours2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panneau.add(new JLabel("Adresse "), constraints);

        constraints.gridx = 1;
        panneau.add(lieu, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panneau.add(new JLabel("Type d'établissement "), constraints);

        constraints.gridx = 1;
        panneau.add(categorie, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panneau.add(new JLabel("Capacité de la chambre "), constraints);

        constraints.gridx = 1;
        panneau.add(capacite, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panneau.add(new JLabel("Nombre de chambres "), constraints);

        constraints.gridx = 1;
        panneau.add(nombre, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panneau.add(new JLabel("Prix/personnes "), constraints);

        constraints.gridx = 1;
        panneau.add(prix, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panneau.add(piscine, constraints);

        constraints.gridx = 1;
        panneau.add(terrasse, constraints);

        constraints.gridx = 2;
        constraints.gridy = 9;
        valid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                validActionPerformed(actionEvent, proprio);
            }
        });
        panneau.add(valid, constraints);
        //Ajout de bordures au panneau
        panneau.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Ajout d'établissement"));
        //Ajout du panneau sur la fenetre
        this.getContentPane().add(panneau);
        pack();
        //Centrer la fenetre sur l'ecran
        setLocationRelativeTo(null);
        // Affichage de la fenêtre
        setVisible(true);
    }
    // </editor-fold>//GEN-END:initComponents
    private void par1ActionPerfomed(ActionEvent actionEvent){
        JFileChooser fichier = new JFileChooser();
        fichier.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fichier.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            p1 = fichier.getSelectedFile();
            photo1.setText(p1.getPath());
        }
    }
    private void par2ActionPerfomed(ActionEvent actionEvent){
        JFileChooser fichier = new JFileChooser();
        fichier.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fichier.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            p2 = fichier.getSelectedFile();
//            selectedFile.renameTo(new File("images/" + selectedFile.getName()));
//            p2 = new File("images/" + selectedFile.getName());
            photo2.setText(p2.getPath());
        }
    }
    private void validActionPerformed(ActionEvent actionEvent, Proprio proprio){
        if(titre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panneau, "Le nom de l'établissement ne peut pas être vide");
        }
        else{
            Hebergement hebergement = new Hebergement();
            hebergement.setMail(proprio.getMail());
            hebergement.setTitre(titre.getText());
            hebergement.setPhoto1("images/" + hebergement.getTitre() + "_photo1_" + p1.getName());
            hebergement.setPhoto2("images/" + hebergement.getTitre()+ "_photo2_" + p2.getName());
            hebergement.setCategorie((String)categorie.getSelectedItem());
            hebergement.setLieu(lieu.getText());
            hebergement.setPrix(Float.parseFloat(prix.getText()));
            hebergement.setCapacite(Integer.parseInt(capacite.getText()));
            hebergement.setNbChambres(Integer.parseInt(nombre.getText()));
            hebergement.setPiscine(piscine.isSelected());
            hebergement.setTerrasse(terrasse.isSelected());
            hebergement.setNote(5);
            HebergDao hebergDao = new HebergDaoImpl();
            try {
                if (hebergDao.addHebergement(hebergement)) {
                    p1.renameTo(new File("images/" + hebergement.getTitre() + "_photo1_" + p1.getName()));
                    p2.renameTo(new File("images/" + hebergement.getTitre() + "_photo2_"+ p2.getName()));
                    panneau.removeAll();
                    remove(panneau);
                    dispose();
                }
                else {
                    constraints.gridx = 2;
                    constraints.gridy = 0;
                    panneau.add(new JLabel("Pas original chakal change de nom"), constraints);
                    pack();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
