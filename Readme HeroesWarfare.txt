Heroes Warfare guide :

Pré-requis :

- Java installé sur son pc => JDK installé, Path set (JAVA_HOME)

- Se munir d'un terminal pour les commandes

- Aller sur le git du projet : https://gaufre.informatique.univ-paris-diderot.fr/zhangs/jeu-de-strat

Puis, clonez le projet avec la commande : 

	[ git clone https://gaufre.informatique.univ-paris-diderot.fr/zhangs/jeu-de-strat.git ]

Une fois le projet cloné, ouvrir un terminal dans le dossier du projet puis saisir la commande :
	
	[ ./gradlew run ]

Gradle devrait être entrain de s'installer puis le jeu devrait se lancer à la fin.


Comment jouer ?

Interface :
- Au menu principal, cliquez sur jouer pour passer à l'écran suivant, ou quitter pour quitter le jeu.

- Après avoir cliqué sur jouer, vous accédez à la sélection du mode de jeu : Soit "joueur vs joueur", qui
est le mode local joueur contre joueur. Soit le mode "joueur vs IA", qui est le mode dans lequel vous 
jouez contre le robot.

- Après sélection du mode, vous accédez au menu des maps, ici vous pouvez choisir différentes maps ayant
des tailles et configuration différentes : bonus sur les maps, obstacles etc..

- Une fois sur le plateau et une fois la partie entamée, vous pouvez à tout moment appuyer sur "Echap",
appuyer dessus permet d'ouvrir une fenêtre vous demandant si vous voulez continuer à jouer ou bien quitter,
si vous quittez, vous retournerez au menu principal.


Règle du jeu :

Condition de victoire :
- Eliminer le héro adverse.


Achat d'unité :
- Cliquer sur le bouton du "Shop" pour acheter des unités, une fois l'unité acheté elle doit être placé.

- Une unité peut être achetée quand on le souhaite, l'argent est obtenue soit en tuant une autre unité,
soit en restant sur une case "Gold" (voir partie explicative du plateau).


Liste d'unité :
- Cliquez sur l'icone du casque afin d'afficher à l'écran la liste des unités que vous possédez, ainsi
que le nombre maximum possible d'unité que vous pouvez avoir.


Déplacement :
- Pour déplacer une unité, cliquez sur l'unité et choisissez l'icone de la botte puis cliquez sur une
des cases vertes qui signifie qu'on peut déplacer l'unité à cet endroit.


Attaque :
- Chaque unité possède une attaque de base et des compétences.

- L'attaque se fait en sélectionnant l'icone d'attaque puis en cliquant sur l'unité qu'on veut attaquer,
attention le friendly fire est actif, c'est à dire, vous pouvez aussi attaquer vos propres unités
(également toutes les compétences sont applicables sur tout le monde).


Système de tour :
- Au premier tour d'une unité, l'unité ne possède aucun point d'action et doit attendre le prochain tour.
Par exemple, lors de l'achat, ou lorsque la partie commence, les héros n'ont pas de PA (point d'action).

- Pour passer son tour, lorsque vous ne pouvez plus rien faire ie plus de PA etc.. Cliquez sur l'icone
de la flèche.


Guide des unités : 
