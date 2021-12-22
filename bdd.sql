CREATE DATABASE IF NOT EXISTS covid19;
USE covid19;

create table IF NOT EXISTS Utilisateur(
	id int auto_increment primary key, 
    login varchar(255) NOT NULL,  
    `password` varchar(255) NOT NULL,
    nom varchar(255) NOT NULL, 
    prenom varchar(255) NOT NULL, 
    date_naissance date  NOT NULL, 
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
	id int auto_increment primary key, 
    nom varchar(255)
) ENGINE=INNODB;

create table IF NOT EXISTS Notifications(
	id int auto_increment, 
    id_utilisateur int,
    id_source int,
    type_notif int NOT NULL, 
    contenu varchar(255),
    primary key(id,id_utilisateur),
    foreign key (id_utilisateur) references Utilisateur(id),
    foreign key (id_source) references Utilisateur(id),
    foreign key (type_notif) references Type_Notification(id)
) ENGINE=INNODB;

INSERT INTO UTILISATEUR(login, `password`, nom, prenom, date_naissance, administrateur) VALUES('admin', 'mdpAdmin', 'Alan', 'Turing', '1912-06-23', 1)
