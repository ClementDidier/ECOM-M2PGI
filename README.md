# ECOM-M2PGI
Projet e-commerce de 2ème année de Master informatique - Université Grenoble Alpes
Groupe 4 : Bungaloww
Abello Stephen
Didier Clément
Ejjelthi Zakariae      
Legrand Lucas


## Projet Ecom

Le projet consiste en une plateforme de réservation de bungalow de vacances sur îles, en ligne.

### Client AngularJS

La gestion du coté frontend alimentée par une instance AngularJS.
Le client propose des fonctionnalités à l'utilisateur et communique avec le backend par des appels RESTful, utilisant des services injectables basés sur la bibliothèque `HTTP` d'AngularJS.

L'interface est organisée par la bibliothèque `bootstrap` installée via les modules d'AngularJS.
Enfin toutes les dépendances du frontend sont téléchargées lors du `npm install`, grace à un fichier de gestion des dépendances du projet (Natif à AngularJS).

Démarrez le serveur avec `npm start` afin de lancer le serveur avec le proxy local.
### Serveur JEE - EJB

ECOM est un serveur JEE de location de bungalows. Il donne accès à une API REST utilisant la serialisation JSON.

#### API
| URL | TYPE | PARAMÈTRES | DESCRIPTION |
| ------ | ------ | ------ | ------ |
| /bungalows?requestid='1' | GET |  | Obtient l'ensemble des bungalows de la base |
| /bungalows?requestid='2' | GET | id | Obtient le bungalow définit par l'identifiant donné |
| /bungalows?requestid='3' | GET | minbedcount, minprice, maxprice, islandid, startweek, endweek | Obtient l'ensemble des bungalows de la base, correspondants aux critères donnés |
| /cart?requestid='1' | GET | | Retourne la liste des éléments présents dans le panier |
| /cart?requestid='2' | POST | | Vide le panier actuel|
| /cart?requestid='3' | POST | | Valide le panier afin de passer commande |

*Fonctionne avec le conteneur d'EJB WildFly **(v.10.x)** et communique avec une base de données MySQL **(5.1.x)**.*
