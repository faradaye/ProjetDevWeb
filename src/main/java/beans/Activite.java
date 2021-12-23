package beans;

import dao.DaoFactory;

import java.sql.Date;
import java.sql.Time;

public class Activite {
    private int id;
    private String nom;
    private Date date_activite;
    private Time heure_debut;
    private Time heure_fin;
    private int id_lieu;

    private Lieu lieu;

    public Activite(int id, String nom, Date date_activite, Time heure_debut, Time heure_fin, int id_lieu) {
        this.id = id;
        this.nom = nom;
        this.date_activite = date_activite;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.id_lieu = id_lieu;
        this.lieu = DaoFactory.getInstance().getLieuDao().getLieu(id_lieu);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate_activite() {
        return date_activite;
    }

    public void setDate_activite(Date date_activite) {
        this.date_activite = date_activite;
    }

    public Time getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public Time getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(Time heure_fin) {
        this.heure_fin = heure_fin;
    }

    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
        this.lieu = DaoFactory.getInstance().getLieuDao().getLieu(id_lieu);
    }

    public Lieu getLieu() {
        return lieu;
    }
}
