package dao;

import beans.Activite;
import beans.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiviteDao {
    private final DaoFactory daoFactory;

    public ActiviteDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouter(Activite activite){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Activite(date_activite, nom, heure_debut, heure_fin, id_lieu) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, activite.getDate_activite());
            preparedStatement.setString(2, activite.getNom());
            preparedStatement.setTime(3, activite.getHeure_debut());
            preparedStatement.setTime(4, activite.getHeure_fin());
            preparedStatement.setInt(5, activite.getId_lieu());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion de lieu, pas de ligne ajoutée");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    activite.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Echec insertion d'activite', pas d'id obtenu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifier(Activite activite) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE Activite " +
                    " SET date_activite = ?, nom = ?, heure_debut = ?, heure_fin = ?, id_lieu = ? " +
                    " WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, activite.getDate_activite());
            preparedStatement.setString(2, activite.getNom());
            preparedStatement.setTime(3, activite.getHeure_debut());
            preparedStatement.setTime(4, activite.getHeure_fin());
            preparedStatement.setInt(5, activite.getId_lieu());
            preparedStatement.setInt(6, activite.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec modification d'activite, pas de ligne modifiée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(Activite activite) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM Activite WHERE id=?;");
            preparedStatement.setString(1, String.valueOf(activite.getId()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Activite> getAll() {
        List<Activite> activites = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Activite;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                Date date_activite = resultat.getDate("date_activite");
                Time heure_debut = resultat.getTime("heure_debut");
                Time heure_fin = resultat.getTime("heure_fin");
                int id_lieu = resultat.getInt("id_lieu");

                Activite activite = new Activite(id, nom, date_activite, heure_debut, heure_fin, id_lieu);
                setParticipants(activite);
                activites.add(activite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activites;
    }

    public Activite getActivite(int idActivite) {
        Activite activite = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Activite WHERE id=?;");
            preparedStatement.setInt(1, idActivite);

            ResultSet resultat = preparedStatement.executeQuery();

            if(resultat.next()){
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                Date date_activite = resultat.getDate("date_activite");
                Time heure_debut = resultat.getTime("heure_debut");
                Time heure_fin = resultat.getTime("heure_fin");
                int id_lieu = resultat.getInt("id_lieu");

                activite = new Activite(id, nom, date_activite, heure_debut, heure_fin, id_lieu);
                setParticipants(activite);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activite;
    }

    private void setParticipants(Activite activite){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        List<Integer> participants = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM ParticipationActivite WHERE id_activite=?;");
            preparedStatement.setInt(1, activite.getId());

            ResultSet resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                int id = resultat.getInt("id_utilisateur");
                participants.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        activite.setParticipants(participants);
    }

    public void addParticipant(Activite activite, Utilisateur utilisateur){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        List<Integer> participants = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO participationactivite(id_utilisateur, id_activite) VALUES(?, ?);");
            preparedStatement.setInt(1, utilisateur.getId());
            preparedStatement.setInt(2, activite.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion de participation, pas de ligne ajoutée");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}