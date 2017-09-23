# ECOM SERVEUR
## Serveur JEE - EJB

ECOM est un serveur JEE de location de bungalows. Il donne accès à une API REST utilisant la serialisation JSON.
  
### API
| URL | TYPE | PARAMÈTRES | DESCRIPTION |
| ------ | ------ | ------ | ------ |
| /bungalows?requestid='1' | GET |  | Obtient l'ensemble des bungalows de la base |
| /bungalows?requestid='2' | GET | id | Obtient le bungalow définit par l'identifiant donné |
| /bungalows?requestid='3' | GET | minbedcount, minprice, maxprice, islandid, startweek, endweek | Obtient l'ensemble des bungalows de la base, correspondants aux critères donnés |
| /cart?requestid='1' | GET | | Retourne la liste des éléments présents dans le panier |
| /cart?requestid='2' | POST | | Vide le panier actuel|
| /cart?requestid='3' | POST | | Valide le panier afin de passer commande |

*Fonctionne avec le conteneur d'EJB WildFly **(v.10.x)** et communique avec une base de données MySQL **(5.1.x)**.*