package dao;

import beans.Lieu;
import beans.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDao {
    private final DaoFactory daoFactory;

    public LieuDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouter(Lieu lieu){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Lieu(nom, adresse, latitude, longitude) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lieu.getNom());
            preparedStatement.setString(2, lieu.getAdresse());
            preparedStatement.setDouble(3, lieu.getLatitude());
            preparedStatement.setDouble(4, lieu.getLongitude());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion de lieu, pas de ligne ajoutée");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lieu.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Echec insertion de lieu, pas d'id obetenu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifier(Lieu lieu) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE Lieu " +
                    " SET nom = ?, adresse = ?, latitude = ?, longitude = ? " +
                    " WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lieu.getNom());
            preparedStatement.setString(2, lieu.getAdresse());
            preparedStatement.setDouble(3, lieu.getLatitude());
            preparedStatement.setDouble(4, lieu.getLongitude());
            preparedStatement.setInt(5, lieu.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec modification de lieu, pas de ligne modifié");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(Lieu lieu) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM Lieu WHERE id=?;");
            preparedStatement.setString(1, String.valueOf(lieu.getId()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Lieu> getAll() {
        List<Lieu> lieux = new ArrayList<Lieu>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Lieu;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String adresse = resultat.getString("adresse");
                double latitude = resultat.getDouble("latitude");
                double longitude = resultat.getDouble("longitude");

                Lieu lieu = new Lieu(id, nom, adresse, latitude, longitude);

                lieux.add(lieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lieux;
    }

    public boolean nomLieuExiste(String nom) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Lieu WHERE nom=?;",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, nom);

            ResultSet resultat = preparedStatement.executeQuery();

            if (!resultat.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Lieu getLieu(int idLieu) {
        Lieu lieu = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Lieu WHERE id=?;");
            preparedStatement.setInt(1, idLieu);

            ResultSet resultat = preparedStatement.executeQuery();

            if(resultat.next()){
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String adresse = resultat.getString("adresse");
                double latitude = resultat.getDouble("latitude");
                double longitude = resultat.getDouble("longitude");

                lieu = new Lieu(id, nom, adresse, latitude, longitude);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lieu;
    }
}
