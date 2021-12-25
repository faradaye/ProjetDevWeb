CREATE DATABASE IF NOT EXISTS covid19;
USE covid19;

create table IF NOT EXISTS Utilisateur(
	id int auto_increment primary key, 
    login varchar(255) NOT NULL,  
    `password` varchar(255) NOT NULL,
    nom varchar(255) NOT NULL, 
    prenom varchar(255) NOT NULL, 
    date_naissance date  NOT NULL,
    email varchar(255) DEFAULT NULL,
    administrateur BOOLEAN DEFAULT 0
) ENGINE=INNODB;

create table IF NOT EXISTS Lieu(
	id int auto_increment primary key, 
    nom varchar(255), 
    adresse varchar(255), 
    latitude decimal(8,6), 
    longitude decimal(9,6)
) ENGINE=INNODB;

create table IF NOT EXISTS Activite(
	id int auto_increment primary key,
	nom varchar(255) NOT NULL,
	date_activite date  NOT NULL, 
	heure_debut time  NOT NULL, 
	heure_fin time  NOT NULL, 
	id_lieu int  NOT NULL,
    foreign key (id_lieu) references Lieu(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=INNODB;

create table IF NOT EXISTS Amis(
	id_utilisateur1 int NOT NULL, 
    id_utilisateur2 int NOT NULL,
    primary key(id_utilisateur1,id_utilisateur2),
    foreign key (id_utilisateur1) references Utilisateur(id) ON UPDATE CASCADE ON DELETE CASCADE,
    foreign key (id_utilisateur2) references Utilisateur(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=INNODB;

create table IF NOT EXISTS Type_Notification(
	id int primary key, 
    nom varchar(255)
) ENGINE=INNODB;

create table IF NOT EXISTS Notifications(
	id int auto_increment, 
    id_utilisateur int,
    id_source int,
    type_notif int NOT NULL, 
    contenu varchar(255),
    lue BOOLEAN DEFAULT 0,
    primary key(id,id_utilisateur),
    foreign key (id_utilisateur) references Utilisateur(id),
    foreign key (id_source) references Utilisateur(id),
    foreign key (type_notif) references Type_Notification(id)
) ENGINE=INNODB;

create table IF NOT EXISTS RecupMotDePasse(
    id_utilisateur int,
    token varchar(255),
    primary key(id_utilisateur,token),
    foreign key (id_utilisateur) references Utilisateur(id)
) ENGINE=INNODB;

create table IF NOT EXISTS ParticipationActivite(
    id_utilisateur int,
    id_activite int,
    primary key(id_utilisateur, id_activite),
    foreign key (id_utilisateur) references Utilisateur(id) ON UPDATE CASCADE ON DELETE CASCADE,
    foreign key (id_activite) references Activite(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=INNODB;

INSERT INTO UTILISATEUR(login, `password`, nom, prenom, date_naissance, administrateur) VALUES('admin', 'mdpAdmin', 'Alan', 'Turing', '1912-06-23', 1);
INSERT INTO TYPE_NOTIFICATION(id, nom) VALUES(1, 'Demande d\'amis');
INSERT INTO TYPE_NOTIFICATION(id, nom) VALUES(2, 'Suppression amis');