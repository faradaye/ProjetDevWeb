package dao;

import beans.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDao {
    private final DaoFactory daoFactory;

    public UtilisateurDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouter(Utilisateur utilisateur){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur(login, `password`, nom, prenom, date_naissance, administrateur) VALUES(?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utilisateur.getLogin());
            preparedStatement.setString(2, BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(12)));
            preparedStatement.setString(3, utilisateur.getNom());
            preparedStatement.setString(4, utilisateur.getPrenom());
            preparedStatement.setString(5, utilisateur.getDate_naissance().toString());
            preparedStatement.setBoolean(6, utilisateur.isAdministrateur());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion d'utilisateur, pas de ligne ajoutée");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    utilisateur.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Echec insertion d'utilisateur, pas d'id obetenu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifier(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE Utilisateur " +
                    " SET login = ?, `password` = ?, nom = ?, prenom = ?, date_naissance = ?, administrateur = ? " +
                    " WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utilisateur.getLogin());
            preparedStatement.setString(2, BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(12)));
            preparedStatement.setString(3, utilisateur.getNom());
            preparedStatement.setString(4, utilisateur.getPrenom());
            preparedStatement.setString(5, utilisateur.getDate_naissance().toString());
            preparedStatement.setBoolean(6, utilisateur.isAdministrateur());
            preparedStatement.setInt(7, utilisateur.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec modification d'utilisateur, pas de ligne modifié");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM UTILISATEUR WHERE id=?;");
            preparedStatement.setString(1, String.valueOf(utilisateur.getId()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Utilisateur> getAll() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Utilisateur;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String login = resultat.getString("login");
                String password = "";
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Date date_naissance = resultat.getDate("date_naissance");
                Boolean administrateur = resultat.getBoolean("administrateur");

                Utilisateur utilisateur = new Utilisateur(id, login, password, nom, prenom, date_naissance, administrateur);

                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public Utilisateur authUtilisateur(String login, String password) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE login=?;",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, login);

            ResultSet resultat = preparedStatement.executeQuery();
            if (!resultat.isBeforeFirst()){
                return  null;
            }
            else if (resultat.first()) {
                if(BCrypt.checkpw(password, resultat.getString("password"))){
                    int id = resultat.getInt("id");
                    String loginUtilisateur = resultat.getString("login");
                    String passwordUtilisateur = resultat.getString("password");
                    String nom = resultat.getString("nom");
                    String prenom = resultat.getString("prenom");
                    Date date_naissance = resultat.getDate("date_naissance");
                    Boolean administrateur = resultat.getBoolean("administrateur");

                    utilisateur = new Utilisateur(id, loginUtilisateur, passwordUtilisateur, nom, prenom, date_naissance, administrateur);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public boolean loginUtilisateurExiste(String login) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE login=?;",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, login);

            ResultSet resultat = preparedStatement.executeQuery();

            if (!resultat.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Utilisateur getUtilisateur(int idUtilisateur) {
        Utilisateur utilisateur = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE id=?;");
            preparedStatement.setInt(1, idUtilisateur);

            ResultSet resultat = preparedStatement.executeQuery();

            if(resultat.next()){
                int id = resultat.getInt("id");
                String login = resultat.getString("login");
                String password = "";
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Date date_naissance = resultat.getDate("date_naissance");
                boolean administrateur = resultat.getBoolean("administrateur");

                utilisateur = new Utilisateur(id, login, password, nom, prenom, date_naissance, administrateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
}
