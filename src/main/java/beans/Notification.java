package beans;

import dao.DaoFactory;

import java.sql.Blob;

public class Notification {
    private int id;
    private int id_utilisateur;
    private int id_source;
    private int type_notif;
    private String contenu;
    private String prenom_nom_source;
    private Blob image_source;
    private boolean lue;

    public Notification(int id, int id_utilisateur, int id_source, int type_notif, String contenu, String prenom_nom_source, boolean lue) {
        this.id = id;
        this.id_utilisateur = id_utilisateur;
        this.id_source = id_source;
        this.type_notif = type_notif;
        this.contenu = contenu;
        this.prenom_nom_source = prenom_nom_source;
        this.lue = lue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getType_notif() {
        return type_notif;
    }

    public void setType_notif(int type_notif) {
        this.type_notif = type_notif;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId_source() {
        return id_source;
    }

    public void setId_source(int id_source) {
        this.id_source = id_source;
    }

    public Blob getImage_source() {
        return DaoFactory.getInstance().getUtilisateurDao().getUtilisateur(this.getId_source()).getImageProfile();
    }

    public void setImage_source(Blob image_source) {
        this.image_source = image_source;
    }

    public String getPrenom_nom_source() {
        return prenom_nom_source;
    }

    public void setPrenom_nom_source(String prenom_nom_source) {
        this.prenom_nom_source = prenom_nom_source;
    }

    public boolean isLue() {
        return lue;
    }

    public void setLue(boolean lue) {
        this.lue = lue;
    }
}
