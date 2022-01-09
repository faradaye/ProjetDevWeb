# ProjetDevWeb

## Dépendances

- Java 11+
- Maven 3

## Lancement

Dans un terminal :
```bash
# On clone le dépôt...
git clone https://github.com/thomasbigel/ProjetDevWeb.git -b main
# ...et on se déplace dans sa racine
cd ProjetDevWeb

#On compile le projet
mvn package

# On lance le projet en utilisant tomcat
mvn tomcat7:run

L'url est http://localhost:8080/ProjetDevWeb/
```
## Diagramme de la base de données

![diagramme de classe base de données](./doc/uml_database.svg)
