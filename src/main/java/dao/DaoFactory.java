package dao;

import beans.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DaoFactory {
    private final String url;
    private final String username;
    private final String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private static class DaoFactorySingletonHolder{
        private final static DaoFactory instance = createConnection();
        private static DaoFactory createConnection() {
            try {
                Properties p = new Properties();
                p.load(Thread.currentThread().getContextClassLoader().
                        getResourceAsStream("dao/confBDD.properties"));
                // chargement du driver
                Class.forName(p.getProperty("driver"));
                checkAdminExistance(p.getProperty("url"), p.getProperty("user"), p.getProperty("pwd"));
                return new DaoFactory(p.getProperty("url"), p.getProperty("user"), p.getProperty("pwd"));
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
            }
            //Parametres par defauts
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            checkAdminExistance("jdbc:mysql://localhost:3306/covid19", "root", "");
            return new DaoFactory("jdbc:mysql://localhost:3306/covid19", "root", "");
        }

        private static void checkAdminExistance(String url, String username, String password){
            Utilisateur utilisateur = null;
            Connection connexion = null;
            PreparedStatement preparedStatement = null;

            try {
                connexion = DriverManager.getConnection(url, username, password);
                preparedStatement = connexion.prepareStatement("SELECT * FROM Utilisateur WHERE login=?;");
                preparedStatement.setString(1, "admin");

                ResultSet resultat = preparedStatement.executeQuery();

                if(resultat.next()){
                    int id = resultat.getInt("id");
                    String loginUtilisateur = resultat.getString("login");
                    String passwordUtilisateur = "";
                    String nom = resultat.getString("nom");
                    String prenom = resultat.getString("prenom");
                    Date date_naissance = resultat.getDate("date_naissance");
                    String email = resultat.getString("email");
                    boolean administrateur = resultat.getBoolean("administrateur");

                    utilisateur = new Utilisateur(id, loginUtilisateur, passwordUtilisateur, nom, prenom, date_naissance, email, administrateur);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Si l'admin n'existe pas on le crée une fois
            if(utilisateur==null){
                try {
                    preparedStatement = connexion.prepareStatement("INSERT INTO Utilisateur(login, `password`, nom, prenom, date_naissance, email, administrateur) VALUES(?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, "admin");
                    preparedStatement.setString(2, BCrypt.hashpw("mdpAdmin", BCrypt.gensalt(12)));
                    preparedStatement.setString(3, "Turing");
                    preparedStatement.setString(4, "Alan");
                    preparedStatement.setDate(5, Date.valueOf("1912-06-23"));
                    preparedStatement.setString(6, "");
                    preparedStatement.setBoolean(7, true);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Echec insertion d'utilisateur, pas de ligne ajoutée");
                    }

                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                        }
                        else {
                            throw new SQLException("Echec insertion d'utilisateur, pas d'id obetenu");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DaoFactory getInstance()
    {
        return DaoFactorySingletonHolder.instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération des DAO
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDao(this);
    }

    public NotificationDao getNotificationDao() { return new NotificationDao(this); }

    public LieuDao getLieuDao() {
        return new LieuDao(this);
    }

    public ActiviteDao getActiviteDao(){
        return new ActiviteDao(this);
    }
}
