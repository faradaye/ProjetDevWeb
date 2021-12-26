package dao;

import beans.Activite;
import beans.Notification;
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
            preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur(login, `password`, nom, prenom, date_naissance, email, administrateur) VALUES(?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utilisateur.getLogin());
            preparedStatement.setString(2, BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(12)));
            preparedStatement.setString(3, utilisateur.getNom());
            preparedStatement.setString(4, utilisateur.getPrenom());
            preparedStatement.setDate(5, utilisateur.getDate_naissance());
            preparedStatement.setString(6, utilisateur.getEmail());
            preparedStatement.setBoolean(7, utilisateur.isAdministrateur());

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
                    " SET login = ?, `password` = ?, nom = ?, prenom = ?, date_naissance = ?, email = ?, administrateur = ? " +
                    " WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utilisateur.getLogin());
            preparedStatement.setString(2, BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt(12)));
            preparedStatement.setString(3, utilisateur.getNom());
            preparedStatement.setString(4, utilisateur.getPrenom());
            preparedStatement.setDate(5, utilisateur.getDate_naissance());
            preparedStatement.setString(6, utilisateur.getEmail());
            preparedStatement.setBoolean(7, utilisateur.isAdministrateur());
            preparedStatement.setInt(8, utilisateur.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec modification d'utilisateur, pas de ligne modifié");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierSansModifMotDePasse(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE Utilisateur " +
                    " SET login = ?, nom = ?, prenom = ?, date_naissance = ?, email = ?, administrateur = ? " +
                    " WHERE id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utilisateur.getLogin());
            preparedStatement.setString(2, utilisateur.getNom());
            preparedStatement.setString(3, utilisateur.getPrenom());
            preparedStatement.setDate(4, utilisateur.getDate_naissance());
            preparedStatement.setString(5, utilisateur.getEmail());
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
            preparedStatement = connexion.prepareStatement("DELETE FROM Utilisateur WHERE id=?;");
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
                String email = resultat.getString("email");
                Boolean administrateur = resultat.getBoolean("administrateur");

                Utilisateur utilisateur = new Utilisateur(id, login, password, nom, prenom, date_naissance, email, administrateur);

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
                    String email = resultat.getString("email");
                    Boolean administrateur = resultat.getBoolean("administrateur");

                    utilisateur = new Utilisateur(id, loginUtilisateur, passwordUtilisateur, nom, prenom, date_naissance, email, administrateur);
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

    public boolean emailUtilisateurExiste(String email) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE email=?;",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, email);

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
                String email = resultat.getString("email");
                boolean administrateur = resultat.getBoolean("administrateur");

                utilisateur = new Utilisateur(id, login, password, nom, prenom, date_naissance, email, administrateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public Utilisateur getUtilisateurParEmail(String email) {
        Utilisateur utilisateur = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE email=?;");
            preparedStatement.setString(1, email);

            ResultSet resultat = preparedStatement.executeQuery();

            if(resultat.next()){
                int id = resultat.getInt("id");
                String login = resultat.getString("login");
                String password = "";
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Date date_naissance = resultat.getDate("date_naissance");
                String emailUtilisateur = resultat.getString("email");
                boolean administrateur = resultat.getBoolean("administrateur");

                utilisateur = new Utilisateur(id, login, password, nom, prenom, date_naissance, emailUtilisateur, administrateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public void enregistrementDemandeRecupMotDePasse(Utilisateur utilisateur, String token) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM RecupMotDePasse WHERE id_utilisateur=?;");
            preparedStatement.setInt(1, utilisateur.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec suppression ancienne demandes dans la table de recuperation de mot de passe, pas de ligne supprimées");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO RecupMotDePasse(id_utilisateur, token) VALUES(?, ?);");
            preparedStatement.setInt(1, utilisateur.getId());
            preparedStatement.setString(2, token);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion dans la table de recuperation de mot de passe, pas de ligne ajoutée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getUtilisateurDemandeRecupMotDePasse(String token) {
        Utilisateur utilisateur = null;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur u, RecupMotDePasse r " +
                    " WHERE r.token=? " +
                    " AND u.id = r.id_utilisateur;");
            preparedStatement.setString(1, token);

            ResultSet resultat = preparedStatement.executeQuery();

            if(resultat.next()){
                int id = resultat.getInt("id");
                String login = resultat.getString("login");
                String password = "";
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Date date_naissance = resultat.getDate("date_naissance");
                String emailUtilisateur = resultat.getString("email");
                boolean administrateur = resultat.getBoolean("administrateur");

                utilisateur = new Utilisateur(id, login, password, nom, prenom, date_naissance, emailUtilisateur, administrateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public void recupMotDePasse(Utilisateur utilisateur, String token) {
        this.modifier(utilisateur);
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM RecupMotDePasse WHERE id_utilisateur=? AND token=?;");
            preparedStatement.setInt(1, utilisateur.getId());
            preparedStatement.setString(2, token);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getAmis(Utilisateur utilisateur){
        List<Integer> amis = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM Amis WHERE id_utilisateur1=?;");
            preparedStatement.setInt(1, utilisateur.getId());

            ResultSet resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                int id = resultat.getInt("id_utilisateur2");
                amis.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amis;
    }

    public void ajouterAmi(int id1, int id2){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            //ajout dans un sens
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Amis(id_utilisateur1, id_utilisateur2) VALUES(?, ?);");
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion de lien d'amitie, pas de ligne ajoutée");
            }

            //ajout dans l'autre sens
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO Amis(id_utilisateur1, id_utilisateur2) VALUES(?, ?);");
            preparedStatement.setInt(1, id2);
            preparedStatement.setInt(2, id1);

            affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec insertion de lien d'amitie, pas de ligne ajoutée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerAmi(int id1, int id2){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM Amis WHERE id_utilisateur1=? AND id_utilisateur2=?;");
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);

            //suppression dans un sens
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec suppression de lien d'amitie, pas de ligne supprimée");
            }

            //suppression dans l'autre sens
            preparedStatement = connexion.prepareStatement("DELETE FROM Amis WHERE id_utilisateur1=? AND id_utilisateur2=?;");
            preparedStatement.setInt(1, id2);
            preparedStatement.setInt(2, id1);
            affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Echec suppression de lien d'amitie, pas de ligne supprimée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilisateur> getCasContacts(Utilisateur utilisateur){
        List<Utilisateur> casContacts = new ArrayList<>();
        List<Activite> activitesUtilisateurDerniersJours =
                daoFactory.getActiviteDao().getActivitesUtilisateurDerniersJours(utilisateur);

        List<Activite> activitesMemeLieuMemeMoment = new ArrayList<>();
        for(Activite a : activitesUtilisateurDerniersJours){
            activitesMemeLieuMemeMoment.addAll(daoFactory.getActiviteDao().getActivitesMemeLieuMemeMoment(a));
        }

        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();

            for(Activite a : activitesMemeLieuMemeMoment){
                preparedStatement = connexion.prepareStatement("SELECT id_utilisateur FROM participationactivite WHERE " +
                        "id_activite = ?");

                preparedStatement.setInt(1, a.getId());

                ResultSet resultat = preparedStatement.executeQuery();

                while(resultat.next()){
                    int id = resultat.getInt("id_utilisateur");
                    casContacts.add(getUtilisateur(id));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return casContacts;
    }
}
