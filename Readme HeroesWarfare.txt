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
-Archer: possède une compétence active, et une compétence passive. La compétence active est une attaque classique 
et la compétence passive est que l'archer peut récupérer 1PA par tour, lorsqu'il tue une unité.

-Assassin: possède quatre compétences actives. La première compétence est une attaque classique. La deuxième
compétence "poignard empoisoné" applique l'effet poison à une unité ciblée. La troisième compétence "camouflage" permet
d'appliquer l'effet camouflage à soi-même. La quatrième compétence "assassinat" permet d'achever une unité qui
possède moins de 100 point de vie.

-Cavalier: possède deux compétences actives. La première compétence est une attaque classique, et la suivante est
compétence de charge (permettant de foncer en ligne droite vers une direction).

-Hero: possède seulement une attaque classique. 

-Lancier: possède deux compétences actives. La première compétence est une attaque classique et la deuxieme compétence
"maniement agile" permet d'infliger moins de dégâts, mais permet d'étourdir une unité ciblée.

-Mage: possède trois compétences actives. La première compétence est une attaque classique. La deuxième compétence
"météore" permettant de faire des dégâts de zone depuis une unité ciblée. La troisième compétence "transformation en
mouton" permet de transformer une unité ciblée en mouton temporairement.

-Mouton: unité invocable seulement depuis la compétence "transformation en mouton" du mage. Possède seulement une 
attaque classique.

-Prétresse: possède trois compétences actives. La première compétence permet de soigner de 50 point de vie, une unité ciblée.
La deuxième compétence "lumière aveuglante" permet d'appliquer "aveugle" à une unité ciblée (lui empêchant d'attaquer). La troisième compétence "implorer le seigneur" est utilisable une fois par partie, et par joueur, et sous la 
condition d'avoir soigner avec 500 points de vie, avec une même prétresse. Cette compétence lui permet d'invoquer l'unité
"Prophête" sur le terrain. Le prophète ne peut être invoqué qu'une seule fois par partie, et par joueur.

-Prophête: unité invocable seulement depuis la compétence "implorer le seigneur" de la prétresse. Il possède trois compétences
actives. La première compétence est une attaque classique. La deuxième compétence "donationPA" permet de redonner à une unité
ciblée, 1PA. La troisième compétence "La voie du seigneur" permet de donner un effet spécial à une unité ciblée. Cet effet ne peut 
être appliqué qu'à une seule unité, tant que cette unité ciblée est encore en vie. Cet effet double les points de vie actuels,
la portée de déplacement, la portée d'attaque et les dégâts infligés.

-Templier: possède deux compétences actives et une compétence passive. La premiere compétence active est une attaque classique.
La deuxièeme compétence active permet d'appliquer une immunité contre l'immobilisation, l'étourdissement, le ralentissement, et 
l'aveuglement temporairement, à soi-même. La compétence passive permet de récupérer le double d'argent, lorsque le templier se
trouve dans une case "gold" du terrain.

