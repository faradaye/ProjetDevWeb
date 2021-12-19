package dao;

import beans.Notification;
import beans.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {
    private final DaoFactory daoFactory;

    public NotificationDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouter(Notification notification){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Notifications(id_utilisateur, type_notif, contenu) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(notification.getId_utilisateur()));
            preparedStatement.setString(2, String.valueOf(notification.getType_notif()));
            preparedStatement.setString(3, notification.getContenu());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion de notification, pas de ligne ajoutée");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    notification.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Echec insertion de notification, pas d'id obtenu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void supprimer(Notification notification) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM NOTIFICATIONS WHERE id=?;");
            preparedStatement.setString(1, String.valueOf(notification.getId()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Notification> getAll() {
        List<Notification> notifications = new ArrayList<Notification>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Notifications;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                int id_utilisateur = resultat.getInt("id_utilisateur");
                int type_notif = resultat.getInt("type_notif");
                String contenu = resultat.getString("contenu");

                Notification notification = new Notification(id, id_utilisateur, type_notif, contenu);

                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public List<Notification> getAllForUser(Utilisateur utilisateur) {
        List<Notification> notifications = new ArrayList<Notification>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Notifications WHERE id_utilisateur="+utilisateur.getId()+";");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                int id_utilisateur = resultat.getInt("id_utilisateur");
                int type_notif = resultat.getInt("type_notif");
                String contenu = resultat.getString("contenu");

                Notification notification = new Notification(id, id_utilisateur, type_notif, contenu);

                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
