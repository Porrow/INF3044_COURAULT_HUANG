# INF3044_COURAULT_HUANG

Ce projet est en deux parties dépendantes l'une de l'autre. D'une part il y a une application android
et d'autre part, un programme serveur. L'application permet à tous ceux qui la possède de communiquer par messages texte. 
Le serveur transmet les messages aux différents utilisateurs connectés et enregistre ceux-ci pour les utilisateurs déconnectés.
Pour se rendre compte du plein potentiel de l'application, il faut donc malheureusement au moins deux téléphones.
Le code source du serveur est disponible à l'adresse suivante : https://github.com/courault/INF3044_SERVER
L'application ne peut pas fonctionner correctement sans le programme serveur ainsi qu'un réseau en commum pour les différents terminaux. 
Celui-ci est développé en java et s'execute donc sur n'importe quelle plateforme. Si vous souhaitez le faire tourner sur votre machine, 
il faut tout d'abord modifier dans le fichier "Connection.java" de l'application Android, le champ "HOST" avec la bonne adresse IP.
Cependant pour plus de simplicité je vais mettre le serveur en ligne (le 25/12 au plus tard, car je ne suis pas chez moi). Il sera donc utilisable directement avec une connexion internet, sans configuration supplémentaire à effectuer.
