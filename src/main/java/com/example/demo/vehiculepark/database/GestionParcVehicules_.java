package com.example.demo.vehiculepark.database;

import com.example.demo.vehiculepark.classes.ParcVehicules;
import com.example.demo.vehiculepark.classes.Vehicule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class GestionParcVehiculesGUI extends JFrame {
    private ParcVehicules parc;

    public GestionParcVehiculesGUI() {
        parc = new ParcVehicules();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gestion du Parc de Véhicules");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        JButton btnAdd = new JButton("Ajouter un véhicule");
        JButton btnDelete = new JButton("Supprimer un véhicule");
        JButton btnEdit = new JButton("Modifier un véhicule");
        JButton btnSearchByName = new JButton("Rechercher un véhicule par nom");
        JButton btnListByLetter = new JButton("Lister les véhicules par lettre");
        JButton btnCount = new JButton("Afficher le nombre de véhicules");
        JButton btnListAll = new JButton("Afficher tous les véhicules");
        JButton btnSearchById = new JButton("Rechercher un véhicule par ID");
        JButton btnExit = new JButton("Quitter");

        panel.add(btnAdd);
        panel.add(btnDelete);
        panel.add(btnEdit);
        panel.add(btnSearchByName);
        panel.add(btnListByLetter);
        panel.add(btnCount);
        panel.add(btnListAll);
        panel.add(btnSearchById);
        panel.add(btnExit);

        add(panel, BorderLayout.CENTER);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterVehicule();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerVehicule();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierVehicule();
            }
        });

        btnSearchByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherVehiculeParNom();
            }
        });

        btnListByLetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listerVehiculesParLettre();
            }
        });

        btnCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherNombreDeVehicules();
            }
        });

        btnListAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherTousLesVehicules();
            }
        });

        btnSearchById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherVehiculeParId();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void ajouterVehicule() {
        JTextField idField = new JTextField(5);
        JTextField nomField = new JTextField(5);
        JTextField typeField = new JTextField(5);
        JTextField attributsField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID:"));
        myPanel.add(idField);
        myPanel.add(Box.createHorizontalStrut(15)); // espace entre les champs
        myPanel.add(new JLabel("Nom:"));
        myPanel.add(nomField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Type:"));
        myPanel.add(typeField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Attributs:"));
        myPanel.add(attributsField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Veuillez entrer les détails du véhicule", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                parc.ajouterVehicule(new Vehicule(idField.getText(), nomField.getText(), typeField.getText(), attributsField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void supprimerVehicule() {
        String id = JOptionPane.showInputDialog("Entrez l'ID du véhicule à supprimer:");
        try {
            parc.supprimerVehicule(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifierVehicule() {
        JTextField idField = new JTextField(5);
        JTextField nomField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID:"));
        myPanel.add(idField);
        myPanel.add(Box.createHorizontalStrut(15)); // espace entre les champs
        myPanel.add(new JLabel("Nouveau nom:"));
        myPanel.add(nomField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Veuillez entrer les nouveaux détails du véhicule", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                parc.modifierVehicule(idField.getText(), nomField.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void rechercherVehiculeParNom() {
        String nom = JOptionPane.showInputDialog("Entrez le nom du véhicule à rechercher:");
        try {
            Vehicule vehicule = parc.rechercherVehiculeParNom(nom);
            if (vehicule != null) {
                JOptionPane.showMessageDialog(null, vehicule.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Véhicule non trouvé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listerVehiculesParLettre() {
        String lettre = JOptionPane.showInputDialog("Entrez la lettre alphabétique:");
        try {
            parc.listerVehiculesParLettre(lettre.charAt(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherNombreDeVehicules() {
        try {
            int count = parc.nombreDeVehicules();
            JOptionPane.showMessageDialog(null, "Nombre de véhicules en stock: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherTousLesVehicules() {
        try {
            parc.afficherTousLesVehicules();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rechercherVehiculeParId() {
        String id = JOptionPane.showInputDialog("Entrez l'ID du véhicule à rechercher:");
        try {
            Vehicule vehicule = parc.rechercherVehiculeParId(id);
            if (vehicule != null) {
                JOptionPane.showMessageDialog(null, vehicule.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Véhicule non trouvé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionParcVehiculesGUI().setVisible(true);
            }
        });
    }
}
