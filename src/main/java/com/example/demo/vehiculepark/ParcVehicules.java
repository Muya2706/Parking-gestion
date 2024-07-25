package com.example.demo.vehiculepark;

import com.example.demo.vehiculepark.classes.Vehicule;

import java.sql.*;
import java.util.*;

public class ParcVehicules {
    private Map<String, Vehicule> vehicules = new HashMap<>();

    public void ajouterVehicule(Vehicule vehicule) throws SQLException {
        try (Connection conn = com.vehiculepark.database.DBConnection.getConnection()) {
            String sql = "INSERT INTO vehicules (id, nom, type, attributs) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, vehicule.getId());
                stmt.setString(2, vehicule.getNom());
                stmt.setString(3, vehicule.getType());
                stmt.setString(4, vehicule.getAttributs());
                stmt.executeUpdate();
            }
        }
        vehicules.put(vehicule.getId(), vehicule);
    }

    public void supprimerVehicule(String id) throws SQLException {
        try (Connection conn = com.vehiculepark.database.DBConnection.getConnection()) {
            String sql = "DELETE FROM vehicules WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                stmt.executeUpdate();
            }
        }
        vehicules.remove(id);
    }

    public void modifierVehicule(String id, String nouveauNom) throws SQLException {
        try (Connection conn = com.vehiculepark.database. DBConnection.getConnection()) {
            String sql = "UPDATE vehicules SET nom = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nouveauNom);
                stmt.setString(2, id);
                stmt.executeUpdate();
            }
        }
        Vehicule vehicule = vehicules.get(id);
        if (vehicule != null) {
            vehicule.setNom(nouveauNom);
        }
    }

    public Vehicule rechercherVehiculeParNom(String nom) throws SQLException {
        try (Connection conn =com.vehiculepark.database. DBConnection.getConnection()) {
            String sql = "SELECT * FROM vehicules WHERE nom = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nom);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Vehicule(rs.getString("id"), rs.getString("nom"), rs.getString("type"), rs.getString("attributs"));
                    }
                }
            }
        }
        return null;
    }

    public void listerVehiculesParLettre(char lettre) throws SQLException {
        try (Connection conn =com.vehiculepark.database. DBConnection.getConnection()) {
            String sql = "SELECT * FROM vehicules WHERE nom LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, lettre + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(new Vehicule(rs.getString("id"), rs.getString("nom"), rs.getString("type"), rs.getString("attributs")));
                    }
                }
            }
        }
    }

    public int nombreDeVehicules() throws SQLException {
        try (Connection conn =com.vehiculepark.database. DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM vehicules";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public void afficherTousLesVehicules() throws SQLException {
        try (Connection conn = com.vehiculepark.database.DBConnection.getConnection()) {
            String sql = "SELECT * FROM vehicules";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    System.out.println(new Vehicule(rs.getString("id"), rs.getString("nom"), rs.getString("type"), rs.getString("attributs")));
                }
            }
        }
    }

    public Vehicule rechercherVehiculeParId(String id) throws SQLException {
        try (Connection conn =com.vehiculepark.database. DBConnection.getConnection()) {
            String sql = "SELECT * FROM vehicules WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Vehicule(rs.getString("id"), rs.getString("nom"), rs.getString("type"), rs.getString("attributs"));
                    }
                }
            }
        }
        return null;
    }
}
