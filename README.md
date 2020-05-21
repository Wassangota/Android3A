# Application Rick and Morty and Co
créé par Wassil Khetim

## Presentation

**Rick and Morty and Co**, application developpée dans le cadre du projet android de 3ème année, met en place une liste de personnage avec un affichage de détails supplémentaires pour chacun des personnages de la série.

Dans cette application on pourras retrouvé des données obtenues à partir de l'API *The Rick and Morty API* créée par Axel Fuhrmann et Talita et obtenable [ici](https://rickandmortyapi.com/).

## Élements de la consigne

Voici les éléments qui ont été respectés et ajoutés :

#### Éléments obligatoires (4/4):

- Écran avec une liste d'éléments;
- Écran avec le détail d'un élément;
- Appel WebService à une API Rest;
- Stockage de données en cache;

#### Éléments bonus :

- Utilisation de GLIDE pour afficher les images;
- Architecture MVC;
- Singletons;
- Gitflow;

## Fonctionalités

#### Écran d'accueil :

Lorsque l'on lance l'application pour la première fois elle va effectuer un appel serveur vers l'api Rick et Morty et récupérer les informations nécessaires des personnages de la série et les stocké les données dans le cache de l'application. En lançant l'application, si cette dernière trouve des données déjà sauvegardés, elle va les charger et les afficher à la place d'effectuer la demande get à l'API.

<p float="left">
  <img src=https://github.com/Wassangota/Android3A/blob/master/Screenshots/Screenshot_1590081995.png width=25%>
  <img src=https://github.com/Wassangota/Android3A/blob/master/Screenshots/Screenshot_1590082067.png width=25%>
</p>

#### Écran détail du personnage :

Lorsque l'on clique sur le nom de l'un des personnages dans la liste, l'application ouvre une nouvelle fenêtre (activity) donnant diverses détails du personnages.

<p float="left">
  <img src=https://github.com/Wassangota/Android3A/blob/master/Screenshots/Screenshot_1590082126.png width=25%>
  <img src=https://github.com/Wassangota/Android3A/blob/master/Screenshots/Screenshot_1590082151.png width=25%>
  <img src=https://github.com/Wassangota/Android3A/blob/master/Screenshots/Screenshot_1590082160.png width=25%>
</p>
