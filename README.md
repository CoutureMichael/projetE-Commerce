Explication pour se connecter a E-Commerce

Repo Github
https://github.com/CoutureMichael/projetE-Commerce.git


Créer la base de données dans Mysql :

CREATE DATABASE ecommerce;
Use ecommerce;


Configurer le fichier persistence.xml 
URL MySQL
utilisateur (root)
mot de passe
hibernate.hbm2ddl.auto=update


Pour créer les table de la bd 
Démarer widfly et déployer le projet e-commerce
ouvirir l'application http://localhost:8080/E-Commerce et hibernate va créer automatiquement les table

Dans le dossier seed executer les 2 seeder utilisateurSeeder et produitSedder pour créer les donées dans les tables 

Pour se connecter a E-commerce 

email:admin@gmail.com
password:admin123

email:user@gmail.com
password:user123 







