package vue;
import modele.proprio.Proprio;
import modele.proprio.ProprioDao;
import modele.proprio.ProprioDaoImpl;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 * The type Proprio register.
 */
public class ProprioRegister extends JFrame {
    private JPanel panneau;
    private JTextField nom;
    private JTextField email = new JTextField(20);
    private JTextField mdp = new JTextField(20);
    private JTextField tel;
    private JLabel ls = new JLabel();
    private JLabel le = new JLabel();
    //Création du spinner pour rentrer l'age
    private JSpinner age;
    private GridBagConstraints constraints;

    /**
     * Instantiates a new Proprio register.
     */
    public ProprioRegister(){
        setTitle("ECLOCAR.fr");
        setSize(1000, 500);
        //pour fermer proprement la fenêtre en vliquant sur la croix
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inscription();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void inscription() {
        panneau = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        if(nom == null)
            nom = new JTextField(20);
        if(tel == null)
            tel = new JTextField(20);
        //Création du spinner pour rentrer l'age
        if(age == null)
            age = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));

        age.setEditor(new JSpinner.DateEditor(age, "MM/YYYY"));
        age.setBounds(100, 100, 45, 30);
        //Création de l'affichage
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        //Ajouts des éléments dans le panneau
        constraints.gridx = 0;
        constraints.gridy = 0;
        panneau.add(new JLabel("Nom Prénom : "), constraints);

        constraints.gridx = 1;
        panneau.add(nom, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panneau.add(new JLabel("E-Mail : "), constraints);

        constraints.gridx = 1;
        panneau.add(email, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panneau.add(new JLabel("Mot de passe : "), constraints);

        constraints.gridx = 1;
        panneau.add(mdp, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panneau.add(new JLabel("Téléphone (ex:+33123456789) : "), constraints);

        constraints.gridx = 1;
        panneau.add(tel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panneau.add(new JLabel("Date de naissance (MM/YYYY) : "), constraints);

        constraints.gridx = 1;
        panneau.add(age, constraints);

        //Création du bouton de validation
        JButton bouton = new JButton("S'Inscrire");
        //Evenementiel du bouton de validation
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boutonActionPerformed(e);
            }
        });
        // Ajout du bouton à la fenêtre
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panneau.add(bouton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        panneau.add(new JLabel("Vous avez déjà un établissement ?"), constraints);
        //Création du bouton de connexion
        JButton conn = new JButton("Connectez-vous");
        conn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connActionPerformed(e);
            }
        });
        //Ajout du bouton de connexion au panneau
        constraints.gridx = 1;
        constraints.gridy = 8;
        panneau.add(conn, constraints);

        //Ajout de bordures au panneau
        panneau.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Inscription"));

        this.getContentPane().add(panneau);
        pack();

        setLocationRelativeTo(null);
        // Affichage de la fenêtre
        setVisible(true);
    }
    private void connexion(){
        //Création du panneau pour les éléments
        panneau = new JPanel(new GridBagLayout());
        //Création de l'affichage
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        //Ajouts des éléments dans le panneau
        panneau.add(new JLabel("E-Mail : "), constraints);

        constraints.gridx = 1;
        panneau.add(email, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panneau.add(new JLabel("Mot de passe : "), constraints);

        constraints.gridx = 1;
        panneau.add(mdp, constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        //Création du bouton de validation
        JButton bouton2 = new JButton("Se Connecter");
        bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bouton2ActionPerformed(e);
            }
        });
        //Ajout du bouton de validation au panneau
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panneau.add(bouton2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        panneau.add(new JLabel("Pas encore d'établissement ?"), constraints);
        //Création du bouton de validation
        JButton insc = new JButton("Inscrivez-vous");
        insc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inscActionPerformed(e);
            }
        });
        //Ajout du bouton d'inscription au panneau
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panneau.add(insc, constraints);
        //Ajout de bordures au panneau
        panneau.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Connexion"));
        //Ajout du panneau sur la fenetre
        this.getContentPane().add(panneau);
        pack();
        //Centrer la fenetre sur l'ecran
        setLocationRelativeTo(null);
        // Affichage de la fenêtre
        setVisible(true);

    }// </editor-fold>//GEN-END:initComponents
    private void connActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connActionPerformed
        constraints = null;
        remove(panneau);
        connexion();
    }//GEN-LAST:event_connActionPerformed
    private void inscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connActionPerformed
        constraints = null;
        remove(panneau);
        inscription();
    }//GEN-LAST:event_connActionPerformed

    private void boutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonActionPerformed
        ls.setText("Compte créé Bienvenue chakal ;)");
        le.setText("Gros naze t'as déjà utilisé cet email");
        Period agee = Period.between(new java.sql.Date(((Date) age.getValue()).getTime()).toLocalDate(), LocalDate.now());
        ProprioDao proprioDao = new ProprioDaoImpl();
        Proprio P1 = new Proprio();
        P1.setName(nom.getText());
        P1.setMail(email.getText());
        P1.setPassword(mdp.getText());
        P1.setNumTel(tel.getText());
        P1.setAge(agee.getYears());
        try {
            constraints.gridx = 1;
            constraints.gridy = 5;
            if (proprioDao.addPropio(P1)) {
                panneau.remove(le);
                panneau.add(ls, constraints);
                new HebergRegister(P1);
                remove(panneau);
                dispose();
            } else {
                panneau.remove(ls);
                panneau.add(le, constraints);
            }
            panneau.revalidate();
            panneau.repaint();
            pack();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_boutonActionPerformed
    private void bouton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bouton2ActionPerformed
        ls.setText("Connexion réussie bien joué chakal ;)");
        le.setText("Gros naze tu t'es trompé de mdp ou id");
        ProprioDao proprioDao = new ProprioDaoImpl();
        Proprio P1 = new Proprio();
        P1.setMail(email.getText());
        P1.setPassword(mdp.getText());
        try {
            constraints.gridx = 1;
            constraints.gridy = 3;
            if (proprioDao.searchPropio(P1)) {
                panneau.remove(le);
                panneau.add(ls, constraints);
                new HebergRegister(P1);
                dispose();
            } else {
                panneau.remove(ls);
                panneau.add(le, constraints);
            }
            panneau.revalidate();
            panneau.repaint();
            pack();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_bouton2ActionPerformed

}
