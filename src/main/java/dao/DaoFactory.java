package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public static DaoFactory getInstance() {
        try {
            Properties p = new Properties();
            p.load(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream("dao/confBDD.properties"));
            // chargement du driver
            Class.forName(p.getProperty("driver"));
            return new DaoFactory(p.getProperty("url"), p.getProperty("user"), p.getProperty("pwd"));
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        //Parametres par defauts
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new DaoFactory("jdbc:mysql://localhost:3306/covid19", "root", "");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération des DAO
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDao(this);
    }

    public NotificationDao getNotificationDao() { return new NotificationDao(this); }

}
