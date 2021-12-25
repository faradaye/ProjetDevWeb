package dao;

import beans.Lieu;
import beans.Notification;
import beans.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {
    private final DaoFactory daoFactory;
    private UtilisateurDao utilisateurDao;

    public NotificationDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    public void ajouter(Notification notification){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Notifications(id_utilisateur, id_source, type_notif, contenu, lue) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(notification.getId_utilisateur()));
            preparedStatement.setString(2, String.valueOf(notification.getId_source()));
            preparedStatement.setString(3, String.valueOf(notification.getType_notif()));
            preparedStatement.setString(4, notification.getContenu());
            preparedStatement.setBoolean(5, notification.isLue());

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

    public void modifier(Notification notification) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE Notifications " +
                    " SET id_utilisateur = ?, id_source = ?, type_notif = ?, contenu = ?, lue = ? " +
                    " WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, notification.getId_utilisateur());
            preparedStatement.setInt(2, notification.getId_source());
            preparedStatement.setInt(3, notification.getType_notif());
            preparedStatement.setString(4, notification.getContenu());
            preparedStatement.setBoolean(5, notification.isLue());
            preparedStatement.setInt(6, notification.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec modification de notif, pas de ligne modifié");
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

    public Notification getNotif(int idNotif) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Notification notification = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Notifications WHERE id="+idNotif+";");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                int id_utilisateur = resultat.getInt("id_utilisateur");
                int id_source = resultat.getInt("id_source");
                int type_notif = resultat.getInt("type_notif");
                String contenu = resultat.getString("contenu");
                boolean lue = resultat.getBoolean("lue");

                notification = new Notification(id, id_utilisateur, id_source, type_notif, contenu, null, lue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notification;
    }

    public List<Notification> getAll() {
        List<Notification> notifications = new ArrayList<>();
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
                int id_source = resultat.getInt("id_source");
                int type_notif = resultat.getInt("type_notif");
                String contenu = resultat.getString("contenu");
                boolean lue = resultat.getBoolean("lue");

                Utilisateur source = utilisateurDao.getUtilisateur(id_source);

                String prenom_nom_source = "";
                if(source != null){
                    prenom_nom_source = source.getPrenom() + " " + source.getNom();
                }

                Notification notification = new Notification(id, id_utilisateur, id_source, type_notif, contenu, prenom_nom_source, lue);

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
                int id_source = resultat.getInt("id_source");
                int type_notif = resultat.getInt("type_notif");
                String contenu = resultat.getString("contenu");
                boolean lue = resultat.getBoolean("lue");
                Utilisateur source = utilisateurDao.getUtilisateur(id_source);

                String prenom_nom_source = "";
                if(source != null){
                    prenom_nom_source = source.getPrenom() + " " + source.getNom();
                }

                Notification notification = new Notification(id, id_utilisateur, id_source, type_notif, contenu, prenom_nom_source, lue);

                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
