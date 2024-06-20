Ouvrez votre invite de commandes sur votre ordinateur

Dirigez-vous vers l'endroit ou vous avez mis votre fichier dans votre arborescence 

Pour compiler et executer le jeu de base , rendez-vous dans AppFinal et mettez la commande suivante

<code> javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur && cd ..  </code> 

Pour compiler et executer le scénario , rendez-vous dans AppFinal et mettez la commande suivante 

<code> javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "nom_scenario.run"  && cd class </code>

Pour rappel, il y'a comme fichier scénario :

scenario_1.run
scenario_2.run
scenario_3.run


Donc pour le scénario 1 :

<code> javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "scenario_1.run"  && cd class </code>

Donc pour le scénario 2 :

<code> javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "scenario_2.run"  && cd class </code>

Donc pour le scénario 3 :

<code> javac -encoding utf8 -d @Compile.list -d class && cd class && java psyche.Controleur "scenario_3.run"  && cd class </code>
