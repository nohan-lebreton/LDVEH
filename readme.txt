Veuillez vous mettre à la racine du projet.
Pour compiler et exécuter le programme, nous avons 2 possibilités :

La première, Compilation et Exécution classique :

Pour compiler:

	javac -d build src/gestion_fichier/fichier/*.java src/color/*.java src/gestion_fichier/fichier_graph/*.java src/console/*.java src/graph/*.java src/rpg/*.java src/main/*.java src/graphique/*.java src/graphique/editeur/*.java

Pour exécuter:

	java -cp build main.Main 

Pour créer la javadoc :
	javadoc -d docs/javadoc src/gestion_fichier/fichier/*.java src/color/*.java src/gestion_fichier/fichier_graph/*.java src/console/*.java src/graph/*.java src/rpg/*.java src/main/*.java src/graphique/*.java src/graphique/editeur/*.java



La deuxième, avec ANT :

Pour compiler:

	ant compile

Pour exécuter :

	ant run

Pour créer la javadoc :

	ant javadoc
	